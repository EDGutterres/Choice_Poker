/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choice_poker;

import br.ufsc.inf.leobr.cliente.Jogada;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author edgut
 */
public class Mao implements Jogada {

    Carta cartas[];

    public Mao() {
        
        cartas = new Carta[5];

    }

    public int avaliar() {
        //10 - Royal Flush
        //9 - Straight Flush
        //8 - Four of a Kind
        //7 - Full House
        //6 - Flush
        //5 - Straight
        //4 - Three of a Kind
        //3 - Two Pair
        //2 - One Pair
        //1 - High Card
        
        if (isRoyalFlush())
            return 10;
        else if (isStraightFlush())
            return 9;
        else if (isFour())
            return 8;
        else if (isFullHouse())
            return 7;
        else if (isFlush())
            return 6;
        else if (isStraight())
            return 5;
        else if (isTrinca())
            return 4;
        else if (isDoisPares())
            return 3;
        else if (isPar())
            return 2;

        return 1;
    }

    public boolean isFlush() {
        String naipe = cartas[0].getNaipe();

        return (cartas[1].getNaipe().equals(naipe))
                && (cartas[2].getNaipe().equals(naipe))
                && (cartas[3].getNaipe().equals(naipe))
                && (cartas[4].getNaipe().equals(naipe));
    }

    public boolean isStraight() {
        boolean b = ((cartas[0].getNumero() == cartas[1].getNumero() + 1)
                && (cartas[1].getNumero() == cartas[2].getNumero() + 1)
                && (cartas[2].getNumero() == cartas[3].getNumero() + 1)
                && (cartas[3].getNumero() == cartas[4].getNumero() + 1));
        if (b == false && cartas[4].getNumero() == 1) {
            b = ((cartas[0].getNumero() == 13)
                    && (cartas[1].getNumero() == 12)
                    && (cartas[2].getNumero() == 11)
                    && (cartas[3].getNumero() == 10));
        }
        return b;
    }

    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    public boolean isRoyalFlush() {
        return (isStraightFlush() && (cartas[0].getNumero() == 13)
                && (cartas[1].getNumero() == 12)
                && (cartas[2].getNumero() == 11)
                && (cartas[3].getNumero() == 10)
                && (cartas[4].getNumero() == 1));
    }

    public boolean isPar() {
        return qtdCartas(2) == 1;
    }

    public boolean isDoisPares() {
        return qtdCartas(2) == 2;
    }

    public boolean isTrinca() {
        return qtdCartas(3) == 1;
    }

    public boolean isFullHouse() {
        return (qtdCartas(3) == 1) && (qtdCartas(2) == 1);
    }

    public boolean isFour() {
        return qtdCartas(4) == 1;
    }

    private int qtdCartas(int nCartas) {
        int qtdDama = 0;
        int qtdRei = 0;
        int qtdAs = 0;
        int qtdValete = 0;
        int qtdDez = 0;
        int qtdNove = 0;
        int qtdOito = 0;
        int qtdSete = 0;
        int qtdSeis = 0;
        int qtdCinco = 0;
        int qtdQuatro = 0;
        int qtdTres = 0;
        int qtdDois = 0;
        int nuTrinca = 0;
        //--
        for (int i = 0; i < 5; i++) {
            switch (cartas[i].getNumero()) {
                case 1:
                    qtdAs++;
                    break;
                case 2:
                    qtdDois++;
                    break;
                case 3:
                    qtdTres++;
                    break;
                case 4:
                    qtdQuatro++;
                    break;
                case 5:
                    qtdCinco++;
                    break;
                case 6:
                    qtdSeis++;
                    break;
                case 7:
                    qtdSete++;
                    break;
                case 8:
                    qtdOito++;
                    break;
                case 9:
                    qtdNove++;
                    break;
                case 10:
                    qtdDez++;
                    break;
                case 11:
                    qtdValete++;
                    break;
                case 12:
                    qtdDama++;
                    break;
                case 13:
                    qtdRei++;
                    break;
                default:
                    break;
            }

        }
        if (qtdAs == nCartas) {
            nuTrinca++;
        }
        if (qtdRei == nCartas) {
            nuTrinca++;
        }
        if (qtdValete == nCartas) {
            nuTrinca++;
        }
        if (qtdDama == nCartas) {
            nuTrinca++;
        }
        if (qtdDez == nCartas) {
            nuTrinca++;
        }
        if (qtdNove == nCartas) {
            nuTrinca++;
        }
        if (qtdOito == nCartas) {
            nuTrinca++;
        }
        if (qtdSete == nCartas) {
            nuTrinca++;
        }
        if (qtdSeis == nCartas) {
            nuTrinca++;
        }
        if (qtdCinco == nCartas) {
            nuTrinca++;
        }
        if (qtdQuatro == nCartas) {
            nuTrinca++;
        }
        if (qtdTres == nCartas) {
            nuTrinca++;
        }
        if (qtdDois == nCartas) {
            nuTrinca++;
        }
        return nuTrinca;
    }

    public void ordenar() {

        ArrayList<Carta> cartaList = new ArrayList<Carta>();

        for (int i = 0; i < 5; i++) {
            cartaList.add(cartas[i]);
        }
        Collections.sort(cartaList, (s1, s2)
                -> Integer.compare(s2.getNumero(), s1.getNumero()));

        for (int i = 0; i < 5; i++) {
            cartas[i] = cartaList.get(i);
        }
    }

    public Carta[] getCartas() {
        return cartas;
    }

    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    public Carta getCarta(int index) {
        return cartas[index];
    }

    public void setCarta(Carta carta, int index) {
        cartas[index] = carta;
    }
    
    public boolean isCarta(int index) {
        return (cartas[index] != null);
    }

}
