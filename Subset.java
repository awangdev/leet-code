/*Subset:
Non-descending order: sort arrayList -> Collisions.sort( arrayList);
recursive
search
shared variable output. add new item into it all the time. However, every time needs to add a new item.
Use the concept: pick or not pick
*/

 public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> source) {
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
        output.add(new ArrayList<Integer>(newList));
        
        for( int i = pos; i < source.size(); i++){
            newList.add( source.get(i));
            subsetHelper(i+1, source, newList, output);
            newList.remove( newList.size() - 1 );
        }
    }

