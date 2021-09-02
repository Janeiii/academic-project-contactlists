/*
 * Name: Janet Lin
 * PID:  A16686792
 */

import java.util.*;

public class ContactList {
	
	// Add instance variables here
    private BSTree<String> tree;
    private HashMap<String, Person> map;
    private int nContacts;

    public ContactList() {
        this.tree = new BSTree<>();
        this.map = new HashMap<String, Person>();
        this.nContacts = 0;
    }
	
    public boolean createContact(Person person) {
        if (lookupContact(person.getName()))
            return false;
        else {
            this.map.put(person.getName(), person);
            this.nContacts++;
            return true;
        }
    }

    public boolean lookupContact(String name) {
        return this.map.containsKey(name);
    }

    public Person getContact(String name) {

        if (this.map.containsKey(name)) {
            return this.map.get(name);
        }
        return null;
    }

    public Person[] getContactByRange(String start, String end) {
        if(start == null || end == null || start.compareTo(end) < 0){
            throw new IllegalArgumentException();
        }
        ArrayList<Person> list = new ArrayList<>();
        for (String i : this.map.keySet()) {
            if(start.compareTo(i) <= 0 &&end.compareTo(i) < 0){
                list.add(this.map.get(i));
            }
        }
        return list.toArray(new Person[list.size()]);
    }

    public boolean deleteContact(String name) {
        if (!this.map.containsKey(name)) {
            return false;
        }

        this.map.remove(name);
        this.nContacts--;
        return true;
    }

    public int size() {
        return this.nContacts;
    }

    public String[] fetchAllNames() {
        String[] ans = new String[this.size()];
        int count = 0;
        TreeMap<String, Person> sorted = new TreeMap<>(this.map);
        for (Map.Entry<String, Person> entry : sorted.entrySet())
            ans[count++] = entry.getKey();
        return ans;
    }

    public String[] fetchAllPhoneNumbers() {
        BSTree<String> allPhone = new BSTree<>();
        ArrayList<String> temp = new ArrayList<>();
        for (Person i : this.map.values()) {
            for (String phone: i.getPhoneNumbers()) {
                allPhone.insert(phone);
            }
        }
        temp = allPhone.printInOrder();
        String[] ans = temp.toArray(new String[temp.size()]);
        return ans;
    }
    public class BSTree<T extends Comparable<? super T>> implements Iterable {

        /* * * * * BST Instance Variables * * * * */

        private int nelems; // number of elements stored
        private BSTNode root; // reference to root node
        private String postOrderString;
        private String preOrderString;
        private String inOrderString;
        private ArrayList<T> order;


        /* * * * * BST Node Inner Class * * * * */

        protected class BSTNode {

            T key;
            BSTNode left;
            BSTNode right;

            /**
             * A constructor that initializes the BSTNode instance variables.
             *
             * @param left  Left child
             * @param right Right child
             * @param key   Node's key
             */
            public BSTNode(BSTNode left, BSTNode right, T key) {
                this.left = left;
                this.right = right;
                this.key = key;
            }

            /**
             * Return the key
             *
             * @return The key
             */
            public T getKey() {
                return this.key;
            }

            /**
             * Return the left child of the node
             *
             * @return The left child of the node
             */
            public BSTNode getLeft() {
                return this.left;
            }

            /**
             * Return the right child of the node
             *
             * @return The right child of the node
             */
            public BSTNode getRight() {
                return this.right;
            }


            /**
             * Setter for left child of the node
             *
             * @param newLeft New left child
             */
            public void setLeft(BSTNode newLeft) {
                this.left = newLeft;
            }

            /**
             * Setter for right child of the node
             *
             * @param newRight New right child
             */
            public void setRight(BSTNode newRight) {
                this.right = newRight;
            }

        }

        /* * * * * BST Methods * * * * */

        /**
         * 0-arg constructor that initializes root to null and nelems to 0
         */
        public BSTree() {
            this.root = null;
            this.nelems = 0;

        }

