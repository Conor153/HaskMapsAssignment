package main;
import java.util.HashMap;

/**
 * This is main.LRUCache
 * Skeleton Code @author ruth.lennon
 * Functionality of methods @author Conor.Callaghan L00173495
 * Date 23/12/2025
 */

class LRUCache {
    /**
     * Inner Node class
     * Used to manage nodes
     */
    class Node {
        int key, value;
        Node prev, next;
        /**
         * Constructor to create a Hash Table
         * @param key used to identify the node
         * @param value value stored in the node
         * */
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> cache;
    private int capacity, size;
    private Node head, tail;

    /**
     * Constructor to create a Hash Table
     * @param capacity to set capacity of the hash map
     * */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    /**
     * function to move item to the front of cache
     * @param node which is to be moved to the front of the cache
     * */
    private void moveToFront(Node node) {
        //If node is the head
        //It already is at the front and does not need to be moved
        if(head == node)
        {
            return;
        }
        //If the tail is to be moved to the front
        //then its predecessor is to become the tail
        if(tail == node)
        {
            tail = node.prev;
            tail.next = null;
        }
        //Else if the node is in the middle of the pack
        //Connect its predecessor with the next node
        else
        {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        //Assign node to the head
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }

    /**
     * function to add node to the front of the cache
     * @param node which is added to the front of the cache
     * */
    private void addToFront(Node node) {
        if(tail == null)
        {
            head = node;
            tail = node;
        }
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    /**
     * function to remove item from cache
     * @param node which is removed from the cache
     * */
    private void remove(Node node) {
        //Remove the key-value pairing from hash table
        //Decrease the size of the cache
        cache.remove(node.key);
        size--;
        if(tail == null)
        {
            return;
        }
        if(head == tail)
        {
            head = null;
            tail = null;
            return;
        }
        //If head is to be removed set next node to head
        if(head == node)
        {
            head = head.next;
            head.prev = null;
            return;
        }
        //If tail is removed set the previous node as tail
        if(tail == node)
        {
            tail = tail.prev;
            tail.next = null;
            return;
        }
        //Connect its predecessor with the next node
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;

    }

    /**
     * function to search for an item in the cache
     * @param key is used to identify the node within the hash
     * */
    public int get(int key) {
        //Check cache for the key
        //If found move the node to the front of the cache
        //Return its value
        if(cache.containsKey(key))
        {
            Node node = cache.get(key);
            moveToFront(node);
            return node.value;
        }
        //Key not found
        return -1;
    }

    /**
     * function to insert Node to the cache
     * @param key used to identify the value stored in the hash table
     * @param value is the item stored within the hash table
     * */
    public void put(int key, int value) {
        //Create a new node using key and value
        //Add it to the front of the cache
        Node node = new Node(key,value);
        addToFront(node);
        cache.put(key, node);
        size++;
        //If cache size is bigger than the capacity remove the tail
        //As it is least recently used
        if(size > capacity)
        {
            remove(tail);
        }

    }

    /**
     * function to print the Cache
     * */
    public void printCache() {
        Node temp = head;
        System.out.println("L00173495 Conor Callaghan Cache");
        while(temp != null)
        {
            System.out.print(temp.value + " ---> ");
            temp = temp.next;
        }
        System.out.println("END L00173495 Conor Callaghan");

    }

    // Main method to test the LRU Cache implementation
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(20); // Cache capacity of 20
        // Insert items into cache
        lruCache.put(1, 101111);
        lruCache.put(2, 101222);
        lruCache.put(3, 101333);
        lruCache.put(4, 101444);
        lruCache.put(5, 101555);

        lruCache.put(6, 101667);
        lruCache.put(7, 101777);
        lruCache.put(8, 101888);
        lruCache.put(9, 101999);
        lruCache.put(10, 102000);

        lruCache.put(11, 102111);
        lruCache.put(12, 102222);
        lruCache.put(13, 102333);
        lruCache.put(14, 102444);
        lruCache.put(15, 102555);

        lruCache.put(16, 102667);
        lruCache.put(17, 102777);
        lruCache.put(18, 102888);
        lruCache.put(19, 102999);
        lruCache.put(20, 103000);
        //Print the cache
        System.out.println("Print Original Cache");
        lruCache.printCache();
        // Access key 2 (this will make key 2 the most recently used)
        System.out.println("Get 2: " + lruCache.get(2)); // Should return 2
        //Print the cache
        System.out.println();
        System.out.println("Print Cache after 2 has been accessed");
        lruCache.printCache();
        // Insert a new key, which will evict key 1 (the LRU)
        System.out.println();
        System.out.println("Print Cache after new Node added 1 is last and gets removed");
        lruCache.put(21, 103111);
        lruCache.printCache();
        // Access key 20
        System.out.println();
        System.out.println("Print Cache after 20 has been accessed");
        System.out.println("Get 20: " + lruCache.get(20));
        lruCache.printCache();
        // Insert another new key, which will evict key 4 (the LRU)
        System.out.println();
        System.out.println("Print Cache after 22 is added. 3 is last and gets removed");
        lruCache.put(22, 103222);
        lruCache.printCache();
        //Remove the head to showcase that any node can be removed
        System.out.println();
        System.out.println("Print Cache after head is removed. 22 is removed");
        System.out.println("Remove Head");
        lruCache.remove(lruCache.head);
        lruCache.printCache();
        //Remove the tail
        System.out.println();
        System.out.println("Print Cache after tail is removed. 4 is removed");
        System.out.println("Remove Tail");
        lruCache.remove(lruCache.tail);
        lruCache.printCache();
    }
}