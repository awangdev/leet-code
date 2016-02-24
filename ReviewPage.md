# Review Page

This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. 
New problems will be automatically updated once added.

**0. [2 Sum II - Input array is sorted.java](https://github.com/shawnfan/LintCode/blob/master/Java/2 Sum II - Input array is sorted.java)**		Level: Medium

排序好的array. two pointer前后夹击。



---
**1. [2 Sum II.java](https://github.com/shawnfan/LintCode/blob/master/Java/2 Sum II.java)**		Level: Medium

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer


---
**2. [2 Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/2 Sum.java)**		Level: Medium

解法1：相对暴力简洁, HashMap<value, index>，找到一个value, 存一个; 若在HashMap里面 match 到结果, 就return HashMap里存的index. O(n) space && time.

解法2：Sort array, two pointer 前后++,--搜索。Sort 用时O(nlogn).   
    1. 第一步 two pointer 找 value.    
    2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)    
    O(n) space, O(nlogn) time.



---
**3. [3 Sum Closest.java](https://github.com/shawnfan/LintCode/blob/master/Java/3 Sum Closest.java)**		Level: Medium

3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用


---
**4. [3 Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/3 Sum.java)**		Level: Medium

用个for loop 加上 2sum 的土办法。

注意:   
   1. 找 value triplets, 多个结果。注意，并非找index。    
   2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。

步骤:   
   1. For loop 挑个数字A.    
   2. 2Sum 出一堆2个数字的结果    
   3. Cross match 步骤1里面的A.   

时间 O(n^2), 两个nested loop


另外, 还是可以用HashMap来做2Sum。稍微短点。还是要注意handle duplicates.

再另外（leetcode做时写的）：先sort，然后two pointer。


---
**5. [3Sum Smaller.java](https://github.com/shawnfan/LintCode/blob/master/Java/3Sum Smaller.java)**发现j,k满足条件时候，(k - j)就是所有 sum <target的情况了。
而一旦>target, 又因为j不能后退，只能k--，那么问题就被锁定了. 这样可以做到O(n2)

---
**6. [4 Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/4 Sum.java)**		Level: Medium

方法1： 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)

方法2： 参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/      
   1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
   2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
   3. 再尝试从所有i+1后面,找合适的2nd pair。   

   注意：在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。


---
**7. [A+B.java](https://github.com/shawnfan/LintCode/blob/master/Java/A+B.java)**		Level: Easy

Bit Operation
 
   a & b: 每bit可能出得余数       
   a ^ b: 每bit在此次操作可能留下的值，XOR 操作         
   每次左移余数1位，然后存到b, 再去跟a做第一步。loop until b == 0    



---
**8. [Add and Search Word.java](https://github.com/shawnfan/LintCode/blob/master/Java/Add and Search Word.java)**		Level: Medium

Trie结构, prefix tree.
节点里面有char, isEnd, HashMap<Character, TrieNode>
记得怎么造trie：无增有移，没node就加，有node就移动。
寻找word也一样逻辑：无错有移。



---
**9. [Add Binary.java](https://github.com/shawnfan/LintCode/blob/master/Java/Add Binary.java)**		Level: Easy

方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on



---
**10. [Add Two Numbers II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Add Two Numbers II.java)**		Level: Medium

LinkedList并没有反过来，那么自己反：   
   方向相反。巧用stack.

做加法都一样：   
   1. carrier
   2. carrier = (rst + carrier) / 10
   3. rst = (rst + carrier) % 10


---
**11. [Add Two Numbers.java](https://github.com/shawnfan/LintCode/blob/master/Java/Add Two Numbers.java)**		Level: Easy

LinkedList都已经反转好了，直接做。

遍历两个l1,l2把carry-on处理好，每次生成一个新node，最后检查carry-on。

跟Add Binary的理解方式一模一样。



---
**12. [Alien Dictionary.java](https://github.com/shawnfan/LintCode/blob/master/Java/Alien Dictionary.java)**There is a new alien language which uses the latin alphabet. 
However, the order among letters are unknown to you. 
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
Hide Company Tags Google Facebook
Hide Tags Graph Topological Sort
Hide Similar Problems (M) Course Schedule II

*/

/*
	NOT DONE
	Thoughts:
	They have sink node. They form a valid tree, without sycle.
	A char can visit another node, does not mean they have order.
	A char appear in a lower row means they have different order.
	For 1st column, w appears before e, e appears before r.
	For 2nd column:r appears before t, t appears before f
	For 3rd col: t appears before f.
	For 4th col, nothing to compare.
	So make in[][]: [w,e] [e,r][r,t][t,f] based on the possible order.

	Then do topological sort on the sequence and mark the sequence like in course schedule II
*/

public class Solution {
    public String alienOrder(String[] words) {
        
    }
}









---
**13. [Anagrams.java](https://github.com/shawnfan/LintCode/blob/master/Java/Anagrams.java)**		Level: Medium

HashMap 的做法. sort每个string, 存进HashMap, 重复的就是anagrams,最后输出。   
   toCharArray
   Arrays.sort
   Stirng.valueOf(char[])

时间n*L*O(logL),L是最长string的长度。



另一种做法：http://www.jiuzhang.com/solutions/anagrams/   
   1. take each string, count the occurrance of the 26 letters. save in int[]count.   
   2. hash the int[] count and output a unique hash value.   
      hash = hash * a + num   
      a = a * b.   
   3. save to hashmap in the same way as we do. 

这一步把for s: strs 里面的时间复杂度降到了O(L). L = s.length().   
Need to work on the getHash() function.

时间变成n*O(L). Better.



---
**14. [Backpack II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Backpack II.java)**		Level: Medium

做了Backpack I, 这个就如出一辙。   
想法还是，选了A[i-1] 或者没选A[i].   
一路往前跑不回头。就出来了。   
其实这个Backpack II 还更容易看懂代码。

O(m)的做法:   
想想，的确我们只care 最后一行，所以一个存value的就够了。    
注意：和bakcpackI的 O(m)一样的，j是倒序的。如果没有更好的j，就不要更新。就是这个道理。   



---
**15. [Backpack.java](https://github.com/shawnfan/LintCode/blob/master/Java/Backpack.java)**		Level: Medium

DP。   
   row是item大小: 0, A[0], A[1] ... A[A.length -1]
   col是背包累积的size: 0, 1, 2, ... m.

想法：
   dp[i][j]有这么i-1个item, 用他们可否组成size为j的背包？true/false.  （反过来考虑了，不是想是否超过size j， 而是考虑是否能拼出exact size == j）。   
   注意注意：虽然dp里面一直存在i的位置，实际上考虑的是在i位置的时候，看前i-1个item.    

看一遍code，会发现：
   1. picked A[i-1]: 如果上一个item, A[i-1],被加了上来, 用j-A[i-1]看看，是否这再前一步也true. true就好啦。   
   2. did not pick A[i-1]: 那就是说，不加上A[i-1], 上一行d[i-1][j]还是需要是true。   

最后：   
   跑一边dp 最下面一个row.  从末尾开始找，最末尾的一个j (能让dp[i][j] == true)的，就是最多能装的大小 :)   

时间，空间都是：O(mn)



再有：     
O(m)时间的做法，具体看solution. 注意j是倒序的啊！   
依然是O(mn)的空间



---
**16. [Balanced Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Balanced Binary Tree.java)**		Level: Medium

1. DFS using depth marker: 每个depth都存一下。然后如果有不符合条件的，存为-1.
   一旦有-1， 就全部返回。
   最后比较返回结果是不是-1. 是-1，那就false.
   Traverse 整个tree, O(n)

2. 从基本的题目理解考虑，想到leaf node的情况。如果判断了leaf node, 那其他node应该就是可以recursive。
   直接在isBalanced上面recursive.
   关键return false的判断情况：如果有个node是null, 那么同一行相邻的那个，一旦有了children,那么就说明两个分支的depth已经是>=2了，那么就return false.
   
   然后这个可能是个小小的优化，因为不需要计算所有的depth.一旦发现一个false,其他的就不需要计算，直接返回了。



---
**17. [Best Time to Buy and Sell Stock I.java](https://github.com/shawnfan/LintCode/blob/master/Java/Best Time to Buy and Sell Stock I.java)**		Level: Medium

理解意思是关键：   
   每天都就交易价格，n天只让买卖一次，那就找个最低价买进，找个最高价卖出。
   记录每天最小值Min是多少。O(n)
   每天都算和当下的Min买卖，profit最大多少。



---
**18. [Best Time to Buy and Sell Stock II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Best Time to Buy and Sell Stock II.java)**		Level: Medium

和Stock I 的区别：可以买卖多次，求总和的最大盈利。

找涨幅最大的区间，买卖：
找到低谷，买进:peek = start + 1 时候，就是每次往前走一步;若没有上涨趋势，继续往低谷前进。
涨到峰顶，卖出:一旦有上涨趋势，进一个while loop，涨到底, 再加个profit.

中间的：
   profit += prices[peek - 1] - prices[start]; 听特别的。
   当没有上涨趋势时候，peek-1也就是start, 所以这里刚好profit += 0.

O(n)


---
**19. [Best Time to Buy and Sell Stock III .java](https://github.com/shawnfan/LintCode/blob/master/Java/Best Time to Buy and Sell Stock III .java)**		Level: Medium

比stock II 多了一个限制：只有2次卖出机会。也就是：找峰头；然后往下再找一个峰头。

怎么样在才能Optimize两次巅峰呢？

从两边同时开始找Max！（棒棒的想法）

   leftProfit是从左往右，每个i点上的最大Profit。
   rightProfit是从i点开始到结尾，每个点上的最大profit.
   那么在i点上，就是leftProfit，和右边rightProfit的分割点。在i点，leftProfit+rightProfit相加，找最大值。

三个O(n),还是O(n)


---
**20. [Best Time to Buy and Sell Stock IV.java](https://github.com/shawnfan/LintCode/blob/master/Java/Best Time to Buy and Sell Stock IV.java)**		Level: Hard

记得要理解： 为什么 i-1天的卖了又买，可以和第 i 天的卖合成一次交易？
   因为每天交易的price是定的。所以卖了又买，等于没卖！这就是可以合并的原因。要对价格敏感啊少年。

Inspired from here:
http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html

局部最优解 vs. 全局最优解：   
   local[i][j] = max(global[i – 1][j – 1] + diff, local[i – 1][j] + diff)
   global[i][j] = max(global[i – 1][j], local[i][j])

local[i][j]和global[i][j]的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生。
   当第i天的价格高于第i-1天（即diff > 0）时，那么可以把这次交易（第i-1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]+diff；
   当第i天的价格不高于第i-1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff<=0，所以可写成local[i][j]=global[i-1][j-1]。
   (Note:在我下面这个solution里面没有省去 +diff）

global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，可分为两种情况：
   如果第i天没有交易（卖出），那么global[i][j]=global[i-1][j]；
   如果第i天有交易（卖出），那么global[i][j]=local[i][j]。




---
**21. [Binary Representation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Representation.java)**		Level: Hard

首先要分两半解决，断点是'.': str.split("\\.");

Integer那一半好弄，whie loop里： num%2, num/2。

Decimal那边复杂点.
   bit == 1的数学条件：当下num * 2 >= 1。 更新: num = num * 2 - 1;
   bit == 0的数学条件： num * 2 < 1. 更新: num = num * 2

注意：num是 double, 小数在 （num = num * 2 -1）的公式下可能无限循环. 因此check: num重复性，以及binary code < 32 bit.

(所以题目也才有了32BIT的要求！)


---
**22. [Binary Search Tree Iterator.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Search Tree Iterator.java)**		Level: Medium

用O(h)空间的做法：

理解binary search tree inorder traversal的规律：
   先找left.left.left ....left 到底，这里是加进stack.
   然后考虑parent,然后再right.

例如这题：
   stack里面top，也就是tree最左下角的node先考虑,取名rst.
   其实这个rst拿出来以后, 它也同时是最底层left null的parent，算考虑过了最底层的parent。
   最后就考虑最底层的parent.right, 也就是rst.right.

注意:
   next()其实有个while loop, 很可能是O(h).题目要求average O(1),所以也是okay的.


用O(1)空间的做法：不存stack, 时刻update current为最小值。

找下一个最小值：   
   如果current有right child,那么再找一遍current.right的left-most child,就是最小值了。
   如果current没有right child,那么就要找current node的右上parent.

注意：
   一定要确保找到的parent满足parent.left == current.
   反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
   但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。



---
**23. [Binary Tree Inorder Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Inorder Traversal.java)**		Level: Easy

法一:      
Recursive: Divide and Conquer, with helper method

法二:   
Stack: 
Add left nodes all the way   
Print curr   
Move to right, add right if possible.   
  
注意stack.pop()在加完left-most child 的后，一定要curr = curr.right.

若不右移，很可能发生窘境：    
curr下一轮还是去找自己的left-most child，不断重复curr and curr.left, 会infinite loop, 永远在左边上下上下。



---
**24. [Binary Tree Level Order Traversal II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Level Order Traversal II.java)**		Level: Medium

普通BFS，用一个queue，加上一个queue.size()来交替换行.

rst里面add(0,...)每次都add在list开头



---
**25. [Binary Tree Level Order Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Level Order Traversal.java)**		Level: Medium

方法1. 最普通,Non-recursive: BFS, queue, 用个queue.size()来end for loop:换行。   
   或者用两个queue. 当常规queue empty，把backup queue贴上去。

方法2. Recursive with dfs:   
   每个level都应该有个ArrayList. 那么用一个int level来查看：是否每一层都有了相应的ArrayList。   
   如果没有，就加上一层。    
   之后每次都通过DFS在相应的level上面加数字。



---
**26. [Binary Tree Longest Consecutive Sequence.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Longest Consecutive Sequence.java)**我洋洋洒洒那么多行，最后还不work.看了solution, 如此精简。

主要想法：
Recursive用好。首先在这个level比一比，可否成。
不成的话，另立门户。
然后左右开弓。再把结果拿过来比较一下就好了。简单明了。

---
**27. [Binary Tree Maximum Path Sum II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Maximum Path Sum II.java)**		Level: Medium

比Binary Tree Maximum Path Sum I 简单许多. 因为条件给的更多：at least 1 node + have to start from root => have to have root.

方法1:   
维持一个global或者recursive里的sum。traversal entire tree via DFS. 简单明了。


方法2:   
Single path: either left or right.   
If the path sum < 0, just skip it.   


---
**28. [Binary Tree Maximum Path Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Maximum Path Sum.java)**		Level: Medium

第一次做有点难理解，复杂原因是：因为可能有负值啊。不能乱assume正数。   
   single path max 的计算是为了给后面的comboMax用的。
   如果single path max小于0，那没有什么加到parent上面的意义，所以就被再次刷为0.

combo的三种情况：(root可能小于0)   
   1. 只有left    
   2。 只有右边   
   3. root大于0，那么就left,right,curr全部加起来。

情况1和情况2取一个最大值，然后和情况三比较。做了两个Math.max(). 然后就有了这一层的comboMax


12.11.2015 recap:   
   So totally, 5 conditions:   
   (save in single)    
        left + curr.val OR right + curr.val   
   (save in combo:)    
   left, right, OR left + curr.val + right   




---
**29. [Binary Tree Path Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Path Sum.java)**		Level: Easy

Binary Tree的一个基本题。   
遍历到底，比较sum vs. target。   
注意divide的情况。要把遍历的例子写写。   



---
**30. [Binary Tree Paths.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Paths.java)**		Level: Easy

方法1：   
Recursive:分叉。Helper。

方法2，Iterative:    
   非递归练习了一下   
   因为要每次切短list, 所以再加了一个Stack 来存level   



---
**31. [Binary Tree Postorder Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Postorder Traversal.java)**		Level: Easy

最prefer 2 stack的做法：   
   stack1和stack2合作。倒水。记这个做法。。。挺神奇的。

Divide and Conquer 的recursive方法也非常明了！

注意，这些binary tree traversal的题目，常常有多个做法:recursive or iterative


---
**32. [Binary Tree Preorder Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Preorder Traversal.java)**		Level: Easy

Preorder 写写， stack   
1. Divide and conquer   
2. Stack(NON-recursive) push curr, push right, push left.   
3. recursive with helper method   


---
**33. [Binary Tree Right Side View.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Right Side View.java)**		Level: Medium

最右:即level traversal每一行的最末尾.   

BFS，用queue.size()来出发saving result.


---
**34. [Binary Tree Serialization.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Serialization.java)**		Level: Medium

方法1: BFS. Non-recursive, using queue. 想法直观。level-order traversal. save到一个string里面就好。

方法2: DFS. Recursive. 需要一点思考。basically divide and conquer. 但是代码相对来说短。


---
**35. [Binary Tree Zigzag Level Order Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Binary Tree Zigzag Level Order Traversal.java)**		Level: Medium

简单的level traversal.根据level奇数偶数而add到不同位子.


---
**36. [Building Outline.java](https://github.com/shawnfan/LintCode/blob/master/Java/Building Outline.java)**Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。
An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.
Example
Given 3 buildings：
[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：
[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]
Note
Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.
Tags Expand 
LintCode Copyright Heap
*/
/*
Thoughts:
Well, based on JiuZhang, http://www.jiuzhang.com/solutions/building-outline/, implement a HashHeap. 
**HashHeap. Super long implementation: http://www.jiuzhang.com/solutions/hash-heap/
*/





/****
  Attempt1, may not be correct.
  Thoughts: 
  PriorityQueue<Point>, sort by start.
  1. Keep track of max height. 
  2. Find max height.
  3. Poll() queue. Whenever there is a jump(up or down) at current node, close a interval.
  4. When closing interval, set prev = new node.h
****/


/**

What is HashHeap Exactly? Document below:

**/
class HashHeap {
    //Heap is a arraylist, which stores the actaul Integer values. It stores the real data
    ArrayList<Integer> heap;
    //HashMap stores the actual value, and the corresponding node.
    HashMap<Integer, Node> hash;
    String mode;
    int size_t;
   
    /*
      Used in HashMap,
      id: id in the Heap tree
      num: The frequency of the appearance of this id.
    */
    class Node {
        public Integer id;  
        public Integer num;

        Node(Node now) {
            id = now.id;
            num = now.num;
        }

        Node(Integer first, Integer second) {

            this.id = first;
            this.num = second;
        }
    }

    public HashHeap(String mod) { // 传入min 表示最小堆，max 表示最大堆
        // TODO Auto-generated constructor stub
        heap = new ArrayList<Integer>();
        mode = mod;
        hash = new HashMap<Integer, Node>();
        size_t = 0;
    }
    /*Regular peak, size, empty functions*/
    int peak() {
        return heap.get(0);
    }

    int size() {
        return size_t;
    }

    Boolean empty() {
        return (heap.size() == 0);
    }
    // Basis of heap
    int parent(int id) {
        if (id == 0) {
            return -1;
        }
        return (id - 1) / 2;
    }
    // Basis of heap. Our heap is base indxe = 0
    int lson(int id) {
        return id * 2 + 1;
    }
    // Basis of heap. Our heap is base indxe = 0
    int rson(int id) {
        return id * 2 + 2;
    }
    // Basis of heap. 
    //If min heap, parent < left/right child
    //If max heap, parent > left/right child
    boolean comparesmall(int a, int b) {
        if (a <= b) {
            if (mode == "min")
                return true;
            else
                return false;
        } else {
            if (mode == "min")
                return false;
            else
                return true;
        }

    }
    //swap value in heap based the 2 ids
    //based on value, create new node in hashmap.
    void swap(int idA, int idB) {
        int valA = heap.get(idA);
        int valB = heap.get(idB);

        int numA = hash.get(valA).num;
        int numB = hash.get(valB).num;
        hash.put(valB, new Node(idA, numB));
        hash.put(valA, new Node(idB, numA));
        heap.set(idA, valB);
        heap.set(idB, valA);
    }

    //Similar to delete, but only delete element at index==0, and return the value
    Integer poll() {
        size_t--;
        Integer now = heap.get(0);
        Node hashnow = hash.get(now);
        if (hashnow.num == 1) {
            swap(0, heap.size() - 1);
            hash.remove(now);
            heap.remove(heap.size() - 1);
            if (heap.size() > 0) {
                siftdown(0);
            }
        } else {
            hash.put(now, new Node(0, hashnow.num - 1));
        }
        return now;
    }
    //Add
    //If exist, count++ in hashmap
    //If not exited, add to tail, then sfitup
    void add(int now) {
        size_t++;
        if (hash.containsKey(now)) {
            Node hashnow = hash.get(now);
            hash.put(now, new Node(hashnow.id, hashnow.num + 1));

        } else {
            heap.add(now);
            hash.put(now, new Node(heap.size() - 1, 1));
        }

        siftup(heap.size() - 1);
    }
    //Remove node
    //If not last one, count-- from the hashMap
    //If last one, move it to tail of the structure, and delete it.
    //When the id is not tail (note, the id is already attached with new values after swap), then siftup and siftdown to sort all ids
    void delete(int now) {
        size_t--;
        ;
        Node hashnow = hash.get(now);
        int id = hashnow.id;
        int num = hashnow.num;
        if (hashnow.num == 1) {

            swap(id, heap.size() - 1);
            hash.remove(now);
            heap.remove(heap.size() - 1);
            if (heap.size() > id) {
                siftup(id);
                siftdown(id);
            }
        } else {
            hash.put(now, new Node(id, num - 1));
        }
    }
    //If the target id and its parent do not comply the heap structure, siftup.
    void siftup(int id) {
        while (parent(id) > -1) {
            int parentId = parent(id);
            if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
                break;
            } else {
                swap(id, parentId);
            }
            id = parentId;
        }
    }
    //If the target id and its children do not comply with the heap structure, siftdown
    void siftdown(int id) {
        while (lson(id) < heap.size()) {
            int leftId = lson(id);
            int rightId = rson(id);
            int son;
            if (rightId >= heap.size() || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
                son = leftId;
            } else {
                son = rightId;
            }
            if (comparesmall(heap.get(id), heap.get(son)) == true) {
                break;
            } else {
                swap(id, son);
            }
            id = son;
        }
    }
}

---
**37. [Burst Balloons.java](https://github.com/shawnfan/LintCode/blob/master/Java/Burst Balloons.java)**dp[i][j] =  balloons i~j 之间的sum. 然后找哪个点开始burst? 设为x。
For loop 所有的点作为x， 去burst。
每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。


这个是momorization, 而不纯是DP
因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing

---
**38. [Classical Binary Search.java](https://github.com/shawnfan/LintCode/blob/master/Java/Classical Binary Search.java)**
   while: start + 1 < end
   mid = start + (end - start) / 2;
   末尾double check start, end.



---
**39. [Climbing Stairs.java](https://github.com/shawnfan/LintCode/blob/master/Java/Climbing Stairs.java)**40% Accepted
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example
Tags Expand 

Thinking process:
State: at i level, f[i] is the ways to climb to i position.
Function: f[i] = f[i-1] + f[i-2]. 
	f[i] is constructed from 2 branches: 
		Last step is 1 from f[i-1]
		Last step is 2 from f[i-2]
	This idea can be presented using a tree. However we don’t need to do recursive. We just need to use two pointers to withhold 2 level’s values.
Init: The for loop starts at level2, so before level 2 there are 2 init states:
	f[0] == 1. This means we jump 2 steps from level0 to level2. 
	f[i] == 1.  This means we jump 1 steps to level1, then jump another step to level2
Answer: f[n]
*/

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int last = 1;   //Init f[1]
        int lastlast = 1;   //Init f[0]
        int now = 0;
        for (int i = 2; i <= n; i++) {  //Start from level2
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
}



---
**40. [Clone Graph.java](https://github.com/shawnfan/LintCode/blob/master/Java/Clone Graph.java)**Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Hide Tags Depth-first Search Breadth-first Search Graph

	
*/

/*
    //NEED TO RUN THIS ON LINT
    Thoughts: 12.12.2015
    The original thoughs of using ArrayList, and using a index to track of which node has not been visited.
        It's alright, but it uses extra space, and basically copie all nodes again.
        It's similar to using a queue.
        At the end, it's doing O(m * n)
    Maybe can improve this.

    Need a queue and process each element. and a hashmap to track duplicates.
        1. make sure the node is no duplicate
        2. make sure to all all child

    border: case: node == nul, or node has not child, return a new instance of it'self?

*/

public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null || node.neighbors.size() == 0) {
            return node;
        }

        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = 
            new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();

        queue.offer(node);
        //process each node
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            UndirectedGraphNode newNode;
            if (!map.containsKey(curr)) {
                map.put(curr, new UndirectedGraphNode(curr.label));
            }
            UndirectedGraphNode newNode = map.get(curr);
            //Add neighbors for each node
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                UndirectedGraphNode newNeighbor;
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                }
                newNeighbor = map.get(neighbor);

                newNode.neighbors.add(newNeighbor);
            }//end for

        }//end while

        return map.get(node);        
    }
}

  

/*

    
Thinking process:
1. Clone all nodes available: using HashMap to go through all possible query. No duplicates added using HashMap.
    HashMap map has the list of all new nodes. No neighbors added yet
    <key,value> = <original node,  new node with just a label (without neighbor list)>
    At same time, the arrayList nodes has all original nodes(with neighbors) in Breadth-first order.
2. Add neighbor for nodes in map:
    - Locate the 'newNode' from map by using the key: the original node
    - loop through the original node's neighbor size
    - use original neighbor as key to get the new neighbor instance from map
    - add this new neighbor instance to the neighbor list of 'newNode'
*/
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        nodes.add(node);
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        map.put(node, new UndirectedGraphNode(node.label));
        int start = 0;
        //Clone nodes without neighbors:
        while (start < nodes.size()) {
            List<UndirectedGraphNode> neighbors = nodes.get(start++).neighbors;
            for (int i = 0; i < neighbors.size(); i++) {
                if (!map.containsKey(neighbors.get(i))) {
                    map.put(neighbors.get(i), new UndirectedGraphNode(neighbors.get(i).label));
                    nodes.add(neighbors.get(i));
                }
            }
        }
        // Clone neighbors:
        for (int i = 0; i < nodes.size(); i++) {
            UndirectedGraphNode newNode = map.get(nodes.get(i));
            for (int j = 0; j < nodes.get(i).neighbors.size(); j++) {
                newNode.neighbors.add(map.get(nodes.get(i).neighbors.get(j)));
            }
        }
        return map.get(node);    
    }
}


---
**41. [Closest Binary Search Tree Value.java](https://github.com/shawnfan/LintCode/blob/master/Java/Closest Binary Search Tree Value.java)**Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Tags: Tree Binary Search
Similar Problems: (M) Count Complete Tree Nodes, (H) Closest Binary Search Tree Value II

*/


/*
Thoughts:
Binary search, maintain a closest value.
Note: initial closest in real case is just the root, since we start from the root
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int closestValue(TreeNode root, double target) {
 		if (root == null) {
 			return 0;
 		}       
 		double closest = root.val;
 		while (root != null) {
 			if (root.val == target) {
 				return root.val;
 			} else {
 				if (Math.abs(target - closest) >= Math.abs(target - root.val)) {
 					closest = root.val;
 				}
 				if (root.val > target) {
 					root = root.left;
 				} else {
 					root = root.right;
 				}
 			}
 		}//END while
 		return (int)closest;
    }
}

---
**42. [Closest Number in Sorted Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Closest Number in Sorted Array.java)**一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)

---
**43. [Coins in a Line.java](https://github.com/shawnfan/LintCode/blob/master/Java/Coins in a Line.java)**However, 分析过后简直屌炸天。一个 n%3就解决了。纯粹math.

---
**44. [Combination Sum II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Combination Sum II.java)**

---
**45. [Combination Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Combination Sum.java)**记得求sum时候也pass 一个sum进去，backtracking一下sum也，这样就不必每次都sum the list了。

---
**46. [Combinations.java](https://github.com/shawnfan/LintCode/blob/master/Java/Combinations.java)**Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example
For example,
If n = 4 and k = 2, a solution is:
[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
Tags Expand 
Backtracking Array

Thinking process:
Use a helper method to perform recursive backtracking:add an element to next-level recursive call, and remote the entry after the recursive call.
Note: When 'new' something, cannot use 'List' because it's a abstract class. Need to new 'ArrayList'
*/

public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (n <= 0 || k <= 0) {
            return rst;
        }
        List<Integer> solution = new ArrayList<Integer>();
        helper(rst, solution, n, k, 1);// Start == 1 because we want 1 ~ n in this problem
        return rst;
    }
    public void helper(List<List<Integer>> rst, 
                List<Integer> solution, int n, int k, int start) {
        if (solution.size() == k) {
            rst.add(new ArrayList(solution));
            return;
        }        
        for (int i = start; i <= n; i++) {// <=n because we want 1 ~ n in this problem
            solution.add(i);
            helper(rst, solution, n, k, i + 1);
            solution.remove(solution.size() - 1); //Back-track
        }
    }
}


---
**47. [Compare Strings.java](https://github.com/shawnfan/LintCode/blob/master/Java/Compare Strings.java)**Compare two strings A and B, determine whether A contains all of the characters in B.

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


---
**48. [Complete Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Complete Binary Tree.java)**		Level: Easy

BFS   

Use a flag . 当出现了第一次有 null children的node的时候，   
说明complete tree的最低level出现了。    
自此以后，queue再不该有node再有child, queue后面出现的node的左右孩子应该都是null.    



---
**49. [Construct Binary Tree from Inorder and Postorder Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Construct Binary Tree from Inorder and Postorder Traversal.java)**		Level: Medium

写个Inorder和Postorder的例子。利用他们分left/right subtree的规律解题。

Postorder array 的末尾， 就是当下层的root.   
在Inorder array 里面找到这个root,就刚好把左右两边分割成left/right tree。

这题比较tricky地用了一个helper做recursive。 特别要注意处理index的变化, precisely考虑开头结尾

可惜有个不可避免的O(n) find element in array.


---
**50. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Construct Binary Tree from Inorder and Preorder Traversal.java)**		Level: Medium

和Construct from Inorder && Postorder 想法一样。

写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。


---
**51. [Container With Most Water.java](https://github.com/shawnfan/LintCode/blob/master/Java/Container With Most Water.java)**左右两墙，往中间跑动。
另，若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低）；但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）

---
**52. [Convert Binary Search Tree to Doubly Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Convert Binary Search Tree to Doubly Linked List.java)**不论right == null or != null, 每次都要强行move to right.

如果不node = node.right,
很可能发生窘境：
node alays = stack.top(), 然后stack.top()一直是一开始把left 全部遍历的内容。所以就会infinite loop, 永远在左边上下上下。

---
**53. [Convert Expression to Polish Notation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Convert Expression to Polish Notation.java)**根据题意，Tree出来以后，来个Pre-order-traversal.

---
**54. [Convert Expression to Reverse Polish Notation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Convert Expression to Reverse Polish Notation.java)**这个里面把TreeNode就当做成我们需要的node,里面扩展成有left/right child的node.
这题，目的是建造tree,然后来个post-traversal就行了。

---
**55. [Convert Integer A to Integer B.java](https://github.com/shawnfan/LintCode/blob/master/Java/Convert Integer A to Integer B.java)**Determine the number of bits required to convert integer A to integer B 

Example
Given n = 31, m = 14,return 2

(31)10=(11111)2

(14)10=(01110)2

Tags Expand 
Cracking The Coding Interview Bit Manipulation Binary Representation

Thinking process:
Assume the integer is 32 bit.
XOR a and b, shift by 1 bit everytime -> want to check the XORed value at index 0 : just & 1 will do.
Count the above calculated result: how many bit difference do a and b have.
*/

class Solution {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (a ^ b) >> i & 1;
        }
        return count;
    }
};



---
**56. [Convert Sorted Array to Binary Search Tree With Minimal Height.java](https://github.com/shawnfan/LintCode/blob/master/Java/Convert Sorted Array to Binary Search Tree With Minimal Height.java)**		Level: Easy

Binary Search的感觉. 中间一开两半, divde and conquer,左右各自recursive下去build left/right child.


---
**57. [Convert Sorted List to Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Convert Sorted List to Binary Search Tree.java)**
找到mid。
然后把root = mid.next 

然后开始sortedListToBST(mid.next.next); //后半段
mid.next = null;//非常重要，要把后面拍过序的断掉
sortedListToBST(head); //从头开始的前半段


最后root.left, root.right merge一下。


---
**58. [Copy List with Random Pointer.java](https://github.com/shawnfan/LintCode/blob/master/Java/Copy List with Random Pointer.java)**31% Accepted
A linked list is given such that each node contains an additional random pointer 
which could point to any node in the list or null.

Return a deep copy of the list.

Example
Tags Expand 
Hash Table Linked List

*/	

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
    Recap: 12.10.2015
    Iterative through the list. 
    Use a dummyHead and return dummyHead.next at the end.
    In each iteration, check if Head is already exist, or make a new one
    * use HashMap<oldNode, newNode> to mark if a node has been visited.
    deep copy the random node of head as well.
    
    border case: if head == null, return null
*/


public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        //creat node, used to link all nodes
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode node = dummy;
        RandomListNode newNode;
        
        //HashMap to mark node
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        while(head != null) {
            //process head. (we already know head!=null)
            if (!map.containsKey(head)) {
                map.put(head, new RandomListNode(head.label));
            }
            newNode = map.get(head);
            node.next = newNode;
            //process head.random
            if (head.random != null) {
                if(!map.containsKey(head.random)) {
                    map.put(head.random, new RandomListNode(head.random.label));
                }
                newNode = map.get(head.random);
                node.next.random = newNode;
            }
            node = node.next;
            head = head.next;
        }
        return dummy.next;
    }
}

/*
Thinking process:
1. Loop through the original list
2. Use a HashMap<old node, new node>. User the old node as a key and new node as value.
3. Doesn't matter of the order of node that being added into the hashMap.
    For example, node1 is added.
    node1.random, which is node 99, will be added into hashMap right after node1.
4. During the loop:
    If head exist in hashmap, get it; if not existed, create new node using head, add into hashMap
    If head.random exist, get it; if not, add a new node using head.random.

*/
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        RandomListNode newNode;
        while (head != null) {
            //Add new node 
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            //Add new node's random node
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }
            //append and shift
            pre.next = newNode;
            pre = newNode;
            head = head.next;
        }
        return dummy.next;
    }
}


---
**59. [Cosine Similarity.java](https://github.com/shawnfan/LintCode/blob/master/Java/Cosine Similarity.java)**
---
**60. [Count 1 in Binary.java](https://github.com/shawnfan/LintCode/blob/master/Java/Count 1 in Binary.java)**Count how many 1 in binary representation of a 32-bit integer.

Example
Given 32, return 1

Given 5, return 2

Given 1023, return 9

Challenge
If the integer is n bits with m 1 bits. Can you do it in O(m) time?

Tags Expand 
Binary Bit Manipulation

Thoughts:
1. break string into char[]
2. convert char[] into integer using Character.getNumericValue()

*/




public class Solution {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        if (num < 0) {
            return 0;
        }
        String bits = Integer.toBinaryString(num);
        char[] bitArray = bits.toCharArray();
        int sum = 0;
        for (int i = 0; i < bitArray.length; i++) {
            sum += Character.getNumericValue(bitArray[i]);
        }
        return sum;
    }
};

---
**61. [Count and Say.java](https://github.com/shawnfan/LintCode/blob/master/Java/Count and Say.java)**The count-and-say sequence is the sequence of integers beginning as follows:

1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.

11 is read off as "two 1s" or 21.

21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Example
Given n = 5, return "111221".

Note
The sequence of integers will be represented as a string.

Tags Expand 
String


1. Set up initial value '11'
2. use while loop to build on past variable
3. In each while loop case, break the string into charArray, count and name mark the type
4. In for loop: when different, append string (count+type); when same, count++.
*/


public class Solution {
    /**
     * @param n the nth
     * @return the nth sequence
     */
    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String str = "11";
        int ind = 2;
        while (ind < n) {
        	StringBuffer sb = new StringBuffer();
        	char[] arr = str.toCharArray();
	        int count = 1;
	        int type = Character.getNumericValue(arr[0]);
	        for (int i = 1; i < arr.length; i++) {
	            if (arr[i] == arr[i - 1]) {
	                count++;
	            } else {
	                sb.append(count + "" + type);
	                type = Character.getNumericValue(arr[i]);
	                count = 1;
	            }
	        }
        	ind++;
        	sb.append(count + "" + type);
        	str = sb.toString();
        }
        return str;
    }
}

---
**62. [Count of Smaller Number before itself.java](https://github.com/shawnfan/LintCode/blob/master/Java/Count of Smaller Number before itself.java)**		Level: Hard

与Count of Smaller Number非常类似。以实际的value来构成segment tree，leaf上存（count of smaller number）。

Trick: 先Query，再modify.   
每次Query时候，A[i]都还没有加入到Segment Tree 里面，而A[i+1,...etc]自然也还没有加进去。   
那么就自然是coutning smaller number before itself.   
刁钻啊！   

另外注意：   
在modify里面：多Check了root.start <= index 和  index <= root.end。 过去都忽略了。以后可以把这个也写上。   
（其实是Make sense的，就是更加严格地check了index再 root.left 或者 root.right里面的站位）   


---
**63. [Count of Smaller Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Count of Smaller Number.java)**		Level: Medium

和平时的segment tree问题不同。 0 ～ n-1代表实际数字。是造一个based on real value的segment tree.
Modify时，把array里面的value带进去，找到特定的位子（leaf）,然后count+1. 

最终在SegmentTree leaf上面全是array里面实际的数字。

trick:   
在query前，给进去的start和end是： 0 ~ value-1.   
value-1就是说，找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   


[那么其他做过的SegmentTree是怎么样呢？]   
那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 

[但是这题]   
构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    

其实很容易看穿:   
若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.

若题目让构造一个空心SegmentTree, based on value 0 ~ n-1 (n <= 10000), 然后把一个Array的value modify 进去。   
这样八成是另外一种咯。


---
**64. [Count Primes.java](https://github.com/shawnfan/LintCode/blob/master/Java/Count Primes.java)**还有另外一个中定义方法：
这个n,有没有小于n的一个i,而达到： i*i + # of i = n. 如果有，那就不是 prime。 

方法很牛逼也很数学。没做的时候可能想不到。做了之后就觉得，哎，我去，有道理啊。
简而言之：简历一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.
然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.
所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.
最后，数一遍还剩下的true个数就好了

---
**65. [Course Schedule II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Course Schedule II.java)**
---
**66. [Course Schedule.java](https://github.com/shawnfan/LintCode/blob/master/Java/Course Schedule.java)**是topological sort的题目。一般都是给有dependency的东西排序。

最终都会到一个sink node， 再不会有向后的dependency, 在那个点截止。
我就已这样子的点为map的key, 然后value是以这个node为prerequisite的 list of courses.

画个图的话，prerequisite都是指向那个sink node， 然后我们在组成map的时候，都是从sink node 发散回来到dependent nodes.

在DFS里面，我们是反向的， 然后，最先完全visited的那个node, 肯定是最左边的node了，它被mark的seq也是最高的。

而我们的sink node，当它所有的支线都visit完了，seq肯定都已经减到最小了，也就是0，它就是第一个被visit的。



---
**67. [Data Stream Median.java](https://github.com/shawnfan/LintCode/blob/master/Java/Data Stream Median.java)**Numbers keep coming, return the median of numbers at every time a new number added.

Have you met this question in a real interview? Yes
Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge
Total run time in O(nlogn).

Clarification
What's the definition of Median? - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

Tags Expand 
LintCode Copyright Heap Priority Queue
*/



public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        int[] rst = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return rst;
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        });
        
        rst[0] = nums[0];
        maxHeap.offer(rst[0]);
        
        for (int i = 1; i < rst.length; i++){
            int preMedian = maxHeap.peek();
            if (nums[i] > preMedian) {
                minHeap.offer(nums[i]);
            } else {
                maxHeap.offer(nums[i]);
            }

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            rst[i] = maxHeap.peek();
        }
        return rst;
    }
}

---
**68. [Delete Digits.java](https://github.com/shawnfan/LintCode/blob/master/Java/Delete Digits.java)**Given string A representative a positive integer which has N digits, remove any k digits of the number, the remaining digits are arranged according to the original order to become a new positive integer.

Find the smallest integer after remove k digits.

N <= 240 and k <= N,

Example
Given an integer A = "178542", k = 4

return a string "12"

Tags Expand 
Greedy LintCode Copyright

Attempt2,Thoughts:
loop k times: each interation, find one digit to remove
Rules: want to remove whatever digit at A[i] that's A[i] > A[i+1].
Reason: Higher position (left side of the string) is always stronger/high number, and remove the strong/high digit will always be right option.
Well... thinking straight (attempt2) seems much easier to understand and to code up than my attempt1

Note:
remember to remove the prefixing 0's
*/

public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        if (A == null || A.length() == 0 || k == 0) {
            return A;
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < A.length(); j++) {
                if (j == A.length() - 1) {
                    A = A.substring(0, j);
                    break;
                } else if (A.charAt(j) > A.charAt(j + 1)) {
                    A = A.substring(0, j) + A.substring(j + 1);
                    break;
                }
            }
        }
        //remote prefixing-0's
        int i = 0;
        while(i < A.length() && A.charAt(i) == '0') {
            i++;
        }
        return A.substring(i);
    }
}




/*
Attempt1: Lintcode 83% correct, but Does not work for : [9876141516171818818181890001988181700198181778786761256512651653145345143, 55]
my output: 	1111111134143
expect:		1111111345143

Not sure where went wrong.

Thoughts:
This seems to be: Pick (N - k) digits and make a smallest number, without changing the order of digits.
Create an array with length == (N - k): digits
Starting from i = 0, digits[0] = A.charAt[0] - '0'
if A[i] < digits[i] , replace digits[i] with A[i]
	Note: here loop through (N - k) and see if the A[i] can be put anywhere

Note: handle prefix '0' in string
*/
public class Solution {
    public static String DeleteDigits(String A, int k) {
    	if (A == null || A.length() == 0 || k < 0) {
    		return A;
    	}
    	int n = A.length() - k;
    	//System.out.println(A.length() + " " + n);
    	int[] digits = new int[n];
    	for (int j = 0; j < n; j++) {
    		digits[j] = -1;
    	}
    	int[] backup = Arrays.copyOf(digits, digits.length);
    	for (int i = 0; i < A.length(); i++) {
    		int digit = (int)(A.charAt(i) - '0');
    		
    		for (int j = 0; j < n; j++) {
    			if ((digit < digits[j] || digits[j] < 0) && (A.length() - i) >= (n - j)) {
    				//System.out.println(j + " " + digit + " | " + (A.length() - i) + " " + (n - j));
    				if (j == 0) {
    					digits = Arrays.copyOf(backup, backup.length);
    				}
    				digits[j] = digit;
    				break;
    			}
    		}
    	}
    	//System.out.println(Arrays.toString(digits));
    	String rst = "";
    	for (int j = 0; j < n; j++) {
    		if (rst.length() == 0 && digits[j] == 0) {
    			continue;
    		} else {
    			rst += digits[j];
    		}
    	}

    	return rst;
    }

}

---
**69. [Delete Node in the Middle of Singly Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Delete Node in the Middle of Singly Linked List.java)**Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.

Example
Given 1->2->3->4, and node 3. return 1->2->4

Tags Expand 
Cracking The Coding Interview Linked List

Thoughts:
1. Only have this node, make it look like its next
2. remove next

*/


/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param node: the node in the list should be deleted
     * @return: nothing
     */
    public void deleteNode(ListNode node) {
        if (node == null) {
        	return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

---
**70. [Distinct Subsequences.java](https://github.com/shawnfan/LintCode/blob/master/Java/Distinct Subsequences.java)**Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example
Given S = "rabbbit", T = "rabbit", return 3.

Challenge
Do it in O(n2) time and O(n) memory.

O(n2) memory is also acceptable if you do not know how to optimize memory.

Tags Expand 
String Dynamic Programming
*/
/*
Attempt2:
Use DP. Okay, first I had no idea how to start, but here is a reference: http://blog.csdn.net/abcbc/article/details/8978146
First of all, Map out the number of existance of T in S in a 2D map:
	  0 1 2 3 4 5 6 7
	  ---------------
	    r a b b b i t
0|	  1 1 1 1 1 1 1 1
1|	r 0 1 1 1 1 1 1 1
2|	a 0 0 1 1 1 1 1 1
3|	b 0 0 0 1 2 3 3 3
4|	b 0 0 0 0 1 3 3 3
5|	i 0 0 0 0 0 0 3 3
6|	t 0 0 0 0 0 0 0 3  

Use DP[T][S]. We realize:
1.DP[0][0] == 1; //Both null can be a match
2.DP[0][1 ~ S.length - 1] = 1;//First fow, when T=="", whatever S will have 1 subsequence: ""
3.DP[1 ~ T.length][0] = 0;// First column, when S=="", whatever T will not be subsequence of S == ""
4.When looking at each row and filling out the pixels, we realize when T exist in S[a~b], it will surely exist in S[a~b+1], taht is:
	Step1: DP[i][j] is at least equal to DP[i][j - 1];//DP[i][j] is always based on DP[i][j-1], so DP[i][j] = DP[i][j+1] + something
	Step2: So, what's that 'something' in step1?  For example, look at T[3] == 'b' against S[0 ~ 3]:
		S[0 ~ 3] has 1 'b' at S[3], and also, T[0~3] == S[0~3], that's a perfect match. SO DP[3][3] = 1
		S[0 ~ 4] has 2 'b' at S[3] and S[4]. Now imagine we pick either S[3] or S[4] to genreate T[0~3] out of S[0~4]: we have 2 possibilities.D[3][4] = 2
			Consider: D[i][j] means we picked S[j]; in our S[0 ~ 4] case, that means we picked S[4] but skipped S[3], though S[3] still counts towards another situation where we skipped S[4].
					After all, we will count whatever that we skipped into our current DP[i][j], that is DP[i][j] += T[i - 1] == S[j - 1] ? DP[i - 1][j - 1] : 0;
	Conclusion: while we for-looping through each row, if we find out S[j] and S[j - 1] both equals to T[i - 1], we want to make sure we count D[i - 1][j -1]'s previous records in!

Note:
In double for loop, set i,j <= xxxx.length(), since we've increased the 2D array by 1 block on row and col.
*/


public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
    	int[][] DP = new int[T.length() + 1][S.length() + 1];
    	DP[0][0] = 1;
    	for(int i = 1; i < S.length(); i++) {
    		DP[0][i] = 1;
    	}
    	for (int i = 1; i < T.length(); i++) {
    		DP[i][0] = 0;
    	}
    	for (int i = 1; i <= T.length(); i++) {
    		for (int j = 1; j <= S.length(); j++){
    			DP[i][j] = DP[i][j - 1];
    			if (T.charAt(i - 1) == S.charAt(j - 1)) {
    				DP[i][j] += DP[i - 1][j - 1];
    			}
    		}
    	}
    	return DP[T.length()][S.length()];
    }
}


/*
Attemp1:
recursive on substring of S, accumulate total count
However, exceed time limit
*/
public class Solution {
    public int numDistinct(String S, String T) {
    	if (S.length() == 0) {
    		return T.length() == 0 ? 1 : 0;
    	}
    	if (T.length() == 0) {
    		return 1;
    	}
    	int count = 0;
    	for (int i = 0; i < S.length(); i++) {
    		if (S.charAt(i) == T.charAt(0)) {
    			count += numDistinct(S.substring(i + 1), T.substring(1));
    		}
    	}
    	return count;
    }
}



/*
First Thought:
find the # of ways to get T from S, while having to follow the rules of 'subsequence'
How about: find what chars are missing in T based on S, then find the number of ways to insert the missing chars to make it back to S?
The missing chars: misChars = new ArrayList<String>();
However, time cost on this:
For example I have n missing chars from S.length == m. so I have (m + 1) places where i can insert the n chars. Then it's a mCn problem. This goes up to m!, too much. Not applicapable.

*/

---
**71. [Easy Reverse Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Easy Reverse Linked List.java)**39% 通过
Reverse a linked list.

样例
For linked list 1->2->3, the reversed linked list is 3->2->1

挑战
Reverse it in-place and in one-pass

标签 Expand 
Linked List

Thinking process:
User a variable ‘newList’(or reversedList) to store the partial list. It will be the final result
this will first be null
then something will add newList to its tail
The ‘something’ is actually the ‘head’ during each cycle
save: newList = something
Every cycle, cut off head.next
Store head.next in a temporary node, which we will use later. Now, link a different head.next
append: head.next = newList
Like in ThinkingProcess(1), save: newList = head.
At this moment, we are about to get into next cycle, change the head to the cut-off part that saved in the temporary node.
Cycle until head is null. 
return newList
*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode reversedList = null;
        while (head != null) {
            ListNode cutOffPart = head.next;
            head.next = reversedList;
            reversedList = head;
            head = cutOffPart;
        }
        return reversedList;
    }
}




---
**72. [Edit Distance.java](https://github.com/shawnfan/LintCode/blob/master/Java/Edit Distance.java)**Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example
Given word1 = "mart" and word2 = "karma", return 3.

Tags Expand 
String Dynamic Programming

Thoughts:
Draw a 2D array, consider rows as word1 and cols as word2. 
DP[i][j] means the steps (edit distance) to take to transfer word1[0 ~ i] to word2[0 ~ j]
And, we have 3 different calculations for the 3 methods:
1. Replace: DP[i][j] = word1[i-1] == word2[j-1] ? DP[i - 1][j - 1] : DP[i-1][j-1] + 1;
2. Insert: DP[i][j]  = word1[i - 1][j] + 1; // missing 1 char in word1
3. Delete: DP[i][j]  = word1[i][j - 1] + 1; // extra char in word1

Note: just remember to start from i=1,j=1, because we are using DP[i-1][j-1], becareful with border case
*/



public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
		if (word1 == null && word2 != null) {
			return word2.length();
		} else if (word1 != null && word2 == null) {
			return word1.length();
		} else if (word1 == null && word2 == null) {
			return 0;
		}
		int[][] DP = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 1; i <= word1.length(); i++) {
			DP[i][0] = i;
		}
		for (int j = 1; j <= word2.length(); j++) {
			DP[0][j] = j;
		}

		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				DP[i][j] = Math.min(Math.min(DP[i - 1][j] + 1, DP[i][j - 1] + 1), word1.charAt(i - 1) == word2.charAt(j - 1) ? DP[i - 1][j - 1] : DP[i - 1][j - 1] + 1);
			}
		}

		return DP[word1.length()][word2.length()];
    }
}