        /**
         * Return the root of BSTree. Returns null if the tree is empty
         *
         * @return The root of BSTree, null if the tree is empty
         */
        public BSTNode getRoot() {
            if ((this.root) == null)
                return null;
            return this.root;
        }

        /**
         * Return the BST size
         *
         * @return The BST size
         */
        public int getSize() {
            return this.nelems;
        }

        /**
         * Insert a key into BST
         *
         * @param key to be inserted
         * @return true if insertion is successful and false otherwise
         * @throws NullPointerException is the key is null
         */
        public boolean insert(T key) {

            if (key == null)
                throw new NullPointerException();

            if (contains(key))
                return false;

            // Case for if the tree was empty
            if (this.root == null) {

                this.root = new BSTNode(null,null,key);

            } else { // If the tree was not empty

                // Iterate through the tree and insert
                BSTNode cur = this.root;

                BSTNode pre = null;

                while (cur != null) {
                    pre = cur;
                    if (key.compareTo(cur.getKey()) < 0)
                        cur = cur.left;
                    else cur = cur.right;
                }

                if (key.compareTo(pre.getKey()) < 0)
                    pre.left = new BSTNode(null,null,key);
                else
                    pre.right = new BSTNode(null,null,key);

            }
            this.nelems++;
            return true;
        }

        /**
         * Return true if the tree contains the 'key', false
         * otherwise
         *
         * @param key To be searched
         * @return True if the 'key' is found, false otherwise
         * @throws NullPointerException If key is null
         */
        public boolean contains(T key) {

            if (key == null)
                throw new NullPointerException();

            Iterator<T> iter = this.iterator();

            while(iter.hasNext()){
                if (iter.next() == key)
                    return true;
            }
            return false;
        }

        /**
         * Remove the key from the BST
         *
         * @param key To be removed
         * @return True if the 'key' is removed, false otherwise
         * @throws NullPointerException If key is null
         */
        public boolean remove(T key) {
            if (key == null)
                throw new NullPointerException();

            if (!this.contains(key))
                return false;

            else {
                this.root = removeHelper(this.root, key);
                this.nelems--;
                return true;
            }
        }

        private BSTNode removeHelper(BSTNode node, T key) {
            if (node == null)
                return node;

            if (key.compareTo(node.key) < 0)
                node.left = removeHelper(node.left,key);
            else if (key.compareTo(node.key) > 0)
                node.right = removeHelper(node.right, key);
            else {
                if (node.left == null)
                    return node.right;
                else if (node.right == null)
                    return node.left;
                node = findMin(node.right);
                node.right = removeHelper(node.right, node.getKey());
            }
            return node;
        }

        /**
         * Returns the smallest node from a given node
         *
         * @param root Smallest node will be found from this node
         * @return The smallest node from the 'root' node
         */
        private BSTNode findMin(BSTNode root) {

            // Instance initialization
            BSTNode current = root;

            while (current != null) {
                if (current.left != null)
                    current = current.left;
                else
                    break;
            }
            return current;
        }

        /**
         * Print the BST nodes by preorder traversal
         *
         * @return string of keys in preorder, separated by a single space (“ ”).
         */
        public String printPreOrder(){
            if (this.root == null)
                return "";

            this.preOrderString = "";
            printPreOrderHelper(this.root);
            this.preOrderString = this.preOrderString.substring(0,this.preOrderString.length() - 1);
            System.out.println(this.preOrderString);
            return this.preOrderString;
        }

        /**
         * Helper function to recursively call printPostOrder to print
         * @param node to be the root for recursion
         */
        private void printPreOrderHelper(BSTNode node) {

            // Base case to stop recursion
            if (node == null)
                return;

            this.preOrderString += node.getKey() + " ";
            printPreOrderHelper(node.left);
            printPreOrderHelper(node.right);

        }

