/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.Iterator;

/**
 *
 * @author saum
 */
public interface Tree<E> extends Iterable<E> { //another interface, but this time for the general tree class
    Position<E> root(); 
    Position<E> parent(Position<E> p ) throws IllegalArgumentException;
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    int numChildren(Position<E> p) throws IllegalArgumentException;
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    int size();
    boolean isEmpty();
    @Override
    Iterator<E> iterator();
    Iterable<Position<E>> positions();
}
