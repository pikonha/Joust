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
        
//       for (int i = 0; i < 8; ++i) {
//           for (int j = 0; j < 8; ++j) {
//               interfaceTabuleiro.getButton(i, j).addActionListener(new ClickButtonListener());
//           }
//       }
    }

    public Component getInterface() {
        return interfaceTabuleiro;
    }   
    
    public void novoLance(Lance lance) {
        if (atorRede.ehMinhaVez()){
            int resultado = tabuleiro.trataLance(lance);
            
            if (resultado == 0) {
                atorRede.enviarJogada(lance);
            } else if (resultado == 1) {
                interfaceTabuleiro.setMessage("Posição inválida.");
            } if (resultado == 2) {
                interfaceTabuleiro.informarVencedor(tabuleiro.getIdVencedor());
            } else
                JOptionPane.showMessageDialog(interfaceTabuleiro, "Ocorreu um erro no tratamento de lance.");
        }  
    }
    
    public void receberLanceRede(Lance lance) {
        tabuleiro.assumirLance(lance);
    }

    public void go() {
        interfaceTabuleiro = new InterfaceTabuleiro(this);
        
        idJogador = JOptionPane.showInputDialog(interfaceTabuleiro, "Escolha o nome do participante 1:");
        
        interfaceTabuleiro.setVisible(true);
        interfaceTabuleiro.setStatus("Conectado");
        interfaceTabuleiro.setNomeJogador1(idJogador);
        interfaceTabuleiro.setMessage("Conecte-se ao servidor para\n começar novo jogo.");
        
    }
    
    /* chamado sempre que uma partida inicia */
    public void iniciarPartidaRede(boolean comecoJogando) {
        
        tabuleiro = new Tabuleiro();
        
        if (comecoJogando) {
            tabuleiro.setJogador1(idJogador);
            tabuleiro.setJogador2(atorRede.obterNomeAdversario());
            JOptionPane.showMessageDialog(interfaceTabuleiro, "Você começa jogando.");
        } else {
            tabuleiro.setJogador1(atorRede.obterNomeAdversario());
            tabuleiro.setJogador2(idJogador);
            JOptionPane.showMessageDialog(interfaceTabuleiro, "O adversário começa jogando.");
        }
    }
    
    /* request para iniciar uma nova partida */
    public void iniciarPartida() {
        atorRede.iniciarPartidaRede();
    }
    
    public static void main(String[] args) {
        new AtorJoust().go();
    }  

    public void desconectar() {
        atorRede.desconectar();
    }

    public void encerrar() {
        atorRede.desconectar();
        System.exit(0);
    }

    public void conectar() {
        atorRede.conectar(idJogador);
    }
    

}
