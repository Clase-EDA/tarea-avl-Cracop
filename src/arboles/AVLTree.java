/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author rodri
 */
public class AVLTree<T extends Comparable<T>> extends LinkedBinarySearchTree<T> {

    public AVLTree() {
        super();
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
        viajarARaizDIR(aInsertar);
        CalculaTodo(raiz.der);
        balanceaTodo();
    }

    private void viajarARaizDIR(NodoBin<T> actual) {
        if (actual == raiz || actual == null) {
            return;
        }
        if (actual.papa.izq == actual) {
            actual.papa.factorEquilibrio--;
        } else {
            actual.papa.factorEquilibrio++;
        }

        if (actual.papa.factorEquilibrio == 0) {
            return;
        }
        if (actual.factorEquilibrio < -1 || 1 < actual.factorEquilibrio) {
            actual = balancear(actual);
            actual.calcularFactorDeEquilibrio();

        }
        viajarARaizDIR(actual.papa);

    }

    private NodoBin<T> balancear(NodoBin<T> nodoEmproblemado) {
        NodoBin<T> alfa = nodoEmproblemado;
        if (alfa == null) {
            return alfa;
        }
        if (alfa.factorEquilibrio == -2) {
            if (alfa.izq != null && alfa.izq.factorEquilibrio == -1 /*|| alfa.izq.factorEquilibrio == 0)*/) {
                //izq - izq
                return rotarIzqIzq(alfa);
            } else {
                //izq - der
                return rotarIzqDer(alfa);
            }
        } else {
            if (alfa.der != null && alfa.der.factorEquilibrio == 1 /*|| alfa.der.factorEquilibrio == 0)*/) {
                //der - der
                return rotarDerDer(alfa);
            } else {
                //der - izq
                return rotarDerIzq(alfa);
            }
        }

    }

    private NodoBin<T> rotarDerIzq(NodoBin<T> alfa) {
        NodoBin<T> papa, beta, gamma, A, B, C, D;
        papa = alfa.papa;
        beta = alfa.der;
        gamma = beta.izq;
        A = alfa.izq;
        B = gamma.izq;
        C = gamma.der;
        D = beta.der;

        gamma.cuelga(alfa);
        gamma.cuelga(beta);
        if (A == null) {
            alfa.izq = A;
        } else {
            alfa.cuelga(A);
        }
        if (B == null) {
            alfa.der = B;
        } else {
            alfa.cuelga(B);
        }
        if (C == null) {
            beta.izq = C;
        } else {
            beta.cuelga(C);
        }
        if (D == null) {
            beta.der = D;
        } else {
            beta.cuelga(D);
        }
        if (papa == raiz) {
            gamma.papa = raiz;
            papa.der = gamma;
        } else {
            papa.cuelga(gamma);
        }

        alfa.calcularFactorDeEquilibrio();
        beta.calcularFactorDeEquilibrio();
        gamma.calcularFactorDeEquilibrio();
        return gamma;
    }

    private NodoBin<T> rotarDerDer(NodoBin<T> alfa) {
        NodoBin<T> papa, beta, gamma, A, B, C, D;
        papa = alfa.papa;
        beta = alfa.der;
        gamma = beta.der;
        A = alfa.izq;
        B = beta.izq;
        C = gamma.izq;
        D = gamma.der;

        beta.cuelga(alfa);
        if (A == null) {
            alfa.der = A;
        } else {
            alfa.cuelga(A);
        }
        if (B == null) {
            alfa.izq = B;
        } else {
            alfa.cuelga(B);
        }
        if (C == null) {
            gamma.izq = C;
        } else {
            gamma.cuelga(C);
        }
        if (D == null) {
            gamma.der = D;
        } else {
            gamma.cuelga(D);
        }

        if (papa == raiz) {
            beta.papa = raiz;
            papa.der = beta;
        } else {
            papa.cuelga(beta);
        }

        alfa.calcularFactorDeEquilibrio();
        beta.calcularFactorDeEquilibrio();
        gamma.calcularFactorDeEquilibrio();
        return alfa;
    }

