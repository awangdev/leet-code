/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

Tags Expand 
Dynamic Programming Subarray
*/
/*
Attempt2: Use a max array and a min array. (http://www.jiuzhang.com/solutions/maximum-product-subarray/)
This is similar to my original attempt1, but saves a lot memory space.

0. Max array is always positive, Min array is always negative. Use these 2 arrays to keep track of largest positive number and smallest negative number
1. When current nums[i] > 0, use max[i - 1] * nums[i].
2. When current nums[i] < 0, user min[i - 1] * nums[i];
3. Don't for get to calculate both max and min for each i, for next iteration to use.

In either case, we will produce largest possible product.

Trick: depending on nums[i] is positive or negative, calculate differently ...
*/

public class Solution {
    public int maxProduct(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	int[] max = new int[nums.length];
    	int[] min = new int[nums.length];
    	max[0] = nums[0];
    	min[0] = nums[0];
    	int rst = max[0];
    	for (int i = 1; i < nums.length; i++) {
    		if (nums[i] > 0) {
    			max[i] = Math.max(nums[i], max[i - 1] * nums[i]);//the nums[i] could just be the best option
    			min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
    		} else {
    			max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
    			min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
    		}
    		rst = Math.max(rst, max[i]);
    	}
    	return rst;
    }
}



/*
Attempt1 thoughts:
97% correct. However, this exceeds memory, basically the DP[][] is too large.
Draw a 2D array: 
Row: Start from a number ROW[i], what contiguous value can we get:
		0	1	2	3
		-------------
		2 	3 	-2	4
0|  2  	2	6	-12	-48
1|  3  	x	x	-6	-24
2|  -2  x	x	x	-8
3|  4  	x	x	x	x

Look, according to the rules of (contiguous subarray), we can't do Row[i]xRow[i], so we have to do: Row[i]xROW[i+1]xROW[i+2]...etc
Goal: find the max in DP
1. Define DP[0][0] = nums[0];
2. DP[i][j] = DP[i][j - 1] * nums[j]
3. And we keep track of the max value

Note: j will always > i, so cases that i >= j are not necessary.
*/


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	int[][] DP = new int[nums.length][nums.length];
	    	DP[0][0] = nums[0];
	    	int max = DP[0][0];
	    	
	    	for (int i = 0; i < nums.length; i++) {
	    		for (int j = 1; j < nums.length; j++) {
	    		    if (i == j) {
	    		        DP[i][j] = nums[j];
	    		    }
	    			if (j > i) {
	    				if (DP[i][j - 1] == 0) {
	    					DP[i][j] = nums[j];
	    				} else {
	    					DP[i][j] = DP[i][j - 1] * nums[j];
	    				}
	    				max = Math.max(max, DP[i][j]);
	    			}
	    			max = Math.max(max, nums[j]);
	    		}
	    	}
    	return max;
    }
}