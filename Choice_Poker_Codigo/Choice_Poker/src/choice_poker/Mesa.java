/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choice_poker;

import javax.swing.JOptionPane;
import rede.Lance;

/**
 *
 * @author edgut
 */

public class Mesa {
    
    private Jogador jogador1;
    private Jogador jogador2;
    private Baralho baralho;
    private boolean conectado;
    private boolean partidaEmAndamento, partidaTerminada;
    private boolean j1Pronto, j2Pronto, desordenada;
    private int fase, fichas;
    
    public Mesa() {
        
    }
    
    public int apostar(int fichas) {
        boolean vez = jogador1.isTurno();
        int resultado;
        if (vez) {
            resultado = this.tratarAposta(jogador1, fichas);
            return resultado;
        }
        return 8;
    }
    
    public void iniciar() {
        baralho = new Baralho();
        jogador1 = null;
	jogador2 = null;
        fichas = 0;
	partidaEmAndamento = false;
    }
    
    public Lance informarJogada(Jogador jogador, int apostadas) {
        Lance lance = new Lance();
        lance.setJogador(jogador);
        lance.setFichas(apostadas);
        return lance;
    }
    
     public Lance informarJogada(int qualCarta, Jogador jogador) {
        Lance lance = new Lance();
        lance.setJogador(jogador);
        lance.setQualCarta(qualCarta);
        return lance;
    }
     
    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public boolean isPartidaEmAndamento() {
        return partidaEmAndamento;
    }

    public void setPartidaEmAndamento(boolean partidaEmAndamento) {
        this.partidaEmAndamento = partidaEmAndamento;
    }

    public void receberJogada(Lance jogada) {
        int fichas = jogada.getFichas();
        int qualCarta = jogada.getQualCarta();
        Jogador jogador = jogada.getJogador();
        boolean check = jogada.isCheck();
        int resultado = 0;
        switch (fase) {
            case 0:
                resultado = tratarRecebimento(jogador);
                break;
            case 1:
                resultado = tratarDescarte(qualCarta, jogador);
                break;
            case 2:     
                if (!check) {
                    if (desordenada) {
                        desordenada = false;
                        jogador1.ordenar();
                    }
                    resultado = this.tratarAposta(jogador, fichas);
                } else {
                    resultado = this.tratarCheck(jogador, check);
                }
                break;
        }
    }

    public void embaralhar() {
        baralho = new Baralho();
        fichas = 0;
    }

    public void criarJogador(String idUsuario) {
        if (jogador1 == null) {
            jogador1 = new Jogador();
            jogador1.iniciar();
            jogador1.setNome(idUsuario);
        } else {
            jogador2 = new Jogador();
            jogador2.iniciar();
            jogador2.setNome(idUsuario);
        }
    }

    public void iniciarPartida(Integer posicao) {
        partidaEmAndamento = true;
        if(posicao == 1){
            jogador1.setTurno(true);
            JOptionPane.showMessageDialog(null, "Seu turno");
        }else{
            jogador2.setTurno(true);
        }
        fase = 0;
    }

    private int tratarAposta(Jogador jogador, int apostadas) {
        if (fase == 2) {
            boolean vez = jogador1.isTurno();
            if (vez) {
                jogador1.setFichas(jogador1.getFichas() - apostadas);
                jogador1.setApostadas(jogador1.getApostadas() + apostadas);
                this.fichas += apostadas;
                jogador1.setTurno(false);
                jogador2.setTurno(true);
                return 10;
            } else {
                jogador2.setFichas(jogador.getFichas());
                jogador2.setApostadas(jogador.getApostadas());
                this.fichas += apostadas;
                jogador2.setTurno(false);
                jogador1.setTurno(true);
                JOptionPane.showMessageDialog(null, "Seu turno");
                return 10;
            }
        }
        return 12;
    }

    public int trocarCartas(int qualCarta) {
        boolean vez = jogador1.isTurno();
        int resultado;
        if (vez) {
            resultado = tratarDescarte(qualCarta, jogador1);
            return resultado;
        }
        return 8;
    }

