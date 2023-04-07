//
// Enter your name: Tate Enticknap
// Enter your student ID: 201656531
//
import java.util.*;
import java.io.*;

class COMP108W04 {

	static void sumFromOne(int number) {
		int i;
		int sum = 0;

		i = 1;
		sum = 0;
		while (i <= number) {
			sum = sum + i;
			i = i + 1;
		}
 		System.out.println("Sum from 1 to " + number + " equals to " + sum);

	}

	// fill in this method for Task 1
	// output the sum from numberX to numberY
	// Hint: refer to sumFromOne()
	static void sumFromTo(int numberX, int numberY) {
		int i;
		int sum;

		i = numberX;
		sum = 0;
		while (i <= numberY) {
			sum = sum + i;
			i = i + 1;
		}
		System.out.println("Sum from " + numberX + " to " + numberY + " equals to " + sum);

	}

	static void isFactor(int numberX, int numberY) {
		if (numberX % numberY == 0)
			System.out.println(numberY + " is a factor of " + numberX);
		else
			System.out.println(numberY + " is not a factor of " + numberX);
	}

	// fill in this method for Task 2
	// finding all multiples of numberX that are factors of numberY
	static void multipleFactor(int numberX, int numberY) {
		int i;

		i = numberX;
		while (i <= numberY) {
			if (numberY % i == 0)
				System.out.print(i + " ");
			i = i + numberX;
		}
		System.out.println();

	}

	// Aim: to output all common multiples of x and y up to 100
	// Find the bug and fix it by altering ONE line of code
	static void bugOne(int numberX, int numberY) {
		int i, bound;

		i = numberY; // I changed this line from `i = 1;`
		bound = 100;
		System.out.print("Common Multiples up to " + bound + ": ");
		while (i <= bound) {
			if (i%numberX == 0)
				System.out.print(i + " ");
			i+=numberY;
		}
		System.out.println();
	}

}
