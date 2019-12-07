/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import choice_poker.InterfaceChoicePoker;
import choice_poker.Jogador;
import choice_poker.Mesa;
import javax.swing.JOptionPane;

/**
 *
 * @author edgut
 */
public class AtorJogador {
    
    private Mesa mesa;
    private AtorRede atorRede;
    private String idUsuario;
    private InterfaceChoicePoker interfaceChoicePoker;
    
    public AtorJogador(InterfaceChoicePoker interfaceChoicePoker) {
        super();
        atorRede = new AtorRede(this);
        setInterfaceChoicePoker(interfaceChoicePoker);
        mesa = new Mesa();
        mesa.iniciar();
    }
    
    public  InterfaceChoicePoker informarInterface() {
        return interfaceChoicePoker;
    }
    
    public void iniciarNovaPartida(Integer posicao) {
        mesa.embaralhar();
        mesa.criarJogador(idUsuario);
        String idJogador = atorRede.informarNomeAdversario(idUsuario);
        mesa.criarJogador(idJogador);
        mesa.iniciarPartida(posicao);
    }
    
    public int conectar() {
        boolean conectado = mesa.isConectado();
        if (!conectado){
            String servidor = this.obterDadosConexao();
            //idUsuario = "netbeans";
            boolean exito = atorRede.conectar(idUsuario, servidor);
            if (exito){
                mesa.setConectado(true);
                return 0;
            } else {
                return 2;
            }			
        }else{
            return 1;
        }		
    }
    
     public int desconectar() {
        boolean conectado = mesa.isConectado();
        if (conectado){
            boolean exito = atorRede.desconectar();
            if (exito){
                mesa.setConectado(false);
                return 3;
            } else {
                return 5;
            }			
        } else {
            return 4;
        }			
    }
     
     public int iniciarPartida() {
         boolean partidaEmAndamento = mesa.isPartidaEmAndamento();
         boolean interromper = false;
         boolean conectado = false;
         
         if (partidaEmAndamento) {
            interromper = true;
         } else {
             conectado = mesa.isConectado();
         }
         if (interromper || ((!partidaEmAndamento) && conectado)){
             atorRede.iniciarPartida();
             return 6;
         }
         if (!conectado) {
             return 7;
         }
         return 13;
    }
     
    public int apostar(int apostadas) {
        int resultado = 0;
        resultado = mesa.apostar(apostadas);
         if ((resultado == 10) || (resultado == 9)){
            this.enviarJogada(mesa.getJogador1(), apostadas);
         }
        return resultado;
    }
    
    public void enviarJogada(Jogador jogador, int apostadas) {
        Lance lance = mesa.informarJogada(jogador, apostadas);
        atorRede.enviarJogada(lance);
    }
    
    public void enviarJogada(int qualCarta, Jogador jogador) {
        Lance lance = mesa.informarJogada(qualCarta, jogador);
        atorRede.enviarJogada(lance);
    }
    
     public void enviarJogada(Jogador jogador) {
        Lance lance = mesa.informarJogada(jogador);
        atorRede.enviarJogada(lance);
    }
      
    public void receberJogada(Lance lance) {
        mesa.receberJogada(lance);
        interfaceChoicePoker.atualizar(mesa);
    }
    
    public String obterDadosConexao() {
        idUsuario = JOptionPane.showInputDialog("Informe o id do usuario.");
        String servidor = JOptionPane.showInputDialog("Informe o id do servidor.");
        return servidor;
    }

    public void setInterfaceChoicePoker(InterfaceChoicePoker interfaceChoicePoker) {
        this.interfaceChoicePoker = interfaceChoicePoker;
    }

    public int trocarCartas(int qualCarta) {
        int resultado = 0;
        resultado = mesa.trocarCartas(qualCarta);
        if ((resultado == 10) || (resultado == 9)){
            this.enviarJogada(qualCarta, mesa.getJogador1());	
	}
	return resultado;
    }
    
    public boolean conectado() {
        return mesa.isConectado();
    }
    
    public boolean emAndamento() {
        return mesa.isPartidaEmAndamento();
    }

    public int receberCartas() {
        int resultado = 0;
        resultado = mesa.receberCartas();
        if ((resultado == 10) || (resultado == 9)){
            this.enviarJogada(mesa.getJogador1());		
        }		
	return resultado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public int check() {
        int resultado = 0;
        resultado = mesa.check();
        if ((resultado == 10) || (resultado == 9)){
            this.enviarJogada(mesa.getJogador1(), true);
         }
        return resultado;
    }

    private void enviarJogada(Jogador jogador, boolean b) {
        Lance lance = mesa.informarJogada(jogador, b);
        atorRede.enviarJogada(lance);
    }
    
}
