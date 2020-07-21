import java.util.Arrays;
import java.util.Scanner;
/*
April 9th, 2020
Program by Josh Stevens
This program is a binary tree search that handles integer data.
Using helper methods that implement recursion, given integers will be
sorted into a tree format.
Helper methods:
boolean isBalancedRecursion(Node temp)
public void rebalanceTreeRecursion(int[] vals)
public String printInOrderRecursion(Node temp)
public String printPreOrderRecursion(Node temp)
public String printPostOrderRecursion(Node temp)
 */
public class BinaryTree implements BinTree {
    Node root;
    int count;
    private String output;
    // delete the smallest value in the tree
    public void removeMin() { //Get the smallest value and call the remove method to remove it
        Node min = getMin();
        remove(min.data);
    }

    // delete the largest value in the tree
    public void removeMax() { // get the largest value and call the remove method to remove it
        Node max = getMax();
        remove(max.data);
    }

    public Node getMin(Node node) {
        return null;
    }

    public Node getMin() { // this returns the smallest node,
        if (root.left == null && root.right == null) { //Follow the left side of every tree to find smallest value.
            return root;
        }
        Node temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }
    //Helper method which will deal with Test 3 in remove(removing something


    // remove the first instance of node that matches the key
    public void remove(int key) {
        //First check if it contains this said key
        if (contains(key)) {
            Node temp = root;
            //Test 1 root is null
            if (root == null) {
                return;
            }
            //Test 2 root is the item
            //There are 3 cases for the root as well as for none root nodes
            //Here the cases follow the order:
            /*
            Case 1 : No Children
            Case 2 : 1 Child left side
            Case 3 : 1 Child right side
            Case 4 : 2 Children
                Case 4 SubCase 1 : Left max = left
                Case 4 SubCase 2 : Left Max != left
             */
            else if (root.data == key) {
                if (root.right == null && root.left == null) {
                    root = null;
                    count--;
                    return;
                } else if (root.right == null && root.left != null) {
                    root = root.left;
                    count--;
                    return;
                } else if (root.right != null && root.left == null) {
                    root = root.right;
                    count--;
                    return;
                } else if (root.right != null && root.left != null) {
                    Node tempMax = getMax(root.left);
                    if (tempMax == root.left) {
                        root.left.right = root.right;
                        root = root.left;
                        count--;
                        return;
                    } else {
                        tempMax.right = root.right;
                        tempMax.left = root.left;
                        root = tempMax;
                        count--;
                        return;
                    }
                }
            }
            //Test 3 is where the root is not the item and we must now search the tree
            while (temp.left != null || temp.right != null) {

                if (temp.right != null) {
                    if (temp.right.data == key) {
                        if (temp.right.right == null && temp.right.left == null) { //First Case of Test 3 is where there are no children from the node we want to remove
                            temp.right = null;
                            count--;
                            return;
                        } else if ((temp.right.right != null && temp.right.left == null)) { // Second case of Test 3 is where there is only a right child
                            temp.right = temp.right.right;
                            count--;
                            return;
                        } else if ((temp.right.right == null && temp.right.left != null)) { // Second case of Test 3 is where there is only a left child
                            temp.right = temp.right.left;
                            count--;
                            return;
                        } else if (temp.right.right != null && temp.right.left != null) {// Third case of Test 3 is where there is 2 children
                            Node tempMax = getMax(temp.right.left);                     //Second check is required to see if the left max
                            if (tempMax == temp.right.left) {                           //Is the same as the straight left
                                temp.right.left.right = temp.right.right;
                                temp.right = temp.right.left;
                                count--;
                                return;
                            } else {
                                tempMax.right = temp.right.right;
                                tempMax.left = temp.right.left;
                                temp.right = tempMax;
                                count--;
                                return;
                            }
                        }
                    }
                }
                    if (temp.left != null) {
                        if (temp.left.data == key) {
                            if (temp.left.right == null && temp.left.left == null) { //First Case of Test 3 is where there are no children from the node we want to remove
                                temp.left = null;
                                count--;
                                return;
                            } else if ((temp.left.right != null && temp.left.left == null)) { // Second case of Test 3 is where there is only a right child
                                temp.left = temp.left.right;
                                count--;
                                return;
                            } else if ((temp.left.right == null && temp.left.left != null)) { // Second case of Test 3 is where there is only a left child
                                temp.left = temp.left.left;
                                count--;
                                return;
                            } else if (temp.left.right != null && temp.left.left != null) {// Third case of Test 3 is where there is 2 children
                                Node tempMax = getMax(temp.left.left); //In this case we have to do a third check where the left max is the same as the left
                                if (tempMax == temp.left.left) {        // Therefore we don't have to use the new tempmax
                                    temp.left.left.right = temp.left.right;
                                    temp.left = temp.left.left;
                                    count--;
                                    return;
                                } else {
                                    tempMax.right = temp.left.right;
                                    tempMax.left = temp.left.left;
                                    temp.left = tempMax;
                                    count--;
                                    return;
                                }
                            }
                        }
                    }
                if(key > temp.data){
                    temp = temp.right;
                } else if (key < temp.data){
                    temp = temp.left;
                }

            }
        } else {
            return;
        }
    }

