/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import road2covid.pkg19.Lista;

/**
 *
 * @author Camilo Cespedes
 */
public class Contagiado extends Vertice {

    public Contagiado(Vertice vertice) {
        super(vertice.getAristas(), vertice.getvID(), vertice.isMask(), vertice.isContagiado());
    }

    public void infectarAdyacentes() {
        for (Arista arista : this.getAristas()) {
            if (!arista.getvTerminal().isContagiado()) {
                arista.getvTerminal().setContagiado(verificarContagio(arista));
            }
        }
    }

    public boolean verificarContagio(Arista arista) {
        double prob;
        if (!arista.getvInicial().isMask()) {
            if (!arista.getvTerminal().isMask()) {
                if (arista.getPeso() <= 2) {
                    prob = 0.9;
                } else {
                    prob = 0.8;
                }
            } else {
                if (arista.getPeso() <= 2) {
                    prob = 0.6;
                } else {
                    prob = 0.4;
                }
            }
        } else {
            if (!arista.getvTerminal().isMask()) {
                if (arista.getPeso() <= 2) {
                    prob = 0.4;
                } else {
                    prob = 0.3;
                }
            } else {
                if (arista.getPeso() <= 2) {
                    prob = 0.3;
                } else {
                    prob = 0.2;
                }
            }
        }
        if (Math.random() <= prob){
            return true;
        }else{
            return false;
        }
        
    }
}
