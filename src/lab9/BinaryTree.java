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
public interface BinaryTree<E> extends Tree<E>{ //binary tree interface
    Position<E> left(Position<E> p) throws IllegalArgumentException; //left sibling in binary tree
    Position<E> right(Position<E> p) throws IllegalArgumentException; //right sibling in binary tree
    Position<E> sibiling(Position<E> p) throws IllegalArgumentException; //sibiling in binary tree
}
