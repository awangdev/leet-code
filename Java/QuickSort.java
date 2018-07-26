M
1526358989
tags: Sort, Quick Sort

implement quick sort.

#### Quick Sort
- 首先partition. 返还一个partition的那个中间点的位置: 这个时候, 所有小于nums[partitionIndex] 都应该在 partitionIndex左边
- 然后劈开两半
- 前后各自 quick sort, recursively
- 注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.
- Time O(nlogn), Space: O(1)

```
/*
Quick Sort a array.
*/

public class Solution {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        dfs(nums, 0, nums.length - 1);
    }
    
    /**
        Partition one side, and recursively partition(swaping).
        Once one side finished, move on to partition && sorting the other side.
     */
    private void dfs(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionIndex = partition(nums, start, end);
        dfs(nums, start, partitionIndex - 1);
        dfs(nums, partitionIndex, end);
    }
    
    /**
        Use two pointers: find the two points on left/rigth of the pivot that are startNum < pivot < endNum.
        Swap them, and keep moving star++, end--.
        This operation partition the array into 2 parts:
        - numbers less than pivot (unsorted) on left of pivot
        - numbers breater than pivot (unsorted) on right of pivot
     */
    private int partition(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
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
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private void printArray(int[] nums, String str) {
        System.out.print(str);
        if (nums == null || nums.length == 0) {
            System.out.println();
            return;
        }
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("Implement and test quick sort");
        
        int[] array = {5,3,2,7,5,4,1,1,2,9,5,3, -1};
        sol.quickSort(array);
        sol.printArray(array, "Return: ");
        
        array = null;
        sol.quickSort(array);
        sol.printArray(array, "Return Empty: ");
        
        array = new int[]{ - 1, 2};
        sol.quickSort(array);
        sol.printArray(array, "Return: ");
        
        array = new int[]{ 1,1,1,1,1};
        sol.quickSort(array);
        sol.printArray(array, "Return: ");
        
        array = new int[]{ 19,8,7,6,5,4,3,2,1};
        sol.quickSort(array);
        sol.printArray(array, "Return: ");
    }
}

```