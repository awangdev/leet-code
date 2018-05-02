 
 
 
## Sort (8)
**0. [Wiggle Sort.java](https://github.com/awangdev/LintCode/blob/master/Java/Wiggle%20Sort.java)**      Level: Medium
      

方法1:
排序, nLog(n). 然后把直线上坡变成层叠山峰, 需要每隔几个(题目中是每隔2位)就做个swap 造成高低不平.
Note: 每隔山峰之间是相互没有关系的, 所以每次只要操心 [i], [i-1]两个位置就好了.

方法2:
O(n)
看好奇数偶数位的规律, 然后根据题目给出的规律, 跑一遍, 每次只关注两个位置: 把不合适的[i], [i-1]调换位置就好了.

方法3:
跟法2一样, 只是更巧妙一点罢了:
第一遍想太多. 其实做一个fall-through就能把问题解决，原因是因为：
这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！
这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。



---

**1. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---

**2. [Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Anagram.java)**      Level: Easy
      

HashMap



---

**3. [Meeting Rooms.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms.java)**      Level: Easy
      

- 注意接头点要考虑所有开会结会的情况，不要恰巧漏掉相接的点
- 开会的是超人。瞬间移动接上下一个会议

#### 方法1:
找是否有overlap. priorityQueue 按照start time排序好以后, 比较current和peek: current.end > peek.start?

#### 方法2: Sweep line
- class Point{pos, flag}, PriorityQueue排序。计算count
- 跟 Number of Airplanes in the Sky 是一个类型的题目





---

**4. [Number of Airplane in the sky.java](https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java)**      Level: Medium
      

#### Sweep Line
- 把Interval拆分成数轴上的Point 
- 起飞mark 1   
- 降落mark -1     
- 用PriorityQueue排序， loop through queue, 计算(起飞+降落)值可能有的max。

#### 注意
- 同时起飞和降落，就是 1 - 1 = 0. 所以在while loop里面有第二个while loop，    
- 当坐标x重合时，在这里做完所有x点的加减，然后再比较 max。     
- 这避免了错误多count，或者少count



---

**5. [Meeting Rooms II.java](https://github.com/awangdev/LintCode/blob/master/Java/Meeting%20Rooms%20II.java)**      Level: Medium
      

给一串数字pair, 代表会议的开始/结束时间. 找同时又多少个会议发生(需要多少件房间)

#### 方法1
- PriorityQueue + 一个Class来解决.Ｏ(nlogn)
- 跟 Number of Airpline in the sky是同一道题

#### 方法2: 尝试了一下用一个sorted Array + HashMap
也还行，但是handle edge的时候,HashMap 要小心，因为相同时间start和end的map key 就会重复了。



---

**6. [Insertion Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Insertion%20Sort%20List.java)**      Level: Medium
      

input一串数字, 需要出sorted output. 每次insert一个数字时, 都要放到正确的sorted的位置

每次insertion的时候, 都从input里面减掉这个数字

#### Linked List
- 把list里面每个元素都拿出来，scan and insert一遍
- Time O(n^2), worst case, 每次放入n个数字里面的element, 刚好都是最大的
- 所以每次要traverse n nodes, 然后走n次

##### 思考方法
- 如果已经有个sorted list, insert一个element进去。怎么做？
- while 里面每个元素都小于 curr, keep going
- 一旦curr在某个点小了，加进去当下这个空隙。



---

**7. [Largest Number.java](https://github.com/awangdev/LintCode/blob/master/Java/Largest%20Number.java)**      Level: Medium
      

给一串数字, 非负数, 把所有数字串联起来, 组成最大数字.

因为结果很大, 所以用string表示 

#### Sort, Comparator
- 考虑 more significant spot 应该拿到更大的值
- 如果sort number,  comparator 会比较难写: 每个digit的weight不同, 要分别讨论个位数和多位数.
- goal: 让较大的组合数排在前面, 让较小的组合数排在后面
- 不如: 组合两种情况, 用String比较一下大小 (也可以用 integer来比较组合数, 但是为保险不超Integer.MAX_VALUE, 这里比较String)
- String.compareTo() 是按照 lexicographically, 字典顺序排列的
- 利用compareTo, 来倒序排列 string, 刚好就得到我们要的结果.
- O(nlogn), 排序



---

