E
tags: Array, Hash Table
time: O(n)
space: O(n)

Unsorted array, 找出是否有duplicate elemenets: 必要条件是, 这两个element的index i,j 的大小最多相差k.

#### HashSet
- 很巧妙地根据k range地条件
    - 把HashSet里面的值控制在[i - k, i]
    - 每次不断地往set里面加新元素, 从set里减去末尾index的元素
- 而set.add(x)如果遇到重复, 会return false.
- 一旦在这个length k 的 range里面, 有重复, 就符合条件. 
- Time O(n)

#### HashTable<value, List of duplicates>
- Time O(nm), m = # of duplicates. 太慢
- 记录每个element value的index in the list
- 一旦有重复element重复, 就把整个list of indexes 端出来, 查看有没有符合条件的: (index - i) <= k


#### 这两种做法的区别很有艺术感觉
- 方法1是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中, 剩下的就不看了.
- 方法2是把符合条件的index找出来, 集中处理, 但是所有candidate都会选出来
- 就好像招人一样: 一种是遇到好的就停止; 第二种是看过所有人, 从中选拔最好的. 显然第一种更快.


```
/*
Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that:
nums[i] = nums[j] and the absolute difference between i and j is at most k.
*/

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
        Set<Integer> set = new HashSet<>();
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
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                for (int index : map.get(nums[i])) { // wasting time
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

```