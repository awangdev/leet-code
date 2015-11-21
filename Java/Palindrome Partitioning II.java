Double for loop 检查每种substring string (i~j). 若i,j相邻或者同点，那么肯定isPal；否则，i,j之间的（i+1, j-1）一定得isPal。
看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。

okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
    当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。

最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。
```
/*
Given a string s, cut s into some substrings such that every substring is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.
Example
For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
Tags Expand 
Dynamic Programming
Thinking process:
DP problem.
Use a isPal to record if any [i ~ j] is Palindrome, true/false
    for any char s[i] and s[j], if s[i] == s[j], then need to check if [i + 1, j - 1] is Palindrome, which is just isPal[i + 1, j - 1].
Use cut[j] to record the minimal cut from char index [0 ~ j] 
    by default, cut[j] = j because the worst condition is cut j times at each charactor: none 2+ character palindrome, and split into individual chars.
    update cut[j] by comparing existing cut[j] and (cut[i - 1] + 1).
At the end, return cut[s.length() - 1].
*/

public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        boolean[][] isPal = new boolean[length][length];
        int[] cut = new int[length];
        for (int j = 0; j < length; j++) {
            cut[j] = j;
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                    if (i > 0) {
                        cut[j] = Math.min(cut[j], cut[i - 1] + 1);
                    } else {
                        cut[j] = 0;
                    }
                }
            }//end i_for
        }//end for j_for
        return cut[length - 1];
    }
};
```