M
1519232186

方法1: Iterative with BFS using queue.

方法2: Recursively adding chars per digit

```
/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Cellphone. Picture:http://www.lintcode.com/en/problem/letter-combinations-of-a-phone-number/

Example
Given "23"

Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

Note
Although the above answer is in lexicographical order, your answer could be in any order you want.

Tags Expand 
String Backtracking Recursion Facebook Uber
*/

/*
Thoughts:
1. Have to use all letters
2. DFS: can skip a letter but need to use it later for other combinations
3. When string length matches target length, add string.
4. When input digits are used up, return final result.
*/
class Solution {
    final Map<Character, List<String>> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        final List<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        
        //Prepare map
        map.put('2', Arrays.asList("a","b","c"));
        map.put('3', Arrays.asList("d","e","f"));
        map.put('4', Arrays.asList("g","h","i"));
        map.put('5', Arrays.asList("j","k","l"));
        map.put('6', Arrays.asList("m","n","o"));
        map.put('7', Arrays.asList("p","q","r","s"));
        map.put('8', Arrays.asList("t","u","v"));
        map.put('9', Arrays.asList("w","x","y","z"));
        
        List<String> list = new ArrayList<>();
        dfs(rst, list, digits.toCharArray(), 0);
        
        return rst;
    }
    
    public void dfs(List<String> rst, List<String> list, char[] digit, int level) {
        if (list.size() == digit.length) {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str);
            }
            rst.add(sb.toString());
            return;
        }
        
        List<String> letters = map.get(digit[level]);
        for (String letter : letters) {
            list.add(letter);
            dfs(rst, list, digit, level + 1);
            list.remove(list.size() - 1);
        }
    }
}


/*3.7.2016 recap. Iterative way using BFS*/
//Hashmap the list of chars.
//Use queue to append all possibile candidates. BFS
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        //Init map
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        // Init 1 digits and the chars in queue
        Queue<String> queue = new LinkedList<String>();
        char c = digits.charAt(0);
        String s = map.get(c);
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i) + "");
        }
        
        int size = 0;
        for (int i = 1; i < digits.length(); i++) {//iteratve all numbers
            c = digits.charAt(i);
            s = map.get(c);
            size = queue.size();
            for (int j = 0; j < size; j++) {//iteratve old queue
                String str = queue.poll();
                for (int k = 0; k < s.length(); k++) {//iteratve possibile chars per number key
                    queue.offer(str + s.charAt(k));
                }
            }
        }
        while (!queue.isEmpty()) {
            rst.add(queue.poll());
        }
        
        return rst;
    }
}


/*
    Thoughts: have done this on Leetcode.
    map integer to letters
    combination of existing letters (by pressing fist number) with next number's letters.
    put combinations into queue, reuse the queue.
    finally, output into arraylist

    NON-recursive/iterative: use a queue. (Done this one Leetcode)

    This time, use recursive:
    pass along rst, list, level number, digits, 
    for (combine list with all next level's candidates, map)
    when level number == digits.length(), return the candidate into rst.
*/
public class Solution {
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        ArrayList<String[]> map = new ArrayList<String[]>();
        map.add(new String[]{});//key 0: nothing
        map.add(new String[]{});//key 1: nothing
        map.add(new String[]{"a","b","c"});
        map.add(new String[]{"d","e","f"});
        map.add(new String[]{"g","h","i"});
        map.add(new String[]{"j","k","l"});
        map.add(new String[]{"m","n","o"});
        map.add(new String[]{"p","q","r","s"});
        map.add(new String[]{"t","u","v"});
        map.add(new String[]{"w","x","y","z"});

        ArrayList<String> list = new ArrayList<String>();
        helper(rst, list, map, digits, 0);

        return rst;
    }

    public void helper(ArrayList<String> rst, ArrayList<String> list, 
        ArrayList<String[]> map, String digits, int level){
        //If level is finished, compress into string
        if (level == digits.length()) {
            StringBuffer sb = new StringBuffer();
            for (String s : list) {
                sb.append(s);
            }
            rst.add(sb.toString());
            return;
        }
        //For a specific list of candidates, face the level of chars
        int num = Integer.parseInt(digits.substring(level, level + 1));
        String[] strs = map.get(num);

        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);
            helper(rst, list, map, digits, level + 1);
            list.remove(list.size() - 1);
        }
    }

}







//Iterative: 
//Use 1 queue
// and optimize a bit
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");map.put(3, "def");
        map.put(4, "ghi");map.put(5, "jkl");map.put(6, "mno");
        map.put(7, "pqrs");map.put(8,"tuv");map.put(9,"wxyz");
        
        Queue<String> queue = new LinkedList<String>();
        
        //init
        int index = 0;
        int digit = Integer.parseInt(digits.substring(index, index + 1));
        String keys = map.get(digit);
        index++;
        
        for (int i = 0; i < keys.length(); i++) {
            queue.offer(keys.substring(i,i+1));
        }
        int size = queue.size();
        
        while (index < digits.length() && !queue.isEmpty()) {
            String str = queue.poll();
            digit = Integer.parseInt(digits.substring(index, index + 1));
            keys = map.get(digit);
            for (int i = 0; i < keys.length(); i++) {
                queue.offer(str + keys.substring(i,i+1));
            }
            size--;
            if (size == 0 && index < digits.length() - 1) {
                index++;
                size = queue.size();
            }
        }//end while
        
        while (!queue.isEmpty()) {
            rst.add(queue.poll());
        }
        
        return rst;
    }
}












```