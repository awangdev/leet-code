代码是不难的. 

首先partition. 返还一个partition的那个中间点的位置。
然后劈开两半。
前后各自 quick sort, recursively

注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.


但是： 在partition array那个题目里面，第二个 nums[end] >= pivot, 是要去加上这个 ‘=’的
```
/*
Quick Sort a array.

Self test.

*/

class Solution{
	public void quickSort(int[] nums){
        if (nums == null || nums.length == 0) {
            return;
        }
        sortHelper(nums, 0, nums.length - 1);
    }

    public void sortHelper(int[] nums, int start, int end){
        if (start >= end) {
            return;
        } else {

            int partitionPoint = partition(nums, start, end);
            sortHelper(nums, start, partitionPoint - 1);
            sortHelper(nums, partitionPoint, end);
        }
    }

    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp; 
    }

    public int partition(int[] nums, int start, int end){
        int mid = start + (end - start)/2;
        
        int pivot = nums[mid];
        while (start <= end) {
            while (start <= end && nums[start] < pivot) {
                start++;
            }
            while (start <= end && nums[end] > pivot) {
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
```