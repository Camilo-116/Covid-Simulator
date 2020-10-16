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
        verticePrimero = incluirAristas(verticePrimero);
        System.out.println("");
    }

    /**
     * Crea un número dado de elementos Vertice (sin aristas de conexión) y los
     * añade a la lista de vertices del grafo
     *
     * @param typeMask Informa al metodo si los vertices llevarán mascarilla o
     * no, o si es un evento aleatorio
     * @param num Numero de elementos Vertice que serán creados
     * @return Primer elemento de la lista de vertices
     */
    private Lista<Vertice> iniciarVertices(int typeMask, int num) {
        Lista<Vertice> listaV = new Lista();
        for (int i = 1; i <= num; i++) {
            Vertice v = new Vertice(i, typeMask);
            listaV.add(v);
        }
        return listaV;
    }

    /**
     * Crea aristas de relación aleatorias entre los vertices desconectados del
     * grafo
     *
     * @param verticeP Primer elemento de la lista de vertices a unir
     * @return Primer elemento de la lista de vertices ya relacionados por
     * aristas
     */
    private Lista<Vertice> incluirAristas(Lista<Vertice> verticeP) {

        verticeP = asegurarFConexo(verticeP);
        int last_aID = numAristas(verticeP);
        verticeP = aristasAleatorias(verticeP, last_aID);

        return verticeP;
    }

    /**
     * Ejecuta una primera unión aleatoria pero específica, de forma que se
     * asegure que el grafo dirigido creado sea fuertemente conexo y se pueda
     * llegar desde cualquier Vertice a otro
     *
     * @param verticeP Primer elemento de la lista de vertices a unir
     * @return Primer elemento de la lista de vertices ya relacionados por las
     * aristas básicas que describen una relación de conexión fuerte
     */
    private Lista<Vertice> asegurarFConexo(Lista<Vertice> verticeP) {
        Lista<Vertice> vertices = verticeP;
        int indices[] = mezclarIndices(vertices);
        int i = 0, limit = vertices.size() - 1, aID = 1;
        for (Vertice vertice : vertices) {
            if (i < limit) {
                if (isBiAlready(vertice.getvID(), indices[i])) {
                    i++;
                    limit++;
                    vertice.getAristas().add(new Arista(vertice, vertices.getObject(indices[i] - 1), Math.random() * 2 + 0.1, aID));
                    vertices.getObject(indices[i] - 1).getAristas().add(new Arista(vertices.getObject(indices[i] - 1), vertice, Math.random() * 2 + 0.1, aID));
                } else {
                    vertice.getAristas().add(new Arista(vertice, vertices.getObject(indices[i] - 1), Math.random() * 2 + 0.1, aID));
                    vertices.getObject(indices[i] - 1).getAristas().add(new Arista(vertices.getObject(indices[i] - 1), vertice, Math.random() * 2 + 0.1, aID));
                }
                aID++;
                i++;
            } else {
                break;
            }
        }
        return vertices;
    }

    /**
     * Realiza conexiones aleatorias entre vértices, de forma que no se generen
     * bucles ni existan aristas múltiples
     *
     * @param verticeP Primer elemento de la lista de vertices a manejar
     * @param last_aID Numero de identificacion de la ultima arista añadida al
     * grafo
     * @return Primer elemento de la lista de vertices con las nuevas uniones
     * incluidas
     */
    private Lista<Vertice> aristasAleatorias(Lista<Vertice> verticeP, int last_aID) {
        Lista<Vertice> vertices = verticeP;
        int limMin, limMax, numArist, vertUnir;
        /**
         * Se designa el límite minimo y maxímo de la cantidad de aristas entre
         * dos vértices dependiendo de la cantidad de vértices que se manejarán
         * El modelo manjea dos límites para la escogencia aleatoria del número
         * de aristas de cada vértice. Mientras más vértices hayan en el grafo,
         * mayor será el rango dentro del cual se escogerá el número de aristas
         * por vertice. -> limMin indica el limite inferior del rango de
         * aleatoriedad del numero de aristas para el vertice -> limMax indica
         * el limite superior del rango de aleatoriedad del numero de aristas
         * para el vértice 
         * Entre 0 y 4 vertices no se asignan aristas nuevas, puesto que ya hay 
         * suficientes
         */
        if (vertices.size() > 0 && vertices.size() <= 4) {
            limMin = 0;
            limMax = 0;
        } else {
            if (vertices.size() > 4 && vertices.size() <= 15) {
                limMin = 1;
                limMax = 2;
            } else {
                if (vertices.size() > 15 && vertices.size() <= 60) {
                    limMin = 3;
                    limMax = 7;
                } else {
                    if (vertices.size() > 60 && vertices.size() <= 130) {
                        limMin = 8;
                        limMax = 15;
                    } else {
                        limMin = 18;
                        limMax = (int) Math.floor(Math.random() * 57 + 22);
                    }
                }
            }
        }
        int aID = last_aID + 1;
        for (Vertice vertice : vertices) {
            numArist = (int) Math.floor(Math.random() * (limMax - limMin + 1) + limMin);
            for (int i = 0; i < numArist; i++) {
                do {
                    vertUnir = (int) Math.floor(Math.random() * vertices.size() + 1);
                } while (vertUnir == vertice.getvID() || isAlready(vertice.getvID(), vertUnir));
                vertice.getAristas().add(new Arista(vertice, vertices.getObject(vertUnir - 1), Math.random() * 2 + 0.1, aID));
                aID++;
            }
        }
        return vertices;
    }

    /**
     * Cuenta el número de aristas total que hay entre los vertices del grafo
     *
     * @param verticeP Primer elemento de la lista de vertices
     * @return Entero correspondiente al numero total de aristas que hay entre
     * los vertices del grafo
     */
    private int numAristas(Lista<Vertice> verticeP) {
        int numAristas = 0;
        for (Vertice vertice : verticeP) {
            numAristas = numAristas + vertice.getAristas().size();
        }
        return numAristas;
    }

    /**
     * Permite saber si un valor está en un vector
     *
     * @param j Valor a buscar
     * @param indices Vector objetivo
     * @return Booleano que indica si se encontró o no el valor
     */
    private boolean isNot(int j, int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            if (j == indices[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Crea un vector con tamaño igual al número de vertices del grafo, e
     * introduce los numeros de identificacion de cada vertice en el vector de
     * forma aleatoria
     *
     * @param vertices Primer elemento de la lista de vertices a manejar
     * @return Vector de enteros que contiene los numeros de identificacion de
     * los vertices del grafo ordenados de forma aleatoria
     */
    private int[] mezclarIndices(Lista<Vertice> vertices) {
        int indices[] = new int[vertices.size()];
        int j = (int) Math.floor(Math.random() * vertices.size() + 1), i = 0;
        for (Vertice vertice : vertices) {
            while (isNot(j, indices)) {
                indices[i] = j;
                i++;
                do {
                    j = (int) Math.floor(Math.random() * vertices.size() + 1);
                } while (!isNot(j, indices) && i < vertices.size());

            }
        }
        if (generatesLoop(indices, vertices)) {
            return mezclarIndices(vertices);
        }
        return indices;
    }

    /**
     * Permite conocer si un orden específico del vector de números de
     * identificación provocaría que apareciera un bucle en el grafo (lo cual es
     * inadmisible)
     *
     * @param indices Vector que contiene los números de identificación en orden
     * aleatorio
     * @param vertices Primer elemento de al lista de vertices a manejar
     * @return Booleano que indica si la combinación especificada produce un
     * bucle o no
     */
    private boolean generatesLoop(int[] indices, Lista<Vertice> vertices) {
        int i = 0;
        for (Vertice vertice : vertices) {
            if (vertice.getvID() == indices[i]) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Permite conocer si ya existe una relación bidireccional entre dos
     * vertices
     *
     * @param indice Numero de identificación del Vertice 1
     * @param vID Numero de identificacion del Vertice 2
     * @return Booleano que indica si existe una realción bidireccional entre
     * los dos vertices indicados
     */
    private boolean isBiAlready(int vID, int indice) {
        Vertice v1 = this.verticePrimero.getObject(vID - 1), v2 = this.verticePrimero.getObject(indice - 1);
        if (!v1.getAristas().isEmpty() && !v2.getAristas().isEmpty()) {
            for (Arista arista : v1.getAristas()) {
                if (arista.getvTerminal().equals(v2)) {
                    for (Arista arista1 : v2.getAristas()) {
                        if (arista1.getvTerminal().equals(v1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Permite conocer si existe una arista que apunte de un Vertice 1 a un
     * Vertice 2
     *
     * @param vID Numero de identificacion del Vertice 1
     * @param indice Numero de identificacion del Vertice 2
     * @return Booleano que indica si existe una arista que apunte de 1 a 2
     */
    private boolean isAlready(int vID, int indice) {
        Vertice v1 = this.verticePrimero.getObject(vID - 1), v2 = this.verticePrimero.getObject(indice - 1);
        if (!v1.getAristas().isEmpty()) {
            for (Arista arista : v1.getAristas()) {
                if (arista.getvTerminal().equals(v2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Permite acceder al número de aristas desde cualquier otra clase.
     * @param vertices Lista con los vIDs de los vertices.
     * @return 
     */
    public int getNumAristas(Lista<Vertice> vertices) {
        return numAristas(vertices);
    }

    public Lista<Vertice> getVerticePrimero() {
        return verticePrimero;
    }

    public int[][] getMI() {
        return MI;
    }
    
    
}
