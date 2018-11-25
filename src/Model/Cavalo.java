/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lucas
 */
public class Cavalo {
    
    private String idJogador;
    private boolean vencedor;
    private boolean daVez;   
    private int cor;
    
    public Cavalo(String nome, int cor){
        this.idJogador = nome;
        this.vencedor = false;
        this.daVez = false;
    }      
    
    public void assumirVencedor() {
        this.vencedor=true;
    }
    
    public boolean informarVencedor() {
        return this.vencedor;
    }

    public String informarId() {
        return this.idJogador;
    }       
    
    public boolean informarDaVez() {
        return this.daVez;
    }
    
    public void setDaVez(boolean daVez) {
        this.daVez = daVez;
    }
    
}
