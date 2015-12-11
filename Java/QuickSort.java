/*
Quick Sort a arraylist.

Self test.
*/

class Solution{
	public int[] quickSort(int[] nums){
		if (nums == null || nums.length == 0) {
			return nums;
		}
		//quick sort divide into 2 parts


	}


	public void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp; 
	}

	public void partition(int[] nums, int start, int end, int pivot){
		while (start <= end) {
			while (start <= end && nums[start] <= nums[pivot]) {
				start++;
			}
			while (start <= end && nums[end] >= nums[pivot]) {
				end--;
			}
			if (start <= end) {
				swap(nums, start, end);
				start++;
				end--;
			}
		}
		return start;
	}
}