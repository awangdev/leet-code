H
tags: Graph, Topological Sort, DFS, BFS, Backtracking
time: O(n), n = # of graph edges
space: O(n)

给一个 array of strings: 假如这个array是按照一个新的字母排序表(alien dictionary)排出来的, 需要找到这个字母排序.

有可能有多重排序的方法, 给出一种就可以.

#### Graph, Topological Sort with InDegree count, BFS
- `Build graph`:
    - 上下两行string, 相对应的相同的index上, 如果字母不同, 就说明排在第一行的字母在字母表里更领先: form sequence between chars
    - form graph Map<Char, List of Chars>, for topological sort usage.
    - 也可以`List[26] edges` (Course Schedule problem)
- `Build InDegreeCountMap<Char, Count>`: based on the char diff of 2 words
    - 注意: indegree 是反向的 (跟 node to neighbors 相反的方式建立)
- `Topological Sort`, BFS:
    - 1) use queue to find `inDegree == 0` node. It is the letter that points to others, 排在字母表前面.
    - 2) reduce edges using Graph`map<Character, List<Character>>` (more generic than List[26], 26个字母的dictionary)
- Edge Case:
    - `inDegreeCountMap.size() != result.length()`: some nodes did not make it into result sequence
    - `cycle`: when inDegree of a one node would never reduce to 0, and will not be added to result
        - In this case, it will be treated as invalid input, and return ""
- space: O(n), n = # of graph edges 
- time: O(n)

#### DFS
- TODO
- 跟BFS建立 grpah 的过程一模一样
- DFS的不同在于: 用visited map 来标记走过的地方
- 走到leaf的时候, add to result: 但因为走到了底才add, 最终的顺序应该颠倒 (或者, sb.insert(0, x) 直接用颠倒的顺序add)

```

/*

There is a new alien language which uses the latin alphabet. 
However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, 
where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

*/

Check:
https://leetcode.com/problems/alien-dictionary/discuss/70119/Java-AC-solution-using-BFS

/*
Thoughts:
Topological sort with BFS. The tricky part is: how to construct the node-edge relationship?
For the same index of two strings: if the word1[index] != word2[index],
that means c1 is at the leading position than c2 in topological order.

Use this feature to build the edges.

1. Build graph
2. Calculate indegree
3. BFS
*/
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        
        // Build graph && indegree map
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        build(graph, inDegree, words);
        
        // Topological sort with BFS
        return topoSort(graph, inDegree);
    }
    
    private void build(Map<Character, List<Character>> graph, Map<Character, Integer> inDegree, String[] words) {
        // Init graph, inDegree map
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        // Build graph: find char diff between curr row and next row => build graph edge and increase inDegree relationship
        // always compare same index on: words[i] -> words[i+1]
        // if c1 != c2, build graph and inDegree map and break: later index does not have any more relationship.
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                char c1 = words[i].charAt(index);
                char c2 = words[i + 1].charAt(index);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    inDegree.put(c2, inDegree.get(c2) + 1);
                    break;
                }
                index++;
            }
        }
    }

    private String topoSort(Map<Character, List<Character>> graph, Map<Character, Integer> inDegree) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) { // Build queue with item of inDegree==0: means no edge points towards it.
            if (inDegree.get(c) == 0) queue.offer(c);
        }

        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char edgeNode : graph.get(c)) { // reduce edge degrees count since node:graph.get(c) has been processed
                inDegree.put(edgeNode, inDegree.get(edgeNode) - 1);
                if (inDegree.get(edgeNode) == 0) queue.offer(edgeNode);
            }
        }
        
        if (sb.length() != graph.size()) return "";
        return sb.toString();
    }
}


/*
Thoughts:
DFS, mark visited. When dfs down to an leaf element,
it'll be the last element of the final output. (reverse order)
*/
class Solution {
    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> visited = new HashMap<>();
    StringBuffer sb = new StringBuffer();
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        // Build graph, and visited map.
        buildGraph(words);
        
        // Topological sort with dfs
        for (char c : graph.keySet()) {
            if (!dfs(c)) {
                return "";
            }
        }
        
        return sb.toString();
    }
    
    private boolean dfs(Character c) {
        if (visited.get(c) == 1) return true;
        if (visited.get(c) == -1) return false;
            
        visited.put(c, -1);
        for (char edgeNode : graph.get(c)) {
            if (!dfs(edgeNode)) {
                return false;
            }
        }
        visited.put(c, 1);
        sb.insert(0, c); // leaf element, add to buffer
        return true;
    }
    
    private void buildGraph (String[] words) {
        // Create nodes
        for (String word: words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new ArrayList<>());
                    visited.put(c, 0);
                }
            }
        }
        
        // Build edges
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                char c1 = words[i].charAt(index);
                char c2 = words[i + 1].charAt(index);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break;
                }
                index++;
            }
        }
    }
}



```
