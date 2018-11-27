/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author Lucas
 */
public class Lance implements Jogada {
    
    int linha;
    int coluna;
    String idJogador;

    public Lance(int linha, int coluna, String idJogador) {
        this.linha = linha;
        this.coluna = coluna;
        this.idJogador = idJogador;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String getIdJogador() {
        return idJogador;
    }

    

    
  
}
