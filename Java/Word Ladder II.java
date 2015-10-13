/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
Have you met this question in a real interview? Yes
Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note
All words have the same length.
All words contain only lowercase alphabetic characters.
Tags Expand 
Backtracking Depth First Search Breadth First Search

Attempt1 is by me: however it exceeds time/memory limit.
Some other good sources can be found online:
//http://www.jiuzhang.com/solutions/word-ladder-ii/
//http://www.cnblogs.com/shawnhue/archive/2013/06/05/leetcode_126.html
Adjacency List, Prefix ... etc. Let's look at them one after another. First get it through with a NineChapter solution
*/

//Attempt2: Use Nine Chapter solution, BFS + DFS. It works, very nicely, using backtracking.
/*
BFS:
1. For all mutations in dict, create pastMap: all possible mutations that can turn into each particular str in dict.
2. For all mutations in dict, create distance: distance to start point.
DFS:
3. Find minimum path by checking distance different of just 1. Use a List<String> to do DFS

Note: 
Map uses containsKey. Set uses contains
In DFS, add new copy: new ArrayList<String>(path)
BFS: queue, while loop
DFS: recursion, with a structure to go deeper, remember to add/remove element when passing alone
*/
public class Solution {
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> rst = new ArrayList<List<String>>();
        Map<String, ArrayList<String>> pastMap = new HashMap<String, ArrayList<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();
        
        //Initiate the variables
        dict.add(start);
        dict.add(end);
        queue.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            pastMap.put(s, new ArrayList<String>());
        }

        //BFS
        BFS(start, end, distance, pastMap, dict, queue);

        //DFS
        ArrayList<String> path = new ArrayList<String>();
        DFS(start, end, distance, pastMap, path, rst);

        return rst;
    }
    //BFS to populate map and distance:
    //Distance: distance from each str in dict, to the starting point.
    //Map: all possible ways to mutate into each str in dict.
    public void BFS(String start, String end, Map<String, Integer> distance, Map<String, ArrayList<String>> pastMap, Set<String> dict, Queue<String> queue) {
        while(!queue.isEmpty()) {
            String str = queue.poll();
            List<String> list = expand(str, dict);

            for (String s : list) {
                pastMap.get(s).add(str);
                if (!distance.containsKey(s)) {
                    distance.put(s, distance.get(str) + 1);
                    queue.offer(s);
                }
            }
        }
    }
    //DFS on the map, where map is the all possible ways to mutate into a particular str. Backtracking from end to start
    public void DFS(String start, String str, Map<String, Integer> distance, Map<String, ArrayList<String>> pastMap, ArrayList<String> path, List<List<String>> rst) {
        path.add(str);
        if (str.equals(start)) {
            Collections.reverse(path);
            rst.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {//next step, trace 1 step towards start
            for (String s : pastMap.get(str)) {//All previous-mutation options that we have with str:
                if (distance.containsKey(s) && distance.get(str) == distance.get(s) + 1) {//Only pick those that's 1 step away: keep minimum steps for optimal solution
                    DFS(start, s, distance, pastMap, path, rst);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    //Populate all possible mutations for particular str, skipping the case that mutates back to itself.
    public ArrayList<String> expand(String str, Set<String> dict) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {//Alternate each letter position
            for (int j = 0; j < 26; j++) {//Alter 26 letters
                if (str.charAt(i) != (char)('a' + j)) {
                    String newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
                    if (dict.contains(newStr)) {
                        list.add(newStr);
                    }
                }
            }
        }
        return list;
    }
}



//Attempt1: probably works, however:
//Testing against input: "qa", "sq", ["si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"]
//0. Could be backtrackList exceed memory limit.
//1. If use HashSet<String> set to check if particular sequence exist, then exceed memory
//2. If use StringBuffer strCheck to check if particular sequence exist, then exceed time limit.
//It looks like we'd use DFS for final results.
public class Solution {
	private Queue<String> q = new LinkedList<String>();
	private Queue<ArrayList<String>> backtrackList = new LinkedList<ArrayList<String>>();
    private Set<String> dict;
    private String end;
    private int level = 1;
    private int len = Integer.MAX_VALUE;
    private List<List<String>> rst = new ArrayList<List<String>>();

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null || start.length() != end.length()) {
    		return rst;
    	}
    	this.dict = dict;
    	this.end = end;
    	ArrayList<String> head = new ArrayList<String>();
    	head.add(start);
    	q.offer(start);
    	backtrackList.offer(head);
    	while(!q.isEmpty()) {//BFS
    		int size = q.size();//Fix size
    		level++;
    		for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
	    		String str = q.poll();
	    		ArrayList<String> list = backtrackList.poll();
	    		validateMutations(str, list);
	    	}//END FOR K
    	}//END WHILE

    	List<List<String>> minRst = new ArrayList<List<String>>();
    	for (int i = 0; i < rst.size(); i++) {
    		if (rst.get(i).size() == len) {
    			minRst.add(rst.get(i));
    		}
    	}
    	return minRst;
    }


    public void validateMutations(String str, ArrayList<String> list) {
    	if (list.size() > len) {//No need to digger further if list is already greater than min length
    		return;
    	}
    	for (int i = 0; i < str.length(); i++) {//Alternate each letter position
			for (int j = 0; j < 26; j++) {//Alter 26 letters
                if (str.charAt(i) == (char)('a' + j)) {
                    continue;
                }
				String newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);

				ArrayList<String> temp = (ArrayList<String>)list.clone();
				temp.add(newStr);
				if (dict.contains(newStr)) {
					if (newStr.equals(end)) {//Found end
						len = Math.min(len, level);
						rst.add(temp);
					} else {
						q.offer(newStr);
						backtrackList.offer(temp);
					}
				}
			}//END FOR J
		}//END FOR I
    }
}