    private NodoBin<T> rotarIzqDer(NodoBin<T> alfa) {
        NodoBin<T> papa, beta, gamma, A, B, C, D;
        papa = alfa.papa;
        beta = alfa.izq;
        gamma = beta.der;
        A = beta.izq;
        B = gamma.izq;
        C = gamma.der;
        D = alfa.der;

        gamma.cuelga(beta);
        gamma.cuelga(alfa);
        if (A == null) {
            beta.izq = A;
        } else {
            beta.cuelga(A);
        }
        if (B == null) {
            beta.der = B;
        } else {
            beta.cuelga(B);
        }
        if (C == null) {
            alfa.izq = C;
        } else {
            alfa.cuelga(C);
        }
        if (D == null) {
            alfa.der = D;
        } else {
            alfa.cuelga(D);
        }

        if (papa == raiz) {
            gamma.papa = raiz;
            papa.der = gamma;
        } else {
            papa.cuelga(gamma);
        }

        alfa.calcularFactorDeEquilibrio();
        beta.calcularFactorDeEquilibrio();
        gamma.calcularFactorDeEquilibrio();
        return gamma;
    }

    private NodoBin<T> rotarIzqIzq(NodoBin<T> alfa) {
        NodoBin<T> papa, beta, gamma, A, B, C, D;
        papa = alfa.papa;
        beta = alfa.izq;
        gamma = beta.izq;
        A = gamma.izq;
        B = gamma.der;
        C = beta.der;
        D = alfa.der;

        beta.cuelga(alfa);
        beta.cuelga(gamma);
        if (A == null) {
            gamma.izq = A;
        } else {
            gamma.cuelga(A);
        }
        if (B == null) {
            gamma.der = B;
        } else {
            gamma.cuelga(B);
        }
        if (C == null) {
            alfa.izq = C;
        } else {
            gamma.cuelga(C);
        }
        if (D == null) {
            gamma.der = D;
        } else {
            gamma.cuelga(D);
        }

        if (papa == raiz) {
            beta.papa = raiz;
            papa.der = beta;
        } else {
            papa.cuelga(beta);
        }

        alfa.calcularFactorDeEquilibrio();
        beta.calcularFactorDeEquilibrio();
        gamma.calcularFactorDeEquilibrio();
        return beta;
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
    public void borra(T elem) {
        NodoBin<T> nodo = encuentra(elem);
        if(nodo == null){
            return;
        }
        NodoBin<T> nextInOrder;
        NodoBin<T> papa = nodo.papa;
        
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
        papa.calcularFactorDeEquilibrio();
        viajarARaizDBR(papa);
        CalculaTodo(raiz.der);
        balanceaTodo();
        cont--;
    }
    
    private void viajarARaizDBR(NodoBin<T> actual) {
        if (actual == raiz || actual == null) {
            return;
        }
        if (actual.papa.izq == actual) {
            actual.papa.factorEquilibrio++;
        } else {
            actual.papa.factorEquilibrio--;
        }

        if (actual.papa.factorEquilibrio == 0) {
            return;
        }
        if (actual.factorEquilibrio < -1 || 1 < actual.factorEquilibrio) {
            actual = balancear(actual);
            actual.calcularFactorDeEquilibrio();

        }
        viajarARaizDIR(actual.papa);

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
        nodo.calcularFactorDeEquilibrio();
        System.out.print(nodo.elem + " (eq " + nodo.factorEquilibrio + ")\n");

        // Process left child  
        print2DUtil(nodo.izq, tam);
    }

// Wrapper over print2DUtil()  
    public void print2D(NodoBin<T> root) {
        // Pass initial space count as 0  
        print2DUtil(root, 0);
    }

    private void CalculaTodo(NodoBin<T> actual) {
        if (actual == null) {
            return;
        }
        CalculaTodo(actual.izq);
        actual.calcularFactorDeEquilibrio();
        CalculaTodo(actual.der);
    }
    
    private void balanceaTodo(){
        NodoBin<T> p = raiz.der,i,d;
        if (p != null){
            i = p.izq;
            d = p.der;
        }//if
        else
            return;
        if (Math.abs(p.calcularFactorDeEquilibrio()) == 2)
            balancear(p);
        balanceaRTodo(i);
        balanceaRTodo(d);
    }
    
    private void balanceaRTodo(NodoBin<T> p){
        NodoBin<T>i,d;
        if (p != null){
            i = p.izq;
            d = p.der;
        }//if
        else
            return;
        if (Math.abs(p.calcularFactorDeEquilibrio()) == 2)
            balancear(p);
        balanceaRTodo(i);
        balanceaRTodo(d);
    }
}

