/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author saum
 * @param <E>
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    //nested node class
    protected static class Node<E> implements Position<E> {
        //encapsulated fields
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        //constructor, sets all the ecapsulated fields
        public Node(E e, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
        //setters and getters
        @Override
        public E getElement() throws IllegalStateException {
            return this.element;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public Node<E> getLeft() {
            return this.left;
        }

        public Node<E> getRight() {
            return this.right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }
    //general create node method, used for further update methods later
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }
    
    //encapsulated fields for LinkedBinaryTree class
    protected Node<E> root = null;
    private int size = 0;
    private Comparator<E> comp = new DefaultComparator();
    
    //constructor
    public LinkedBinaryTree() {
    }
    
    //converts elements of the Position type to a Node
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Not valid position type");
        }
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) {
            throw new IllegalArgumentException("p is no longer in the treee");
        }
        return node;
    }
    
    //getters
    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }
    
    //retruns the parent of a position
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }
    
    //returns the left node of a position
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }
    
    //returns the right node of a position
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }
    
    //adds a root to the tree
    public Position<E> addRoot(E e) throws IllegalArgumentException {
        if (!isEmpty()) { //if tree is not empty, there already is a root
            throw new IllegalStateException("Tree is not empty");
        }
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }   
    
    //add an element to the left leaf of a node
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) { //if the left node is not null, it is occupied
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }
    
    //add an element to the right leaf of a node
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) { //if the right node is not null, it is occupied
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }
    
    //sets an existing node to have an element
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    
    //removes a node
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) { //if a node has two children, it cannot be removed
            throw new IllegalArgumentException("p has two children");
        }
        //
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) {
            child.setParent(node.getParent()); //the grandparent becomes the child node's parent
        }
        if (node == root) {
            root = child; //child becomes root if the main node was the root
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child); //setting the child node on the correct node side of the grandparent
            } else {
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();
        //removes node from the tree
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(null);
        return temp;
    }

    public void add(E e) {
        if (isEmpty()) { //if the node is empty, the add function should create a root
            addRoot(e);
        } else {
            Node<E> node = validate(root());
            while (isInternal(node)) { //loop while the node is internal
                int x = comp.compare(e, node.getElement());
                if (numChildren(node) == 1) { //if the node has one child
                    if (x > 0) { //if e is greater than the node element, we will be inserting to the right
                        if (node.getRight() == null) {
                            break; //if the node is null on the right, we need to create a node there
                        } else {
                            node = node.getRight(); //otherwise advance to the right node
                        }
                    } else { //e is less than or equal to element in the current node
                        if (node.getLeft() == null) {
                            break; //if there is no node on the left, craete one
                        } else {
                            node = node.getLeft(); //otherwise advance to the next node
                        }
                    }
                } else { //if there are two children we need to find which one works
                    if (node.getRight() != null && comp.compare(e, node.getElement()) > 0) {
                        node = node.getRight(); //if e is greater than node element, advance to the right node
                    } else if (node.getLeft() != null && comp.compare(e, node.getElement()) <= 0) {
                        node = node.getLeft();  //else advance to left node
                    }
                }
            }
            if (node.getRight() == null && comp.compare(e, node.getElement()) > 0) {
                addRight(node, e); //add to the right node
            } else {
                addLeft(node, e); //add to the left node
            }
        }
    }
    
    public boolean find(E e) {
        return findNext(root(), e);
    }
    //recursive find finction
    public boolean findNext(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        int x = comp.compare(e, node.getElement());
        if (x == 0)
            return true;
        else if (x > 0 && node.getRight() != null)
            //if node on right exists and e is greater than the current element, go to the right node
            return findNext(node.getRight(), e);
        else if (x < 0 && node.getLeft() != null)
            //if node on left exists and e is less than current element, go to left node
            return findNext(node.getLeft(), e);
        else
            //element is not part of the tree
            return false;
    }

    public String inOrder() {
        String s = "";
        return inOrderTraversal(root, s);
    }
    //recursive call function
    public String inOrderTraversal(Node<E> node, String s) {
        //if node on left exists, advance to there
        if (node.getLeft() != null) {
            s = inOrderTraversal(node.getLeft(), s);
        }
        //add current element to the String
        s = s + String.valueOf(node.getElement()) + ", ";
        //if node on right exists, advance there
        if (node.getRight() != null) {
            s = inOrderTraversal(node.getRight(), s);
        } //return the string so far
        return s;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Position<E>> positions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
