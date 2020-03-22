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
public interface BinaryTreeADT<T extends Comparable <T>> {
    public boolean isEmpty();
    public int size();
    public boolean contains(T elem);
    public T find(T elem);
    public Iterator<T> inOrden();
    public Iterator<T> postOrden();
    public Iterator<T> preOrden();
    public Iterator<T> levelOrden();
}
