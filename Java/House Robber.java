E

最基本的dp。      
看前一个或前两个的情况，再总和考虑当下的。      
思考的适合搞清楚当下的和之前的情况的关系。    
滚动数组的优化，就是确定了是这类“只和前一两个位子“相关的Fn而推出的。   

```
/*
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that 
adjacent houses have security system connected and it will automatically 
contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.


Example
Given [3, 8, 4], return 8.

Challenge
O(n) time and O(1) memory.

Tags Expand 
Dynamic Programming

*/



/*
    3.24.2016 recap
    Find max, either add nums[i - 1] or not. Use dp[i] to track the max money take from [0 ~ i]
*/
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);                
        }

        return dp[dp.length - 1];
    }
}

/*
    Should be able to do with recursive way.
    dfs(nums, index, robFlag, sum)
    Based on robFlag (true/false), this recursive level will be limited weather we can pick the current
    house or no. Update robFlag, sum, index and go into next level of dfs.
*/




/*
	Thoughts:
	dp[i]: the best we can rob by i.
	If I'm at house i, I'll either pick i or not pick i.
	Pick i: dp[i-2] + A[i]
	Not pick i: dp[i-1]
	fn:
		dp[i] = Math.max(dp[i-1], dp[i-2] + A[i])
	Init:
		dp[0] = A[0]
		dp[1] = Math.max(A[0], A[1])
	Return:
		dp[n-1]
*/

//O(n) space
public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
    	if (A == null || A.length == 0) {
    		return 0;
    	} else if (A.length == 1) {
    	    return A[0];
    	}
    	int n = A.length; 
    	long[] dp = new long[n];
    	dp[0] = A[0];
    	dp[1] = Math.max(A[0], A[1]);

    	for (int i = 2; i < n; i++) {
    		dp[i] = Math.max(dp[i-1], dp[i-2] + A[i]);
    	}

    	return dp[n - 1];
    }
}


//O(1) space, 滚动数组。
public class Solution {
    public long houseRobber(int[] A) {
    	if (A == null || A.length == 0) {
    		return 0;
    	} else if (A.length == 1) {
    	    return A[0];
    	}
    	int n = A.length; 
    	long[] dp = new long[2];
    	dp[0] = A[0];
    	dp[1] = Math.max(A[0], A[1]);

    	for (int i = 2; i < n; i++) {
    		dp[i%2] = Math.max(dp[(i-1)%2], dp[(i-2)%2] + A[i]);
    	}

    	return dp[(n - 1)%2];
    }
}











```