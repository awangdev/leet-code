//Remove node from linkedlist
   //H L L L 0
  Node current = head;//L
  Node node = current;
  Node prev = new Node('0');
  prev.next = node;
  while (current != null) {//1L
    
    while (node.next != null) {//21L
      if (current.value == node.next.value ) {
        prev.next = prev.next.next;
      }
      prev = prev.next;
      node = node.next;
      
    }
    prev = current;
    current = current.next;
    node = current;
  }
  return head;
  