M

练习HashMap with customized class. 

```
/*
Build Hashtable datastructure with three methods: set, get, getrandom.

Note1: getrandom needs to pick evenly/uniformaly distributed entry.

Note2: to be realistic, hashtable should be able to handle collision, that's where linkedlist comes into play.

Note3: use default object.hashcode() function to get hash code, then apply hashcode % table size


More about O(1) random access
    If no collision, uniform random access is easy.

With collision, need to figure our how to access element on the linkedlist with O(1), but it's unlikely.
    So, like Java HashMap, we can implement rehashing. Bascially, when map size increase to over capacity, double the capacity.

*/

import java.io.*;
import java.util.*;

public class CHashMap<K, V> {
    public int capacity;
    public int count;
    public Entry<K,V>[] table;
    
    public class Entry<K, V> {
        public V value;
        public K key;
        public Entry<K,V> next;
        
        public Entry(K key, V value, Entry<K,V> next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }
    }
    
    
    public CHashMap() {   
        this.capacity = 16;
        this.table = new Entry[this.capacity];
        this.count = 0;
    }
    
    public CHashMap(int capacity) {   
        this.capacity = capacity;
        this.table = new Entry[this.capacity];
        this.count = 0;
    }
    
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int hashedKey = hashFunction(key);
        if (table[hashedKey] != null) {
            Entry<K,V> node = table[hashedKey];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }
    
    
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        this.count++;
        Entry<K,V> entry = new Entry<K,V>(key, value, null);
        int hashedKey = hashFunction(key);
        if (table[hashedKey] == null) {
            table[hashedKey] = entry;
        } else {
            Entry<K,V> node = table[hashedKey]; 
            if (node.key.equals(key)) {
                table[hashedKey] = entry;
                entry.next = node.next;
                return;
            }
            while (node.next != null) {
                if (node.next.key.equals(key)) {
                    Entry<K,V> temp = node.next;
                    node.next = entry;
                    entry.next = temp.next;
                    return;
                }
                node = node.next;
            }
            node.next = entry;
        }
    }

    public int hashFunction (K key) {
        return Math.abs(key.hashCode()) % this.capacity;
    }

    public void display() {
        for (int i = 0; i < table.length; i++) {
            Entry<K,V> node = table[i];
            StringBuffer sb = new StringBuffer();
            while (node != null) {
                sb.append("[key: " + node.key + ", value: " + node.value + "], ");
                node = node.next;
            }
            if (sb.length() != 0)
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    /*     
        If no collision, uniform random access is easy.

        With collision, need to figure our how to access element on the linkedlist with O(1), but it's unlikely.

    */ 
    public V getRandom() {
        Random rd = new Random();
        int hashedKey = hashFunction(rd.nextInt(this.capacity));
        return table[hashedKey];
    }
  
    

    
    
    
    public static void main(String[] args) {
        CHashMap<Character, String> map = new CHashMap<Character, String>(2);

        System.out.println("TEST SET------");
        map.put('a', "1st string");
        map.put('b', "2nd string!");
        map.display();
        map.put('a', "wowo change");
        map.display();
        
        
        System.out.println("TEST PUT------");
        System.out.println(map.get('a'));
        System.out.println(map.get('c'));
        map.put('c', "okay test c");
        System.out.println(map.get('c'));
        map.display();
        
        System.out.println("TEST COLLISION------");
        map.put('d', "test d");
        map.put('e', "test E");
        map.display();
        
        
        //test getrandom
        
    }
}
```