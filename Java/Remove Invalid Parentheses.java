H

NOT DONE

```
/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

/*
Thoughts:
1. Spend O(n) time checking: # of extra '(' or ')', choose the one with min#
2. Decide where do we remove the extra parenthese? DFS

DFS(Set<String> rst, char c, int num, StringBuffer sb)
sb.deleteCharAt(...)

Check, may use BFS instead.
http://www.cnblogs.com/grandyang/p/4944875.html
*/
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null) {
            return rst;
        }
        StringBuffer sb = new StringBuffer(s);
        int count = 0;
        int rightParenRemovalCount = 0;
        int leftParenRemovalCount = 0;
        List<Character> removalChars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) {
            char c = s.charAt(i);
            count += c == '(' ? 1 : 0;
            count += c == ')' ? -1 : 0;
            if (count < 0) {
                removalChars.add(')');
                count = 0;
            }
        }
        for (int i = 0; i <= Math.abs(count); i++) {
            removalChars.add(count < 0 ? ')' : '(');
        }
        Set<String> result = new HashSet<String>();
        
        dfs(result, removalChars, sb);    
        /*
        if (rightParenRemovalCount == 0 && leftParenRemovalCount == 0) {
            result.add(s);
        } else {
            if (leftParenRemovalCount > 0) {
                dfs(result, '(', leftParenRemovalCount, sb);    
            }
            if (rightParenRemovalCount > 0) {
                dfs(result, ')', rightParenRemovalCount, sb);    
            }
        }*/
        return new ArrayList(result);
    }
    
    public void dfs(Set<String> rst, List<Character> removalChars, StringBuffer sb) {
        if (removalChars.size() <= 0) {
            String ss = sb.toString();
            if (!rst.contains(ss) && isValid(ss)) {
                rst.add(ss);
            }
            return;
        }
        for (int i = 0; i < removalChars.size(); i++) {
            char target = removalChars.get(i);
            removalChars.remove(i);
            for (int j = 0; j < sb.length(); j++) {
                if (sb.charAt(j) == target) {
                    dfs(rst, removalChars, sb.deleteCharAt(j));
                    sb.insert(j, target);
                }
            }
            removalChars.add(i, target);
        }
    }
    
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            count += c == '(' ? 1 : 0;
            count += c == ')' ? -1 : 0;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
```