    public Node getMax() { // Finds the max node from the entire tree
        if (root.left == null && root.right == null) { //Just keep going right from root until you no longer can
            return root;
        }
        Node temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    public Node getMax(Node node) { //Find the max node given a start node
        Node temp = node;       //Just keep going right from given node until you no longer can
        if (temp.right == null) {//This is primarily used for the remove method.
            return temp;
        } else {
            while (temp.right != null) {
                temp = temp.right;
            }
            return temp;
        }
    }
    boolean isBalancedRecursion(Node temp) //Helper method used to determine if the tree is balance using recursion
    {
        //If root is null then the tree will be balanced because there is no difference greater than 1
        if (temp == null) {
            return true;
        }
        int leftHeight = height(temp.left); // Get height of left side
        int rightHeight = height(temp.right); //Get height of right side
        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalancedRecursion(temp.left) && isBalancedRecursion(temp.right)) { //Calculate the difference
            return true;                                                                                                    //If less than or equal to 1 than is balanced.
        }
        //If above isn't true the tree must not be balanced
        return false;
    }

    public boolean isBalanced() { //Using the helper recursion method to determine if it's balanced Explination of that method above ^^^^^^^^^^
        return isBalancedRecursion(root);
    }

    //this is a helper method which uses recursion to create the balanced tree by taking the halfs of the lower and upper half of given integer array.
    public void rebalanceTreeRecursion(int[] vals){
        //inster the root which will be the middle value
       insert(vals[vals.length/2]);
       //if length is 2 or more than there is at least 1 child
       if(vals.length >= 2){
           rebalanceTreeRecursion(Arrays.copyOfRange(vals, 0 , vals.length/2));
       }
       //if length is 3 or more than there isat least 2 children
       if(vals.length >= 3){
           rebalanceTreeRecursion(Arrays.copyOfRange(vals, vals.length/2 + 1, vals.length));
       }

    }

    public void rebalanceTree() {
        //First check if balanced
        if(isBalanced()){
            return;
        }
        //Create a integer array the size of the amount of nodes present
        int[] sortednums = new int[count()];
        //Using a current to select certain parts of the SortedString
        String current = "";
        String SortedString = printInOrder();
        //Loop for as many times there are nodes
        for(int i = 0; i < count(); i++){
            SortedString = SortedString.trim(); //trimming gets rid of the spaces at the end
            if(i == count() - 1){  //checks if its the last node
                current = SortedString;
            } else {// if it's not than the current is just a cut off from the int to the first space
                current = SortedString.substring(0, SortedString.indexOf(" "));
            }
            sortednums[i] = Integer.parseInt(current); //add this integer to the array of ints
            if(i != count()-1){ // if we're not at the last int than shorten the SortedString
                SortedString = SortedString.substring(SortedString.indexOf(" "));
            }
        }
        //Clear to make our new tree
        clear();
        //Helper method explained above
        rebalanceTreeRecursion(sortednums);
    }

