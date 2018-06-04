M
1528074589
tags: Backtracking, DFS, Permutation

#### Recursive: Backtracking
- Given a remaining list: 取, 或者不取
- Improvement: maintain list (add/remove elements) instead of 'list.contains'
- time O(n!): visit all possible outcome
- T(n) = n * T(n-1) + O(1)

#### Iterative: Insertion
- 插入法:
- 1. 一个一个element加进去
- 2. 每一次把rst里面的每个list拿出来, 创建成新list, 然后选位置加上new element
- 3. 加新元素的时候, 要在list的每个位置insert, 最终也要在原始的list末尾加上new element
- 还是O(n!), 因为rst insert O(n!)个permutations
- 但是比dfs要快, 因该是因为 # of checks 少: 不需要check list.size(), 不需要maintain remaining list.

#### Previous Notes
- 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍
- Time O(n!)
- A bit slower, possibly because of the polling and saving the entire list every time


```
/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Challenge
Do it without recursion

Tags Expand 
Recursion Search

*/

/*
Thoughts:
1. Backtracking: could start from any digit, until all elements are used
2. Need a easily-resizable object to pass through DFS function such that we can maintain the items left at current level
3. Each level loops through that levelList, minums one item and move to next level.
4. Check condition: when index reach to end, add to result

Slightly optimized becaue we are not using list.contains(...) which essentially is a search O(nLog(n))
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        dfs(result, new ArrayList<>(), numList);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> levelList, List<Integer> remainingList) {
        if (remainingList.size() == 0) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < remainingList.size(); i++) {
            int num = remainingList.get(i);
            levelList.add(num);
            remainingList.remove(i);

            dfs(result, levelList, remainingList);

            levelList.remove(levelList.size() - 1);
            remainingList.add(i, num);
        }
    }
}

/*
Build permutation by insertion:
- Start with 1st element.
- Add 2nd element in front/end of 1st element; and add the new layout to rst
- Add 3rd element in front, between, end ...
- Loop over elements in nums => rst list => each row
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        rst.add(new ArrayList<>());
       
        for (int i = 0; i < nums.length; i++) { // Pick element
            int currRstSize = rst.size();
            for (int j = 0; j < currRstSize; j++) { // pick base list
                List<Integer> baselist = rst.get(j);
                // Pick slot to insert element
                for (int index = 0; index < baselist.size(); index++) {
                    baselist.add(index, nums[i]);
                    rst.add(new ArrayList<>(baselist));
                    baselist.remove(index);
                }
                baselist.add(nums[i]);
            }
        }

        return rst;
    }
}



// Previous notes

/*
    non-recursive.
    Set up a queue, add all elements into it.
    give a size/level variable to keep track of cycle.
    Slow, because every queue iteration requires O(n) to maintain entire list
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        int n = nums.length;
        
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> list;
        for (int num : nums) {
            list = new ArrayList<>();
            list.add(num);
            queue.offer(new ArrayList<>(list));
        }

        while (!queue.isEmpty()) {
            list = queue.poll();
            if (list.size() == n) {
                rst.add(new ArrayList<>(list));
                continue;
            }
            List<Integer> candidates = new ArrayList<>(numList);
            candidates.removeAll(list);
            for (int num : candidates) {
                list.add(num);
                queue.offer(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
        }

        return rst;
    }
}
/*
BAD: contains() is O(logn)
Thinking Process:
1. Very similar idea: choose or not choose (1 / 0)
    A key point is: when jumpped into next level of recursion, the 'list' will surely be filled up until it reach the max length.
    That is: when 'not choose', the empty seat will be filled eventually with points not existed in 'list'.
2. The recursion does not end before the list is filled.
3. A for loop is doiong the filling of blank. Any order/combination will occur.
*/

class Solution {
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return rst;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(rst, list, nums);
        return rst;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, ArrayList<Integer> nums) {
        if (list.size() == nums.size()) {
            rst.add(new ArrayList<Integer>(list));
            return ;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!list.contains(nums.get(i))) {
                list.add(nums.get(i));
                helper(rst, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }
}
//Same solution as above, for Leetcode：
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        helper(rst, new ArrayList<Integer>(), nums);
        return rst;
    }
    
    public void helper(List<List<Integer>> rst, ArrayList<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            rst.add(new ArrayList<Integer>(list));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                helper(rst, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }
}


```