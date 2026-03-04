import java.util.*;
public class chainedHash{
    private static class Node {
        String key;
        String value;
        Node next;
        Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    private Node[] table;
    private int size; 
    private int m;    
    public chainedHash(int m) {
        this.m = m;
        this.table = new Node[m + 1]; 
        this.size = 0;
    }
    
    private int hash(String key) {
        final int BASE = 31;
        long hash = 0;
        
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * BASE + key.charAt(i)) % m;
        }
        
        return (int)(hash % m) + 1;
    }
 public void insert(String key, String value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new Node(key, value);
            size++;
            return;
        }
        Node current = table[index];
        Node prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }
        prev.next = new Node(key, value);
        size++;
    }
    public String lookup(String key) {
        int index = hash(key);
        Node current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
 public boolean delete(String key) {
        int index = hash(key);
        Node current = table[index];
        Node prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        
        return false;
    }
    public boolean isInTable(String key) {
        return lookup(key) != null;
    }
    public double getLoadFactor() {
        return (double)size / m;
    }
    public void printChainStats() {
        int minChain = Integer.MAX_VALUE;
        int maxChain = 0;
        int emptyChains = 0;
        double totalLength = 0;
        for (int i = 1; i <= m; i++) {
            int length = 0;
            Node current = table[i];
            while (current != null) {
                length++;
                current = current.next;
            } 
            if (length == 0) {
                emptyChains++;
            } else {
                minChain = Math.min(minChain, length);
                maxChain = Math.max(maxChain, length);
                totalLength += length;
            }
        }
        double avgChain = totalLength / (m - emptyChains);
        System.out.println("Chain Statistics:");
        System.out.println("  Empty chains: " + emptyChains + "/" + m);
        System.out.println("  Min chain length: " + minChain);
        System.out.println("  Max chain length: " + maxChain);
        System.out.println("  Avg chain length (non-empty): " + 
                          String.format("%.2f", avgChain));
    }
}


    

