H
tags: Array, Two Pointers, Stack
time: O(n)
space: O(1)

这道题目的方法比较多.

#### Method1: Max wall from both sides
- Array: Left Max Wall vs Right Max Wall.
- 对于每个index而言, vertically 能存放的最大水柱, 就是靠 左 右 最高墙决定的: 
    - min(leftHighestWall, rightHighestWall) - currHeight.
- time: O(n)
- space: O(n)

#### Method2: Two Pointers
- Optimization from Method1: two pointer, 还是找左边最高和右边最高. O(1) space.
- 利用到了方法3里面的想法一样: 整个structure是被中间的最高bar 二分天下:
- always limited by the shorter wall: 左边按照maxLeft来计算, 右边按照maxRight来计算.
- time: O(n)
- space: O(1)

#### Method3: 2 Pointers, start from 2 sides
- 1. 找中间最高bar的index    
- 2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条
- 3. 每次还要减去block自身的height
- time: O(n)
- space: O(1)

#### Method4: Stack
- 主要想法和方法3一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
- 最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
- 用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.
- time: O(n)
- space: O(n)


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
Method1: 
At each position, if want to know how much water can trap, need to know height of leftWall, and rightWall.
Use the short wall and water height.
Don't forget to remove current wall height.

Record highest left wall in one array and highest right wall in anothe array for every position.

Time: O(n)
Space: O(n)
*/
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int[] maxLeft = new int[n], maxRight = new int[n];
        maxLeft[0] = height[0];
        maxRight[n - 1] = height[n - 1];
        // Prepare left/right highest for each index
        for (int i = 1; i < n; i++) maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        for (int i = n - 2; i >= 0; i--) maxRight[i] = Math.max(maxRight[i + 1], height[i]);

        // Add water and remove wall height
        int total = 0;
        for (int i = 0; i < n; i++) {
            int waterDepth = Math.min(maxLeft[i], maxRight[i]);
            total += height[i] < waterDepth ? waterDepth - height[i] : 0;
        }
        return total;
    }
}


/*
Method2: Two Pointer
The two pointer approach respects the central heightest point:
The entire structure is divided by the central highest point, where maxLeft == maxRight.
Before left and right meet on this point, keep left++, right--.

Time: O(n)
Space: O(1)
*/
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length, left = 0, right = n - 1;
        int maxLeft = height[0], maxRight = height[n - 1];
        
        // process left/right pointer together
        int total = 0;
        while (left < right) {
            if (maxLeft < maxRight) {
                left++;
                if (maxLeft > height[left]) total += maxLeft - height[left];
                else maxLeft = height[left]; 
            } else {
                right--;
                if (maxRight > height[right]) total += maxRight - height[right];
                else maxRight = height[right];
            }
        }
        return total;
    }
}



/*
Method3: find max first, then calculate from 2 sides
1. Find max's index, and use this index as the central pivot. (WHY? because highest bar can hold any volumn of water)
2. Process left and right separately.
    a. assume each height jump fills that increased height of volumn. that is, if increased by r rows, add r rows of water into sum
    b. all the black blocks (stones) should be minus from the sum on each index.
O(n) on finding the max.
O(n/2) for both left and right.
Total 2*O(n) = O(n)

*/
class Solution {
    public int trap(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int maxIndex = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > heights[maxIndex]) maxIndex = i;
        }

        int sum = 0, prev = 0;
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

/*
Thoughts:
Wather only can aggregate at bottom of the hill, 
which means we need tack downhill indexes untill it hit bottom (before acending).
We can use Stack<Integer> as decendingStack:
1. push to stack when current height[i] < stack.top()
2. If current height[i] > stack.top(), then it's ascending, 
   and try to calculate water accumulated from all stack elements that's <= height[i]

Extreme case:
When it's always ascending and only have a downhill at last index, then it'll be a O(n) for loop, 
and at the end + another O(n) while loop for once.

Overall, time should be O(n), space O(n)
The 'while' iterate over stack once, which should be distributed across for interations.
*/
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        // deceding stack that stores decending indexes
        final Stack<Integer> stack = new Stack<>();
        int total = 0;
        stack.push(0);
        
        for (int i = 1; i < height.length; i++) {
            int curr = height[i];
            if (curr >= height[stack.peek()]) {// if ascending
                int bottom = height[stack.pop()];
                // Calculate volumn based on height diff from stack.peek() index to bottom index
                // Note, stack.peek() and bottom are moving leftwards, and each time a row of water is added to result
                // Of course, left bound has to be lower than current height[i]
                while (!stack.isEmpty() && curr >= height[stack.peek()]) {
                    int leftBound = stack.pop(); // pop(): shift left bound
                    total += (height[leftBound] - bottom) * (i - leftBound - 1); // a water row
                    bottom = height[leftBound]; // shift bottom to left
                }
                // When current height[i] < leftBound from stack, try use existing bottom to add more rows of water.
                if (!stack.isEmpty() && curr < height[stack.peek()]) {
                    total += (curr - bottom) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return total;   
    }
}


```