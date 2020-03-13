E
tags: Quick Sort, Quick Select, Array, Two Pointers, Lint
time: O(n)
space: O(logN)

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 跟`kth largest element in an Array`的 template一样.
- quickSelect 可以找到 kth 最小的元素
    - 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- 主要步骤: 
    - 1. partition
    - 2. check end state `pivot index ?= target index`
    - 3. recursive call one part of the array 
- time: 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
    - n + n/2 + n/4 + n/8 + ....+ 1 = O(2n) = O(n)
- space: O(logn), based on recursive stacks


```
/*
https://www.lintcode.com/problem/median/description
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
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        return quickSelect(nums, 0, n - 1, (n - 1)/ 2);
    }
    
    /*
        target = (n-1)/2, the median index
        - end state: the returned partition pivot equals target
        - verify `index < target` and continue quick select
    */
    private int quickSelect(int[] nums, int start, int end, int target) {
        int index = partition(nums, start, end);
        if (index == target) return nums[index];
        else if (index < target) return quickSelect(nums, index + 1, end, target);
        else return quickSelect(nums, start, index - 1, target);
    }
    
    /*
        Partition the array and return the adjusted pivot index.
    */
    private int partition(int[] nums, int low, int high) {
        int pivotIndex = high; // end
        int pivot = nums[pivotIndex];

        while (low < high) {
            while (low < high && nums[low] < pivot) low++; // stop when nums[low] >= pivot
            while (low < high && nums[high] >= pivot) high--; // stop when nums[high] < pivot
            swap(nums, low, high);
        }
        
        swap(nums, low, pivotIndex);

        return low;
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

```