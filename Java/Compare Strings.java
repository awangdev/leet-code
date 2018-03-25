E
1522012438
tags: String

看StringA是不是包括所有 StringB的字符.

#### Basic Implementation
- 比较一下大小, null.
- 然后用int[]来count chars from A, count[x]++. 再对照chars in B, count[x]--
- 如果 count[c] < 0, 就 false.
- O(n)

```
/*
Compare two strings A and B, determine whether A contains all of the characters in B.

The characters in string A and B are all Upper Case letters.

Example
For A = "ABCD", B = "ABC", return true.

For A = "ABCD" B = "AABC", return false.

Tags Expand 
Basic Implementation String LintCode Copyright

*/

/*
Thoughts:
Loop over A, B and ++/-- chars
count arr should have no negative results
*/
public class Solution {
    public boolean compareStrings(String A, String B) {
        if (A == null || B == null || A.length() < B.length()) {
            return false;
        }
        int[] count = new int[26];
        
        for (char c : A.toCharArray()) {
            count[c - 'A']++;
        }

        for (char c : B.toCharArray()) {
            count[c - 'A']--;
            if (count[c - 'A'] < 0) {
                return false;
            }
        }
        return true;
    }
}

/*
Previous notes
Thinking process:
Count the number of occurance for StringA. 
Count the number of occurance for StringB.
Check if all of StringB's char# <= StringA's char# at each index.
 */
public class Solution {
    public boolean compareStrings(String A, String B) {
        if (A == null || B == null || A.length() < B.length()) {
            return false;
        }
        int[] countA = new int[26];
        int[] countB = new int[26];
        for (int i = 0; i < A.length(); i++) {
            countA[A.charAt(i) - 'A']++;
        }
        for (int i = 0; i < B.length(); i++) {
            countB[B.charAt(i) - 'A']++;
            if (countB[B.charAt(i) - 'A'] > countA[B.charAt(i) - 'A']) {
                return false;
            }
        }
        return true;
    }
}


```