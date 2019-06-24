package com.org.bst;

public class DeleteNodesOutOfRange {
	
	
	public class Node{
		int data;
		Node left, right;
		
		public Node (int key){
			data = key;
			left = right = null;
		}
	}
	
	Node root;
	public void insert(int data)
	{
		root = insertNode(root, data);
	}
	
	public Node insertNode(Node root, int num)
	{
		Node ele = new Node(num);
		if(root == null)
		{
			root = ele;
			return root;
		}
		if (num <= root.data)
			root.left = insertNode(root.left, num);
		else if(num > root.data)
			root.right = insertNode(root.right, num);
		return root;
	}
	
	public void inorderTraversal(){
		inorder(root);
	}
	
	void inorder(Node root) {
	     if (root != null) {
	         inorder(root.left);
	         System.out.println(root.data);
	         inorder(root.right);
	     }
	 }
	
	public void removeOutOfRange(Node root, int min, int max){
		root = removeOutOfRangeRec(root, min, max);
	}
	
	public Node removeOutOfRangeRec(Node root, int min, int max){
		if(root == null)
			return null;
		root.right = removeOutOfRangeRec(root.right, min, max);
		root.left = removeOutOfRangeRec(root.left, min, max);
		if(root.data < min){
			Node temp = root.right;
			return temp;
		}
			
		else if(root.data > max){
			Node temp = root.left;
			return temp;
		}
		return root;
	}
	public static void main(String[] args) {
		DeleteNodesOutOfRange bst = new DeleteNodesOutOfRange();
		bst.insert(10);
		bst.insert(15);
		bst.insert(1);
		bst.insert(11);
		bst.insert(0);
		bst.insert(4);
		System.out.println("Inorder traversal: ");
		bst.inorderTraversal();
		bst.removeOutOfRange(bst.root, 4, 15);
		System.out.println("Inorder traversal post deletion: ");
		bst.inorderTraversal();
	}
}
