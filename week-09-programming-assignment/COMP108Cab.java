//
// Coded by Prudence Wong 2021-03-06
// Updated 2023-02-25
//
// Note: You are allowed to add additional methods if you need.
// Name: Tate Enticknap
// Student ID: 201656531
//
// Time Complexity and explanation:
// f denotes initial cabinet size
// n denotes the total number of requests
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
//
// appendIfMiss():
// Inner loop:         function: f          Big-O notation: O(f)
// Outer loop:         function: n          Big-O notation: O(n)
// Total(*):           function: fn         Big-O notation: O(fn)
//
// The inner loop iterates O(f) times
// The outer loop iterates O(n) times
// For each outer loop iteration, we need to iterate the inner loop
// Hence the  total(*) time complexity is O(fn)
//
// (*)Also considering headToTail() and tailToHead():
// Second outer loop:  function: d + f      Big-O notation: O(d + f)
// Third outer loop:   function: d + f      Big-O notation: O(d + f)
// Total:              function: fn+2(d+f)  Big-O notation O(fn)
//
// The total time complexity remains O(fn), since both headToTail(), and
// tailToHead() are linear whereas total(*) is polynomial
//
// freqCount():
// Fist inner loop:    function: d + f      Big-O notation: O(d + f)
// Second inner loop:  function: d + f      Big-O notation: O(d + f)
// Outer loop:         function: n          Big-O notation: O(n)
// Total(*):           function: 2n(d + f)  Big-O notation: O(n(d + f))
//
// The first and second inner loop both iterate O(d + f) times
// The outer loop iterates O(n) times
// For each outer loop iteration, we need to iterate both the inner loops
// Hence the total(*) time complexity is O(n(d + f))
//
// (*)Also considering headToTail(), tailToHead() and headToTailFreq():
// Second outer loop:  function: d + f      Big-O notation: O(d + f)
// Third outer loop:   function: d + f      Big-O notation: O(d + f)
// Fourth outer loop:  function: d + f      Big-O notation: O(d + f)
// Total:              function: (2n+3)(d+f)Big-O notation O(n(d + f))
//
// The total time complexity remains O(n(d + f)), since each of headToTail(),
// tailToHead() and headToTailFreq() are linear whereas total(*) is polynomial
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

				if (curr.data == request) {
					hit = true;
					break;
				}
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

				if (curr.data == request) {
					hit = true;
					break;
				}
				curr = curr.next;
			}

			if (!hit) {
				insertTail(new COMP108Node(request));

				output.missCount++;
				continue;
			}

			curr.freq++;

			if (curr != head) {
				COMP108Node prevNode = curr.prev;

				while (prevNode != null && curr.freq > prevNode.freq) {
					prevNode = prevNode.prev;
				}

				if (prevNode != curr.prev) {
					COMP108Node nextNode = (prevNode == null) ? head : prevNode.next;

					curr.prev.next = curr.next;
					if (curr.next == null)
						tail = curr.prev;
					else
						curr.next.prev = curr.prev;

					if (prevNode == null)
						head = curr;
					else
						prevNode.next = curr;

					curr.prev = prevNode;
					curr.next = nextNode;
					nextNode.prev = curr;
				}
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
