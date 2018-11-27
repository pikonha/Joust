/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import Model.AtorJoust;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class AtorRede implements OuvidorProxy {

    private AtorJoust atorJoust;
    
    private Proxy proxy;
    
    private boolean ehMinhaVez = false;
    
    public AtorRede(AtorJoust atorJoust) {
        super();
        this.atorJoust = atorJoust;
        this.proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }
    
    public void conectar(String nome, String servidor) {
        try {
            proxy.conectar(servidor, nome);
        } catch (JahConectadoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoPossivelConectarException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArquivoMultiplayerException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void iniciarPartidaRede() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarJogada(Lance lance) {
        try {
            proxy.enviaJogada(lance);
            ehMinhaVez = false;
        } catch (NaoJogandoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Recebe quando vai iniciar uma partida */
    @Override
    public void iniciarNovaPartida(Integer posicao) {
        ehMinhaVez = posicao == 1;
        
        atorJoust.iniciarPartidaRede(ehMinhaVez);        
    }
    
    @Override
    public void receberJogada(Jogada jogada) {
        Lance lance = (Lance)jogada;      
        atorJoust.receberLanceRede(lance);
        ehMinhaVez = true;  
    }
    
    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String obterNomeAdversario() {
        return proxy.obterNomeAdversario(ehMinhaVez ? 2 : 1);        
    }

    public boolean ehMinhaVez() {
        return ehMinhaVez;
    }
    

    
    
    @Override
    public void finalizarPartidaComErro(String message) {
        JOptionPane.showMessageDialog(atorJoust.getInterface(), message);
    }

    @Override
    public void receberMensagem(String msg) {
        
    }  

    @Override
    public void tratarConexaoPerdida() {
        JOptionPane.showMessageDialog(atorJoust.getInterface(), "A conexão com o servidor foi perdida.");
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        JOptionPane.showMessageDialog(atorJoust.getInterface(), "Não foi possível iniciar o jogo.");
    }    
}
