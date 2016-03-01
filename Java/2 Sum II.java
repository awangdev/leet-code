M

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。

```
/*
Given an array of integers, find how many pairs in the array such that 
their sum is bigger than a specific target number. Please return the number of pairs.
Example
numbers=[2, 7, 11, 15], target=24

return 1

Challenge
Either of the following solutions are acceptable:

O(1) Space, O(nlogn) Time
Tags Expand 
Two Pointers

*/

/*
Thoughts:
After doing Trigle Count...Can we just do the same for this, move while pointers to center?
OMG. The original idea was sooooooo complicated.
*/
public class Solution {
    public int twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        Arrays.sort(nums);
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                count += (right - left);
                right--;
            } else {
                left++;
            }
        }
        return count;
    }
}

//Below are bad solutions. Don't worry about them

/*

Thoughts:
1. For loop to try each element from (i ~ end). O(n)
2. In for, do binary search on nums[i] + nums[j] > target, 
3. count += (length - j)

Note: when not found, return nums.length, because at then end, (length - length) == 0, which will be added to count.
Note: Always pin target down, and move mid to compare. Don't get confused
Also, take care of corner cases.
*/

public class Solution {
    public int twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            int index = binarySearch(nums, target - nums[i - 1], i, nums.length - 1);
            count += nums.length - index;
        }
        return count;
    }

    public int binarySearch(int[] nums, int target, int start, int end) {
        int mid;
        int sum;
        while (start + 1 < end) {
            mid = start + (end - start) /2;
            if (mid - 1 >= 0 && nums[mid-1] <= target && target < nums[mid]) {
                return mid;
            } else if (mid + 1 < nums.length &&  nums[mid] <= target && target < nums[mid + 1]) {
                return mid + 1;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] > target) {
            return start;
        }
        return (nums[end] > target) ? end : nums.length;
    }
}

//Brutle force, O(n^2)
public class Solution {
    public int twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                count += (nums[i] + nums[j] > target) ? 1 : 0;
            }
        }
    }
}

```