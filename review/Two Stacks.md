 
 
 
## Two Stacks (1)
**0. [145. Binary Tree Postorder Traversal.java](https://github.com/awangdev/LintCode/blob/master/Java/145.%20Binary%20Tree%20Postorder%20Traversal.java)**      Level: Medium      Tags: [Stack, Tree, Two Stacks]
      

如题, POST-ORDER traversal.

LeetCode给了hard, 应该是觉得stack的做法比较难想到.

#### Method1: DFS, Recursive
- trivial, 先加left recursively, 再加right recursively, 然后组成头部.
- Option1 w/o helper; option2 with dfs helper.

#### Method2, Iterative, Stack
- Option1: reversely add to list
- 双stack的思想, 需要在图纸上画一画
    - 原本需要的顺序是: 先leftChild, rightChild, currNode.
    - 营造一个stack, reversely process: 先currNode, 再rightChild, 再leftChild
    - 这样出来的结果是reverse的, 那么翻转一下就可以了.
- reverse add: `list.add(0, x)`;
- 利用stack的特点
    - 每次加element进stack的时候, 想要在 bottom/后process的, 先加
    - 想要下一轮立刻process的, 最后push进stack.
- Option2: regular sequence add to stack: add curr, right, left
    - Use set to contain the processed children 
    - only process curr if its children is processed




---

