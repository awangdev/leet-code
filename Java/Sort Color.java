M
1531084797
tags: Array, Two Pointers, Sort, Quick Sort



#### partition array, the base of quick sort
- partition the array by pivot k = {0, 1, 2}
- 每一次partition都return starting point of the current partition
- 然后根据下一个 color, 去还没有sort 干净的那个部分, 再sort一下就好
- time O(kn), where k = 0 => O(n)
- 这里只是partion, 并不需要recursively quick sort, 所以结果是简单的O(n)

#### One pass
- have two pointers, left/right
- start tracks red, end tracks blue. Swap red/blue to right position, and left++ or right--.
- leave white as is and it will be sorted automatically
- be very careful with index i: when swapping with index right, we do not know what is nums[right], so need to re-calculate index i .
- O(n)
- Note: this one pass solution does not work if there are more than 3 colors. Need to use the regular quick sorty.

#### Counting sort
- TODO: count occurance and reassign array

```

/*
Given an array with n objects colored red, white or blue, 
sort them in-place so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, 
then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with a one-pass algorithm using only constant space?

*/

/*
Thoughts;
A easier version of Sort ColorII. Using the exact same code with different k number. 
Note, now k starts from 0.
*/
// Partition, base of quick sort, 100%
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // Sort [0, end]
        int k = 2, end = nums.length - 1;
        for (int i = 0; i < k; i++) { // 3 different colors
            end = partition(nums, 0, end, k - i - 1);
        }
        
        /**
        // Sort [start, n - 1]
        int k = 2, start = 0;
        for (int i = 0; i < k; i++) {
            start = partition(nums, start, nums.length - 1, i);
        }
        */
    }
    // quick sort partion function template
    private int partition(int[] nums, int start, int end, int pivot) {
        int low = start, high = end;
        while (low <= high) {
            while(low < high && nums[low] <= pivot) low++;
            while(high > 0 && nums[high] > pivot) high--;
            
            if (low <= high) swap(nums, low++, high--);
        }
        return low - 1;
    }

    private void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

/*
One pass, O(n)
Since there are only 3 colors: [0, 1, 2]
- left reprents the last color==0, right reprents the first color==2.
- Move pointer: swap color0 with left, swap color2 with right.
- when index i meet right, there is not need to move
*/
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0, right = nums.length - 1;
        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) swap(nums, i, left++); // we know any index < left is accurate
            else if (nums[i] == 2) swap(nums, i--, right--); // not sure what on index right, need to re-consider i, so i--
        }
    }
    
    private void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

```
