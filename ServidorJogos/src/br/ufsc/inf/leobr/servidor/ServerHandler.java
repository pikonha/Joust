/* This file is part of the DualRpcServer libraries
 Copyright (c) 2004-2005, Vick Perry

 This library is free software; you can redistribute it and/or
 modify it under the terms of the BSD license. See the contents
 of the file LICENSE.txt for more details.
 */
package br.ufsc.inf.leobr.servidor;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.logging.Logger;

import br.ufsc.inf.leobr.cliente.DadosAplicacao;

import com.retrogui.dualrpc.server.AbstractServerRpcHandler;
import com.retrogui.dualrpc.server.DualRpcServerDispatcher;

public class ServerHandler extends AbstractServerRpcHandler {

	public static final String PRIMARY_CLIENT_HANDLER_CLASSNAME = "br.ufsc.inf.leobr.cliente.ClienteTratador";

	public static Logger logger = Logger.getLogger(ServerHandler.class
			.getName());

	public Server server = null;

	public long serverSessionId = 0;

	public long clientSessionId = 0;

	public Jogo jogo;

	public Partida partida;

	public String nomeJogador;

	private boolean estahConectado;
	
	public ServerHandler(DualRpcServerDispatcher dispatcher) {
		super(dispatcher);
		// O objeto de configuração é o Server
		this.server = (Server) this.getServerHandlerConfigurationObject();

		logger.info("Objeto de configuração é: "
				+ this.server.getClass().getName());

		// store server session id for later use
		this.serverSessionId = this.getDispatcher().getSession().getSessionId();

		ConexaoPerdidaTratadorServidor lHandler = new ConexaoPerdidaTratadorServidor(
				this);

		this.getDispatcher().setCallbackHandler(lHandler);
		this.estahConectado = true;
	}

	// ---------------------------------- Get e Set

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	// ----------------------------  Cliente -> Servidor

	/**
	 * Stratup com o cliente.
	 * 
	 * @param clientSessionId
	 * @return
	 */
	public DadosAplicacao estabelecerSessao(Long clientSessionId, Long idJogo,
			String nomeJogador) {

		// set client session id
		this.clientSessionId = clientSessionId.longValue();
		logger.info("Server: Id de sessão " + this.serverSessionId
				+ " corresponds to client session id: " + this.clientSessionId);

		DadosAplicacao appData = new DadosAplicacao();
		appData.serverSessionId = new Long(this.serverSessionId);

		this.nomeJogador = nomeJogador;
		this.jogo = server.cadastrarNoJogo(this, idJogo);

		logger.info("Jogador("+this.nomeJogador+"): Sessão entre cliente e servidor estabelecida.");
		return appData;
	}

	public void enviaJogada(MarshalledObject jogada) {
		logger.info("Jogador("+this.nomeJogador+"): Tentando efetuar uma jogada.");
		partida.efetuaJogada(this, jogada);
	}

	

	public void iniciarPartida(Integer qtdeJogadoresNaPartida) {
		logger.info("Jogador("+this.nomeJogador+"): Tentando iniciar uma partida.");
		if (this.partida != null) {
			jogo.encerrarPartida(this.partida);
		}
		partida = null;
		this.jogo.iniciarPartida(this, qtdeJogadoresNaPartida);
	}
	
	/**
	 * O jogador continua conectado mas a partida é finalizada.
	 */
	public void finalizarPartida(){
		logger.info("Jogador("+this.nomeJogador+"): Tentando finalizar a partida atual (" + this.partida.getIdPartida()+")");
		if (partida != null){
			jogo.encerrarPartida(this.partida);
		}
		partida = null;
	}

	public void reiniciarPartida() {
		logger.info("Jogador("+this.nomeJogador+"): Tentando reiniciar a partida atual (" + this.partida.getIdPartida()+")");
 		if (this.partida != null) {
			partida.reiniciar(this);
		}
	}

	public void desconectar() {
		logger.info("Jogador("+this.nomeJogador+"): Desconectando");
		this.estahConectado = false;
		
		if (jogo != null) {
			jogo.removerJogador(this);
			server.removerJogo(jogo);
		}
		this.jogo = null;
		this.partida = null;
		
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	/**
	 * Obtêm o nome dos jogadores da partida na qual o jogador está participando no momento
	 * @return
	 */
	public List<String> obterNomeJogadores() {
		if (this.estahJogando()){ 
			return this.partida.obterNomeJogadores();
		}else{
			return this.jogo.obterNomeJogadores();
		}
		
		
	}
	
	// ----------------------------  Servidor -> Cliente
	
	public void enviaMensagem(String s) {
		logger.info("Jogador("+this.nomeJogador+"): Invocando o método receberMensagem no jogador");
		try {
			this.getDispatcher().callAsync(PRIMARY_CLIENT_HANDLER_CLASSNAME,
					"receberMensagem", s);
		} catch (Exception ex) {
			logger.info("Erro em enviaMensagem(String s) ");
			this.tratarExcecaoConexaoPerdida();
		}
	}

	public void recebeJogada(MarshalledObject jogada) {
		logger.info("Jogador("+this.nomeJogador+"): Invocando o método receberJogada no jogador");
		try {
			// callAsync because no return needed from client
			this.getDispatcher().callAsync(PRIMARY_CLIENT_HANDLER_CLASSNAME,
					"recebeJogada", jogada);
		} catch (Exception ex) {
			// se não é possivel enviar uma jogada então deve finalizar a
			// partida.
			logger.info("Erro em passaJogada(MarshalledObject jogada) ");
			this.tratarExcecaoConexaoPerdida();
		}

	}

	public void iniciarNovaPartida(Integer posicao) {
		logger.info("Jogador("+this.nomeJogador+"): Invocando o método iniciarNovaPartida no jogador");
		try {
			// callAsync because no return needed from client
			this.getDispatcher().callAsync(PRIMARY_CLIENT_HANDLER_CLASSNAME,
					"iniciarNovaPartida", posicao);
		} catch (Exception ex) {
			logger.info("Erro em iniciarNovaPartida(Integer posicao) "
					+ this.nomeJogador);
			this.tratarExcecaoConexaoPerdida();
		}

	}

	public void finalizarPartidaComErro(String message) {
		logger.info("Jogador("+this.nomeJogador+"): Invocando o método finalizarPartidaComErro no jogador");
		try {
			this.partida = null;
			this.getDispatcher().callAsync(PRIMARY_CLIENT_HANDLER_CLASSNAME,
					"finalizarPartidaComErro", message);
		} catch (Exception ex) {
			logger.info("Erro em finalizarPartidaComErro(String message)");
			this.tratarExcecaoConexaoPerdida();
		}

	}

	public void tratarPartidaNaoIniciada(String message) {
		logger.info("Jogador("+this.nomeJogador+"): Invocando o método tratarPartidaNaoIniciada no jogador");
		try {
			this.getDispatcher().callAsync(PRIMARY_CLIENT_HANDLER_CLASSNAME,
					"tratarPartidaNaoInciada", message);
		} catch (Exception ex) {
			logger.info("Erro em tratarPartidaNaoIniciada(String message)");
			this.tratarExcecaoConexaoPerdida();
		}
	}
	
	public void tratarExcecaoConexaoPerdida(){
		this.desconectar();
	}
	
	public boolean estahJogando(){
		if (this.partida != null){
			return true;
		}
		
		return false;
	}

	/**
	 * Indica se o jogador estah conectado
	 * @return
	 */
	public boolean estahConectado() {
		return estahConectado;
	}

}
