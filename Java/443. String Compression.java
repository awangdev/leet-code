E
1557851233
tags:String, Basic Implementation

```
/*
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

 
Follow up:
Could you solve it using only O(1) extra space?

 
Example 1:

Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 

Example 2:

Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
 

Example 3:

Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
 

Note:

All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.
*/

/*
- for loop, track index, compare [i, i+1], count occurance
- no need to clena up rest
- return final last index 
- measure number over 10

edge case: 
1 char: show itself
no char or null: return
*/
class Solution {
    public int compress(char[] chars) {
        if (chars.length <= 1) {
            return chars.length; 
        }
        int index = 1, count = 1;
        char c = chars[0];
        
        // skip end case
        for (int i = 0; i < chars.length - 1; i++) {
            if (c == chars[i + 1]) {
                count++;
                continue;
            }

            // next char
            c = chars[i + 1]; 

            // closure of existing section
            if (count != 1) {
                index = assignChar(chars, index, count);
            }
            chars[index++] = c;
            count = 1;
        }
        
        // end case
        if (count != 1) {
            index = assignChar(chars, index, count);
        }

        // index is increased by 1 after use, which just equals the actual length
        return index;
    }
    
    private int assignChar(char[] chars, int index, int count) {
        String s = String.valueOf(count);
        for (char c : s.toCharArray()) {
            chars[index++] = c;
        }
        return index;
    }
}
```