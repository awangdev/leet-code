H
tags: Graph
time: O(n)
space: O(n)

#### Graph
- analysis: 
    - 1) should not have mult-origin cases: 1 char maps to 1 char at maximum
    - 2) need a buffer char NOT exist in target to hold inter-media transformation 
        - check open char (out of 26 lower letter) that is NOT in target chars
- impl the validation rules
- more to read in https://leetcode.com/problems/string-transforms-into-another-string/discuss?currentPage=1&orderBy=most_votes&query=

```
/*
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.
 

Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.
*/

/*
#### Graph
- analysis: 
    - 1) should not have mult-origin cases: 1 char maps to 1 char at maximum
    - 2) need a buffer char NOT exist in target to hold inter-media transformation 
        - check open char (out of 26 lower letter) that is NOT in target chars
- impl the validation rules
*/
class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        Map<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(i), b = str2.charAt(i);
            map.putIfAbsent(a, b);
            if (map.get(a) != b) return false;
        }
        
        return new HashSet<>(map.values()).size() < 26;
    }
}
```