package br.ufsc.inf.leobr.servidor;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Leonardo Brasil
 * 
 */
/**
 * @author grad
 * 
 */
public class Partida {

	private static final long serialVersionUID = 1L;

	private Jogo jogo;

	private List<ServerHandler> jogadores = new ArrayList<ServerHandler>();

	private ServerHandler jogadorDono;
	
	private long idPartida;

	private static Logger logger = Logger.getLogger(Partida.class
			.getName());


	/**
	 * Construtor com uma lista com n elementos de uma partida.
	 * 
	 * @param jogo
	 * @param jogadores
	 */
	public Partida(long idPartida, Jogo jogo, List<ServerHandler> jogadores) {
		super();
		this.jogadores = Collections
				.synchronizedList(new ArrayList<ServerHandler>());
		this.jogadores.addAll(jogadores);

		if (jogadores.size() > 0) {
			this.setJogadorDono(jogadores.get(0));
		}

		this.jogo = jogo;
		this.idPartida = idPartida;
		logger = Logger.getLogger("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Partida criada." );
	}

	/**
	 * Retorna o jogador dono de uma partida. O jogador dono é o jogador que
	 * iniciou a partida e é sempre o primeiro a jogar.
	 * 
	 * @return
	 */
	public ServerHandler getJogadorDono() {
		return jogadorDono;
	}

	/**
	 * O jogador dono deve sempre estar na lista de jogadores.
	 * 
	 * @param jogadorDono
	 */
	public void setJogadorDono(ServerHandler jogadorDono) {
		if (jogadores.contains(jogadorDono)) {
			this.jogadorDono = jogadorDono;
		}
	}

	/**
	 * @param qualJogador
	 * @return true se o jogador está participando da partida e false caso
	 *         contrário
	 */
	public boolean possuiJogador(ServerHandler qualJogador) {

		for (ServerHandler jogador : jogadores) {
			if (jogador.equals(qualJogador)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Envia mensagens para os dois jogadores informando que o jogo começou e
	 * informando que é o adversário
	 */
	public void iniciaPartida(ServerHandler jogadorPedinte) {

		// Se o cliente não definiu uma ordem específica

		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Iniciando uma nova partida.");

		int posicao = 1;

		jogadorPedinte.iniciarNovaPartida(posicao);
		posicao++;

		logger
		.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"):Enviando mensagem de inicio de partida a todos os jogadores.");
		for (ServerHandler jogador : jogadores) {

			if (!(jogador.equals(jogadorPedinte))) {
				jogador.iniciarNovaPartida(posicao);
				posicao++;
			}
		}
		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Partida iniciada com sucesso");

	}

	/**
	 * Encaminha a jogada a todos os jogadores. Menos para o jogador que enviou a jogada.
	 * 
	 * @param jogadorEfetuante
	 * @param jogada
	 */
	public void efetuaJogada(ServerHandler jogadorEfetuante,
			MarshalledObject jogada) {

		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Efetuando uma jogada enviada pelo jogador " + jogadorEfetuante.getNomeJogador());
		
		List<ServerHandler> jogadoresLista = new ArrayList<ServerHandler>();
		jogadoresLista.addAll(this.jogadores);

		jogadoresLista.remove(jogadorEfetuante);

		for (ServerHandler jogador : jogadoresLista) {
			jogador.recebeJogada(jogada);
		}
		
		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Jogada efetuada com sucesso pelo jogador " + jogadorEfetuante.getNomeJogador());

	}

	/**
	 * Finaliza uma partida com erro.
	 */
	public void encerrarPartida() {

		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Encerrando a partida.");
		
		this.jogo = null;
		this.jogadorDono = null;
		
		for (ServerHandler jogador : this.jogadores) {
			if (jogador.estahConectado()){
				jogador
				.finalizarPartidaComErro("Um dos jogadores caiu e a partida foi finalizada");
			}
		}

		this.jogadores = null;
	}

	public void reiniciar(ServerHandler jogadorPedinte) {
		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Reiniciando a partida.");	
		this.iniciaPartida(jogadorPedinte);
	}

	
	public List<String> obterNomeJogadores() {

		logger.info("Jogo("+ jogo.getIdJogo() +") Partida("+ idPartida +"): Obtendo o nome dos jogadores");
		List<String> nomesJogadores = new ArrayList<String>();

		for (ServerHandler jogador : jogadores) {
			nomesJogadores.add(jogador.getNomeJogador());
		}

		return nomesJogadores;

	}

	public long getIdPartida() {
		return idPartida;
	}
	
}
