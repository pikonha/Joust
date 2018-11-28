/* This file is part of the DualRpcServer libraries
 Copyright (c) 2004-2005, Vick Perry

 This library is free software; you can redistribute it and/or
 modify it under the terms of the BSD license. See the contents
 of the file LICENSE.txt for more details.
 */
package br.ufsc.inf.leobr.servidor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;

import com.retrogui.dualrpc.server.DualRpcServer;

/**
 * O servidor
 * 
 * @author LSB
 */
public class Server {

	private static final String LOGFILE_PROPERTY = "log4j.appender.rollinglogfile.File";

	private static final String DUALRPC_DIRECTORY = ".dualrpc";

	private static final String SYSTEM_PROPERTIES_USER_HOME = "user.home";

	private static final String CONFIG_SERVER_HANDLER_CLASSNAME_PREFIX = "server.handler.classname.";

	private static final String CONFIG_SERVER_PORT = "server.port";

	private static final String CONFIG_SERVER_HOST = "server.host";

	private static final String DEFAULT_CONFIGURATION_FILE = "server.properties";

	private static final String CONFIGURATION_FILE_VM_ARG = "server.configuration.file";

	private static Logger logger = Logger.getLogger(Server.class.getName());

	private DualRpcServer dualRpcServer = null;

	private String serverHost = null;

	private int serverPort = 0;

	private Map<Long, Jogo> jogos = new HashMap<Long, Jogo>();

	public Server() {
	}

	public void listen() throws IOException, ClassNotFoundException {

		String configurationFile = DEFAULT_CONFIGURATION_FILE;

		String alternateConfigurationFile = System
				.getProperty(CONFIGURATION_FILE_VM_ARG);

		if (alternateConfigurationFile != null) {
			configurationFile = alternateConfigurationFile;
		}
		
		URL urlJmp = ClassLoader.getSystemResource(configurationFile);
		
		InputStream in = null;

		if (urlJmp == null) {
			in = this.getClass().getClassLoader().getResourceAsStream(
					configurationFile);
		} else {
			in = urlJmp.openStream();
		}
		
		File arquivoMultiplayer = new File(configurationFile);

		if (in == null) {
			logger.info("Server: Arquivo não encontrado internamente.");
			in = new BufferedInputStream(new FileInputStream(
					arquivoMultiplayer));

			if (in == null) {
				logger.info("Server: Arquivo de configuração não encontrado externamente.");
			}

		}

		
		Properties configurationProperties = new Properties();
		configurationProperties.load(in);
		in.close();

		String homedir = System.getProperty(SYSTEM_PROPERTIES_USER_HOME);
		String logdir = (homedir == null ? "" : homedir + File.separator)
				+ DUALRPC_DIRECTORY;

		File dir = new File(logdir);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new IOException("Cannot create logging directory: "
						+ logdir);
			}
		}

		// is the logging directory really a directory?
		if (!dir.isDirectory()) {
			throw new IOException(
					"Logging directory path conflicts with an existing file: "
							+ logdir);
		}

		// prepend the logging directory to the log file name
		String originalLogFile = configurationProperties
				.getProperty(LOGFILE_PROPERTY);
		String newLogFile = logdir + File.separator + originalLogFile;
		configurationProperties.setProperty(LOGFILE_PROPERTY, newLogFile);

		// configure log4j logger from properties
		org.apache.log4j.PropertyConfigurator
				.configure(configurationProperties);

		// startup log entry
		logger
				.info("Starting client with configuration file "
						+ configurationFile);
		logger.info("Logging to " + newLogFile);

		// To insure communication when both IPv4 and IPv6 stacks are running
		// force
		// everything to IPv4. Later when IPv6 is in everyday use then make this
		// configurable
		logger.info("Forcing use of IPv4");
		System.setProperty("java.net.preferIPv4Stack", "true");

		// Read properties file
		this.serverHost = configurationProperties
				.getProperty(CONFIG_SERVER_HOST);
		logger.info(CONFIG_SERVER_HOST + "=" + this.serverHost);
		this.serverPort = Integer.parseInt(configurationProperties
				.getProperty(CONFIG_SERVER_PORT));
		logger.info(CONFIG_SERVER_PORT + "=" + this.serverPort);

		// Instantiate server with regular server socket factory.
		// The configuration object is this Server object.
		this.dualRpcServer = new DualRpcServer(this.serverHost,
				this.serverPort, this);

		String handlerClassname = null;
		int i = 0;

		while (true) {
			handlerClassname = configurationProperties
					.getProperty(CONFIG_SERVER_HANDLER_CLASSNAME_PREFIX + i);

			if (handlerClassname != null) {
				this.dualRpcServer
						.registerServerSideHandlerClassname(handlerClassname);
			} else {
				break;
			}

			i++;
		}

		this.dualRpcServer.setMaxConnections(500);
		// Inicia o servidor
		this.dualRpcServer.listen();
	}

	/**
	 * Insere um novo jogador em um jogo no servidor.
	 * Caso o jogo não exista ele cria a representação do jogo.
	 * @param jogador
	 * @param idJogo
	 * @return
	 */
	public synchronized Jogo cadastrarNoJogo(ServerHandler jogador, Long idJogo) {

		logger.info("Server: Adicionando o jogador " + jogador.getNomeJogador() + " no jogo " + idJogo);
		Jogo jogo;
		
		synchronized (this.jogos) {
			if (jogos.containsKey(idJogo)) {
				logger
				.info("Server: Jogo " + idJogo+ "já está rodando.");
				jogo = jogos.get(idJogo);
			} else {
				jogo = new Jogo(idJogo);
				jogos.put(idJogo, jogo);
				logger.info("Server: Jogo "+ idJogo+ " criado. Número de jogos ativos: " + this.jogos.size()); 
				
			}
		}

		jogo.incluirJogador(jogador);
		jogador.setJogo(jogo);

		logger.info("Server: Jogador "+ jogador.getNomeJogador()+" adicionado no jogo " + idJogo);
		
		notifyAll();

		return jogo;

	}

	/**
	 * Remove um jogo do servidor.
	 * Confere se o jogo não possui mais jogadores e somente depois retira ele da lista de jogos ativos.
	 * @param jogo
	 */
	public synchronized void removerJogo(Jogo jogo) {
		
		logger.info("Server: Verificando se é possível finalizar o jogo " + jogo.getIdJogo()+ " no servidor"); 
		if (jogo.estahFinalizado()) {
			if (this.jogos.containsValue(jogo)) {
				jogo.finalizar();
				this.jogos.remove(jogo.getIdJogo());
				logger.info("Server: Jogo "+jogo.getIdJogo()+" removido do servidor. Número de jogos ativos: "+ this.jogos.size());
			}

		}
		notifyAll();
	}

}
