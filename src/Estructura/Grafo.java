/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import road2covid.pkg19.Lista;

/**
 * Estructura que modela un grafo
 *
 * @author Camilo Cespedes, Luisa Escobar, Eduardo Rey
 */
public class Grafo {

    /**
     * Primer elemento de la lista de Vertices
     */
    private Lista<Vertice> verticePrimero;
    /**
     * Matriz de incidencia del grafo
     */
    private int MI[][];

    /**
     * Crea un nuevo Grafo
     */
    public Grafo(int typeMask, int num) {
        verticePrimero = iniciarVertices(typeMask, num);
        /*for (Vertice vertice : verticePrimero) {
            System.out.println(vertice.getvID());
        }*/

        verticePrimero = incluirAristas(verticePrimero);
    }

    private Lista<Vertice> iniciarVertices(int typeMask, int num) {
        Lista<Vertice> listaV = new Lista();
        for (int i = 0; i < num; i++) {
            Vertice v = new Vertice(i, typeMask);
            listaV.add(v);
        }
        return listaV;
    }

    private Lista<Vertice> incluirAristas(Lista<Vertice> verticeP) {
        Lista<Vertice> vertices = verticeP;
        int indices[] = new int[vertices.size()];
        return vertices;
    }
}
