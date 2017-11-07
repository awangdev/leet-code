E

方法1: HashTable<value, list of duplicates>, brutly check agains the list
方法2: 很巧妙地根据k range地条件, 把HashSet里面的值控制在[i - k, i]. 那么一旦match, 就符合条件. 

这两种做法很艺术:
一般的想法是把符合条件的index找出来, 集中处理
第二种做法是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中.

```
/*
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that:
nums[i] = nums[j] and the absolute difference between i and j is at most k.
*/


/*
Thoughts:
Store in hashmap<value, index>. When there is a duplicate, check against k.
Though, quite slow: O(n * h), where h is the possible duplicates. In the extreme case when n = h, it becomes O(n^2)
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                for (int index : map.get(nums[i])) {
                    if (Math.abs(index - i) <= k) {
                        return true;
                    }
                }
            } else {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        return false;
    }
}

/*
Thoughts:
1. Think of k as the upper limit of the range that we want to pick our i and j.
2. When i is in [0 ~ k], we can pick and j that's also in [0 ~ k]. This can be checked in a for loop, with a HashSet.
3. Once i pass k, we need to remove any value that's in range [0, i-k) from the set, because they are out of range.
They are no longer fit the condition to duplicate with nums[i], regardless if they are duplicates or not

Note: set.add(..) return false if the value already exist in set.
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
```