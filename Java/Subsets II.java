M
1531544611
tags: Array, Backtracking, DFS, BFS
time: O(2^n)
sapce: O(2^n)

给一串integers(may have duplicates), 找到所有可能的subset. result里面不能有重复.

#### DFS
- DFS, 找准需要pass along的几个数据结构. 先`sort input`, 然后DFS
- Using for loop approach: 每个dfs call是一种可能性，直接add into result.     
- 为了除去duplicated result, skip used item at current level: `if (i > depth && nums[i] == nums[i - 1]) continue;`
- sort O(nlogn), subset: O(2^n)
- space O(2^n), save results

#### Simplier BFS on selected candidates
- use rst = `set<List<String>>` to cache candidates, starting from []
- add one num at a time; use `rst.contains()` to O(1) check candidates
- save every result rst.

#### BFS
- Regular BFS, 注意考虑如果让one level to generate next level
- skip duplicate: `if (i > endIndex && nums[i] == nums[i - 1]) continue;`
- 1. 用queue来存每一次的candidate indexes
- 2. 每一次打开一层candiates, add them all to result
- 3. 并且用每一轮的candidates, populate next level, back into queue.
- srot O(nlogn), subset: O(2^n)
- should be same O(2^n). slower than dfs

#### Previous notes:
- 在DFS种skip duplicate candidates, 基于sorted array的技巧：    
- 一旦for loop里面的i!=index，并且nums[i] == nums[i-1],
- 说明x=nums[i-1]已经在curr level 用过，不需要再用一次: [a,x1,x2]，x1==x2    
- i == index -> [a,x1]    
- i == index + 1 -> [a,x2]. 我们要skip这一种
- 如果需要[a,x1,x2]怎么办？ 其实这一种在index变化时，会在不同的两个dfs call 里面涉及到。

#### 注意
- 不能去用result.contains(), 这本身非常costly O(nlogn)
- 几遍是用 list.toString() 其实也是O(n) iteration, 其实也是增加了check的时间, 不建议


