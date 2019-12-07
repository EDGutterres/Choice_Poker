/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choice_poker;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 *
 * @author edgut
 */
public class Jogador implements Jogada {
    
    private String nome;
    private int fichas = 100, apostadas = 0;
    private Mao mao;
    private boolean turno, check, maoVazia, vencedor;
    private Carta descartada1, descartada2;
    
    public Jogador() {
 
    }
    
    public void iniciar() {
        vencedor = false;
        maoVazia = true;
        check = false;
        descartada1 = new Carta();
        descartada2 = new Carta();
        descartada1.setIcone("/resources/BACK.png");
        descartada2.setIcone("/resources/BACK.png");
        mao = new Mao();
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Mao getMao() {
        return mao;
    }

    public void setMao(Mao mao) {
        this.mao = mao;
    }

    public int getFichas() {
        return fichas;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    } 

    public Carta getDescartada1() {
        return descartada1;
    }

    public Carta getDescartada2() {
        return descartada2;
    }

    public boolean setDescartada1(Carta descartada1) {
        this.descartada1 = descartada1;
        return true;
    }

    public boolean setDescartada2(Carta descartada2) {
        this.descartada2 = descartada2;
        return true;
    }
    
    public Carta getCarta(int index) {
        return mao.getCarta(index);
    }
    
    public void setCarta(Carta carta, int index) {
           mao.setCarta(carta, index);
    }

    public void setMaoVazia(boolean maoVazia) {
        this.maoVazia = maoVazia;
    }

    public boolean isMaoVazia() {
        return maoVazia;
    }

    public int getApostadas() {
        return apostadas;
    }

    public void setApostadas(int apostadas) {
        this.apostadas = apostadas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public void ordenar() {
        mao.ordenar();
    }
    
    public int avaliar() {
        return mao.avaliar();
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }
    
    public boolean isCarta(int index) {
        return mao.isCarta(index);
    }
    
}
