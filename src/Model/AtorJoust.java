/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.InterfaceTabuleiro;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rede.AtorRede;

/**
 *
 * @author Lucas
 */
public class AtorJoust {
    
    private Tabuleiro tabuleiro;
    private String nome = "";
    private AtorRede atorRede;
    private boolean ehMinhaVez = false;
    
    private InterfaceTabuleiro interfaceTabuleiro;
        
    public AtorJoust() {
        this.atorRede = new AtorRede(this);
        interfaceTabuleiro = new InterfaceTabuleiro(tabuleiro);
        
//        for (int i = 0; i < 8; ++i) {
//            for (int j = 0; j < 8; ++j) {
//                interfaceTabuleiro.getButton(i, j).addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    clickConectar(evt);
//                }
//                });
//            }
//        }
    }
    
    
    public void go() {
        nome = JOptionPane.showInputDialog(null, "Nome de parcipante: ");
        atorRede.conectar(nome, "localhost");
    }
  
    public Component getFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void iniciarPartida() {
        atorRede.iniciarPartidaRede();
    }
    
    public void iniciarPartidaRede(boolean comecoJogando) {
        String nomeAdversario = atorRede.obterNomeAdversario();
        tabuleiro = new Tabuleiro();
        
        if (comecoJogando) {
            tabuleiro.setJogador1(new Cavalo(nome, 1));
            tabuleiro.setJogador2(new Cavalo(nomeAdversario, 2));
        }
        else {
            tabuleiro.setJogador1(new Cavalo(nomeAdversario, 2));
            tabuleiro.setJogador2(new Cavalo(nome, 1));
        }
            
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void receberImagemRede(ImagemTabuleiro imagemTabuleiro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
