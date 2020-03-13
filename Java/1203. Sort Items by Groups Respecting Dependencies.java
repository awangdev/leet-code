H
tags: DFS, BFS, Graph, Topological Sort
time: O(V + E) to traverse the graph, #nodes + #edges
space: O(V + E)

#### Topological Sort
- Realize we need to: 1) topo sort group, 2) topo sort items in the group. 
- Luckily, the candidates to be sorted are all integers: groupIds, or item ids. We can have 1 generic topo sort function
- Overall workflow
    - 1) group items to map <GroupId, List<items>>
    - 2) build group graph
    - 3) topo sort group -> return sorted group id list
    - 4) for each group: build item graph, topo sort items -> return sorted item list
    - 5) flatten and return results

```
/*
There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted list.
There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty list if there is no solution.

 

Example 1:



Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
Output: [6,3,4,1,5,2,0,7]
Example 2:

Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
Output: []
Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 

Constraints:

1 <= m <= n <= 3*10^4
group.length == beforeItems.length == n
-1 <= group[i] <= m-1
0 <= beforeItems[i].length <= n-1
0 <= beforeItems[i][j] <= n-1
i != beforeItems[i][j]
beforeItems[i] does not contain duplicates elements.
*/

/*
- Realize we need to: 1) topo sort group, 2) topo sort items in the group. 
- Luckily, the candidates to be sorted are all integers: groupIds, or item ids. We can have 1 generic topo sort function
- Overall workflow
    1) group items to map <GroupId, List<items>>
    2) build group graph
    3) topo sort group -> return sorted group id list
    4) for each group: build item graph, topo sort items -> return sorted item list
    5) flatten and return results
*/
/*
- Realize we need to: 1) topo sort group, 2) topo sort items in the group. 
- Luckily, the candidates to be sorted are all integers: groupIds, or item ids. We can have 1 generic topo sort function
- Overall workflow
    1) group items to map <GroupId, List<items>>
    2) build group graph
    3) topo sort group -> return sorted group id list
    4) for each group: build item graph, topo sort items -> return sorted item list
    5) flatten and return results
*/
/*
- Realize we need to: 1) topo sort group, 2) topo sort items in the group. 
- Luckily, the candidates to be sorted are all integers: groupIds, or item ids. We can have 1 generic topo sort function
- Overall workflow
    1) group items to map <GroupId, List<items>>
    2) build group graph
    3) topo sort group -> return sorted group id list
    4) for each group: build item graph, topo sort items -> return sorted item list
    5) flatten and return results
*/
class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        Map<Integer, List<Integer>> groupMap = buildGroup(n, m, group);
        Map<Integer, List<Integer>> groupGraph = buildGroupGraph(n, group, beforeItems);
        List<Integer> sortedGroupIds = topoSort(groupGraph, groupMap.keySet());
        List<Integer> rst = new ArrayList<>();
        for (int groupId : sortedGroupIds) {
            Map<Integer, List<Integer>> itemGraph = buildItemGraph(groupMap.get(groupId), beforeItems);
            List<Integer> sortedItemIds = topoSort(itemGraph, new HashSet<>(groupMap.get(groupId)));
            rst.addAll(sortedItemIds);
        }
        
        if (rst.size() != n) return new int[] {};
        
        int[] result = new int[n];
        for (int i = 0; i < rst.size(); i++) {
            result[i] = rst.get(i);
        }
        return result;
    }
    
    private Map<Integer, List<Integer>> buildGroup(int n, int m, int[] group) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int groupId = group[i] == -1 ? m++ : group[i];
            group[i] = groupId;
            map.putIfAbsent(groupId, new ArrayList<>());
            map.get(groupId).add(i);
        }
        return map;
    }
    
    private List<Integer> topoSort(Map<Integer, List<Integer>> graph, Set<Integer> set) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int item : set) { // init all elements from the set
            indegree.put(item, 0);
        }
        for (int node : graph.keySet()) { // sink node
            if (!set.contains(node)) continue; // ignore the dependency on nodes outside of the target set
            for (int parent : graph.get(node)) { // parent node
                indegree.put(parent, indegree.get(parent) + 1);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) queue.offer(key);
        }
        
        List<Integer> rst = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            rst.add(node);
            if (!graph.containsKey(node)) continue;
            for (int parent : graph.get(node)) {
                indegree.put(parent, indegree.get(parent) - 1);
                if (indegree.get(parent) == 0) queue.offer(parent);
            }
        }
        
        return rst;
    }
    
    private Map<Integer, List<Integer>> buildItemGraph(List<Integer> items, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i : items) {
            for (int item : beforeItems.get(i)) {
                graph.putIfAbsent(item, new ArrayList<>());
                graph.get(item).add(i);
            }
        }
        return graph;
    }

    private Map<Integer, List<Integer>> buildGroupGraph(int n, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int groupId = group[i];
            for (int item : beforeItems.get(i)) {
                if (groupId == group[item]) continue;
                graph.putIfAbsent(group[item], new ArrayList<>());
                graph.get(group[item]).add(groupId);
            }
        }
        return graph;
    }

}
```