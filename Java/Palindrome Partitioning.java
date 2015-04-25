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
