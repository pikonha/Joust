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
    
    private Cavalo jogador;
    private Tabuleiro tabuleiro;
    private InterfaceTabuleiro interfaceTabuleiro;

    private AtorRede atorRede;
        
    public AtorJoust() {
       atorRede = new AtorRede(this);        
    }

    public Component getInterface() {
        return interfaceTabuleiro;
    }   
    
    public void novoLance(Lance lance) {
        if (atorRede.ehMinhaVez()){
            int resultado = tabuleiro.trataLance(lance);
            
            if (resultado == 0) {
                atorRede.enviarJogada(new Lance(lance.getLinha(), lance.getColuna(), jogador.getLinha(), jogador.getColuna(), lance.getIdJogador()));
                
                interfaceTabuleiro.desativaPosicao(jogador.getLinha(), jogador.getColuna());
                
                System.out.println("Desativando posicao" + jogador.getLinha() + " : " + jogador.getColuna());
                
                jogador.setLinha(lance.getLinha());
                jogador.setColuna(lance.getColuna());
                
                interfaceTabuleiro.setPosicaoJogador(jogador.informarCor(), jogador.getLinha(), jogador.getColuna());                
                tabuleiro.getPosicao(jogador.informarCor(), jogador.getLinha()).setOcupacao(3);
                
                atorRede.setEhMinhaVez(false);
                
            } else if (resultado == 1) {
                interfaceTabuleiro.setMessage("Posição inválida.");
            } else if (resultado == 2) {
                interfaceTabuleiro.informarVencedor(tabuleiro.getIdVencedor());
            } else
                JOptionPane.showMessageDialog(interfaceTabuleiro, "Ocorreu um erro no tratamento de lance.");
        }  
    }
    
    public void receberLanceRede(Lance lance) {
        tabuleiro.assumirLance(lance);        
        
        interfaceTabuleiro.desativaPosicao(lance.blinha, lance.bcoluna);
        interfaceTabuleiro.setPosicaoJogador(jogador.informarCor() == 1 ? 2 : 1, lance.getLinha(), lance.getColuna());
    }

    public void go() {
        interfaceTabuleiro = new InterfaceTabuleiro(this);
        
        String nome = JOptionPane.showInputDialog(interfaceTabuleiro, "Escolha o nome do participante:");
        
        jogador = new Cavalo(nome); 
        
        interfaceTabuleiro.setVisible(true);
        interfaceTabuleiro.setStatus("Aguardando conexão");
        interfaceTabuleiro.setMessage("Conecte-se ao servidor para\n começar novo jogo.");   
    }
    
    /* chamado sempre que uma partida inicia */
    public void iniciarPartidaRede(boolean comecoJogando) {
    	tabuleiro = new Tabuleiro();
    	
        String nomeAdversario = atorRede.obterNomeAdversario();
        
        tabuleiro.setEmAndamento(true);
        
        if (comecoJogando) {
        	jogador.setCor(1);
            jogador.setLinha(0);
            jogador.setColuna(4);
        	
            tabuleiro.setJogador1(jogador);
            tabuleiro.setJogador2(new Cavalo(nomeAdversario, 2, 7, 3));
            
            JOptionPane.showMessageDialog(interfaceTabuleiro, "Você começa jogando. Sua cor é a: branca." );
            
            interfaceTabuleiro.setNomeJogador1(jogador.informarId());
            interfaceTabuleiro.setNomeJogador2(nomeAdversario);
            
        	interfaceTabuleiro.setPosicaoJogador(jogador.informarCor() == 1 ? 2 : 1, 7, 3);  
   
        } else {
           	jogador.setCor(2);
            jogador.setLinha(7);
            jogador.setColuna(3);
            
        	interfaceTabuleiro.setPosicaoJogador(jogador.informarCor() == 1 ? 2 : 1, 0, 4);  
            
            tabuleiro.setJogador1(new Cavalo(atorRede.obterNomeAdversario(), 1, 0, 4));
            tabuleiro.setJogador2(jogador);
            
            JOptionPane.showMessageDialog(interfaceTabuleiro, "O adversário começa jogando. Sua cor é a: preta.");
            
            interfaceTabuleiro.setNomeJogador1(nomeAdversario);
            interfaceTabuleiro.setNomeJogador2(jogador.informarId());
        }
        
    	interfaceTabuleiro.setPosicaoJogador(jogador.informarCor(), jogador.getLinha(), jogador.getColuna());    	

    }
    
    /* request para iniciar uma nova partida */
    public void iniciarPartida() {    	
    	this.atorRede.iniciarPartidaRede();
    }
    
    public static void main(String[] args) {
        new AtorJoust().go();
    }  

    public void desconectar() {
        atorRede.desconectar();
		tabuleiro.setEmAndamento(false);
    }

    public void encerrar() {
        atorRede.desconectar();
        System.exit(0);
    }

    public void conectar() {
        atorRede.conectar(jogador.informarId());
        interfaceTabuleiro.setStatus("Conectado.");
        interfaceTabuleiro.setMessage("Conectado ao servidor.\n Inicie um novo jogo.");
    }
    

}
