M

分三份：a b c考虑。若a, countA++, 或b, countB++，或c，countA--,countB--.

最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.

比较a和b哪个大，就return哪一个。

```
/*
Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Note
There is only one majority number in the array

Example
For [1, 2, 1, 2, 1, 3, 3] return 1

Challenge
O(n) time and O(1) space

*/

/*

Thinking process:
Need to think the relations of 3 parts of the array:
1. Consider a and b.
2. Consider the rest of the array is in set c, which can contain all different elements.
3. Two if statement makes sure a and b fall into 1/3 of the conditions.

Discuss relations between a, b, c

Implementation:
1. Have valA and valB two pointers to represent a and between
2. Check valA against the array to count duplicates, similar as in Majority Number I 
3. Check valB against .....
4. Note: at each index i, only one of valA or valB is checked. That means, we evaluate a and b individually against the section c.
5. At the end, we found 2 candidates: a and b. Now compare the # of a and b to see which is greater.
*/
public class Solution {
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


```