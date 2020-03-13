E
tags: Hash Table, Design, Memoization
time: O(n)
space: O(n)

#### Hash Table, Memo
- Use Map<number, count > to store the inputs
- Iterate over map to find the pair
- Use Set<int> memo to store the success cases for fast return
- time: O(n), loop over all elements in map
- space: O(n), store all elements in map & memoization set

```
/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false
*/
/*
- Use Map<number, count > to store the inputs
- Iterate over map to find the pair
- Use Set<int> memo to store the success cases for fast return
*/
class TwoSum {
    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> memo = new HashSet<>();
    /** Initialize your data structure here. */
    public TwoSum() { }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.putIfAbsent(number, 0);
        map.put(number, map.get(number) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (memo.contains(value)) return true;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            int remain = value - num;
            if (map.containsKey(remain)) {
                if (remain == num && count < 2) continue; // only 1 instance of the element exist, skip
                memo.add(value);
                return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```