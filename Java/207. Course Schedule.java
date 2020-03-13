M
tags: DFS, BFS, Graph, Topological Sort, Backtracking
time: O(n)
space: O(n)

- 一堆课用int[2] pair 来表示. [1, 0] 表示要上课1的话, 必须先把课0上了. 
- 每一个数字都是一个ndoe, 题目问是否能把所有的课排了
- input是 numOfCourses, 还有这个prerequisites [[]]
- Concept of Indegree: `# of incoming node that depends on me`. It is a graph of `arrows pointing inward to me` structure.

##### Indegree 原理
- Remember: **indegree是周围的node到我这里的次数count**.
    - Note: 如果有cycle, 这个node上面会多一些inDegree, 也就无法清0, 它也无法进入 queue && sorted list. 
    - 如果周围所有node的连线, 都意义切除后, 我的indegree还不等于0, 那么肯定有某些node间接地有重复连线, 也就是有cycle
- Topological problem: almost always care about cycle case (if detecting cycle is not goal)

#### BFS, Topological Sort
- Two structures:
    - 1) build inDegreeEdges: `List[] inDegreeEdges`: list of incoming nodes that depends on `node i`, 
    - 2) build dependencyCount: `int[] dependencyCount`, count # of braches that curr node depends on
- any dependencyCount[node]==0, means this node is now a leaf, add to queue
- Topological Sort Process, Kahn algorithem:
- topologically process: 
    1) add leaf node to queue, get ready to process; 
    2) process leafNode, like cutting of leaf
    3) if any child node dependencyCount == 0, it is a leaf node now: add this node to queue.

#### DFS
- this problem aims for deteching cycle, not output final list. Simply: visit all nodes and verify cycle
- Option1: array of indegree lists, List[]
    - 用 visited int[] 来确认是否有cycle. 1 means `visited`, -1 means `visted from last dfs level`
        - Deteching `-1`: 说明这个node在上一级或者以上的同一个dfs path里面已经走过, 那么证明有cycle, return false.
        - dfs on curr node indegree dependencies; if all passes w/o failing, set visited[i] = 1
    - Similarly, can use `HashMap<Integer, List<Integer>> map` to replace List[], but exact same idea.
- Optoin2: use a struct `class Node {Boolean visiting; Map<Integer, Node> inDegreeMap}` to be more generic
- topo sort may output the sort order: 1) at DFS bottom level, put record to a `stack`, 2) rst.insert(0, curr record)

#### Notes
- 还有 List[] arrayOfList = new ArrayList[]; 这样的操作啊, 代替了map<integer, integerList>. Though: map may be more flexible
- 是topological sort的题目。一般都是给有dependency的东西排序。    
    - 最终都会到一个sink/leaf node，no further dependency, 在那个点截止
- 画个图的话, prerequisite都是指向那个sink/leaf node
- when building the inDegreeMap/inDegreeEdge: we use sink/leaf node as key/index, which pionts back to inDegree/parent nodes
- BFS: when all braches/dependency count are reduced to 0, then it is now a leaf node, ready to be used.
- DFS Insert Order: rst.insert(0, node); Assume we want leaf/node at index 0 in final output:
    - the very bottom-node **depends on everybody**
    - any visited node should be added to 0 index of the list, so it will be at tail later