---
**73. [Encode and Decode Strings.java](https://github.com/shawnfan/LintCode/blob/master/Java/Encode and Decode Strings.java)**不难，但是要考虑好如何handle ""。
因为平时都把“” 当做Null对待，这里就犯浑了。
这题，要把Null特别mark一下为‘NULL’，而特别处理 “” empty string.

---
**74. [Excel Sheet Column Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Excel Sheet Column Number.java)**记得：'A' - 'A' = 0 呀。所以 any char - 'A' + 1 = 这个char在我们这个题目里面所代表的数字。
其次，26个字母代表着26位运算。和10位一样嘛，那第一位x26就好了。

---
**75. [ExcelSheetColumnNumber .java](https://github.com/shawnfan/LintCode/blob/master/Java/ExcelSheetColumnNumber .java)**Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*/


public class Solution {//ABC -> 'A', 'B', 'C'
    public int titleToNumber(String s) {//S = AA
        int rst = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {//i = 0,1,2 // (char c : arr)
            rst = rst * 26 + arr[i] - 'A' + 1;//rst =1, 26 + 1 = 27, 
        }
        return rst;
    }
}

---
**76. [Expression Evaluation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Expression Evaluation.java)**做的还是PostTraversal。先eval left, right, 然后eval符号。

注意Handle数字时，若左右Child全Null,那必定是我们weight最大的数字node了。
若有个child是null,那就return另外一个node。
还要注意：
过程中用个Long吧，最后结局在cast back to int.

---
**77. [Expression Tree Build.java](https://github.com/shawnfan/LintCode/blob/master/Java/Expression Tree Build.java)**这个题目是Min-tree， 头上最小，Logic 和max-tree如出一辙。
注意虚拟的形态：treeNode,作用就是为了有个weight，好排序。
要想想，Java这个strict mom，如果换做JavaScript, 直接在expressionTreeNode上面附加一个object就完了，哪还用什么另外一个TreeNode class.
O(n)

---
**78. [Fast Power.java](https://github.com/shawnfan/LintCode/blob/master/Java/Fast Power.java)**Calculate the a^n % b where a, b and n are all 32bit integers.

Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)

Tags Expand 
Divide and Conquer

Thoughts:
Learn online:
(a * b) % p = (a % p * b % p) % p
Than mean: a ^ n can be divided into a^(n/2) * a^(n/2), that can be used for recursion: divde and conqure.

Note: when n is odd number, it cannot be evenly divided into n/2 and n/2. This case needs special treatment: n = n/2 + n/2 + 1;
*/

class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
    	if (n == 0) {
    		return 1 % b;
    	}
    	if (n == 1) {
    		return a % b;
    	}

    	long recurPow = fastPower(a, b, n / 2);
    	recurPow = (recurPow * recurPow) % b;

    	if (n % 2 == 1) {
    		recurPow = recurPow * a % b;
    	}

    	return (int)recurPow;
    }
};

---
**79. [Fibonacci.java](https://github.com/shawnfan/LintCode/blob/master/Java/Fibonacci.java)**Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:

The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.
The first ten numbers in Fibonacci sequence is:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...


Example
Given 1, return 0

Given 2, return 1

Given 10, return 34

Note
The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.

Tags Expand 
Enumeration Mathematics Non Recursion

Thoughts:
1. If non-recursion, do for loop for that n
2. Note: this specfiic problem is not 0-based. it's 1-based.
3. return fib[n]
*/

class Solution {
    /**
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        if (n <= 1) {
        	return 0;
        }
        int[] fib = new int[n + 1];
        fib[1] = 0;
        fib[2] = 1;
        for (int i = 3; i <= n; i++) {
        	fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}

---
**80. [Find Minimum in Rotated Sorted Array II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Find Minimum in Rotated Sorted Array II.java)**Medium Find Minimum in Rotated Sorted Array II My Submissions

40% Accepted
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

Example
Given [4,4,5,6,7,0,1,2] return 0

Tags Expand 
Binary Search Divide and Conqueri

Thinking process:
It seems using binary search will leads to O(n), so just use a for loop with O(n)
*/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            if (min > num[i]) {
                min = num[i];
            }
        }
        return min;
    }
}


---
**81. [Find Minimum in Rotated Sorted Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Find Minimum in Rotated Sorted Array.java)**Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

Example
Given [4,5,6,7,0,1,2] return 0

Tags Expand 
Binary Search

Thinking process:
Understand how to use binary in this problem: compare the mid point with end point.
In this problem, because the sorted line is cut at one point then rotate, so one of the line is absolutely greater than the other line.
Situation 1:
if mid < end :  that means minimum is on the end point's line. Move end to left. end = mid.
Situation 2:
if mid > end: that means there must be a mountain-jump somewhere after mid and before end, which is the minimum point. Now move start to mid.
*/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int start = 0;
        int end = num.length - 1;
        int mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (num[mid] > num[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (num[start] < num[end]) {
            return num[start];
        } else {
            return num[end];
        }
    }
}


---
**82. [Find Peak Element II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Find Peak Element II.java)**There is an integer matrix which has the following features:

The numbers in adjacent positions are different.
The matrix has n rows and m columns.
For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
We define a position P is a peek if:

A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.

Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1 ,2 ,3 ,6 ,5],
  [16,41,23,22,6],
  [15,17,24,21,7],
  [14,18,19,20,10],
  [13,14,11,10,9]
]
return index of 41 (which is [1,1]) or index of 24 (which is [2,2])

Note
The matrix may contains multiple peeks, find any of them.

Challenge
Solve it in O(n+m) time.

If you come up with an algorithm that you thought it is O(n log m) or O(m log n), can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?

Tags Expand 
Binary Search LintCode Copyright Matrix
*/

/*
	NOT DONE. Will try if have time
*/


class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
    }
}


