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
public class NodoBin<T extends Comparable<T>> {

    T elem;
    NodoBin<T> izq, der, papa;
    int factorEquilibrio;

    public NodoBin(T e) {
        elem = e;
        izq = null;
        der = null;
        factorEquilibrio = 0;
    }

    public int numDescendientes() {
        int total = 0;
        if (izq == null && der == null) {
            return total;
        }

        if (izq != null) {
            total = numDescR(izq, total);
        }

        if (der != null) {
            total += numDescR(der, total);
        }

        return total;
    }

    private int numDescR(NodoBin nodo, int cont) {
        if (izq == null && der == null) {
            return cont;
        }
        if (izq != null) {
            numDescR(izq, ++cont);
        }
        if (der != null) {
            numDescR(der, ++cont);
        }
        return cont;
    }

    public int numDescendientes2() {
        int res = 0;
        if (izq != null) {
            res = izq.numDescendientes2();
        }
        if (der != null) {
            res += der.numDescendientes2();
        }
        return res;
    }

    //Le das un elemento y lo cuelga donde corresponda del nodo
    public void cuelga(T cosa) {
        if (cosa == null) {
            return;
        }

        NodoBin<T> nodo = new NodoBin<T>(elem);
        if (cosa.compareTo(elem) < 0) {
            izq = nodo;
        } else {
            der = nodo;
        }
        nodo.papa = (this);
    }

    public void cuelga(NodoBin<T> nuevoHijo) {
        if (nuevoHijo == null) {
            return;
        }

        if (nuevoHijo.elem.compareTo(elem) < 0) {
            izq = nuevoHijo;
        } else {
            der = nuevoHijo;
        }
        nuevoHijo.papa = this;
    }

    public int altura() {
        if (izq != null && der != null) {
            NodoBin<T> a = this;
            return alturaR(a);
        } else {
            return 1;
        }
    }

    private int alturaR(NodoBin<T> raiz) {
        if (raiz == null) {
            return 1;
        }

        int alturaIzq = alturaR(raiz.izq);
        int alturaDer = alturaR(raiz.der);

        if (alturaIzq > alturaDer) {
            return (alturaIzq + 1);
        } else {
            return (alturaDer + 1);
        }
    }

    public int calculaNivel() {
        if (this == null) {
            return 0;
        }
        int izqN, derN = 0;
        if (izq == null) {
            izqN = 0;
        } else {
            izqN = izq.calculaNivel();
        }
        if (der == null) {
            derN = 0;
        } else {
            derN = der.calculaNivel();
        }
        int res;
        if(izqN < derN){
            res = derN;
        }else{
            res=izqN;
        }
        return res + 1;
    }
    
     public int calcularFactorDeEquilibrio(){
        int d, i;
        if (der == null)
            d = 0;
        else
            d = der.calculaNivel();
        if (izq == null)
            i = 0;
        else
            i = izq.calculaNivel();
        factorEquilibrio = d - i;
        return factorEquilibrio;
            
    }
    /*
    public void calcularFactorDeEquilibrio() {
        int aDer = 0, aIzq = 0;
        if (der != null) {
            aDer = der.altura();
        }
        if (izq != null) {
            aIzq = izq.altura();
        }
        //System.out.println("El valor del nodo " + elem + " la altura se su hijo derecho: " + aDer + " la altura de su hijo izquierdo: " + aIzq);
        factorEquilibrio = aDer - aIzq;
        //factorEquilibrio=12234;
    }
*/
}
