M
tags: Backtracking, DFS, BFS, Permutation
time: O(n!)
space: O(n!)

#### Method1-Option1: Recursive Backtracking, with queue to maintain the remaining list
- Best of all recursive approaches
- iterate over nums: pick or not pick
- reduce remaining item in next level:
    - option1: use queue, restrict queue size; backtrack append to queue
    - option2: remove element before passing into next level; backtrack: insert back
- time O(n!): visit all possible outcome
    - T(n) = n * T(n-1) + O(1)

Method1-Option2: Recursive Backtracking, with `list.contains()` to avoid reuse of index
- A bit worse than option1, uses more time:
    - list.contains() cost O(logn). Technically, it is O(n^n), plus the `contains` is nlogn time
    - also, each dfs, it has to iterate over entire nums list

Method1-Option3: Recursive Backtracking, with `visited[]` to avoid reuse of index
- Use visited[] to track, still causes for(over n items), not efficient

#### Method2-Option1: Iterative, Build permutation by insertion
- Best of all iterative approaches
- Each time pick 1 NEW element and find places to insert into candidate list:
    - 1. 一个一个element加进去
    - 2. 每一次把rst里面的每个list拿出来, 创建成新list, 然后选位置加上new element
    - 3. 加新元素的时候, 要在list的每个位置insert, 最终也要在原始的list末尾加上new element
- 还是O(n!), 因为rst insert O(n!)个permutations
- Better than the Option2/Option3 (`BFS+Queue`), because this solution does not need to check duplicates

#### Method2-Option2: Iterative, use Queue to hold candidate list
- 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍
- Time O(n!)
- Slow: checking candidate.contains() is O(logn) each time

#### Method2-Option3: Iterative, use queue to candidate list, and calculate remain list on the fly
- Almost same as Method2-Option2, but it builds remainingCandidate list on the fly list.removeall(xyz): O(n)
- Even slower than Method2-Option2

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


/**
Method1-Option1: Recursive Backtracking, with queue to maintain the remaining list
Use queue in dfs: true T(n) = n * T(n-1), each time have 1 less item to check
*/
class Solution {
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
		if (nums == null || nums.length == 0) return rst;

        Queue<Integer> queue = new LinkedList<>();
        for (int num : nums) queue.offer(num); // [1, 2, 3]
		
		dfs(rst, new ArrayList<>(), queue);
		return rst;
    }

    private void dfs(List<List<Integer>> rst, List<Integer> list, Queue<Integer> queue) {
        if (queue.isEmpty()) {
            rst.add(new ArrayList<>(list));
            return;
        }
        int size = queue.size();
        while (size-- > 0) {
            int val = queue.poll(); // queue maintains the remaining list in processing order: simulates pick/not-pick
            list.add(val);

            dfs(rst, list, queue);

            // backtracking
            list.remove(list.size() - 1); 
            queue.offer(val);
        }
    }
}


/**
Method1-Option2: Recursive Backtracking, with `list.contains()` to avoid reuse of index
- Bad DFS Option, though still fast enough
- Use list.contains() to check. Technically, it is O(n^n), plus the `contains` is nlogn time

*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        
        dfs(result, new ArrayList<>(), nums);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            dfs(result, list, nums);
            list.remove(list.size() - 1);
        }
    }
}

// Method1-Option3: Use visited[] to track, still causes for(over n items), not efficient
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        
        dfs(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) { // still loop over O(n)
            if (visited[i]) continue;
            list.add(nums[i]);
            visited[i] = true;

            dfs(result, list, nums, visited);
            
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}


/*
#### Method2: Iterative, Option1: Build permutation by insertion

- Start with 1st element.
- Add 2nd element in front/end of 1st element; and add the new layout to rst
- Add 3rd element in front, between, end ...
- Loop over elements in nums => rst list => each row
*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;
        rst.add(new ArrayList<>());
       
        for (int i = 0; i < nums.length; i++) { // Pick element
            int rstSize = rst.size();
            for (int j = 0; j < rstSize; j++) { // pick base list from rst list
                List<Integer> baselist = rst.get(j);
                // Pick slot to insert element at [n, baseList.size())
                for (int index = 0; index < baselist.size(); index++) {
                    baselist.add(index, nums[i]);
                    rst.add(new ArrayList<>(baselist)); // add new candidate back to rst
                    baselist.remove(index);
                }
                baselist.add(nums[i]); // add picked num to tail of base
            }
        }
        return rst;
    }
}

/*
#### Method2: Iterative, Option2: use Queue to hold candidate list
- produce a list of candidate and put them into queue
- pull them out and try to expand: each execution generate a new candidate
- when candidate size == n, skip adding to queue, and just add to rst
- when processing queue item: for(n items) and do candidate.contains(nums[i]).
	- this is not as efficient.
- to improve time: want to know the remaining items at each level
- maintain another large list of list, this wastes space

*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>());

        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                List<Integer> list = queue.poll();
                if (list.size() == nums.length) {
                    rst.add(new ArrayList<>(list));
                    continue;
                }
                populateQueue(queue, list, nums);
            }
        }
        return rst;
    }

    private void populateQueue(Queue<List<Integer>> queue, List<Integer> list, int[] nums) {
        for (int num : nums) {
            if (list.contains(num)) continue; // slow
            list.add(num);
            queue.offer(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
    }
}

/*
    #### Method2: Iterative, Option3: use queue, and calculate remain list on the fly
    Set up a queue, add all elements into it.
    give a size/level variable to keep track of cycle.
    Slow, because every queue iteration requires O(n) to maintain entire list
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;
        
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) numList.add(num);
        
        int n = nums.length;
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>());

        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            if (list.size() == n) {
                rst.add(new ArrayList<>(list));
                continue;
            }
            List<Integer> candidates = new ArrayList<>(numList);
            candidates.removeAll(list);// the remaining items to insert
            for (int num : candidates) {
                list.add(num);
                queue.offer(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
        }

        return rst;
    }
}


```