---
**83. [Find Peak Element.java](https://github.com/shawnfan/LintCode/blob/master/Java/Find Peak Element.java)**一个特别的check condition, 和特别的move left, move right的case罢了。

---
**84. [Find the Connected Component in the Undirected Graph.java](https://github.com/shawnfan/LintCode/blob/master/Java/Find the Connected Component in the Undirected Graph.java)**
一定注意要把visit过的node Mark一下。因为curr node也会是别人的neighbor，会无限循环。

Component的定义：所有Component内的node必须被串联起来via path (反正这里是undirected, 只要链接上就好)

这道题：其实component在input里面都已经给好了，所有能一口气visit到的，全部加进queue里面，他们就是一个component里面的了。

而我们这里不需要判断他们是不是Component。

---
**85. [Find the Weak Connected Component in the Directed Graph.java](https://github.com/shawnfan/LintCode/blob/master/Java/Find the Weak Connected Component in the Directed Graph.java)**看到了weak component的形式： 一个点指向所有，那么所有的点都有一个公共的parent，然后就是要找出这些点。

为何不能从一个点出发，比如A，直接print它所有的neighbors呢？
	不行，如果轮到了B点，那因为是directed,它也不知道A的情况，也不知道改如何继续加，或者下手。

所以，要把所有跟A有关系的点，或者接下去和A的neighbor有关系的点，都放进union-find里面，让这些点有Common parents.

最后output的想法：
做一个 map <parent ID, list>。
之前我们不是给每个num都存好了parent了嘛。
每个num都有个parent, 然后不同的parent就创造一个不同的list。
最后，把Map里面所有的list拿出来就好了。

---
**86. [First Bad Version.java](https://github.com/shawnfan/LintCode/blob/master/Java/First Bad Version.java)**isBadVersion 是有方向的嘛，一个点错了，后面全错。

---
**87. [First Missing Positive.java](https://github.com/shawnfan/LintCode/blob/master/Java/First Missing Positive.java)**Given an unsorted integer array, find the first missing positive integer.

Example
Given [1,2,0] return 3, and [3,4,-1,1] return 2.

Challenge
Your algorithm should run in O(n) time and uses constant space.

Tags Expand 
Array

Thoughts:
It means: after it's sorted, what's the first missing postive int counted from 1 ---> more

1. Arrays.sort();
2. count = first non-zero element in A.
3. count +1, and see if maches the current A[i]?

NOTE:
Deal with negative and positive number separately
Watch out for redundant number: ask if the list has duplicated elements
*/


public class Solution {
    /**    
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
    	if (A == null || A.length == 0) {
    		return 1;
    	}
    	Arrays.sort(A);
    	int count = -1;
    	for (int i = 0; i < A.length; i++) {
    		if (A[i] > 0) {
    			if (count < 0) {//process 1st positive element
	    			count = A[i];
	    			if (count != 1) {
	    				return 1;
	    			}
    			} 
    			else if (A[i] == A[i - 1]) {//watch out for duplicates
    				count--;
    			}
				else if(A[i] != count) {//if not match, kick out
					return count;
				}	
    			count++;
    		}
    	}
    	if (count < 0) {//if all negative, return 1
    		return 1;
    	}
    	return count;
    }
}

---
**88. [Flatten 2D Vector.java](https://github.com/shawnfan/LintCode/blob/master/Java/Flatten 2D Vector.java)**Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.


Tags: Design
Similar Problems: (M) Binary Search Tree Iterator, (M) Zigzag Iterator, (M) Peeking Iterator

*/

/*
Thoughts:
As hint indicates: use 2 pointers to hold position.
Use hasNext to validate (x,y)  and move x.
Use next() to return (x,y) and move it(regardless of correctness, which is determined by hasNext())
*/
public class Vector2D {
	private int x;
	private int y;
	private List<List<Integer>> list;
    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d == null) {
        	return;
        }
        this.x = 0;
        this.y = 0;
        this.list = vec2d;
    }

    public int next() {
        int rst = list.get(x).get(y);
        if (y + 1 >= list.get(x).size()) {
        	y = 0;
        	x++;
        } else {
        	y++;
        }
        return rst;
    }

    public boolean hasNext() {
        if (list == null) {
            return false;
        }
        while (x < list.size() && list.get(x).size() == 0) {
        	x++;
        	y = 0;
        }
        if (x >= list.size()) {
        	return false;
        }
        if (y >= list.get(x).size()) {
        	return false;
        }
        return true;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

---
**89. [Flatten Binary Tree to Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Flatten Binary Tree to Linked List.java)**
Not Done



---
**90. [Flattern 2D Vector.java](https://github.com/shawnfan/LintCode/blob/master/Java/Flattern 2D Vector.java)**注意啊，一开始理解题意搞错：我以为是必须要排序正确，所以上来就PriorityQueue+HashMap搞得无比复杂。其实，这个跟一个nxn的matrix遍历，是没区别的拉。
所有来个x,y，把2d list跑一变。


---
**91. [Flip Game II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Flip Game II.java)**注意：不要乱改input s. recursive call 需要用原始的input s.

这个题目李特是屌炸天的。
我飞了九牛二虎之力（路子对），但是代码写的七荤八素，好长好长好长好长的。
结果正解，三四行就搞定了。真是心有不甘啊。
想法如下：
保证p1能胜利，就必须保持所有p2的move都不能赢。
同时，p1只要在可走的Move里面，有一个move可以赢就足够了。
（题目里面用一个for loop + 只要 满足条件就return true来表达 OR的意思：p1不同的路子，赢一种就行了）
p1: player1
p2: player2


---
**92. [Flip Game.java](https://github.com/shawnfan/LintCode/blob/master/Java/Flip Game.java)**很郁闷的就是条件不明，原来只需要从'++'转到'--'的情况，反过来没必要关注...搞了我半天啊

---
**93. [Fraction to Recurring Decimal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Fraction to Recurring Decimal.java)**很容易忽略的是integer的益处。

---
**94. [Game of Life.java](https://github.com/shawnfan/LintCode/blob/master/Java/Game of Life.java)**According to the Wikipedia's article: 
"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules 
(taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. 
	In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
	How would you address these problems?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Company Tags Google TinyCo
Hide Tags Array
Hide Similar Problems (M) Set Matrix Zeroes

*/

/*
	Thoughts:
	https://segmentfault.com/a/1190000003819277
	http://my.oschina.net/Tsybius2014/blog/514447
	build state machine.
	take mod of 2 at the end.
*/

public class Solution {
    public void gameOfLife(int[][] board) {
        
    }
}

---
**95. [Gas Station.java](https://github.com/shawnfan/LintCode/blob/master/Java/Gas Station.java)**There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Example
Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.

Note
The solution is guaranteed to be unique.

Challenge
O(n) time and O(1) extra space

Tags Expand 
Greedy

Thoughts:
Loop through the gas station, and track the possible starting index.
Start from i = 0 ~ gas.length, and use a second pointer move to track how far we are travelling
	calculate: remain += gas[i] - cost[i]. (remain + gas[i] - cost[i]: the remaining gas plus i's gas, can we make it to i+1 gas station?)
	if remain < 0, fail. Note: if from i ~ j can't work, even it's possible that i can make it to i+1's station, but i+1 ~ j won't work still.
		Thus, once i's station failed to get to x, set index = x + 1: we are moving on to next possible starting point.

'total':simply indicates if we can make it a circle
*/

public class Solution {
    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
    		return -1;
    	}
    	int start = 0;
    	int remain = 0;
    	int total = 0;
    	for (int i = 0; i < gas.length; i++) {
    		remain += gas[i] - cost[i];
    		if (remain < 0) {
    			remain = 0;
    			start = i + 1;
    		} 
    		total += gas[i] - cost[i];
    	}
    	if (total < 0) {
    		return -1;
    	}
    	return start;
    }
}

---
**96. [Generate Parentheses.java](https://github.com/shawnfan/LintCode/blob/master/Java/Generate Parentheses.java)**看thought.取或者不取(,  )

---
**97. [Graph Valid Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Graph Valid Tree.java)**题目类型：查找2个元素是不是在一个set里面。如果不在，false. 如果在，那就合并成一个set,共享parent.
存储的关键都是：元素相对的index上存着他的root parent.

另一个union-find， 用hashmap的：http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/

---
**98. [Gray Code.java](https://github.com/shawnfan/LintCode/blob/master/Java/Gray Code.java)**		Level: Medium

题目蛋疼，目前只接受一种结果。

BackTracking + DFS:   
   Recursive helper里每次flip一个 自己/左边/右边. Flip过后还要恢复原样.遍历所有.

曾用法（未仔细验证）：   
基本想法就是从一个点开始往一个方向走，每次flip一个bit, 碰壁的时候就回头走。


---
**99. [Group Anagrams.java](https://github.com/shawnfan/LintCode/blob/master/Java/Group Anagrams.java)**		Level: Medium

和check anagram 想法一样：转化并sort char array，用来作为key。把所有anagram 存在一起。注意结尾Collections.sort().


---
**100. [H-Index II.java](https://github.com/shawnfan/LintCode/blob/master/Java/H-Index II.java)**binary search

---
**101. [H-Index.java](https://github.com/shawnfan/LintCode/blob/master/Java/H-Index.java)**	当然，搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn

o(n)也可以，用bucket. 比较巧妙。



---
**102. [Happy Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Happy Number.java)**Write an algorithm to determine if a number is happy.

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example
19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
Tags Expand 
Hash Table Mathematics
*/

/*
	Thoughts:
	Try some examples then find out: if it's not happy number, the 'sum of square of its digits' will
	repeatedly occur. Use hashset to track existance.
*/
public class Solution {
    public boolean isHappy(int n) {
    	if (n <= 0) {
    		return false;
    	}
    	long sum = n;
    	HashSet<Long> set = new HashSet<Long>();
    	while (sum != 1) {
    		String s = String.valueOf(sum);
    		sum = 0;
    		for (char c : s.toCharArray()){
    			sum += (c-'0')*(c-'0');
    		}
    		if (set.contains(sum)) {
    			return false;	
    		} else {
    			set.add(sum);
    		}
    	}
    	return true;
    }
}





---
**103. [Hash Function.java](https://github.com/shawnfan/LintCode/blob/master/Java/Hash Function.java)**In data structure Hash, hash function is used to convert a string(or any other type) 
into an integer smaller than hash size and bigger or equal to zero. The objective of 
designing a hash function is to "hash" the key as unreasonable as possible. 
A good hash function can avoid collision as less as possible. 
A widely used hash function algorithm is using a magic number 33, 
consider any string as a 33 based big integer like follow:

hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33^1 + ascii(d)*33^0) % HASH_SIZE 

                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE

                              = 3595978 % HASH_SIZE

here HASH_SIZE is the capacity of the hash table 
(you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

Given a string as a key and the size of hash table, return the hash value of this key.



Example
For key="abcd" and size=100, return 78

Clarification
For this problem, you are not necessary to design your own hash algorithm 
or consider any collision issue, you just need to implement the algorithm as described.

Tags Expand 
Hash Table

Thinking process:
Use given hash function.
However, need to consider integer overflow. 
A simple way: save it as a long during calculation. Then return a (int).
*/

class Solution {
    /**
     * @param key: A String you should hash
     * @param HASH_SIZE: An integer
     * @return an integer
     */
    public int hashCode(char[] key, int HASH_SIZE) {
        if (key.length == 0) {
            return 0;
        }
        long hashRst = 0;
        for (int i = 0; i < key.length ; i++) {
            hashRst = hashRst * 33 + (int)(key[i]);
            hashRst = hashRst % HASH_SIZE;
        }
        return (int)hashRst;
    }
};


---
**104. [HashWithArray.java](https://github.com/shawnfan/LintCode/blob/master/Java/HashWithArray.java)**	Self Test:
	Implement HashTable with just array and integer.

	Thoughts:
	A simple approach is to % size of the array, if the key exist, move 1 slot over.

	A bug will be: when exceeds the size of array, there will be no avialable space,
	and it'll run into error.

	Inspired here :http://www.algolist.net/Data_structures/Hash_table/Simple_example
	1. create a entry class.
	2. hash the key, and put Entry into that hased index.
*/

Class Entry{
	int key;
	int value; 
	public Entry(int key, int value) {
		this.key = key;
		this.value = value;
	}

	public getKey(){
		return this.key;
	}

	public getValue() {
		return this.value;
	}
}

Class HashMap {
	int[] table;
	int SIZE = 128;
	public HashMap(){
		table = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			table[i] = null;
		}
	}

	public void put(int key, int value){
		int hash = key % SIZE;
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + 1) % SIZE;
		}
		table[key] = new Entry(key, value);
	}

	public int get(int key) {
		int hash = key % SIZE;
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + 1) % SIZE;
		}
		if (table[hash] == null) {
			return -1;
		}
		return table[hash].getValue();
	}
}









---
**105. [Heapify.java](https://github.com/shawnfan/LintCode/blob/master/Java/Heapify.java)**	只能从for(i = n/2-1 ~ 0)， 而不能从for(i = 0 ~ n/2 -1)
	这是不是因为siftdown每次只顺脚下的孩子，所以必须中间开花，向上跑的时候才能确保脚下是符合heap规则的


---
**106. [House Robber.java](https://github.com/shawnfan/LintCode/blob/master/Java/House Robber.java)**看前一个或前两个的情况，再总和考虑当下的。
思考的适合搞清楚当下的和之前的情况的关系。
滚动数组的优化，就是确定了是这类“只和前一两个位子“相关的Fn而推出的。

---
**107. [Identical Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Identical Binary Tree.java)**		Level: Easy

Divide, && 每种情况（左右一一对应)    
注意 null states



---
**108. [Implement Queue by Two Stacks.java](https://github.com/shawnfan/LintCode/blob/master/Java/Implement Queue by Two Stacks.java)**As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Example
For push(1), pop(), push(2), push(3), top(), pop(), you should return 1, 2 and 2

Challenge
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.

Thoughts:
1. Push everything into stack2: whatever comes in last, will be on top.
2. Pop and Top: return stack1's top element. 
3. Initially, when stack1 is empty, need to reverse all stack2 and put into stack: like pouring water from cup stack2 into cup stack1.
	Or:when stack1 has been top() over, pour stack2 into stack1 again: the stack2's bottom becomes stack1's top, which is correct: returning the oldest element of queue (front of queue)

Tags Expand 
LintCode Copyright Stack Queue
*/


public class Solution {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public void pourS2ToS1(){
    	while (!stack2.empty()) {
    		stack1.push(stack2.peek());
    		stack2.pop();
    	}
    }
    public Solution() {
    	stack1 = new Stack<Integer>();
    	stack2 = new Stack<Integer>();	
    }
    
    public void push(int element) {
    	stack2.push(element);
    }

    public int pop() {
    	if (stack1.empty()) {
    		pourS2ToS1();
    	}
    	return stack1.pop();
    }

    public int top() {
    	if (stack1.empty()) {
    		pourS2ToS1();
    	}
    	return stack1.peek();
    }
}


---
**109. [Implement Stack by Two Queues.java](https://github.com/shawnfan/LintCode/blob/master/Java/Implement Stack by Two Queues.java)**用一个Temp做swap

做法1:
逻辑在top()/pop()里, 每次换水，查看末尾项.

做法2:
逻辑在push里面:
1. x 放q2。
2. q1全部offer/append到q2.
3. 用一个Temp做swap q1, q2.
q1的头，就一直是最后加进去的值.

---
**110. [Implement Stack.java](https://github.com/shawnfan/LintCode/blob/master/Java/Implement Stack.java)**Data Structure: ArrayList 
return/remove ArrayList的末尾项。


---
**111. [Implement strStr().java](https://github.com/shawnfan/LintCode/blob/master/Java/Implement strStr().java)**1. border condition。 如果 haystack.length() < needle.length() 的话，必须错。但是这个可以优化省略。
2. 当S2是“”的时候，也就是能在s1的其实位置找到s2....index = 0.
3. 记得如何在s1里面找s2. 就是把遍历s1的 i , 加上遍历s2的 j。

优化：
1. s1, s2长短可以不比较。因为forloop的时候： s1.length() - s2.length() + 1，如果s2长于s1,这里自然就断了。
2. if(s1.charAT(i+j) == s2.charAT(j)). 可以省略。For loop 里面就Check到这个了。

---
**112. [Implement Trie (Prefix Tree).java](https://github.com/shawnfan/LintCode/blob/master/Java/Implement Trie (Prefix Tree).java)**如果是遇到一个一个字查询的题，可以考虑一下。
构建TrieNode的时候要注意：如何找孩子？如果是个map的话，其实就挺好走位的。
而且，每个node里面的 char 或者string有时候用处不大，
可以为空。但是有些题目，比如在结尾要return一些什么String，就可以在end string那边存一个真的String。



---
**113. [Implement Trie.java](https://github.com/shawnfan/LintCode/blob/master/Java/Implement Trie.java)**Trie用来insert word，找word, 找prefix

---
**114. [Inorder Successor in Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Inorder Successor in Binary Search Tree.java)**		Level: Medium

画inorder图，发现规律.每个node的后继node(successor)有几种情况:   
1. node.right 是个leaf到底了。那么就return.   
2. set rightNode = node.right， 但发现rightNode has a lot left children to leaf.   
3. 比如, node.right == null， 也就是node自己是leaf，要回头看山顶找Inorder traversal规则里的下一个。   
	发现：其实就是每层都把路过的curr node放在stack里，最上面的，就是当下改return的那个successor:) Done.


---
**115. [Insert Interval.java](https://github.com/shawnfan/LintCode/blob/master/Java/Insert Interval.java)**Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Have you met this question in a real interview? Yes
Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].

Tags Expand 
Basic Implementation

Thoughts:
1. Find right position to insert: find the last start position that's <= newInterval.start
2. After insertion, merge.
3. How to merge? Look at merge inerval question
*/



/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
class Solution {
    /**
     * Insert newInterval into intervals.
     * @param intervals: Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0 || newInterval == null) {
            if (newInterval != null) {
                intervals.add(newInterval);
            }
            return intervals;
        }
        //Insert
        int start = newInterval.start;
        int front = -1;
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start <= start) {
                front = i;
            }
        }
        if (front == -1) {
            intervals.add(0, newInterval);
        }
        intervals.add(front + 1, newInterval);
     
        //Merge
        Interval pre = intervals.get(0);
        Interval curr = null;
        for (int i = 1; i < intervals.size(); i++) {
            curr = intervals.get(i);
            if (pre.end >= curr.start) {
                pre.end = pre.end > curr.end ? pre.end : curr.end;
                intervals.remove(i);
                i--;
            } else {
                pre = curr;
            }
        }
    
        return intervals;
    }
}

---
**116. [Insert Node in a Binary Search Tree .java](https://github.com/shawnfan/LintCode/blob/master/Java/Insert Node in a Binary Search Tree .java)**43% Accepted
Given a binary search tree  and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

Example
Given binary search tree as follow:

     2

  /    \

1        4

         /   

       3 

after Insert node 6, the tree should be:

     2

  /    \

1        4

         /   \ 

       3        6

Challenge
Do it without recursion

Tags Expand 
Binary Search Tree LintCode Copyright

Thinking process:
Binary Search Tree:
parent must < left node
parent must > right node
use a dummy node runNode to flow around on the binary search tree, compare with target node.
Find the leaf node and add into appropriate pos.
*/

public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        }
        TreeNode runNode = root;
        TreeNode parentNode = null;
        while (runNode != null) {
            parentNode = runNode;
            if (runNode.val > node.val) {
                runNode = runNode.left;
            } else {
                runNode = runNode.right;
            }
        }//while
        
        if (parentNode != null) {
            if (parentNode.val > node.val) {
                parentNode.left = node;
            } else {
                parentNode.right = node;
            }
        }
        return root;
    }
}


---
**117. [Insertion Sort List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Insertion Sort List.java)**基本上就是正常的想法：已经有个sorted list, insert一个element进去。怎么做？
    while 里面每个元素都小于 curr, keep going
    一旦curr在某个点小了，加进去当下这个空隙。
这个题目也就是：把list里面每个元素都拿出来，scan and insert一遍！


---
**118. [Integer to English Words.java](https://github.com/shawnfan/LintCode/blob/master/Java/Integer to English Words.java)**Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
Tags: Math, String
Similar Problems: (M) Integer to Roman

Thoughts:
2^31 - 1  = 2,147,483,647
Trillion, Billion, Million, Thousand, Hundred, Ninty .... Ten, Nine ... One, Zero.

1. Break the words to up to 4 parts: new break[4] by /(1000 ^ i)
2. For each i, deal with that 3-digit number in break[i]
3. Append corresponding words for each break[i]

Special case:
zero
000 in one break[i]: skip the whole thing
*/


public class Solution {
    public String[] v1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public String[] v2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String numberToWords(int num) {
        if (num < 0) {
            return "";
        }
        if (num == 0) {
            return "Zero";
        }
        String rst = "";
        for (int i = 0; i < 4; i++) {
            int partial = num  - (num / 1000) * 1000;
            if (partial > 0) {
                rst = helper(partial, i) + " " + rst;
            }
            num /= 1000;
        }
        while (rst.charAt(rst.length() - 1) == ' ')  {
            rst = rst.substring(0, rst.length() - 1);
        }
        return rst;
    }

    public String helper(int num, int i) {
        String str = "";
        if (num >= 100) {
            int hund = num / 100;
            str = v1[hund] + " Hundred ";
            num = num % 100;
        }

        if (num < 20) {
            str += v1[num] + " ";
        } else {
            int numTens = num / 10;
            int numDigit = num % 10;
            str += v2[numTens] + " ";
            str += v1[numDigit] + " ";
        }
        
        while (str.charAt(str.length() - 1) == ' ')  {
            str = str.substring(0, str.length() - 1);
        }
        
        //depending on i:
        switch (i) {
            case 1 : 
                str += " Thousand";
                break;
            case 2 :
                str += " Million";
                break;
            case 3 :
                str += " Billion";
                break;
        }

        return str;
    }
}

---
**119. [Interleaving Positive and Negative Numbers.java](https://github.com/shawnfan/LintCode/blob/master/Java/Interleaving Positive and Negative Numbers.java)**这里主要要特别考虑，正数多还是负数多的问题。
count一下，然后举两个小栗子就看出来端倪了。
然后Two Pointer

---
**120. [Interleaving String.java](https://github.com/shawnfan/LintCode/blob/master/Java/Interleaving String.java)**Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.

Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
Challenge
O(n2) time or better

Tags Expand 
Longest Common Subsequence Dynamic Programming

Attempt2: DP[i][j]: boolean that if first S1(i) chars and first S2(j) chars can interleavign first S3(i + j)
Match one char by one char. We have 2 conditions: match s1 or s2 char, Let's do double-for-loop on s1 and s2
1. match s1: s3.charAt(i + j -1) == s1.charAt(i - 1) && DP[i - 1][j]; // makes sure DP[i-1][j] also works before adding s1[i-1] onto the match list
2. match s2: s3.charAt(i + j -1) == s2.charAt(j - 1) && DP[i][j - 1]// similar as above

Note:
Need to initiate the starting conditions with just s1, or just s2
Note2:
DP ususally start i == 1, and always use (i - 1) in the loop... this is all because we are trying to get DP[i][j], which are 1 index more than length
*/
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || (s1 == null && s2 == null) || s1.length() + s2.length() != s3.length()) {
        	return false;
        }
        boolean[][] DP = new boolean[s1.length() + 1][s2.length() + 1];
        DP[0][0] = true; // empty s1 and s2 would be a working case

        //with just s1:
        for (int i = 1; i <= s1.length(); i++) {
        	if (s3.charAt(i - 1) == s1.charAt(i - 1) && DP[i - 1][0]) {
        		DP[i][0] = true;
        	}
        }

        //with just s2:
        for (int j = 1; j <= s2.length(); j++) {
        	if (s3.charAt(j - 1) == s2.charAt(j - 1) && DP[0][j - 1]) {
        		DP[0][j] = true;
        	}
        }

        for (int i = 1; i <= s1.length(); i++) {
        	for (int j = 1; j <= s2.length(); j++) {
        		if ((s3.charAt(i + j - 1) == s1.charAt(i - 1) && DP[i - 1][j]) 
        			|| (s3.charAt(i + j - 1) == s2.charAt(j - 1) && DP[i][j - 1])) {
        			DP[i][j] = true;
        		}
        	}
        }

        return DP[s1.length()][s2.length()];
    }
}




/*

Attempt1, Incorrect: tho, magically passed 91% of lintcode, by coincidence
This solution could goes on and on with s1, and failed at certain point when j == 0 does not fit in.
s1 = "sdfjas;dfjoisdu"
s2 = "dfnakd"
s3 = "sdfjas;dfjoisdf..." // Failed at that 'f' in s3

Thoughts:
DP[mxn]: loop through S1.length and S2.length, record DP[k] = true or false.
DP[k] = (S1(0~i) + S2(0 ~ j)) is leading S3: index of (xxx) == 0.

*/
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || (s1 == null && s2 == null) || s1.length() + s2.length() != s3.length()) {
        	return false;
        }

        int i = 0; 
        int j = 0;
        String base = "";
        for (int k = 0; k < s1.length()*s2.length() - 1; k++) {
        	if (i < s1.length() || j < s2.length()) {
	        	if (i < s1.length() && s3.indexOf(base + s1.charAt(i)) == 0) {
	        		base += s1.charAt(i);
	        		i++;
	        	} else if (j < s2.length() && s3.indexOf(base + s2.charAt(j)) == 0) {
	        		base += s2.charAt(j);
	        		j++;
	        	} else {
	        		return false;
	        	}
        	}
        }
        return true;
    }
}

---
**121. [Intersection of Two Linked Lists.java](https://github.com/shawnfan/LintCode/blob/master/Java/Intersection of Two Linked Lists.java)**		Level: Easy

长短list，找重合点。
长度不同的话，切掉长的list那个的extra length。 那么起点一样后，重合点就会同时到达。



---
**122. [Interval Minimum Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Interval Minimum Number.java)**		Level: Medium

SegtmentTree, methods: Build, Query. 这题是在SegmentTreeNode里面存min.

类似的有存:max, sum, min




---
**123. [Interval Sum II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Interval Sum II.java)**		Level: Hard

SegmentTree大集合。记得几个Methods: Build, Query, Modify. 不难。只是要都记得不犯错:)


---
**124. [Interval Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Interval Sum.java)**		Level: Medium

其实是segment tree 每个node上面加个sum。   

记得Segment Tree methods: Build, Query

Note: 存在SegmentTreeNode里面的是sum.  其他题目可能是min,max ... or something else.



---
**125. [Invert Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Invert Binary Tree.java)**		Level: Easy

non-recursive: BFS with queue。 或者regular recurisve - divide and conquer.


---
**126. [Jump Game II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Jump Game II.java)**Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Tags Expand 
Greedy Array

Thanks to Yu’s Garden blog
Thinking process:
0.   Use two pointers pStart and pEnd to track the potential locations we can move to.
Consider a range from current spot to the farthest spot: try to find a max value from this range, and see if the max can reach the tail of array. 
If no max can read the tail of array, that means we need to move on. At this point, let pStart = pEnd + 1. At same time, move pEnd to the max spot we can go to. Since pEnd moves forward, we could step++
If max reach the tail of array, return the steps.
*/

public class Solution {
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int pStart = 0;
        int pEnd = 0;
        int steps = 0;
        while (pEnd < A.length - 1) {
            steps++;    //Cound step everytime when pEnd is moving to the farthest.
            int farthest = 0;
            //Find farest possible and see if reach the tail
            for (int i = pStart; i <= pEnd; i++) {
                farthest = Math.max(farthest, i + A[i]);
                if (farthest >= A.length - 1) {
                    return steps;
                }
            }
            //Re-select pointer position for start and end
            pStart = pEnd + 1;
            pEnd = farthest;
        }
        return -1;  //This is the case where no solution can be found.
    }
}


//Also DP from nineChapter:
http://www.ninechapter.com/solutions/jump-game-ii/


---
**127. [Jump Game.java](https://github.com/shawnfan/LintCode/blob/master/Java/Jump Game.java)**Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

This can be done using DP. However, greedy algorithm is fast in this particular problem. Consider both solutions.

DP
Thinking Process:
We have array A, that stores the # of steps for each index.
State: f[i] means if previous steps can reach to i. True/False
Function: f[i] = f[j] && (j + A[j] >= i)
Init: f[0] = true
Answer: f[n-1], if n is the length of A
*/

public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     **/
  //DP
  public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
	//By default, boolean[] can is all false
        boolean[] can = new boolean[A.length];
        can[0] = true;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] && (j + A[j] >= i)) {
                    can[i] = true;
                    break;
                }
            }
        }
        return can[A.length - 1];
    }
}



/*

Greedy. Ideas from Yu’s Garden
At each index, check how far we can jump, store this forest-can-jump position in variable ‘farest’. Take max of current farest and (index + A[index]), store is in farest
At each index, compare if ‘farest’ is greater than the end of array, if so, found solution, return true.
At each index, also check if ‘farest == current index’, that means the farest we can move is to current index and we cannot move forward. Then return false.
*/

public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     **/
     
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int farest = 0;
        for (int i = 0; i < A.length; i++) {
            farest = Math.max(farest, i + A[i]);
            if (farest > A.length - 1) {
                return true;
            }
            if (farest == i) {
                return false;
            }
        }
        return true;
    }
}


---
**128. [Kth Largest Element.java](https://github.com/shawnfan/LintCode/blob/master/Java/Kth Largest Element.java)**Find K-th largest element in an array.

Example
In array [9,3,2,4,8], the 3rd largest element is 4

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Note
You can swap elements in the array

Challenge
O(n) time, O(1) space

Tags Expand 
Quick Sort Sort

Thoughts:
Almost the same as the Median problem: 
the only difference is, this one is not looking for the middle point, but for the last kth element. 
Using the same quick sort code with minor modifications, and we can solve this problem.
*/


class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        return helper(nums, 0, nums.size() - 1, nums.size() - k);
    }
    
    public void swap( ArrayList<Integer>nums, int x, int y){
        int temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    }
    
    public int helper( ArrayList<Integer> nums, int start, int end, int mid) {
        int pivot = end;
        int num = nums.get(pivot);
        int low = start;
        int high = end;
        while (low < high) {
            while(low < high && nums.get(low) < num) {
                low++;
            }
            while(low < high && nums.get(high) >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);
        if (low == mid) {
            return nums.get(low);
        } else if (low < mid) {
            return helper(nums, low + 1, end, mid);
        } else {
            return helper(nums, start, low - 1, mid);
        }
    }
};



























---
**129. [Kth Smallest Element in a BST.java](https://github.com/shawnfan/LintCode/blob/master/Java/Kth Smallest Element in a BST.java)**Recursive 不难，然后稍微优化一下，确保rst.size() == k 时候，就可以return了。
Iterative 稍微难想点：先把最左边的add， pop() stack， 加上右边（如果存在）； 下一个轮回，如果又左孩子，又是一顿加。


---
**130. [Kth Smallest Sum In Two Sorted Arrays.java](https://github.com/shawnfan/LintCode/blob/master/Java/Kth Smallest Sum In Two Sorted Arrays.java)**用priority queue. 每次把最小的展开，移位。分别x+1,或者y+1。
因为当下的Min里面x,y都是最小的。所以下一个最小的不是（x+1,y）,就是（x,y+1）。当然，放在PriorityQueue里面的原因就是，很可能跟之前在queue里面的pair产生比较。
每次就poll（）一个，放新candidate进去就好了。
注意，这样的做法会用重复，比如例子（7,4）会出现两次。用一个HashSet挡一下。

学会用priorityqueue.

注意，HashSet的唯一性，用一个"x,y"的string就可以代为解决。

---
**131. [Largest Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Largest Number.java)**Given a list of non negative integers, arrange them such that they form the largest number.

Example
Given [1, 20, 23, 4, 8], the largest formed number is 8423201.

Note
 The result may be very large, so you need to return a string instead of an integer.

Tags Expand 
Sort

Thoughts:
Use a comparator with String.comareTo, then uset Arrays.sort(...)

*/

class CustomComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return (s2 + s1).compareTo(s1 + s2);
    }
}
public class Solution {
    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        if (num == null || num.length == 0) {
            return "";
        }
        String[] strs = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            strs[i] = num[i] + "";
        }
        Arrays.sort(strs, new CustomComparator());
        StringBuffer sb=  new StringBuffer();
        for (int i = 0; i < num.length; i++) {
            sb.append(strs[i]);
        }
        String rst = sb.toString();
        if (rst.charAt(0) == '0') {
            return "0";
        }
        return rst;
    }
}


















---
**132. [Largest Rectangle in Histogram.java](https://github.com/shawnfan/LintCode/blob/master/Java/Largest Rectangle in Histogram.java)**Example
Given height = [2,1,5,6,2,3],
return 10.

Tags Expand 
Array Stack

Thinking Process:
///TODO: missing thinking process for Largest Rectangle in Histogram

*/

public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }    
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int current = (i == height.length) ? -1 : height[i];
            while (!stack.empty() && current <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.empty() ? i : i - stack.peek() - 1;
                max = Math.max(max, w * h);
            }
            stack.push(i);
        }
        return max;
    }
}


---
**133. [Last Position of Target.java](https://github.com/shawnfan/LintCode/blob/master/Java/Last Position of Target.java)**
---
**134. [Length of Last Word.java](https://github.com/shawnfan/LintCode/blob/master/Java/Length of Last Word.java)**Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Example
Given s = "Hello World", return 5.

Note
A word is defined as a character sequence consists of non-space characters only.

Tags Expand 
String

Thoughts:
1. Split by space
2. return last word's length

Note: Java split: have to add '\\' in order to pass the key word. 
*/



public class Solution {
    /**
     * @param s A string
     * @return the length of last word
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }
        String[] arr = s.split("\\ ");
        String lastWord = arr[arr.length - 1];

       	return lastWord.length();
    }
}

---
**135. [Letter Combinations of a Phone Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Letter Combinations of a Phone Number.java)**Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Cellphone. Picture:http://www.lintcode.com/en/problem/letter-combinations-of-a-phone-number/

Example
Given "23"

Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

Note
Although the above answer is in lexicographical order, your answer could be in any order you want.

Tags Expand 
String Backtracking Recursion Facebook Uber
*/

/*
	Thoughts: have done this on Leetcode.
	map integer to letters
	combination of existing letters (by pressing fist number) with next number's letters.
	put combinations into queue, reuse the queue.
	finally, output into arraylist

	NON-recursive/iterative: use a queue. (Done this one Leetcode)

	This time, use recursive:
	pass along rst, list, level number, digits, 
	for (combine list with all next level's candidates, map)
	when level number == digits.length(), return the candidate into rst.
*/
public class Solution {
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        ArrayList<String[]> map = new ArrayList<String[]>();
        map.add(new String[]{});//key 0: nothing
        map.add(new String[]{});//key 1: nothing
        map.add(new String[]{"a","b","c"});
        map.add(new String[]{"d","e","f"});
        map.add(new String[]{"g","h","i"});
        map.add(new String[]{"j","k","l"});
        map.add(new String[]{"m","n","o"});
        map.add(new String[]{"p","q","r","s"});
        map.add(new String[]{"t","u","v"});
        map.add(new String[]{"w","x","y","z"});

        ArrayList<String> list = new ArrayList<String>();
        helper(rst, list, map, digits, 0);

        return rst;
    }

    public void helper(ArrayList<String> rst, ArrayList<String> list, 
    	ArrayList<String[]> map, String digits, int level){
    	//If level is finished, compress into string
    	if (level == digits.length()) {
    		StringBuffer sb = new StringBuffer();
    		for (String s : list) {
    			sb.append(s);
    		}
    		rst.add(sb.toString());
    		return;
    	}
    	//For a specific list of candidates, face the level of chars
    	int num = Integer.parseInt(digits.substring(level, level + 1));
    	String[] strs = map.get(num);

    	for (int i = 0; i < strs.length; i++) {
    		list.add(strs[i]);
    		helper(rst, list, map, digits, level + 1);
    		list.remove(list.size() - 1);
    	}
    }

}







//Iterative: 
//Use 1 queue
// and optimize a bit
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");map.put(3, "def");
        map.put(4, "ghi");map.put(5, "jkl");map.put(6, "mno");
        map.put(7, "pqrs");map.put(8,"tuv");map.put(9,"wxyz");
        
        Queue<String> queue = new LinkedList<String>();
        
        //init
        int index = 0;
        int digit = Integer.parseInt(digits.substring(index, index + 1));
        String keys = map.get(digit);
        index++;
        
        for (int i = 0; i < keys.length(); i++) {
            queue.offer(keys.substring(i,i+1));
        }
        int size = queue.size();
        
        while (index < digits.length() && !queue.isEmpty()) {
            String str = queue.poll();
            digit = Integer.parseInt(digits.substring(index, index + 1));
            keys = map.get(digit);
            for (int i = 0; i < keys.length(); i++) {
                queue.offer(str + keys.substring(i,i+1));
            }
            size--;
            if (size == 0 && index < digits.length() - 1) {
                index++;
                size = queue.size();
            }
        }//end while
        
        while (!queue.isEmpty()) {
            rst.add(queue.poll());
        }
        
        return rst;
    }
}












