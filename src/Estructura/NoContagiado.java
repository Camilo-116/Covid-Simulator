/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

/**
 *
 * @author Camilo Cespedes
 */
public class NoContagiado extends Vertice{
    
    public NoContagiado(Vertice vertice) {
        super(vertice.getAristas(), vertice.getvID(), vertice.isMask(), vertice.isContagiado());
    }
    
}
