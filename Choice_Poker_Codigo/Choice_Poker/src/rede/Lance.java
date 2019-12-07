/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import choice_poker.Jogador;

/**
 *
 * @author edgut
 */
public class Lance implements Jogada {
    
    Jogador jogador;
    private int fichas, qualCarta;
    boolean vitoriaMelhor, check;
    
    public Lance(){}

    public int getFichas() {
        return fichas;
    }
    
    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public int getQualCarta() {
        return qualCarta;
    }

    public void setQualCarta(int qualCarta) {
        this.qualCarta = qualCarta;
    }

    public boolean isVitoriaMelhor() {
        return vitoriaMelhor;
    }

    public void setVitoriaMelhor(boolean vitoriaMelhor) {
        this.vitoriaMelhor = vitoriaMelhor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

}