//Solution from NineChapter, commented:

/*
public class Solution {
    public List<List<String>> findLadders(String start, String end,Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
 
        bfs(map, distance, start, end, dict);
        //Now at this step, we have: 
        //a distance map of all mutated string from start, 
        //a map of mutation and its list of 'pre-mutation' string
        //dict: includes start and end
        List<String> path = new ArrayList<String>();
        
        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }
    //crt: is not necessarily the 'end', since this is a recursive method
    //crt at first is the 'end' string, then it's switching to other strings inorder to finally matches 'start'
    void dfs(List<List<String>> ladders, List<String> path, String crt,
            String start, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {//Now, finally if the crt makes it to start and equals to start, we found a match.
            Collections.reverse(path);//We had a reversed path
            ladders.add(new ArrayList<String>(path));//add
            Collections.reverse(path);//need to reverse it back, becase we need 'path' for more recursive calls.
        } else {
            for (String next : map.get(crt)) {//Find all possible tranformations/mutations that can turn itself into crt: we have a ArrayList of candidates (pre-mutated strings)
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { //if that mutation is just 1 step different, that's good, which means these mutation takes minimum of 1 step to happen. Note: we are comparing the distance to start point.
                    dfs(ladders, path, next, start, distance, map);//If that's the case, pass varibles to next level: use new path (with crt added), and use the 'next' string (which is 1 step closer to start) for next level of searching.
                }
            }           
        }
        path.remove(path.size() - 1);//remove that ending crt, since 'path' is shared in recursive methods, need to keep it cleaned.
    }
//map: each string in the dict (including start, end) represents a key, and the value is a ArrayList of string.
    void bfs(Map<String, List<String>> map, Map<String, Integer> distance, String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);//Distance: key: str, value: distance value from start.
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();//Get head of queue, the item currently we are looking at. Called X.

            List<String> nextList = expand(crt, dict);//generate all possible mutations (must exist in dict)
            for (String next : nextList) {//For all mutations
                map.get(next).add(crt);//append X to end of all of the mutated string (this will become a reverse order). This creates a path of mutation
                if (!distance.containsKey(next)) {//If that mutated string never occured:
                    distance.put(next, distance.get(crt) + 1);//add distance to this mutation. This is fixed and will never change, btw. This becomes a list of all mutations and distance from start.
                    q.offer(next);//Add this mutation to queue.
                }
            }
        }
    }
//all possible mutations based on 1 str polled from the queue.
    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }
        return expansion;
    }
}


*/