    public void mirror() { // This method just swaps the right side with the left side.
        Node temp = root.right;
        root.right = root.left;
        root.left = temp;

    }

    public int height() {
        return height(root);
    }

    public int height(Node node) {
        /* base case tree is empty */
        if (node == null)
            return 0;
        
        /* If tree is not empty then height = 1 + max of left
         height and right heights */
        return 1 + Math.max(height(node.left), height(node.right));
    }


    public void clear() {
        root = null;
    }

    public void insert(int newItem) {
        //First Test : Is the root null if so make the root equal to the newItem
        if (root == null) {
            root = new Node(newItem);
            count++;
        } else {
            //Second Test : Is the newItem greater or less than the root's data
            if (newItem > root.data && root.right == null) {
                root.right = new Node(newItem);
                count++;
                return;
            } else if (newItem < root.data && root.left == null) {
                root.left = new Node(newItem);
                count++;
                return;
            }
            Node temp = root;
            //Third Test : if the newItem is greater or less than the temp's data
            do { //Reason for do while instead of while is for the check to happen after the loop is processed
                if (newItem < temp.data && temp.left != null) { //Order of these if statements are very important because we must be traversing the tree before inserting.
                    temp = temp.left;
                } else if (newItem > temp.data && temp.right != null) {
                    temp = temp.right;
                }
                if (newItem > temp.data && temp.right == null) { //These two if statements create the new node depending on if it's greater or less than the temp.
                    temp.right = new Node(newItem);
                    count++;
                    return;
                } else if (newItem < temp.data && temp.left == null) {
                    temp.left = new Node(newItem);
                    count++;
                    return;
                }
            } while (temp.left != null || temp.right != null);
        }
    }

    public boolean contains(int item) {
        Node temp = root;
        //Test 1 root is null
        if (root == null) {
            return false;
        }
        //Test 2 root is the item
        else if (temp.data == item) {
            return true;
        }
        //Test 3 is where the root is not the item and we must now search the tree
        while (temp.left != null || temp.right != null) {
            if (item > temp.data && temp.right != null) {
                temp = temp.right;
            } else if (item < temp.data && temp.left != null) {
                temp = temp.left;
            }
            if (item > temp.data && temp.right == null) {
                return false;
            } else if (item < temp.data && temp.left == null) {
                return false;
            }
            if (temp.data == item) {
                return true;
            }
        }
        return false;

    }

    //Helper method using recursion to  create a string representation of the items listed from smallest to largest.
    public String printInOrderRecursion(Node temp) {
        if (temp == null) {
            return "";
        }
        return printInOrderRecursion(temp.left) + " " + temp.data + " " + printInOrderRecursion(temp.right);
    }
    //Helpper method using recursion to create a string representation of the items going from the root then left subtree then right subtree
    public String printPreOrderRecursion(Node temp) {
        if (temp == null) {
            return "";
        }
        return  temp.data + " " + printPreOrderRecursion(temp.left) + " " + printPreOrderRecursion(temp.right);
    }
    //Helper method using recursion to create a string representation of the items going from left subtree, right subtree than root
    public String printPostOrderRecursion(Node temp){
        if(temp == null){
            return "";
        }
        return printPostOrderRecursion(temp.left) + " " + printPostOrderRecursion(temp.right) + " " + temp.data;
    }
    //Printing from largest to smallest
    public String printInOrder(){
        return printInOrderRecursion(root);
    }
    //Printing from root to left subtree to right subtree
    public String printPreOrder(){
        return printPreOrderRecursion(root);
    }
    //Printing from left subtree to right subtree to root
    public String printPostOrder(){
        return printPostOrderRecursion(root);
    }

    public int count(){
        return count;
    }

} 