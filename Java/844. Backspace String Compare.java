E
tags: Two Pointers, Stack
time:  O(n)
space: O(1)

#### Method1: Two pointers to backtack from end of string
- time: O(n)
- space: O(1)

#### Method2: Stack
- need to remove entity just added 
- use stack to hold array content; pop if # is found

```
/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
*/

// Method1: Two pointers to backtack from end of string
class Solution {
    public boolean backspaceCompare(String s, String t) {
        
        int i = s.length() - 1, j = t.length() - 1;
        while (i >= 0 || j >= 0) {
            i = backtrack(s, i);
            j = backtrack(t, j);
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;
            if ((i >= 0) != (j >= 0)) return false;
            i--;
            j--;
        }
        return true;
    }
    
    private int backtrack(String s, int index) {
        int i = index, count = 0;
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                count++;
                i--;
            } else if (count > 0) {
                count--;
                i--;
            } else break;
        }
        return i;
    }
}

/*
Method2: Stack: use stack to hold array content; pop if # if found
*/
class Solution {
    public boolean backspaceCompare(String s, String t) {
        
        return build(s).equals(build(t));
    }
    
    private String build(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != '#') stack.push(c);
            else if (!stack.isEmpty()) stack.pop();
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }
}
```