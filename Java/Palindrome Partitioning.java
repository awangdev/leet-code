很清楚的DFS Backtracking.
在遍历str的时候，考虑从每个curr spot 到 str 结尾，是能有多少种palindorme? 那就从curr spot当个字符开始算，开始back tracing.
如果所选不是palindrome， 那move on.
若所选的确是palindrome,　加到path里面，DFS去下个level，等遍历到了结尾，这就产生了一种分割成palindrome的串。
每次DFS结尾，要把这一层加的所选palindrome删掉，backtracking嘛。
```
/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
Example
given s = "aab",
Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]
Tags Expand 
Backtracking
Thinking process:
1. Know how to write isPalindrome
2. Back tracking:
    check partial string
    add it
    recursive call
    remove it.
*/

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