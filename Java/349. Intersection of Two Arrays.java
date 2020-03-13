E
tags: Hash Table, Two Pointers, Binary Search, Sort
time: O(m + n)
space: O(m + n)

#### Set
- 用到hashset找unique && duplicate: O(m+n)

#### Binary Search
- 可以用binary search 找数字. 
- Note:binary search一定需要array sorted: nLog(m)

```
/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
*/


/*
Thoughts:
Sol1: Use hashSet and add common item to result. 
set.contains() will be a search, which is O(1), plus all items -> O(n + m)

*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) set.add(num);
        Set<Integer> rst = new HashSet<>();
        for (int num: nums2) {
            if (set.contains(num)) rst.add(num);
        }
        int i = 0;
        int[] result = new int[rst.size()];
        for (int num : rst) result[i++] = num;
        return result;
    }
}


/*
Sol2: Binary search each item of nums1 from nums2. The runtime will be nlog(n)
Also need to sort one array fist, it's another nLog(n)
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);// nLog(n)
        Set<Integer> resultSet = new HashSet<>();
        for (int num: nums2) { // nLog(m)
            if(binarySearch(nums1, num)) {
                resultSet.add(num);
            }
        }
        int i = 0;
        int[] result = new int[resultSet.size()];
        for (int num: resultSet) {
            result[i++] = num;
        }
        return result;
    }
    
    private boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
```