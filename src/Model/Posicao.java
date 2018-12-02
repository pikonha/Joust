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
public class Posicao {

    private int ocupante;
    
    public Posicao() {
        this.ocupante = 0;
    }
    
    public boolean verificarOcupada() {
        return ocupante != 0;
    }
    
    public void setOcupacao(int simbolo) {
        this.ocupante = simbolo;
    }

    public void esvaziar() {
        this.ocupante = 0;
    }
    
    public void desativar() {
        this.ocupante = 3;
    }

    public int informarOcupante() {
        return this.ocupante;
    }
}
