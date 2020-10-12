/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import road2covid.pkg19.Lista;

/**
 * Modelo de un vertice del grafo
 *
 * @author Camilo Cespedes, Luisa Escobar, Eduardo Rey
 */
public class Vertice {

    /**
     * Primer elemento de la lista de aristas adyacentes al vértice
     */
    private Lista<Arista> aristas;
    /**
     * Numero de dentificación del vértice
     */
    private int vID;
    /**
     * Identificador de uso de tapabocas
     */
    private boolean mask;
    /**
     * Identificador de contagio
     */
    private boolean contagiado;

    /**
     * Crea un nuevo Vertice
     */
    public Vertice(int vID, int typeMask) {
        this.vID = vID;
        contagiado = false;
        aristas = new Lista();
        switch (typeMask) {
            case 1:
                mask = true;
                break;
            case 2: 
                mask = false;
                break;
            case 3:
                //Se asigna una probabilidad del 50% a cada evento (Portar mascarilla o no) en caso de que se haya seleccionado la opción de aleatoriedad
                if (Math.random()>0.5){
                    mask = false;
                }else{
                    mask = true;
                }
                break;
        }
    }

    /**
     * Permite acceder al primer elemento de la lista de aristas del Vertice
     * @return Primer elemento de una lista de aristas
     */
    public Lista<Arista> getAristas() {
        return aristas;
    }

    /**
     * Permite acceder al número de identificación del Vertice
     * @return Entero correspondiente al número de identificación del Vertice
     */
    public int getvID() {
        return vID;
    }

    /**
     * Permite acceder al identificador de uso de mascarilla del Vertice
     * @return Booleano que indica si el Vertice usa mascarilla o no
     */
    public boolean isMask() {
        return mask;
    }

    /**
     * Permite acceder al identificador de contagio del Vertice
     * @return Booleano que indica si el Vertice ha sido contagiado o no
     */
    public boolean isContagiado() {
        return contagiado;
    }

    
    /**
     * Permite modificar el identificador de contagio, en caso de que el vértice haya sido contagiado
     *
     * @param contagiado Variable lógica que indica el nuevo estado de contagio
     */
    public void setContagiado(boolean contagiado) {
        this.contagiado = contagiado;
    }

}