---
**136. [Linked List Cycle II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Linked List Cycle II.java)**

O(1)要首先break while loop when there is a slow==fast
然后，然后就有个我不懂得地方：

当head == slow.next时候， head就是cycle starting point.
也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

这个可能要写一写，装一装，证明证明才行...不是特别清楚。

---
**137. [Linked List Cycle.java](https://github.com/shawnfan/LintCode/blob/master/Java/Linked List Cycle.java)**那个时候其实slow.val = fast.val.

O(n):用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle le 

---
**138. [Longest Common Prefix.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Common Prefix.java)**Given k strings, find the longest common prefix (LCP).

Example
For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"

For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"

Tags Expand 
Enumeration Basic Implementation String LintCode Copyright

Thoughts:
1. Continuous while loop until something breaks out.
2. Str to record the longest prefix
3. for loop on each while-loop, based on strs[0]
	Check if index exist
	check if all other strs have same char at this point. If so, add it. If not, break.

Note:
Arrays: use strs.length
String: use str.length().             
Odd, right? I know ... dislike.
String seems to be supirior, it's a god damn object, and we have a method for checking string length.
For array, well, looks like it's been mistreated ... we are only reading a length property of the array object. 

Note2: 
Ask for border case: when only 1 string, longest prefix turns out it's the strs[0] itself.
*/


public class Solution {
    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
        	return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = "";
        int ind = 0;
        while (ind < strs[0].length()) {
        	char c = strs[0].charAt(ind);
        	boolean valid = false;
        	for (int i = 1; i < strs.length; i++) {
        		if (strs[i].length() > ind && strs[i].charAt(ind) == c) {
        			valid = true;
        		} else {
        			valid = false;
        			break;
        		}
        	}
        	if (valid) {
        		prefix += "" + c;
        	} else {
        		break;
        	}
        	ind++;
        }//END WHILE
        return prefix;
    }
}

---
**139. [Longest Common Subsequence.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Common Subsequence.java)**Given two strings, find the longest comment subsequence (LCS).

Your code should return the length of LCS.

Example
For "ABCD" and "EDCA", the LCS is "A" (or D or C), return 1

For "ABCD" and "EACB", the LCS is "AC", return 2

Clarification
What's the definition of Longest Common Subsequence?

    * The longest common subsequence (LCS) problem is to find the longest subsequence common to all sequences in a set of sequences (often just two). (Note that a subsequence is different from a substring, for the terms of the former need not be consecutive terms of the original sequence.) It is a classic computer science problem, the basis of file comparison programs such as diff, and has applications in bioinformatics.

    * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

Tags Expand 
LintCode Copyright Longest Common Subsequence Dynamic Programming

Thinking process:
Using DP.
check[i][j] means: the length of longest common subsequnce between A(0 ~ i) and B(0 ~ j).
Then there are two ways to reach check[i][j]:
1. A(i-1) == B(j - 1), then check[i][j] = check[i - 1][j - 1] + 1;
2. A(i-1) != B(j - 1), then pick the max between (i-1,j) ,  (i,j-1)  and (i, j )
Note: check[][] is initialized with all 0's. Index (0,0) is used as starting 0.
*/
public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int[][] check = new int[A.length()  + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    check[i][j] = check[i - 1][j - 1] + 1;
                } else {
                    check[i][j] = Math.max(check[i][j], check[i - 1][j]);
                    check[i][j] = Math.max(check[i][j], check[i][j - 1]);
                }
            }
        }
        return check[A.length()][B.length()];
    }
}



---
**140. [Longest Common Substring.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Common Substring.java)**Given two strings, find the longest common substring.

Return the length of it.

Example
Given A = "ABCD", B = "CBCE", return 2.

Note
The characters in substring should occur continuously in original string. This is different with subsequence.

Challenge
O(n x m) time and memory.

Tags Expand 
LintCode Copyright Longest Common Subsequence Dynamic Programming

Thoughts:
1. Compare all i X j.
2. Use a D[i][j] to mark the amount of common substring based on D[i - 1][j -1]. Could be 0.
3. track max length

NOTE1: create 2D array that's [N + 1][M + 1] because we want to hold D[n][M] in the 2d array
NOTE2: be carefule with init index 0's

*/


public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
    	if (A == null || B == null || A.length() == 0 || B.length() == 0) {
    		return 0;
    	}   
    	int [][] D = new int[A.length() + 1][B.length() + 1];
    	int max = 0;
    	for (int i = 0; i <= A.length(); i++) {
    		for(int j = 0; j <= B.length(); j++) {
    		    if (i == 0 || j == 0) {
    		        D[i][j] = 0;
    		    } else {
        			if (A.charAt(i - 1) == B.charAt(j - 1)) {
        				D[i][j] = D[i - 1][j - 1] + 1;
        			} else {
        				D[i][j] = 0;
        			}
        			max = Math.max(max, D[i][j]);
    		    }
    		}
    	}
    	return max;
    }
}

---
**141. [Longest Consecutive Sequence.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Consecutive Sequence.java)**Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Hide Tags Array

Thinking process:
0. This problem can be done using sorting, but time complexity of sorting is O(nlogn). This problem requires O(n).
1. Want to check if a number's left and right is consecutive to itself, but cannot do it due to the given unsorted array: think about a Hashmap.
2. HashMap(Key, Value) = (the number itself, boolean: have been counted or not). If you count a number as a consecutive, you only need to count it once.
3. How HashMap works: 
	when checking a number's consecutive, look at number--, number++, see if they are in the HashMap. If exist, means consecutive.
	If a number exist in the hashmap and its value is 'true', then we need to skip this number beacuse it has been checked.
4. Track the total number consecutives of 1 perticular number, compare it with the maxL. Save the Math.max to maxL.
5. Depending on the problem, we can store a consecutive sequence or simply just its length: maxL. This problem wants the maxL.
*/

public class Solution {
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int maxL = 1;
        HashMap<Integer, Boolean> history = new HashMap<Integer, Boolean>();
        for (int i : num) {
            history.put(i, false);
        }
        for (int i : num) {
            if (history.get(i)) {
                continue;
            }
            //check ++ side
            int temp = i;
            int total = 1;
            while (history.containsKey(temp + 1)) {
                total++;
                temp++;
                history.put(temp, true);
            }
            //check -- side
            temp = i;
            while (history.containsKey(temp - 1)) {
                total++;
                temp--;
                history.put(temp, true);
            }
            maxL = Math.max(maxL, total);
        }
        return maxL;
    }
}



/*
10.19.2015
Thougths:
1. sort
2. use a 'count' and 'max' to keep track of consecutive elements
3. one-pass

Note:
Take care of equal numbers: skip/continue those

*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        if (num.length == 1) {
            return 1;
        }
        int count = 1;
        int max = 1;
        Arrays.sort(num);
        for (int i = 1; i < num.length; i++) {
            if (num[i - 1] == num[i]) {
                   continue;
            } else if (num[i - 1] + 1 == num[i]) {
                count++;
                max = Math.max(count, max);
            } else {
                count = 1;
            }
        }
        return max;
    }
}

---
**142. [Longest Increasing Continuous subsequence II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Increasing Continuous subsequence II.java)**O(mn) runtime because each spot will be marked once visited. 
这个题目的简单版本一个array的例子：从简单题目开始想DP会简单一点。每个位置，都是从其他位置（上下左右）来的dpValue +　１.　如果啥也没有的时候，init state 其实都是1， 就一个数字，不增不减嘛。

---
**143. [Longest Increasing Continuous subsequence.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Increasing Continuous subsequence.java)**O(1)是用了两个int来存：每次到i点时，i点满足条件或不满足条件所有的longestIncreasingContinuousSubsequence.
特点：返跑一回，ans还是继续和left轮的ans作比较；求的所有情况的最大值嘛。

---
**144. [Longest Increasing Subsequence.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Increasing Subsequence.java)**每次都考虑o~i的所有情况。所以double for loop

---
**145. [Longest Palindromic Substring.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Palindromic Substring.java)**		Level: Medium

方法1: 从中间劈开. 遍历i，从n个不同的点劈开：每次劈开都看是否可以从劈开出作为palindromic的中点延伸。   
   Worst case: 整个string都是相同字符，time complexity变成： 1 + 2 +３　＋　．．．　＋n = O(n^2)

方法2: 穷举double for loop. O(n^2)



---
**146. [Longest Substring with At Most K Distinct Characters.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Substring with At Most K Distinct Characters.java)**map.size一旦超标，要把longest string最开头（marked by pointer:start）的那个char抹掉，而且要把它所有的appearance都抹掉；这样还不够，它最后一次出现以前的其他所有chars，也都要抹掉。
大清洗的原因是： 一旦某一个char要被清除，由于substring必须是连续的，所以在它之前的所有chars都要被清洗。
我去，黑帮大哥除龙头啊。
简直就是要消灭伏地魔的7个魂器。

---
**147. [Longest Substring Without Repeating Characters.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Substring Without Repeating Characters.java)**		Level: Medium

方法1：只要有non-existing char就count++. 一旦有重复char:   
   i = 新出现重复Char的位置.
   重新init HashMap, count.

这个方法每次都把map打碎重来, 是可以的，也没什么不好。就是在for里面改i，自己觉得不太顺.方法二可能顺一点。

方法2:用两个pointer, head和i.    
   head从index 0 开始。若没有重复char, 每次只有for loop的i++。每次取substring[head,i]作为最新的string.
   一旦有重复，那么意味着，从重复的老的那个index要往后加一格开始。所以head = map.get(i) +１.

注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1. 当然这是不对的，所以head要确保一直增长，不回溯。
   


---
**148. [Longest Words.java](https://github.com/shawnfan/LintCode/blob/master/Java/Longest Words.java)**Given a dictionary, find all of the longest words in the dictionary.

Example
Given

{
  "dog",
  "google",
  "facebook",
  "internationalization",
  "blabla"
}
the longest words are(is) ["internationalization"].

Given

{
  "like",
  "love",
  "hate",
  "yes"
}
the longest words are ["like", "love", "hate"].

Challenge
It's easy to solve it in two passes, can you do it in one pass?

Tags Expand 
Enumeration String LintCode Copyright

Thoughts:
Two pass: 1st, get longest length. 2nd pass, get all words.

One pass:
1. Use hashmap: <lengthOfString, ArrayList<String>>
2. keep track of the longest length

Review:
Map: put, get
ArrayList: add
We can get a value from map, and change directly on it, if that's an object (basically refer to the original object)
*/


class Solution {
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
    	if (dictionary == null || dictionary.length == 0) {
    		return null;
    	}
    	HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
    	int longestLength = 0;

    	for (int i = 0; i < dictionary.length; i++) {
    		int strLength = dictionary[i].length();
    		if (map.containsKey(strLength)) {
    			map.get(strLength).add(dictionary[i]);
    		} else {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(dictionary[i]);
    			map.put(strLength, list);
    		}
    		longestLength = strLength > longestLength ? strLength : longestLength;
    	}
    	return map.get(longestLength);
    }
};

---
**149. [Lowest Common Ancestor II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Lowest Common Ancestor II.java)**		Level: Easy

这个题有个奇葩的地方，每个node还有一个parent。 所以可以自底向上.

1. 曾经做的hashset的优化，找到的都存hashset. exist就return那个duplicate.


2. 普通做法：2 lists。
   自底向上。利用parent往root方向返回。   

注意：无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！



---
**150. [Lowest Common Ancestor of a Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Lowest Common Ancestor of a Binary Search Tree.java)**		Level: Medium

利用 BST的性质，可以直接搜到target node，而做成两个长度不一定相等的list。然后很简单找到LCA 



---
**151. [Lowest Common Ancestor.java](https://github.com/shawnfan/LintCode/blob/master/Java/Lowest Common Ancestor.java)**		Level: Easy

普通的Binary Tree，node child 自顶向下蔓延。

方法1：O(1) sapce O(h). Recursive. 循环的截点是：      
当root == null或者 A B 任何一个在findLCA底部被找到了(root== A || root == B)，那么就return 这个root.     

三种情况：   
1. A,B都找到，那么这个level的node就是其中一层的parent。其实，最先recursively return到的那个，就是最底的LCA parent.   
2. A 或者 B 找到，那就还没有公共parent,return 非null得那个。   
3. A B 都null, 那就找错了没有呗, return null


//无法找到target element, 因为不是Binary Search Tree    
//[Not Working]：O(n) space O(h) time。把两条线binary search出来。找第一个不同的parent. 代码长。 Iterative       


---
**152. [LRU Cache.java](https://github.com/shawnfan/LintCode/blob/master/Java/LRU Cache.java)**啥是LRU Cache啊？ 接下去看吧。

后来，我很天真的来了一个O(n) 的解法，结果果然时间过多。
天真解法很简单啊：一个map<key,value>存数值。一个queue<key>来存排位。
每次有更新，就把最新的放在末尾；每次超过capaticity,就把大头干掉。很简单嘛，但是跑起来太久，失败了。

于是就来了第二个做法。其实还是跟方法一是类似的。只不过用了一个特别的双向的LinkNode，有了head和tail，这样就大大加快了速度。
主要加快的就是那个‘更新排位’的过程，过去我是O(n),现在O(1)就好了。具体看下面。

巧妙点：
1. head和tail特别巧妙：除掉头和尾，和加上头和尾，就都特别快。
2. 用双向的pointer: pre和next， 当需要除掉任何一个node的时候，只要知道要除掉哪一个，
直接把node.pre和node.next耐心连起来就好了，node就自然而然的断开不要了。

李特的这个Design的题目很有意思。果然值得被作为Hard。但是一旦知道怎么解决了，就不是很特别，因为并不是难想的算法。

````
/*
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Tags: Design

*/
/*
It looks like we need to do some design, according to (http://www.cnblogs.com/yuzhangcmu/p/4113462.html). Though, the solution's concept is quite similar as attempt1.

1. The design uses HashMap, and 2-way LinkedList. Map<key, LinkNode>
2. Use two dummy node: head and tail. When add/remove nodes, we are add/remove nodes in between head and tail.
	So. head.next is our real 1st element
	andd tail.pre is our real last element

Note:
Be careful: when removing a node due to capacity issue, remember to remove both 1st node(head.next) and the corresponding entry in the map: map.remove(head.next.key)
*/
public class LRUCache {
	private class LinkNode {
		int key;
		int val;
		LinkNode pre = null;
		LinkNode next = null;
		LinkNode(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

    private int cap;
    private HashMap<Integer, LinkNode> map;
    private LinkNode head;
    private LinkNode tail;
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<Integer, LinkNode>();
        this.head = new LinkNode(-1, -1);
        this.tail = new LinkNode(-1, -1);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
    	if (map.containsKey(key)) {
    		LinkNode temp = map.get(key);
    		moveUsedToTail(temp);
    		return temp.val;
    	} else {
    		return -1;
    	}
    }
    
    public void set(int key, int value) {
    	if (map.containsKey(key)) {
    		LinkNode temp = map.get(key);
    		temp.val = value;
    		moveUsedToTail(temp);	
    	} else {
			if (map.size() >= cap) {
        		map.remove(head.next.key);
        		removeHead();
        	}
        	LinkNode newNode = new LinkNode(key, value);
	        addTail(newNode);
	        map.put(key, newNode);
    	}
    }
    
    public void moveUsedToTail(LinkNode node) {
   		removeNode(node);
    	addTail(node);
   	}

   	public void removeHead(){//O(1)
   		removeNode(head.next);
   	}
   	public void addTail(LinkNode node){//O(1)
   		tail.pre.next = node;
   		node.pre = tail.pre;
   		node.next = tail;
   		tail.pre = node;
   	}
   	public void removeNode(LinkNode node) {//O(1)
   		node.pre.next = node.next;
   		node.next.pre = node.pre;
   	}
}

````
````
/*
First Attempt: time exceeds

Thoughts:
Easy to implement: cache the least-added item. However, we are looking for the cache of 'leaset-recently-used item'.

'used' means the get() method:
whenever get, remove that key from the queue, and move that key to top.

Time Cost: O(n) on get(), set()


*/

public class LRUCache {
    private int cap;
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> queue;
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<Integer, Integer>();
        this.queue = new LinkedList<Integer>();
    }
    
    public int get(int key) {
    	if (map.containsKey(key)) {
    		moveUsedToTop(key);
    		return map.get(key);
    	} else {
    		return -1;
    	}
    }
    
    public void set(int key, int value) {
    	if (map.containsKey(key)) {
    		moveUsedToTop(key);
    		map.put(key, value);
    	} else {
			if (queue.size() >= cap) {
        		map.remove(queue.poll());
        	}
	        queue.offer(key);
	        map.put(key, value);
    	}
        
    }
    //O(n)
    public void moveUsedToTop(int key) {
    	for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i) == key) {
				queue.remove(i);
				queue.offer(key);
				break;
			}
		}
    }
}
````

---
**153. [Majority Number II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Majority Number II.java)**Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Note
There is only one majority number in the array

Example
For [1, 2, 1, 2, 1, 3, 3] return 1

Challenge
O(n) time and O(1) space

Thinking process:
Need to think the relations of 3 parts of the array:
1. Assume a > 1/3, which is the candidate were are looking for
	However, only konwing a appears more than 1/3 of the array, does not mean there is no other element appears more than 1/3, for example, aaaaabcccccc, a = 5/12, b = 6/12. The majority is b.
2. Consider another element b, which is a different element rather than a. Discuss the 2 conditions of b.
3. Consider the rest of the array is in set c, which can contain all different elements.

Discuss relations between a, b, c
Assume a > 1/3
Case1: b < 1/3 
	given: a > 1/3, means b + c < 2/3, known b < 1/3
	get: c < 1/3
	conclusion: a is the majority
Case2: b > 1/3
	given: a + b ? 2/3
	get: c < 1/3
	conclusion: return the greater element# of a or b

Implementation:
1. Have valA and valB two pointers to represent a and between
2. Check valA against the array to count duplicates, similar as in Majority Number I 
3. Check valB against .....
4. Note: at each index i, only one of valA or valB is checked. That means, we evaluate a and b individually against the section c.
5. At the end, we found 2 candidates: a and b. Now compare the # of a and b to see which is greater.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int valA = 0; 
        int valB = 0;
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (countA == 0 || nums.get(i) == valA) {
                valA = nums.get(i);
                countA++;
            } else if (countB == 0 || nums.get(i) == valB) {
                valB = nums.get(i);
                countB++;
            } else {//None of a || b matches
                countA--;
                countB--;
                if (countA == 0) {
                    countA = 1;
                    valA = nums.get(i);
                } else if (countB == 0) {
                    countB = 1;
                    valB = nums.get(i);
                }
            }
        }//For
        
        countA = 0; 
        countB = 0;
        for (int num : nums) {
            countA += num == valA ? 1 : 0;
            countB += num == valB ? 1 : 0;
        }
        return countA > countB ? valA : valB;
    }
}


---
**154. [Majority Number III.java](https://github.com/shawnfan/LintCode/blob/master/Java/Majority Number III.java)**Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array. Find it.

Note
There is only one majority number in the array.

Example
For [3,1,2,3,2,3,3,4,4,4] and k = 3, return 3

Challenge
O(n) time and O(k) extra space

Thinking process:
Very similar to Majority I, II, except we can use a HashMap to store information (value, count).
Having a HashMap we have one advantage: when checking if current value matches any previously recorded val, just do a map.containsKey(val).
When a pair in hashMap has count ==0, remove this pair.
Note, to learn how to use iterator in HashMap.
Note: when map.containsKey(currVal) == false, the code checks map.size() == k before count-- perations. This is because:
We first need to put k candidates into HashMap before we count-- from all of them. If map.size() < k, that means we still have free spot for candidate in the HashMap, so in this case we do: map.put(candidateKey, 1).
*/


public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer num : nums) {
            if (map.containsKey(num)) {//Found duplicates, count++
                map.put(num, map.get(num) + 1);
            } else {
                if (map.size() == k) {//All candidates added, do count--
                    Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<Integer, Integer> entry = iter.next();
                        if (entry.getValue() - 1 == 0) {
                            iter.remove();
                        } else {
                            map.put(entry.getKey(), entry.getValue() - 1);
                        }
                    }//While
                } else {
                    map.put(num, 1);
                }
            }
        }//For
        
        int result = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}


---
**155. [Majority Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Majority Number.java)**Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
For [1, 1, 1, 1, 2, 2, 2], return 1

Challenge
O(n) time and O(1) space

Tag: Enumeration

Thinking process:
Natural thinking process: count how many you have for 1st element, if next one is the same, count++, if next one is not the same, count- -. 
When count ==0, that means other types of element has same amount as the 1st majority number, they are even.
From this point, count the value at current position as the majority number, keep the loop rolling.
Note: this solutions works only when the given array has a valid solution.
CounterCase:[111223], with actually return 3 as the majority number. But again, this is not a valid input in this case.
*/

public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int majorNum = nums.get(0);
        int count = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (majorNum == nums.get(i)) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majorNum = nums.get(i);
                count = 1;
            }
        }
        return majorNum;
    }
}


---
**156. [Matrix Zigzag Traversal.java](https://github.com/shawnfan/LintCode/blob/master/Java/Matrix Zigzag Traversal.java)**小心走位。

---
**157. [Max Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Max Tree.java)**Stack从下到上，最大在下。维持和利用这个性质：
把所有小于curr node的，全Pop出来，最后一个小于Curr的，放在curr.left.
接下去stack里面的一定是大于curr,那就变成curr的left parent.
结尾：stack底部一定是最大的那个，也就是max tree的头。

妙啊！！！

---
**158. [Maximal Square.java](https://github.com/shawnfan/LintCode/blob/master/Java/Maximal Square.java)**		Level: Medium

DP问题

从边长为2的正方形看起，看左上角的那个点。   
如何确定是个正方形？首先看左上点是不是1，然后看右边，右下，下面的点是不是1.   

DP就是根据这个特征想出来。dp[i,j]: 从右下往左上推算，包括当前点在内的所能找到的最大边长。   
   注意dp[i,j]被右边，右下，下面三点的最短点所限制。这就是fn. 

Init：   
   把右边，下边两个边缘init一遍，存matrix在这两条边上的值，代表的意思也就是dp[i][j]在这些点上的初始值:变成为1 or 0.  


---
**159. [Maximum Depth of Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Maximum Depth of Binary Tree.java)**		Level: Easy

DFS: Divide and conquer. 维持一个最大值。


---
**160. [Maximum Product Subarray.java](https://github.com/shawnfan/LintCode/blob/master/Java/Maximum Product Subarray.java)**Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

Tags Expand 
Dynamic Programming Subarray
*/
/*
Attempt2: Use a max array and a min array. (http://www.jiuzhang.com/solutions/maximum-product-subarray/)
This is similar to my original attempt1, but saves a lot memory space.

0. Max array is always positive, Min array is always negative. Use these 2 arrays to keep track of largest positive number and smallest negative number
1. When current nums[i] > 0, use max[i - 1] * nums[i].
2. When current nums[i] < 0, user min[i - 1] * nums[i];
3. Don't for get to calculate both max and min for each i, for next iteration to use.

In either case, we will produce largest possible product.

Trick: depending on nums[i] is positive or negative, calculate differently ...
*/

public class Solution {
    public int maxProduct(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	int[] max = new int[nums.length];
    	int[] min = new int[nums.length];
    	max[0] = nums[0];
    	min[0] = nums[0];
    	int rst = max[0];
    	for (int i = 1; i < nums.length; i++) {
    		if (nums[i] > 0) {
    			max[i] = Math.max(nums[i], max[i - 1] * nums[i]);//the nums[i] could just be the best option
    			min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
    		} else {
    			max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
    			min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
    		}
    		rst = Math.max(rst, max[i]);
    	}
    	return rst;
    }
}



/*
Attempt1 thoughts:
97% correct. However, this exceeds memory, basically the DP[][] is too large.
Draw a 2D array: 
Row: Start from a number ROW[i], what contiguous value can we get:
		0	1	2	3
		-------------
		2 	3 	-2	4
0|  2  	2	6	-12	-48
1|  3  	x	x	-6	-24
2|  -2  x	x	x	-8
3|  4  	x	x	x	x

Look, according to the rules of (contiguous subarray), we can't do Row[i]xRow[i], so we have to do: Row[i]xROW[i+1]xROW[i+2]...etc
Goal: find the max in DP
1. Define DP[0][0] = nums[0];
2. DP[i][j] = DP[i][j - 1] * nums[j]
3. And we keep track of the max value

Note: j will always > i, so cases that i >= j are not necessary.
*/


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	int[][] DP = new int[nums.length][nums.length];
	    	DP[0][0] = nums[0];
	    	int max = DP[0][0];
	    	
	    	for (int i = 0; i < nums.length; i++) {
	    		for (int j = 1; j < nums.length; j++) {
	    		    if (i == j) {
	    		        DP[i][j] = nums[j];
	    		    }
	    			if (j > i) {
	    				if (DP[i][j - 1] == 0) {
	    					DP[i][j] = nums[j];
	    				} else {
	    					DP[i][j] = DP[i][j - 1] * nums[j];
	    				}
	    				max = Math.max(max, DP[i][j]);
	    			}
	    			max = Math.max(max, nums[j]);
	    		}
	    	}
    	return max;
    }
}

---
**161. [Maximum Subarray III.java](https://github.com/shawnfan/LintCode/blob/master/Java/Maximum Subarray III.java)**
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Have you met this question in a real interview? Yes
Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Note
The subarray should contain at least one number

Tags Expand 
LintCode Copyright Dynamic Programming Subarray Array
*/

/*
	NOT DONE
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(ArrayList<Integer> nums, int k) {
        // write your code
    }
}


---
**162. [MaximumSubarray.java](https://github.com/shawnfan/LintCode/blob/master/Java/MaximumSubarray.java)**然后presum[j] - presum[i- 1] 就是 (i,j)之间的和。

---
**163. [MaximumSubarrayII.java](https://github.com/shawnfan/LintCode/blob/master/Java/MaximumSubarrayII.java)**注意：右边算prefix sum， 看上去好像是什么postfix sum? 其实不是。其实都和prefix一样。
我们需要的那部分prefix sum，其实就是一段数字的总和。
所以从右边累计上来的。也是一样可以的。

---
**164. [Median of two Sorted Arrays.java](https://github.com/shawnfan/LintCode/blob/master/Java/Median of two Sorted Arrays.java)**
Median of two Sorted Arrays

There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Example
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

Given A=[1,2,3] and B=[4,5], the median is 3.

Challenge
The overall run time complexity should be O(log (m+n)).

Tags Expand 
Sorted Array Divide and Conquer Array Zenefits Uber Google

*/

/*
	Thoughts:
	Trivial: merge and find median. NOPE: have to be in log(m+n) time
	http://www.jiuzhang.com/solutions/median-of-two-sorted-arrays/

	http://fisherlei.blogspot.com/2012/12/leetcode-median-of-two-sorted-arrays.html
	
*/

class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
    }
}


---
**165. [Median.java](https://github.com/shawnfan/LintCode/blob/master/Java/Median.java)**Given a unsorted array with integers, find the median of it. 

A median is the middle number of the array after it is sorted. 

If there are even numbers in the array, return the N/2-th number after sorted.

Example
Given [4, 5, 1, 2, 3], return 3

Given [7, 9, 4, 5], return 5

Challenge
O(n) time.

Tags Expand 
LintCode Copyright Quick Sort Array


*/
/*
    Recap 12.09.2015.
    O(n) means just run through it. It's similar to Partition array: it tries to split the list into 2 parts, and find the pivot.
*/

/*
Thoughts:
Use standard quick sort, but the goal is to look for the middle point. 
1. Get middle point: remember to -1 because we are looking for position, rather than length.
2. Increase low pointer until find a point >= piviot
3. Decrease high pointer until find a point < poviot
4. Swap the low and high: this set the first value greather than pivot to the right, and first avlue less than pivot to the left.
5. after low and high pointer meets, swap low with the piviot: simply because pivot should be the break point of low and high
6. at the end, the low sould be the middle point, which is the point we are looking for. return corresponding recursive helper.

*/
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: An integer denotes the middle number of the array.
     */
    public int median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length % 2 == 0) {
            return helper(nums, 0, nums.length - 1, nums.length/2 - 1);
        } else {
            return helper(nums, 0, nums.length - 1, nums.length/2);
        }
    }
    
    public void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    
    public int helper(int[] nums, int start, int end, int mid) {
        int pivot = end;
        int num = nums[pivot];
        int low = start;
        int high = end;
        while (low < high) {
            while(low < high && nums[low] < num) {
                low++;
            }
            while(low < high && nums[high] >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);
        if (low == mid) {
            return nums[low];
        } else if (low < mid) {
            return helper(nums, low + 1, end, mid);
        } else {
            return helper(nums, start, low - 1, mid);
        }
    }
}


























---
**166. [Meeting Rooms II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Meeting Rooms II.java)**这里有尝试了一下用一个sorted Array + HashMap： 也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。


---
**167. [Meeting Rooms.java](https://github.com/shawnfan/LintCode/blob/master/Java/Meeting Rooms.java)**注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点。
这开会的dude是个超人。瞬间移动接上下一个会议。

---
**168. [Merge Intervals.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge Intervals.java)**
Given a collection of intervals, merge all overlapping intervals.

Example
Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]
Challenge
O(n log n) time and O(1) extra space.

Tags Expand 
Sort Array

Thoughts:
1. use comparator to sort list. Well, no need to create a new class. Just do it inline.
2. iterate through the list and merge (whenever there is overlap)


Review:
List: size(), get(..), remove(..)
Comparator
*/


