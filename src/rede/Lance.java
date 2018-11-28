/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import Model.Cavalo;
import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author Lucas
 */
public class Lance implements Jogada {
    
	public int blinha;
	public int bcoluna;	
    private int linha;
    private int coluna;
    private String idJogador;
    private String vencedor = "";

    public Lance(int linha, int coluna, String idJogador) {
        this.linha = linha;
        this.coluna = coluna;
        this.idJogador = idJogador;       
    }
    
    public Lance(int linha, int coluna, String idJogador, String vencedor) {
        this.linha = linha;
        this.coluna = coluna;
        this.idJogador = idJogador;       
        this.vencedor = vencedor;
    }
    
    public Lance(int Alinha, int Acoluna, int Blinha, int Bcoluna, String idJogador) {
        this.linha = Alinha;
        this.coluna = Acoluna;
        this.idJogador = idJogador;          
        this.bcoluna = Bcoluna;
        this.blinha = Blinha;
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

    public String getVencedor() {
        return vencedor;
    }
}
