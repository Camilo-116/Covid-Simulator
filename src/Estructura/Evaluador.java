/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;


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
     * Crea un nuevo Evaluador
     */
    public Evaluador() {
        grafo = new Grafo(3, 5);
    }
    
}
