这个琢磨了我好半天。
第一个方法至少O(n^3),果然时间太多，输了给了李特。这种方法从两头check (i,j)，太慢。

第二个方法，是Code Granker上面的，利用了高中学排列组合时候的概念。
有个‘abc’，那么总共可以看成'a_b_c' 5个字符位置。
分为两种情况： 一种是在'a|b'的间隙里面分割；一种是'abc'分割在'b'上。这样是O(2n-1)*O(n) = O(n^2)，还不错。

方法三应该是DP。还没有细细研究。

````
/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, 
and there exists one unique longest palindromic substring.

Tags: String
Similar Problems: (H) Shortest Palindrome, (E) Palindrome Permutation

*/

/*
Attempt2: http://blog.csdn.net/linhuanmars/article/details/20888595, Code Granker

Assuming index x is the middle of the longest palindromic substring. Start from the middle, and check left,right indexes see if that are matching.

How many different index x can we check?
Think of string "abcd" as "a b c d", where the x can be any of 'a,b,c,d' character, and it can also just be in between of any two characters. That makes the total number of x to check: 2*n - 1. (n chars, and n-1 in-between space)

More details:
	When x is odd: x % 2 == 1, it's this pattern: a_b_c_d, for example x/2 = i lands on the 2nd space ' ', i == 3. 
	So: left = x/2 = 3 = ' '; right = x/2 = 3 = ' '. Right now, we are almost going to compare 'a_b' and '_c_d'.However, right needs to +1, because the we really want to compare 'a_b' and 'c_d'. We are split by the in-between-space, good to go.

	When x is even: x % 2 = 0, it's a_b_c, for example i lands on 'b', i ==2.
	So:left = x/2 = 2 = 'b'; right = x/2 = 2 = 'b'; We are splitting the string on 'b', it's okay. Then just split them like: 'a_' and '_c'.

	In conclusion: when x % 2 == 1: right = x/2 + 1;

Also, for each 'left' and 'right', start expanding left-- and right-- as long as they match the pattern of palindromic. Whenever failed to match, return whatever length of palindromic substring. 
Track the max length and max string.
*/

public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
        	return s;
        }
        int x = 2 * s.length() - 1;
        String str = "";
        int max = 0;
        for (int i = 0; i < x; i++) {//Check through the 2*len - 1
        	int left = i / 2;
        	int right = i / 2;
        	if (i % 2 == 1) {
        		right++;
        	}
        	//Check Palindromic
        	while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        		left--;
        		right++;
        	}
        	String temp = s.substring(left + 1, right);
        	//Track max
        	if (temp.length() > max) {
        		max = temp.length();
        		str = temp;
        	}
        }//END for
        return str;
    }
}

/*
1st Attempt.Thoughts:
Check palindromic with while(start < end){}.
Double-for loop on i ~ n.
Time: O(n^2 * n/2) = O(n^3)
*/
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
        	return s;
        }
        String rst = "";
        for (int i = 0; i < s.length(); i++) {
        	for (int j = s.length() - 1; j > i; j--) {
        		int start = i;
        		int end = j;
        		String str = s.substring(i,j + 1);
        		boolean isPalindromic = true;
        		while (start < end && isPalindromic) {
        			if (s.charAt(start) != s.charAt(end)) {
        				isPalindromic = false;
        			} else {
        			    start++;
        			    end--;
        			}
        		}
        		if (isPalindromic) {
        		    if (str.length() > rst.length()) {
        		        rst = str;
        		    }
        		}
        	}
        }//END for
        return rst;
    }
}






````