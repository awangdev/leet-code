M
tags: Array, Lint


```
/*
LeetCode:
Given an array nums of n integers where n > 1,  
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? 
(The output array does not count as extra space for the purpose of space complexity analysis.)

*/





/*
LintCode
Given an integers array A.

Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1], calculate B WITHOUT divide operation.

Example
For A = [1, 2, 3], return [6, 3, 2].

Tags Expand 
Forward-Backward Traversal LintCode Copyright

Thought:
Trivial way would be first calculate the zigma(A[0]* ... A[n-1]) then divide by B[i]. However, not allowed in this question.

The other way: do for loop again and again? that will be n^2 time. 

*/



public class Solution {
    /**
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
    	if (A == null || A.size() == 0) {
    		return null;
    	}
    	ArrayList<Long> rst = new ArrayList<Long>();
    	for (int i = 0; i < A.size(); i++) {
    		long num = 1;
    		for (int j = 0; j < A.size(); j++) {
    			if (j != i) {
    				num *= A.get(j);
    			}
    		}
    		rst.add(num);
    	}
    	return rst;
    }
}



```