		[Tutorial Link](https://www.youtube.com/watch?v=2hNK0Yz53LQ&list=PLZn-UvluQZuNedn1hDzTmNLE8MQWXjKVb)
过滤alphanumeric，其他字母掠过


---
**309. [Valid Parentheses.java](https://github.com/shawnfan/LintCode/blob/master/Java/Valid Parentheses.java)**		Level: Easy

剥皮过程。解铃还须系铃人   
左边的外皮'{['在stack底部   
右边的外皮应该和stack顶上的左外皮一一对应 




---
**310. [Valid Sudoku.java](https://github.com/shawnfan/LintCode/blob/master/Java/Valid Sudoku.java)**		Level: Easy

用HashSet存visited value.

方法1: 在nest for loop里面validate row,col,and block.     
validate block要利用i 和 j 增长的规律。    
说白了，i && j是按照0~n增长的index, 具体怎么用是可以flexible的。这个方法在同一个nest for loop解决所有运算。

方法2: 单独做block validation: validate block的时候虽然看到了4层for.其实也就是n^2.


---
**311. [Validate Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Validate Binary Search Tree.java)**		Level: Medium

查看每个parent-child关系。同时把root level上面传下来max,min界限定住。


---
**312. [Wiggle Sort.java](https://github.com/shawnfan/LintCode/blob/master/Java/Wiggle Sort.java)**
这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！

这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。

---
**313. [Wood Cut.java](https://github.com/shawnfan/LintCode/blob/master/Java/Wood Cut.java)**
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Note
You couldn't cut wood into float length.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge
O(n log Len), where Len is the longest length of the wood.

Tags Expand 
Binary Search

Thinking process:
Take the largest item. 
Priorities:
1. Have to get calculatedK >= givenK
2. Meanwhile, want to maximize the  smal piece.

One thing not clear: do we have to use the given small piece? If we have to, we need to concern about the shortest wood piece. See commentted-out part
In this problem, however, we can abandon the small pieces, as long as the max_small_pieces can allow calculatedK >= givenK.

Use binary search on the largest item:
1. if calculatedK < givenK: end = mid;
2. If calculated >= givenK, move start = mid as much as possible, which gives maximized small piece.

*/


public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0 || k < 0) {
            return 0;
        } 
        if (L.length == 1) {
            return L[0] / (L[0] / k);
        }
        Arrays.sort(L);
        int start = 0;
        int end = L[L.length - 1];
        int mid = 0;
        int max = 0;
       // int min = L[0];
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //if (mid > min) {
            //    end = mid;
           // } else {
                int count = 0;
                for (int i : L) {
                    count += i / mid;
                }
                if (count < k) {
                    end = mid;
                } else {
                    start = mid;
                    max = mid;
                }
            //}
        }//end while
        return max;
    }
}


---
**314. [Word Break II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Break II.java)**		Level: Hard

两个DP一起用.解决了timeout的问题     
1. isWord[i][j], subString(i,j)是否存在dict中？

2. 用isWord加快 isValid[i]: [i ～ end]是否可以从dict中找到合理的解？      
	从末尾开始查看i：因为我们需要测试isWord[i][j]时候，j>i, 而我们观察的是[i,j]这区间；       
	j>i的部分同样需要考虑，我们还需要知道isValid[0～j+1]。 所以isValid[x]这次是表示[x, end]是否valid的DP。     
	i 从 末尾到0, 可能是因为考虑到isWord[i][j]都是在[0~n]之内，所以倒过来数，坐标比较容易搞清楚。     
	(回头看Word Break I， 也有坐标反转的做法)

3. dfs 利用 isValid 和isWord做普通的DFS。

Note:
在Word Break里面用了set.contains(...), 在isValid里面，i 从0开始。 但是，contains()本身是O(n).     
在这道题里面应该是因为word dictionary太大，加上nest for, 变成O(n^3)所以timeout.

istead,用一个isWord[i][j]，就O(1)判断了i~j是不是存在dictionary里面。


---
**315. [Word Break.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Break.java)**		Level: Medium

DP

方法1:（attempt3 code）
state,rst[i]: 从[0～i] inclusive的string是否可以在dict中break开来找到？      
function: rst[i] = true if (rst[i - j] && set.contains(s.substring(i - j, i))); j in[0~i]     
1. rst[i - j] 记录的是[0, i-j]这一段是否可以break后在dict找到。     
2. 若true，再加上剩下所有[i-j, i]都能在dict找到，那么rst[i] = rst[0, i - j] && rst[i-j, i] == true

优化：找dict里面最长string, 限制j的增大。

(attempt4 code)    
与Word BreakII用同样的DP。
valid[i]: 记录从i到valid array末尾是否valid.


---
**316. [Word Ladder II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Ladder II.java)**		Level: Hard


---
**317. [Word Ladder.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Ladder.java)**		Level: Medium

BFS Brutle: 在start string基础上，string的每个字母都遍历所有26个字母，换换。

方法2:    
用Trie。 理应更快. However implementation可能有点重复计算的地方，LeetCode timeout. 需要再做做。


---
**318. [Word Pattern.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Pattern.java)**		Level: Easy

每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。


---
**319. [Word Search II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Search II.java)**		Level: Hard

Big improvement: use boolean visited on TrieNode!     
不要用rst.contains(...), 因为这个是O(n) 在leetcode还是会timeout（lintcode竟然可以pass）!    
在Trie search() method 里面，凡是visit过的，mark一下。  

Regular:   
for loop on words: inside, do board DFS based on each word.     
Time cpmplexity: word[].length * boardWidth * boardHeight * (4^wordMaxLength)

Build Trie with target words: insert, search, startWith.    
依然要对board matrix做DFS。

no for loop on words. 直接对board DFS:   
每一层,都会有个up-to-this-point的string. 在Trie里面check它是不是存在。以此判断。   
若不存在，就不必继续DFS下去了。

Trie solution time complexity, much better:      
build Trie:   n * wordMaxLength
search: boardWidth * boardHeight * (4^wordMaxLength + wordMaxLength[Trie Search])



---
**320. [Word Search.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Search.java)**		Level: Medium

Backtracking:
比较 Brutle。找到开头的字母，然后投入一个recursive找字母的工程：每到一个字母，朝四个方向走。他们之中，有一个true就可以。

Note:每次到一个字母，mark一下'#'. 4个path recurse回来后，mark it back.

Backtracking方法2:    
用一个boolean visited[][]




---
**321. [Zigzag Iterator.java](https://github.com/shawnfan/LintCode/blob/master/Java/Zigzag Iterator.java)**
每次next(), 相应的list的头拿下来就好。
然后就跑圈呗，每次刷一个list头。不难。只要把几个variable维护清楚就行。

---
