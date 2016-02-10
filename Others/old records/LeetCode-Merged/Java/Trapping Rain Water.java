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
Solution2: imagine there is always 2 bar that holds the water in middle; or imagine: we only have one side of the wall (right wall in this case),
and we are adding a hard-paper on leftside of the water, thus we can calculate the volumn of water in middle.
Idea is: two artificial wall has to both > water in middle, then will have water in middle.
Also, another idea of shifting highest wall: the highest wall from left and highest wall from right will hold a triangle shape of water in between,
when calculating the water in middle index by index, we can simulate the two walls in by comparing max.
Note: the true volumn for each index calculated should respect the min of the 2 highest walls.

Note2: leftSideHighWall always store the heigest wall on left side of current index.
*/
public class Solution {
    public int trap(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }   
        int[] leftSideHighWall = new int[heights.length + 1];
        leftSideHighWall[0] = 0;
        for (int i = 0; i < heights.length; i++) {
            leftSideHighWall[i + 1] = Math.max(leftSideHighWall[i], heights[i]);
        }
        int rightSideHighWall = 0;
        int sum = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            int minOfTwoWalls = Math.min(leftSideHighWall[i], rightSideHighWall);
            sum += minOfTwoWalls > heights[i] ? minOfTwoWalls - heights[i] : 0;
            rightSideHighWall = Math.max(heights[i], rightSideHighWall);
        }
        return sum;
    }
}

/*
Solution1, Add extra and remove extra
Thoughts:
1. Find max's index, and use this index as the central pivot. (WHY? because highest bar can hold any volumn of water)
2. Process left and right separately.
	a. assume each height jump fills that increased height of volumn. that is, if increased by r rows, add r rows of water into sum
	b. all the black blocks (stones) should be minus from the sum on each index.
O(n) on finding the max.
O(n/2) for both left and right.
Total 2*O(n) = O(n)

*/




public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trap(int[] heights) {
    		if (heights == null || heights.length == 0) {
    		return 0;
    	}
    	int max = 0;
    	int maxIndex = -1;
    	int sum = 0;
    	int prev;
    	for (int i = 0; i < heights.length; i++) {
    		if (heights[i] > max) {
    			max = heights[i];
    			maxIndex = i;
    		}
    	}

    	//Process left
    	prev = 0;
    	for (int i = 0; i < maxIndex; i++) {
    		if (heights[i] > prev) {
    			sum += (heights[i] - prev) * (maxIndex - i);
    			prev = heights[i];
    		}
    		sum -= heights[i];
    	}

    	//Process right:
    	prev = 0;
    	for (int i = heights.length - 1; i > maxIndex; i--) {
    		if (heights[i] > prev) {
    			sum += (heights[i] - prev) * (i - maxIndex);
    			prev = heights[i];
    		}
    		sum -= heights[i];
    	}

    	return sum;
    }

    }

}





















