E

比较一下大小, null.

然后用char[]来count chars from A. 再对照chars in B.

```
/*
Compare two strings A and B, determine whether A contains all of the characters in B.

The characters in string A and B are all Upper Case letters.

Example
For A = "ABCD", B = "ABC", return true.

For A = "ABCD" B = "AABC", return false.

Tags Expand 
Basic Implementation String LintCode Copyright

Thinking process:
Count the number of occurance for StringA. 
Count the number of occurance for StringB.
Check if all of StringB's char# <= StringA's char# at each index.
*/

public class Solution {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
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