        /**
         * Print the BST nodes by postorder traversal
         *
         * @return string of keys in postorder, separated by a single space (“ ”).
         */
        public String printPostOrder(){
            if (this.root == null)
                return "";

            this.postOrderString = "";
            printPostOrderHelper(this.root);
            this.postOrderString = this.postOrderString.substring(0,this.postOrderString.length() - 1);
            System.out.println(this.postOrderString);
            return this.postOrderString;

        }

        /**
         * Helper function to recurssively call printPostOrder to print
         * @param node to be the root for recursion
         */
        private void printPostOrderHelper(BSTNode node) {

            // Base case to stop recursion
            if (node == null)
                return;

            printPostOrderHelper(node.left);
            printPostOrderHelper(node.right);
            this.postOrderString += node.getKey() + " ";
        }

        /**
         * Print the BST nodes by inorder traversal
         *
         * @return string of keys in inorder, separated by a single space (“ ”).
         */
        public ArrayList<T> printInOrder(){

            Iterator<T> iter = this.iterator();
            this.order = new ArrayList<T>();

            StringBuilder ans = new StringBuilder();
            // Case for if the tree is empty
            if (this.root == null)
                return new ArrayList<T>();
            else {
                // Else iterate through the tree and print
                while (iter.hasNext()){
                    T elem = iter.next();
                    this.order.add(elem);
                    ans.append(elem.toString() + " ");
                }

                this.inOrderString = ans.toString();
                this.inOrderString = this.inOrderString.substring(0,this.inOrderString.length() - 1);
                System.out.println(this.inOrderString);
                return this.order;
            }
        }

        /**
         * Return the height of the tree
         *
         * @return The height of the tree, -1 if BST is empty
         */
        public int findHeight() {
            // If the tree was empty
            if (this.getSize() == 0)
                return 0;

            return findHeightHelper(this.root);

        }

        /**
         * Helper for the findHeight method
         *
         * @param root Root node
         * @return The height of the tree, -1 if BST is empty
         */
        private int findHeightHelper(BSTNode root) {
            if (root == null)
                return 0;

            if (root.left == null && root.right == null)
                return  1;

            else {
                int left_depth = findHeightHelper(root.left);
                int right_depth = findHeightHelper(root.right);

                if (left_depth > right_depth)
                    return (left_depth + 1);
                else
                    return (right_depth+1);
            }
        }

        /* * * * * BST Iterator * * * * */

        /**
         * Class BSTree_Iterator
         */
        public class BSTree_Iterator implements Iterator<T> {

            // Instance initialization
            Stack<BSTNode> tree;
            BSTNode current;

            /**
             * Constructor for BSTree_Iterator
             */
            public BSTree_Iterator() {

                // Instance initialization
                this.tree = new Stack<BSTNode>();
                this.current = BSTree.this.root;

                while (this.current != null) {
                    this.tree.push(this.current);
                    if (this.current.left != null)
                        this.current = this.current.left;
                    else
                        break;
                }
            }

            /**
             * Methods to check if the iterator has a valid next node to iterate
             * @return true if had, false otherwise
             */
            public boolean hasNext() {
                return !this.tree.isEmpty();
            }

            /**
             * Returns the next item in the BST
             * @return the next item
             * @throws NoSuchElementException is there is no next item
             */
            public T next() {
                // Throw NoSuchElementException is there is no next item
                if (!this.hasNext())
                    throw new NoSuchElementException();

                // Pop a node from the Stack and temporarily stores the item at the node
                BSTNode node = this.tree.pop();
                BSTNode current = node;

                // Traverse through the tree
                if (current.right != null) {
                    current = current.right;
                    while(current != null) {
                        this.tree.push(current);
                        if(current.left != null)
                            current = current.left;
                        else
                            break;
                    }
                }
                return node.getKey();

            }
        }

        /**
         * Return the next object
         * @return a new object
         */
        public Iterator<T> iterator() {
            return new BSTree_Iterator();
        }
    }

}
