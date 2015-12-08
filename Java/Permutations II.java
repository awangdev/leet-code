要unique,就是要确定visit过的那个index不被重新visit.
一个办法就是给一个visited queue。 和queue在所有的地方一同populate. 然后visited里面存得时visited indexes
```
/*
Given a list of numbers with duplicate number in it. Find all unique permutations.

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


/*
    Thoughts:
    Same as Permutation I, but check also check for duplicate, if duplicate, continue
    Use a queue of marker to track if that index has been visited.
*/
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return rst;
        }
        Queue<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
        Queue<ArrayList<Integer>> visited = new LinkedList<ArrayList<Integer>>();
        ArrayList<Integer> list;
        ArrayList<Integer> mark;
        for (int i = 0; i < nums.size(); i++) {
            list = new ArrayList<Integer>();
            list.add(nums.get(i));
            mark = new ArrayList<Integer>();
            mark.add(i);
            queue.offer(new ArrayList<Integer>(list));
            visited.offer(new ArrayList<Integer>(mark));
        }

        while (!queue.isEmpty()) {
            list = queue.poll();
            mark = visited.poll();
            if (list.size() == nums.size()) {
                if (!rst.contains(list)) {
                    rst.add(new ArrayList<Integer>(list));
                }
                continue;
            }
            for (int i = 0; i < nums.size(); i++) {
                if (!mark.contains(i)) {
                    list.add(nums.get(i));
                    mark.add(i);
                    queue.offer(new ArrayList<Integer>(list));
                    visited.offer(new ArrayList<Integer>(mark));
                    list.remove(list.size() - 1);
                    mark.remove(mark.size() - 1);
                }
            }
        }

        return rst;
    }
}




/*

Thougths:
Use regular recursion, use a mark[] to make sure the same charactor at same postion won't be reused 
Do a backtrack on the dfs, to make sure a element has same chance of 'selectd' or 'non-solectd'
*/
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return rst;
        }
        Collections.sort(nums);
        boolean[] mark = new boolean[nums.size()];
        dfs(nums, new ArrayList<Integer>(), rst, mark);
        return rst;
    }

    public void dfs (ArrayList<Integer> nums, ArrayList<Integer> list,
                        ArrayList<ArrayList<Integer>> rst, boolean[] mark) {
        if (list.size() == nums.size()) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (mark[i] || (i != 0 && mark[i - 1] && nums.get(i) == nums.get(i - 1))) {
                continue;
            }           
            list.add(nums.get(i));
            mark[i] = true;
            dfs(nums, list, rst, mark);
            list.remove(list.size() - 1);
            mark[i] = false;
        }
        return;
    }
}




```