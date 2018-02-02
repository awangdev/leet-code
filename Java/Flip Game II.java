M

12.06.2015 recap:
注意：不要乱改input s. recursive call 需要用原始的input s.

这个题目李特是屌炸天的。
我飞了九牛二虎之力（路子对），但是代码写的七荤八素，好长好长好长好长的。
结果正解，三四行就搞定了。真是心有不甘啊。
想法如下：
保证p1能胜利，就必须保持所有p2的move都不能赢。
同时，p1只要在可走的Move里面，有一个move可以赢就足够了。
（题目里面用一个for loop + 只要 满足条件就return true来表达 OR的意思：p1不同的路子，赢一种就行了）
p1: player1
p2: player2

```
/*
You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

Tags: Backtracking
Similar Problems: (E) Nim Game, (E) Flip Game
*/

/*
    recap: 12.06.2015
    Make sure to use a new string, and do not alter the original input s when calling recursively on canWin.
*/

public class Solution {
  public static boolean canWin(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        String str = new String(s);
        while (str.indexOf("++") != -1) {
            int index = str.indexOf("++");
            if(!canWin(s.substring(0, index) + "--" + s.substring(index + 2))) {
                return true;
            }
            str = str.substring(0, index) + "-" + str.substring(index + 1);
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
  public static boolean canWin(String s) {
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