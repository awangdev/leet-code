E
1557812783
tags:String, Basic Implementation, Edge Case

Track: 纸上分析edge case.
Validation helps speed it up.

```

/*
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.
*/
/*
Keep Index of until finding it?
validaion optimization: all B chars should appear in A, to avoid unused calculation.
brutal: keep appending A to sb and do indexOf(B), until sb longer than B
edge case: one extra A should cover the bridge case
*/
class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (!validate(A, B)) return -1;
        int count = 0;
        StringBuffer sb = new StringBuffer();
        while (sb.length() < B.length()) {
            count++;
            sb.append(A);
        }
        if (sb.indexOf(B) != -1) return count;
        if (sb.append(A).indexOf(B) != -1) return count + 1;
        return -1;
    }
    
    private boolean validate(String A, String B) {
        boolean[] checkA = new boolean[256];
        boolean[] checkB = new boolean[256];
        
        for (char c : A.toCharArray()) {
            checkA[c] = true;
        }
        for (char c : B.toCharArray()) {
            checkB[c] = true;
        }
        for (int i = 0; i < 256; i++) {
            if (checkB[i] && !checkA[i]) {
                return false;
            }
        }
        return true;
    }
}
```