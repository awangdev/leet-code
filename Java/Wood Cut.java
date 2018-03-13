M
1520921982
tags: Binary Search

二分的思想: 判断的是一个 validate() function, 而不是简单的'=='

不需要sort! 为什么呢? 因为我们不是在given array上面二分, 我们是根据最大值在[0, max]上二分.

Overall time: O(nLogM), where M = largest wood length

```
/*
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Note
You couldn't cut wood into float length.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge
O(n log Len), where Len is the longest length of the wood.

Tags Expand 
Binary Search

*/
/*
Thoughts:
Find a number, that can split all L's into K parts. The number should range:
[0, L(largest)]
1. binary search [0, largest value]
2. condition check: count pieces
3. Need find the mid: valid(mid) && !valid(mid + 1)

Time: O(n) find max
Time: O(n * logM)
Overall: O(nLogM), where M = largest wood length
*/
public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0 || k < 0) {
            return 0;
        }
        
        int m = L.length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, L[i]);
        }
        
        int start = 0;
        int end = max;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (validate(mid, k, L) && mid + 1 < max && !validate(mid + 1, k , L)) {
                return mid;
            } else if (validate(mid, k, L)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (validate(end, k , L)) {
            return end;
        } else {
            return start;
        }
    }
    
    private boolean validate(int size, int k, int[] L) {
        int count = 0;
        for (int length : L) {
            count += length / size;
        }
        return count >= k;
    }
}
```