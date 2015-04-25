/*31% Accepted
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].

Example
Tags Expand 
Array Two Pointers

Thinking Process:
Two pointers, i, j
i: the regular for loop
j - jumper:
If nums[i] == nums[j], do not update nums[j]. It stays the same.
after i++, compare nums[j] with the new nums[i]. If not the same, means the position after j can have a new number that’s not duplicate of nums[j]. In this case, we update nums[j] = nums[i].
Do this until regular i runs out.
At the end, j is actually the last index of new Array. j + 1 is the size.
*/

public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }
}

