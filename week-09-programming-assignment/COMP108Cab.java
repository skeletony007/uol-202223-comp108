//
// Coded by Prudence Wong 2021-03-06
// Updated 2023-02-25
//
// Note: You are allowed to add additional methods if you need.
// Name:
// Student ID:
//
// Time Complexity and explanation:
// f denotes initial cabinet size
// n denotes the total number of requests
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
//
// appendIfMiss():
//
// freqCount():
//

class COMP108Cab {

	public COMP108Node head, tail;

	public COMP108Cab() {
		head = null;
		tail = null;
	}

	// append to end of list when miss
	public COMP108CabOutput appendIfMiss(int rArray[], int rSize) {
		COMP108CabOutput output = new COMP108CabOutput(rSize);

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			boolean hit = false;

			COMP108Node curr;

			curr = head;

			while (curr != null && !hit) {
				output.compare[i]++;

				if (curr.data == request)
					hit = true;
				curr = curr.next;
			}

			if (!hit) {
				insertTail(new COMP108Node(request));

				output.missCount++;
				continue;
			}

			output.hitCount++;
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}

	// move the file requested so that order is by non-increasing frequency
	public COMP108CabOutput freqCount(int rArray[], int rSize) {
		COMP108CabOutput output = new COMP108CabOutput(rSize);

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			boolean hit = false;

			COMP108Node curr;

			curr = head;

			while (curr != null && !hit) {
				output.compare[i]++;

				if (curr.data == request)
					hit = true;
				curr = curr.next;
			}

			if (!hit) {
				insertTail(new COMP108Node(request));

				output.missCount++;
				continue;
			}

			curr.freq++;
			COMP108Node prevNode = curr.prev;
			COMP108Node nextNode = curr.next;

			while (curr != head && curr.freq > prevNode.freq) {
				// Update the next node's prev pointer to point to the previous node
				if (nextNode != null)
					nextNode.prev = prevNode;

				// Update the previous node's next pointer to point to the next node
				prevNode.next = nextNode;

				// Update curr's next and prev pointers to swap it with the previous node
				curr.prev = prevNode.prev;
				curr.next = prevNode;

				// Update the previous node's prev pointer to point to curr
				prevNode.prev = curr;

				// Update the previous node's next pointer if it is not the first node in the list
				if (curr.prev != null)
					curr.prev.next = curr;
				else
					head = curr;

				prevNode = curr.prev;
				nextNode = curr.next;
			}

			output.hitCount++;
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		output.cabFromHeadFreq = headToTailFreq();
		return output;
	}

	// DO NOT change this method
	// insert newNode to head of list
	public void insertHead(COMP108Node newNode) {

		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to tail of list
	public void insertTail(COMP108Node newNode) {

		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// delete the node at the head of the linked list
	public COMP108Node deleteHead() {
		COMP108Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
			else
				head.prev = null;
		}
		return curr;
	}

	// DO NOT change this method
	// empty the cabinet by repeatedly removing head from the list
	public void emptyCab() {
		while (head != null)
			deleteHead();
	}


	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTail() {
		COMP108Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	// Only to be used for output, do not use it to manipulate the list
	public String tailToHead() {
		COMP108Node curr;
		String outString="";

		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the frequency of the list nodes into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	public String headToTailFreq() {
		COMP108Node curr;
		String outString="";

		curr = head;
		while (curr != null) {
			outString += curr.freq;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

}
