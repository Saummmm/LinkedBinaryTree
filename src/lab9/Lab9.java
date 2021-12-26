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
public class Lab9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedBinaryTree tree = new LinkedBinaryTree(); //creates object, binary tree
        int[] arr = {5, 4, 20, 15, 3, 1, 50, 7, 9}; //array for easier method to add the numbers to the binary tree
        for (int i : arr) { //for loop to add numbers to tree
            tree.add(i);
        }
        System.out.println(tree.inOrder()); //inorder traversal
        System.out.println(tree.find(7)); //--> true
        System.out.println(tree.find(10));//--> false
    }

}