```
/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.


Hints:
1. This problem is equivalent to finding if a cycle exists in a directed graph. 
	If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera 
   explaining the basic concepts of Topological Sort.
3. Topological sort could also be done via BFS.
*/

/*
Method1: Topological sort, BFS Kahn's algorithem.
- 1) build inDegreeEdges, 2) build dependencyCount 
- topologically process: 1) add leaf node to queue, get ready to process; 2) process leafNode, like cutting of leaf
- any dependencyCount[node]==0, means this node is now a leaf, add to queue
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (validateInput(numCourses, prerequisites)) return true;

        List[] inDegreeEdges = new List[numCourses];
        int[] dependencyCount = new int[numCourses]; 
        
        // Initialize
        for (int i = 0; i < numCourses; i++) inDegreeEdges[i] = new ArrayList<>();
        
        // Build inDegreeEdges, and dependencyCount
        for (int[] prerequisite : prerequisites) {
            inDegreeEdges[prerequisite[1]].add(prerequisite[0]);
            dependencyCount[prerequisite[0]]++;
        }
        
        // BFS: 1) add leaf nodes into queue. Get ready to process its incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (dependencyCount[i] == 0) queue.add(i); // leaf node that depends on nobody
        }
        
        // 2) process leafNode
        int count = 0; // count of leaf node that can eventually can be independent: when all its dependencies are exhausted
        while (!queue.isEmpty()) {
            count++;
            int leafNode = queue.poll();
            List<Integer> dependencies = inDegreeEdges[leafNode];
            for (int node : dependencies) {
                dependencyCount[node]--; // imaging we are cutting off leafNode, so its parent will decrese the dependency count
                if (dependencyCount[node] == 0) queue.add(node); // add to result when any node's dependencies count are exhausted
            }
        }
        
        // BFS will always end. Validate if # of leaf/independent course equals to total numCourses
        // If not equal, then there is cycle edges that cannot be resolved to 0
        return count == numCourses;
    }

    private boolean validateInput(int numCourses, int[][] prerequisites) {
        return numCourses == 0 || prerequisites == null || prerequisites.length == 0
            || prerequisites[0] == null || prerequisites[0].length == 0;
    }
}

/*
Method2: DFS, Option1, using List[] edges to mark inDegree Structure
    Similarly, can use HashMap<Integer, List<Integer>> map; rather than List[].
DFS. This question only asks about true/false (to detect cycle),
so there is no need to record the actual final list.
We'll simply traverse all of the nodes
If there is a cycle, there must be a node being visited again in one loop: 
mark last visited node = -1
mark visited starting node after dfs: node = 1
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) return true;
        List[] edges = buildIndegreeEdges(numCourses, prerequisites);
        
        // DFS serach && marking visited
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(!dfs(edges, visited, i)) return false;
        }
        return true;
    }
    
    private boolean dfs(List[] edges, int[] visited, int node) {
        if (visited[node] == 1) return true;
        if (visited[node] == -1) return false; // cyclic
        
        visited[node] = -1;
        List<Integer> childNodes = edges[node];
        for (int childNode : childNodes) {
            if (!dfs(edges, visited, childNode)) return false;
        }
        visited[node] = 1;
        
        return true;
    }
    
    private List[] buildIndegreeEdges(int numCourses, int[][] prerequisites) {
        List[] edges = new ArrayList[numCourses];
        // Initialize with empty list of indgree edges
        for (int i = 0; i < numCourses; i++) edges[i] = new ArrayList<>();
        // Populate graph edges. prerequisite[1]=current node; prerequisite[0] = incoming dependency
        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
        }
        return edges;
    }
}

// DFS Option2: with `class Node {Boolean visiting; Map<Integer, Node> inDegreeMap}` to build inDegree structure
class Solution {
    class Node {
        Boolean visiting = null;
        Map<Integer, Node> inDegreeMap = new HashMap<>();
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) return true;
        Node root = buildIndegreeMap(numCourses, prerequisites);
        
        // DFS serach && marking visited
        return dfs(root);
    }
    
    private boolean dfs(Node node) {
        // if visiting: cyclic, return false; visited but now node.visiting == false, return true;
        if (node.visiting != null) return !node.visiting;

        node.visiting = true;
        for (Node childNode : node.inDegreeMap.values()) {
            if (!dfs(childNode)) return false;
        }
        node.visiting = false;
        
        return true;
    }
    
    private Node buildIndegreeMap(int numCourses, int[][] prerequisites) {
        Node root = new Node();
        // Initialize with empty list of indgree edges
        for (int i = 0; i < numCourses; i++) root.inDegreeMap.put(i, new Node());
        // Populate graph edges. prerequisite[1]=current node; prerequisite[0] = incoming dependency
        Map<Integer, Node> rootIndegreeMap = root.inDegreeMap;
        for (int[] prerequisite : prerequisites) {
            Node currNode = rootIndegreeMap.get(prerequisite[1]);
            Node dependencyNode = rootIndegreeMap.get(prerequisite[0]);
            currNode.inDegreeMap.put(prerequisite[0], dependencyNode);
        }
        return root;
    }
}


```