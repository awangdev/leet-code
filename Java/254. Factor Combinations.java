M
tags: DFS, BFS, Backtracking
time: O(x), x is the # of results
space: O(y), y is all ongoing candidates in queue


#### Method1: DFS
- build candidate into dfs: treat each list candidate as success, add to rst
- remove last item from the candidate, try to add factor to it, and supply it with remain element
- backtrack after dfs


#### Method2: BFS
- Check if the number can be devided by [2, sqrt(n)], return a list of possible factors. Only check till `Math.sqrt(n)`
  - build suffixes: use the factor to devide last element of list and replace last element
  - build candidate: replace last element of the queue item with new list of suffixes; add to rst
  - add success item back to queue: in case last element can be simplified
- **remove dupilcates**: since we start factor from [2, sqrt(n)], the final factor list should be **ascending**!!
- time: O(x), x is the # of results
- space: O(y), y is all ongoing candidates in queue

```
/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/

/*
Method1: DFS
- build candidate into dfs: treat each list candidate as success, add to rst
- remove last item from the candidate, try to add factor to it, and supply it with remain element
- backtrack after dfs
*/
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (n <= 3) return rst;
        dfs(rst, new ArrayList<>(), n, null);
        return rst; 
    }

    public void dfs(List<List<Integer>> rst, List<Integer> list, int num, Integer lastFactor) {
        if (lastFactor != null) { // it only reaches here when there is a `num % low == 0` from last level
            list.add(num);
            rst.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
        int high = (int) Math.sqrt(num);
        int low = lastFactor == null ? 2 : Math.max(2, lastFactor);
        while (low <= high) {
            if (num % low == 0) {
                list.add(low);
                dfs(rst, list, num / low, low);
                list.remove(list.size() - 1);
            }
            low++;
        }
    }
}


/*
Method2: BFS:
- test candidate with all possible factors
- continue with factoring the last element, replace last element of candidate and add to rst
- end state: no way to break it down further, return
*/
class Solution {
    Map<Integer, List<List<Integer>>> memo = new HashMap<>();
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> rst = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>(calcFactors(2, n));

        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            rst.add(new ArrayList<>(list));
            
            int num = list.get(list.size() - 1);
            int lastFactor = list.get(list.size() - 2); // remove duplicates
            List<List<Integer>> factorList = calcFactors(lastFactor, num); // [2, 8]
            if (factorList.isEmpty()) continue;

            for (List<Integer> factors : factorList) {
                List<Integer> candidate = new ArrayList<>(list);
                candidate.remove(candidate.size() - 1);
                candidate.addAll(factors);
                queue.offer(candidate);
            }
        }
        
        return rst;
    }
    
    /*
      Calculate factor for tail element.
      Reduce duplication: start factoring from last factor, so it does not create error case: [2,2,3], [2,3,2]
     */
    private List<List<Integer>> calcFactors(int lastFactor, int num) {
        List<List<Integer>> rst = new ArrayList<>();
        int sqrt = (int)Math.sqrt(num);
        for (int i = lastFactor; i <= sqrt; i++) { // prevent reuse of elements
            int remain = num / i;
            if (i * remain == num) rst.add(Arrays.asList(i, remain));
        }
        return rst;
    }
}

```