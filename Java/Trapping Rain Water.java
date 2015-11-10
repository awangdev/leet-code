2 Pointers， 
双面夹击：找中间最大bar，两面往中心扫

```
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Example
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Challenge
O(n) time and O(1) memory

O(n) time and O(n) memory is also acceptable.

Tags Expand 
Two Pointers Forward-Backward Traversal Array

Related Problems Expand 
Medium Container With Most Water

LeetCode:
Tags: Array, Stack, Two Pointers
Similar Problems: (M) Container With Most Water, (M) Product of Array Except Self


*/

/*
Thoughts:
1. Find max's index, and use this index as the central pivot. (WHY? because highest bar can hold any volumn of water)
2. Process left and right separately.
    a. assume each height jump fills that increased height of volumn. that is, if increased by r rows, add r rows of water into sum
    b. all the black blocks (stones) should be minus from the sum on each index.
O(n) on finding the max.
O(n/2) for both left and right.
Total 2*O(n) = O(n)

*/

//O(n)

public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int max = 0;
        int maxIndex = 0;
        int sum = 0;
        int prev = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > max) {
                max = heights[i];
                maxIndex = i;
            }
        }

        //Process left
        for (int i = 0; i < maxIndex; i++) {
            if (heights[i] > prev) {
                sum += (maxIndex - i) * (heights[i] - prev);
                prev = heights[i];
            }
            sum -= heights[i];
        }

        //Process right:
        prev = 0;
        for (int i = heights.length - 1; i > maxIndex; i--) {
            if (heights[i] > prev) {
                sum += (i - maxIndex) * (heights[i] - prev);
                prev = heights[i];
            }
            sum -= heights[i];
        }

        return sum;
    }
}


















```