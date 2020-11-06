/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import road2covid.pkg19.Lista;
import static Estructura.Grafo.GOES_TO_INF;

/**
 *
 * @author Camilo Cespedes
 */

public class MayorRiesgo {

    public static Lista<Vertice> FWarshall(double G[][], Grafo grafo, Vertice vertice) {
        double matrix[][] = new double[grafo.getVertices().size()][grafo.getVertices().size()];
        int i, j, k;
        int next[][] = new int[grafo.getVertices().size()][grafo.getVertices().size()];
        G = prepararMatriz(G);
        for (i = 0; i < grafo.getVertices().size(); i++) {
            for (j = 0; j < grafo.getVertices().size(); j++) {
                if (G[i][j] != GOES_TO_INF) {
                    if (i != j) {
                        matrix[i][j] = grafo.probabilidad_entreVertices(grafo.getVertices().getObject(i), grafo.getVertices().getObject(j), G[i][j]);
                    }
                    next[i][j] = j;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        for (k = 0; k < grafo.getVertices().size(); k++) {
            for (i = 0; i < grafo.getVertices().size(); i++) {
                for (j = 0; j < grafo.getVertices().size(); j++) {
                    if (G[i][j] != 0) {
                        if (matrix[i][k] * matrix[k][j] > matrix[i][j]) {
                            matrix[i][j] = matrix[i][k] * matrix[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
        }
        //printMatrix(matrix, grafo);
        //printMatrix(next, grafo);
        Vertice probMax = null;
        for (Contagiado contagiado : grafo.getContagiados()) {
            if (contagiado == grafo.getContagiados().getObject(0)) {
                probMax = contagiado;
                continue;
            }
            if (matrix[contagiado.getvID()-1][vertice.getvID()-1] > matrix[probMax.getvID()-1][vertice.getvID()-1]) {
                probMax = contagiado;
            }
        }
        if (probMax == null) {
            return null;
        }
        return MayorRiesgo.printPath(grafo, probMax, vertice, next);
    }

    public static Lista<Vertice> printPath(Grafo g, Vertice origen, Vertice destino, int next[][]) {
        Lista<Vertice> path = new Lista();
        Vertice n = origen;
        path.add(n);
        while (n != destino) {
            n = g.getVertices().getObject(next[n.getvID()-1][destino.getvID()-1]);
            System.out.print(n.getvID() + " ");
            path.add(n);
        }
        return path;
    }

    private static double[][] prepararMatriz(double matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    if (matrix[i][j] == 0) {
                        matrix[i][j] = (int) GOES_TO_INF;
                    }
                }
            }
        }
        return matrix;
    }
}
