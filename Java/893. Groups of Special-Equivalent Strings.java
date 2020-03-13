E
1557809555
tags:String, Basic Implementation

Mark # of characters can be useful to print string signature

```
/*
You are given an array A of strings.

Two strings S and T are special-equivalent if after any number of moves, S == T.

A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].

Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.

Return the number of groups of special-equivalent strings from A.

 

Example 1:

Input: ["a","b","c","a","c","c"]
Output: 3
Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]
Example 2:

Input: ["aa","bb","ab","ba"]
Output: 4
Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
Example 3:

Input: ["abc","acb","bac","bca","cab","cba"]
Output: 3
Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]
Example 4:

Input: ["abcd","cdab","adcb","cbad"]
Output: 1
Explanation: 1 group ["abcd","cdab","adcb","cbad"]
 

Note:

1 <= A.length <= 1000
1 <= A[i].length <= 20
All A[i] have the same length.
All A[i] consist of only lowercase letters.
 */

/*
brutle: n^2: compare each one against all

`i % 2 == j % 2` means on the odd or on even indexes, anagram should be the same

*/
class Solution {
    public int numSpecialEquivGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        
        for (String s : A) {
            int[] odd = new int[256];
            int[] even = new int[256];
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i);
                if (i % 2 == 0) {
                    even[c]++;
                } else {
                    odd[c]++;
                }
            }
            String signature = Arrays.toString(odd) + Arrays.toString(even);
            set.add(signature);
        }

        return set.size();
    }
}
```
