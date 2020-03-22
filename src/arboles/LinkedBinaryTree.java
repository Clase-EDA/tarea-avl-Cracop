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
public class LinkedBinaryTree<T extends Comparable<T>> implements BinaryTreeADT<T> {

    NodoBin<T> raiz;
    int cont;

    LinkedBinaryTree() {
        raiz = new NodoBin(null);
    }

    @Override
    public boolean isEmpty() {
        return cont != 0;
    }

    @Override
    public int size() {
        return cont;
    }

    @Override
    public boolean contains(T elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T find(T elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> inOrden() {
        ArrayList<T> lista = new ArrayList();
        inOrdenR(raiz.der, lista);

        return lista.iterator();
    }

    private void inOrdenR(NodoBin<T> actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        inOrdenR(actual.izq, lista);
        lista.add(actual.elem);
        inOrdenR(actual.der, lista);
    }

    @Override
    public Iterator<T> postOrden() {
        ArrayList<T> lista = new ArrayList();
        postOrdenR(raiz.der, lista);
        return lista.iterator();
    }

    private void postOrdenR(NodoBin<T> actual, ArrayList<T> lista) {
        if (actual == null);
        lista.add(actual.elem);
        preOrdenR(actual.izq, lista);
        preOrdenR(actual.der, lista);
    }

    @Override
    public Iterator<T> preOrden() {
        ArrayList<T> lista = new ArrayList();
        preOrdenR(raiz.der, lista);
        return lista.iterator();
    }

    private void preOrdenR(NodoBin<T> actual, ArrayList<T> lista) {
        if (actual == null) {
            return;
        }
        lista.add(actual.elem);
        preOrdenR(actual.izq, lista);
        preOrdenR(actual.der, lista);
    }

    @Override
    public Iterator<T> levelOrden() {
        ArrayList<T> lista = new ArrayList();
        levelOrdenR(raiz.der, lista);
        return lista.iterator();
    }

    private void levelOrdenR(NodoBin<T> actual, ArrayList<T> lista) {
        Queue<NodoBin> queue = new LinkedList<NodoBin>();
        queue.add(actual);
        while (!queue.isEmpty()) {
            NodoBin<T> tempNode = queue.remove();
            lista.add(tempNode.elem);

            /*Enqueue left child */
            if (tempNode.izq != null) {
                queue.add(tempNode.izq);
            }

            /*Enqueue right child */
            if (tempNode.der != null) {
                queue.add(tempNode.der);
            }
        }
    }

}
