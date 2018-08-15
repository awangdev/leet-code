M
tags: Graph, DFS, Union Find

#### DFS
- build map of `x#y -> val` to store values[i] and 1/values[i]
- build map of `x -> list children`
- dfs to traverse the graph

```
/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.


*/

class Solution {
    Map<String, Double> valueMap = new HashMap<>();
    Map<String, List<String>> graph = new HashMap<>();
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // build graph
        for (int i = 0; i < values.length; i++) {
            String x = equations[i][0], y = equations[i][1];
            valueMap.put(getKey(x, y), values[i]);
            valueMap.put(getKey(y, x), 1.0 / values[i]);
            graph.putIfAbsent(x, new ArrayList<>());
            graph.putIfAbsent(y, new ArrayList<>());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        // dfs on each item
        int n = queries.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            String[] query = queries[i];
            result[i] = dfs(new HashSet<>(), query[0], query[1], 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }

        return result;
    }
    
    private double dfs(Set<String> visited, String start, String end, double value) {
        if (visited.contains(start)) return 0.0;
        if (!graph.containsKey(start)) return 0.0;
        if (start.equals(end)) return value;
        visited.add(start);

        double temp = 0.0;
        for (String next : graph.get(start)) {
            String nextKey = getKey(start, next);
            temp = dfs(visited, next, end, value * valueMap.get(nextKey));
            if (temp != 0.0) break;
        }
        return temp;
    }
    
    private String getKey(String a, String b) {
        return a + "/" + b;
    }
}
```