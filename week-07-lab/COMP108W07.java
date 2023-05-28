class COMP108W07 {

	public Node head, tail;

	public COMP108W07() {
		head = null;
		tail = null;
	}

	// sequential search if key is in the list
	// return true or false accordingly
	public boolean seqSearchList(int key) {
		boolean found = false;
		Node curr;

		curr = head;

		while (curr != tail.next) {
			if (curr.data == key)
				found = true;
			curr = curr.next;
		}

		return found;
	}

	// sequential search to count how many times key appears is in the list
	// return the count
	public int countList(int key) {
		int count=0;
		Node curr;

		curr = head;

		while (curr != tail.next) {
			if (curr.data == key)
				count = count + 1;
			curr = curr.next;
		}

		return count;
	}

	// finding the minimum number in the list
	// return the minimum
	public int searchMin() {
		int min=Integer.MAX_VALUE;
		Node curr;
		int data;

		curr = head;

		while (curr != tail.next) {
			data = curr.data;
			if (data < min)
				min = data;
			curr = curr.next;
		}

		return min;
	}

	// finding the maximum number in the list
	// return the maximum
	public int searchMax() {
		int max=Integer.MIN_VALUE;
		Node curr;
		int data;

		curr = head;

		while (curr != tail.next) {
			data = curr.data;
			if (data > max)
				max = data;
			curr = curr.next;
		}

		return max;
	}

	// insert newNode to the head of the list
	public void insertHead(Node newNode) {
		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// insert newNode to the tail of the list
	public void insertTail(Node newNode) {
		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// this will turn the list into a String from head to tail
	// This is only here to ease outputing the list content.
	public String headToTail() {
		Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// this will turn the list into a String from tail to head
	// This is only here to ease outputing the list content.
	public String tailToHead() {
		Node curr;
		String outString="";

		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

}
