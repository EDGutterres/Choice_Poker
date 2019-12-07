/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choice_poker;

import javax.swing.JOptionPane;

/**
 *
 * @author edgut
 */
public class Baralho {
    
    Carta cartas[];
    
    public Baralho() {
    
        cartas = new Carta[52];
        
        for (int i=0; i<13; i++) 
            cartas[i] = new Carta("Bastos", i+1);
        
        for (int i=13; i<26; i++) 
            cartas[i] = new Carta("Copas", (i+1) - 13);
        
        for (int i=26; i<39; i++) 
            cartas[i] = new Carta("Espadas", (i+1) - 26);
        
        for (int i=39; i<52; i++) 
            cartas[i] = new Carta("Ouros", (i+1) - 39);
        
    }

    public Carta[] getCartas() {
        return cartas;
    }

    public void setCartas(Carta[] carta) {
        this.cartas = carta;
    }
    
    public boolean empty() {
        for (int i=0; i<52; i++) {
            if (cartas[i] != null) {
                return false;
            }
        }
        return true;
    }
    
    public Carta getRandomCarta() {
        
        Carta carta;
        int i;
        
        do {
            if (empty()) {
                return new Carta("Vazio", 0);
            }
            i = new java.util.Random().nextInt(52);
            carta = cartas[i];
        } while (carta == null);
        
        return carta;
        
    }
    
    public boolean descartarCarta(Carta carta) {
        if (carta == null) {
            JOptionPane.showMessageDialog(null, "Carta invalida");
            return false;
        }
        
        String naipe = carta.getNaipe();
        int numero = carta.getNumero();
        for (int i=0; i<52; i++) {
            if (cartas[i] != null) {
                if ((cartas[i].getNumero() == numero) && cartas[i].getNaipe().equals(naipe)) {
                    cartas[i] = null;
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Nao foi encontrada a carta");
        return false;
    }
    
    public boolean contem(Carta carta) {
        for (int i=0; i<52; i++) {
            if (cartas[i].equals(carta)) {
                return true;
            }
        }
        return false;
    }
    
    public Carta getCarta(int index) {
        return cartas[index];
    }
    
}
