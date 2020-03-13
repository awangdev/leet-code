 
 
 
## HashHeap (3)
**0. [[lint]. HashHeap.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20HashHeap.java)**      Level: Hard      Tags: [HashHeap, Heap, Lint]
      
非题.是从九章找来的HashHeap implementation.

#### HashHeap
- An efficient implementation of a priority queue. 
- The linear hash function monotonically maps keys to buckets, and each bucket is a heap
- https://xlinux.nist.gov/dads/HTML/hashheap.html



---

**1. [[lint]. Heapify.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%20Heapify.java)**      Level: Medium      Tags: [HashHeap, Heap, Lint, MinHeap]
      
Turn unsorted array into a min-heap array, where for each A[i], 

A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

#### Heap
- Heap用的不多. 得用一下, 才好理解. 通常default 的PriorityQueue就是给了一个现成的min-heap:
- 所有后面的对应element都比curr element 小。
- Heapify里面的**siftdown**的部分:
- 只能从for(i = n/2-1 ~ 0)， 而不能从for(i = 0 ~ n/2 -1): 必须中间开花，向上跑的时候才能确保脚下是符合heap规则的

#### Heapify/SiftDown做了什么?
- 确保在heap datastructure里面curr node下面的两个孩子，以及下面所有的node都遵循一个规律
- 比如在这里，若是min-heap,就是后面的两孩子都要比自己大。若不是，就要swap。    

#### min-heap的判断规律:
- for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
- siftdown时：在curr node和两个child里面小的比较。如果的确curr < child, 搞定，break while.   
- 但若curr 并不比child小，那么就要换位子，而且继续从child的位子往下面盘查。    



---

**2. [218. The Skyline Problem.java](https://github.com/awangdev/LintCode/blob/master/Java/218.%20The%20Skyline%20Problem.java)**      Level: Hard      Tags: [BIT, Divide and Conquer, HashHeap, Heap, PriorityQueue, Segment Tree, Sweep Line]
      

#### Sweep Line, Time O(nLogN), Space O(n)
- Analysis (inspired by, but not same solution: https://leetcode.com/problems/the-skyline-problem/solution/)
    - If there are just 2 overlapping building (totally 4 points on x-axis), here is the outline process:
    - Process x coordinate from left->right, one at a time.
        - 1) compare all `on-going heights` and find max, add as new outline point
        - 2) Handling building end: if the position ends a building, need to remove this height from the list of `on-going heights`
    - Requires 2 heap:
        1) sort by x coordinates
        2) `on-going heights`: maintain a pq of ongoing heights
- Steps: 
    - original reference http://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html?_sm_au_=isVmHvFmFs40TWRt
    - 画图分析: 需要找到 non-overlaping height point at current index; also height needs to be different than prev height peek to be visible.
    - `on-going heights`: 用max-heap (reversed priorityqueue)，再iterate heightPoints 来存最大的height
    - NOTE: heightQueue里面加一个0, 用来在结尾的时候做closure
- time: initial sort O(nlogn) + calculate n * O(nlogn) [maxQueue sort]
- space: O(n)

#### Segment Tree
- 看了一些做法, segment tree写法很复杂, 估计在面试中难以用segment tree来写: https://www.cnblogs.com/tiezhibieek/p/5021202.html

#### HashHeap
- HashHeap template 可以考虑: https://www.jiuzhang.com/solution/building-outline/#tag-highlight-lang-java

Binary Indexed Tree?





---

