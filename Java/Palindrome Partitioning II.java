H
1523832548
tags: DP, Partition DP

给一个String s, 找出最少用多少cut, 使致 切割出的每一个substring, 都是palindrome

#### Partition DP
- Find minimum cut: 分割型DP
- 考虑[j, i - 1] 是否是回文串, 如果是, 那么: dp[i]= min(dp[i], d[j] + 1).

#### 计算Palindrome的优化
- 利用palindrome的性质, 可以算出 boolean palindrome[i, j]的情况. 
- 这样就给我们的问题合理降维, 目前是time: O(n^2). 
- 不然求一次palindrome, 就是n, 会变成O(n^3)

#### Previous Notes
- Double for loop 检查每种substring string (i~j). 若i,j相邻或者同点，那么肯定isPal；否则，i,j之间的（i+1, j-1）一定得isPal。
- 看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。
- okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
- 想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
- 反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
- 当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。
- 最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。


```
/*
Given a string s, cut s into some substrings such that every substring is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.
Example
For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
Tags Expand 
Dynamic Programming
*/

/*
Thoughts:
Find minimum?
Say dp[i] represents min # of cuts for string with length i.
Think about last index i: dp[i]'s min depends on if previous 1 or more indexes forms a palindrome:
dp[i] = Min(dp[i - 1], dp[i - 2], dp[i - 3], .... dp[i - x]) + 1, assuming [i-1, i) is palindrome.
Set up a j and let j = i - 1, j-- to try all options see if any [j, i-1] is palindrome.

Note:
dp[i] gives minimum # of palindromes.
We need cuts, so dp[i] - 1

O(n^2)
We optimized using a palindrome[][] to save all possible palindromes across [i,j]
*/
class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        boolean[][] palindrome = calcPalindrome(s.toCharArray());
        dp[0] = 0;

        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (palindrome[j][i - 1]) { // check if [j , i - 1]
                    dp[i] = Math.min(dp[i], dp[j] + 1); // move cursor to j
                }
            }
        }
        return dp[len] - 1; // need cuts
    }

    /*
        Find if any [i,j] is palindrome.
        Start attempting from mid point, and expand to both side. Set true of is palindrome.
        For odd length: there will be a mid point, and expansion goes to both sides naturally.
        For even length: mid and mid+1 will be middle 2 indexes.
    */
    private boolean[][] calcPalindrome(char[] s) {
        int n = s.length;
        boolean[][] palindrome = new boolean[n][n];
        
        for (int mid = 0; mid < n; mid++) {
            // odd
            int i = mid, j = mid;
            while (i >= 0 && j < n && s[i] == s[j]) {
                palindrome[i][j] = true;
                i--;
                j++;
            }
            
            i = mid;
            j = mid + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                palindrome[i][j] = true;
                i--;
                j++;
            }
        }
        return palindrome;
    }
}

/**
Print the min-cut solution
1. Record the index where the min-cut was taken.
2. Once the cuts: pi[i] is recorded, start from i = n to print the path
**/
class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        boolean[][] palindrome = calcPalindrome(s.toCharArray());
        dp[0] = 0;

        int[] pi = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (palindrome[j][i - 1]) { // check if [j , i - 1]
                    dp[i] = Math.min(dp[i], dp[j] + 1); // move cursor to j
                    if (dp[i] == dp[j] + 1) {
                        pi[i] = j;
                    }
                }
            }
        }
        // Print result
        int i = len;
        char[] arr = s.toCharArray();
        while (i != 0) {
            for (int j = pi[i]; j < i; j++) {
                System.out.print(arr[j]);
            }

            System.out.println();
            i = pi[i]; // move i to the cut position, backtrack
        }
        return dp[len] - 1; // need cuts
    }

    private boolean[][] calcPalindrome(char[] s) {
        int n = s.length;
        boolean[][] palindrome = new boolean[n][n];
        
        for (int mid = 0; mid < n; mid++) {
            // odd
            int i = mid, j = mid;
            while (i >= 0 && j < n && s[i] == s[j]) {
                palindrome[i][j] = true;
                i--;
                j++;
            }
            
            i = mid;
            j = mid + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                palindrome[i][j] = true;
                i--;
                j++;
            }
        }
        return palindrome;
    }
}

/*
Previous notes
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