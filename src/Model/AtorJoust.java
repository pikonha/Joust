/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.InterfaceTabuleiro;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import rede.AtorRede;
import rede.Lance;

/**
 *
 * @author Lucas
 */
public class AtorJoust {
    
    private String idJogador;
    private Tabuleiro tabuleiro;
    private InterfaceTabuleiro interfaceTabuleiro;
    private AtorRede atorRede;
        
    public AtorJoust() {
       atorRede = new AtorRede(this);   
       tabuleiro = new Tabuleiro();
    }

    public Component getInterface() {
        return interfaceTabuleiro;
    }   
    
    
    public void iniciarNovaPartida(boolean comecoJogando) {    	   	
    	tabuleiro.resetarPosicoes();   
    	
        String nomeAdversario = atorRede.obterNomeAdversario(idJogador);
                
        if (comecoJogando) {
         	tabuleiro.criaJogador1(idJogador);
            tabuleiro.criaJogador2(nomeAdversario);
            
            interfaceTabuleiro.setNomeJogador1(idJogador);
            interfaceTabuleiro.setNomeJogador2(nomeAdversario);   
            
            JOptionPane.showMessageDialog(interfaceTabuleiro,"Você começa jogando. Sua cor é a branca.");
        } else {
         	tabuleiro.criaJogador1(nomeAdversario);
            tabuleiro.criaJogador2(idJogador);
            
            interfaceTabuleiro.setNomeJogador1(nomeAdversario);
            interfaceTabuleiro.setNomeJogador2(idJogador);   
            
            JOptionPane.showMessageDialog(interfaceTabuleiro,"O adversário começa jogando. Sua cor é a preta.");
        }
                
        tabuleiro.setEmAndamento(true);   
        tabuleiro.ativarPosicoesIniciais();
        
        Cavalo jogador1 = tabuleiro.getJogador(idJogador);
    	interfaceTabuleiro.setPosicaoJogador(jogador1.getCor(), jogador1.getLinha(), jogador1.getColuna());
    	
    	Cavalo jogador2 = tabuleiro.getJogador(nomeAdversario);
    	interfaceTabuleiro.setPosicaoJogador(jogador2.getCor(), jogador2.getLinha(), jogador2.getColuna());
    }
    
    
    public void novoLance(int linha, int coluna) { 
    	Cavalo jogador = tabuleiro.getJogador(idJogador);    	
    	
    	if (jogador.getDaVez()) {
            Lance lance = new Lance(linha, coluna, 
            		jogador.getLinha(), 
            		jogador.getColuna(), idJogador);
            
        	int resultado = tabuleiro.trataLance(lance);
            
            if (resultado == 0) {        	
                atorRede.enviarJogada(lance);
                
                interfaceTabuleiro.desativaPosicao(jogador.getLinha(), jogador.getColuna());
                tabuleiro.getPosicao(jogador.getCor(), jogador.getLinha()).setOcupacao(3);
                
                jogador.setLinha(lance.getLinha());
                jogador.setColuna(lance.getColuna());
                jogador.setDaVez(false);
                
                interfaceTabuleiro.setPosicaoJogador(jogador.getCor(), jogador.getLinha(), jogador.getColuna());                
                tabuleiro.getPosicao(jogador.getCor(), jogador.getLinha()).setOcupacao(jogador.getCor());
               
            } else if (resultado == 1) {
            	System.out.println("posicao invalida");
//            	JOptionPane.showMessageDialog(interfaceTabuleiro, "Posição inválida.");
            } else if (resultado == 2) {
            	JOptionPane.showMessageDialog(interfaceTabuleiro, "Jogador " + tabuleiro.getIdVencedor() + " venceu.");
            	tabuleiro.resetarPosicoes();
            } else
                JOptionPane.showMessageDialog(interfaceTabuleiro, "Ocorreu um erro no tratamento de lance.");    
    	}
    	else 
    		System.out.println("não é sua vez");
    }
    
    public void receberLanceRede(Lance lance) {
    	interfaceTabuleiro.desativaPosicao(lance.blinha, lance.bcoluna);
    	
    	tabuleiro.assumirLance(lance);        
    	
    	Cavalo jogador = tabuleiro.getJogador(lance.getIdJogador());
        interfaceTabuleiro.setPosicaoJogador(jogador.getCor(), lance.getLinha(), lance.getColuna());
        
        tabuleiro.getJogador(idJogador).setDaVez(true);        
    }

    public void go() {
        interfaceTabuleiro = new InterfaceTabuleiro(this);
        
        idJogador = JOptionPane.showInputDialog(interfaceTabuleiro, "Escolha o nome do participante:");
        
        interfaceTabuleiro.setVisible(true);
        interfaceTabuleiro.setStatus("Aguardando conexão");  
    }

    
    /* request para iniciar uma nova partida */
    public void iniciarPartida() {  
//    	if (tabuleiro.infomarEmAndamento())
//    		JOptionPane.showMessageDialog(getInterface(), "Jogo já em andamento");
    	
    	this.atorRede.iniciarPartidaRede();
    }
    
    public static void main(String[] args) {
        new AtorJoust().go();
    }  

    public void desconectar() {
        atorRede.desconectar();
		tabuleiro.setEmAndamento(false);
        interfaceTabuleiro.setStatus("Conectado");
    }

    public void encerrar() {
        atorRede.desconectar();
        tabuleiro.setEmAndamento(false);
        System.exit(0);
    }

    public void conectar() {
            	
    	atorRede.conectar(idJogador);
        interfaceTabuleiro.setStatus("Conectado");
    }
    

}
