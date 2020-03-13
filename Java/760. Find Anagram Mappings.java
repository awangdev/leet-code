E
tags: Hash Table
time: O(n)
space: O(n)

- HashMap 存index list
- 遍历一遍数组A, 列举出所有元素

```
/*
Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.

We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.

These lists A and B may contain duplicates. If there are multiple answers, output any of them.

For example, given

A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]
We should return
[1, 4, 3, 2, 0]
as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
Note:

A, B have equal lengths in range [1, 100].
A[i], B[i] are integers in range [0, 10^5].

*/

/*
Thoughts:
HashMap && O(n) solution
Loop over A to put all elements in map as key and list of indexs as value.
Loop over B to and pick up the item from map, and put B's index in the value list.
Loop over A again and use the map.value to build result. Note, we should not have to worry about outbound since A & B are anagram
*/
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0) {
            return null;
        }

        int[] result = new int[A.length];
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        // Prepare the map
        for (int i = 0; i < B.length; i++) {
            map.putIfAbsent(B[i], new ArrayList<>());
            map.get(B[i]).add(i);
        }
        
        for (int i = 0; i < A.length; i++) {
            ArrayList<Integer> list = map.get(A[i]);
            result[i] = list.get(0);
            list.remove(0);            
        }
        return result;
    }
}
```