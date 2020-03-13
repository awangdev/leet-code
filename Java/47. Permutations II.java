M
tags: Backtracking, DFS
time O(n!)
space: O(n!)

给一串数组, 找出所有permutation数组. 注意: 给出的nums里面有重复数字, 而permutation的结果需要无重复.

Similar to 46. Permutations, but with dedup

#### Backtracking
- Sort the list, and on same level, if last element is the same as curr, skip this recursive call
- time O(n!)

#### Non-recursive, manuall swap
- Idea from: https://www.sigmainfy.com/blog/leetcode-permutations-i-and-ii.html
- 用到 sublist sort
- 用 swap function, 在原数组上调节出来新的permutation
- 注意: 每次拿到新的candidate, 都要把没有permutate的数位sort, 然后再开始swap.
- 这是为了确保, [j]和[j-1]在重复时候, 不用重新记录.

```
/*
Given a collection of numbers that might contain duplicates, 
return all possible unique permutations.

Example
For numbers [1,2,2] the unique permutations are:

[

    [1,2,2],

    [2,1,2],

    [2,2,1]

]

Challenge
Do it without recursion.

Tags Expand 
LinkedIn Recursion

*/
// option1 with remaining Queue
class Solution {
	public List<List<Integer>> permuteUnique(int[] nums) {
		if (nums == null || nums.length == 0) return new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(nums);
        for (int num : nums) queue.offer(num); // [1, 2, 3]
		List<List<Integer>> rst = new ArrayList<>();
		dfs(rst, new ArrayList<>(), queue);
		return rst;
    }

    private void dfs(List<List<Integer>> rst, List list, Queue<Integer> queue) {
        if (queue.isEmpty()) { // 
            rst.add(new ArrayList<>(list));
            return;
        }
        int size = queue.size(); 
        Integer last = null;
        while (size-- > 0) {
            int val = queue.poll();
            if (last != null && last == val) {
                queue.offer(val);
                continue;
            }
            list.add(val);
            dfs(rst, list, queue);
            list.remove(list.size() - 1); 
            queue.offer(val);
            last = val;
        }
    }
}


// option2: Use visited[i] to mark visited copies, then skip [i-1] item
class Solution {
    boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        int n = nums.length;
        visited = new boolean[n];
        dfs(rst, new ArrayList<>(), nums);
        return rst;
    }
    
    private void dfs(List<List<Integer>> rst, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            rst.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            visited[i] = true;
            list.add(nums[i]);
            dfs(rst, list, nums);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}

// Sort
/*
Thoughts:
1. Use nums to swap nodes
2. Each swap should produce a unique row
3. Iterate over position to swap x,  and then iterative over (x + 1, n) to swap each one
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        
        rst.add(new ArrayList<>(list));

        for (int pos = 0; pos < n; pos++) {
            for (int i = rst.size() - 1; i >= 0; i--) {
                list = rst.get(i);
                Collections.sort(list.subList(pos, list.size()));
                
                for (int j = pos + 1; j < n; j++) {
                    if (list.get(j) == list.get(j - 1)) {
                        continue;
                    }
                    swap(list, pos, j);
                    rst.add(new ArrayList<>(list));
                    swap(list, pos, j);
                }   
            }
        }
        
        return rst;
    }
    
    private void swap(List<Integer> list, int x, int y) {
        int temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
}

```