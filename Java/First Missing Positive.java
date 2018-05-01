H
1525157745
tags: Array

给一串无序数字, 有负数: 找这个array里面第一个 missing的 positive integer

missing positive integer 其实是以 [1, n] 来做比较的.

#### Array分析, index 技巧
- 用while loop, 不断地尝试把 number 送到该放的地方
- 如果 index = nums[i] 超过了nums.length, 当然就不移动了
- 注意: 检查 val != nums[val], avoid infinitely loop
- 检验: nums[i] 是否等于 i, 如果不对, 就找到了结果

#### Edge Case
- 如果nums==null, 其实missing positive integer 自然而然是 1
- validation时, 有可能这串数字里没有断开的integer, 但是最大的integer在首位 (因为index超标, 无法被放到正确的地方)
- 这种时候, n被放在 index 0, 其实就是说, 下一个integer应该是 n + 1
- 最终, 如果array本来就是完全sorted, 也不缺, 还符合角标的条件, 那么唯一下一个就是array范围外的第一个positive number: n

```
/*
Given an unsorted integer array, find the first missing positive integer.

Example
Given [1,2,0] return 3, and [3,4,-1,1] return 2.

Challenge
Your algorithm should run in O(n) time and uses constant space.

Tags Expand 
Array

*/


class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        if (nums.length == 1) {
            return nums[0] == 1 ? 2 : 1;
        }
        int n = nums.length;
        // Move value to correct position with best efforts. O(n)
        int i = 0;
        while (i < n) {
            int val = nums[i];
            if (val != i && val >= 0 && val < n && val != nums[val]) { // val != nums[val], avoid infinitely loop
                int temp = nums[val];
                nums[val] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        // Check, O(n)
        // Skip index 0, only care about positive digits
        for (i = 1; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        // max value is n and stored at index 0 
        if (nums[0] == n) {
            return n + 1;
        }

        // max value if (n - 1) and n will be the next positive number (outside of array)
        return n;
    }
}

// Wrong, Using O(nLogN) time
/**

Thoughts:
It means: after it's sorted, what's the first missing postive int counted from 1 ---> more

1. Arrays.sort();
2. count = first non-zero element in A.
3. count +1, and see if maches the current A[i]?

NOTE:
Deal with negative and positive number separately
Watch out for redundant number: ask if the list has duplicated elements
 */
public class Solution {
    public int firstMissingPositive(int[] A) {
    	if (A == null || A.length == 0) {
    		return 1;
    	}
    	Arrays.sort(A);
    	int count = -1;
    	for (int i = 0; i < A.length; i++) {
    		if (A[i] > 0) {
    			if (count < 0) {//process 1st positive element
	    			count = A[i];
	    			if (count != 1) {
	    				return 1;
	    			}
    			} 
    			else if (A[i] == A[i - 1]) {//watch out for duplicates
    				count--;
    			}
				else if(A[i] != count) {//if not match, kick out
					return count;
				}	
    			count++;
    		}
    	}
    	if (count < 0) {//if all negative, return 1
    		return 1;
    	}
    	return count;
    }
}
```