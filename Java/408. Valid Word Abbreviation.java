E
1557989899
tags:String, Basic Implementation

tricky: find integer within a string
edge case: leading '0' should not be allow in such abbr.

```
/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
*/

/*
abbr uses int to replace letters.
walk through abbr, if non-int letter from abbr all matches original word, and travese completes, it's a true.
*/
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (abbr == null || abbr.length() > word.length()) {
            return false;
        }
        int index = 0; // index tracker on word
        int i = 0; // index tracker for abbr
        int m = word.length(), n = abbr.length();
        while (i < n && index < m) {
            if (word.charAt(index) == abbr.charAt(i)) {
                index++;
                i++;
                continue;
            }
            
            // edge case: no integer, or: leading '0' cannot be used for abbr
            if (!isInt(abbr.charAt(i)) || abbr.charAt(i) == '0') {
                return false;
            }
            // obtain the int
            StringBuffer sb = new StringBuffer();
            while (i < n && isInt(abbr.charAt(i))) {
                sb.append(abbr.charAt(i));
                i++;
            }
            index += Integer.parseInt(sb.toString());
        }
        return index == m && i == n;
    }
    
    private boolean isInt(char c) {
        return c >= '0' && c <= '9';
    }
}
```