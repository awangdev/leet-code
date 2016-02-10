/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

Hide Company Tags Uber Facebook
Hide Tags Backtracking String
Hide Similar Problems (M) Generate Parentheses (M) Combination Sum
*/

/*
	Thoughts:
	basic: use 2 queue. one queue as base. the other is result. swap the base and result when based is all poll() out.
*/
//Use 2 queues
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
        
        Queue<String> base = new LinkedList<String>();
        Queue<String> queue = new LinkedList<String>();
        
        //init
        int index = 0;
        int digit = Integer.parseInt(digits.substring(index, index + 1));
        char[] arr = map.get(digit).toCharArray();
        index++;
        
        for (char c : arr) {
            base.offer(c+"");
        }
        if (index == digits.length()) {
            queue = base;
        }

        while (index < digits.length() && !base.isEmpty()) {
            String str = base.poll();
            digit = Integer.parseInt(digits.substring(index, index + 1));
            arr = map.get(digit).toCharArray();
            for (char key : arr) {
                queue.offer(str + key);
            }

            if (base.isEmpty() && index < digits.length() - 1) {
                Queue<String> temp = base;
                base = queue;
                queue = temp;
                index++;
            }
        }//end while
        
        while (!queue.isEmpty()) {
            rst.add(queue.poll());
        }
        
        return rst;
    }
}


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

