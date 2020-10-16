/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import road2covid.pkg19.Lista;


/**
 * Evaluador de caminos y probabilidades de contagio
 * @author Camilo Cespedes, Luisa Escobar, Eduardo Rey
 */
public class Evaluador {

    /**
     * Grafo que se evaluará
     */
    private Grafo grafo;
    /**
     * Lista con todos los vertices contagiados
     */
    private Lista<Vertice> contagiados;
    /**
     * Crea un nuevo Evaluador
     */
    public Evaluador(int typeMask, int numV) {
        grafo = new Grafo(typeMask, numV);
    }
    
    /**
     * Genera un contagiado aleatorio.
     */
    private void contagioInicial() {
        int aristas = grafo.getNumAristas(grafo.getVertices());
        int contagiado = (int) Math.random()*aristas +1;
        for (Vertice vertice : grafo.getVertices()) {
            if (vertice.getvID() == contagiado) {
                vertice.setContagiado(true);
            }
        }
    }
    
    /**
     * Esta función retorna la probabilidad de que nuestro contagiado se contagie por X ruta
     * @param distancia Distancia entre los 2 vertices
     * @param noCont Vertice no contagiado
     * @param conta Vertice contagiado 
     * @param ruta Ruta a la cual se le calcula la probabilidad
     * @return Entero correspondiente a la probabilidad de contagio de una ruta
     */
    private int probabilidad(double distancia, Vertice noCont, Vertice conta, Lista<Vertice> ruta){
        distancia = getDistancia(ruta);
        if (noCont.isMask()==false && conta.isMask()==false) {
            if (distancia > 2) {
                return 80;
            } else {
                return 90;
            }
        } else if (conta.isMask()==false && noCont.isMask()==true){
            if (distancia > 2) {
                return 40;
            } else {
                return 60;
            }
        } else if(conta.isMask()==true && noCont.isMask()==false){
            if (distancia > 2) {
                return 30;
            } else {
                return 40;
            }
        } else {
            if (distancia > 2) {
                return 20;
            } else {
                return 30;
            }
        }
    }
    
    /**
     * Calcula la distancia desde el no contagiado hasta el contagiado en X ruta
     * @param ruta Ruta desde el vertice no contagiado hasta el contagiado
     * @return Double correspondiente a la distancia fisica entre dos vertices
     */
    private double getDistancia(Lista<Vertice> ruta){
        Vertice primero = ruta.getObject(0);
        int i = 1;
        Vertice segundo = ruta.getObject(i);
        Lista<Arista> aristas = primero.getAristas();
        double distancia = 0;
        while (segundo != null){
            for (Arista arista : aristas) {
                if (arista.getvInicial() == primero && arista.getvTerminal()== segundo) {
                    distancia = arista.getPeso()+ distancia;
                }
            }
            primero = segundo;
            aristas = primero.getAristas();
            segundo = ruta.getObject(i+1);
        }
        return distancia;
    }
    
    // por hacer ignorar mientras
    private Lista<Vertice> getRuta(Vertice noCont, Vertice Cont){
        
        return null;
    }
    
}
