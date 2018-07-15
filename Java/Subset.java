M
1531543789
tags: Array, Backtracking, DFS, BFS, Bit Manipulation
time: O(2^n)
space: O(2^n)

给一串unique integers, 找到所有可能的subset. result里面不能有重复.

#### DFS
- dfs的两种路子: 1. pick&&skip dfs, 2. for loop dfs
- 1. pick&&skip dfs: 取或者不取 + backtracking. 当level/index到底，return 一个list. Bottom-up, reach底部, 才生产第一个solution.
- 2. for loop dfs: for loop + backtracking. 记得：做subset的时候, 每个dfs recursive call是一种独特可能，先加进rst.  top-bottom: 有一个solution, 就先加上.
- Time&&space: subset means independent choice of either pick&&not pick. You pick n times: `O(2^n)`, 3ms

#### Bit Manipulation
- n = nums.length, 那么在每一个index, 都是 pick / not pick: 0/1
- 考虑subset index 0/1的bit map: range 的就是 [0000...00 ~ 2^n-1]
- 每一个bitmap就能展现出一个subset的内容: all the 1 represents picked indexes
- 做法:
- 1. 找出Range
- 2. 遍历每一个bitmap candidate
- 3. 对每一个integer 的 bit representation 遍历, 如果是1, add to list
- time: O(2^n * 2^n) = O(4^n), still 3ms, fast.

#### Iterative, BFS
- Regular BFS, 注意考虑如果让one level to generate next level
- 1. 用queue来存每一次的candidate indexes
- 2. 每一次打开一层candiates, add them all to result
- 3. 并且用每一轮的candidates, populate next level, back into queue.
- should be same O(2^n), but actual run time 7ms, slower



```
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */
// pick&&skip dfs, backtracking, 
// bottom-up: reach leaf to save result
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        dfs(result, new ArrayList<>(), nums, 0); // dfs with depth = 0
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int depth) {
        if (depth >= nums.length) { // closure case
            result.add(new ArrayList<>(list));
            return;
        }
        // pick
        list.add(nums[depth]);
        dfs(result, list, nums, depth + 1);

        // backtracking, and move to the not-pick option
        list.remove(list.size() - 1);
        dfs(result, list, nums, depth + 1);
    }
}


// for loop dfs:
// top-down, add each step as solution, as see fit
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result; // edge case

        List<Integer> list = new ArrayList<>();
        result.add(new ArrayList<>(list));

        // dfs with depth = 0
        dfs(result, list, nums, 0);    
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int depth) {
        for (int i = depth; i < nums.length; i++) {
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            dfs(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}


// Bit manipulation
class Solution {
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // edge case
        if (nums == null || nums.length == 0) {
            return result;
        }
        // set bit range
        int n = nums.length;
        long maxRange = (long) Math.pow(2, n) - 1;

        // for loop for all integer representation of the bit map
        for (int i = 0; i <= maxRange; i++) {
            List<Integer> list = new ArrayList<>();
            int index = 0;
            int num = i;
            // bit & each index to find if that index is picked
            while (num != 0) {
                if ((num & 1) == 1) {
                    list.add(nums[index]);
                }
                num = num >> 1;
                index++;
            }
            result.add(list);
        }
        return result;
    }
}


// BFS, Queue
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // edge, init result
        if (nums == null || nums.length == 0) {
            return result;
        }
        // init queue
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> initialList = new ArrayList<>();
        queue.offer(initialList);

        // while queue not empty
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                List<Integer> indexRow = queue.poll();
                // add result
                List<Integer> list = new ArrayList<>();
                for (int index : indexRow) {
                    list.add(nums[index]);
                }
                result.add(list);

                // populate queue with index
                int endIndex = indexRow.size() == 0 ?
                    0 : indexRow.get(indexRow.size() - 1) + 1;
                for (int i = endIndex; i < nums.length; i++) {
                    indexRow.add(i);
                    queue.offer(new ArrayList<>(indexRow));
                    indexRow.remove(indexRow.size() - 1);
                }
                size--;
            }
        }
        return result;
    }
}


/*
LintCode
Given a set of distinct integers, return all possible subsets.

Example
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Note
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Challenge
Can you do it in both recursively and iteratively?

Tags Expand 
Recursion Facebook Uber
*/
/*
Previous Notes:

最基本的递归题目。   
坑：记得一开头sort一下 nums。 因为要升序。那么整体就是O(nlogn)


 */
/*
    12.06.2015 recap.
    Choose or not choose
    When level == nums.length, return a entry.
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(rst, list, nums, 0);
        
        return rst;
    }
    public void helper(List<List<Integer>> rst, ArrayList<Integer> list,
                int[] nums, int level) {
        if (level == nums.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        //pick curr num
        list.add(nums[level]);
        helper(rst, list, nums, level + 1);
        list.remove(list.size() - 1);
        
        //not pick curr num
        helper(rst, list, nums, level + 1);
    }
}


//Back tracking, DFS, recursive: recursive call of (rst, list, nums, index). 
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        dfs(rst, list, nums, 0);
        return rst;
    }
    
    public void dfs(List<List<Integer>> rst, ArrayList<Integer> list, int[] nums, int index){
        rst.add(new ArrayList<>(list));
        
        for( int i = index; i < nums.length; i++){
            list.add(nums[i]);
            dfs(rst, list, nums, i+1);
            list.remove(list.size() - 1);
        }
    }
}

```