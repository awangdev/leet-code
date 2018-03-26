H
1522042382
tags: Divide and Conquer, DP, Range DP, Memoization

一排球, 每个球有value, 每次扎破一个, 就会积分: 左*中间*右 的值. 求, 怎么扎, 最大值?

TODO: Need more thoughts on why using dp[n + 2][n + 2] for memoization, but dp[n][n] for range DP.

#### Range DP
- 因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
- 最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.
- dp[i][j] represent max value on range [i, j)
- Need to calculate dp[i][j] incrementally, starting from range size == 3 ---> n
- Use k to divide the range [i, j) and conquer each side.

##### Range DP 三把斧:
- 中间劈开
- 砍断首或尾
- Range区间作为iteration的根本

##### Print the calculation process
- use pi[i][j] and print recursively.
- Print k, using pi[i][j]: max value taken at k

#### Memoization
- 其实会做之后挺好想的一个DP
- dp[i][j] =  balloons i~j 之间的 max. 
- 然后找哪个点开始burst? 设为x。
- For loop 所有的点作为x， 去burst。
- 每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。
- Note: 这个是Memoization, 而不纯是DP
- 因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing


```
/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
Credits:
Special thanks to @peisi for adding this problem and creating all test cases.

Hide Company Tags Google
Show Tags
Divide and Conquer Dynamic Programming


*/

/*
Thoughts:
Range DP. Think about it: it's really hard to find which ballon burst first; 
how about which ballon burst last?

If it burst last, the value will be left * lastItem * right.
Now we just have to pick which one burst last? k in [i, j]

Note that, we need the invisible wall on head and tail, so make sure creating dp at length of n+2
dp[i][j] represent max value on range [i, j)

Pick k in the middle:
dp[i][j] = dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j];
where:
dp[i][k]: range (i, k)
dp[k][j]: range (k, j)

Time O(n^3)
Space O(n^2)
*/
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] values = new int[n + 2];
        values[0] = 1;
        values[n + 1] = 1;
        // reassign new array
        for (int i = 1; i <= n; i++) {
            values[i] = nums[i - 1];
        }
        
        n = values.length;
        int[][] dp = new int[n][n]; // dp[i][j] denotes the max value in range [i, j)
        // Critical: iterate over RANGE: then come up with i and j; i <= n - len
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = len + i - 1;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}


/*
	Thoughts: as seen in dicussion. Build DP.
	State:
	dp[i][j]: the number of max coins can collect between i and j.
		For a position x in [i,j], where to burst it? So this goes into a divide and conquer method.
		Burst at x, track the sum, and record the max into dp[i][j]
	Function:
		dp[i][j] = Math.max(dp[i][j], DP(i, x-1) + nums[x-1]*nums[x]*nums[x+1] + DP(x+1, j))
	Init:
		create dp[n+2][n+2].  (from 0 to n+1)
		dp[0][1] = 1;
		dp[n][n+1] = 1;
	Return:	
		dp[1][n]

	DP(int i, int j, int[][] dp)

	Need to redo that nums.
*/


public class Solution {
	int[][] dp;
	int[] values;
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        int n = nums.length;
        dp = new int[n + 2][n + 2];

        //Initialize new array
        values = new int[n + 2];
        values[0] = values[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
        	values[i] = nums[i - 1];
        }
       
        return DP(1, n);
    }

    public int DP(int i, int j){
    	if (dp[i][j] > 0) {//momorization
    		return dp[i][j];
    	}
    	for (int x = i; x <= j; x++) {
    		dp[i][j] = Math.max(dp[i][j], DP(i, x - 1) + values[i-1]*values[x]*values[j+1] + DP(x + 1, j));
    	}
    	return dp[i][j];
    }
}

/*
	用了recursive + memorization, 但是也可以用传统的DP，比如：
	for (int length = 1; length < n; length++) [
        for (int = 0; i < n-1; i++)  {
            j = i + length; 
            if length == 1:
                dp[i][j] = A[i] * A[j] + A[i]
            else:
                dp[i][j] = max {}
        }
    }

*/



/*
	My Thought: TOO COMPLEX. Should go with the easy DP approach. Also, using a hashMap to trach all the patterns,
	this might not be applicable: because as the integer array's length goes up, there will be too many possible
	combinations to store in hashamp.
	Burst each balloon, and DFS into each branch, calcualte the sum + each balloon-burst's product.
	Also, use a HahsMap<"Value combination", max value>. to reduce the # of re-calculation.
	convert nums into string, and in DFS, we don't even need bakc-tracking
	helper(list, sum)


	Thoughts:http://www.cnblogs.com/grandyang/p/5006441.html
	dp[i,j]: burst range [i~j]'s max coins.

*/
```
