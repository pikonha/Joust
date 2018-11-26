/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.InterfaceTabuleiro;

/**
 *
 * @author Lucas
 */
public class Tabuleiro {
    
    private Cavalo jogador1;
    private Cavalo jogador2;
    private Posicao posicoes[][] = new Posicao[8][8];
    private boolean conectado;
    private boolean partidaEmAndamento;
    
    
    public Tabuleiro() {
        
    }
    
    public Tabuleiro(Cavalo jogador1, Cavalo jogador2, boolean conectado, boolean partidaEmAndamento) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.conectado = conectado;
        this.partidaEmAndamento = partidaEmAndamento;
    }
 
    public Cavalo informarJogador(int idJogador) {
        return idJogador == 1 ? jogador1 : jogador2;
    }

    public boolean informarConectado() {
        return conectado;
    }
    
    public Cavalo getJogadorDaVez() {
        return jogador1.informarDaVez() ? jogador1 : jogador2;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
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
    
    public int click(int linha, int coluna) {
        if (!partidaEmAndamento)
            return 4;        
        
        Posicao pos = getPosicao(linha, coluna);
        
        Cavalo jogador = getJogadorDaVez();
        
        if (!jogador.informarDaVez()) 
            return 1;
        
        if (validarMovimento(pos, jogador)){
            if (verificarVencedor())
                return 3;            
            return 0;
        }
         
                
        return 0;
    }
    
    public void resetarPosicoes() {
        for (int i = 0; i < posicoes.length; ++i) {
            for (int j = 0; j < posicoes.length; ++j) {
                posicoes[i][j].setOcupacao(0);
            }
        }
    }
    
    public void setOcupacao(Posicao posicao, int ocupacao) {
        posicao.setOcupacao(ocupacao);
    }
    
    public void criarJogador(String idJogador) {
        if (jogador1 != null) 
            jogador1 = new Cavalo(idJogador, 1);
        else if (jogador2 != null)
            jogador2 = new Cavalo(idJogador, 2);            
    }     
    
    public void terminarPartida() {
        partidaEmAndamento = false;
    }
    
    public boolean verificarVencedor() {                
        return false;
    }
        
    public void passarTurno() {        
    }   
    
    public void ativarPosicoesIniciais() {  
        resetarPosicoes();
        posicoes[0][4].setOcupacao(jogador2.informarCor());
        posicoes[7][4].setOcupacao(jogador1.informarCor());   
        
    }
    
    public boolean verificarDisponivel(Posicao posicao) {
        return posicao.informarOcupante() == 0;        
    }
    
    public boolean validarMovimento(Posicao posicao, Cavalo jogador) {
        return true;
    }
    
    public boolean conectar() {
        return true;
    }
    
    public void desconectar() {
        
    }
    
    public void novoJogo(String idJogador1, String idJogador2) {
        if (!this.partidaEmAndamento && conectado) {
            jogador1 = new Cavalo(idJogador1, 0);
            jogador2 = new Cavalo(idJogador2, 1);        
            setEmAndamento(true);
            
            ativarPosicoesIniciais();
        }
    }    
    
    public void fechar() {
        
    }
}
