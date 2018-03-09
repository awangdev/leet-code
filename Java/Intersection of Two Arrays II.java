E
1518453586
tags: Hash Table, Two Pointers, Binary Search, Sort

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]

```
/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

/*
Thoughts:
As problem suggests, items appeared in nums1 && appears in nums2 should be recorded. Use Map.
Space O(n)
Time O(n)
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        final List<Integer> result = new ArrayList<>();
        final Map<Integer, Integer> map = new HashMap<>();
        // fill nums1 in to map
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        // check nums2 against the map
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] rst = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            rst[i] = result.get(i);
        }
        return rst;
    }
    
}

/*
Thoughts:
Result can be in any other: sort nums1, nums2, then find each in the other?
*/
/*
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        List<Integer> indexes = new ArrayList<>();
        Arrays.sort(nums1);
        for (int i = 0; i < nums2.length; i++) {
            int index = binarySearch(nums1, indexes, nums2[i]);
            if(index != -1) {
                indexes.add(index);
            }
        }
        int[] rst = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {
            rst[i] = nums1[indexes.get(i)];
        }
        return rst;
    }
    
    private int binarySearch(int[] nums, List<Integer> indexes, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                if (indexes.contains(mid)) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}*/
```