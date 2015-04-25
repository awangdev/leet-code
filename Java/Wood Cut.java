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

Thinking process:
Take the largest item. 
Priorities:
1. Have to get calculatedK >= givenK
2. Meanwhile, want to maximize the  smal piece.

One thing not clear: do we have to use the given small piece? If we have to, we need to concern about the shortest wood piece. See commentted-out part
In this problem, however, we can abandon the small pieces, as long as the max_small_pieces can allow calculatedK >= givenK.

Use binary search on the largest item:
1. if calculatedK < givenK: end = mid;
2. If calculated >= givenK, move start = mid as much as possible, which gives maximized small piece.

*/


public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0 || k < 0) {
            return 0;
        } 
        if (L.length == 1) {
            return L[0] / (L[0] / k);
        }
        Arrays.sort(L);
        int start = 0;
        int end = L[L.length - 1];
        int mid = 0;
        int max = 0;
       // int min = L[0];
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //if (mid > min) {
            //    end = mid;
           // } else {
                int count = 0;
                for (int i : L) {
                    count += i / mid;
                }
                if (count < k) {
                    end = mid;
                } else {
                    start = mid;
                    max = mid;
                }
            //}
        }//end while
        return max;
    }
}

