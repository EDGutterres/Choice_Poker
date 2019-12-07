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
public class Carta implements Jogada {
    
    int numero;
    String figura, naipe, icone;
    
    public Carta() {
        
    }
    
    public Carta(String naipe, int numero) {
        this.naipe = naipe;
        this.numero = numero;
        
        switch (numero) {
            case 1:
                figura = "As";
                break;
            case 11:
                figura = "Valete";
                break;
            case 12:
                figura = "Dama";
                break;
            case 13:
                figura = "Rei";
                break;
  
        }
        
        this.icone = "/resources/" + numero + naipe.charAt(0) + ".png";
        
    }
    
    @Override
    public String toString() {
        return ((figura == null) ? numero : figura) + " de " + naipe + ".";
    }

    public String getIcone() {
        return icone;
    }

    public String getNaipe() {
        return naipe;
    }

    public int getNumero() {
        return numero;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    
}
