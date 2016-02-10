递归：找准需要pass along的几个数据结构。

Iterative: 写一写，用个Queue.
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
	Thoughts: 12.07.2015
	try to do non-recursive - iterative

	create a queue, initi with []. put [] into rst as well.
	Each time pick/not pick curr element: 2 branch: add back into queue, and try to add to rst(if non-exist)
	For loop through all elements in S

	Use Queue
*/

class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
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
	    		if (!rst.contains(list)) {
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
	Thoughts: 12.07.2015. 
	Do regular subset recursion: pick curr or not pick curr, (rst, list, level, S)
	Use a HashMap to mark if the cmobination exists already
	Recursive.
*/
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
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
		if (!rst.contains(list)) {
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





//Older version, with for loop:


class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> source) {
         // write your code here
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> newList = new ArrayList<Integer>();
        Collections.sort(source);
        subsetHelper(0, source, newList, output);
        return output;
    }
    
     
    public void subsetHelper(int pos, 
            ArrayList<Integer> source, ArrayList<Integer> newList, 
            ArrayList<ArrayList<Integer>> output){
        if (!output.contains(newList)){
            output.add(new ArrayList<Integer>(newList));
        }
        
        for (int i = pos; i < source.size(); i++){
            newList.add(source.get(i));
            subsetHelper(i + 1, source, newList, output);
            newList.remove(newList.size() - 1);
        }
    
    }
}
```