E
tags: String

找一串String里面最长的公共prefix.

#### Sort, compare string
- Sort O(nlogn)
- first and last string should share common prefix
- 这里假设题目要求的是所有string的公共 prefix, 而不是部分strings

#### Brutle
- Nested loop, 每一次比较所有string 同位是否相等
- 相等，append string. 不等，return.
- O(mn)

```

/**
Leetcode
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
*/

/*
lintcode
Given k strings, find the longest common prefix (LCP).

Example
For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"

For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"

Tags Expand 
Enumeration Basic Implementation String LintCode Copyright

*/

/*
Assumption: requirement is common prefix for all strings.
Sort the strings: then head and tail should extract the common prefix
*/
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        Arrays.sort(strs);
        String head = strs[0];
        String tail = strs[strs.length - 1];
        
        int size = head.length() < tail.length() ? head.length() : tail.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (head.charAt(i) != tail.charAt(i)) {
                break;
            }
            sb.append(head.charAt(i));
        }
        return sb.toString();
    }
}

/*

Thoughts:
1. Continuous while loop until something breaks out.
2. Str to record the longest prefix
3. for loop on each while-loop, based on strs[0]
    Check if index exist
    check if all other strs have same char at this point. If so, add it. If not, break.

Note:
Arrays: use strs.length
String: use str.length().      

Note2: 
Ask for border case: when only 1 string, longest prefix turns out it's the strs[0] itself.

*/


public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = "";
        int ind = 0;
        while (ind < strs[0].length()) {
            char c = strs[0].charAt(ind);
            boolean valid = false;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() > ind && strs[i].charAt(ind) == c) {
                    valid = true;
                } else {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                prefix += "" + c;
            } else {
                break;
            }
            ind++;
        }//END WHILE
        return prefix;
    }
}


```