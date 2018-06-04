M
tags: Array

需斟酌: why reverse is need? why we are looking for k?

Permutation的规律:     
1. 从小的数字开始变化因为都是从小的数字开始recursive遍历。    
2. 正因为1的规律，所以找大的断点数字要从末尾开始： 确保swap过后的permutation依然是 前缀固定时 当下最小的。    

steps:    
1. 找到最后一个上升点，k      
2. 从后往前，找到第一个比k大的点, bigIndex      
3. swap k &&　bigIndex     
4. 最后反转 (k+1，end)      


```
/*
Given a list of integers, which denote a permutation.

Find the next permutation in ascending order.

Example
For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]

Note
The list may contains duplicate integers.

Tags Expand 
LintCode Copyright Permutation


*/

/*
http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html?_sm_au_=isV7p50vt5RqMQ1Q

Thoughts:
Not much info are given. Need to ask. It looks like:
We are dong permutation on the given numbers, and find out what's next permutation array based on given order.

Ascending order: Permutations that permutation(i) < permutation(i + 1)

Goal:
To find the next smallest permutation.
1. Find the last increasing index (a peek before decresing): k
2. Find the first bigger permutation: Well, it turns out this first bigger index is always on right side of k.
    Note: we are trying to get the least significant change on the given permuation.
    Next Step: reverse (k+1, end). This is because: before the change, right side of K will be the largest possible combination. 
    After swapping K, we need the right side to be the smallest combination. (Well, this is my understanding....
    Still a bit confused on why we take these steps in this problem)
*/

public class Solution {
    //Revers the given part of a int[]
    public int[] reverse(int start, int end, int[] nums) {
        for (int i = start, j = end; i < j; i++,j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
    
    public int[] nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        //Find the bottom: initial increasing point after decreasing. nums[k] < nums[k+1]
        int k = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                k = i;
                break;
            }
        }
        if (k == -1) {
            return reverse(0, nums.length - 1, nums);
        }
        //Find the last larger point, from right to left
        int bigIndex = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[k]) {
                bigIndex = i;
                break;
            }
        }
        //1. Swap bigger index with k; 2. Reverse the right side of k. [Try to make the smallest next permutation]
        int temp = nums[k];
        nums[k] = nums[bigIndex];
        nums[bigIndex] = temp;

        return reverse(k + 1, nums.length - 1, nums);
    }



}


```