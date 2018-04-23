R
1524464833
tags: DFS, backtracking

#### Backtracking
- curr player 每走一步, 就生成一种新的局面, dfs on this
- 等到dfs结束, 不论成功与否, 都要backtracking
- curr level: 把"++" 改成 "--"; backtrack的时候, 改回 '--'
- 换成boolean[] 比 string/stringBuilder要快很多, 因为不需要重新生成string.
- ++ 可以走 (n - 1)个位置: 
- T(N) = T(1) + T(2) + T(3) + ... + T(N-2) + T(N - 1)
- => T(N) = 2T(N-1) = 2 * 2 * T(N - 2) = ... = (2^n)T(1) = O(2 ^ n)

##### iterate based on "++"
- 做一个String s的 replica: string or stringBuilder
- 每次dfs后, 然后更替里面的字符 "+" => "-"
- 目的只是Mark已经用过的index
- 真正的dfs 还是在 original input string s 身上展开
- 每次都重新生成substring, 并不是很efficient

##### Game theory
- 保证p1能胜利，就必须保持所有p2的move都不能赢
- 或者说, 在知道棋的所有情况时, 只要p2有一种路子会输, p1就一定能走对路能赢.
- 同时，p1只要在可走的Move里面，有一个move可以赢就足够了。
- p1: player1, p2: player2

#### O(N^2) 的 DP
- 需要Game Theory的功底, Nim game. https://www.jiuzhang.com/qa/941/
- http://www.1point3acres.com/bbs/thread-137953-1-1.html

```
/*
You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and 
therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. 
The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

Tags: Backtracking
Similar Problems: (E) Nim Game, (E) Flip Game
*/

public class Solution {
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        boolean[] sign = new boolean[s.length()];
        for (int i = 0; i < sign.length; i++) {
            sign[i] = s.charAt(i) == '+';
        }
        return dfs(sign);
    }

    public boolean dfs(boolean[] sign) {
        for (int i = 0; i < sign.length - 1; i++) {
            if (sign[i] && sign[i + 1]) {
                sign[i] = false;
                sign[i + 1] = false;
                // When opponent is possible to lose, return true for curr player
                if (!dfs(sign)) {
                    sign[i] = true;
                    sign[i + 1] = true;
                    return true;
                }
                sign[i] = true;
                sign[i + 1] = true;
            }
        }
        return false;
    }
}



/*
Thoughts:
DFS, search by replace '++' with "--" at each possible spot.

*/

class Solution {
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        return search(new StringBuilder(s));
    }
    
    public boolean search(StringBuilder sb) {
        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.substring(i, i + 2).equals("++")) {
                sb.replace(i, i + 2, "--");
                if (!canWin(sb.toString())) {
                    sb.replace(i, i + 2, "++");                    
                    return true;
                }
                sb.replace(i, i + 2, "++");
                
            }
        }
        return false;
    }
}


/*
    Make sure to use a new string, and do not alter the original input s 
    when calling recursively on canWin.
*/
public class Solution {
  public static boolean canWin(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.indexOf("++") != -1) {
            int index = sb.indexOf("++");
            if(!canWin(s.substring(0, index) + "--" + s.substring(index + 2))) {
                return true;
            }
            sb.replace(index, index + 1, "-");
        }
        return false;
    }
}



/*
Attemp2, from:http://www.cnblogs.com/jcliBlogger/p/4886741.html
Similar to my idea, but much more clear: no need of the isP1 flag.
Iterative idea:p1 can win, and p2 must not win at all.
Therefore, if p2's move can't win, we return true on p1's state.
For loop and the if statement works as 'OR': just need one of the p1's movement win.
*/

public class Solution {
  public boolean canWin(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        String str = new String(s);
        for (int i = str.indexOf("++"); i >= 0 && i < str.length() - 1; i = str.indexOf("++")) {
           if (!canWin( s.substring(0, i) + "--" + s.substring(i + 2))) {//Just pick one way of p1's move
               return true;
           }
            str = str.substring(0, i) + "-" + str.substring(i + 1);//Help to move certain spot.
        }
        return false;
    }

}
//let k = n/2
//O(k * (k - 1) * (k - 2) ... k) = O(k!) = O((n/2)!) = O(n!)

/*
Attempt1, Failed.
Thoughts:
method checkP1Win(), inside of it:
OR all p1's win state, if one of the move wins, return true;
However, a bit of code redundancy, does not feel good about this.
Fails on "+++++++++"
*/
public class Solution {
  public static boolean canWin(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        boolean rst = false;
        String str = new String(s);
        for (int i = str.indexOf("++"); i >= 0 && i < str.length() - 1; i = str.indexOf("++")) {
            if (checkP1Win(s, i, true)) {
                rst = true;
                break;
            }
            str = str.substring(0, i) + "-" + str.substring(i + 1);
        }
        return rst;

    }
    public static boolean checkP1Win(String str, int x, boolean isP1) {
        String s = str.substring(0,x) + "--" + str.substring(x + 2);
        if (s.indexOf("++") == -1) {
            return isP1;
        } 
	    for (int i = s.indexOf("++"); i >= 0 && i < s.length() - 1; i = s.indexOf("++")) {
	        if (checkP1Win(s, i, !isP1)) {
	            return true;
	        }
	        s = s.substring(0, i) + "-" + s.substring(i + 1);
	    } 
	    return false;
    }


}



public class Solution {
  public static boolean canWin(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        boolean rst = false;
        String str = new String(s);
        for (int i = str.indexOf("++"); i >= 0 && i < str.length() - 1; i = str.indexOf("++")) {
            if (checkP1Win(s, i, true)) {
                rst = true;
                break;
            }
            str = str.substring(0, i) + "-" + str.substring(i + 1);
        }
        return rst;

    }
    public static boolean checkP1Win(String str, int x, boolean isP1) {
        String s = str.substring(0,x) + "--" + str.substring(x + 2);
        if (s.indexOf("++") == -1) {
            return isP1;
        } 
        if (isP1) {
        	String temp = s;
		    for (int i = temp.indexOf("++"); i >= 0 && i < temp.length() - 1; i = temp.indexOf("++")) {
		        if (checkP1Win(s, i, !isP1)) {
		            return true;
		        }
		        temp = temp.substring(0, i) + "-" + temp.substring(i + 1);
		    } 
		    return false;
	    } else {
	    	String temp = s;
	    	for (int i = temp.indexOf("++"); i >= 0 && i < temp.length() - 1; i = temp.indexOf("++")) {
		        if (!checkP1Win(s, i, !isP1)) {
		            return false;
		        }
		        temp = temp.substring(0, i) + "-" + temp.substring(i + 1);
		    } 
		    return true;
	    }
    }
}
```