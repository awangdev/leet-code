M
1517465602
tags: Two Pointers, Array

一般的O(n3)肯定不行。在此基础上优化。
发现j,k满足条件时候，(k - j)就是所有 sum <target的情况了。
而一旦>target, 又因为j不能后退，只能k--，那么问题就被锁定了. 这样可以做到O(n2)

```
/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]

Follow up:
Could you solve it in O(n2) runtime?

Tags: Array Two Pointers
Similar Problems:(M) 3Sum, (M) 3Sum Closest

*/

/*
Thoughts:
For loop over initial selection. Have 2sum solution within (2 pointer).
When < target, count+= end-start, start++.
When >= target, end--.
*/
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        int start, end;
        for (int i = 0; i < nums.length - 2; i++) {
            int firstNum = nums[i];
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                if (nums[i] + nums[start] + nums[end]< target) {
                    count += end - start;
                    start++;
                } else {
                    end--;
                }
            }
        }
        return count;
    }
}

/*
Thoughts:
Similar to 3 sum, but ofcourse, this one check on '<' so we can not use HashMap anymore.
Basic concept is to fix first number, then check for the rest two numbers, see if they addup < target.
When checking j and k, realize something nice:
	if nums[j] + nums[k] < target - nums[i], that means for all index <= k will work, so directly add (k - j) to result (that's: index = j+1, j+2, ....,k)
	also, move j forward for next round.
OR, if three-add-up >= target, since j can only increase, we do k-- to make the three-add-up smaller

Note:
Don't forget to sort, otherwise the sequence/order is unpredictable
*/
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
        	return 0;
        }
        Arrays.sort(nums);
        int rst = 0;
        for (int i = 0; i < nums.length - 2; i++) {
        	int j = i + 1; 
        	int k = nums.length - 1;
        	while (j < k) {
        		if (nums[i] + nums[j] + nums[k] >= target) {
        			k--;
        		} else {
        			rst += (k - j);
        			j++;
        		}
        	}
        }//END for
        return rst;
    }
}
```