/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.InterfaceTabuleiro;

/**
 *
 * @author 16204446
 */
public class Main {
    
    public static void main(String[] args) {  
        
       Tabuleiro tabuleiro = new Tabuleiro();
        
       InterfaceTabuleiro Itabuleiro = new InterfaceTabuleiro(tabuleiro);
       Itabuleiro.setVisible(true);
        
        
        
        
    }
}
