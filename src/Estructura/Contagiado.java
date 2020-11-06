/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import road2covid.pkg19.Lista;

/**
 * Modelo de un vértice contagiado
 * @author Camilo Cespedes, Luisa Escobar, Eduardo Rey
 */
public class Contagiado extends Vertice {

    /**
     * COnstructor de Contagiado, crea un nuevo vértice contagiado
     * @param vertice Vertice modelo a partir del cual se crea el contagiado
     */
    public Contagiado(Vertice vertice) {
        super(vertice.getAristas(), vertice.getvID(), vertice.isMask(), vertice.isContagiado());
    }

    /**
     * Evalúa cada uno de los vertices adyacentes no contagiados del grafo para tomar una decisión de contagio
     */
    public void infectarAdyacentes() {
        for (Arista arista : this.getAristas()) {
            if (!arista.getvTerminal().isContagiado()) {
                arista.getvTerminal().setContagiado(verificarContagio(arista));
            }
        }
    }
    /**
     * Toma la decisión de si un vertice contagiará a otro partiendo de la probabilidad de contagio
     * @param arista Arista que representa la relación entre el vértice contagiado y el vertice sin contagiar
     * @return 
     */
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
