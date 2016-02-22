E

简单处理array list.

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
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> rst = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return rst;
        } else if (rowIndex == 0) {
            rst.add(1);
            return rst;
        }
        
        rst.add(1);
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        while (rowIndex > 0) {//2, 1, 0x
            list.add(1);
            for (int i = 0; i < rst.size() - 1; i++) {
                list.add(rst.get(i) + rst.get(i + 1));
            }
            list.add(1);
            rst = list;//[1,1], [1,2,1]
            list = new ArrayList<Integer>();
            rowIndex--;
        }
        
        return rst;
    }
}
```