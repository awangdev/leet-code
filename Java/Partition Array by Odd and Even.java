/*
Partition an integers array into odd number first and even number second.

Example
Given [1, 2, 3, 4], return [1, 3, 2, 4]

Challenge
Do it in-place.

Tags Expand 
Two Pointers Array

Thougths:
Use two pointers: nextOddPt, firstEvenPt
1. Whenever nextOddPt > firstEvenPt, swapt them
2. Incrase nextOddPt in a for loop
Note:
After each swap, have to start checking again from beginning-switching point, which will be firstEvenPt. Need to set i = firstEvenPt.
However, since for loop will do i++, we need to set i = firstEvenPt - 1;
And firstEvenPt only needs to be update once so use -1 to check if it's set.
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
    	if (nums == null || nums.length == 0){
    		return;
    	}
    	int nextOddPt = -1;
    	int firstEvenPt = -1;
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] % 2 == 1) {
    			nextOddPt = i;
    		} else {
    			if (firstEvenPt == -1) {
    				firstEvenPt = i;
    			}
    		}
    		if (nextOddPt > firstEvenPt && firstEvenPt != -1) {
    			int temp = nums[nextOddPt];
    			nums[nextOddPt] = nums[firstEvenPt];
    			nums[firstEvenPt] = temp;
    			i = firstEvenPt - 1;
    			firstEvenPt = -1;
    		}
    	}
    }
}
