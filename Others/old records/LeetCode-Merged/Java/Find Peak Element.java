/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.

Tags: Array, Binary Search

*/

/*
Thoughts:
Easy brutle force: pick any element, see it's increasing or decresing, then move forward the increasing directin. O(n)
Here, try binary search method
Note: Special case of 3 element, which could run into infinite loop, so check start + 1 < end. (That is, when start, mid, end are 3 adjacent increasing/decresing point, they will run into infinite loop)

Note2: because of missing case, 1,0,1 case when mid is at '0'. It needs either direction.
*/
public class Solution {
    public int findPeakElement(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return 0;
		}        
		int start = 0; // 0
		int end = nums.length - 1;// 2
		int mid;
		while (start + 1 < end) {
			mid = start + (end - start)/2;//1
			if (mid <= 0 || mid >= nums.length - 1) {//start and end are adjacent, jump to end
				break;
			}
			if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
				return mid;
			} else if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
				start = mid + 1;
			} else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) {
				end = mid - 1;
			} else {//1,0,1 case
				end = mid - 1;
			}
		}//end while
		return nums[start] > nums[end] ? start : end;
    }
}
