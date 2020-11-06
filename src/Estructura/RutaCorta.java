/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

/**
 *
 * @author Minassuo
 */
public class RutaCorta {
    /**
     * Número de vertices
     */
    int numVer;
    /**
     * Grafo a evaluar
     */
    Grafo grafo;
    private int distMin(double[][] mat, boolean arbMin[], int tam, int ind, double[][] arbMinGraf){
        double min = Integer.MAX_VALUE;
        int indicemin = -1;
        for (int i = 0; i < tam; i++) {
            if (mat[ind][i]< min && arbMin[ind]==false) {
                min = mat[ind][i];
                indicemin = i;
                arbMinGraf[ind][i] = mat[ind][i];
                arbMinGraf[i][ind] = mat[ind][i];
            }
        }
        return indicemin;
    }
    
    
    private void getRutaCorta(double[][] mat, int origen, int destino, int tam, double dist){
        boolean[] arbMin = new boolean[tam];
        String path = "";
        double min = Integer.MAX_VALUE;
        double[][] arbMinGraf = new double[tam][tam]; // grafo arb min de exp
        
        //inicializa el vector bool del arbol min de expansion y su matriz de pesos
        for (int i = 0; i < tam; i++) {
            arbMin[i] = false;
            for (int j = 0; j < tam; j++) {
                arbMinGraf[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // arbol min de exp
        for (int i = 0; i < tam-1; i++) {
            int u = distMin(mat, arbMin, origen, destino, arbMinGraf);
            arbMin[u] = true;
        }
        if (arbMinGraf[origen][destino]!= 0 && arbMinGraf[origen][destino]!=Integer.MAX_VALUE) {
            dist = arbMinGraf[origen][destino];
        } else {
            dist = 0;
        }
        
    }
    
}
