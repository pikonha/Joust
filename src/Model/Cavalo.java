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
    
    protected String nome;
    protected Cor cor;
    protected boolean vencedor;
    protected boolean daVez;
    
    
    public Cavalo(String nome, Cor cor){
        this.nome = nome;
        this.cor = cor;
        this.vencedor = false;
        this.daVez = false;
    }
    
    public void assumirVencedor() {
        this.vencedor=true;
    }
    
    public boolean getVencedor() {
        return this.vencedor;
    }

    public String informarNome() {
        return this.nome;
    }    
    
    public Cor getCor() {
        return this.cor;
    }
    
    public boolean informarDaVez() {
        return this.daVez;
    }
    
    public void setDaVez(boolean daVez) {
        this.daVez = daVez;
    }
    
}