    private int tratarDescarte(int qualCarta, Jogador jogador) {
        if (fase == 1 && !baralho.empty()) {
            boolean vez = jogador1.isTurno();
            if (vez) {
                if (qualCarta == 5) {
                    j1Pronto = true;
                    jogador1.setTurno(false);
                    jogador2.setTurno(true);
                    if (j1Pronto && j2Pronto) {
                        jogador2.ordenar();
                        desordenada = true;
                        fase = 2;
                    }
                    return 10;
                }
                if (jogador1.getDescartada1().getNaipe()==null) {
                    Carta cartaDescartada = jogador1.getCarta(qualCarta);
                    jogador1.setDescartada1(cartaDescartada);
                    Carta cartaRecuperada = baralho.getRandomCarta();
                    baralho.descartarCarta(cartaRecuperada);
                    jogador1.setCarta(cartaRecuperada, qualCarta);
                    return 10;
                } else if (jogador1.getDescartada2().getNaipe()==null) {
                    Carta cartaDescartada = jogador1.getCarta(qualCarta);
                    jogador1.setDescartada2(cartaDescartada);
                    Carta cartaRecuperada = baralho.getRandomCarta();
                    baralho.descartarCarta(cartaRecuperada);
                    jogador1.setCarta(cartaRecuperada, qualCarta);
                    j1Pronto = true;
                    if (j1Pronto && j2Pronto) {
                        jogador2.ordenar();
                        desordenada = true;
                        fase = 2;
                    }
                    jogador1.setTurno(false);
                    jogador2.setTurno(true);
                    return 10;
                } else {
                    j1Pronto = true;
                    jogador1.setTurno(false);
                    jogador2.setTurno(true);
                    if (j1Pronto && j2Pronto) {
                        jogador2.ordenar();
                        desordenada = true;
                        fase = 2;
                    }
                    return 12;
                }
            } else {
                if (qualCarta == 5) {
                    j2Pronto = true;
                    jogador2.setTurno(false);
                    jogador1.setTurno(true);
                    JOptionPane.showMessageDialog(null, "Seu turno");
                    if (j1Pronto && j2Pronto) {
                        jogador1.ordenar();
                        jogador2.ordenar();
                        fase = 2;
                        JOptionPane.showMessageDialog(null, "Comece apostando");
                    }
                    return 10;
                }
                if (jogador.getDescartada2().getNaipe()!=null) {
                    jogador2.setDescartada2(jogador.getDescartada2());
                    jogador2.setMao(jogador.getMao());
                    baralho.descartarCarta(jogador.getCarta(qualCarta));
                    j2Pronto = true;
                    jogador2.setTurno(false);
                    jogador1.setTurno(true);
                    JOptionPane.showMessageDialog(null, "Seu turno");
                    if (j1Pronto && j2Pronto) {
                        jogador1.ordenar();
                        jogador2.ordenar();
                        fase = 2;
                        JOptionPane.showMessageDialog(null, "Comece apostando");
                    }
                    return 10;
                }
                if (jogador.getDescartada1().getNaipe()!=null) {
                    jogador2.setDescartada1(jogador.getDescartada1());
                    jogador2.setMao(jogador.getMao());
                    baralho.descartarCarta(jogador.getCarta(qualCarta));
                    return 10;
                } else {
                    j2Pronto = true;
                    jogador2.setTurno(false);
                    jogador1.setTurno(true);
                    JOptionPane.showMessageDialog(null, "Seu turno");
                    if (j1Pronto && j2Pronto) {
                        jogador1.ordenar();
                        jogador2.ordenar();
                        fase = 2;
                        JOptionPane.showMessageDialog(null, "Comece apostando");
                    }
                    return 12;
                }
            }
        }
        return 12;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public int getFichas() {
        return fichas;
    }

    private void tratarVitoria(boolean vitoriaMelhor) {
        int j1Mao = jogador1.avaliar();
        int j2Mao = jogador2.avaliar();
        if (vitoriaMelhor) {
            if (j1Mao > j2Mao){
                jogador1.setVencedor(true);
            } else if (j2Mao > j1Mao) {
                jogador2.setVencedor(true);
            } else {
                switch (j1Mao) {
                    case 10:
                        JOptionPane.showMessageDialog(null, "Uau. Voces dois tem um Royal Flush. Nao sei se isso ja aconteceu na historia.");
                    default:
                        if (jogador1.getCarta(0).getNumero() > jogador2.getCarta(0).getNumero()) {
                            jogador1.setVencedor(true);
                        } else if (jogador1.getCarta(0).getNumero() < jogador2.getCarta(0).getNumero()) {
                            jogador2.setVencedor(true);
                        } else {
                            if (jogador1.getCarta(1).getNumero() > jogador2.getCarta(1).getNumero()) {
                                jogador1.setVencedor(true);
                            } else if (jogador1.getCarta(1).getNumero() < jogador2.getCarta(1).getNumero()) {
                                jogador2.setVencedor(true);
                            } else {
                                if (jogador1.getCarta(2).getNumero() > jogador2.getCarta(2).getNumero()) {
                                    jogador1.setVencedor(true);
                                } else {
                                    jogador2.setVencedor(true);
                                }
                            }
                        }
                        break;
                        
                }       
            }
        } else {
            if (j1Mao > j2Mao){
                jogador2.setVencedor(true);
            } else if (j2Mao > j1Mao) {
                jogador1.setVencedor(true);
            } else {
                switch (j1Mao) {
                    case 10:
                        JOptionPane.showMessageDialog(null, "Uau. Voces dois tem um Royal Flush. Nao sei se isso ja aconteceu na historia.");
                    default:
                        if (jogador1.getCarta(0).getNumero() > jogador2.getCarta(0).getNumero()) {
                            jogador2.setVencedor(true);
                        } else if (jogador1.getCarta(0).getNumero() < jogador2.getCarta(0).getNumero()) {
                            jogador1.setVencedor(true);
                        } else {
                            if (jogador1.getCarta(1).getNumero() > jogador2.getCarta(1).getNumero()) {
                                jogador2.setVencedor(true);
                            } else if (jogador1.getCarta(1).getNumero() < jogador2.getCarta(1).getNumero()) {
                                jogador1.setVencedor(true);
                            } else {
                                if (jogador1.getCarta(2).getNumero() > jogador2.getCarta(2).getNumero()) {
                                    jogador2.setVencedor(true);
                                } else {
                                    jogador1.setVencedor(true);
                                }
                            }
                        }
                        break;
                        
                }       
            }
        }
        if (jogador1.isVencedor()) {
            JOptionPane.showMessageDialog(null, "Voce venceu!");
            jogador1.setFichas(jogador1.getFichas() + fichas);
            jogador1.setApostadas(0);
            fichas = 0;
            jogador2.setApostadas(0);
        } else if (jogador2.isVencedor()){
            JOptionPane.showMessageDialog(null, "Voce perdeu!");
            jogador2.setFichas(jogador2.getFichas() + fichas);
            jogador2.setApostadas(0);
            fichas = 0;
            jogador1.setApostadas(0);
        }
        if (jogador1.getFichas() == 0) {
            JOptionPane.showMessageDialog(null, "Voce perdeu o jogo. Inicie uma nova partida");
            fase = 3;
        } else if (jogador2.getFichas() == 0) {
            JOptionPane.showMessageDialog(null, "Voce ganhou o jogo. Inicie uma nova partida");
            fase = 3;
        }
    }

    public int receberCartas() {
        boolean vez = jogador1.isTurno();
        int resultado;
        if (vez) {
            resultado = tratarRecebimento(jogador1);
            return resultado;
        }
        return 8;
    }

    public Lance informarJogada(Jogador jogador) {
        Lance lance = new Lance();
        lance.setJogador(jogador);
        return lance;
    }

    private int tratarRecebimento(Jogador jogador) {
        boolean vez = jogador1.isTurno();
        Mao mao = new Mao();
        if (vez) {
            if (!jogador1.isMaoVazia()) {
                return 12;
            }
            Carta cartas[] = new Carta[5];
            for (int i=0; i<5; i++) {
                cartas[i] = baralho.getRandomCarta();
                baralho.descartarCarta(cartas[i]);
            }
            mao.setCartas(cartas);
            jogador1.setMao(mao);
            jogador1.setMaoVazia(false);
            jogador1.setTurno(false);
            jogador2.setTurno(true);
            if (!jogador1.isMaoVazia() && !jogador2.isMaoVazia()) {
                fase = 1;
            }
            return 10;
        } else {
            if (partidaTerminada) {
                jogador2.iniciar();
                jogador1.iniciar();
                j1Pronto = false;
                j2Pronto = false;
                embaralhar();
                partidaTerminada = false;
            }
            jogador2.setMao(jogador.getMao());
            jogador2.setMaoVazia(false);
            for (int i=0; i<5; i++) {
                baralho.descartarCarta(jogador2.getCarta(i));
            }
            jogador2.setTurno(false);
            jogador1.setTurno(true);
            JOptionPane.showMessageDialog(null, "Seu turno");
            if (!jogador1.isMaoVazia() && !jogador2.isMaoVazia()) {
                fase = 1;
                JOptionPane.showMessageDialog(null, "Clique em trocar cartas");
            }
            return 10;
        }
    }

    public int getFase() {
        return fase;
    }

        public int check() {
        boolean vez = jogador1.isTurno();
        int resultado;
        if (vez) {
            if (jogador1.getApostadas() < jogador2.getApostadas() || jogador2.isCheck()) {
                resultado = tratarCheck(jogador1, true);
                return resultado;
            }
            return 15;
        }
        return 8;
    }

    public Lance informarJogada(Jogador jogador, boolean b) {
        Lance lance = new Lance();
        lance.setJogador(jogador);
        lance.setCheck(b);
        return lance;    
    }

    private int tratarCheck(Jogador jogador, boolean b) {
        if (fase == 2) {
            boolean vez = jogador1.isTurno();
            if (vez) {
                jogador1.setCheck(b);
                jogador1.setTurno(false);
                jogador2.setTurno(true);
                if (jogador2.isCheck()) {
                    String options[] = {"Melhor mao", "Pior mao"};
                    int opcoes = JOptionPane.showOptionDialog(null, "Selecione a condicao de vitoria", "Selecionar vitoria",
                        JOptionPane.DEFAULT_OPTION, 0, null, options, options[0]);
                    if (opcoes == 0) {
                        tratarVitoria(true);   
                    } else {
                        tratarVitoria(false);
                    }
                    fase = 0;
                    partidaTerminada = true;
                    return 10;
                }
                return 10;
            } else {
                jogador2.setCheck(b);
                jogador1.setTurno(true);
                jogador2.setTurno(false);
                if (jogador1.isCheck()) {
                    if (!jogador.isVencedor()) {
                        JOptionPane.showMessageDialog(null, "Voce venceu!");
                        jogador1.setFichas(jogador1.getFichas() + fichas);
                        jogador1.setApostadas(0);
                        fichas = 0;
                        jogador2.setApostadas(0);
                    } else if (jogador.isVencedor()) {
                        JOptionPane.showMessageDialog(null, "Voce perdeu!");
                        jogador2.setFichas(jogador2.getFichas() + fichas);
                        jogador2.setApostadas(0);
                        fichas = 0;
                        jogador1.setApostadas(0);
                    }
                    if (jogador1.getFichas() == 0) {
                        JOptionPane.showMessageDialog(null, "Voce perdeu o jogo. Inicie uma nova partida");
                        fase = 3;
                        return 10;
                    } else if (jogador2.getFichas() == 0) {
                        JOptionPane.showMessageDialog(null, "Voce ganhou o jogo. Inicie uma nova partida");
                        fase = 3;
                        return 10;
                    }
                    fase = 0;
                    jogador2.iniciar();
                    jogador1.iniciar();
                    j1Pronto = false;
                    j2Pronto = false;
                    embaralhar();
                    JOptionPane.showMessageDialog(null, "Seu turno");
                    return 10;
                }
                JOptionPane.showMessageDialog(null, "O outro jogador deu check. Clique em check para decidir a condicao de vitoria");
                return 10;
            }
        }
        return 12;
    }

}
