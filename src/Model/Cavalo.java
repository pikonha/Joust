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
    private int cor;
    private int linha;
    private int coluna;
    private boolean vencedor;
    
    public Cavalo(String nome, int cor){
        this.idJogador = nome;
        this.cor = cor;
        this.linha = this.coluna = -1;
        this.vencedor = false;
    }      

    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    } 
    
    public String informarId() {
        return this.idJogador;
    }       
    
    public int informarCor() {
        return cor;
    }    

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    
}
