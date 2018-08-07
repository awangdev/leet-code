M
1525233126
tags: Sort

给一串数字, 非负数, 把所有数字串联起来, 组成最大数字.

因为结果很大, 所以用string表示 

#### Sort, Comparator
- 考虑 more significant spot 应该拿到更大的值
- 如果sort number,  comparator 会比较难写: 每个digit的weight不同, 要分别讨论个位数和多位数.
- goal: 让较大的组合数排在前面, 让较小的组合数排在后面
- 不如: 组合两种情况, 用String比较一下大小 (也可以用 integer来比较组合数, 但是为保险不超Integer.MAX_VALUE, 这里比较String)
- String.compareTo() 是按照 lexicographically, 字典顺序排列的
- 利用compareTo, 来倒序排列 string, 刚好就得到我们要的结果.
- O(nlogn), 排序

```
/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example
Given [1, 20, 23, 4, 8], the largest formed number is 8423201.

Note
 The result may be very large, so you need to return a string instead of an integer.

Tags Expand 
Sort

Thoughts:
Use a comparator with String.comareTo, then uset Arrays.sort(...)

*/


/*
class CustomComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return (s2 + s1).compareTo(s1 + s2);
    }
}
*/
public class Solution {
    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        if (num == null || num.length == 0) {
            return "";
        }
        String[] strs = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            strs[i] = num[i] + "";
        }

        // The lexicographically order is "1" < "2"; here we reverse the compareTo() to get reverse effect
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num.length; i++) {
            sb.append(strs[i]);
        }
        String rst = sb.toString();
        
        // The case "000000"
        if (rst.charAt(0) == '0') {
            return "0";
        }
        return rst;
    }
}
```