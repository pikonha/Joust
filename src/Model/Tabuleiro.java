/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import rede.Lance;


/**
 *
 * @author Lucas
 */
public class Tabuleiro {   
    
    private Cavalo jogador1;
    private Cavalo jogador2;
    private Posicao posicoes[][] = new Posicao[8][8];
    private boolean partidaEmAndamento;

    public Tabuleiro() {
        for (int i = 0; i < posicoes.length; ++i) {
            for (int j = 0; j < posicoes.length; ++j) {
                posicoes[i][j] = new Posicao(i, j);
            }
        }
    }      
    
    public void setJogador1(Cavalo cavalo) {
        this.jogador1 = cavalo;
    }

    public void setJogador2(Cavalo cavalo) {
        this.jogador2 = cavalo;
    }
    
    public int trataLance(Lance lance) {
        Cavalo jogador = getJogador(lance.getIdJogador());
        
        if (validaPosicaoDestino(jogador.getLinha(), jogador.getColuna(),
                                lance.getLinha(), lance.getColuna())) {
            return verificarVencedor() ? 2 : 0;
        }
        
        return 1;
    }
    
    public void assumirLance(Lance lance) {
        Cavalo jogador = getJogador(lance.getIdJogador());
        
        getPosicao(jogador.getLinha(), jogador.getColuna()).desativar();
        getPosicao(lance.getLinha(), lance.getColuna()).setOcupacao(jogador.informarCor());
    }
    
    public Cavalo getJogador(String idJogador) {
        return jogador1.informarId() == idJogador ? jogador1 : jogador2;
    }
    
    public boolean validaPosicaoDestino(int linhaAtual, int colunaAtual, int linhaDest, int colunaDest) {
        int linha = linhaAtual - linhaDest;
        int coluna = colunaAtual - colunaDest;
        
        if (linha >= -2 && linha != 0 && linha <= 2
                && coluna >= -2 && coluna != 0 && coluna <= 2) {
        	
        	return true;
        }            
        else {
            linha = linhaAtual + linhaDest;
            coluna = colunaAtual + colunaDest; 
            
            return linha >= -2 && linha != 0 && linha <= 2
                && coluna >= -2 && coluna != 0 && coluna <= 2;
        }
    }

    public boolean infomarEmAndamento() {
        return partidaEmAndamento;
    }

    public void setEmAndamento(boolean partidaEmAndamento) {
        this.partidaEmAndamento = partidaEmAndamento;
    }
    
    public Posicao getPosicao(int linha, int coluna){ 
        return posicoes[linha][coluna];
    }
    
    public void resetarPosicoes() {
        for (int i = 0; i < posicoes.length; ++i) {
            for (int j = 0; j < posicoes.length; ++j) {
                posicoes[i][j].setOcupacao(0);
            }
        }
    }
    
      
    public void terminarPartida() {
        partidaEmAndamento = false;
    }
    
    public boolean verificarVencedor() {     
        
        
        return false;
    }

    public void ativarPosicoesIniciais() {  
        resetarPosicoes();
        posicoes[0][4].setOcupacao(jogador2.informarCor());
        posicoes[7][3].setOcupacao(jogador1.informarCor());       
   }

    public String getIdVencedor() {
        return jogador1.isVencedor() ? jogador1.informarId() : jogador2.informarId();
    }
 
}
