M
1523836015
tags: DFS, Backtracking

给个string s, partition(分段)后, 要确保每个partition都是palindrome. 

求所有partition palindrome组合. `list<list<string>>`

#### DFS
- 在遍历str的时候，考虑从每个curr spot 到 str 结尾，是能有多少种palindorme?
- 那就从curr spot当个字符开始算，开始back tracing.
- 如果所选不是palindrome， 那move on.
- 若所选的确是palindrome,　加到path里面，DFS去下个level，等遍历到了结尾，这就产生了一种分割成palindrome的串。
- 每次DFS结尾，要把这一层加的所选palindrome删掉，backtracking嘛

#### Optimization
- 可以再每一个dfs level 算 isPalindrome(S), 但是可以先把 boolean[][] isPalin 算出来, 每次O(1) 来用
- 注意: isPalin[i][j] 是 inclusive的, 所以用的时候要认准坐标
- Overall Space O(n^2): 存 isPlain[][]
- Time O(n!), 每一层的for loop spawn n * (n - 1) * (n - 2)

```
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/

/*
Thoughts:
DFS to return all possible palindromes:
partition at index x in [0, s.length() )
Optimize by calculating boolean[][] isPalin ahead of time

// 99.82%
*/
class Solution {
    boolean[][] isPalin;
    String str;
    public List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        isPalin = calcPalin(s);
        str = s;
        dfs(rst, new ArrayList<>(), 0);
        return rst;
    }
    
    private void dfs(List<List<String>> rst, List<String> list, int x) {
        if (x == str.length()) {
            rst.add(new ArrayList<>(list));
            return;
        }
        for (int i = x + 1; i <= str.length(); i++) {
            if (isPalin[x][i - 1]) { // 也需要查看自身是不是 palindrome: s.charAt(x). isPalin[i][j] 是 inclusive的
                list.add(str.substring(x, i));
                dfs(rst, list, i);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private boolean[][] calcPalin(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] isPalin = new boolean[n][n];
        int mid, i, j;
        
        for (mid = 0; mid < n; mid++) {
            // odd
            i = j = mid;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
            
            // even
            i = mid;
            j = mid + 1;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }
        return isPalin;
    }
}

public class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<List<String>>();
        if (s == null || s.length() < 0) {
            return rst;
        }
        ArrayList<String> path = new ArrayList<String>();
        helper(s, path, 0, rst);
        return rst;
    }
    //Check Palindrome
    public boolean isPalindrome(String s){
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    //helper:
    public void helper(String s, ArrayList<String> path, int pos, 
        List<List<String>> rst) {
        if (pos == s.length()) {
            rst.add(new ArrayList<String>(path));
            return;
        }        
        for (int i = pos + 1; i <= s.length(); i++) {//i is used in s.sbustring(pos, i), which can equal to s.length()
            String prefix = s.substring(pos, i);
            if (!isPalindrome(prefix)) {
                continue;
            }
            path.add(prefix);
            helper(s, path, i, rst);
            path.remove(path.size() - 1);
        }    
    }
}
```