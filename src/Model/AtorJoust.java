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
    	tabuleiro.criarJogador(idJogador);
    	
        String nomeAdversario = atorRede.obterNomeAdversario(idJogador);
                
        tabuleiro.criarJogador(nomeAdversario);
        
        tabuleiro.setEmAndamento(true);        
        
        interfaceTabuleiro.setNomeJogador1(idJogador);
        interfaceTabuleiro.setNomeJogador2(nomeAdversario);     
        
        Cavalo jogador1 = tabuleiro.getJogador(idJogador);
    	interfaceTabuleiro.setPosicaoJogador(jogador1.getCor(), jogador1.getLinha(), jogador1.getColuna());
    	
    	Cavalo jogador2 = tabuleiro.getJogador(nomeAdversario);
    	interfaceTabuleiro.setPosicaoJogador(jogador2.getCor(), jogador2.getLinha(), jogador2.getColuna());
    }
    
    
    public void novoLance(Lance lance) { 
        int resultado = tabuleiro.trataLance(lance);
        
        if (resultado == 0) {
        	Cavalo jogador = tabuleiro.getJogador(idJogador);
        	
            atorRede.enviarJogada(new Lance(lance.getLinha(), lance.getColuna(), lance.getIdJogador()));
            
            interfaceTabuleiro.desativaPosicao(jogador.getLinha(), jogador.getColuna());
            tabuleiro.getPosicao(jogador.getCor(), jogador.getLinha()).setOcupacao(3);
            
            jogador.setLinha(lance.getLinha());
            jogador.setColuna(lance.getColuna());
            
            interfaceTabuleiro.setPosicaoJogador(jogador.getCor(), jogador.getLinha(), jogador.getColuna());                
            tabuleiro.getPosicao(jogador.getCor(), jogador.getLinha()).setOcupacao(jogador.getCor());
           
        } else if (resultado == 1) {
//        	JOptionPane.showMessageDialog(interfaceTabuleiro, "Posição inválida.");
        } else if (resultado == 2) {
        	JOptionPane.showMessageDialog(interfaceTabuleiro, "Jogador " + tabuleiro.getIdVencedor() + " venceu.");
        	tabuleiro.resetarPosicoes();
        } else
            JOptionPane.showMessageDialog(interfaceTabuleiro, "Ocorreu um erro no tratamento de lance.");
         
    }
    
    public void receberLanceRede(Lance lance) {
        
    	Cavalo jogador = tabuleiro.getJogador(lance.getIdJogador());
    	tabuleiro.getPosicao(jogador.getLinha(), jogador.getColuna()).desativar();
    	interfaceTabuleiro.desativaPosicao(jogador.getLinha(), jogador.getColuna());
    	
    	tabuleiro.assumirLance(lance);        
            	
        interfaceTabuleiro.setPosicaoJogador(jogador.getCor(), lance.getLinha(), lance.getColuna());
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
