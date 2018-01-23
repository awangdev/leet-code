M
1516683660

其实也就是3sum的变形, 或者而说2sum的变形. 主要用2 pointers来做.
注意, 在选index时候每次定好一个 [0 ~ i], 在这里面找点start, end, 然后i 来组成triangle.
Note巧妙点:
在此之中, 如果一种start/end/i 符合, 那么从这个[start~end]中, 大于start的都可以, 所以我们count+= end-start.
反而言之, <end的其他index, 就不一定能符合nums[start] + nums[end] > nums[i]

```
/*
Valid Triangle Number
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

 */

/*
Thoughts:
Follow the triangle rules, we'll sort, and use two pointers to count all possible triangles.
When sorted, if at one point moving out of range, any point going beyound will be out of range.
*/

class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i++) {
            int start = 0;
            int end = i - 1;
            while (start < end) {
                if (nums[start] + nums[end] > nums[i]) {
                    count += end - start; // any num[index] where start < index < end, can replace start and fit.
                    end--;
                } else {
                    start++;
                }
            }
        }
        return count;
    }
}


/*
Given an array of integers, how many three numbers can be found in the array, 
so that we can build an triangle whose three edges length is the three numbers that we find?

Example
Given array S = [3,4,6,7], return 3. They are:

[3,4,6]
[3,6,7]
[4,6,7]
Given array S = [4,4,4,4], return 4. They are:

[4(1),4(2),4(3)]
[4(1),4(2),4(4)]
[4(1),4(3),4(4)]
[4(2),4(3),4(4)]
Tags Expand 
Two Pointers LintCode Copyright
*/
/*
Thoughts:
Pick 3 integers that fits the condition: 
A + B > C
B + C > A
A + C > B
If we sort the input, then we know A <= B <= C, so we can remove 2 conditoins above and only have:
A + B > C
That is, Pick one C, and pick two integers A,B in front. Similar to TWO SUM II.
Have a fixed C as target, and find A + B > target in the remaining array on left of C. 
How about just use 2 pointers left, right, and compare with a C (s[i] in for loop)
Time: O(n^2)

Note: don't forget to sort
*/

public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
    	if (S == null || S.length == 0) {
    		return 0;
    	}
    	Arrays.sort(S);
    	int count = 0;
    	for (int i = 0; i < S.length; i++) {
    		int left = 0;
    		int right = i - 1; //at least 1 step left from C
    		while (left < right){
    			if (S[left] + S[right] > S[i]) {
	    			count += (right - left);
	    			right--;
	    		} else {//(S[left] + S[right] <= S[i]) 
	    			left++;
	    		}
    		}
    	}
    	return count;
    }
}


```