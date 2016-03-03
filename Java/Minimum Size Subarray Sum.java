M

2 pointer, O(n). 找subarray, start 或 end pointer，每次一格这样移动.

好的策略: 先找一个solution, 定住end, 然后移动start; 记录每个solution if occurs； 然后再移动end，往下找。

Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)


Note done the O(nlogn) yet

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
3.2.2016
Clearner:
1. Move 'end' for first possbile solution
2. Store it and remove one 'start' element from sum. Save any solution if occurs, in while loop.
3. Go back to step 1, until 'end' hits nums.length
*/

public class Solution {
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (end < nums.length) {
            while (end < nums.length && sum < s) {
                sum += nums[end];
                end++;
            }
            while (sum >= s) {
                min = Math.min(min, end - start);
                sum -= nums[start];
                start++;
            }
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
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