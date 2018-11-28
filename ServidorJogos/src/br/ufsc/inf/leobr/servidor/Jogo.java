package br.ufsc.inf.leobr.servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



/**
 * @author Leonardo Brasil
 * 
 */
public class Jogo {

	/**
	 * O identificador do jogo.
	 */
	private Long idJogo;

	/**
	 * Partidas de um jogo.
	 */
	private List<Partida> partidas;
	
	/**
	 * Id atribuido a ultima partida criada.
	 */
	private long ultimaPartida;

	/**
	 * Os jogadores de um jogo
	 */
	private List<ServerHandler> jogadores;
	
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(Jogo.class
			.getName());

	
	/**
	 * Retorna o identificador do jogo.
	 * @return
	 */
	public Long getIdJogo() {
		return idJogo;
	}

	/**
	 * Construtor do jogo.
	 * Instancia as listas de jogadores e partidas.
	 * @param idJogo Identificador atribuido ao jogo.
	 */
	public Jogo(Long idJogo) {
		this.idJogo = idJogo;
		this.jogadores = new ArrayList<ServerHandler>();
		this.partidas = new ArrayList<Partida>();
		this.ultimaPartida = 0;
		logger.info("Jogo (" + idJogo +") criado com sucesso!");

	}

	/**
	 * Inclui um jogador em um jogo.
	 * 
	 * @param serverHandler
	 */
	public void incluirJogador(ServerHandler serverHandler) {
		synchronized (this.jogadores) {
			this.jogadores.add(serverHandler);
		}
		logger.info("Jogador: " + serverHandler.getNomeJogador()+ " incluido no jogo (" + idJogo +"). Numero de jogadores ativos: " + this.jogadores.size());
	}

	/**
	 * Envia uma mensagem para os jogadores de um jogo.
	 * 
	 * @param destino
	 *            Quem está enviando a mensagem
	 * @param mensagem
	 *            A mensagem em texto a ser enviada
	 */
	public void enviaMensagem(ServerHandler destino, String mensagem) {
		for (ServerHandler handler : jogadores) {
			if (!(handler.equals(destino))) {
				handler.enviaMensagem(mensagem);
			}
		}

	}

	/**
	 * Inicia uma nova partida
	 * 
	 * @param jogadorPedinte
	 *            O jogador que requisitou o inicio de uma nova partida
	 * @param quantidadeJogadores
	 *            O número exato de jogadores que essa partida deve conter,
	 *            incluindo o jogador que fez a requisição
	 */
	public void iniciarPartida(ServerHandler jogadorPedinte,
			Integer quantidadeJogadores) {
		
		logger.info("Jogo("+idJogo+"): Tentando iniciar uma partida pedida pelo jogador " + jogadorPedinte.getNomeJogador());
		
		synchronized (this.jogadores) {

			List<ServerHandler> jogadoresPartida = new ArrayList<ServerHandler>();
			jogadoresPartida.add(jogadorPedinte);

			for (ServerHandler jogador : this.jogadores) {

				if (!jogador.equals(jogadorPedinte) && !(jogador.estahJogando())) {
					jogadoresPartida.add(jogador);
					logger.info("Jogo("+ idJogo +"): Jogador " + jogador.getNomeJogador() + " selecionado para uma nova partida");
					
					if (jogadoresPartida.size() == quantidadeJogadores){
						break;
					}
				}

				
			}

			if (quantidadeJogadores.equals(jogadoresPartida.size())) {

				ultimaPartida++;
				Partida partida = new Partida(ultimaPartida, this, jogadoresPartida);
				
				for (ServerHandler jogadorPartida : jogadoresPartida) {
					jogadorPartida.setPartida(partida);
				}

				synchronized (this.partidas) {
					partidas.add(partida);
					logger.info("Jogo("+ idJogo +"): Uma nova partida adicionada. Número de partidas: " +partidas.size());
				}
				
				partida.iniciaPartida(jogadorPedinte);
			}else{
				jogadorPedinte
				.tratarPartidaNaoIniciada("Não haviam jogadores sulficientes no servidor. Por favor tente novamente mais tarde");
				logger.info("Jogo("+ idJogo +"): A partida não pode ser iniciada porque não haviam jogadores suficientes.");
			}

		}

	}


	/**
	 * Remove um jogador do jogo. Se o jogador não estiver no jogo, procura em
	 * uma das partidas do jogo. Se encontrado então remove o jogador da
	 * partida.
	 * 
	 * @param jogador
	 */
	public void removerJogador(ServerHandler jogador) {

		logger.info("Jogo("+ idJogo +"): Removendo o jogador"+ jogador.getNomeJogador() +" do jogo.");
		
		if (jogadores.contains(jogador)) {
			jogadores.remove(jogador);
			Partida partida = jogador.getPartida();
			if (partida != null){
				this.encerrarPartida(partida);
			}
		}else{
			logger.info("O jogador "+  jogador.getNomeJogador() +" não estava presente no jogo " +idJogo +".");
		}

	}

	/**
	 * Envia uma mensagem para os jogadores de um jogo.
	 * 
	 * @param destino
	 *            Quem está enviando a mensagem
	 * @param mensagem
	 *            A mensagem em texto a ser enviada
	 */
	public void enviaMensagemParaTodos(String mensagem) {
		for (ServerHandler handler : jogadores) {
			handler.enviaMensagem(mensagem);
		}

	}


	public boolean estahFinalizado() {
		synchronized (this.jogadores) {
			if (this.jogadores.size() == 0) {
				logger.info("Jogo("+idJogo+"): Nao possui mais jogadores");
				return true;
			}
			
			boolean todosDesconectados = true;
			
			logger.info("Jogo("+idJogo+"): Verificando se todos jogadores estao conectados");
			for (ServerHandler jogador : jogadores) {
				if (jogador.estahConectado()){
					todosDesconectados = false;
					break;
				}
			}
			
			if (todosDesconectados){
				logger.info("Jogo("+idJogo+"): Todos os jogadores estao desconectados, jogo finalizado.");
				return true;
			}
		}
		logger.info("Jogo("+idJogo+"): Ainda possui "+ jogadores.size()+ " jogadores");
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> obterNomeJogadores() {
		
		logger.info("Jogo("+ idJogo +"): Obtendo o nome dos jogadores.");

		List<String> nomesJogadores = new ArrayList<String>();

		for (ServerHandler jogador : jogadores) {
			nomesJogadores.add(jogador.getNomeJogador());
		}

		return nomesJogadores;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idJogo == null) ? 0 : idJogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Jogo other = (Jogo) obj;
		if (idJogo == null) {
			if (other.idJogo != null)
				return false;
		} else if (!idJogo.equals(other.idJogo))
			return false;
		return true;
	}

	public void encerrarPartida(Partida partida) {
		
		logger.info("Jogo("+ idJogo +"): Encerrando a partida: " + partida.getIdPartida());
		synchronized (this.partidas) {
			if (this.partidas.contains(partida)){
				partida.encerrarPartida();
				this.partidas.remove(partida);
				logger.info("Jogo("+ idJogo +"): Partida removida. Número de partidas: " + partidas.size());
			}else{
				logger.info("Jogo("+ idJogo +"): A partida"+ partida.getIdPartida()+" não está nesse jogo");
			}
		}
		
		
	}

	/**
	 * Finaliza o jogo.
	 */
	public void finalizar() {
		this.jogadores = null;
		this.partidas = null;
		
	}

}
