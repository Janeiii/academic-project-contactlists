/*
 * Name: Janet Lin
 * PID:  A16686792
 */

import java.util.*;

public class ContactList {

	// Add instance variables here
    private HashMap<String, Person> map;
    private int nContacts;

    public ContactList() {
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
        if(start == null || end == null || start.compareTo(end) >= 0){
            throw new IllegalArgumentException();
        }

        ArrayList<Person> list = new ArrayList<>();
        for (String i : this.map.keySet()) {
            if( (start.compareTo(i) <= 0 ) && (end.compareTo(i) > 0) ){
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



    public class BSTree<T extends Comparable<? super T>> {

        /* * * * * BST Instance Variables * * * * */

        private int nelems; // number of elements stored
        private BSTNode root; // reference to root node
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

                this.root = new BSTNode(null, null, key);

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
                    pre.left = new BSTNode(null, null, key);
                else
                    pre.right = new BSTNode(null, null, key);

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

            BSTNode current = this.root;

            while (current != null) {
                if (current.getKey() == key)
                    return true;
                else if (current.getKey().compareTo(key) > 0)
                    current = current.left;
                else if (current.getKey().compareTo(key) < 0)
                    current = current.right;
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
                node.left = removeHelper(node.left, key);
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

        public ArrayList<T> printInOrder() {
            inOrderHelper(this.root);
            return this.order;
        }

        private void inOrderHelper(BSTNode node) {

            // Base case to stop recursion
            if (node == null)
                return;
            inOrderHelper(node.left);
            this.order.add(node.getKey());
            inOrderHelper(node.right);

        }
    }
}
