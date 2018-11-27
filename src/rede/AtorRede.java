/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import Model.AtorJoust;
import Model.ImagemTabuleiro;
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
            JOptionPane.showMessageDialog(atorJoust.getFrame(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoPossivelConectarException ex) {
            JOptionPane.showMessageDialog(atorJoust.getFrame(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArquivoMultiplayerException ex) {
            JOptionPane.showMessageDialog(atorJoust.getFrame(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void iniciarPartidaRede() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getFrame(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarJogada(ImagemTabuleiro imagemTabuleiro) {
        try {
            Lance lance = new Lance(imagemTabuleiro);
            proxy.enviaJogada(lance);
        } catch (NaoJogandoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getFrame(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(atorJoust.getFrame(), ex.getMessage());
            Logger.getLogger(AtorRede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void iniciarNovaPartida(Integer posicao) {
        ehMinhaVez = posicao == 1;
        
        atorJoust.iniciarPartidaRede(ehMinhaVez);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberJogada(Jogada jogada) {
        Lance lance = (Lance) jogada;
        atorJoust.receberImagemRede(lance.getImagemTabuleiro());
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarConexaoPerdida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String obterNomeAdversario() {
        String nome = "";
        if (ehMinhaVez) {
            nome = proxy.obterNomeAdversario(2);
        } else {
            nome = proxy.obterNomeAdversario(1);
        }
        return nome;
    }
    
    
    
}
