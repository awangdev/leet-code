/*
Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Note
There is only one majority number in the array

Example
For [1, 2, 1, 2, 1, 3, 3] return 1

Challenge
O(n) time and O(1) space

Thinking process:
Need to think the relations of 3 parts of the array:
1. Assume a > 1/3, which is the candidate were are looking for
	However, only konwing a appears more than 1/3 of the array, does not mean there is no other element appears more than 1/3, for example, aaaaabcccccc, a = 5/12, b = 6/12. The majority is b.
2. Consider another element b, which is a different element rather than a. Discuss the 2 conditions of b.
3. Consider the rest of the array is in set c, which can contain all different elements.

Discuss relations between a, b, c
Assume a > 1/3
Case1: b < 1/3 
	given: a > 1/3, means b + c < 2/3, known b < 1/3
	get: c < 1/3
	conclusion: a is the majority
Case2: b > 1/3
	given: a + b ? 2/3
	get: c < 1/3
	conclusion: return the greater element# of a or b

Implementation:
1. Have valA and valB two pointers to represent a and between
2. Check valA against the array to count duplicates, similar as in Majority Number I 
3. Check valB against .....
4. Note: at each index i, only one of valA or valB is checked. That means, we evaluate a and b individually against the section c.
5. At the end, we found 2 candidates: a and b. Now compare the # of a and b to see which is greater.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int valA = 0; 
        int valB = 0;
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (countA == 0 || nums.get(i) == valA) {
                valA = nums.get(i);
                countA++;
            } else if (countB == 0 || nums.get(i) == valB) {
                valB = nums.get(i);
                countB++;
            } else {//None of a || b matches
                countA--;
                countB--;
                if (countA == 0) {
                    countA = 1;
                    valA = nums.get(i);
                } else if (countB == 0) {
                    countB = 1;
                    valB = nums.get(i);
                }
            }
        }//For
        
        countA = 0; 
        countB = 0;
        for (int num : nums) {
            countA += num == valA ? 1 : 0;
            countB += num == valB ? 1 : 0;
        }
        return countA > countB ? valA : valB;
    }
}

