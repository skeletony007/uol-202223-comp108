class COMP108W05 {

	// print the content of an array of size n
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// data[] is an array, n is size of array, key is the number we want to find
	static void seqSearch(int[] data, int n, int key) {
		int i, count;
		boolean found;

		// start sequential search on the array called data[]
		found = false;	// to indicate if the number is found
		i = 0;		// an index variable to iterate through the array
		count = 0;	// to count how many comparisons are made
		while (i<n && found==false) {
			if (data[i] == key) {
				found = true;
			} else {
				i = i+1;
			}
			count = count+1;
		}
		System.out.print("The number " + key + " is ");
		if (found == false)
			System.out.print("not ");
		System.out.println("found by sequential search and the number of comparisons used is " + count);
	}

	// data[] is an array in ascending order, n is size of array, key is the number we want to find
	// You can assume that data[] is already sorted
	static void binarySearch(int[] data, int n, int key) {
		int count = 0;

		int first = 0;
		int last = n - 1;
		boolean found = false;

		while (first <= last && found == false) {
			int mid = (first + last) / 2;
			if (data[mid] == key) {
				found = true;
			} else if (data[mid] < key) {
				first = mid + 1;
			} else {
				last = mid - 1;
			}
			count++;
		}

		System.out.print("The number " + key + " is ");
		if (found == false)
			System.out.print("not ");
		System.out.println("found by binary search and the number of comparisons used is " + count);
	}

	// print the smallest number in the array of size n
	static void findMin(int[] data, int n) {
		int i, min;

		min = data[0];
		i = 1;
		while (i < n) {
			if (data[i] < min)
				min = data[i];
			i++;
		}
		System.out.println("The smallest number is " + min + ".");
	}

	// print the largest number in the array of size n
	static void findMax(int[] data, int n) {
		int i, max;

		max = data[0];
		i = 1;
		while (i < n) {
			if (data[i] > max)
				max = data[i];
			i++;
		}
		System.out.println("The largest number is " + max + ".");
	}

	// print the second largest number in the array of size n
	static void findSecondMax(int[] data, int n) {
		int i, max, secondMax;

		max = data[0];
		secondMax = data[0];
		i = 1;
		while (i < n) {
			if (data[i] > max) {
				secondMax = max;
				max = data[i];
			} else if (data[i] > secondMax && data[i] != max) {
				secondMax = data[i];
			}
			i++;
		}
		System.out.println("The largest number is " + max + " and the second largest number is " + secondMax);
	}


}