/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: Sorted interval list.
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
        	return intervals;
        }

      //  intervals.sort(new CustomComparator());
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
        		return a.start - b.start;
        	}
        });
        //Merge
        Interval pre = intervals.get(0);
        Interval curr = null;
        for (int i = 1; i < intervals.size(); i++) {
        	curr = intervals.get(i);
        	if (pre.end >= curr.start) {
        		pre.end = pre.end > curr.end ? pre.end : curr.end;
        		intervals.remove(i);
        		i--;
        	} else {
        	    pre = curr;
        	}
        }
    
        return intervals;
    }
}

---
**169. [Merge k Sorted Arrays.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge k Sorted Arrays.java)**自己建立一个class 来存放必要信息

---
**170. [Merge k Sorted Lists.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge k Sorted Lists.java)**		Level: Hard

用Priorityqueue来排列所有list的头.

记得k lists 需要是已经sort好的。   

时间：n*O(logk)   
PriorityQueue: logk   

这个题目可以有好几个衍生：   
   比如，如果k很大，一个机器上放不下所有的k list怎么办？ 
   比如，如果Merge起来的很长，一个机器上放不下怎么办？


---
**171. [Merge Sorted Array II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge Sorted Array II.java)**
---
**172. [Merge Sorted Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge Sorted Array.java)**		Level: Easy

A够长，那么可以从A的尾部开始加新元素。     
注意，从尾部，是大数字优先的。   


---
**173. [Merge Two Sorted List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge Two Sorted List.java)**		Level: Easy

小的放前。每次比head大小。   
while过后，把没完的list一口气接上。   

一开始建一个node用来跑路, 每次都存node.next = xxx。存一个dummy。用来return dummy.next.


---
**174. [Merge Two Sorted Lists.java](https://github.com/shawnfan/LintCode/blob/master/Java/Merge Two Sorted Lists.java)**Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Tags: Linked List
Similar Problems: (H) Merge k Sorted Lists, (E) Merge Sorted Array, (M) Sort List, (M) Shortest Word Distance II

*/

/*
Thouhts:
Loop throgh both list. Make sure to check the border cases
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
        	return null;
        }
        ListNode node = new ListNode(0);
        ListNode dummy = node;
        while (l1 != null || l2 != null) {
        	if (l1 == null) {
        		node.next = l2;
        		break;
        	} else if (l2 == null) {
        		node.next = l1;
        		break;
        	} else {
        		if (l1.val < l2.val) {
        			node.next = l1;
        			l1 = l1.next;
        		} else {
        			node.next = l2;
        			l2 = l2.next;
        		}
    			node = node.next;
        	}
        }//end while
        return dummy.next;
    }
}

---
**175. [Middle of Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Middle of Linked List.java)**
不在乎slow是不是到底，因为fast肯定先到。
确保fast, fast.next不是Null就好


return slow

---
**176. [Min Stack.java](https://github.com/shawnfan/LintCode/blob/master/Java/Min Stack.java)**		Level: Easy

双Stack：一个正常stack，另一个minStack存当下level最小值. 注意维护minStack的变化



---
**177. [Minimum Height Trees.java](https://github.com/shawnfan/LintCode/blob/master/Java/Minimum Height Trees.java)**For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Show Hint 
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Credits:
Special thanks to @peisi for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags Breadth-first Search Graph
Hide Similar Problems (M) Course Schedule (M) Course Schedule II

*/


/*
    Starting from leaf with depth == 1,
        remove all leaf, and the edge
    Till the end, whatever node left, should be the root.
    
    * WHen there is only 1,2 nodes remaining. that's the rst.
    
    Put Node in  HahsMap<node, list of neighbor node>
    
    Iterative over map till map.size() <= 2
    
    border n == 2,1, just returl rst.
    edges == null, return null.
    edges.length == 1, reutrn list
*/

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> rst = new ArrayList<Integer>();
        if (n == 1) {
            rst.add(0);
            return rst;
        }else if (n == 0 || edges == null || edges.length == 0 || edges.length != n - 1) {
            return rst;
        }
        
        //populate map
        boolean[] nodes = new boolean[n];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
            nodes[i] = true;
        }
        for (int i = 0; i < edges.length;  i++) {        
            if (!map.get(edges[i][0]).contains(edges[i][1])) {
                map.get(edges[i][0]).add(edges[i][1]);
            }   
            if (!map.get(edges[i][1]).contains(edges[i][0])) {
                map.get(edges[i][1]).add(edges[i][0]);
            }  
        } 
        
        //Remove list with leng == 1
        Queue<Integer> queue = new LinkedList<Integer>();
        while (n > 2) {
            for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
                if (entry.getValue().size() == 1) {
                    queue.offer(entry.getKey());
                }
            }
            while (!queue.isEmpty()) {
                n--;
                Integer key = queue.poll();
                nodes[key] = false;
                int from = map.get(key).get(0);
                map.get(from).remove(key);
                map.get(key).remove(0);
                
            }
        }
        
        //Put remaining into rst
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i]) {
                rst.add(i);
            }

        }
        
        return rst;
    }
}

---
**178. [Minimum Path Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Minimum Path Sum.java)**Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note
You can only move either down or right at any point in time.

Example
Tags Expand 
Dynamic Programming

Thinking process:
1. Check null, lenght == 0
2. Min Sum = sum of array. Initialization is a bit different, for example: each row element is added up from previous elemenet. (Not simple value assign from given grid)
	- Assign (0,0) to grid[0][0]
	- Row 1st row and 1st col, add up values 
3. f(x,y) = sum of path value. f(x,y) = Math.Min(f(x-1,y), f(x, y-1))
4. return f(r-1)(c-1)

*/


public class Solution {
    /**
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] matrix = new int[row][col];
        matrix[0][0] = grid[0][0];
        //Add up for 1st row && 1st col
        for (int i = 1; i < row; i++) {
            matrix[i][0] = matrix[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            matrix[0][j] = matrix[0][j - 1] + grid[0][j];
        }
        //Evaluate
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - 1])
                                + grid[i][j];
            }
        }
        return matrix[row - 1][col - 1];
        
    }
}



---
**179. [Minimum Size Subarray Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Minimum Size Subarray Sum.java)**一个做base, 每次动一格：i.
一个做前锋，加到满足条件为止。
Note: 当sum >= s 条件在while里面满足时，end是多一个index的。所以result里面要处理好边缘情况：(end-1) 才是真的末尾位置，然后计算和开头的间隙：
（end - 1） - start + 1;

---
**180. [Minimum Subarray.java](https://github.com/shawnfan/LintCode/blob/master/Java/Minimum Subarray.java)**Given an array of integers, find the subarray with smallest sum.

Return the sum of the subarray.

Example
For [1, -1, -2, 1], return -3

Note
The subarray should contain at least one integer.

Tags Expand 
Greedy LintCode Copyright Subarray Array

Thoughts:
Note: sub-array has order. It's not sub-set
1. On each index: decide to add with nums.get(i), to use the new lowest value nums.get(i). That means:
	If the new value is negative (it has decresing impact on sum) and the sum is larger than new value, just use the new value.
	In another case, if sum has been nagative, so sum + new value will be even smaller, then use sum.
2. Every time compare the currMin with the overall minimum value, call it minRst.	

Note: remember to pre-set init value for curMin, minRst. 
*/


public class Solution {
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(ArrayList<Integer> nums) {
		if (nums == null || nums.size() == 0) {
			return 0;
		}	
		int curMin = nums.get(0);
		int minRst = nums.get(0);
		for (int i = 1; i < nums.size(); i++) {
			curMin = Math.min(nums.get(i), curMin + nums.get(i));
			minRst = Math.min(curMin, minRst);
		}
		return minRst;
    }
}

---
**181. [Minimum Window Substring.java](https://github.com/shawnfan/LintCode/blob/master/Java/Minimum Window Substring.java)**Given a string source and a string target, find the minimum window in source which will contain all the characters in target.

Example
source = "ADOBECODEBANC" target = "ABC" Minimum window is "BANC".

Note
If there is no such window in source that covers all characters in target, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.

Challenge
Can you do it in time complexity O(n) ?

Clarification
Should the characters in minimum window has the same order in target?

    - Not necessary.

Tags Expand 
Hash Table

Thoughts:
The idea was from jiuzhang.com.
1. count target Characters: store each Character with HashMap:tCounter<Character, # appearance>
2. Test against the source string. Here create another HashMap to keep records of the window:minWindowCounter
3. For any char appears in both target and source, count++ in the minWindowCounter.
4. As long as the number of a specific Character source.charAt(i) in minWindowCounter is less than that in tCounter,
	use a count++ to keep the record.
5. Once count == target.length(), that means there is a candidate macthcing. Now we get into next level and look for the
minimum window. Note, this condition only meets once, and after found the solution, we can return the result, minWindow.
Note, at this point, we confirm target exist in source. Now we just test against target and find minimum window.
6. Now use a leftBound = 0, and loop through the source, as long as leftBound<s.length(): A. If tCounter HashMap doesn't have current Character, leftBound++ and continue.
 B. If minWindowCounter.get(c) is greater than tCounter.get(c), that means there is at least one other c in right side of the 
 srouce string and can also be used as head of the result string (with shorter length), in this case leftBount++, countinue.
7. After the abobe 6.A, 6.B check, break out of the loop and cut out a minWindow and return.
*/
import java.util.*;

public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
    	if (source == null || source.length() == 0) {
    		return source;
    	}
    	if (target == null || target.length() == 0) {
    		return "";
    	}
    	//Count Characters in target
    	HashMap<Character, Integer> tCounter = new HashMap<Character, Integer>();
    	for (int i = 0; i < target.length(); i++) {
    		Character c = target.charAt(i);
    		if (!tCounter.containsKey(c)) {
    			tCounter.put(c, 1);
    		} else {
    			tCounter.put(c, tCounter.get(c) + 1);
    		}
    	}

		HashMap<Character, Integer> minWindowCounter = new HashMap<Character, Integer>();
		int count = 0;
		String rst = "";
		for (int i = 0; i < source.length(); i++) {
			Character c = source.charAt(i);
			if (!tCounter.containsKey(c)) {
				continue;
			}

			if (minWindowCounter.containsKey(c)) {
				minWindowCounter.put(c, minWindowCounter.get(c) + 1);
			} else {
				minWindowCounter.put(c, 1);
			}
					
			if (minWindowCounter.get(c) <= tCounter.get(c)) {
				count++;
			}

			//Once the target exists in soruce: count  = target.length(), find the result
			if (count == target.length()) {
				int leftBound = 0;
				while (leftBound < source.length()) {
					Character cs = source.charAt(leftBound);
					if (!minWindowCounter.containsKey(cs)) {//Not part of window
						leftBound++;
						continue;
					}
					if (minWindowCounter.get(cs) > tCounter.get(cs)) {//Can find shorter window
						minWindowCounter.put(cs, minWindowCounter.get(cs) - 1);
						leftBound++;
						continue;
					}
					break;
				}
				rst = source.substring(leftBound, i + 1);
				return rst;
			}
		}
		return rst;
    }

    public static void main(String[] args) {
    	Solution test = new Solution();
    	String rst = test.minWindow("abcd", "ac");
    	System.out.println("resutl is : " + rst);
    }
}

