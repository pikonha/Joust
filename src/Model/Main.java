/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.InterfaceTabuleiro;
import javax.swing.UIManager;

/**
 *
 * @author 16204446
 */
public class Main {
    
    public static void main(String[] args) {  
        try  {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        InterfaceTabuleiro tabuleiro = new InterfaceTabuleiro();
        tabuleiro.setVisible(true);
    }
}
