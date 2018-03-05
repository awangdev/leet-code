M

用Quick Sort 里面partion的一部分。     
partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
没找到继续partion recursively.

sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)

Quick Sort:   
每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
找到一个low>pivot, high<pivot, 也就可以swap了。    
得到的low就是当下的partion point了


```

/*
LeetCode:
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/


/*
Find K-th largest element in an array.

Example
In array [9,3,2,4,8], the 3rd largest element is 4

In array [1,2,3,4,5], the 1st largest element is 5, 
2nd largest element is 4, 3rd largest element is 3 and etc.

Note
You can swap elements in the array

Challenge
O(n) time, O(1) space

Tags Expand 
Quick Sort Sort

*/

/*

Thoughts:
Almost the same as the Median problem: 
the only difference is, this one is not looking for the middle point, but for the last kth element. 
Using the same quick sort code with minor modifications, and we can solve this problem.
*/

class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        return helper(nums, 0, nums.size() - 1, nums.size() - k);
    }
    
    public void swap( ArrayList<Integer>nums, int x, int y){
        int temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    }
    
    public int helper( ArrayList<Integer> nums, int start, int end, int mid) {
        int pivot = end;
        int num = nums.get(pivot);
        int low = start;
        int high = end;
        while (low < high) {
            while(low < high && nums.get(low) < num) {
                low++;
            }
            while(low < high && nums.get(high) >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);
        if (low == mid) {
            return nums.get(low);
        } else if (low < mid) {
            return helper(nums, low + 1, end, mid);
        } else {
            return helper(nums, start, low - 1, mid);
        }
    }
};



```