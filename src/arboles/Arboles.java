/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.*;

/**
 *
 * @author rodri

 */
public class Arboles<T extends Comparable<T>> {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //10,5,4,8,7,9,12,11,13
        //22,104,2,5,52,3,9,12,23,4,6,7,8,33,6,1,100,70
        AVLTree arbol = new AVLTree();
        //LinkedBinarySearchTree arbol = new LinkedBinarySearchTree();
        Integer[] arre = {22,23,24,25,26,27};
        for (Integer arre1 : arre) {
            arbol.inserta(arre1);
        }
        arbol.print2D(arbol.raiz);
        arbol.borra(104);
        arbol.print2D(arbol.raiz);
        arbol.borra(52);
        System.out.println("- - - - - - ->"+arbol.cont);
        arbol.print2D(arbol.raiz);
    }

}
