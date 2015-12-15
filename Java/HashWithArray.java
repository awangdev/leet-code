/*
	Self Test:
	Implement HashTable with just array and integer.

	Thoughts:
	A simple approach is to % size of the array, if the key exist, move 1 slot over.

	A bug will be: when exceeds the size of array, there will be no avialable space,
	and it'll run into error.

	Inspired here :http://www.algolist.net/Data_structures/Hash_table/Simple_example
	1. create a entry class.
	2. hash the key, and put Entry into that hased index.
*/

Class Entry{
	int key;
	int value; 
	public Entry(int key, int value) {
		this.key = key;
		this.value = value;
	}

	public getKey(){
		return this.key;
	}

	public getValue() {
		return this.value;
	}
}

Class HashMap {
	int[] table;
	int SIZE = 128;
	public HashMap(){
		table = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			table[i] = null;
		}
	}

	public void put(int key, int value){
		int hash = key % SIZE;
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + 1) % SIZE;
		}
		table[key] = new Entry(key, value);
	}

	public int get(int key) {
		int hash = key % SIZE;
		while (table[hash] != null && table[hash].getKey() != key) {
			hash = (hash + 1) % SIZE;
		}
		if (table[hash] == null) {
			return -1;
		}
		return table[hash].getValue();
	}
}








