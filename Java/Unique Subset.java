/*
Unique Subset:
Add one line of code from subset:
Only when the output does not contain the newList, we add a new one in. This ensures the uniqueness.
*/

public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> source) {
         // write your code here
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> newList = new ArrayList<Integer>();
        Collections.sort(source);
        subsetHelper(0, source, newList, output);
        return output;
}
    
public void subsetHelper(int pos, 
        ArrayList<Integer> source, ArrayList<Integer> newList, 
        ArrayList<ArrayList<Integer>> output){
        if (!output.contains(newList)){
            output.add(new ArrayList<Integer>(newList));
        }
        
        for (int i = pos; i < source.size(); i++){
            newList.add(source.get(i));
            subsetHelper(i + 1, source, newList, output);
            newList.remove(newList.size() - 1);
        }
 }

