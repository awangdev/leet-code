M
1516608238

求最值, DP.
两个特别处:
1. 正负数情况, 需要用两个DP array. 
2. continuous prodct 这个条件决定了在Math.min, Math.max的时候, 
是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
这也就注定了需要一个global variable 来hold result.

```
/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

Tags Expand 
Dynamic Programming Subarray
*/
/*
Thoughts:
'Largest', DP.
Consider positivie/Negative numbers.
f[x] = largest continuous product at index x. NOTE: it's not entire array's largest, need a stand-along variable to hold global max.
if nums[x] < 0, want (min of f[x-1]) * nums[x]
if nums[x] > 0, want (max of f[x-1]) * nums[x]
Consider two different arrays.
f[x] = Math.max( min(f[x-1]) * nums[x] if nums[x]<0, or max(f[x-1])*nums[x] if nums[x]>0)
initial condition:
x = 0 -> nums[0]

*/
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int[] maxProduct = new int[nums.length];
        final int[] minProduct = new int[nums.length];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxProduct[i] = Math.max(nums[i], maxProduct[i - 1] * nums[i]);
                minProduct[i] = Math.min(nums[i], minProduct[i - 1] * nums[i]);
            } else {
                maxProduct[i] = Math.max(nums[i], minProduct[i - 1] * nums[i]);
                minProduct[i] = Math.min(nums[i], maxProduct[i - 1] * nums[i]);
            }
            result = Math.max(result, maxProduct[i]);
        }
        return result;
    }
}

/*
Previous notes
Attempt2: Use a max array and a min array. (http://www.jiuzhang.com/solutions/maximum-product-subarray/)
This is similar to my original attempt1, but saves a lot memory space.

0. Max array is always positive, Min array is always negative. Use these 2 arrays to keep track of largest positive number and smallest negative number
1. When current nums[i] > 0, use max[i - 1] * nums[i].
2. When current nums[i] < 0, user min[i - 1] * nums[i];
3. Don't for get to calculate both max and min for each i, for next iteration to use.

In either case, we will produce largest possible product.

Trick: depending on nums[i] is positive or negative, calculate differently ...
*/
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
```