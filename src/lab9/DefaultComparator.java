/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.Comparator;

/**
 *
 * @author saum
 */
//default comparator for generics, I used generics instead of integers
public class DefaultComparator<E> implements Comparator<E> {
    @Override
    public int compare(E a, E b) throws ClassCastException{
        return ((Comparable<E>)a).compareTo(b);
    }
}

    
