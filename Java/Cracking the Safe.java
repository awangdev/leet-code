H
1534220198
tags: Math, DFS, Greedy

#### Greedy, Iterative
- For 2 passwords, the shortest situation is both passwords overlap for n-1 chars.
- We can use a window to cut out last (n-1) substring and append with new candidate char from [k-1 ~ 0]
- Track the newly formed string; if new, add the new char to overall result
- Note: this operation will run for k^n times: for all spots of [0 ~ n - 1] each spot tries all k values [k-1 ~ 0]
- Same concept as dfs

#### DFS
- Same concept: use window to cut out tail, and append with new candidate
- do this for k^n = Math.pow(k, n) times

```
/*
There is a box protected by a password. The password is n digits, 
where each letter can be one of the first k digits 0, 1, ..., k-1.

You can keep inputting the password, 
the password will automatically be matched against the last n digits entered.

For example, assuming the password is "345", I can open it when I type "012345", 
but I enter a total of 6 digits.

Please return any string of minimum length that is guaranteed to open the box 
after the entire string is inputted.

Example 1:
Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:
Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.

http://www.cnblogs.com/grandyang/p/8452361.html
*/

/*
For 2 passwords, the shortest situation is both passwords overlap for n-1 chars.
We can use a window to cut out last (n-1) substring and append with new candidate char from [k-1 ~ 0]
Track the newly formed string; if new, add the new char to overall result
Note: this operation will run for k^n times: for all spots of [0 ~ n - 1] each spot tries all k values [k-1 ~ 0]

This is more greedy: will generate one correct solution
*/
class Solution {
    public String crackSafe(int n, int k) {
        //if (n == 1) return "0";
        
        Set<String> set = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("0");
        set.add(sb.toString());
        
        for (int i = 0; i < Math.pow(k, n); i++) {
            String tail = sb.substring(sb.length() - n + 1); // last n - 1 chars
            for (int j = k - 1; j >= 0; j--) {
                String newStr = tail + j;
                if (!set.contains(newStr)) {
                    set.add(newStr);
                    sb.append(j);
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}


// DFS
class Solution {
    public String crackSafe(int n, int k) {        
        Set<String> visited = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("0");
        visited.add(sb.toString());
        
        // recursive, dfs
        dfs(n, k, Math.pow(k, n), visited, sb);
        return sb.toString();
    }
    
    private void dfs(int n, int k, double total, Set<String> visited, StringBuffer sb) {
        if (visited.size() == total) return;
        String tail = sb.substring(sb.length() - n + 1);
        for (int j = k - 1; j >= 0; j--) {
            String newStr = tail + j;
            if (visited.contains(newStr)) continue;
            visited.add(newStr);
            sb.append(j);
            dfs(n, k, total, visited, sb);
        }
    }
}
```