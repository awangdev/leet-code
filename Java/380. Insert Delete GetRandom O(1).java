M
tags: Design, Array, Hash Table
time: O(1) avg
space: O(n)

#### Hash Table, Swap when removing
- Map: `map<val, index>, Lis: tracks `index->value`
- list maintain 用来 insert/remove/random operations.
- Remove: swap input valueIndex & tialIndex = list.size() -1.
    - list.remove(object) 应该是要O(logn) 做一个search的.
    - list.remove(list.size() - 1) is cheapter

```

/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/

/*
Use a global random number.
map<value, index>
list, where valid items are tracked. When removing, swap with tail, and drop.
*/
class RandomizedSet {
    List<Integer> list = new LinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();
    Random rnd = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() { }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val); 
        swap(index, list.size() - 1);
        map.put(list.get(index), index);
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = rnd.nextInt(list.size());
        return list.get(index);
    }
    
    private void swap(int a, int b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```