// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) throws Exception {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() throws Exception {
        if (isEmpty())
            throw new Exception();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax() throws Exception {
        if (isEmpty())
            throw new Exception();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) throws Exception {
        if (t == null)
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) throws Exception {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }

    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;

    private int nodeCount() {
        return nodeCount(root);
    }
    //recursively counts nodes
    private int nodeCount(BinaryNode<AnyType> t) {
        // If tree is empty
        if (t == null)
            // Return null
            return 0;
        else
            // Add 1 and the number of nodes in left and right subtrees
            return (nodeCount(t.left) + 1 + nodeCount(t.right));
    }

    public boolean isFull() {
        return isFull(this.root);
    }
    //recursively checks if the tree has every node as either a leaf or a parent with two children
    private boolean isFull(BinaryNode<AnyType> node) {
        if ((node.left == null || node.right == null && node.left != null || node.right != null))
            return false;
        else if (node.left == null && node.right == null)
            return true;
        else
            return isFull(node.left) && isFull(node.right);
    }
    //compares the root nodes before recursive call
    public boolean compareStructure(BinarySearchTree<AnyType> t2) {
        BinaryNode<AnyType> node = root;
        BinaryNode<AnyType> node2 = t2.root;
        return compareStructure(node, node2);
    }
    //recursively compares the left and right nodes of the first and second tree
    private boolean compareStructure(BinaryNode<AnyType> node, BinaryNode<AnyType> node2) {
        if (node == null && node2 == null)
            return true;
        else if (node == null || node2 == null)
            return false;
        else {
            return compareStructure(node.left, node2.left) && compareStructure(node.right, node2.right);
        }
    }
    //checks if roots are equal before recursive call
    public boolean equals(BinarySearchTree<AnyType> t2) {
        BinaryNode<AnyType> node =root;
        BinaryNode<AnyType> node2= t2.root;
        return equals(node,node2);
    }
    //recursively checks if the element of the left and right nodes are the same
    private boolean equals(BinaryNode<AnyType> node, BinaryNode<AnyType> node2) {
        if(node ==null && node2==null)
            return true;
        else if((node ==null || node2==null) || (node.element!=node2.element)) {
            return false;
        }else {
            return equals(node.left,node2.left) && equals(node.right,node2.right);
        }
    }
    //copies elements of the first tree into the new tree
    public BinarySearchTree<AnyType> copy() {
        BinarySearchTree<AnyType> newT = new BinarySearchTree<>();
        newT.root = copy(root);
        return newT;
    }

    // returns a copy of the original tree
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t) {
        if (t != null) {
            BinaryNode<AnyType> newNode = new BinaryNode<AnyType> (t.element, null,null);
            newNode.left = copy(t.left);
            newNode.right = copy(t.right);
            return new BinaryNode<AnyType>(t.element, t.left, t.right);
        }
        return null;
    }
    //Creates a new tree that is a mirror of the original
    public BinarySearchTree<AnyType> mirror() {
        BinarySearchTree<AnyType> newTree = new BinarySearchTree<AnyType>();
        newTree.root = mirror(root);
        return newTree;
    }
    //returns a mirror copy of the original tree
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t) {
        if (t != null) {
            BinaryNode<AnyType> newT = new BinaryNode<AnyType>(t.element, null, null);
            newT.left = mirror(t.right);
            newT.right = mirror(t.left);
            return newT;
        }
        return null;
    }

    public boolean isMirror(BinarySearchTree<AnyType> t) {
        return isMirror(root, t.root);
    }
    //checks if the two trees are mirrors of each other
    private boolean isMirror(BinaryNode<AnyType> t, BinaryNode<AnyType> mirrorT) {
        if (t != null && mirrorT != null) {
            if (t.element.equals(mirrorT.element))
                return (isMirror(t.left, mirrorT.right) && isMirror(t.right, mirrorT.left));
            else
                return false;
        } else if (t == null && mirrorT == null) {
            return true;
        }
        return false;
    }

    void print() {
        // Create linked list queue
        Queue<BinaryNode> q = new LinkedList<BinaryNode>();
        // Add the root q to queue
        q.add(root);

        while (!q.isEmpty()) {
            BinaryNode temp = q.poll();
            System.out.print(temp.element + " ");
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
    }
    //printing level by level
    void printLevels() {
        if(root == null)
            return;

        Queue<BinaryNode<AnyType>> q =new LinkedList<>();
        q.add(root);

        while(true) {
            int nodeCount = q.size();
            if(nodeCount == 0)
                break;

            while(nodeCount > 0) {
                BinaryNode<AnyType> node = q.peek();
                System.out.print(node.element + " ");
                q.remove();
                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
            System.out.println();
        }
    }

    // Test program
    public static void main( String [ ] args ) throws Exception
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree();
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
            if( !t.contains( i ) )
                System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
        //used to demonstrate when not same structure or equal
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        t2.insert(5);
        t2.insert(10);
        t2.insert(25);
        t2.insert(50);
        t2.insert(75);
        t2.insert(100);


        System.out.println("Number of nodes for first tree: " + t.nodeCount());
        System.out.println("Number of nodes for second tree: " + t2.nodeCount());
        System.out.print("Printing first tree: ");
        t.print();
        System.out.println();
        System.out.print("Printing second tree: ");
        t2.print();
        System.out.println();
        if(t.isFull()) {
            System.out.println("The tree is full.");
        } else if(!t.isFull()){
            System.out.println("The tree is not full.");
        }

        if(t.compareStructure(t2)) {
            System.out.println("First tree and second tree are the same.");
        } else
            System.out.println("First tree and second tree are not the same.");

        if(t.equals(t2)) {
            System.out.println("First tree and second tree are equal.\n");
        } else
            System.out.println("First tree and second tree are not equal.\n");

        System.out.println("Copying the first tree into a new tree.");
        BinarySearchTree<Integer> newT = t.copy();
        System.out.print("Printing first tree: ");
        t.print();
        System.out.println();
        System.out.print("Printing new tree: ");
        newT.print();
        System.out.println();

        if(t.compareStructure(newT)) {
            System.out.println("First tree and new tree are the same.");
        } else
            System.out.println("First tree and new tree are not the same.");

        if(t.equals(newT)) {
            System.out.println("First tree and new tree are equal.\n");
        } else
            System.out.println("First tree and new tree are not equal.\n");

        System.out.print("Creating a mirror of the tree");
        BinarySearchTree<Integer> mirrorT = t.mirror();
        if(t.isMirror(mirrorT)) {
            System.out.println("First tree and new tree are mirrored.");
        } else
            System.out.println("First tree and new tree are not mirrored.");

        System.out.print("Printing first tree: ");
        t.print();
        System.out.println();
        System.out.print("Printing mirrored tree: ");
        mirrorT.print();
        System.out.println();

        System.out.println("Printing level by level: ");
        t.printLevels();



    }
}