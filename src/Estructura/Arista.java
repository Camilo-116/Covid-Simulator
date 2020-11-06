/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

/**
 * Modelo de una arista del grafo
 *
 * @author Camilo Cespedes, Luisa Escobar, Eduardo Rey
 */
public class Arista {

    /**
     * Numero de identificacion de la arista
     */
    private int aID;
    /**
     * Vertice inicial de la arista dirigida 
     */
    private Vertice vInicial;
    /**
     * Vertice terminal de la arista dirigida
     */
    private Vertice vTerminal;
    /**
     * Peso de la arista
     */
    private double peso;

    /**
     * Crea una nueva arista entre dos vértices
     *
     * @param vInicial Vertice que se asignara como inicial de la arista
     * @param vTerminal Vertice que se asignará como terminal de la arista
     * @param peso Peso que se asignará a la arista
     */
    public Arista(Vertice vInicial, Vertice vTerminal, double peso, int aID) {
        if (vTerminal.equals(vInicial)){
            System.out.println("Hay ciclo y me valio monda");
        }
        this.vInicial = vInicial;
        this.vTerminal = vTerminal;
        this.peso = peso;
        this.aID = aID;
    }

    /**
     * Permite acceder al Vertice inicial de la arista
     *
     * @return Vertice correspondiente al extremo de inicio de la arista
     */
    public Vertice getvInicial() {
        return vInicial;
    }

    /**
     * Permite acceder al numero de identificacion de la arista
     * @return Entero correspondiente al numero de identificacion de la arista
     */
    public int getaID() {
        return aID;
    }

    
    /**
     * Permite acceder al Vertice terminal de la arista
     *
     * @return Vertice correspondiente al extremo final de la arista
     */
    public Vertice getvTerminal() {
        return vTerminal;
    }

    /**
     * Permite acceder al peso de la arista
     * 
     * @return Float correspondiente al peso de la arista
     */
    public double getPeso() {
        return peso;
    }
    
    

}
