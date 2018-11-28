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
    private Posicao posicoes[][];
    private boolean conectado;
    private boolean partidaEmAndamento;

    public Tabuleiro() {
        for (int i = 0; i < posicoes.length; ++i) {
            for (int j = 0; j < posicoes.length; ++j) {
                posicoes[i][j] = new Posicao(i, j);
            }
        }
    }      
    
    public void setJogador1(String idJogador) {
        this.jogador1 = new Cavalo(idJogador, 1);
    }

    public void setJogador2(String idJogador) {
        this.jogador2 = new Cavalo(idJogador, 2);
    }
    
    public boolean trataLance(Lance lance) {
        
        return true;
    }
    
    public void assumirLance(Lance lance) {
        
        
        
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
    
//    public int click(int linha, int coluna) {
//        if (!partidaEmAndamento)
//            return 4;        
//        
//        Posicao pos = getPosicao(linha, coluna);
//        
//        Cavalo jogador = getJogadorDaVez();
//        
//        if (!jogador.informarDaVez()) 
//            return 1;
//        
//        if (validarMovimento(pos, jogador)){
//            if (verificarVencedor())
//                return 3;            
//            return 0;
//        }
//         
//                
//        return 0;
//    }
    
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
        posicoes[7][3].setOcupacao(jogador1.informarCor());   
    
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
    

    public void fechar() {
        
    }
}
