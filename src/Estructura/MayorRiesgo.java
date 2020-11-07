
package Estructura;

import road2covid.pkg19.Lista;
import static Estructura.Grafo.GOES_TO_INF;

/**
 * Clase que modela el mecanismo del algoritmo de Floyd Warshall para caminos mínimos (modificado)
 * @author Camilo Cespedes, Luisa Escobar, Eduardo Rey
 */

public class MayorRiesgo {

    /**
     * Método estático que permite conocer los vertices a traves de los cuales se genera el camino de mayor riesgo de contagio
     * @param G Matriz de adyacencia/pesos del grafo
     * @param grafo Grafo para el cual se calcularál los caminos minimos entre sus vertices
     * @param vertice Vertice desde el cual se quiere conocer el camino a través del cual se arriesga más
     * @return Lista enlazada de vertices ordenada del vértice contagiado más riesgoso hasta el vértice objetivo
     */
    public static Lista<Vertice> FWarshall(double G[][], Grafo grafo, Vertice vertice) {
        double MAFloyd[][] = new double[grafo.getVertices().size()][grafo.getVertices().size()];    //Matriz de Adyacencia/Pesos que se manejará
        int i, j, k;
        int MRFloyd[][] = new int[grafo.getVertices().size()][grafo.getVertices().size()];  //Matriz de recorridos que se manejará
        G = prepararMatriz(G);
        for (i = 0; i < grafo.getVertices().size(); i++) {
            for (j = 0; j < grafo.getVertices().size(); j++) {
                if (G[i][j] != GOES_TO_INF) {
                    if (i != j) {
                        MAFloyd[i][j] = grafo.probabilidad_entreVertices(grafo.getVertices().getObject(i), grafo.getVertices().getObject(j), G[i][j]);
                    }
                    MRFloyd[i][j] = j;
                } else {
                    MAFloyd[i][j] = 0;
                }
            }
        }

        for (k = 0; k < grafo.getVertices().size(); k++) {
            for (i = 0; i < grafo.getVertices().size(); i++) {
                for (j = 0; j < grafo.getVertices().size(); j++) {
                    if (G[i][j] != 0) {
                        if (MAFloyd[i][k] * MAFloyd[k][j] > MAFloyd[i][j]) {
                            MAFloyd[i][j] = MAFloyd[i][k] * MAFloyd[k][j];
                            MRFloyd[i][j] = MRFloyd[i][k];
                        }
                    }
                }
            }
        }
        Vertice probMax = null;
        for (Contagiado contagiado : grafo.getContagiados()) {
            if (contagiado == grafo.getContagiados().getObject(0)) {
                probMax = contagiado;
                continue;
            }
            if (MAFloyd[contagiado.getvID()-1][vertice.getvID()-1] > MAFloyd[probMax.getvID()-1][vertice.getvID()-1]) {
                probMax = contagiado;
            }
        }
        if (probMax == null) {
            return null;
        }
        return MayorRiesgo.recorridoDeRiesgo(grafo, probMax, vertice, MRFloyd);
    }

    /**
     * Construye la lista de vertices que representan el camino de mayor contagio
     * @param g Grafo a manejar
     * @param origen Vertice de origen
     * @param destino Vertice de destino
     * @param MRFloyd Matriz de recorridos construida por el algoritmo de Floyd Warshall
     * @return Lista de vertices en el camino, ordenada desde el vértice contagiado hasta el vértice a contagiar
     */
    public static Lista<Vertice> recorridoDeRiesgo(Grafo g, Vertice origen, Vertice destino, int MRFloyd[][]) {
        Lista<Vertice> camino = new Lista();
        Vertice n = origen;
        camino.add(n);
        while (n != destino) {
            n = g.getVertices().getObject(MRFloyd[n.getvID()-1][destino.getvID()-1]);
            camino.add(n);
        }
        return camino;
    }

    /**
     * Prepara la matriz de adyacencia/pesos, ubicando números que representen al infinito en las casillas para las cuales no hay camino directo
     * @param MAFloyd Matriz de Adyacencia/pesos a manejar
     * @return Matriz de elementos double que representan los pesos para los caminos directos entre un vértice y otro, modificada para trabajar con el algoritmo de Floyd Warshall
     */
    private static double[][] prepararMatriz(double MAFloyd[][]) {
        for (int i = 0; i < MAFloyd.length; i++) {
            for (int j = 0; j < MAFloyd.length; j++) {
                if (i != j) {
                    if (MAFloyd[i][j] == 0) {
                        MAFloyd[i][j] = (int) GOES_TO_INF;
                    }
                }
            }
        }
        return MAFloyd;
    }
}
