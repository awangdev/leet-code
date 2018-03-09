H
1516782888
tags: Array, Two Pointers, Stack

方法1:
主要想法和方法2一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.

方法2:
2 Pointers， 双面夹击：   
1. 找中间最高bar的index    
2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条。    
3. 每次还要减去block自身的height。

```
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

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
Wather only can aggregate at bottom of the hill, which means we need tack downhill indexes untill it hit bottom (before acending).
We can use Stack<Integer> as decendingStack:
1. push to stack when current height[i] < stack.top()
2. If current height[i] > stack.top(), then it's ascending, and try to calculate water accumulated from all stack elements that's <= height[i]

Extreme case:
When it's always ascending and only have a downhill at last index, then it'll be a O(n) for loop, and at the end + another O(n) while loop for once.
Overall, time should be O(n), space O(n)
The 'while' iterate over stack once, which should be distributed across for interations.
*/
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        // deceding stack that stores decending indexes
        final Stack<Integer> decendingStack = new Stack<>();
        int result = 0;
        decendingStack.push(0);
        
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= height[decendingStack.peek()]) {// if ascending
                int bottom = height[decendingStack.pop()];
                // Calculate volumn based on height diff from stack.peek() index to bottom index
                // Note, stack.peek() and bottom are moving leftwards, and each time a row of water is added to result
                // Of course, left bound has to be lower than current height[i]
                while (!decendingStack.isEmpty() && height[i] >= height[decendingStack.peek()]) {
                    int leftBound = decendingStack.pop(); // pop(): shift left bound
                    result += (height[leftBound] - bottom) * (i - leftBound - 1); // a water row
                    bottom = height[leftBound]; // shift bottom to left
                }
                // When current height[i] < leftBound from stack, try use existing bottom to add more rows of water.
                if (!decendingStack.isEmpty() && height[i] < height[decendingStack.peek()]) {
                    result += (height[i] - bottom) * (i - decendingStack.peek() - 1);
                }
            }
            decendingStack.push(i);
        }
        return result;   
    }
}

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