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
public class ImagemTabuleiro {
    Posicao[][] posicoes;
    String status;
    String idJogador1;
    String idJogador2;
    String message;

    public ImagemTabuleiro(Posicao[][] posicao, String status, String idJogador1, String idJogador2, String message) {
        this.posicoes = posicao;
        this.status = status;
        this.idJogador1 = idJogador1;
        this.idJogador2 = idJogador2;
        this.message = message;
    }

    
    public Posicao getPosicao(int linha, int coluna) {
        return posicoes[linha][coluna];
    }
    
    public String getStatus() {
        return status;
    }

    public String getIdJogador1() {
        return idJogador1;
    }

    public String getIdJogador2() {
        return idJogador2;
    }

    public String getMessage() {
        return message;
    }

    
    
    
    
   
    
    
}
