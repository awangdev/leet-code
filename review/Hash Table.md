 
 
 
## Hash Table (55)
**0. [Fraction to Recurring Decimal.java](https://github.com/awangdev/LintCode/blob/master/Java/Fraction%20to%20Recurring%20Decimal.java)**      Level: Medium      Tags: [Hash Table, Math]
      

TODO: no need of hashMap, just use set to store the existing

不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。


---

**1. [Jewels and Stones.java](https://github.com/awangdev/LintCode/blob/master/Java/Jewels%20and%20Stones.java)**      Level: Easy      Tags: [Hash Table]
      
1524017454

给J 和 S两个string. J里的character是unique 的珠宝, S 里面的character包含珠宝和石头. 找S里面有多少珠宝

#### Basic HashSet



---

**2. [LFU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LFU%20Cache.java)**      Level: Hard      Tags: [Design, Hash Table]
      

#### Hash Table
- 具体看thoughts, 几种不同的方式使用map
- `regular object map`: map of <key, item>, where `item : {int val; int count}`
- Use a Map<frequency count, doubly-linked node> to track the frequency
- Track constant capacity, and minimum frequency
- Every get(): update all frequency map as well
- Every put(): update all frequency map as well, with optional removal (if over capacity)

- Original post: http://www.cnblogs.com/grandyang/p/6258459.html
- TODO: one doubly linked list might be good enough to replace below:
- `frequency list map`: map of <frequency count, List<item>>, where the list preserves `recency`
- `item location in frequency map`: map of <key, int location index in list>:
- index relative to the item in a particular list, not tracking which list here



---

**3. [Majority Number III.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20III.java)**      Level: Medium      Tags: [Hash Table, Linked List]
      

TODO: 
1. hash table solution not passing
2. Find O(n) solution

#### Hash Table
- 与其他Majority Number一样。
- 出现次数多余1/k，就要分成k份count occurance.用HashMap。 存在的+1；不存在map里的，分情况:    
- 若map.size() == k,说明candidate都满了，要在map里把所有现存的都-1；
- 若map.size() < k, 说明该加新candidate，那么map.put(xxx, 1);
- 最后在HashMap里找出所留下的occurance最大的那个数。
- 但这样的worst case是 O(nk)



---

**4. [Find Anagram Mappings.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20Anagram%20Mappings.java)**      Level: Easy      Tags: [Hash Table]
      

比较简单, 用HashMap 存index list. 最后再遍历一遍数组A, 列举出所有元素.
O(n)



---

**5. [Island Perimeter.java](https://github.com/awangdev/LintCode/blob/master/Java/Island%20Perimeter.java)**      Level: Easy      Tags: [Hash Table]
      

最简单的方法: 每个格子4个墙;每个shared的墙要-2 (墙是两面, -1 * 2)
最后合计结果就好.

不必想太多用HashMap做.但是也可以思考一下:
- 把每个block相连的block全部存在以当下block为key的list里面. 那么这里需要把2D坐标, 转化成一个index.
- 最后得到的map, 所有的key-value应该都有value-key的反向mapping, 那么久可以消除干净, 每一次消除就是一个shared wall.
- 一点点optimization: DFS去找所有的island, 如果island的图非常大, 而island本身不打,那么适合optimize.
  但是整体代码过于复杂. 不建议写.




---

**6. [First Unique Character in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/First%20Unique%20Character%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table, String]
      

方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)



---

**7. [Encode and Decode TinyURL.java](https://github.com/awangdev/LintCode/blob/master/Java/Encode%20and%20Decode%20TinyURL.java)**      Level: Medium      Tags: [Hash Table, Math]
      

其实想到了切入点, 是个可难可简单的题目. 这里的encode就是想办法把url存起来, 然后给个 key.
那么具体怎么做这个key, 简单就可以用一个map, 然后counting. 复杂一点就可以做random letter/number组成key.



---

**8. [4 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/4%20Sum.java)**      Level: Medium      Tags: [Hash Table]
      

#### Based on 2sum
- 1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
- 2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
- 3. 再尝试从所有i+1后面,找合适的2nd pair。   
- Time: O(n^2 * x), where x = # of candidates, still slow
- 可以用HashSet<List>, 可以直接比较list里面每一个元素, 保证set不重复.
- Previous Notes: 在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。
- 参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/    

#### Based on 3Sum
- 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)



---

**9. [Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

方法1:
用HashMap: 存一个nums1, 再拿nums2 check against map. 时间/空间:O(n)

方法2:
Binary search? 需要array sorted. 否则时间O(nlogn)不值得.
[没做完, 有错]



---

**10. [Construct Binary Tree from Inorder and Preorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Construct%20Binary%20Tree%20from%20Inorder%20and%20Preorder%20Traversal.java)**      Level: Medium      Tags: [Array, DFS, Divide and Conquer, Hash Table, Tree]
      

如题

#### DFS
- 和Construct from Inorder && Postorder 想法一样。
- 写出Preorder和Inorder的字母例子，发现Preorder的开头总是这Level的root。依此写helper,注意处理index。
- 跟Convert Sorted Array to Binary Tree类似, 找到对应的index, 然后:
- node.left = dfs(...), node.right = dfs(...)
- Divide and Conquer
- optimize on finding mid node: given value, find mid of inorder. Instead of searching linearly, just store map <value -> index>, O(1)
- sapce: O(n), time: O(n) access



---

**11. [Valid Anagram.java](https://github.com/awangdev/LintCode/blob/master/Java/Valid%20Anagram.java)**      Level: Easy      Tags: [Hash Table, Sort]
      

HashMap



---

**12. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium      Tags: [Hash Table, String, Two Pointers]
      

方法1:
Two Pointers
双指针: 从start开始遍历, 但是第一步是while loop来推进end; 直到推不动end, 然后start++
巧妙点: 因为end是外围variable, 在start的loop上, end不会重置;[star ~ end] 中间不需要重复计算.
最终可以O(n);

Previous verison of two pointers:
用两个pointer, head和i.
注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯

方法2:
   HashMap<Char, Integer>: <character, last occurance index>
   一旦有重复, rest map.
   没有重复时候, 不断map.put(), 然后求max值

问题: 每次reset map之后就开始从新从一个最早的index计算, 最坏情况是O(n^2):
'abcdef....xyza'




---

**13. [Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Window%20Substring.java)**      Level: Hard      Tags: [Hash Table, String, Two Pointers]
      

基本思想: 用个char[]存string的frequency. 然后2pointer, end走到底, 不断validate.
符合的就process as result candidate.

HashMap的做法比char[]写起来要复杂一点, 但是更generic



---

**14. [Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Medium      Tags: [Hash Table, String]
      

大清洗 O(nk)   
map.size一旦>k，要把longest string最开头（marked by pointer:start）的那个char抹掉    
一旦某一个char要被清除，所以在这个char 的1st and last appearance之间的char都要被清洗from map




---

**15. [Palindrome Pairs.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Pairs.java)**      Level: Hard      Tags: [Hash Table, String, Trie]
      

Obvious的做法是全部试一遍, 判断, 变成 O(n^2) * O(m) = O(mn^2). O(m): isPalindrome() time.

当然不行了, 那就看是O(nlogN), 还是O(n)?

#### 方法1: Hash Table + Palindrome的性质. 复合型.
O(mn)

##### 思路
- 每一个word, 都可以拆分成 front + mid + end. 如果这个word + 其他word可以组成palindrome,那就是说
- 砍掉 (mid+end), front.reverse() 应该存在 words[] 里面.
- 砍掉 (front+mid), end.reverse() 应该存在 words[] 里面.
- 我们用HashMap存所有的<word, index>, 然后reverse, 找配对就好.

##### Corner case
- 如果有 empty string "", 那么它跟任何一个palindrome word, 都可以配对, 并且根据位置前后变换, 凑成2份 distinct indexes.
- 这样就有了那个 `if (reverseEnd.equals("")) {...}` 的logic.
- 注意: 虽然在处理砍头/砍尾的两个 for loop里面都在根据 empty string 重复记录, 
  但因为 "" 自己本身不能作为起点, 所以overall只会在被其他palindrome配对时记录一次.


#### 方法2: Trie
还要做一下那.



---

**16. [Maximal Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximal%20Rectangle.java)**      Level: Hard      Tags: [Array, DP, Hash Table, Stack]
      

#### 方法1: monotonous stack
分解开来, 其实是'Largest Rectangle in Histogram', 只不过这里要自己model heights.
一个2D array里面的rectangle, 最终也是用height * width做出来的.
巧妙在于, 把每一行当做底边, 算出这个底边, 到顶部的height: 
- 如果底边上的一个value==0, 那么算作没有height(以这个底边做rectangle, value==0的位置是空中楼阁, 不能用)
- 如果底边上的value==1, 那么就把上面的height加下来, 做成histogram

如果看具体实例, 有些row似乎是白算的, 但是没有办法, 这是一个搜索的过程, 最终会比较出最优解.

#### 方法2: DP
Coordinate DP?



---

**17. [Binary Tree Inorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/Binary%20Tree%20Inorder%20Traversal.java)**      Level: Easy      Tags: [Hash Table, Stack, Tree]
      

Inorder traverse Binary Tree

#### Recursive
- 在自己的基础上recursive, 不用helper function
- Divide and Conquer, with helper(dfs) method
- O(n) time, no extra space

#### Iterative: Stack
- Add left nodes all the way   
- Print curr   
- Move to right, add right if possible
- O(n) time, O(h) space
  
注意stack.pop()在加完left-most child 的后，一定要curr = curr.right.

若不右移, 很可能发生窘境:
curr下一轮还是去找自己的left-most child，不断重复curr and curr.left, 会infinite loop, 永远在左边上下上下。

#### HashMap
? How?



---

**18. [Contains Duplicate.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate.java)**      Level: Easy      Tags: [Array, Hash Table]
      

无序数组, 找是否有重复element, return true/false.

#### HashSet
- No brain: HashSet.
- Time O(n), Space O(n)

#### Sort, Binary Search
- Arrays.sort(x): Time O(nLogN), Space O(1)
- 排序后, 重复数会排在一起, 然后 binary search



---

**19. [Contains Duplicate II.java](https://github.com/awangdev/LintCode/blob/master/Java/Contains%20Duplicate%20II.java)**      Level: Easy      Tags: [Array, Hash Table]
      

Unsorted array, 找出是否有duplicate elemenets: 必要条件是, 这两个element的index i,j 的大小最多相差k.

#### HashSet
- 很巧妙地根据k range地条件, 把HashSet里面的值控制在[i - k, i]
- 每次不断地往set里面加新元素, 从set里减去末尾index的元素
- 而set.add(x)如果遇到重复, 会return false.
- 一旦在这个length k 的 range里面, 有重复, 就符合条件. 
- Time O(n)

#### HashTable<value, List of duplicates>
- 记录每个element value的index in the list
- 一旦有重复element重复, 就把整个list of indexes 端出来, 查看有没有符合条件的: (index - i) <= k
- Time O(nm), m = # of duplicates

#### 这两种做法的区别很有艺术感觉
- 方法1是限定选拔的candidate, 不合格就去掉, 那么一旦有符合条件的(duplicates), 那么一定中, 剩下的就不看了.
- 方法2是把符合条件的index找出来, 集中处理, 但是所有candidate都会选出来
- 就好像招人一样: 一种是遇到好的就停止; 第二种是看过所有人, 从中选拔最好的. 显然第一种更快.




---

**20. [Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Easy      Tags: [Hash Table]
      

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### HashTable
- count character apperance 就想到hashtable
- 注意countS, countP 的技巧: 作比较只需要O(26)
- Overall timeO(n)
- 小心不要用一个int[] count 来技术 查0, 复杂度是O(n)



---

**21. [Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Anagrams.java)**      Level: Medium      Tags: [Hash Table, String]
      

给一串string, return list of list, 把anagram 放在一起.

#### Hash Table, key 是 character frequency
- 存anagram
- 用 character frequency 来做unique key
- 用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
- 因为每个位子上的frequency的变化，就能构建一个unique的string
- O(nk), k = max word length


#### Hash Table, key 是 sorted string
- 和check anagram 想法一样：转化并sort char array，用来作为key。
- 把所有anagram 存在一起。注意结尾Collections.sort().
- O(NKlog(K)), N = string[] length, k = longest word length    




---

**22. [Count Primes.java](https://github.com/awangdev/LintCode/blob/master/Java/Count%20Primes.java)**      Level: Easy      Tags: [Hash Table, Math]
      

计数: 所有小于n的prime number.

#### prime number定义
- >=2的没有除自己和1以外公约数的数。   
- 还有另外一个定义方法: 这个n,有没有小于n的一个i, 而达到： i * i + # of i = n. 如果有，那就不是 prime   

#### Steps
- 一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.
- hash key: the number itself
- 然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.     
- 所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.     
- 最后，数一遍还剩下的true个数就好了   



---

**23. [Palindrome Permutation.java](https://github.com/awangdev/LintCode/blob/master/Java/Palindrome%20Permutation.java)**      Level: Easy      Tags: [Hash Table]
      

给String, 看permutation是否能是palindrome

#### Hash, or ASCII array
- count occurrance
- 只可以接受一个odd # appearance.
- 考虑所有 256 ASCII code, 如果还要拓展, 就用HashMap<Character, Integer>
- 注意, 不能assum lower case letter. 应该至少是所有ASCII code



---

**24. [Lowest Common Ancestor II.java](https://github.com/awangdev/LintCode/blob/master/Java/Lowest%20Common%20Ancestor%20II.java)**      Level: Easy      Tags: [Hash Table, Tree]
      

给一个Binary Tree root, 以及两个node A, B. 特点: node里面存了parent pointer. 找 lowest common ancestor


#### Hash Set
- 这个题有个奇葩的地方, 每个node还有一个parent, 所以可以自底向上.
- save visited in hashset. 第一个duplicate, 就是A B 的 lowest common ancestor

#### Save in lists
- 自底向上。利用parent往root方向返回
- 把所有parent存下来, 然后在两个list里面找最后一个 common node

#### 注意
- 无法从root去直接搜target node 而做成两个list. 因为根本不是Binary Search Tree！




---

**25. [Hash Function.java](https://github.com/awangdev/LintCode/blob/master/Java/Hash%20Function.java)**      Level: Easy      Tags: [Hash Table]
      

#### Hash Function
- 解释Hash怎么做. 
- Hash function例子：    
- hashcode("abcd") = (ascii(a) * 33^3 + ascii(b) * 33^2 + ascii(c) *33^1 + ascii(d)*33^0) % HASH_SIZE 
- 用到的参数比如: magic number 33, HASH_SIZE.

- Hash的意义是：给一个string key, 转换成数字，从而把size变得更小。    
- 真实的implementation还要处理collision, 可能需要design hash function 等等。


##### 每一步都%HASH_SIZE的原因
- hashRst = hashRst * 33 + (int)(key[i]);       
- hashRst = hashRst % HASH_SIZE;       
- 原因是，hashRst会变得太大，所以不能算完和 再 %...



---

**26. [LRU Cache.java](https://github.com/awangdev/LintCode/blob/master/Java/LRU%20Cache.java)**      Level: Hard      Tags: [Design, Hash Table, Linked List]
      

#### Double Linked List
- 用了一个特别的双向的ListNode，有了head和tail，这样就大大加快了速度。     
- 主要加快的就是那个‘更新排位’的过程，找到item hashmap O(1), 做减法换位也都是O(1)
- Overall O(1)

##### 巧妙点
- 1. head和tail特别巧妙：除掉头和尾，和加上头和尾，就都特别快。    
- 2. 用双向的pointer: pre和next, 当需要除掉任何一个node的时候，只要知道要除掉哪一个，     
- 直接把node.pre和node.next耐心连起来就好了，node就自然而然的断开不要了。     
- 一旦知道怎么解决了，就不是很特别，并不是难写的算法:    
- moveToHead()    
- insertHead()    
- remove()      

#### O(n) 检查重复
- timeout method, 天真的来了一个O(n) 的解法，结果果然timeout.     
- 一个map<key,value>存数值。一个queue<key>来存排位。     
- 每次有更新，就把最新的放在末尾；每次超过capaticity,就把大头干掉。很简单嘛，但是跑起来太久，失败了。     




---

**27. [Longest Word in Dictionary.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Word%20in%20Dictionary.java)**      Level: Easy      Tags: [Hash Table, Trie]
      

给串word[], 找最长的Word, 满足条件: 这个Word可以从 word[] 里面一个字母一个字母被build出来.

如果多种答案, respect smallest lexicographical order.

#### Sort, HashSet
- 先排序, 排序以后才能逐个看是否partial string已经存在
- 用 set.contains(substring(0, n - 1)) 来查看上一步的 substring 是否存在
- 如果找到, 因为已经按照字母表排序, 找到的这个肯定是这个长度里面最符合的解答.
- 然后brutally找下一个更大的.
- Sort O(n log n), O(n) set space

#### Trie
- 可以先sort words Array: 1. 长 string 排在前; 2. 相等length, 按照dictionary order 排序
- 全部放入Trie. Trie.insert()
- 针对sorted words array, 从最长的开始找 Trie.startWith.
- 一旦找到, 就是符合题意的, 直接return.
- 注意: startWith 必须每一个node都是 isEnd, 才满足'逐个字母拼出' 的条件.
- Time: build Trie O(mn) + sort:O(nlogn) => O(nlogn)
- Space: O(mn)

#### 
- 按大小排序 -> 从最大的开始做contains()的比较 -> 结果再按照字母表顺序(lexicographically) sort一下.
- 但是Collections.sort()了两次, 而且再list.contains(), 比较慢




---

**28. [Longest Consecutive Sequence.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Consecutive%20Sequence.java)**      Level: Hard      Tags: [Array, Hash Table, Union Find]
      

给一串数字, unsorted, 找这串数字里面的连续元素序列长度 (consecutive序列, 是数字连续, 并不是说要按照原order)

#### HashSet
- 要想看连续元素, 必须要num++, num--这样搜索
- 1. 需要O(1)找到元素
- 2. 需要简单快速找到 num - 1, num + 1.
- 如果用min,max开array, 耗费空间
- 用HashSet来存, 用set.contains() 来查找 num - 1, num + 1 存在与否
- for loop. O(n) 
- 里面的while loop 一般不会有O(n); 一旦O(n), 也意味着set 清零, for loop也不会有更多 inner while 的衍生.
- overall O(n) 时间复杂度


#### Union Find
- 最终是要把相连的元素算一下总长, 其实也就是把元素group起来, 相连的group在一起, 于是想到UnionFind
- 这里用到了一个`int[] size` 来帮助处理 `合并的时候parent是哪个`的问题: 永远往group大的union里去
- main function 里面, 有一个map来track, 每个元素, 只处理1遍.
- union的内容: current number - 1, current number + 1
- https://www.jianshu.com/p/e6b955ca208f

##### 特点
- Union Find 在index上做好像更加容易
- 其他union find function: `boolean connected(a,b){return find(a) == find(b)}`



---

**29. [Unique Word Abbreviation.java](https://github.com/awangdev/LintCode/blob/master/Java/Unique%20Word%20Abbreviation.java)**      Level: Medium      Tags: [Design, Hash Table]
      


给一个string[] dict, 和一个word. 

每个word都可以缩写成固定的abbreviation `<first letter><number><last letter>`(详细看原题)

检查input word是否满足unique

#### HashMap<string, Set>
- 简单算出abbreviatioin
- 检查abbr是否存在; 如果存在, 是不是input word本身.



---

**30. [Top K Frequent Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Words.java)**      Level: Medium      Tags: [Hash Table, Heap, PriorityQueue, Trie]
      

#### PriorityQueue
- 用HashMap存frequency, 用ArrayList存lists of words
- create一个Node class, 然后用PriorityQueue.   
- PriorityQueue里面用到了 String.compareTo(another String).巧妙。
- time: PQ uses O(nlogn), overall O(nlogn)

#### Just HashMap + collections.sort()
- 用HashMap存frequency, 用ArrayList存lists of words。最后返回从尾部向前数的k个。   
- 注意排序时Collection.sort()的cost是O(nLogk)
- not efficient


#### Trie && MinHeap屌炸天   
- 可以做一下
- http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/



---

**31. [ColorGrid.java](https://github.com/awangdev/LintCode/blob/master/Java/ColorGrid.java)**      Level: Medium      Tags: [Design, Hash Table]
      

#### basic implementation
- 用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。没有什么太大的悬念和意义.
- 消灭重合点:       
- 如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
- 再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
- 然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。
- 最后read in 是O(P),  process也是O(P).




---

**32. [Copy List with Random Pointer.java](https://github.com/awangdev/LintCode/blob/master/Java/Copy%20List%20with%20Random%20Pointer.java)**      Level: Medium      Tags: [Hash Table, Linked List]
      

deep copy linked list. linked list 上有random pointer to other nodes.

#### HashMap
- Basic Implementation
- use node and dummy to hold new list, 遍历head.next .... null.    
- 每一步都check map里面有没有head. 没有? 加上
- 每一步都check map里面有没有head.random. 没有? 加上



---

**33. [HashWithCustomizedClass(LinkedList).java](https://github.com/awangdev/LintCode/blob/master/Java/HashWithCustomizedClass(LinkedList).java)**      Level: Medium      Tags: [Hash Table]
      

练习HashMap with customized class. functions: get(), put(), getRandom() 

#### Hash Table
- store map as array: `Entry<K,V>[] table;`
- store entry as linked list: `public Entry(K key, V value, Entry<K,V> next)`
- compute hashKey: `Math.abs(key.hashCode()) % this.capacity`
- Handle collision:
- 1. Check if duplicate (matching key), if so, replace and return
- 2. Check through the linked list, find find duplicate (matching key), replace and return.
- 3. If no duplicate, add the entry to the tail
- Find item: compute hashKey -> find linked list -> iterate over list to find a matching key.



---

**34. [Rehashing.java](https://github.com/awangdev/LintCode/blob/master/Java/Rehashing.java)**      Level: Medium      Tags: [Hash Table]
      

给一个Hash Table, 是用 linked list 做的. 问题是: capacity太小, collision太多的情况下, 需要double capacity 然后rehash.

#### Hash Table
- 明白hashCode() function的意义: 拿到hashKey的时候, 用hashKey%capacity 来做hash code
- hashcode就是hash map里面的index
- 明白collision handling 的方式, 和如何double capacity而rehashing
- 都是基本操作, 概念实现



---

**35. [Top K Frequent Elements.java](https://github.com/awangdev/LintCode/blob/master/Java/Top%20K%20Frequent%20Elements.java)**      Level: Medium      Tags: [Hash Table, Heap, PriorityQueue]
      

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### PriorityQueue
- 题目有提醒: 必须beetter than O(nLog(n)), 也就是说明要O(n)
- 首先想到就是PriorityQueue, 并且不能queue.offer on the fly
- 那么就先count, O(n), using HashMap
- 再priorityQueue, (mLog(m)), m是unique 数字的总量
- 最终find top k, O(k)
- Overall time: O(n) + O(mLogm) + O(k) => O(n), if m is small enough



---

**36. [Remove Duplicate Letters.java](https://github.com/awangdev/LintCode/blob/master/Java/Remove%20Duplicate%20Letters.java)**      Level: Hard      Tags: [Greedy, Hash Table, Stack]
      

#### Hash Table, Greedy
- count[] = int[256], 不需要 `c-'a'`
- boolean visited[]: 一旦一个字母固定了位置后, 再次遇到时候, 直接跳过用过的character
- 如果tail字母可以变小, 那就delete掉tail, 重新接上新字母 (前提条件: 去掉的字母后面还会再出现, set visited[tail] = false)
- Space: O(1) count[], visited[].
- Time: Go through all letters O(n)

#### Stack
- Use stack instead of stringBuffer: keep append/remove last added item
- However, stringBuffer appears to be faster than stack.



---

**37. [2 Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/2%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table]
      

#### HashMap<value, index>
- 相对暴力简洁: 找到一个value, 存一个index
- 若在HashMap里面 match 到结果, 就return HashMap里存的index. 
- O(n) space && time.

#### Sort array, two pointer
- 前后++, --搜索. Sort 用时O(nlogn).     
- 1. 第一步 two pointer 找 value.       
- 2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
- O(n) space, O(nlogn) time.    




---

**38. [Perfect Rectangle.java](https://github.com/awangdev/LintCode/blob/master/Java/Perfect%20Rectangle.java)**      Level: Hard      Tags: [Design, Geometry, Hash Table]
      

看的list of coordinates 是否能组成perfect rectangle, 并且不允许overlap area.

#### 画图发现特点
- 特点1: 所有给出的点(再找出没有specify的对角线点), 如果最后组成perfect rectangle, 都应该互相消除, 最后剩下4个corner
- 特点2: 找到所有点里面的min/max (x,y), 最后组成的 maxArea, 应该跟过程中accumulate的area相等
- 特点1确保中间没有空心的部分, 保证所有的重合点都会互相消除, 最后剩下4个顶点
- 特点2确保没有重合: 重合的area会最终超出maxArea



---

**39. [Max Points on a Line.java](https://github.com/awangdev/LintCode/blob/master/Java/Max%20Points%20on%20a%20Line.java)**      Level: Hard      Tags: [Array, Geometry, Hash Table, Math]
      

给list of (x,y) coordinates. Determine  # of points on the same line

#### Observation
- If given n points, we can calculate all possible slopes. O(n^2) times
- For the two dots that generates the same slope, these dots could be on **parallel** slopes
- figure out how to prune the parallel dots

#### Trick: prune parallel dots using greatest common divider
- Devide the x and y by their greatest common divider, such that x and y can be reduced to minimum value
- All other x and y can be reduced to such condition as well
- track the final reduced (x,y) in a map: they are the key to the count
- No need to use Map<Integer, Map<Integer, Integer>> to perform 2 level mapping; just `map<String, Integer>`, where the key is "x@y"



---

**40. [Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum.java)**      Level: Easy      Tags: [Array, Hash Table]
      

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.



---

**41. [Submatrix Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Submatrix%20Sum.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      

给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`



---

**42. [H-Index.java](https://github.com/awangdev/LintCode/blob/master/Java/H-Index.java)**      Level: Medium      Tags: [Bucket Sort, Hash Table, Sort]
      

找到h-index, 给的citation int[] 并不是sorted. h-index 的definition 具体看题目.

#### Sort, find h from end
- 例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
- 搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn
- 题目给的规则, 从小到大排序后: 剩下的 paper `n-h`, 全部要 <= h 个 citation.
- time O(nlogn), search O(n)

##### 正向思考
- 从i = 0 开始找第一个 `citations[i] >= h`, 就是第一个符合 h-index 规则的paper, return h

##### 反向思考
- 如果从 h = n, 每次h--; 那么 `x = n - h` 就是从 `[0 ~ n)` 开始找第一个 `dictations[x] >= h`, 就是结果
- 同时, `dictations[x-1]` 就是最后一个(dictation最多的)其余的paper.

#### Bucket count / Bucket Sort
- O(n)
- Bucket sort的思想(更像是counting sort?): 过一遍 input, 把dictation value 作为 index, 分布在bucket[index]上++
- bucket[x] 是 count when # of citation == x. 
- 如果 x 大于 n的时候, 就超出了index范围, 但是刚好这个问题可以包容, 把这样的情况记位在bucket[n]就可以
- 巧妙: `sum += bucket[h]` where `h = [n ~ 0]` 利用了h-index的definition:
- # of papers (sum of bucket[n]...bucket[0]) has more than h cidations 
- 这里运用到了bucket sort的思想, 但是并不是sorting, 而h-index的定义运用的很巧妙.
- Read more about actual bucket sort: https://en.wikipedia.org/wiki/Bucket_sort



---

**43. [Rearrange String k Distance Apart.java](https://github.com/awangdev/LintCode/blob/master/Java/Rearrange%20String%20k%20Distance%20Apart.java)**      Level: Hard      Tags: [Greedy, Hash Table, Heap]
      

给一个string, 全是lowercase letter, 要求重新排列: 然后每个unique的character要有k distance apart.

跟Task Scheduler有点像, 只不过Task那道题里面还可以用其他方法求count, 这道题要求出排列结果

#### PriorityQueue + HashTable
- PriorityQueue排序+分布排列的一个经典用法.
- Count frequency and store in pq.
- Consume element of pq for k rounds, each time pick one element from queue.
- Exception: if k still has content but queue is consumed: cannot complete valid string, return "";
- space, O(n) extra space in sb, O(26) constant space with pq.
- time: O(n) to add all items



---

**44. [Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Anagrams.java)**      Level: Medium      Tags: [Array, Hash Table]
      

把anagram找到并output

#### HashMap
- 存在int[26], Arrays.toString(arr) 就是 string key: character frequency map
- anagram都有一样的key, 存进hashmap<string, list of anagrams>
- output anagrams

#### HashMap + Sort
- HashMap 的做法. sort每个string, 存进HashMap, 重复的就是anagrams,最后输出。   
- toCharArray
- Arrays.sort
- Stirng.valueOf(char[])
- 时间n*L*O(logL),L是最长string的长度。

#### Previous Notes
- Arrays.toString(arr)的做法。arr是int[26], assuming only have 26 lowercase letters.    
- Count occurrance, 然后convert to String，作为map的key.
- Time complexity: nO(L)
- 另一种做法：http://www.jiuzhang.com/solutions/anagrams/   
- 1. take each string, count the occurrance of the 26 letters. save in int[]count.   
- 2. hash the int[] count and output a unique hash value; hash = hash * a + num; a = a * b.   
- 3. save to hashmap in the same way as we do. 
- 这一步把for s: strs 里面的时间复杂度降到了O(L). L = s.length().   
- Need to work on the getHash() function.
- 时间变成n*O(L). Better.




---

**45. [Path Sum IV.java](https://github.com/awangdev/LintCode/blob/master/Java/Path%20Sum%20IV.java)**      Level: Medium      Tags: [DFS, Hash Table, Tree]
      

给一串3-digit 的数组. 每个数字的表达一个TreeNode, 3 digit分别代表: depth.position.value

这串数字已经从小到大排列. 求: 所有可能的 root->leaf path 的所有可能的 path sum 总和. 

#### DFS, Hash Table
- 因为`前两个digit可以uniquely identify`一个node, 所以可以把前两个digit作为key, 定位node.
- 特点: 比如考虑root, 有 n 个leaf, 就会加 n 遍root, 因为有 n 个 unique path嘛.
- 实现: 每个node, 上来先把curr value加进sum; 只要有child, 到这个node位置的以上path sum 就要被重加一次.
- format: depth.position.value. (on same level, position may not be continuous)
- approach: map each number into: <depth.position, value>, and dfs. 
- Start from dfs(map, rootKey, sum):
- 1. add node value to sum
- 2. compute potential child.
- 3. check child existence, if exist, add sum to result (for both left/right child). Check existence using the map.
- 4. also, if child exist, dfs into next level
- Space, time O(n)



---

**46. [Longest Words.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Words.java)**      Level: Easy      Tags: [Hash Table, String]
      

给一串String, 找到最长的长度, 把最长的String全都return

#### Hash Table
- <Integer,List<String>>
- 存最长值, 最后map.get(max) 



---

**47. [Subarray Sum Equals K.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20Equals%20K.java)**      Level: Medium      Tags: [Array, Hash Table, PreSum]
      
time: O(n)
space: O(n)

#### Hash Table
- Hash Table two sum 思想, but `save frequency of current preSum`
- From the orignal presum solution: `target = preSum[j] - preSum[i - 1]`
- `k = sum - portion`, and reversely, `portion = sum - k`
- We know the value of sum and k, so portion will be requested
- portion is just previously calcualted sum; track its frequency using `map<preSumValue, frequency>`
- map.get(portion) = the # of possible ways to reach k
- O(n) time, O(n) space

#### PreSum, O(n^2)
- move from starting point i = [0 ~ n -1] and define range = [i ~ j]
- use presum to verify k: `preSum[j] - preSum[i - 1]`
- O(n^2): `1 + 2 + 3 + 4 ... + n ~= O(n^2)`




---

**48. [Sparse Matrix Multiplication.java](https://github.com/awangdev/LintCode/blob/master/Java/Sparse%20Matrix%20Multiplication.java)**      Level: Medium      Tags: [Hash Table]
      
time: O(mnk), where `m = A.row`, `n = B.col`, `k = A.col = B.row`
space: O(1) extra

给两个matrics, 做乘积. 注意, 是sparse matrix (特点: 很多0).

#### Hash Table
- Recall matric multiplication rules: result[i][j] = sum(A-row[i] * B-col[j])
- `sparse matric: lots positions are zero`
- 平白地写matric multiplication 没有意义, 重点就是optimization:
- `optimization`: for A-zero-row, and B-zero-col, there is no need to calculate, just return 0.
- 1. Find A-zero-rows and store in setA, same for setB
- 2. during multiplication, reduce time complexity.
- Base: O(mnk), where `m = A.row`, `n = B.col`, `k = A.col = B.row`

#### Matrices
- 乘法规则: result[i][j] = sum(A-row[i] * B-col[j])
- A column size == B row size. 并且: 计算顺序是iterate over A column size



---

**49. [Brick Wall.java](https://github.com/awangdev/LintCode/blob/master/Java/Brick%20Wall.java)**      Level: Medium      Tags: [Hash Table]
      
time: O(mn)
space: O(X), X = max wall width

给一面墙, 每一行是一行bricks. 用一条vertical line 扫描, 会vertically割开brink. 找到割开最少brick的那条线的x index.

#### Hash Table
- Find the vertical line (x-coordinate of the grid), where most gaps are found.
- Each gap has (x,y) coordinate
- Create `map<x-coordinate, #occurrance>`, and maintain a max occurance. 
- 计算: x-coordinate: `x = 0; x += brick[i] width`
- Eventually: min-crossed bricks = wall.lenght - maxOccurrance 

##### 思想
- 分析题意, 找到题目的目标
- 虽然Map自己不能有sort的规律, 但是可以maintain global variable



---

**50. [Accounts Merge.java](https://github.com/awangdev/LintCode/blob/master/Java/Accounts%20Merge.java)**      Level: Medium      Tags: [DFS, Hash Table, Union Find]
      

#### Union Find
- TODO


#### Hash Table solution, passed but very slow
- Definitely need iterate over accounts: merge them by email.
- Account object {name, list of email}
- map<email, account>
- 1. iterate over accounts
- 2. find if 'account' exist;  if does, add emails
- 3. if not, add account to list and to map. map all emails to accounts.
- output -> all accounts, and sort emails
- space O(mn): m row, n = emails
- time O(mn)



---

**51. [Maximum Size Subarray Sum Equals k.java](https://github.com/awangdev/LintCode/blob/master/Java/Maximum%20Size%20Subarray%20Sum%20Equals%20k.java)**      Level: Medium      Tags: [Hash Table, PreSum]
      
time: O(n)
space: O(n)

#### Map<preSumValue, index>
- use `Map<preSum value, index>` to store inline preSum and its index.
- 1. Build presum incline
- 2. Use map to cache current preSum value and its index: `Map<preSum value, index>`
- 3. Each iteration: calculate possible preSum candidate that prior target sequence. ex: `(preSum - k)`
- 4. Use the calculated preSum candidate to find index
- 5. Use found index to calculate for result. ex: calculate range.



---

**52. [Contiguous Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Contiguous%20Array.java)**      Level: Medium      Tags: [Hash Table]
      

TODO: how aout without chaning the input nums?



---

**53. [Line Reflection.java](https://github.com/awangdev/LintCode/blob/master/Java/Line%20Reflection.java)**      Level: Medium      Tags: [Hash Table, Math]
      
time: O(n)
space: O(n)

给一串点, 找是否有一个所有点中间的, 跟y-axis平行的中线.

#### Hash Table
- 1. store in `Map<y, set<x>>`, 2. iterate over map, check head,tail against the mid point
- 很好的细节题目:
- 1. 除以2, 需要存double
- 2. (问面试官)可以有重复的点! 所以track `set<x>`
- 3. 处理 left==right时候, 就当做两个点来处理.
- 4. 存进set里面没有sort, 但是最后做check的时候, 需要sort list
- 时间: visit all nodes 两遍,  O(n)



---

**54. [Insert Delete GetRandom O(1).java](https://github.com/awangdev/LintCode/blob/master/Java/Insert%20Delete%20GetRandom%20O(1).java)**      Level: Medium      Tags: [Array, Design, Hash Table]
      
time: O(1) avg
space: O(n)

#### Hash Table
- 用`map<value, index> 来track value->index`, 用`list track index->value`
- map查看value是否存在
- list maintain 用来 insert/remove/random operations.
- 特点: 一旦remove, 换到list结尾然后 `list.remove(list.size() - 1)`, 这样remove的cost更低. 
- list.remove(object) 应该是要O(logn) 做一个search的.



---

