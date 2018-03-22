 
 
 
## Divide and Conquer (5)
**0. [Burst Balloons.java](https://github.com/awangdev/LintCode/blob/master/Java/Burst%20Balloons.java)**      Level: Hard
      

Range DP.
因为数组规律会变, 所以很难找'第一个burst的球'. 反之, 想哪一个是最后burst?
最后burst的那个变成一堵墙: 分开两边, 分开考虑, 加法原理; 最后再把中间的加上.

Range DP 三把斧:
1. 中间劈开
2. 砍断首或尾
3. Range区间作为iteration的根本

Note: print the process. use pi[i][j] and print recursively.
Print k, using pi[i][j]: max value taken at k


其实会做之后挺好想的一个DP。
dp[i][j] =  balloons i~j 之间的sum. 然后找哪个点开始burst? 设为x。
For loop 所有的点作为x， 去burst。
每次burst都切成了三份：左边可以recusive 求左边剩下的部分的最大值 + 中间3项相乘 + 右边递归下去求最大值。


这个是momorization, 而不纯是DP
因为recursive了，其实还是搜索，但是memorize了求过的值，节省了Processing



---
**1. [Majority Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Element.java)**      Level: Easy
      

方法1: Vote 计数, vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

方法2: HashMap count occurance. Time, Space: O(n)

方法3: Bit manipulation. 还没有做.

Related Problems:
Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。



---
**2. [Expression Expand.java](https://github.com/awangdev/LintCode/blob/master/Java/Expression%20Expand.java)**      Level: Medium
      


#### DFS
- 与Stack时需要考虑的一些function类似. 特别之处: **检查[ ]的结尾**
- 因为DFS时候, 括号里的substring会被保留着进入下一个level, 所以我们在base level要keep track of substring.
- 用int paren 来track 括号的开合, 当paren再次==0的时候 找到closure ']'

#### Stack
- Stack存 [ ] 里面的内容, detect 括号开头结尾: 结尾时process inner string
- 有很多需要注意的细节才能做对:
- Stack<Object> 也可以用, 每个地方要注意 cast. 存进去的需要是Object: String, Integer
- 几个 type check: instanceof String, Character.isDigit(x), Integer.valueOf(int num)
- 出结果时候, 不能轻易 sb.reverse().toString(): sb.reverse() 翻转了整个连在一起的string, 错.
- 用另一个Stack<String>作为buffer, 先把stack里面的内容倒出来 (pure), 但是每个item里面顺序不变.
- 最后再从buffer里面倒进StringBuffer.




---
**3. [Find Peak Element II.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Peak%20Element%20II.java)**      Level: Hard
      

Should break down by mid row. More details:
http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

#### 方法1
##### 基本原理
我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
根据这个点, 再往剩下两个方向移动

1. 在中间的一行, 找到peak所在的y.

2. 在中间的一列, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)

3. 猜一猜 (x,y) 是不是 peak, 如果不是, 像更高的位置移动一格

4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找

##### 剪枝/切分象限
每次只是找到一个row/col里面的peak而已!

找到这个点, 就等于把board切成了两半.

然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.

切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS

##### 时间复杂度
每一个level都减一半
T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### 方法2
Binary Search
还没有写 : )
O(nLogN)



---
**4. [Building Outline.java](https://github.com/awangdev/LintCode/blob/master/Java/Building%20Outline.java)**      Level: Review
      

又叫做skyline. 用Sweep Line做的O(nLogN), 但是貌似还有很多做法: segement tree, hashheap, treeSet?

#### 方法1: Sweep Line, Time O(nLogN), Space O(n)
original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt

sweep line:
- 把所有点分出来， 每个点有index x, 再加上一个height.         
- 在这个list上排序，根据index和height. 注意用负数标记building start point height, 这样保证start在end 之前
- 用负数的height标记start: 在priority queue里面同一个x-pos比较 startPoint.height, endPoint.height 的时候, 因为end height是整数, 所以compare时会自动把start point放在end point前面
- 当然了, 如果两个 start point比较, 第二个point的负数超大的话(也就是height很高), 就会顺理compare return正数, 成章形成倒位
- 在processs时候用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height . 遇到peek,就是一个合理的解    
- heightQueue里面加一个0, 用来在结尾的时候做closure

#### 方法2: Segment Tree
REVIEW

Binary Indexed Tree?

HashHeap?



---
