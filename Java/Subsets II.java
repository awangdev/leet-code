M

递归：找准需要pass along的几个数据结构。    

和SubsetI类似，先sort input, 然后递归。但是input可能有duplicates. 

Using for loop approach: 每个dfs call是一种可能性，直接add into result.     
为了除去duplicated result, 如果在递归里面用rst.contains(),就是O(n), which makes overall O(n^2). 

这里有个基于sorted array的技巧：    
因为我们有mark index。 一旦for loop里面的i!=index，并且nums[i] == nums[i-1],说明x=nums[i-1]已经用过，不需要再用一次：     
[a,x1,x2]，x1==x2    
i == index -> [a,x1]    
i == index + 1 -> [a,x2]. 我们要skip这一种。

如果需要[a,x1,x2]怎么办？ 其实这一种在index变化时，会在不同的两个dfs call 里面涉及到。


Iterative: 写一写，用个Queue. Not recommended, Again, rst.contains() cost too much.

```
/*
Given a list of numbers that may has duplicate numbers, return all possible subsets

Have you met this question in a real interview? Yes
Example
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Note
Each element in a subset must be in non-descending order.
The ordering between two subsets is free.
The solution set must not contain duplicate subsets.
Challenge
Can you do it in both recursively and iteratively?

Tags Expand 
Recursion
*/

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