E
tags: Hash Table, Two Pointers, Binary Search, Sort
time:O(n)
space:O(n)

#### HashMap
- Map of nums1: <num, # appearance>
- check nums2 against nums1 map
- time:O(n + m)
- space:O(n + m)

#### Binary Search
- 

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
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = buildCountMap(nums1);

        // check nums2 against the map
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        
        return convertToArray(result);
    }
    
    // fill nums into map
    private Map<Integer, Integer> buildCountMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num) + 1);
        }
        return map;
    }
    
    private int[] convertToArray(List<Integer> list) {
        int[] rst = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rst[i] = list.get(i);
        }
        return rst;
    }
}


```