```
/*
Given a collection of integers that might contain duplicates, nums, 
return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]


*/

/*
Thoughts:
- have to sort the list, in order to skip items
- using the for loop approach: pick ONE item from the list at a time, with index i, then dfs with i + 1
- skip case: if we've picked an item for once at (i - 1), then in this particular for loop, we should not pick the same item
- thought, important: it can be picked in the next level of dfs
- subset, either take or not take : 2^n space, 2^n time
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result; // edge case

        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();

        // dfs with depth = 0
        result.add(new ArrayList<>(list));
        dfs(result, list, nums, 0);    
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int depth) {
        for (int i = depth; i < nums.length; i++) {
            if (i > depth && nums[i] == nums[i - 1]) continue; // IMPORTANT, skip duplicate: i > depth && nums[i] == nums[i - 1]
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            dfs(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

// Simplier BFS + backtracking
// Sort O(nlogn), extra space O(2^n), pick/not pick O(2^n)
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	Set<List<Integer>> rst = new HashSet<>();
    	if (nums == null || nums.length == 0) return new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);
    	Queue<List<Integer>> queue = new LinkedList<>();
    	List<Integer> list = new ArrayList<>();
    	queue.offer(new ArrayList<>(list));
    	rst.add(new ArrayList<>(list));
    	
    	for (int i = 0; i < n; i++) {
            int num = nums[i];
    		int size = queue.size();
    		while(size > 0) {
	    		list = queue.poll();
	    		//Pick
	    		list.add(num);
	    		if (!rst.contains(list)) {//O(n)
	    			rst.add(new ArrayList<>(list));
	    		}
	    		queue.offer(new ArrayList<>(list));
	    		list.remove(list.size() - 1);
	    		//Not pick
	    		queue.offer(new ArrayList<>(list));
	    		size--;
	    	}
    	}
    	return new ArrayList<>(rst);
    }
}

// BFS, Queue
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // edge, init result
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
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
					if (i > endIndex && nums[i] == nums[i - 1]) { // skip duplicated records
						continue;
					}
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
Thoughts:
- Convert list to key, and validate before adding into the result
- to remove duplicates, make sure nums is sorted at beginning.
- Sort: O(nlogn), 2^n possible subsets => overall time, O(2^n)
- using extra space O(2^n)
*/
class Solution {
    Set<String> set = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // edge case, init result
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();

        // dfs with depth = 0
        dfs(result, list, nums, 0);    
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int depth) {
        // check closure case
        if (depth >= nums.length) {
            String key = list.toString(); // O(n), not optimal
            if (!set.contains(key)) {
                result.add(new ArrayList<>(list));
                set.add(key);
            }
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

/*
03.10.2016
Once sorted at beginning, same num are ajacent.
With for loop solution, we are jumping to next position in one dfs call, so this could happen:
skip current and take next. if current == next, that generates duplicates.
Image we've moved one step forward already, so we are not on given index, also current == prev: 
that means previous position nums[i-1] must have been used. so we skip the current.
if (i != index && nums[i] == nums[i - 1]) {
    continue;
}
*/
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        
        Arrays.sort(nums);
        dfs(rst, new ArrayList<Integer>(), 0, nums);
        
        return rst;
    }
    
    public void dfs(List<List<Integer>> rst, ArrayList<Integer> list, int index, int[] nums) {
        rst.add(new ArrayList<Integer>(list));
        
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfs(rst, list, i + 1, nums);
            list.remove(list.size() - 1);
            
        }
    }
}

/*
  Not recommended, Again, rst.contains() cost too much.
	Thoughts: 12.07.2015
	try to do non-recursive - iterative

	create a queue, initi with []. put [] into rst as well.
	Each time pick/not pick curr element: 2 branch: add back into queue, and try to add to rst(if non-exist)
	For loop through all elements in S

	Use Queue
*/

class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
    	ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
    	if (S == null || S.size() == 0) {
    		return rst;
    	}
    	Collections.sort(S);
    	Queue<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	queue.offer(new ArrayList<Integer>(list));
    	rst.add(new ArrayList<Integer>(list));
    	
    	for (int i = 0; i < S.size(); i++) {
    		int num = S.get(i);
    		int size = queue.size();
    		while(size > 0) {
	    		list = queue.poll();
	    		//Pick
	    		list.add(num);
	    		if (!rst.contains(list)) {//O(n)
	    			rst.add(new ArrayList<Integer>(list));
	    		}
	    		queue.offer(new ArrayList<Integer>(list));
	    		list.remove(list.size() - 1);
	    		//Not pick
	    		queue.offer(new ArrayList<Integer>(list));
	    		size--;
	    	}
    	}
    	return rst;
    }
}




/*
  Not recommended, Again, rst.contains() cost too much.
	Thoughts: 12.07.2015. 
	Do regular subset recursion: pick curr or not pick curr, (rst, list, level, S)
	Use a HashMap to mark if the cmobination exists already
	Recursive.
*/
class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
    	ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
    	if (S == null || S.size() == 0) {
    		return rst;
    	}
    	Collections.sort(S);
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	helper(rst, list, S, 0);

    	return rst;
    }

    public void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, 
    	ArrayList<Integer> S, int level) {
		if (!rst.contains(list)) {//Costly, O(n) each time
			rst.add(new ArrayList<Integer>(list));
		}
		if (level == S.size()) {
		    return;
		}
    		
    	//pick curr
    	list.add(S.get(level));
    	helper(rst, list, S, level + 1);
    	list.remove(list.size() - 1);

    	//no pick curr
    	helper(rst, list, S, level + 1);
    }
}





//Older version, with for loop, use rst.contains()... which is very costly, O(n) each time
// Not recommended, Again, rst.contains() cost too much.
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        
        Arrays.sort(nums);
        dfs(rst, new ArrayList<Integer>(), 0, nums);
        
        return rst;
    }
    
    public void dfs(List<List<Integer>> rst, ArrayList<Integer> list, int index, int[] nums) {
        if (!rst.contains(list)) {//Costly, O(n)
            rst.add(new ArrayList<Integer>(list));
        }
        
        for (int i = index; i < nums.length; i++) {
                list.add(nums[i]);
                dfs(rst, list, i + 1, nums);
                list.remove(list.size() - 1);
        }
    }
}
```