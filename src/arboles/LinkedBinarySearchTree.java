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
 * @param <T>
 */
public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {

    }

    @Override
    public void inserta(T elem) {
        NodoBin<T> aInsertar = new NodoBin<T>(elem);
        if (cont != 0) {
            insertaR(aInsertar, raiz.der);
        } else {
            raiz.der = aInsertar;
            aInsertar.papa = raiz;
        }
        aInsertar.factorEquilibrio = 0;
        cont++;
    }

    private void insertaR(NodoBin<T> aInsertar, NodoBin<T> nodo) {
        if (aInsertar.elem.compareTo(nodo.elem) < 0) {
            if (nodo.izq != null) {
                insertaR(aInsertar, nodo.izq);
            } else {
                nodo.cuelga(aInsertar);
            }
        } else {
            if (nodo.der != null) {
                insertaR(aInsertar, nodo.der);
            } else {
                nodo.cuelga(aInsertar);
            }
        }
    }
    
    @Override
    public boolean contains(T elem) {
        if (cont == 0) {
            return false;
        }
        return containsR(elem, raiz.der);
    }

    private boolean containsR(T buscar, NodoBin<T> nodo) {
        if (nodo == null) {
            return false;
        }

        if (buscar.compareTo(nodo.elem) == 0) {
            return true;
        } else {
            if (nodo.izq == null && nodo.der == null) {
                return false;
            } else {
                if (buscar.compareTo(nodo.elem) < 0) {
                    return containsR(buscar, nodo.izq);
                } else {
                    return containsR(buscar, nodo.der);
                }
            }
        }
    }

    public NodoBin<T> encuentra(T elem) {
        if (cont == 0) {
            return null;
        }
        return encuentraR(raiz.der, elem);
    }

    private NodoBin<T> encuentraR(NodoBin<T> actual, T buscado) {
        if (actual == null) {
            return null;
        }
        if (buscado.compareTo(actual.elem) == 0) {
            return actual;
        } else {
            if (buscado.compareTo(actual.elem) < 0) {
                return encuentraR(actual.izq, buscado);
            } else {
                return encuentraR(actual.der, buscado);
            }
        }

    }

    @Override
    public void borra(T elem) {
        NodoBin<T> nodo = encuentra(elem);
        NodoBin<T> nextInOrder;
        if (nodo == null) {
            return;
        }
        if (nodo.izq != null && nodo.der != null) {
            nextInOrder = siguienteInOrden(nodo);
            nodo.elem=nextInOrder.elem;
            if (nextInOrder.izq == null && nextInOrder.der == null) {
                eliminaHoja(nextInOrder);
            } else {
                eliminaConUnHijo(nextInOrder);
            }
        } else {
            if (nodo.izq == null && nodo.der == null) {
                eliminaHoja(nodo);
            } else {
                eliminaConUnHijo(nodo);
            }
        }
        cont--;
    }

    public void eliminaConUnHijo(NodoBin<T> nodo) {
        NodoBin<T> hijo;
        if (nodo.papa.elem == null && nodo.papa.izq == null) {
            if (nodo.izq != null) {
                hijo = nodo.izq;
                hijo.papa = nodo.papa;
                nodo.papa.der = hijo;
            } else {
                hijo = nodo.der;
                hijo.papa = nodo.papa;
                nodo.papa.der = hijo;
            }
        } else {
            if (nodo.izq != null) {
                hijo = nodo.izq;
            } else {
                hijo = nodo.der;
            }
            nodo.papa.cuelga(hijo);

        }
        nodo = null;
    }

    public void eliminaHoja(NodoBin<T> nodo) {
        if (nodo.papa.izq == nodo) {
            nodo.papa.izq = null;
            nodo.papa = null;
            nodo = null;
        } else {
            nodo.papa.der = null;
            nodo.papa = null;
            nodo = null;
        }
    }

    public NodoBin<T> siguienteInOrden(NodoBin<T> nodo) {
        NodoBin<T> actual = nodo.der;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

    @Override
    public T borraMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T borraMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T encuentraMin() {
        if (raiz == null) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        NodoBin<T> actual = raiz;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual.elem;
    }

    @Override
    public T encuentraMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float sacarMediana() throws Exception {
        float mediana = 0;
        if (cont > 0) {
            if (cont % 2 == 0) {
                mediana = sacarMedianaR(0, raiz.der, (cont + 1) / 2);
                mediana += sacarMedianaR(0, raiz.der, (cont - 1) / 2);
                mediana = mediana / 2;
            } else {
                mediana = sacarMedianaR(0, raiz.der, cont / 2);
            }
        } else {
            throw new Exception("Esto esta vacio");
        }
        return mediana;
    }

    private float sacarMedianaR(int actual, NodoBin nodo, int mediana) {
        float med;

        if (actual == mediana) {
            return (int) nodo.elem;
        }

        med = sacarMedianaR(actual - 1, nodo.izq, mediana);
        med = sacarMedianaR(actual + 1, nodo.der, mediana);
        return 0;
    }

        //codigo print2DUtil modificado a partir de https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
    public void print2DUtil(NodoBin<T> nodo, int tam) {
        // Base case  
        if (nodo == null) {
            return;
        }

        // Increase distance between levels  
        tam += cont;

        // Process right child first  
        print2DUtil(nodo.der, tam);

        // Print current node after space  
        // count  
        System.out.print("\n");
        for (int i = cont; i < tam; i++) {
            System.out.print(" ");
        }
        System.out.print(nodo.elem + " (eq " + nodo.factorEquilibrio + ")\n");

        // Process left child  
        print2DUtil(nodo.izq, tam);
    }

// Wrapper over print2DUtil()  
    public void print2D(NodoBin<T> root) {
        // Pass initial space count as 0  
        print2DUtil(root, 0);
    }
    
}
