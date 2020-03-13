M
tags: Graph, DFS, Backtracking
time: O(n^n)
space: O(m)

#### DFS with backtcking
- Construct graph: `map<String, List<Destination>>`; sort the list of destinations.
- DFS:
    - with any curr city, go over the destination list: `graph.get(curr)`
    - add visit city to rst
    - remove visited city from the desitnation list
    - backtrack
- NOTE: 
    - 1) the graph allows cycle: revisiting same city. Do NOT assume no cycle
    - 2) it asks to us to treat `smaller lexical order city` with priority; however:
        - it does NOT mean visiting `smaller lexical order city` is THE correc anser
        - it can be a leaf sink node of the graph and does not provide correct trip plan
- time: O(n^n). n = # of cities. worst case, each city has (n-1) edges and need to try all combinations
- space: O(n^2), there can at most be n * (n - 1) edges

```
/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/

/*
- Construct map<String, Queue<String>> based on input, and DFS
- Order the items by name;
- remove edge after use; dfs and backtrack
*/
/*
- Construct graph: `map<String, List<Destination>>`; sort the list of destinations.
- DFS:
    - with any curr city, go over the destination list: `graph.get(curr)`
    - add visit city to rst
    - remove visited city from the desitnation list
    - backtrack
- NOTE: 
    - 1) the graph allows cycle: revisiting same city. Do NOT assume no cycle
    - 2) it asks to us to treat `smaller lexical order city` with priority; however:
        - it does NOT mean visiting `smaller lexical order city` is THE correc anser
        - it can be a leaf sink node of the graph and does not provide correct trip plan
*/
class Solution {
    int n = 0;
    public List<String> findItinerary(List<List<String>> tickets) {
        
        List<String> rst = new LinkedList<>();
        Set<String> citySet = new HashSet<>();
        Map<String, List<String>> map = buildMap(tickets);
        n = tickets.size();
        rst.add("JFK");
        dfs(rst, map, "JFK");
        return rst;
    }
    
    private boolean dfs(List<String> list, Map<String, List<String>> map, String curr) {
        if (list.size() == n + 1) return true;
        if (!map.containsKey(curr)) return false;
        List<String> destinations = map.get(curr);
        for (int i = 0; i < destinations.size(); i++) {
            String next = destinations.get(i);
            destinations.remove(i);
            list.add(next);

            if (dfs(list, map, next)) return true;

            // backtrack
            destinations.add(i, next);
            list.remove(list.size() - 1);
        }
        return false;
    }
    
    private Map<String, List<String>> buildMap(List<List<String>> tickets) {
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            map.putIfAbsent(ticket.get(0), new LinkedList<>());
            map.get(ticket.get(0)).add(ticket.get(1));
        }
        for (String key : map.keySet()) Collections.sort(map.get(key));
        return map;
    }
}
```