H
tags: String, Divide and Conquer, DFS, Backtracking
time: O(4^n)
space: O(4^n)

给一个数字String, 数字来自`0-9`, 给3个操作符 `+`,`-`,`*`, 看如何拼凑, 可以做出结果target.

output 所有 expression

#### string dfs, use list to track steps (backtracking)
- 跟string相关, 写起来可能稍微繁琐一点
    - 数字有 dfs([1,2,3...]) 组合方法
    - operator有[`+`,`-`,`*`] 3种组合方法
- 注意1: 乘号要特殊处理, pass along 连乘的数字, 计算下一步乘积的时候, 要 sum - preProduct + product
- 注意2: '01' 这种数字要skip
- 注意3: 第一个选中数字不需要加操作符, 直接加进去
- Time: O(4^n)， Space: O(4^n)
- T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
- T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
- Thus T(n) = 4T(n-1) = 4^2 * T(n - 1) = .... O(4^n)

#### String dfs, use string as buffer
- 逻辑一样, 代码更短, 只不过不做list, 直接pass `buffer + "+" + curr`
- 因为每次都创建新string, 所以速度稍微慢一点. Time complexity 一样

```
/*

Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) +, -, or * 
between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []

*/

/*
Thoughts::
- parse string, dfs on string index
- DFS: Choose integer as individual, or combo: [1,2,3], [12,3]
- Each choice launch 3 dfs : [+, -, *]
- handle * : dfs must pass in currNum and nextNumber for * to work.
- edge case: '0 + 00'
*/


// Append string
class Solution {
    String s;
    int target;
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<>();
        if (num == null || num.length() == 0) return rst;
        this.s = num;
        this.target = target;
        // dfs from index = 0, sum = 0, productFactor = 0,
        dfs(rst, "", 0, 0, 0);
        return rst;
    }

    private void dfs(List<String> rst,
                     String buffer,
                     int index,
                     long sum,
                     long productFactor) {
        if (index >= s.length() && sum == target) {
            rst.add(buffer);
            return;
        }

        // for loop from index -> end
        for (int i = index; i < s.length(); i++) {
            String curr = s.substring(index, i + 1);
            long currValue = Long.parseLong(curr);
            if (s.charAt(index) == '0' && i > index) { // filter case: '01'
                continue;
            }

            if (index == 0) {
                dfs(rst, curr, i + 1, currValue, currValue);
                continue;
            }

            dfs(rst, buffer + "+" + curr, i + 1, sum + currValue, currValue);
            dfs(rst, buffer + "-" + curr, i + 1, sum - currValue, - currValue);
            long product = productFactor * currValue;
            dfs(rst, buffer + "*" + curr, i + 1, sum - productFactor + product, product);
        }
    }
}

/*
Thoughts::
- parse string, dfs on string index
- DFS: Choose integer as individual, or combo: [1,2,3], [12,3]
- Each choice launch 3 dfs : [+, -, *]
- handle * : dfs must pass in currNum and nextNumber for * to work.
- edge case: '0 + 00'
*/
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<>();
        // edge case
        if (num == null || num.length() == 0) {
            return rst;
        }

        // dfs1 from index = 0, sum = 0, preProduct = 0,
        dfs(rst, new ArrayList<>(), num, 0, 0, 0, target);
        return rst;
    }

    private void dfs(List<String> rst, List<String> list, String s, 
                     int index, long sum, long preProduct, int target) {
        if (index >= s.length()) {
            if (sum == target) {
                StringBuffer sb = new StringBuffer();
                for (String part : list) {
                    sb.append(part);
                }
                rst.add(sb.toString());
            }
            return;
        }

        // for loop from index -> end
        for (int i = index; i < s.length(); i++) {
            String curr = s.substring(index, i + 1);
            long currValue = Long.parseLong(curr);
            if (s.charAt(index) == '0' && i > index) { // filter case: '01'
                continue;
            }

            if (index == 0) {
                list.add(curr);
                dfs(rst, list, s, i + 1, currValue, currValue, target);
                list.remove(list.size() - 1);
                continue;
            }

            list.add("+");
            list.add(curr);
            dfs(rst, list, s, i + 1, sum + currValue, currValue, target);
            backtrack(list);

            list.add("-");
            list.add(curr);
            dfs(rst, list, s, i + 1, sum - currValue, - currValue, target);
            backtrack(list);

            list.add("*");
            list.add(curr);
            long product = preProduct * currValue;
            dfs(rst, list, s, i + 1, sum - preProduct + product, product, target);
            backtrack(list);
        }
    }
    
    private void backtrack(List<String> list) {
        list.remove(list.size() - 1);
        list.remove(list.size() - 1);
    }
}



```