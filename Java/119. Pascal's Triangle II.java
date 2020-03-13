E
tags: Array, Basic Implementation
time: O(k^2), pascal triangle size
space: O(k^2)

简单处理 list. code is very similar to Pascal triangle I.

- 注意 `list = Arrays.asList(x, y, z ...)` 给fixed-size list, 不能直接 list.add().
- Use `new ArrayList<>(Arrays.asList(...))` to wrap it up.


```


/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

Hide Tags Array
Hide Similar Problems (E) Pascal's Triangle

*/


/*
     1
    1 1
1   2   1
1 3   3   1
*/

//list store 0 1 0. Iteratve over k, each time createa a new list.
//Add 1 on two size before calculating each row
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex == 0) result.add(1);
        if (rowIndex >= 1) result.add(1);

        for (int row = 1; row <= rowIndex; row++) {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 1));
            List<Integer> lastRow = result;
            int end = row - 1;
            for (int i = 1; i <= end; i++) {
                list.add(i, lastRow.get(i) + lastRow.get(i - 1));
            }
            result = list;
        }
        return result;
    }
}
```