---
**182. [MinimumDepthOfBinaryTree.java](https://github.com/shawnfan/LintCode/blob/master/Java/MinimumDepthOfBinaryTree.java)**		Level: Easy

Divide and Conquery一个最小值. 注意处理Leaf的null, 用Integer.MAX_VALUE代替，这样可以避免错误counting.


---
**183. [Missing Ranges.java](https://github.com/shawnfan/LintCode/blob/master/Java/Missing Ranges.java)**自己做的时候，想的太复杂，做起了binarysearch,企图节省时间。
下次要算清楚，是否有意义。
binarySearch的确logn,但是在lower 和upper之间的数字，很可能还是O(n).
因此一开始就for一遍也是O(n), 而code会相对来说简单许多。

想法：
两个pointer， 每次计较prev和curr之间的部分。
然后prev = curr，向前移动一格。


---
**184. [Multiply Strings.java](https://github.com/shawnfan/LintCode/blob/master/Java/Multiply Strings.java)**
但是做起来有很多坑。适合面试黑。

1. 数字‘123’， 在数组里面， index == 0 是 ‘1’。 但是我们平时习惯从最小位数开始乘积，就是末尾的'3'开始。
	所以！翻转两个数字先！我去。这个是个大坑。

2. 乘积product，和移动Carrier都很普通。

3. ！！最后不能忘了再翻转。

4. 最后一个看坑。要是乘积是0，就返回‘0’。 但是这个其实可以在开头catch到没必要做到结尾catch。

用到几个StringBuffer的好东西:
reverse（）；
sb.deleteCharAt(i)

//找数字，或者26个字母，都可以：
s.charAt(i) - '0'; //数字
s.charAt(i) - 'a'; //字母


---
**185. [Next Permutation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Next Permutation.java)**Given a list of integers, which denote a permutation.

Find the next permutation in ascending order.

Have you met this question in a real interview? Yes
Example
For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]

Note
The list may contains duplicate integers.

Tags Expand 
LintCode Copyright Permutation

Thoughts:
Not much info are given. Need to ask. It looks like:
We are dong permutation on the given numbers, and find out what's next permutation array based on given order.

Ascending order: Permutations that permutation(i) < permutation(i + 1)

Goal:
To find the next smallest permutation.
1. Find the last increasing index (a peek before decresing): k
2. Find the first bigger permutation: Well, it turns out this first bigger index is always on right side of k.
	Note: we are trying to get the least significant change on the given permuation.
	Next Step: reverse (k+1, end). This is because: before the change, right side of K will be the largest possible combination. After swapping K, we need the right side to be the smallest combination. (Well, this is my understanding....Still a bit confused on why we take these steps in this problem)
*/


public class Solution {
    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    //Revers the given part of a int[]
    public int[] reverse(int start, int end, int[] nums) {
    	for (int i = start, j = end; i < j; i++,j--) {
    		int temp = nums[i];
    		nums[i] = nums[j];
    		nums[j] = temp;
    	}
    	return nums;
    }
    
    public int[] nextPermutation(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return nums;
    	}
    	//Find last increasing point before decreasing. nums[k] < nums[k+1]
    	int k = -1;
    	for (int i = nums.length - 2; i >= 0; i--) {
    		if (nums[i] < nums[i + 1]) {
    			k = i;
    			break;
    		}
    	}
    	if (k == -1) {
    		return reverse(0, nums.length - 1, nums);
    	}
    	//Find first bigger point, from right to left
    	int bigIndex = -1;
    	for (int i = nums.length - 1; i >= 0; i--) {
    		if (nums[i] > nums[k]) {
    			bigIndex = i;
    			break;
    		}
    	}
    	//1. Swap bigger index with k; 2. Reverse the right side of k. [Try to make the smallest next permutation]
    	int temp = nums[k];
    	nums[k] = nums[bigIndex];
    	nums[bigIndex] = temp;

    	return reverse(k + 1, nums.length - 1, nums);
    }



}







---
**186. [Nim Game.java](https://github.com/shawnfan/LintCode/blob/master/Java/Nim Game.java)**写一些，发现n=4,5,6,7,8...etc之后的情况有规律性。
最终很简单n%4!=0就可以了

---
**187. [NQueens.java](https://github.com/shawnfan/LintCode/blob/master/Java/NQueens.java)**index就是col number
值就是row number.

validate n queue的时候 target row#
1. array 里面不能有 target row#
2. diagnal. 记得公式：
 row1 - row2 == col1 - col2. Diagnal elelment.fail
 row1 - row2 == -(col1 - col2). Diagnal element. fail


---
**188. [NQueensII.java](https://github.com/shawnfan/LintCode/blob/master/Java/NQueensII.java)**直接add 一个什么乱起八糟的东西进rst都可以。
然后最后要的是 rst.size() = # of solutons

---
**189. [Nth to Last Node in List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Nth to Last Node in List.java)**然后head开始跑。

node 到底，而head ~ node刚好是 n 距离。所以head就是要找的last nth

---
**190. [Number of Airplane in the sky.java](https://github.com/shawnfan/LintCode/blob/master/Java/Number of Airplane in the sky.java)**http://www.lintcode.com/en/problem/number-of-airplanes-in-the-sky/
Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

Example
For interval list [[1,10],[2,3],[5,8],[4,7]], return 3

Note
If landing and flying happens at the same time, we consider landing should happen at first.

Tags Expand 
LintCode Copyright Array Interval
*/


/*
Thoughts: same as the number of meeting.
Use a Point class {time, flag} and mark all time spot, and use a min-heap(PriorityQueue) to sort it.

Note: LintCode forces me to put '10' in PriorityQueue constructor?
*/
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
	class Point {
		int x;
		int flag;
		public Point(int x, int flag) {
			this.x = x;
			this.flag = flag;
		}
	}
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) { 
    	if (airplanes == null || airplanes.size() == 0) {
    		return 0;
    	}
    	PriorityQueue<Point> queue = new PriorityQueue<Point>(10,
    	    new Comparator<Point>(){
    	    public int compare(Point a, Point b) {
    	        return a.x - b.x;
    	    }
    	});
    	for (Interval interval : airplanes) {
    		queue.offer(new Point(interval.start, 1));
    		queue.offer(new Point(interval.end, -1));
    	}
    	int count = 0;
    	int max = 0;
    	while (!queue.isEmpty()) {
    		Point p = queue.poll();
    		count+= p.flag;
    		while (!queue.isEmpty() && queue.peek().x == p.x) {
    			p = queue.poll();
    			count += p.flag;
    		}
    		max = Math.max(count, max);
    	}
    	return max;
    }
}

---
**191. [Number of Islands II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Number of Islands II.java)**Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). Originally, the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. Return how many island are there in the matrix after each operator.

Have you met this question in a real interview? Yes
Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

Note
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Tags Expand 
Union Find
*/

/*
Thoughts:
Each pos(x,y) turns that sea spot into a island spot.
Image each isleand spot is a node in the graph, and each island(many island spots) has a root parent.
In for loop, try to add operators into the matrix one after another.
	Every time when adding a new island spot, check its sourandings and see if there are islands existed.
	If souranding island was land:
		To check if the surrouding spot are on common island (use find and union). 
		Since the operator spot was sea, the it's root parent is itself. Then, souranding spot has different island root, 
		they will surely have differet root parent, but they will do after they connect, so we do count--.

On the otherhand, if surrounding was just sea, then count++ is natural

Note:
1. Know how to write up simple union find class
2. Convert 2D array into 1D
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
	class UnionFind {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		public UnionFind(int length) {
			for (int i = 0; i < length; i++) {
				map.put(i,i);
			}
		}
		public int find(int target) {
			int parent = map.get(target);
			while (parent != map.get(parent)) {
				parent = map.get(parent);
			}
			return parent;
		}

		public void union(int x, int y) {
			int findX = find(x);
			int findY = find(y);
			if (findX != findY) {
				map.put(findX, findY);
			}
		}
	}
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
    	List<Integer> rst = new ArrayList<Integer>();
    	if (operators == null || operators.length == 0) {
    		return rst;
    	}
    	int count = 0;
    	int[] island = new int[m*n];
    	UnionFind uf = new UnionFind(m*n);
    	int[] xs = {-1, 1,  0, 0};
    	int[] ys = {0,  0, -1, 1};
    	for (int i = 0; i < operators.length; i++) {
    		int x = operators[i].x;
    		int y = operators[i].y;
    		int pos = x * m + y;
    		count++;
    		if (island[pos] != 1) {
    			island[pos] = 1;
	    		for (int j = 0; j < 4; j++) {
	    			int nx = x + xs[j];
	    			int ny = y + ys[j];
	    			int newPos = nx * m + ny;
	    			if (nx >= 0 && nx < n && ny >= 0 && ny < m && island[newPos] == 1) {//when new position is land
	    				int findA = uf.find(pos);
	    				int findB = uf.find(newPos);
	    				if (findA != findB) {
	    					count--;
	    					uf.union(pos, newPos);
	    				}
	    			}
	    		}
    		}
    		rst.add(count);
    	}

    	return rst;
    }
}

---
**192. [Number of Islands.java](https://github.com/shawnfan/LintCode/blob/master/Java/Number of Islands.java)**		Level: Medium

方法1: 两个for loop brutle force。 DFS把每个跟1相关的都Mark一遍.生成一个island.

方法2: （暂时没有写union-find的解）
可以用union-find， 就像Number of island II 一样。
只不过这个不Return list, 而只是# of islands


---
**193. [Number Triangles.java](https://github.com/shawnfan/LintCode/blob/master/Java/Number Triangles.java)**Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Note
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Example
For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Tags Expand 
Dynamic Programming

Thinking process:
1. Bottom-up
	- Start from the bottom row, get all values of this row. Note: in triangle, height = cols at each row. So row X has X numbers.
	- Start from (n - 1)th row and run up: calculate min from lower level + current node value.
	- Depending what is wanted, here we use a 2D int arraya and return the min sum.
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    //Bottom - up
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[n - 1][i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                sum[i][j] = Math.min(sum[i + 1][j], sum[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return sum[0][0];
    }
}

/*
2. Memorize Search
	- Go through all nodes and initialize with Integer.MAX_VALUE;
	- Search from top: thislevel-current = Math.min(nextlevel-current, nextlevel-next) + thislevel-current
	- During the Search Helper, when a node has been set previously, just return this value because this min value has been pre-calculated.
		If row is >= triangle.size(), return 0.
	- This method can actually calculate the min sum from bottom to any point in the triangle.
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
       
    //Version 2 : Memorize Search
    private int n;
    private ArrayList<ArrayList<Integer>> triangle;
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        this.n = triangle.size();
        this.triangle = triangle;
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                sum[i][j] = Integer.MAX_VALUE;
            }
        }
        return searchHelper(0, 0, sum);
    }
    
    public int searchHelper(int r, int c, int[][] sum) {
        if (r >= this.n) {
            return 0;
        }
        if (sum[r][c] != Integer.MAX_VALUE) {
            return sum[r][c];
        }
        sum[r][c] = Math.min(searchHelper(r + 1, c, sum), searchHelper(r + 1, c + 1, sum)) + this.triangle.get(r).get(c);
        return sum[r][c];
    }
}




---
**194. [O(1) Check Power of 2.java](https://github.com/shawnfan/LintCode/blob/master/Java/O(1) Check Power of 2.java)**Using O(1) time to check whether an integer n is a power of 2.
Example
For n=4, return true

For n=5, return false

Challenge
O(1) time

Tags Expand 
Binary

Thinking process:
Any integer that's power of 2, follows one pattern. They are all: 1000000000....000 format.
so (n - 1) becomes: 01111111111...111. 
If bit-and them togeter, it will be 0.

*/

class Solution {
    /*
     * @param n: An integer
     * @return: True or false
     */
    public boolean checkPowerOf2(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
};



---
**195. [One Edit Distance.java](https://github.com/shawnfan/LintCode/blob/master/Java/One Edit Distance.java)**换完之后，理论上换成的String 就应该全等。
所以只要if statement进去以后，就结束了，总要出一个叫结局。

---
**196. [Paint Fence.java](https://github.com/shawnfan/LintCode/blob/master/Java/Paint Fence.java)**设定T（n）的做法，最后题目化简以后就跟Fibonacci number一样一样的。详细分析如下。
做完，还是觉得如有神。本来是个Easy题，想不到，就是搞不出。

12.13.2015再看了一下：
因为最多2个fence 颜色相同。
假设i是和 i-1不同，那么结果就是 (k-1)*dp[i - 1]
假设i是何 i-1相同，那么根据条件，i-1和i-2肯定不同。那么所有的结果就是(k-1)*dp[i-2]
加在一起就有了。

---
**197. [Palindrome Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Palindrome Linked List.java)**linkedlist不能reverse iterating， 那么就reverse the list, 从中间开花作比较。

---
**198. [Palindrome Partitioning II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Palindrome Partitioning II.java)**看上去，在检查i,j的时候，中间按的（i+1, j-1）怎么可能先知道？ 其实不然..在j慢慢长大的时候，所有的0~j的substring都检查过。所以isPal[i+1][j-1]一定是已经知道结果的。

okay.那么假如以上任意一种情况成立，也就是说isPal[i][j] == true。那就要判断，切到第一层循环参数j的末尾点时，有多少种切法？
想法很顺：我们naturally会想到，把i之前的cut加上i~j之间发生的不就好了。
反正现在j不变，现在就看吧i定在哪里，cut[i - 1]是否更小/最小； 再在cut[i-1]基础上+1就完了。
    当然，如果i==0, 而 i~j又是isPal,那没啥好谈的，不必切，0刀。

最终，刷到cut[s.length() - 1] 也就是最后一点。 return的理所应当。

---
**199. [Palindrome Partitioning.java](https://github.com/shawnfan/LintCode/blob/master/Java/Palindrome Partitioning.java)**在遍历str的时候，考虑从每个curr spot 到 str 结尾，是能有多少种palindorme? 那就从curr spot当个字符开始算，开始back tracing.
如果所选不是palindrome， 那move on.
若所选的确是palindrome,　加到path里面，DFS去下个level，等遍历到了结尾，这就产生了一种分割成palindrome的串。
每次DFS结尾，要把这一层加的所选palindrome删掉，backtracking嘛。

---
**200. [Palindrome Permutation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Palindrome Permutation.java)**
---
**201. [Partition Array by Odd and Even.java](https://github.com/shawnfan/LintCode/blob/master/Java/Partition Array by Odd and Even.java)**Partition an integers array into odd number first and even number second.

Example
Given [1, 2, 3, 4], return [1, 3, 2, 4]

Challenge
Do it in-place.

Tags Expand 
Two Pointers Array

Thougths:
Use two pointers: nextOddPt, firstEvenPt
1. Whenever nextOddPt > firstEvenPt, swapt them
2. Incrase nextOddPt in a for loop
Note:
After each swap, have to start checking again from beginning-switching point, which will be firstEvenPt. Need to set i = firstEvenPt.
However, since for loop will do i++, we need to set i = firstEvenPt - 1;
And firstEvenPt only needs to be update once so use -1 to check if it's set.
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
    	if (nums == null || nums.length == 0){
    		return;
    	}
    	int nextOddPt = -1;
    	int firstEvenPt = -1;
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] % 2 == 1) {
    			nextOddPt = i;
    		} else {
    			if (firstEvenPt == -1) {
    				firstEvenPt = i;
    			}
    		}
    		if (nextOddPt > firstEvenPt && firstEvenPt != -1) {
    			int temp = nums[nextOddPt];
    			nums[nextOddPt] = nums[firstEvenPt];
    			nums[firstEvenPt] = temp;
    			i = firstEvenPt - 1;
    			firstEvenPt = -1;
    		}
    	}
    }
}

---
**202. [Partition Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Partition Array.java)**从array两边开始缩进。while loop到遍历完。非常直白的implement。
注意low/high,或者叫start/end不要越边界
O(n)

Quick sort的基础。

---
**203. [Partition List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Partition List.java)**
那就最普通的，建造两个list

把满足条件（<x, >=x）的数字分别放到两个list里面

记得用dummyNode track head.
最终pre.next = post链接起来。

---
**204. [Pascal's Triangle II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Pascal's Triangle II.java)**		Level: Easy

简单处理array list.


---
**205. [Peeking Iterator.java](https://github.com/shawnfan/LintCode/blob/master/Java/Peeking Iterator.java)**
回到原题，其实不难。找一个cache来存next()的值，然后每次next()里面维护这个cache就好。


---
**206. [Perfect Squares.java](https://github.com/shawnfan/LintCode/blob/master/Java/Perfect Squares.java)**
１.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
	然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
	看我把12拆分的那个example. 那很形象的就是BFS了。
	面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。

---
**207. [Permutation Index.java](https://github.com/shawnfan/LintCode/blob/master/Java/Permutation Index.java)**最后分析出来：
每个数位的数字，都是基于这个数字之前越过的战壕...好吧，意思是，跳过了多少种可能。
对，就用4，2，1举例。网上大家都用这个例子。不行，我要换一个。

换【6，5，2】吧。我们找6，5，2是permudation里面的第几个。
正常排序，也就是permutation的第一个，应该是【2，5，6】
如果要从首位，2，变成6，要跨过多少条尸体呢？
很简单，高中就学过，重点来了：小于6的数字有多少个呢？（2，5）.然后每个数字变成head，都有各自的变化可能，而每个数字打头，都有(n-1)!种可能。明显了吧，把每个（n-1）!加起来。　注意（ｎ－１）意思就是出去开头的数字（２、５），后面有多少个，有序排列一下有多少情况，不就是（ｎ－１）！嘛。
	这一步，公式推出来就是很简单的 (有几个小于6的数字呀) ×（出去ｈｅａｄ剩下有多少个数字）！

以上	种种，都是为了把６推上皇位，而牺牲的条数。

那么把６推上去以后，还有接下去的呢。

接下去要看５，２.
６确定，后面ｐｅｒｍｕｄａｔｉｏｎ可变的情况有可能是【６，５，２】，那还可能是【６，２，５】呢。

方法一样了。
看ｇｉｖｅｎ　数组的第二位５，算它接下去：
１.　有几个数字小于５呢？
２.　除去５，还有几个数字可以　ｆａｃｔｏｒｉａｌ呢？
３. 一样的。第一步就结果乘以第二步。

接下去要看最后一个元素2了。
一样的想法。


6,5,2全看过了以后咋办。
加起来呗。
加起来，就是【6，5，2】上位，所踏过的所有小命啊！

我这解释太生动了。因为耗费了好长时间思考...

---
**208. [Permutations II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Permutations II.java)**一个办法就是给一个visited queue。 和queue在所有的地方一同populate. 然后visited里面存得时visited indexes

---
**209. [Permutations.java](https://github.com/shawnfan/LintCode/blob/master/Java/Permutations.java)**Iterative: 用个queue，每次poll()出来的list, 把在nums里面能加的挨个加一遍。

---
**210. [Plus One.java](https://github.com/shawnfan/LintCode/blob/master/Java/Plus One.java)**Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.


Example
Given [1,2,3] which represents 123, return [1,2,4].

Given [9,9,9] which represents 999, return [1,0,0,0].

Tags Expand 
Array

*/

public class Solution {
    public int[] plusOne(int[] digits) {
        if(digits.length==0) return digits;
        
        digits[digits.length-1] += 1;
        //Check index digit.length-1 to 1
        for(int i = digits.length-1; i>0; i--){
            if(digits[i] == 10){
                digits[i]=0;
                digits[i-1]+=1;
            }
            else return digits;
        }
        
        //Check index 0. If ==0, set it to 0 and carry over 1
        if(digits[0]==10){
            int[] output = new int[digits.length+1];
            output[0] = 1;
            output[1] = 0;
            for(int i=2; i<output.length-1; i++){
                output[i]=digits[i-1];
            }
            return output;
        }
        else return digits;
    }
}

/* Trivial solution
   create a secondary method func(int index, int[]digits).
   add check index from digits.length-1 to 0: digits[index]+1==10? 0 : digits[index]+1;
   if add up to 10, push into another level; if not ,return digits.
   if index==0, check if add up to 10. If ==10, create a new array and put 1 infront. else return digits.

*/


/*

Thoughts: Old soluton .will fail LeetCode
It looks I should convert array to int, then add, and then convert back to array.
1. Convert to string: Arrays.toString(xxx);
2. Integer.parseInt(str)
3. add
4. split to int array


Note:
Int may not hold the rst since it could exceed 32 bits, so use Long.

But ... What if long does not work neither?
*/

public class Solution {
    /**
     * @param digits a number represented as an array of digits
     * @return the result
     */
    public int[] plusOne(int[] digits) {
    	if (digits == null || digits.length == 0) {
    		return null;
    	}
    	
    	String str = "";
    	for (int i = 0; i < digits.length; i++) {
    		str += digits[i];
    	}
    	long digit = Long.parseLong(str);
    	digit += 1;
    	str = digit + "";
    	int[] rst = new int[str.length()];
    	for (int i = 0; i < str.length(); i++) {
    		rst[i] = Character.getNumericValue(str.charAt(i));
    	}
    	return rst;
    }
}



---
**211. [Pow(x,n).java](https://github.com/shawnfan/LintCode/blob/master/Java/Pow(x,n).java)**n的正负。
n == 0的情况。

---
**212. [Power of Three.java](https://github.com/shawnfan/LintCode/blob/master/Java/Power of Three.java)**		Level: Easy

Power of 3:  3 ^ x == n ?

做出发. 查%.


---
**213. [Product of Array Exclude Itself.java](https://github.com/shawnfan/LintCode/blob/master/Java/Product of Array Exclude Itself.java)**Given an integers array A.

Define B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1], calculate B WITHOUT divide operation.

Example
For A = [1, 2, 3], return [6, 3, 2].

Tags Expand 
Forward-Backward Traversal LintCode Copyright

Thought:
Trivial way would be first calculate the zigma(A[0]* ... A[n-1]) then divide by B[i]. However, not allowed in this question.

The other way: do for loop again and again? that will be n^2 time. 

*/



public class Solution {
    /**
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
    	if (A == null || A.size() == 0) {
    		return null;
    	}
    	ArrayList<Long> rst = new ArrayList<Long>();
    	for (int i = 0; i < A.size(); i++) {
    		long num = 1;
    		for (int j = 0; j < A.size(); j++) {
    			if (j != i) {
    				num *= A.get(j);
    			}
    		}
    		rst.add(num);
    	}
    	return rst;
    }
}



---
**214. [QuickSort.java](https://github.com/shawnfan/LintCode/blob/master/Java/QuickSort.java)**
首先partition. 返还一个partition的那个中间点的位置。
然后劈开两半。
前后各自 quick sort, recursively

注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.


但是： 在partition array那个题目里面，第二个 nums[end] >= pivot, 是要去加上这个 ‘=’的

---
**215. [Recover Rotated Sorted Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Recover Rotated Sorted Array.java)**Rotate三步：
rotate前半
rotate后半
rotate全部

注意先找到断点。

---
**216. [Rehashing.java](https://github.com/shawnfan/LintCode/blob/master/Java/Rehashing.java)**The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

size=3, capacity=4

[null, 21, 14, null]
       ↓    ↓
       9   null
       ↓
      null
The hash function is:

int hashcode(int key, int capacity) {
    return key % capacity;
}
here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

rehashing this hash table, double the capacity, you will get:

size=3, capacity=8

index:   0    1    2    3     4    5    6   7
hash : [null, 9, null, null, null, 21, 14, null]
Given the original hash table, return the new hash table after rehashing .

Example
Given [null, 21->9->null, 14->null, null],

return [null, 9->null, null, null, null, 21->null, 14->null, null]

Note
For negative integer in hash table, the position can be calculated as follow:

C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
Python: you can directly use -1 % 3, you will get 2 automatically.
Tags Expand 
LintCode Copyright Hash Table

Thoughts:
1. Loop through the hashtable[] and find longest, calcualte new capacity
2. rehash

*/

/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 public class Solution {
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */    
    public ListNode[] rehashing(ListNode[] hashTable) {
    	if (hashTable == null || hashTable.length == 0) {
    		return hashTable;
    	}
    	//Find longest size
    	/*
    	int longest = 0;
    	for (int i = 0; i < hashTable.length; i++) {
    		ListNode node = hashTable[i];
    		int count = 0;
    		while (node != null) {
    			count++;
    			node = node.next;
    		}
    		longest = Math.max(longest, count);
    	}*/
    	//Calculate new capacity
    	//Just to clarify, this problem asks to double the hashtable size, rather than 'longest' times longer.
    	int capacity = hashTable.length * 2;
    	if (capacity == hashTable.length) {
    		return hashTable;
    	}
    	
    	ListNode[] rst = new ListNode[capacity];
    	for (int i = 0; i < hashTable.length; i++) {
    		ListNode node = hashTable[i];
    		while (node != null) {
    			ListNode newNode = new ListNode(node.val);
				int hCode = hashcode(newNode.val, capacity);
				if (rst[hCode] == null) {
    				rst[hCode] = newNode;
    			} else {
    				ListNode move = rst[hCode];
    				while (move.next != null) {
    					move = move.next;
    				}
    				move.next = newNode;
    			}
    			node = node.next;
    		}
    	}

    	return rst;
    }

    public int hashcode(int key, int capacity) {
    	if (key < 0) {
    		return (key % capacity + capacity) % capacity;
    	} else {
    		return key % capacity;
    	}
    }
};














---
**217. [Remove Duplicates from Sorted Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Duplicates from Sorted Array.java)**
LinkedList里面我们是最好不要动node.val的，直接把node去掉。
而array我们很难直接把node去掉，又不能用新array，那么就要：

把不重复的element一个个放到最前面。


这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.

* 有个反向思维：remove duplicate,实际上也是找unique elements, and insert into original array


---
**218. [Remove Duplicates from Sorted List II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Duplicates from Sorted List II.java)**多个node，check node.next ?= node.next.next

---
**219. [Remove Duplicates from Sorted List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Duplicates from Sorted List.java)**
---
**220. [Remove Duplicates from Unsorted List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Duplicates from Unsorted List.java)**遍历。
遇到duplicate(可能多个),  while直到node.next不是duplicate.
接下去,既然不是duplicate,那就add 进 set


如果不用extra memory, do it in place:
那就要sort linked list. 用merge sort.

复习merge sort:
1. find middle.
2. recursively: right = sort(mid.next); left = sort(head).
3. within sort(), at the end call merge(left, right)

---
**221. [Remove Linked List Elements.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Linked List Elements.java)**如果不match, parent 和 node 一起移动

---
**222. [Remove Node in Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Node in Binary Search Tree.java)**Given a root of Binary Search Tree with unique value for each node.  Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.

Example
Given binary search tree:

          5

       /    \

    3          6

 /    \

2       4

Remove 3, you can either return:

          5

       /    \

    2          6

      \

         4

or :

          5

       /    \

    4          6

 /   

2

Tags Expand 
LintCode Copyright Binary Search Tree

Thoughts:
We can think of a couple different cases: where is that target node to remove? It can be root, a child (a couple more situations)
Note: before going futher, remember the technique to rip off parent node. In a binary tree, L > parent > R, so always find the L's right-most node and replace parent.
Cases1:
0. Root is target: find L's right-most node, replace root.
case1. A node with 2 null children: set target.parent.L/R = null
case2. A node with just just left node: set target.parent.L/R = target.right
case3. A node with left ndoe != null: find target.right-most node's left side X and replace target.parent.L/R.value = X.value. Remove set that X to null.

Divide into 2 major task:
1. Find the target node, and it's parent.
2. Remove that node (most complex logic)
*/
public class Solution {
    public TreeNode removeNode(TreeNode root, int value) {
    	if (root == null || (root.left == null && root.right == null)) {
    		return null;
    	}
    	TreeNode dummy = new TreeNode(0);;
    	dummy.left = root;
    	//Find node
    	TreeNode parent = findTargetParent(dummy, root, value);
    	TreeNode child;
    	if (parent.left != null && parent.left.val == value) {
    		child = parent.left;
    	} else if (parent.right != null && parent.right.val == value) {
    		child = parent.right;
    	} else {
    		return dummy.left;
    	}
    	//Delete that node:
    	deleteTargetNode(parent, child);
    	return dummy.left;
    }


	//Find target node
	public TreeNode findTargetParent(TreeNode parent, TreeNode node, int value){
    	if (node == null || node.val == value) {
    		return parent;
    	}
    	
    	if (value < node.val) {
    		return findTargetParent(node, node.left, value);
    	} else {
    		return findTargetParent(node, node.right, value);
    	}
    }
    //Delete node
    public void deleteTargetNode(TreeNode parent, TreeNode target) {
    	//Case1 + case2: (target.L == null && target.R == null) || (target.R == null && target.L != null)
    	if (target.right == null) {
    		if (parent.left == target) {
    			parent.left = target.left;
    		} else {
    			parent.right = target.left;
    		}
    	} else {//Case3: when target.right != null
    		TreeNode replaceNode = target.right;
    		TreeNode replaceParent = target;
    		while(replaceNode.left != null) {
    			replaceParent = replaceNode;
    			replaceNode = replaceNode.left;
    		}
            //Remove replaceNode from replaceParent
            if (replaceParent.left == replaceNode) {//Usually it'll be replaceParent.left
                replaceParent.left = replaceNode.right;
            } else {//Sometimes when target.left == null, than means replaceParent.right will be replaceNode (while loop didn't start at all)
                replaceParent.right = replaceNode.right;
            }
            
            //Remove target from parent: not sure it's left or right node of parent
            if (parent.left == target) {
                parent.left = replaceNode;
            } else {
                parent.right = replaceNode;
            }
            
    		replaceNode.left = target.left;
    		replaceNode.right = target.right;
    	}
    }

}









---
**223. [Remove Nth Node From End of List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Remove Nth Node From End of List.java)**Given a linked list, remove the nth node from the end of list and return its head.

Note
The minimum number of nodes in list is n.

Example
Given linked list: 1->2->3->4->5->null, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5->null.
Challenge
O(n) time

Tags Expand 
Two Pointers Linked List

Thinking process:
Very similar to 'Nth to last node'. Except, have a pre pointer to keep track of the previous node of 'nth to last'.
Also have a dummy.next to store the beginning of the list;
*/
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 0) {
            return null;
        }
        int count = 0;
        ListNode dummy = new ListNode(0);
        ListNode pre = new ListNode(0);
        pre.next = head;
        dummy = pre;
        ListNode node = head;
        while (node != null && count < n) {
            node = node.next;
            count++;
        }
        while (node != null) {
            node = node.next;
            head = head.next;
            pre = pre.next;
        }
        pre.next = head.next;
        return dummy.next;   
    }
}




---
**224. [Reorder List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Reorder List.java)**24% 通过
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.



样例
For example,
Given 1->2->3->4->null, reorder it to 1->4->2->3->null.

标签 Expand 
Linked List

Thinking Process:
Similar to sort list: 
find middle.
reverse last section
merge(head, mid) alternatively by using index % 2.
Append whatever left from the 2 lists
Note: re-order in place, does not necessarily mean you can create any variable. As long as the variable is O(1), it should be fine.
*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    private ListNode reverse(ListNode head) {
        ListNode reversedList = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = reversedList;
            reversedList = head;
            head = temp;
        }
        return reversedList;
    }

    private void merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        int index = 0;
        while (head1 != null && head2 != null) {
            if (index % 2 == 0) {
                dummy.next = head1;
                head1 = head1.next;
            } else {
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
            index += 1;
        }
        if (head1 != null) {
            dummy.next = head1;
        } else if (head2 != null) {
            dummy.next = head2;
        }
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;

        merge(head, tail);
    }
}


---
**225. [Restore IP Addresses.java](https://github.com/shawnfan/LintCode/blob/master/Java/Restore IP Addresses.java)**递归在一个index上面（具体问题，具体分析的情况）
validate string要注意leading '0'

注意： 递归的时候可以用一个start/level/index来跑路
但是尽量不要去改变Input source， 会变得非常confusing.


---
**226. [Reverse Integer.java](https://github.com/shawnfan/LintCode/blob/master/Java/Reverse Integer.java)**Reverse digits of an integer. Returns 0 when the reversed integer overflows (signed 32-bit integer).

Example
Given x = 123, return 321

Given x = -123, return -321

Tags Expand 
Integer

Thoughts:
1. Use long to capture the result. If > Integer.MAX_VALUE,return 0;
2. Use string to reverse, the conver to long
3. use string builder to reverse string

*/


public class Solution {
    /**
     * @param n the integer to be reversed
     * @return the reversed integer
     */
    public int reverseInteger(int n) {
        long num = (long)n;
        int sign = n > 0 ? 1 : -1;
        String rst = new StringBuilder(Math.abs(num)+"").reverse().toString();
		num = Long.parseLong(rst) * sign;
		
		if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
			return 0;
		} else {
			return (int)num;
		}
    }
}

---
**227. [Reverse Linked List II .java](https://github.com/shawnfan/LintCode/blob/master/Java/Reverse Linked List II .java)**存一下那个点，
从M开始， for loop， reverse [m~n]。 然后把三段链接在一起。



---
**228. [Reverse Linked List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Reverse Linked List.java)**		Level: Easy

建立新list。每次把newList append 在current node的后面。   
用head来循环所有node。


---
**229. [Reverse Words in a String II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Reverse Words in a String II.java)**		Level: Medium

In-place reverse.

reverse用两回. 全局reverse。局部:遇到空格reverse。

注意：结尾点即使没有' '也要给reverse一下最后一个词。



---
**230. [Reverse Words in a String.java](https://github.com/shawnfan/LintCode/blob/master/Java/Reverse Words in a String.java)**		Level: Medium

几种不同的方法flip：   
坑： 1. 结尾不能有空格。 2. 注意，如果Input是 ‘ ’的话，split以后就啥也没有了。check split以后 length == 0

另个题目Reverse Words in String (char[]) 可以in-place，因为条件说char[]里面是没有首尾空格,好做许多哟.


---
**231. [reverseInteger.java](https://github.com/shawnfan/LintCode/blob/master/Java/reverseInteger.java)**Reverse Integer 

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
//input = 1534236469

Thinking process:
Make sure of operators.
Note: check for overflow using long. When integer is > Integer.MAX_VALUE, then it's overflow.
Initialize long : long x = 1234L;
Convert using (int)

*/
public class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;    //123
        }
        boolean sign = x > 0; //sign = true
        long rst = 0L;
        x = Math.abs(x);                        // 123
        while (x != 0) {                        //x = 123, 12, 1
            rst = rst * 10 + x % 10;           //rst = 3, 30 + 2 = 32, 320 + 1 = 321
            x = x / 10;                         //x = 12; 1; 0
        }
        if (rst < 0 || rst > Integer.MAX_VALUE) {
            return 0;
        }
        return sign ? (int)rst : -(int)rst;
    }
}


---
**232. [Rotate Image.java](https://github.com/shawnfan/LintCode/blob/master/Java/Rotate Image.java)**		Level: Medium

找到个转角度的规律公式。用一个temp。in place.


---
**233. [Rotate List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Rotate List.java)**/* Given a list, rotate the list to the right by k places, where k is non-negative.

Example
Given 1->2->3->4->5->null and k=2
return 4->5->1->2->3->null
Tags Expand 
Basic Implementation Linked List

Thining process:
Two pointers.
First pointer move k steps.
Then 2 pointers start moving together. When 1st pointer reaches the end, then 2nd pointer should be in middle.
Let 2nd pointer be head, and move original head to tail of the list

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        //Check length
        int length = 0;
        ListNode dummy = head;
        while(dummy != null) {
            dummy = dummy.next;
            length++;
        }
        k = k % length;
        //Store dummy as 1 node before tail
        dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for (int i = 0; i < k; i++) {
            head = head.next;
        }
        //Move 2 pointers. When head reaches end, tail.next will be at the newHead
        ListNode tail = dummy;
        while (head.next != null) {
            head = head.next;
            tail = tail.next;
        }
        head.next = dummy.next;//Link old Head to the end, form circle
        dummy.next = tail.next;//Link tail.next as new head. tail should be end point.
        tail.next = null;//add null to end point tail
        return dummy.next;
    }
}

---
**234. [Rotate String.java](https://github.com/shawnfan/LintCode/blob/master/Java/Rotate String.java)**有个坑：offset可能很长，那么要%length，才能得到真正需要rotate的部分。
Note: rotate 一个 full length之后，是string 不变

---
**235. [Search a 2D Matrix II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Search a 2D Matrix II.java)**每次删掉一行，或者一列

---
**236. [Search a 2D Matrix.java](https://github.com/shawnfan/LintCode/blob/master/Java/Search a 2D Matrix.java)**2D转1D。
Binary Search

---
**237. [Search for a Range.java](https://github.com/shawnfan/LintCode/blob/master/Java/Search for a Range.java)**
---
**238. [Search Insert Position.java](https://github.com/shawnfan/LintCode/blob/master/Java/Search Insert Position.java)**在结尾判断该return 哪个position。

---
**239. [Search Range in Binary Search Tree .java](https://github.com/shawnfan/LintCode/blob/master/Java/Search Range in Binary Search Tree .java)**		Level: Medium

等于遍历了所有k1<= x <= k2的x node。

如果是用Binary Search Tree搜索，那么一般是if (...) else {...}，也就是一条路走到底，直到找到target.

这里, 把 left/right/match的情况全部cover了，然后把k1,k2的边框限制好，中间就全部遍历了。


---
**240. [Search Rotated in Sorted Array II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Search Rotated in Sorted Array II.java)**因为最终binary search的结果也是O(n)
所以这道题要记得： 既然是O(n), 那来个简单的for loop 也就好了。

当然，要跟面试官提起来原因。别一上来就只有for。。。

---
**241. [Search Rotated in Sorted Array.java](https://github.com/shawnfan/LintCode/blob/master/Java/Search Rotated in Sorted Array.java)**    1. binay search break point
    2. binary search target
    注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint]) 

方法2：
    还是把它先当做正常的sorted list开始搜。
    但是在比较的时候，多比较一个A[start] < A[mid]? 
    在1 和 2 里面分别讨论 target 的位置
        1. A[start] < A[mid] ?
            说明在前半段
            - start<target<mid
            - target > mid
        2. A[start] > A[mid]
            说明 start 还在前半段，而mid在后半段
            - mid < target < end
            - target < mid

   


---
**242. [Segment Tree Build II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Segment Tree Build II.java)**		Level: Medium


给的是Array。注意找区间内的max, assign给区间。   其余和普通的segment tree build一样   
给array,但是并不根据array里的内容排位，而是依然根据index割开区间，break到底，   
最终start==end。同时assign max=A[start] or A[end]

往上,parent一层的max:就是比较左右孩子,其实都是在两个sub-tree里面比较sub-tree的max。   

这就好做了：   
先分，找到left/right，比较max,在create current node,再append到当前node上面。

实际上是depth-first, 自底向上建立起的。


---
**243. [Segment Tree Build.java](https://github.com/shawnfan/LintCode/blob/master/Java/Segment Tree Build.java)**		Level: Medium

按定义：   
左孩子：（A.left, (A.left+A.rigth)/2）   
右孩子：（(A.left+A.rigth)/2＋1， A.right）   


---
**244. [Segment Tree Modify.java](https://github.com/shawnfan/LintCode/blob/master/Java/Segment Tree Modify.java)**		Level: Medium

Recursively 在segment tree里面找index, update it with value.   

每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下。   
最后轮回到头顶，头顶一下包括头顶，就全部都是max了。   


---
**245. [Segment Tree Query II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Segment Tree Query II.java)**		Level: Medium

和 Segment Tree Query I 以及其他Segment Tree问题没啥区别。这个就是return个count。

这个题目考了validate input source：input 的start,end可能超出root[start,end]。   
那么第一步就要先clear一下。完全不在range就return 0. 有range重合就规整到root的range.




---
**246. [Segment Tree Query.java](https://github.com/shawnfan/LintCode/blob/master/Java/Segment Tree Query.java)**		Level: Medium

[start,end]跟mid相比，可能：   
全在mid左   
全在mid右   
包含了mid： 这里要特别break into 2 query method   

按定义：   
mid = (root.start + root.end)/2


---
**247. [Serilization and Deserialization Of Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Serilization and Deserialization Of Binary Tree.java)**Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

There is no limit of how you deserialize or serialize a binary tree, you only need to make sure you can serialize a binary tree to a string and deserialize this string to the original structure.

Example
An example of testdata: Binary tree {3,9,20,#,#,15,7},  denote the following structure:

    3
   / \
  9  20
    /  \
   15   7
Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.

You can use other method to do serializaiton and deserialization.

Tags Expand 
Binary Tree

Thinking process:
1. Carefully turn the binary tree into a string: use pre-order in this example.
2. Use a global variable to track the data(data string will be cut in different levels of recursion).
The concept is very easy tho, just need to carefully code it up.

*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        String mid = root.val + ",";
        String left = serialize(root.left);
        String right = serialize(root.right);
        mid += left + right;
        return mid;
    }
    
    private String data = "";
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        this.data = data;
        return desHelper();
    }
    
    public TreeNode desHelper() {
        if (this.data.indexOf("#,") == 0) {
            this.data = this.data.substring(this.data.indexOf(",") + 1);
            return null;
        }
        String midVal = this.data.substring(0, this.data.indexOf(","));
        TreeNode mid = new TreeNode(Integer.parseInt(midVal));
        this.data = this.data.substring(this.data.indexOf(",") + 1);
        TreeNode left = desHelper();
        TreeNode right = desHelper();
        mid.left = left;
        mid.right = right;
        return mid;
    }
}




---
**248. [Single Number II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Single Number II.java)**Given 3*n + 1 numbers, every numbers occurs triple times except one, find it.
Example
Given [1,1,2,3,3,3,2,2,4,1] return 4

Challenge
One-pass, constant extra space
Thinking process:
Still using bit manipulation. We need to erase all of the 3-appearance number and leave the single number out. A few steps:
Store the final result by continuously bit OR with the result variable.
Want to XOR the 3 numbers, but can’t erase them as if only 2 duplicate numbers:Consider the number as 3-based number, so XOR can be understand this way 
	when add 3 numbers together, add each individual bit. If the sum is 3, then set it as 0. If not 3, leave as is.
3.   Store the bits in a integer array, which simulates a binary version of the integer
4.   When each bit’s XOR process finishes, bit OR it with result
*/

public class Solution {
    public int singleNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
	//present the XOR results in binary format
        int[] bits = new int[32];
        int rst = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++){
	//XOR the numbers in a 3-base fashion. Whenever bit[i] has a number 3, set it back to 0.
                bits[i] += A[j] >> i & 1;
                bits[i] %= 3;
            }
	//OR it to the result. However, each time only the i - spot is updated with the bits[i].
            rst |= bits[i] << i;
        }
        return rst;
    }
}


---
**249. [Single Number III.java](https://github.com/shawnfan/LintCode/blob/master/Java/Single Number III.java)**Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example
Given [1,2,2,3,4,4,5,3] return 1 and 5

Challenge
O(n) time, O(1) extra space.

Thinking Process:
The 2 exception must have this feature: a ^ b != 0, since they are different
Still want to do 2n + 1 problem as in Single Number I, then we need to split a and b into 2 groups and deal with two 2n+1 problems
Assume c = a^b, there mush be a bit where a and b has the difference, so that bit in c is 1.
Find this bit position and use it to split the group: shift number in the array by ‘bit-position’ indexes. If the shifted number has 1 at the ‘bit-position’, set it to one group; otherwise to another group. 
*/

public class Solution {
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        List<Integer> rst = new ArrayList<Integer>();
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }
        int bitOnePos = 0;
        for (int i = 0; i < 32; i++) {
            if ((xor >> i & 1) == 1) {
                bitOnePos = i;
            }
        }
        int rstA = 0;
        int rstB = 0;
        for (int i = 0; i < A.length; i++) {
            if ((A[i] >> bitOnePos & 1) == 1) {
                rstA ^= A[i];
            } else {
                rstB ^= A[i];
            }
        }
        rst.add(rstA);
        rst.add(rstB);
        return rst;
    }
}


---
**250. [Single Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Single Number.java)**62% Accepted
Given 2*n + 1 numbers, every numbers occurs twice except one, find it.

Example
Given [1,2,2,1,3,4,3], return 4

Challenge
One-pass, constant extra space

Tags Expand 
Greedy

Manipulate bits:
Thinking process:
One-pass and constant extra space.
since all numbers appears twice, consider them as in bits format. Two identical number XOR will be zero. If we XOR everything double-numbers together, it will be zero. At the end, we use o XOR our target number, the result is actually the target number.
Very smart trick to use bits.
In order to compare from index 0 to the end, we need to extract index 0 first as result before for loop. And start for loop at i = 1.
*/

public class Solution {
	/**
	 *@param A : an integer array
	 *return : a integer 
	 */
	public int singleNumber(int[] A) {
        if (A == null || A.length == 0) {
           return 0;
        }
        int rst = A[0];
        for (int i = 1; i < A.length; i++) {
            rst = rst ^ A[i];
        }
        return rst;
	}
}




---
**251. [Singleton.java](https://github.com/shawnfan/LintCode/blob/master/Java/Singleton.java)**Singleton is a most widely used design pattern. If a class has and only has one instance at every moment, we call this design as singleton. For example, for class Mouse (not a animal mouse), we should design it in singleton.

You job is to implement a getInstance method for given class, return the same instance of this class every time you call this method.


Example
In Java:

A a = A.getInstance();
A b = A.getInstance();
a should equal to b.

Challenge
If we call getInstance concurrently, can you make sure your code could run correctly?

Tags Expand 
LintCode Copyright OO Design

Thoughts:
...
Was not clear to me. Need to loop up more on synchronized/volatile
Good reference:
http://www.cnblogs.com/EdwardLiu/p/4443230.html

*/


class Solution {
	public static volatile Solution solution = null;
    /**
     * @return: The same instance of this class every time
     */
    public static Solution getInstance() {
        if (solution == null) {
            synchronized (Solution.class) {
                 // Double check
                 if (solution == null) {
                     solution = new Solution();
                 }
             }
         }
         return solution;
    }
};

---
**252. [Sliding Window Maximum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Sliding Window Maximum.java)**每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
为啥可以不管不无地剔除？
因为我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！

---
**253. [Sliding Window Median.java](https://github.com/shawnfan/LintCode/blob/master/Java/Sliding Window Median.java)**移动窗口2step：
1. 加一个数。
2. 减一个数。
加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
抽完balance一下。

记得:
左边的maxHeap总有 x+1或者x个数字。
后边minHeap应该一直有x个数字。


---
**254. [Sort Color.java](https://github.com/shawnfan/LintCode/blob/master/Java/Sort Color.java)**Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Example
Note
You are not suppose to use the library's sort function for this problem. 

Clarification
Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

Tags Expand 
Two Pointers Sort Array

Thoughts;
A easier version of Sort ColorII. Using the exact same code with different k number. Note, now k is start from 0.

*/

class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] colors) {
        if (colors == null || colors.length == 0) {
            return;
        }
        int end = colors.length - 1;
        int k = 2; // 3 different colors
        for (int i = 0; i < k; i++) {
            end = helper(colors, 0, end, k - i - 1);
        }
    }
    
    public void swap(int[] colors, int x, int y){
        int temp = colors[x];
        colors[x] = colors[y];
        colors[y] = temp;
    }
    
    public int helper(int[] colors, int start, int end, int pivot) {
        int low = start;
        int high = end;
        while (low <= high) {
            while(low < high && colors[low] <= pivot) {
                low++;
            }
            while(high > 0 && colors[high] > pivot) {
                high--;
            }
            if (low <= high) {
                swap(colors, low, high);
                low++;
                high--;
            }
        }
        return low - 1;
    }
}


















---
**255. [Sort Colors II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Sort Colors II.java)**Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

Example
GIven colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4]. 

Note
You are not suppose to use the library's sort function for this problem.

Challenge
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory.

Can you do it without using extra memory?

Tags Expand 
Two Pointers Sort

Thoughts (Need to revist and think about this, very interesting)
Doing quick sort partition for K -1 times.
1. Use K - 1 value as pivot
2. Starting from 0, whenever low<high && less or equal to pivot, low++
3. starting from end, whenever high >0, and greater than pivot, high--
4. Result: only swap when low and high have disagreement on the pivot value.

*/

class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0 || k <= 0) {
            return;
        }
        int end = colors.length - 1;
        for (int i = 0; i < k - 1; i++) {
            end = helper(colors, 0, end, k - i - 1);
        }
    }
    
    public void swap(int[] colors, int x, int y){
        int temp = colors[x];
        colors[x] = colors[y];
        colors[y] = temp;
    }
    
    public int helper(int[] colors, int start, int end, int pivot) {
        int low = start;
        int high = end;
        while (low <= high) {
            while(low < high && colors[low] <= pivot) {
                low++;
            }
            while(high > 0 && colors[high] > pivot) {
                high--;
            }
            if (low <= high) {
                swap(colors, low, high);
                low++;
                high--;
            }
        }
        return low - 1;
    }
}


















---
**256. [Sort Letters by Case.java](https://github.com/shawnfan/LintCode/blob/master/Java/Sort Letters by Case.java)**Given a string which contains only letters. Sort it by lower case first and upper case second.

Example
For "abAcD", a reasonable answer is "acbAD"

Note
It's not necessary to keep the original order of lower-case letters and upper case letters.

Challenge
Do it in one-pass and in-place.

Tags Expand 
String Two Pointers LintCode Copyright Sort

Thoughts:
Another two pointer sorting.
Difference: use a ASCII code 'a' as the pivot. all the letters that from a ~ z 
have bigger integer values, and A~Z have small integer values.
This problem requires lowcase+upperCase, so we'd sort the list from high to low.
NOTE: in the 2 while loop, the it's always having ">='

*/


public class Solution {
    /** 
     *@param chars: The letter array you should sort by Case
     *@return: void
     */
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        char pivot = 'a';
        int start = 0; int end = chars.length - 1;
        while (start <= end) {
            while (start <= end && chars[start] >= pivot) {
                start++;
            }
            while (start <= end && chars[end] < pivot) {
                end--;
            }
            if (start <= end) {
                char temp = chars[end];
                chars[end] = chars[start];
                chars[start] = temp;
                start++;
                end--;
            }
        }
    }
}












