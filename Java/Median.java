E
1525410641
tags: Quick Sort, Quick Select, Array

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 跟`kth largest element in an Array`的 template一样.
- 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
- quickSelect 可以找到 kth 最小的元素
- 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- quick select 的template要熟悉一下, 一下子可能想得到, 但写不出来
- 主要步骤: partition, dfs, only recur on one part of the array 


```
/*
Given a unsorted array with integers, find the median of it. 

A median is the middle number of the array after it is sorted. 

If there are even numbers in the array, return the N/2-th number after sorted.

Example
Given [4, 5, 1, 2, 3], return 3

Given [7, 9, 4, 5], return 5

Challenge
O(n) time.

Tags Expand 
LintCode Copyright Quick Sort Array


*/

/*
Thoughts:
Goal: Find the median, which is N/2-th. 
Enumerate a few examples and find median index = (nums.length  - 1) / 2

Quick sort has the nature of putting all smaller items on left, and larger numbers on right.
If that pivot point happends to hit the midian index, that's our target.
We don't necessarily need to sort all items, but just need to locate the median index.
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, (n - 1)/ 2);
    }
    
    private int quickSelect(int[] nums, int start, int end, int target) {
        int index = partition(nums, start, end);
        if (index == target) {
            return nums[index];
        } else if (index < target) {
            return quickSelect(nums, index + 1, end, target);
        } else {
            return quickSelect(nums, start, index - 1, target);
        }
    }
    
    private int partition(int[] nums, int low, int high) {
        int pivot = high; // end
        int pivotValue = nums[pivot];

        while (low < high) {
            while (low < high && nums[low] < pivotValue) {
                low++;
            }
            while (low < high && nums[high] >= pivotValue) {
                high--;
            }
            swap(nums, low, high);
        }
        
        swap(nums, low, pivot);

        return low;
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}


/*
    Recap 12.09.2015.
    O(n) means just run through it. It's similar to Partition array: it tries to split the list into 2 parts, and find the pivot.
*/

/*
Thoughts:
Use standard quick sort, but the goal is to look for the middle point. 
1. Get middle point: remember to -1 because we are looking for position, rather than length.
2. Increase low pointer until find a point >= piviot
3. Decrease high pointer until find a point < poviot
4. Swap the low and high: this set the first value greather than pivot to the right, and first avlue less than pivot to the left.
5. after low and high pointer meets, swap low with the piviot: simply because pivot should be the break point of low and high
6. at the end, the low sould be the middle point, which is the point we are looking for. return corresponding recursive helper.

*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        return quickSort(nums, 0, n - 1, (n - 1)/ 2);
    }
    
    private int quickSort(int[] nums, int start, int end, int target) {
        int pivot = end;
        int pivotValue = nums[pivot];
        int low = start;
        int high = end;
        while (low < high) {
            while (low < high && nums[low] < pivotValue) {
                low++;
            }
            while (low < high && nums[high] >= pivotValue) {
                high--;
            }
            swap(nums, low, high);
        }
        
        swap(nums, low, pivot);
        // dfs
        if (low == target) {
            return nums[low];
        } else if (low < target) {
            return quickSort(nums, low + 1, end, target);
        } else {
            return quickSort(nums, start, low - 1, target);
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}


```