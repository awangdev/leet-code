E
tags: Hash Table
1524017454

给J 和 S两个string. J里的character是unique 的珠宝, S 里面的character包含珠宝和石头. 找S里面有多少珠宝

#### Basic HashSet

```
/*
You're given strings J representing the types of stones that are jewels, 
and S representing the stones you have.  Each character in S is a type of stone you have.  
You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. 
Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.
*/

/*
Thoughts:
Have jewels in set. Iterate over S to check existance and count.
O(m + n), m = J.length(), n = S.length()
*/
class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || J.length() == 0 || S == null || S.length() == 0) {
            return 0;
        }
        int m = J.length();
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        for (char c : S.toCharArray()) {
            count += set.contains(c) ? 1 : 0;
        }
        return count;
    }
}
```