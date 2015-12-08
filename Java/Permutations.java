还是递归： 取，或者不取。
Iterative: 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍。
```
/*
Given a list of numbers, return all possible permutations.

Example
For nums [1,2,3], the permutaions are: 

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
    Thoughts: 12.07.2015 recap
    recursive: 
    pass list, rst, nums.
    when list.size() == nums.size(), add to rst and return.
    Need to re-add all of those non-added spots. So do for loop everytime to try all possible ways.
    note: check if !list.contains(candiate).
*/

/*
    12.07.2015
    Now, do a non-recursive.
    Set up a queue, add all elements into it.
    give a size/level variable to keep track of cycle.

*/
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return rst;
        }
        Queue<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
        ArrayList<Integer> list;
        for (int num : nums) {
            list = new ArrayList<Integer>();
            list.add(num);
            queue.offer(new ArrayList<Integer>(list));
        }

        while (!queue.isEmpty()) {
            list = queue.poll();
            if (list.size() == nums.size()) {
                rst.add(new ArrayList<Integer>(list));
                continue;
            }
            for (int i = 0; i < nums.size(); i++) {
                if (!list.contains(nums.get(i))) {
                    list.add(nums.get(i));
                    queue.offer(new ArrayList<Integer>(list));
                    list.remove(list.size() - 1);
                }
            }
        }

        return rst;
    }
}



/*

Thinking Process:
1. Very similar idea: choose or not choose (1 / 0)
    A key point is: when jumpped into next level of recursion, the 'list' will surely be filled up until it reach the max length.
    That is: when 'not choose', the empty seat will be filled eventually with points not existed in 'list'.
2. The recursion does not end before the list is filled.
3. A for loop is doiong the filling of blank. Any order/combination will occur.
*/

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
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


```