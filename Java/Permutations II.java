M

方法1:
Mark visited. 并且要检查上一层recursive时有没有略过重复element. 并且要排序，通过permutation规律查看是否排出了重复结果。

背景1:在recursive call里面有for loop, 每次从i=0开始, 试着在当下list上加上nums里面的每一个。    
从i=0开始，所以会依次recursive每一个nums：因此，例如i=2,肯定比i=3先被访问。也就是:取i=2的那个list permutation肯定先排出来。   

背景2:重复的例子：给出Input[x, y1, y2], 假设y的值是一样的。那么，{x,y1,y2}和{x,y2,y1}是相同结果。

综上，y1肯定比y2先被访问,{x,y1,y2}先出。 紧随其后，在另一个recursive循环里，{x,y2...}y2被先访问，跳过了y1。    
重点:规律在此，如果跳过y1，也就是visited[y1] == false, 而num[y2] == num[y1]，那么这就是一个重复的结果，没必要做，越过。

结果:那么，我们需要input像{x,y1,y2}这样数值放一起，那么必须排序。


方法2:
一个办法就是给一个visited queue。 和queue在所有的地方一同populate. 然后visited里面存得时visited indexes。 (Not efficient code. check again)

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
3.3.2016 recap, leetcode
Use visited[i] to mark visited copies
*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        helper(rst, new ArrayList<Integer>(), nums, visited);

        return rst;
    }

    public void helper(List<List<Integer>> rst, ArrayList<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i != 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            helper(rst, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        
        }
    }
}


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