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
class Posicao {
    
    protected int linha;
    protected int coluna;
    protected int ocupante;
    
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
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

    public int informarOcupacao() {
        return this.ocupante;
    }

    public int informarLinha() {
        return this.linha;
    }

    public int informarColuna() {
        return this.coluna;
    }
}
