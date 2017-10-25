/*
Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

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

