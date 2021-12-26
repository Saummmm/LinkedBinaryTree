/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

/**
 *
 * @author saum
 */
public interface Position<E> {
    E getElement() throws IllegalStateException; //Interface position used to create the node class
}
