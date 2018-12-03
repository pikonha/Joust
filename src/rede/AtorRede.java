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
    
    public AtorRede(AtorJoust atorJoust) {
        super();
        this.atorJoust = atorJoust;
        this.proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }
    
    public void conectar(String nome) {
        try {
            proxy.conectar("localhost", nome);
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
        } catch (NaoJogandoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @Override
    public void iniciarNovaPartida(Integer posicao) {        
        atorJoust.iniciarNovaPartida(posicao == 1);        
    }
    
    @Override
    public void receberJogada(Jogada jogada) {
        Lance lance = (Lance)jogada;      
        atorJoust.receberLanceRede(lance);
    }
    
    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getInterface(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String obterNomeAdversario(String idUsuario) {
		String aux1 = proxy.obterNomeAdversario(1);
		return aux1.equals(idUsuario) ? proxy.obterNomeAdversario(2) : aux1;
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
