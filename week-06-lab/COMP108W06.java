//
// Enter your name: Tate Enticknap
// Enter your student ID: 201656531
//
// Question (b.iv):
//   What is the time complexity of `notExists()`?
// Answer:
//   The time complexity of the method `notExists()` is O(nm), where n
//   is the size of the requests report (`size1`), and m is the size of
//   the size of the database (`size2`). This is because the inner loop
//   iterates O(m) times, and the outer loop iterates O(n) times, and
//   for each outer loop iteration, we need to iterate the inner loop.
//
// Question (c.iv):
//   What is the time complexity of `count()`?
// Answer:
//   The time complexity of the method `count()` is O(nm), where n is the
//   size of the database (`size2`), and m is the size of the size of the
//   requests report (`size1`). This is because the inner loop iterates
//   O(m) times, and the outer loop iterates O(n) times, and for each outer
//   loop iteration, we need to iterate the inner loop.
//
import java.util.*;
import java.io.*;

class COMP108W06 {

	// print the content of an array of size n
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// Input: array1[] with size1 entries and array2[] with size2 entries
	// print all entries of array1[] that does not exist in array2[]
	static void notExists(int array1[], int size1, int array2[], int size2) {
		int i;
		int j;

		int movieId;
		boolean inDatabase;
		for (i=0; i < size1; i++) {
			movieId = array1[i];
			inDatabase = false;
			for (j=0; j < size2; j++) {
				if (array2[j] == movieId)
					inDatabase = true;
			}
			if (!inDatabase)
				System.out.print(movieId + " ");
		}
		System.out.println();
	}

	// Input: array1[] with size1 entries and array2[] with size2 entries
	// for each entry in array2[], count how many times it appears in array1[]
	static void count(int array1[], int size1, int array2[], int size2) {
		int i;
		int j;

		int movieId;
		int count;
		for (i=0; i < size2; i++) {
			movieId = array2[i];
			count = 0;
			for (j=0; j < size1; j++) {
				if (array1[j] == movieId)
					count = count + 1;
			}
			System.out.print(count + " ");
		}
		System.out.println();
	}

}