---
**257. [Sort List.java](https://github.com/shawnfan/LintCode/blob/master/Java/Sort List.java)**    1. find middle. 快慢指针
    2. Merge:  假设given list A, B 已经是sorted, 然后按照大小，混合。
    3. Sort: 切开两半，先sort前半, 如果先sort了mid.next~end, sort后，中间点mid.next == null，再sort前半段。
        然后mege.
        要recursively call itself.

Quick sort:
想做可以看讲义：http://www.jiuzhang.com/solutions/sort-list/

但是quick sort不建议用在list上面。

排列list, merge sort可能更可行和合理。原因分析在下面， 以及： http://www.geeksforgeeks.org/why-quick-sort-preferred-for-arrays-and-merge-sort-for-linked-lists/


---
**258. [Space Replacement.java](https://github.com/shawnfan/LintCode/blob/master/Java/Space Replacement.java)**Write a method to replace all spaces in a string with %20. The string is given in a characters array, you can assume it has enough space for replacement and you are given the true length of the string.

Example
Given "Mr John Smith", length = 13.

The string after replacement should be "Mr%20John%20Smith".

Note
If you are using Java or Python，please use characters array instead of string.

Challenge
Do it in-place.

Tags Expand 
String Cracking The Coding Interview

Thoughts:
Overriding the array from the back to front.
This is because as we re-writing the string from the back, stuff at head of the string does not change yet.
This is wonderful:) 

*/


public class Solution {
    /**
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
    	if (string == null || string.length == 0) {
    		return 0;
    	}
    	int count = 0;
    	for (char c : string) {
    		if (c == ' ') {
    			count += 2;
    		}
    	}
    	int lastIndex = length + count - 1;
    	//from back to front:
    	for (int i = length - 1; i >= 0; i--) {
    		if (string[i] == ' ') {
				string[lastIndex--] = '0';
				string[lastIndex--] = '2';
				string[lastIndex--] = '%';
    		} else {
    			string[lastIndex--] = string[i];
    		}
    	}
    	return length + count;
    }
}

---
**259. [Sqrt(x).java](https://github.com/shawnfan/LintCode/blob/master/Java/Sqrt(x).java)**Implement int sqrt(int x).

Compute and return the square root of x.

Example
sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3
Challenge
O(log(x))

Tags Expand 
Binary Search

Thinking process:
Binary search. While loop until the head and tail meets.
*/

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
       long start = 0;
       long end = x;
       while (end >= start) {
           long mid = start + (end - start) / 2;
           if (mid * mid > x) {
               end = mid - 1;
           } else if (mid * mid < x) {
               start = mid + 1;
           } else {
               return (int)mid;
           }
       }
	//When start > end, while loop ends. That means, end must be the largest possible integer that end^2 is closest to x.
       return (int)end;
    }
}

---
**260. [Stone Game.java](https://github.com/shawnfan/LintCode/blob/master/Java/Stone Game.java)**NOT DONE YET

---
**261. [String to Integer(atoi).java](https://github.com/shawnfan/LintCode/blob/master/Java/String to Integer(atoi).java)**		Level: Easy

方法1: 问清情况，一点一点把case都涉及到。

方法2: 用regular expression。if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点


---
**262. [String to Integer.java](https://github.com/shawnfan/LintCode/blob/master/Java/String to Integer.java)**Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
*/

/*
Thoughts:
Ask for requirement, and ajust the code:
Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

public class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
        	return 0;
        }
        String sign = "";
        String digits = "";
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
        	if (digits.length() == 0 && sign.length() == 0) {
        		if (arr[i] == '+' || arr[i] == '-') {
        			sign = arr[i] + "";
        			continue;
        		} else if (arr[i] == ' ') {
        			continue;
        		} else if (arr[i] < '0' || arr[i] > '9') {
        			break;
        		}
        	}//END: integer haven't begin, but can take '+/-' and skip space

        	if (arr[i] >= '0' && arr[i] <= '9') {
        		digits += arr[i];
        	} else {
        	    break;
        	}
        }
        if (digits.length() == 0) {//Check if empty()
            return 0;
        }
        if (digits.length() > 10) {//Check if we have more than 10 digits. It's here because it can exceed Long's MAX as well.
            return sign.equals("-") ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }
        long rst = Long.parseLong(digits) * (sign.equals("-") ? -1 : 1);
        if (rst > Integer.MAX_VALUE || rst < Integer.MIN_VALUE ) {//Double check if digits is within Integer's MAX and MIN
        	return rst > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int)rst;//Convert long to int
    }
}

---
**263. [Strobogrammatic Number II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Strobogrammatic Number II.java)**难的case先不handle.到底之后来一次O(n) scan.
总共的时间起码是O(n/2) + O(n), 所以还是O(n)

---
**264. [Strobogrammatic Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Strobogrammatic Number.java)**A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.
Tags: Hash Table Math
Similar Problems: (M) Strobogrammatic Number II, (H) Strobogrammatic Number III
*/

/*
OPTS 11.04.2015
Thoughts:
Because the symmetric pairs are:
1-1, 8-8,0-0,6-9,9-6, we make a hashmap of it.
Create left/right pointer, where each compare has to match the pair in hashmap.

Note:
On map.containsKey() line, need to check (right), or whichever item that map is going to map.get(...) afterwards. 
If containsKey fails, return false; only when it passes through, then proceed to mpa.get()
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<Character,Character>();
        map.put('0','0');
        map.put('1','1');
        map.put('8','8');
        map.put('6','9');
        map.put('9','6');
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!map.containsKey(num.charAt(right)) || num.charAt(left) != map.get(num.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/*
Thoughts:
Compare digits to the symmetric postion; special care for (6,9) pair, mark it after comparision.
Elimite the  cases before the for-loop run through(can do it in or as well, but that just make the code a bit complex)
Note:
Didn't use HashMap. I beleive hash map is used to mark the spot?
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
        	return true;
        }
        //Any non-strobogrammatic
        if (num.indexOf("2") >= 0 || num.indexOf("3") >= 0 ||
        	num.indexOf("4") >= 0 || num.indexOf("5") >= 0 ||
        	num.indexOf("7") >= 0) {
        	return false;
        }
        //If only 6 or 9 exist:
        if ((num.indexOf("6") >= 0 && num.indexOf("9") < 0) ||
        	(num.indexOf("9") >= 0 && num.indexOf("6") < 0)) {
        	return false;
        }
        //Check if (6,9) or other strobogrammatic # are appearing at symmetric position
    	char[] arr = num.toCharArray();
    	int leng = num.length();
    	for (int i = 0; i < leng; i++) {
    	    if (arr[i] == '6' || arr[i] == '9') {
        		if ((arr[i] == '6' && arr[leng - i - 1] != '9') ||
        			(arr[i] == '9' && arr[leng - i - 1] != '6')) {
        			return false;
        		}
    		    arr[i] = arr[leng - i - 1] = 'M';//marker
    		} else if (arr[i] != 'M' && arr[i] != arr[leng - i - 1]) {
    		    return false;
    		}
    	}
        return true;
    }
}

---
**265. [StrStr.java](https://github.com/shawnfan/LintCode/blob/master/Java/StrStr.java)**StrStr:
strStr My Submissions

19% Accepted
strstr (a.k.a find sub string), is a useful function in string operation. You task is to implement this function. 

For a given source string and a target string, you should output the "first" index(from 0) of target string in source string.

If target is not exist in source, just return -1.

Example
If source="source" and target="target", return -1.

If source="abcdabcdefg" and target="bcd", return 1.

Challenge
O(n) time.

Clarification
Do I need to implement KMP Algorithm in an interview?

    - Not necessary. When this problem occurs in an interview, the interviewer just want to test your basic implementation ability.

Tags Expand 
Basic Implementation String

Thinking process:
Two Pointer.
String Null case.
Break Statement.


Check position i+j of source and position j of target. If not match, break out.
If j matches target.length(), means target is fully embedded in source. 
return start point of target in source: i
*/
public int strStr(String source, String target) {
        //Check Null
        if(source == null || target == null){
            return -1;
        }
        //Two Pointer check for target
        int i,j;
        for (i = 0; i < source.length() - target.length() + 1; i++){
            for (j = 0; j < target.length(); j++){
                if (source.charAt(i+j) != target.charAt(j)){
                    break; 
                } 
            }
            if( j == target.length()){
                return i;
            }
        }
        //'target' not found:
        return -1;
}

---
**266. [Subarray Sum Closest.java](https://github.com/shawnfan/LintCode/blob/master/Java/Subarray Sum Closest.java)**Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4]

Challenge
O(nlogn) time

Tags Expand 
Subarray Sort

Thoughts:
Took a me a while to think through how to find the closest sum to 0.
Credits should be given to: http://rafal.io/posts/subsequence-closest-to-t.html
*/


class CustomComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return Integer.compare(a[0], b[0]);
    }
}

public class Solution {
    
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
   public ArrayList<Integer> subarraySumClosest(int[] nums) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) {
            return rst;
        }
        if (nums.length == 1) {
            rst.add(0); rst.add(0);
            return rst;
        }
        int[][] culmulate = new int[nums.length][2];
        culmulate[0][0] = nums[0];
        culmulate[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            culmulate[i][0] = culmulate[i - 1][0] + nums[i];
            culmulate[i][1] = i;
        }

        Arrays.sort(culmulate, new CustomComparator());
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = culmulate[i + 1][0] - culmulate[i][0];
            if (temp <= min) {
                min = temp;
                start = culmulate[i][1];
                end = culmulate[i + 1][1];
            }
        }
         if (start < end) {
            rst.add(start + 1);
            rst.add(end);
        } else {
            rst.add(end + 1);
            rst.add(start);
        }
        return rst;
    }
}





//I also had to run a little java program locally to test/debug:
/*

import java.lang.*;
import java.util.*;

class CustomComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return Integer.compare(a[0], b[0]);
    }
}

public class test {
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) {
            return rst;
        }
        int[][] culmulate = new int[nums.length][2];
        culmulate[0][0] = nums[0];
        culmulate[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            culmulate[i][0] = culmulate[i - 1][0] + nums[i];
            culmulate[i][1] = i;
        }
        //TEST:
        for(int i =0 ; i < nums.length; i++) {
            System.out.println("test:" + culmulate[i][0] + " " + culmulate[i][1]);
        }
        Arrays.sort(culmulate, new CustomComparator());
        for(int i =0 ; i < nums.length; i++) {
            System.out.println("sorted:" + culmulate[i][0] + " " + culmulate[i][1]);
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = culmulate[i + 1][0] - culmulate[i][0];
            System.out.println(culmulate[i + 1][0] + " minus " + culmulate[i][0] + " = " + temp);
            if (temp <= min) {
                min = temp;
                start = culmulate[i][1];
                end = culmulate[i + 1][1];
                System.out.println("record:" + start + " " + end );
            }
        }
        System.out.println("min:" + min);
        if (start < end) {
            rst.add(start + 1);
            rst.add(end);
        } else {
            rst.add(end + 1);
            rst.add(start);
        }
        return rst;
    }

    public static void main(String[] args){

        int[] nums = {6,-4,-8,3,1,7};//{5,10,5,3,2,1,1,-2,-4,3};
        test t = new test();
        ArrayList<Integer> rst = t.subarraySumClosest(nums);
        System.out.println(rst.get(0) + " " + rst.get(1));
    }
}

*/





---
**267. [Subarray Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Subarray Sum.java)**Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

Note
There is at least one subarray that it's sum equals to zero.

Tags Expand 
Subarray Hash Table

Thougths:
Record the sum from (0 ~ a).
Check sum on each index i, when found an existing sum in the hashMap, we are done.
Reason:
If adding all the numbers together, for example if sum[0 ~ a] = -3, ... sum[0 - b] = -3 again, a<b
That means from a ~ b, there is not change: that is, sum[a - b] = 0.
As result, hashmap.get(a)+1 will be the satrting index, and b will be ending index.
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        //we know that sub-array (a,b) has zero sum if SUM(0 ... a-1) = SUM(0 ... b)
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                rst.add(map.get(sum) + 1);
                rst.add(i);
                return rst;
            }
            map.put(sum, i);
        }//for
        return rst;
    }
}

---
**268. [Subset.java](https://github.com/shawnfan/LintCode/blob/master/Java/Subset.java)**		Level: Medium

最基本的递归题目。   
坑：记得一开头sort一下 nums。 因为要升序。

注意：用level/index来track到哪一步。最后一level就add into rst

方法1: subset的概念，取或者不取,backtracking. 当level/index到底，return 一个list.

方法2: 用for loop backtracking. 记得：每个dfs recursive call是一种独特可能，先加进rst。


---
**269. [Subsets II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Subsets II.java)**
Iterative: 写一写，用个Queue.

---
**270. [Subtree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Subtree.java)**		Level: Easy

找到potential subtree, 比较Children.

一点注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!），而children不同。所以同时要继续recursively isSubtree(T1.left, T2) ...etc.


---
**271. [Summary Ranges.java](https://github.com/shawnfan/LintCode/blob/master/Java/Summary Ranges.java)**Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

Tags: Array
Similar Problems: (M) Missing Ranges


*/
/*
Thoughts: basic implementation, use a arraylist to catch candidates. 
Detect condition, and return results.
*/
public class Solution {
    public List<String> summaryRanges(int[] nums) {
     List<String> rst = new ArrayList<String>();
     if (nums == null || nums.length == 0) {
     	return rst;
     }   
     ArrayList<Integer> list = new ArrayList<Integer>();
     for (int i = 0; i < nums.length; i++) {
     	list.add(nums[i]);
     	if (i + 1 == nums.length || nums[i] + 1 != nums[i + 1]) {
     		if (list.size() == 1) {
     			rst.add(list.get(0) + "");
     		} else {
     			rst.add(list.get(0) + "->" + list.get(list.size() - 1));
     		}
     		list = new ArrayList<Integer>();
     	}
     }
     return rst;
    }
}

//O(n)

---
**272. [Surrounded Regions.java](https://github.com/shawnfan/LintCode/blob/master/Java/Surrounded Regions.java)**Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Hide Tags Breadth-first Search

Thinking Process:
Since dfs does not work, try bfs.
Very similar to DFS, however, when checking the 4 bounaries: 
1. chcek the curruent point.
2. Add surrounding points into a queue.
3. Deal with the queue immediately via a while loop

*/
public class Solution {
    private char[][] board;
    private int row;
    private int col;
    private char target;
    private char mark;
    private Queue<Integer> queue = new LinkedList<Integer>();
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.board = board;
        row = board.length;
        col = board[0].length;
        target = 'O';
        mark = 'M';
        //Check the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    check(i,j);
                }
            }
        }
        //Replacement
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == target) {
                    board[i][j] = 'X';
                } 
                if (board[i][j] == mark) {
                    board[i][j] = target;
                }
            }
        }
    }
    //BFS
    public void check(int i, int j) {
        fill(board, i, j);
        while(!queue.isEmpty()) {
            int val = queue.poll();
            int x = val / col;
            int y = val % col;
            fill(board, x - 1, y);
            fill(board, x + 1, y);
            fill(board, x, y - 1);
            fill(board, x, y + 1);
        }
    }
    public void fill(char[][] board, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != target) {
            return;
        }
        board[i][j] = mark;
        queue.offer(i * col + j);
    }
}




/*
Thinking process:
Using DFS.
1. Whenever the edge has an 'O', all touching point with 'O' will be non-surrounded by 'X'. SO check the 4 bounds first. Mark all non-surrounded point as M.
2. Replace all remaining 'O' with 'X'
3. Replace 'M' with 'O'
However, in the LeetCode test, DFS gives stack overflow. So we'd use BFS instead.
*/

/*

//The following is using DFS, but gives Stackoverflow.
public class Solution {
    private char[][] board;
    private int row;
    private int col;
    private char target;
    private char mark;
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.board = board;
        target = 'O';
        mark = 'M';
        row = board.length;
        col = board[0].length;
        
        //check bound
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                    check(i, j);
                }
            }
        }
        //1. replace remaining target with 'x'
        //2. replace all mark with 'O'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == target) {
                    board[i][j] = 'X';
                } else if (board[i][j] == mark) {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    public void check(int i, int j) {
        if (i < 0 || j < 0 || i > row - 1 || j > col - 1) {
            return;
        }
        if (board[i][j] == target) {
            board[i][j] = mark;
            check(i - 1, j);
            check(i + 1, j);
            check(i, j - 1);
            check(i, j + 1);
        }
    }

}

*/

---
**273. [Swap Nodes in Pairs.java](https://github.com/shawnfan/LintCode/blob/master/Java/Swap Nodes in Pairs.java)**画三个block, 1,2,3. 连线。

---
**274. [Symmetric Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Symmetric Binary Tree.java)**		Level: Easy

注意Symmetric Binary Tree的例子和定义: 是镜面一样的对称. 并不是说左右两个sub-tree相等。

方法1: Recursively check symmetrically相对应的Node.  每个node的children都和镜面另外一边相对的node的children刚好成镜面反射位置。

方法2: 用stack. 左手边sub-tree先加left,再加right child; 右手边sub-tree先加right child, 再加left child。   
process时，若symmetric，所有stack里面出来的node会一一对应。


---
**275. [The Smallest Difference.java](https://github.com/shawnfan/LintCode/blob/master/Java/The Smallest Difference.java)**Given two array of integers(the first array is array A, the second array is array B), now we are going to find a element in array A which is A[i], and another element in array B which is B[j], so that the difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.


Example
For example, given array A = [3,6,7,4], B = [2,8,9,3], return 0

Challenge
O(n log n) time

Tags Expand 
Two Pointers LintCode Copyright Sort Array
*/

/*
	Thoughts:
	Sort A, B. O(nLogn)
	Use smaller array to binarh in longer array. n * logn

*/


public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
    	if (A == null || A.length == 0 || B == null || B.length == 0) {
    		return 0;
    	}
    	if (A.length > B.length) {
    		int[] temp = A;
    		A = B;
    		B = temp;
    	}
    	Arrays.sort(A);
    	Arrays.sort(B);
    	int diff = Integer.MAX_VALUE;	

    	for (int i = 0; i < A.length; i++) {//10
    		int start = 0;
    		int end = B.length - 1;
    		int mid;
    		//Small enhancement
    		if (B[start] >= A[A.length - 1]) {
            	return B[start] - A[A.length - 1];
            }
            if (A[start] >= B[B.length - 1]) {
            	return A[start] - B[B.length - 1];
            }
    		while (start + 1 < end) {
    			mid = start + (end - start)/2;
    			if (B[mid] == A[i]) {
    				return 0;
    			} else if (mid - 1 >= 0 && B[mid - 1] < A[i] && A[i] < B[mid]) {
    				diff = Math.min(diff, Math.min(A[i] - B[mid - 1], B[mid] - A[i]));
    				break;
    			} else if (mid + 1 < B.length - 1 && B[mid] < A[i] && A[i] < B[mid + 1]) {
    				diff = Math.min(diff, Math.min(A[i] - B[mid], B[mid + 1] - A[i]));
    				break;
    			} else if (B[mid] > A[i]) {
    				end = mid;
    			} else {
    				start = mid;
    			}
    		
    		}//end while
        	if (start + 1 >= end) {
        	    int min = Math.min(Math.abs(A[i] - B[start]), Math.abs(A[i] - B[end]));
				diff = Math.min(diff, min);
			}
    	}//end for
    	return diff;
    }
}









---
**276. [Top K Frequent Words.java](https://github.com/shawnfan/LintCode/blob/master/Java/Top K Frequent Words.java)**		Level: Medium

方法1：Brutle force用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
   注意排序时Collection.sort()的cost是O(nLogk)


方法2: Trie && MinHeap屌炸天   
   http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/


---
**277. [Topological Sorting.java](https://github.com/shawnfan/LintCode/blob/master/Java/Topological Sorting.java)**Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

Example
For graph as follow:

picture

The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
Note
You can assume that there is at least one topological order in the graph.

Challenge
Can you do it in both BFS and DFS?

Tags Expand 
LintCode Copyright Geeks for Geeks Depth First Search Breadth First Search

Thoughts:
First idea is Breadth First Search.
1. Find the node which has no parent node: this will be the beginning node. Use a HashMap to map all nodes with children, and whatever not in that map, is a root option.
2. Starting from this node, put all nodes in the queue (breadth-first)
3. process each node in the queue: add to array list


Note: All all possible root node (whatever not added into the map) because there could be multiple heads : (. Really need to ask about this if not sure.

*/

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> rst = new ArrayList<DirectedGraphNode>();
        if (graph == null || graph.size() == 0) {
        	return graph;
        }
       	//Keep track of all neighbors in HashMap
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (DirectedGraphNode node : graph) {
        	for (DirectedGraphNode neighbor : node.neighbors) {
        		int keyN = neighbor.label;
	        	if (map.containsKey(keyN)) {
	        		map.put(keyN, map.get(keyN) + 1);
	        	} else {
	        		map.put(keyN, 1);
	        	}
        	}
        }
        //BFS: Add root node. Note: 
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
        	if (!map.containsKey(node.label)) {
        		queue.offer(node);
        		rst.add(node);
        	}
        }
        //BFS: go through all children
        while (!queue.isEmpty()) {
        	DirectedGraphNode node = queue.poll();	
        	for (DirectedGraphNode n : node.neighbors) {
        		int label = n.label;
        		map.put(label, map.get(label) - 1);
        		if (map.get(label) == 0) {
        			rst.add(n);
        			queue.offer(n);
        		}
        	}
        }
        return rst;
    }
}

---
**278. [Total Occurrence of Target.java](https://github.com/shawnfan/LintCode/blob/master/Java/Total Occurrence of Target.java)**找total number of occurance. 首先找first occurance, 再找last occurance.

---
**279. [Trailing Zeros.java](https://github.com/shawnfan/LintCode/blob/master/Java/Trailing Zeros.java)**Write an algorithm which computes the number of trailing zeros in n factorial.

Example
11! = 39916800, so the out should be 2

Challenge
O(log N) time

Tags Expand 
Mathematics

Thoughts:
Attempt1:
Can this problem be converted to : how many 10's to I have?
Loop through n, and check how many 2s, 5s do we have.
For each i, do while loop and count the number of 2s, and 5s in that particular i.

Note:
5 and 2 makes 10. So don't worry about 10.
Some values will be checked redundantly, so record the ones checked, return the hash value directly.

Attempt2:
Don't even need to worry about 2's because 2 is definitely more than 5's. Only need to care about 5's.

How many 5's? n/5.   loop (1 ~ n)
However, some number within (1 ~ n) may give more 5's, which for example is: 25 = 5 * 5, double 5's. And 125 = triple 5's.

In fact count = n / 5 + n / 25 + n / 125 + ....
*/
class Solution {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
    	if ( n < 5) {
    		return 0;
    	}
    	long count = 0;
    	for (long i = 5; n / i != 0; i *= 5) {
    		count += n / i;
    	}
    	return count;
    }

}



/*
//Attempt 1:
//This solution exceed time limit, and it's over-complex. 滚粗。
class Solution {

    private HashMap<Long, Long> mapTwo = new HashMap<Long, Long>();
	private HashMap<Long, Long> mapFive = new HashMap<Long, Long>();
    public long trailingZeros(long n) {
    	if (n < 5) {
    		return 0;
    	}
    	long countFive = 0;
    	long countTwo = 0;
    	for (int i = 1; i <= n; i++) {
    		if (i % 2 == 0) {
    			countTwo += countExistance(i, 2, mapTwo);
    		}
    		if (i % 5 == 0) {
    			countFive += countExistance(i, 5,mapFive);
    		}
    	}
    	return (countFive < countTwo) ? countFive : countTwo;
    }
    public long countExistance(long n, long m, HashMap<Long, Long> map) {
    	long temp = n;
    	long count = 0;
    	double num = (double)n;
    	while (num / m == n / m) {
    		count++;
    		n = n / m;
    		num = (double)n;
    		if (map.containsKey(n)) {
    			count += map.get(n);
    			break;
    		}
    	}
    	if (!map.containsKey(temp)) {
    		map.put(temp, count);
    	}
    	return count;
    }
};

*/

---
**280. [Trapping Rain Water II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Trapping Rain Water II.java)**Trapping Rain Water II
Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.


Example
Given 5*4 matrix

[12,13,0,12]
[13,4,13,12]
[13,8,10,12]
[12,13,12,12]
[13,13,13,13]
return 14.

Tags Expand 

LintCode Copyright Heap Matrix

*/

/*
Thoughts: same idea as the trap Rain Water I.
Since this is not 1-way run through a 1D array (2D array can go 4 directions...), need to mark visted spot.

Use PriorityQueue, sort lowest on top, because the lowest surroundings determines the best we can get.

Bukkit theory: the lowest bar determines the height of the bukkit water. So, we always process the lowest first. 
Therefore, we use a min-heap, a natural order priorityqueue based on height.

Note: when adding a new block into the queue, comparing with the checked origin, we still want to add the higher height into queue. 
(The high bar will always exist and hold the bukkit.)

Step:
1. Create Cell (x,y,h)
2. Priorityqueue on Cell of all 4 borders
3. Process each element in queue, and add surrounding blocks into queue.
4. Mark checked block


*/

public class Solution {
	class Cell {
		int x;
		int y;
		int h;
		public Cell(int x, int y, int height) {
			this.x = x;
			this.y = y;
			this.h = height;
		}
	}
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
    	if (heights == null || heights.length == 0 || heights[0].length == 0) {
    		return 0;
    	}

    	PriorityQueue<Cell> queue = new PriorityQueue<Cell>(1, new Comparator<Cell>(){
    		public int compare(Cell A, Cell B) {
    		    return A.h - B.h;
    		}
    	});
    	int n = heights.length;
    	int m = heights[0].length;
    	boolean[][] visited = new boolean[n][m];

    	//LEFT-RIGHT
    	for (int i = 0; i < n; i++) {
    		visited[i][0] = true;
    		visited[i][m - 1] = true;
    		queue.offer(new Cell(i, 0, heights[i][0]));
    		queue.offer(new Cell(i, m - 1, heights[i][m - 1]));
    	}
    	//TOP-BOTTOM
    	for (int i = 0; i < m; i++) {
    		visited[0][i] = true;
    		visited[n - 1][i] = true;
    		queue.offer(new Cell(0, i, heights[0][i]));
    		queue.offer(new Cell(n - 1, i, heights[n - 1][i]));
    	}

    	int[] xs = {0,  0, 1, -1};
    	int[] ys = {1, -1, 0,  0};
    	int sum = 0;
    	while (!queue.isEmpty()) {
    		Cell cell = queue.poll();
    		for (int i = 0; i < 4; i++) {
    			int nx = cell.x + xs[i];
    			int ny = cell.y + ys[i];
    			if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
    				visited[nx][ny] = true;
    				sum += Math.max(0, cell.h - heights[nx][ny]);
    				queue.offer(new Cell(nx, ny, Math.max(heights[nx][ny], cell.h)));
    			}
    		}
    	}//end while
    	return sum;
    }
};
















---
**281. [Trapping Rain Water.java](https://github.com/shawnfan/LintCode/blob/master/Java/Trapping Rain Water.java)**双面夹击：找中间最大bar，两面往中心扫


---
**282. [Triangle Count.java](https://github.com/shawnfan/LintCode/blob/master/Java/Triangle Count.java)**Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

Example
Given array S = [3,4,6,7], return 3. They are:

[3,4,6]
[3,6,7]
[4,6,7]
Given array S = [4,4,4,4], return 4. They are:

[4(1),4(2),4(3)]
[4(1),4(2),4(4)]
[4(1),4(3),4(4)]
[4(2),4(3),4(4)]
Tags Expand 
Two Pointers LintCode Copyright
*/

/*
Thoughts:
Pick 3 integers that fits the condition: 
A + B > C
B + C > A
A + C > B
If we sort the input, then we know A <= B <= C, so we can remove 2 conditoins above and only have:
A + B > C
That is, Pick one C, and pick two integers A,B in front. Similar to TWO SUM II.
Have a fixed C as target, and find A + B > target in the remaining array on left of C. 
How about just use 2 pointers left, right, and compare with a C (s[i] in for loop)
Time: O(n^2)

Note: don't forget to sort
*/

public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
    	if (S == null || S.length == 0) {
    		return 0;
    	}
    	Arrays.sort(S);
    	int count = 0;
    	for (int i = 0; i < S.length; i++) {
    		int left = 0;
    		int right = i - 1; //at least 1 step left from C
    		while (left < right){
    			if (S[left] + S[right] > S[i]) {
	    			count += (right - left);
	    			right--;
	    		} else {//(S[left] + S[right] <= S[i]) 
	    			left++;
	    		}
    		}
    	}
    	return count;
    }
}


---
**283. [Tweaked Identical Binary Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Tweaked Identical Binary Tree.java)**		Level: Easy

Recursive 比对左左,左右,右左，右右


---
**284. [Two Lists Sum.java](https://github.com/shawnfan/LintCode/blob/master/Java/Two Lists Sum.java)**You have two numbers represented by a linked list, where each node contains a single digit.The digits are stored in reverse order, such that the 1’s digit is at the head of the list.Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given two lists, 3->1->5->null and 5->9->2->null, return 8->0->8->null

Tags Expand 
Linked List Backtracking
//TODO: check 9chapter solution
Thinking process:
Simply add 2 lists’ values together.
Handle the carrier
Use dummy node at beginning.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;      
 *     }
 * }
 */
public class Solution {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        ListNode rst = new ListNode(0);
        ListNode dummy = rst;
        int carrier = 0;
        //while
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carrier += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carrier += l2.val;
                l2 = l2.next;
            }
            rst.next = new ListNode(carrier % 10);
            carrier = carrier / 10;
            rst = rst.next;
        }
        //check the carrier
        if (carrier == 1) {
            rst.next = new ListNode(1);
        }
        return dummy.next;
    }
}


