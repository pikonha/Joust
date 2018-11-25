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
    
    public boolean verificarPerdeu() {        
        return false;
    }
    
    public void passarTurno() {        
    }   
    
    public void ativarPosicoesIniciais() {        
    }
    
    public boolean verificarDisponivel(Posicao posicao) {
        return posicao.informarOcupante() == 0;        
    }

    
    public boolean validarMovimento(Posicao posicao) {
        return true;
    }
    
    public boolean conectar() {
        return true;
    }
    
    public void desconectar() {
        
    }
    
    public void novoJogo(String idJogador1, String idJogador2) {
        
    }    
    
    public void fechar() {
        
    }
}
