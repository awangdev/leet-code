M
1519835840
tags: Array, Two Pointers, Binary Search

方法1:
2 pointer, O(n). 找subarray, start 或 end pointer，每次一格这样移动.

好的策略: 
1. 先找一个solution, 定住end. 
2. 然后移动start; 记录每个solution if occurs
3. 然后再移动end，往下找。

Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)

方法2:
Double for loop, base i 每次只+1, 所以最后O(n^2)

方法3:
Binary Search, O(nLogN)
Not done yet

```
/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return -1 instead.

Example
Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.

Challenge
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Tags Expand 
Two Pointers Array
*/


/*
Thoughts:
Two pointer. 
1. Find the end which has sum >= s, move start forward as much as possible until sum < s.
2. Use new start and end to look for next new end.
*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start, end, sum;
        start = end = sum = 0;
        int min = Integer.MAX_VALUE;
        while (end < nums.length) {
            while (sum < s && end < nums.length) {
                sum += nums[end];
                end++;
            }
            // move start and log any possible min
            while (sum >= s && start >= 0) {
                min = Math.min(min, end - start);
                sum -= nums[start];
                start++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}


/*
Previous notes on O(n) solution
1. Move 'end' for first possbile solution
2. Store it and remove one 'start' element from sum. Save any solution if occurs, in while loop.
3. Go back to step 1, until 'end' hits nums.length
*/

/*
Thoughts:
Brutly, two pointer checking. Record Min.
Optimize: if futher length > min, no need to move the right pointer
Worst case: n + (n-1) + (n-2) ....+ 1 = O(n^2)
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            if (sum >= s) {
                result = 1;
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j - i >= result) {
                    break;
                }
                sum += nums[j];
                if (sum >= s) {
                    result = Math.min(result, j - i + 1);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

/*
Thoughts: old solution O(n), not prefered.
Create a subarray range: (i,j). Use i as start and j as end. Check if within the range, sum >= s.
Shift the range every time: i += 1.

2 pointer: 
一个做base, 每次动一格：i.
一个做前锋，加到满足条件为止。
Note: 当sum >= s 条件在while里面满足时，end是多一个index的。所以result里面要处理好边缘情况：(end-1) 才是真的末尾位置，然后计算和开头的间隙：
（end - 1） - start + 1;


*/

public class Solution {
    public int minimumSize(int[] nums, int s) {
    	if (nums == null || nums.length == 0) {
    		return -1;
    	}
    	int start = 0;
    	int end = 0;
    	int min = Integer.MAX_VALUE;
    	int sum = 0;
    	for (; start < nums.length; start++) {
    		while(end < nums.length && sum < s) {
    			sum += nums[end];
    			end++;
    		}
    		if (sum >= s) {
    			min = Math.min(min, (end-1) - start + 1);
    		}
    		sum -= nums[start];
    	}
    	return min == Integer.MAX_VALUE ? -1 : min;
    }
}




```