/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import javax.swing.JOptionPane;

/**
 *
 * @author edgut
 */
public class AtorRede implements OuvidorProxy {

    private AtorJogador atorJogador;
    private Proxy proxy;
    
    public AtorRede (AtorJogador atorJogador) {
        super();
        setAtorJogador(atorJogador);
        proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }
    
    public boolean conectar(String nome, String servidor) {
        try {
            proxy.conectar(servidor, nome);
            return true;
        } catch (JahConectadoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        } catch (NaoPossivelConectarException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        } catch (ArquivoMultiplayerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean desconectar() {
        try {
            proxy.desconectar();
            return true;
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        }

    }
    
    public void enviarJogada(Lance lance) {
        try {
            proxy.enviaJogada(lance);
        } catch (NaoJogandoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();       
        }
    }
    
    public void iniciarPartida() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public String informarNomeAdversario(String idUsuario) {
        String aux1 = proxy.obterNomeAdversario(1);
        String aux2 = proxy.obterNomeAdversario(2);
        if (aux1.equals(idUsuario)){
                return aux2;
        } else {
                return aux1;
        }	
    }
    
    @Override
    public void finalizarPartidaComErro(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        atorJogador.iniciarNovaPartida(posicao);
    }

    @Override
    public void receberJogada(Jogada jogada) {
        Lance lance = (Lance) jogada;
        atorJogador.receberJogada(lance);

    }

    @Override
    public void tratarConexaoPerdida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAtorJogador(AtorJogador atorJogador) {
        this.atorJogador = atorJogador;
    }
}