---
**285. [Two Strings Are Anagrams.java](https://github.com/shawnfan/LintCode/blob/master/Java/Two Strings Are Anagrams.java)**		Level: Easy

方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做


---
**286. [Ugly Number II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Ugly Number II.java)**每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
最后时间是n*log(n*3)

注意：
Long
HashSet确保没有重复。

---
**287. [Ugly Number.java](https://github.com/shawnfan/LintCode/blob/master/Java/Ugly Number.java)**Ugly number is a number that only have factors 3, 5 and 7.

Design an algorithm to find the Kth ugly number. The first 5 ugly numbers are 3, 5, 7, 9, 15 ...

Example
If K=4, return 9.

Challenge
O(K log K) or O(K) time.

Tags Expand 
LintCode Copyright Priority Queue

Thoughts:
Every level it's like:
3		5		7
3		3,5		3,5,7

Use a priority queue to keep track. 
Use a for loop to keep calculating the target number, and return it at the end

Note:
Why not offer 3,5, 7 in first if statement? (Which is my original thought). Maybe, we want to limit the number of offers in 3's case, in case some 3's cases becomes bigger than 5's case. That, will accidentally prevent the program to check on 5's.
Therefore, leave 3,5,7 cases till 7's .

*/

class Solution {
    /**
     * @param k: The number k.
     * @return: The kth prime number as description.
     */
    public long kthPrimeNumber(int k) {
    	if (k == 0) {
    		return 0;
    	}
    	PriorityQueue<Long> queue = new PriorityQueue<Long>();
    	queue.offer((long)3);
    	queue.offer((long)5);
    	queue.offer((long)7);
    	long num = 0;
		for (int i = 0; i < k; i++) {
			num = queue.poll();
			if (num % 3 == 0) {
				queue.offer(num  * 3);
			} else if (num % 5 == 0) {
			    queue.offer(num  * 3);
				queue.offer(num  * 5);
			} else if (num % 7 == 0) {
				queue.offer(num  * 3);
				queue.offer(num  * 5);
				queue.offer(num  * 7);
			}
		}
		return num;
    }
};


/*
Can use DP as well:http://blog.welkinlan.com/2015/07/28/ugly-number-lintcode-java/
*/

---
**288. [Unique Binary Search Tree II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Unique Binary Search Tree II.java)**Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

Example
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Tags Expand 
Dynamic Programming Depth First Search

Thinking process:
- For a BST, root can be any node from node(1) to node(n).
- For each root, left nodes has mutiple forms of BST, and right node has mutiple forms of BST.
- For each root node, divide and conquer left / right
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }
    public ArrayList<TreeNode> generate(int start, int end) {
        ArrayList<TreeNode> rst = new ArrayList<TreeNode>();
        if (start > end) {
            rst.add(null);
            return rst;
        }
        for (int i = start; i <= end; i++){
            ArrayList<TreeNode> left = generate(start, i - 1);
            ArrayList<TreeNode> right = generate(i +  1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    rst.add(root);
                }
            }
        }
        return rst;
    }
}


---
**289. [Unique Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Unique Binary Search Tree.java)**Given n, how many structurally unique BST's (binary search trees) that store values 1...n?



Example
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Tags Expand 
Catalan Number Dynamic Programming

Thinking proces:
Knowing what is Catalan number. 
C(n+1) = SUM(C(i)*C(n-i))
OR: C(n) = SUM(C(i)*C(n-i-1)).
*/

public class Solution {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}

---
**290. [Unique Characters.java](https://github.com/shawnfan/LintCode/blob/master/Java/Unique Characters.java)**用hashSet, space O(n), time O(n)

---
**291. [Unique Path.java](https://github.com/shawnfan/LintCode/blob/master/Java/Unique Path.java)**A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note
m and n will be at most 100.

Example
1,1		1,2		1,3		1,4		1,5		1,6		1,7		
2,1
3,1												3,7

Above is a 3 x 7 grid. How many possible unique paths are there?

Tags Expand 
Array Dynamic Programming

Thinking process:
f[x][y]: want to find out all possible path
To get to f[m][m] from f[m-1][n-1] has 2 way: f[m-1][n] or f[m][n-1].
After found 'f[m-1][n-1]', store it to a Hashmap with the #path.
Every node pair (x,y) should have 1 solution.
Recursively add up to (0,0), will find out the total path.

1. Own solution: user HashMap to memorize
*/

public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    private int m,n;
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        this.m = m;
        this.n = n;
        HashMap<ArrayList<Integer>, Integer> his = 
            new HashMap<ArrayList<Integer>, Integer>();
        int right = helper(0, 1, his);
        int down = helper(1, 0, his);
        return right + down;
    }
    
    public int helper(int x, int y, HashMap<ArrayList<Integer>, Integer> his) {
        ArrayList<Integer> pair = new ArrayList<Integer>();
        pair.add(x);
        pair.add(y);
        if (his.containsKey(pair)) {
            return his.get(pair);
        }
        if (x >= this.m -1 || y >= this.n - 1) {
            his.put(pair, 1);
            return his.get(pair);
        }
        int right = helper(x, y + 1, his);
        int down = helper(x + 1, y, his);
        his.put(pair, right + down);
        return his.get(pair);
    }
}

/*

2. 9Chapter solution
Thinking process:
1. Assume (r,c) where r>=1, c>=1. Any node (r,c) has 2 ways to get to: (r-1, c) from top, or (r,c-1) from left-side.
2. (r-1, c) and (r,c-1) stores the possible paths to get to them
3. (r,c) = (r-1, c) + (r,c-1)
4. Initialize the top-row and left-column to be 1: Assuming landing on any initial node has path # of 1.
5. From top-bottom traverse
*/

public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
	//Traverse
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        int[][] matrix = new int[m][n];
        //Initialize
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            matrix[0][i] = 1;
        }
        //Traverse
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }
}


---
**292. [Unique Paths II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Unique Paths II.java)**Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note
m and n will be at most 100.

Example
For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Tags Expand 
Array Dynamic Programming

Thinking process:
1. Still use an extra matrix to count possible paths. 
2. When initializing, skip block if it's obstacle (break the for loop, basically skip this row/col)
3. When evaluating paths, skip block if it's obstacle (save current spot's path as 0, means no path through this point).
4. Note: At evaluating double-for loop, we cannot use break, because we still need to evaluate using upper/left block. Hence we set the obstacle = 0.
*/

public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                matrix[i][0] = 1;
            }
        }
        for (int j = 0; j < col; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            } else {
                matrix[0][j] = 1;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
                }
            }
        }
        return matrix[row - 1][col - 1];
    }
}


---
**293. [Unique Word Abbreviation.java](https://github.com/shawnfan/LintCode/blob/master/Java/Unique Word Abbreviation.java)**An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

Tags: Hash Table, Design
Similar Problems: (E) Two Sum III - Data structure design



*/
/*
Thought:
Originally, used a hashset to store all existing pattern. If checked word exist in dict hashset, then return false.
However, there is a case that: the word existed in the dict only for once, which is by accident the same as the checked work, then return true.
Therefore, we need to keep track of what word has been catagrize into pattern. SO, use a HashMap<String, ArrayList> instead.

Note: Dealing with char, integer, string. Be careful if char are turnning int integers.
*/
public class ValidWordAbbr {
	HashMap<String, ArrayList<String>> map;
    public ValidWordAbbr(String[] dict) {
        if (dict == null || dict.length == 0) {
        	return;
        }
        map = new HashMap<String, ArrayList<String>>();
        for (String s : dict) {
        	String str = "";
        	if (s.length() <= 2) {
        		str = s;
        	} else {
        		str += s.charAt(0) + (s.length() - 2 + "") + s.charAt(s.length() - 1);
        	}
        	if (!map.containsKey(str)) {
        		ArrayList<String> list = new ArrayList<String>();
 				list.add(s);
 				map.put(str, list);
        	} else {
        	    if (!map.get(str).contains(s)) {
        	       	map.get(str).add(s); 
        	    }
        	
        	}
        }
    }

    public boolean isUnique(String word) {
        if (map == null || map.size() == 0) {
            return true;
        }
        String str = "";
        if (word.length() <= 2) {
        	str =  word;
        } else {
        	str += word.charAt(0) + (word.length() - 2 + "") + word.charAt(word.length() - 1);
        }
        if (map.containsKey(str) && map.get(str).size() == 1 && map.get(str).get(0).equals(word)) {
        	return true;
        }
        return !map.containsKey(str);
    }
}





// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");

---
**294. [Update Bits.java](https://github.com/shawnfan/LintCode/blob/master/Java/Update Bits.java)**Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)   



Example
Given N = (10000000000)2, M = (10101)2, i = 2, j = 6

return N = (10001010100)2

Challenge
Minimum number of operations？

Tags Expand 
Cracking The Coding Interview Bit Manipulation Binary Representation

Thinking process:
Create a mask: xxxx000000xxxx.
Trick part: when it encounters negative number or dealing with index at edge index = 31, it starts having issue. Interesting fix: use long for masks.
*/

class Solution {
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        //Create mask: xxx00000xxx
        long rightMask = ~0 >> i;
        rightMask = ~(rightMask << i);// 00000xxx
        long leftMask = ~0 >> (j + 1);
        leftMask = leftMask << (j + 1);//xxxxx00000000
        long mask = leftMask | rightMask;//xxx00000xxx
        n = (int) (n & mask);
        n = (int) (n | (m << i));
        return n;
    }
}



---
**295. [Valid Anagram.java](https://github.com/shawnfan/LintCode/blob/master/Java/Valid Anagram.java)**Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

Tags: Hash Table, Sort
Similar Problems: (M) Group Anagrams, (E) Palindrome Permutation


*/
/*
Thoughts:
Anagram: reorder of letters.
Use HashMap<charactor, count> to store the frequency of chars of 1st string, and check aginst 2nd string.
s character: +1;
t character: -1;
check count of each index in the map; they should all be 0
*/


public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
        	return s == null && t == null;
        } else if (s.length() != t.length()) {
        	return false;
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
        	if (!map.containsKey(s.charAt(i))) {
        		map.put(s.charAt(i), 1);
        	} else {
        		map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        	}
        	if (!map.containsKey(t.charAt(i))) {
        		map.put(t.charAt(i), -1);
        	} else {
        		map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
        	}
        }//END for
        
        for (int i = 0; i < s.length(); i++) {
        	if (map.get(s.charAt(i)) != 0) {
        		return false;
        	}
        }
        return true;
    }
}

---
**296. [Valid Palindrome.java](https://github.com/shawnfan/LintCode/blob/master/Java/Valid Palindrome.java)**
---
**297. [Valid Parentheses.java](https://github.com/shawnfan/LintCode/blob/master/Java/Valid Parentheses.java)**		Level: Easy

剥皮过程。解铃还须系铃人   
左边的外皮'{['在stack底部   
右边的外皮应该和stack顶上的左外皮一一对应 




---
**298. [Valid Sudoku.java](https://github.com/shawnfan/LintCode/blob/master/Java/Valid Sudoku.java)**validate block的时候虽然看到了4层for.其实也就是n^2.

---
**299. [Validate Binary Search Tree.java](https://github.com/shawnfan/LintCode/blob/master/Java/Validate Binary Search Tree.java)**		Level: Medium

查看每个parent-child关系。同时把root level上面传下来max,min界限定住。


---
**300. [Wiggle Sort.java](https://github.com/shawnfan/LintCode/blob/master/Java/Wiggle Sort.java)**这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！

这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。

---
**301. [Wood Cut.java](https://github.com/shawnfan/LintCode/blob/master/Java/Wood Cut.java)**Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

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
**302. [Word Break.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Break.java)**Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

Tags Expand 
String Dynamic Programming

*/
/*
Attemp3:
Optimize attempt2: If the input s is super long, but Dict does not have that long string, then we should avoid that case, so to save time. That is, check dict's strings' max length, and incldue that in 2nd-level for loop

j: last word's length, range from 0 to i.
[i - j]: the first index of current word
rst[i - j]: if s[i ~ j] returns true
s.substring(i - j, i): if s[i-j position to i position] is in dict

Note: use maxLength to optimize the solution.

*/

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict.contains(s)) {
            return true;
        }
        boolean[] rst = new boolean[s.length() + 1];
        rst[0] = true;
        int maxLength = calMaxLength(dict);
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= i && j <= maxLength; j++) {
                if (rst[i - j] && dict.contains(s.substring(i - j, i))) {
                    rst[i] = true;
                    break;
                }
            }
        }
        return rst[s.length()];
    }

    public int calMaxLength(Set<String> dict) {
        int length = 0;
        for (String word : dict) {
            length = Math.max(length, word.length());
        }
        return length;
    }
}



/*
Attemp2, Thought:
Use boolena to denote rst[i]: s[0,i-1] can be break to match dict. For the ease to explain, let's consider rst[i+1] with actually string s[0,i];
How to calculate rst[i+1]? 
	As long as there is at least 1 way to break s[0, i], that would work. so do a for loop to check on string s[0, i]:
	For each i, use another index j, j = 0 ~ i. If rst[j] works and s[j,i+1] is in dict, that makes rst[i+1] = true.

Correct: however excceeds time limit at 97% correct
*/

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
    	if (s == null || dict.contains(s)) {
    		return true;
    	}
    	boolean[] rst = new boolean[s.length() + 1];
    	rst[0] = true;
    	for (int i = 0; i < s.length(); i++) {
    		for (int j = 0; j <= i; j++) {
    			if (rst[j] && dict.contains(s.substring(j, i + 1))) {
    				rst[i + 1] = true;
    				break;
    			}
    		}
    	}
    	return rst[s.length()];
    }
}




/*
Thoughts1:
Is this: select one or more words from dict, to construct the given string.
Create DP[i][j] based on dict that says: combine i number of dict strings and j number of dict strings, do we have a combined string that contains the target?

However, this seems confusing and over-complex. We only have 1 set of variables: dict, so maybe it's now wise to create 2D DP[][].
*/

---
**303. [Word Ladder II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Ladder II.java)**Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
Have you met this question in a real interview? Yes
Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note
All words have the same length.
All words contain only lowercase alphabetic characters.
Tags Expand 
Backtracking Depth First Search Breadth First Search

Attempt1 is by me: however it exceeds time/memory limit.
Some other good sources can be found online:
//http://www.jiuzhang.com/solutions/word-ladder-ii/
//http://www.cnblogs.com/shawnhue/archive/2013/06/05/leetcode_126.html
Adjacency List, Prefix ... etc. Let's look at them one after another. First get it through with a NineChapter solution
*/

//Attempt2: Use Nine Chapter solution, BFS + DFS. It works, very nicely, using backtracking.
/*
BFS:
1. For all mutations in dict, create pastMap: all possible mutations that can turn into each particular str in dict.
2. For all mutations in dict, create distance: distance to start point.
DFS:
3. Find minimum path by checking distance different of just 1. Use a List<String> to do DFS

Note: 
Map uses containsKey. Set uses contains
In DFS, add new copy: new ArrayList<String>(path)
BFS: queue, while loop
DFS: recursion, with a structure to go deeper, remember to add/remove element when passing alone
*/
public class Solution {
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> rst = new ArrayList<List<String>>();
        Map<String, ArrayList<String>> pastMap = new HashMap<String, ArrayList<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();
        
        //Initiate the variables
        dict.add(start);
        dict.add(end);
        queue.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            pastMap.put(s, new ArrayList<String>());
        }

        //BFS
        BFS(start, end, distance, pastMap, dict, queue);

        //DFS
        ArrayList<String> path = new ArrayList<String>();
        DFS(start, end, distance, pastMap, path, rst);

        return rst;
    }
    //BFS to populate map and distance:
    //Distance: distance from each str in dict, to the starting point.
    //Map: all possible ways to mutate into each str in dict.
    public void BFS(String start, String end, Map<String, Integer> distance, Map<String, ArrayList<String>> pastMap, Set<String> dict, Queue<String> queue) {
        while(!queue.isEmpty()) {
            String str = queue.poll();
            List<String> list = expand(str, dict);

            for (String s : list) {
                pastMap.get(s).add(str);
                if (!distance.containsKey(s)) {
                    distance.put(s, distance.get(str) + 1);
                    queue.offer(s);
                }
            }
        }
    }
    //DFS on the map, where map is the all possible ways to mutate into a particular str. Backtracking from end to start
    public void DFS(String start, String str, Map<String, Integer> distance, Map<String, ArrayList<String>> pastMap, ArrayList<String> path, List<List<String>> rst) {
        path.add(str);
        if (str.equals(start)) {
            Collections.reverse(path);
            rst.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {//next step, trace 1 step towards start
            for (String s : pastMap.get(str)) {//All previous-mutation options that we have with str:
                if (distance.containsKey(s) && distance.get(str) == distance.get(s) + 1) {//Only pick those that's 1 step away: keep minimum steps for optimal solution
                    DFS(start, s, distance, pastMap, path, rst);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    //Populate all possible mutations for particular str, skipping the case that mutates back to itself.
    public ArrayList<String> expand(String str, Set<String> dict) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {//Alternate each letter position
            for (int j = 0; j < 26; j++) {//Alter 26 letters
                if (str.charAt(i) != (char)('a' + j)) {
                    String newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
                    if (dict.contains(newStr)) {
                        list.add(newStr);
                    }
                }
            }
        }
        return list;
    }
}



//Attempt1: probably works, however:
//Testing against input: "qa", "sq", ["si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"]
//0. Could be backtrackList exceed memory limit.
//1. If use HashSet<String> set to check if particular sequence exist, then exceed memory
//2. If use StringBuffer strCheck to check if particular sequence exist, then exceed time limit.
//It looks like we'd use DFS for final results.
public class Solution {
	private Queue<String> q = new LinkedList<String>();
	private Queue<ArrayList<String>> backtrackList = new LinkedList<ArrayList<String>>();
    private Set<String> dict;
    private String end;
    private int level = 1;
    private int len = Integer.MAX_VALUE;
    private List<List<String>> rst = new ArrayList<List<String>>();

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null || start.length() != end.length()) {
    		return rst;
    	}
    	this.dict = dict;
    	this.end = end;
    	ArrayList<String> head = new ArrayList<String>();
    	head.add(start);
    	q.offer(start);
    	backtrackList.offer(head);
    	while(!q.isEmpty()) {//BFS
    		int size = q.size();//Fix size
    		level++;
    		for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
	    		String str = q.poll();
	    		ArrayList<String> list = backtrackList.poll();
	    		validateMutations(str, list);
	    	}//END FOR K
    	}//END WHILE

    	List<List<String>> minRst = new ArrayList<List<String>>();
    	for (int i = 0; i < rst.size(); i++) {
    		if (rst.get(i).size() == len) {
    			minRst.add(rst.get(i));
    		}
    	}
    	return minRst;
    }


    public void validateMutations(String str, ArrayList<String> list) {
    	if (list.size() > len) {//No need to digger further if list is already greater than min length
    		return;
    	}
    	for (int i = 0; i < str.length(); i++) {//Alternate each letter position
			for (int j = 0; j < 26; j++) {//Alter 26 letters
                if (str.charAt(i) == (char)('a' + j)) {
                    continue;
                }
				String newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);

				ArrayList<String> temp = (ArrayList<String>)list.clone();
				temp.add(newStr);
				if (dict.contains(newStr)) {
					if (newStr.equals(end)) {//Found end
						len = Math.min(len, level);
						rst.add(temp);
					} else {
						q.offer(newStr);
						backtrackList.offer(temp);
					}
				}
			}//END FOR J
		}//END FOR I
    }
}



//Solution from NineChapter, commented:

/*
public class Solution {
    public List<List<String>> findLadders(String start, String end,Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
 
        bfs(map, distance, start, end, dict);
        //Now at this step, we have: 
        //a distance map of all mutated string from start, 
        //a map of mutation and its list of 'pre-mutation' string
        //dict: includes start and end
        List<String> path = new ArrayList<String>();
        
        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }
    //crt: is not necessarily the 'end', since this is a recursive method
    //crt at first is the 'end' string, then it's switching to other strings inorder to finally matches 'start'
    void dfs(List<List<String>> ladders, List<String> path, String crt,
            String start, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {//Now, finally if the crt makes it to start and equals to start, we found a match.
            Collections.reverse(path);//We had a reversed path
            ladders.add(new ArrayList<String>(path));//add
            Collections.reverse(path);//need to reverse it back, becase we need 'path' for more recursive calls.
        } else {
            for (String next : map.get(crt)) {//Find all possible tranformations/mutations that can turn itself into crt: we have a ArrayList of candidates (pre-mutated strings)
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { //if that mutation is just 1 step different, that's good, which means these mutation takes minimum of 1 step to happen. Note: we are comparing the distance to start point.
                    dfs(ladders, path, next, start, distance, map);//If that's the case, pass varibles to next level: use new path (with crt added), and use the 'next' string (which is 1 step closer to start) for next level of searching.
                }
            }           
        }
        path.remove(path.size() - 1);//remove that ending crt, since 'path' is shared in recursive methods, need to keep it cleaned.
    }
//map: each string in the dict (including start, end) represents a key, and the value is a ArrayList of string.
    void bfs(Map<String, List<String>> map, Map<String, Integer> distance, String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);//Distance: key: str, value: distance value from start.
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();//Get head of queue, the item currently we are looking at. Called X.

            List<String> nextList = expand(crt, dict);//generate all possible mutations (must exist in dict)
            for (String next : nextList) {//For all mutations
                map.get(next).add(crt);//append X to end of all of the mutated string (this will become a reverse order). This creates a path of mutation
                if (!distance.containsKey(next)) {//If that mutated string never occured:
                    distance.put(next, distance.get(crt) + 1);//add distance to this mutation. This is fixed and will never change, btw. This becomes a list of all mutations and distance from start.
                    q.offer(next);//Add this mutation to queue.
                }
            }
        }
    }
//all possible mutations based on 1 str polled from the queue.
    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }
        return expansion;
    }
}


*/

---
**304. [Word Ladder.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Ladder.java)**Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
Tags Expand 
Breadth First Search

Thoughts:
Use the dict (check if mutation exist in dict) as base to create a directed graph, use BFS to find shortest path.

Note:
Be careful with queue size when trying to do for loop on it. Use a pre-fixed size = q.size(), in case queue's size changes during for loop.
*/

//Solution1: nested for loop
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
    	if (start == null || end == null || dict == null || start.length() != end.length()) {
    		return 0;
    	}
    	Queue<String> q = new LinkedList<String>();
    	HashSet<String> set = new HashSet<String>();
    	int level = 1;
    	int len = Integer.MAX_VALUE;
    	q.offer(start);
    	set.add(start);
    	while(!q.isEmpty()) {//BFS
    		int size = q.size();//Fix size
    		level++;
    		for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
	    		String str = q.poll();
	    		for (int i = 0; i < str.length(); i++) {//Alternate each letter position
	    			for (int j = 0; j < 26; j++) {//Alter 26 letters
	    				String newStr;
	    				if (i == 0 && str.length() == 1) {
	    				    newStr = "" + (char)('a' + j);
	    				}
	    				else if (i == str.length() - 1) {
	    					newStr = str.substring(0, i) + (char)('a' + j);
	    				} else {
	 						newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
	    				}
	    				if (!set.contains(newStr) && dict.contains(newStr)) {
	    					if (newStr.equals(end)) {//Found end
	    						len = Math.min(len, level);
	    					} else {
	    						set.add(newStr);
	    						q.offer(newStr);
	    					}
	    				}
	    			}//END FOR J
	    		}//END FOR I
	    	}//END FOR K
    	}//END WHILE
    	return len;
    }
}



//Solution2: separate methods, and hope to make Word Ladder II problem easier
public class Solution {
	private Queue<String> q = new LinkedList<String>();
    private HashSet<String> set = new HashSet<String>();
    private Set<String> dict;
    private String end;
    private int level = 1;
    private int len = Integer.MAX_VALUE;

    public int ladderLength(String start, String end, Set<String> dict) {
    	if (start == null || end == null || dict == null || start.length() != end.length()) {
    		return 0;
    	}
    	this.dict = dict;
    	this.end = end;
    	q.offer(start);
    	set.add(start);
    	while(!q.isEmpty()) {//BFS
    		int size = q.size();//Fix size
    		level++;
    		for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
	    		String str = q.poll();
	    		validateMutations(str);
	    	}//END FOR K
    	}//END WHILE
    	return len;
    }

    public void validateMutations(String str) {
    	for (int i = 0; i < str.length(); i++) {//Alternate each letter position
			for (int j = 0; j < 26; j++) {//Alter 26 letters
				String newStr;
				if (i == 0 && str.length() == 1) {
				    newStr = "" + (char)('a' + j);
				}
				else if (i == str.length() - 1) {
					newStr = str.substring(0, i) + (char)('a' + j);
				} else {
						newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
				}
				if (!set.contains(newStr) && dict.contains(newStr)) {
					if (newStr.equals(end)) {//Found end
						len = Math.min(len, level);
					} else {
						set.add(newStr);
						q.offer(newStr);
					}
				}
			}//END FOR J
		}//END FOR I
    }
}

---
**305. [Word Pattern.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Pattern.java)**但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。

---
**306. [Word Search II.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Search II.java)**Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 

Example
Given matrix:
doaf
agai
dcan
and dictionary:
{"dog", "dad", "dgdg", "can", "again"}

return {"dog", "dad", "can", "again"}


dog:
doaf
agai
dcan

dad:
doaf
agai
dcan

can:
doaf
agai
dcan

again:
doaf
agai
dcan

Challenge
Using trie to implement your algorithm.

Tags Expand 
LintCode Copyright Trie


*/
//Well, the attempt2 uses Trie, but didn't really use find(). It just uses insert() to create Trie, and mainly
//used the end point where string is finished.

/*
Attemp2: Trie solution.
http://www.jiuzhang.com/solutions/word-search-ii/

Here is how Tire works, from my understanding: it creates a new data strucutre that maps all words into a trie structure. Then, based on the given 2D matrix of letters, using each individual letter as starting point, and grab all possible combinations, then save the possibilities into final resuts.

The magic 'checking point' is the use of 'isString' of trie.

Note: should also be careful with marking board[x][y] = '#', which helps to prevent re-use used letters.

About TrieTree:
Each string obtains a particular/unique path.
Different strings could share same prefix path, but at certain index when the two strings are differentiating, they will start the following path on different TrieNode, which leads to completely separate subtree path.
At end of the tree, a string will have isString== true and the real string value stored.

That is, 
insert: for all letter, make sure they are all created as nodes and linked together by using subtree.
find: for loop to iterate through subtrees of nodes, then return target on last index letter.


In the search:
node.subtree.get(current).isString: this determines if a string exists or not.
*/
public class Solution {
   	class TrieNode {
   		boolean isString;
   		String s;
   		HashMap<Character, TrieNode> subtree;
   		public TrieNode() {
   			this.isString = false;
   			this.s = "";
   			this.subtree = new HashMap<Character, TrieNode>();
   		}
   	}

   	class TrieTree {
   		TrieNode node;
   		public TrieTree(TrieNode n) {
   			node = n;
   		}
   		public void insert(String s) {
   			TrieNode curr = node;
   			for (int i = 0; i < s.length(); i++) {
   				if (!curr.subtree.containsKey(s.charAt(i))) {
   					curr.subtree.put(s.charAt(i), new TrieNode());
   				}
   				curr = curr.subtree.get(s.charAt(i));
   			}
   			curr.isString = true;
   			curr.s = s;
   		}
   		public boolean find(String s) {
   			TrieNode curr = node;
   			for (int i = 0; i < s.length(); i++) {
   				if (!curr.subtree.containsKey(s.charAt(i))) {
   					return false;
   				}
   				curr = curr.subtree.get(s.charAt(i));
   			}
   			return curr.isString;
   		}
   	}

   	public void search(char[][] board, ArrayList<String> rst, int i, int j, TrieNode node) {
   		if (node.isString) {
   			if(!rst.contains(node.s)) {
   				rst.add(node.s);
   			}
   		}
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || node == null) {
			return;
		}
		if (node.subtree.containsKey(board[i][j])) {
			char temp = board[i][j];
			TrieNode next = node.subtree.get(board[i][j]);
			board[i][j] = '#';//Mark it, prevent going back-forth
			search(board, rst, i, j + 1, next);
			search(board, rst, i, j - 1, next);
			search(board, rst, i - 1, j, next);
			search(board, rst, i + 1, j, next);
			board[i][j] = temp;
		}
   		
   	}
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    	ArrayList<String> rst = new ArrayList<String>();
    	if (board == null || words == null || words.size() == 0) {
    		return rst;
    	}
    	TrieTree tree = new TrieTree(new TrieNode());
    	for (String word : words) {
    		tree.insert(word);
    	}

    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[i].length; j++) {
    			search(board, rst, i, j, tree.node);
    		}
    	}

    	return rst;
    }
}





/*
Attempt1:
Thoughts:
Use word search1, and do for loop on the words... and that works .........Well, that's not the Trie solution
*/

public class Solution {
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    	ArrayList<String> rst = new ArrayList<String>();
    	if (board == null || words == null || words.size() == 0) {
    		return rst;
    	}
    	for (String word : words) {
    		if (exist(board, word)) {
    			rst.add(word);
    		}
    	}
    	return rst;
    }
    //The following are from Word Search I
     public boolean exist(char[][] board, String word) {
    	if (word == null || word.length() == 0) {
    		return true;
    	}
    	if (board == null) {
    		return false;
    	}
    	
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j] == word.charAt(0)) {
    				boolean rst = search(board, word, i, j, 0);
    				if (rst) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

    public boolean search(char[][] board, String word, int i, int j, int start) {
    	if (start == word.length()) {
    		return true;
    	}
    	if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) {
    		return false;
    	}
    	board[i][j] = '#';
    	boolean rst = search(board, word, i, j - 1, start + 1)
    	|| search(board, word, i, j + 1, start + 1)
    	|| search(board, word, i + 1, j, start + 1)
     	|| search(board, word, i - 1, j, start + 1);   
     	board[i][j] = word.charAt(start);
    	return rst;
    }
}

---
**307. [Word Search.java](https://github.com/shawnfan/LintCode/blob/master/Java/Word Search.java)**Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Have you met this question in a real interview? Yes
Example
Given board =

[
  "ABCE",
  "SFCS",
  "ADEE"
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

Tags Expand 
Backtracking

Thoughts:
1. find starting index i,j
2. Start divde&&conqure: each iteration.
	In each interation: make sure board[i][j] == word.charAt(currentCheckingIndex); If not match, return false and terminate the interation
3. Therefore, if (start) == word.length(), that means all previous-start indexes are matched, so we have a match; return true in this case.


Note: if can use boolean || boolean || boolean, use it and save processing power: once one boolean works, it won't process the rest || elements
*/


public class Solution {
    public boolean exist(char[][] board, String word) {
    	if (word == null || word.length() == 0) {
    		return true;
    	}
    	if (board == null) {
    		return false;
    	}
    	
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j] == word.charAt(0)) {
    				boolean rst = search(board, word, i, j, 0);
    				if (rst) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

    public boolean search(char[][] board, String word, int i, int j, int start) {
    	if (start == word.length()) {
    		return true;
    	}
    	if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) {
    		return false;
    	}
    	board[i][j] = '#';
    	boolean rst = search(board, word, i, j - 1, start + 1)
    	|| search(board, word, i, j + 1, start + 1)
    	|| search(board, word, i + 1, j, start + 1)
     	|| search(board, word, i - 1, j, start + 1);   
     	board[i][j] = word.charAt(start);
    	return rst;
    }
}

---
**308. [Zigzag Iterator.java](https://github.com/shawnfan/LintCode/blob/master/Java/Zigzag Iterator.java)**每次next(), 相应的list的头拿下来就好。
然后就跑圈呗，每次刷一个list头。不难。只要把几个variable维护清楚就行。

---
