import java .util.*;
public class openHash {
  private static class Entry {
    String key ;
    String value;
    boolean isActive;
    Entry ( String key,String value) {
      this.key = key;
      this.value = value;
      this.isActive =true;
    }
  }
  private Entry [] table ;
  private int size ;
  private int m;
  public openHash(int m) {
    this.m = m;
    this.table = new Entry [m +1] ;
    this.size = 0;
  }
  private int hash(String key ) {
    final int BASE = 31;
    long hash = 0 ;
    for( int i = 0 ;i < key.length(); i++) {
      hash = ( hash * BASE + key.charAt((i)) % m;
    }
    return (int )(hash % m) +1;
  }
  private int nextProbe(int current, int attempt) {
        // Linear probing: h(k,i) = (h(k) + i) mod m
        return ((current - 1 + attempt) % m) + 1;
  }
    public void insert(String key, String value) {
        int index = hash(key);
        int attempt = 0;
        while (attempt < m) {
            int probe = nextProbe(index, attempt);
            if (table[probe] == null) {
                table[probe] = new Entry(key, value);
                size++;
                return;
            }
            if (table[probe].isActive && table[probe].key.equals(key)) {
                table[probe].value = value;
                return;
            }
            if (!table[probe].isActive) {
                table[probe] = new Entry(key, value);
                size++;
                return;
            }
            attempt++;
        }
        System.out.println("Warning: Hash table is full, could not insert key: " + key);
    }
    public String lookup(String key) {
        int index = hash(key);
        int attempt = 0;
        while (attempt < m) {
            int probe = nextProbe(index, attempt);
            if (table[probe] == null) {
                return null;
            }
            if (table[probe].isActive && table[probe].key.equals(key)) {
                return table[probe].value;
            }
            attempt++;
        }
        
        return null;
    }
  
