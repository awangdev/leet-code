M
tags: Binary Search
time: O(n*logM)
space: O(1)


#### Binary Search
- Bianry serach on the min/max value range
- The mid value is calcualted with helper function `calc(piples, k)`
- find celing: `count += (i - 1) / k + 1`, faster than `Math.ceil(i / k)`
- time: O(logm) to find the best velocity, assume total range is m; O(n) for each `calc` call

```
/*
Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.

 

Example 1:

Input: piles = [3,6,7,11], H = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], H = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], H = 6
Output: 23
 

Note:

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9

*/
class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        
        int start = 1, end = 1_000_000_000;
        int max = end;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = calc(piles, mid);
            if (count > H) start = mid;
            else {
                if (mid + 1 < max && calc(piles, mid + 1) <= H) {
                    end = mid;
                    continue;
                }
                return start;
            }
        }
        
        if (calc(piles, start) <= H) return start;
        return end;
    }
    
    private int calc(int[] piles, double k) {
        int count = 0;
        for (int i : piles) {
            count += Math.ceil(i / k);
        }
        return count;
    }
}
```