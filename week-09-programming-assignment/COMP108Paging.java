// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// noEvict():
// Inner loop:         function: p          Big-O notation: O(p)
// Outer loop:         function: n          Big-O notation: O(n)
// Total(*):           function: np         Big-O notation: O(np)
//
// The inner loop iterates O(p) times
// The outer loop iterates O(n) times
// For each outer loop iteration, we need to iterate the inner loop
// Hence the total(*) time complexity is O(np)
//
// (*)Also considering arrayToString():
// Second outer loop:  function: p          Big-O notation: O(p)
//
// The total time complexity remains O(np), since arrayToString() is linear
// whereas total(*) is polynomial
//
// evictLRU():
// First inner loop:   function: p          Big-O notation: O(p)
// Second inner loop:  function: p          Big-O notation: O(p)
// Outer loop:         function: n          Big-O notation: O(n)
// Total(*):           function: 2np        Big-O notation: O(np)
//
// The first and second inner loop both iterate O(p) times
// The outer loop iterates O(n) times
// For each outer loop iteration, we need to iterate both the inner loops
// Hence the total(*) time complexity is O(np)
//
// (*)Also considering arrayToString():
// Second outer loop:  function: p          Big-O notation: O(p)
//
// The total time complexity remains O(np), since arrayToString() is linear
// whereas total(*) is polynomial
//

class COMP108Paging {


	// no eviction
	// Aim:
	// do not evict any page
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108PagingOutput noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			boolean hit = false;

			for (int j = 0; j < cSize; j++) {
				int cacheEntry = cArray[j];

				if (cacheEntry == request) {
					hit = true;
					break;
				}
			}

			if (hit) {
				output.hitCount++;
				output.hitPattern += "h";
			} else {
				output.missCount++;
				output.hitPattern += "m";
			}
		}

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// evict LRU
	// Aim:
	// if a request is not in cache, evict the page that hasn't been used for the longest amount of time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108PagingOutput evictLRU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();

		int[] lru = cArray;

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			boolean hit = false;

			for (int j = 0; j < cSize; j++) {
				int cacheEntry = cArray[j];

				if (cacheEntry == request) {
					hit = true;
					break;
				}
			}

			int[] newLru = new int[cSize];

			if (hit) {
				output.hitCount++;
				output.hitPattern += "h";

				hit = false;

				for (int j = 0; j < cSize; j++) {
					if (hit)
						newLru[j - 1] = lru[j];
					else {
						if (lru[j] == request) {
							hit = true;
							continue;
						}
						newLru[j] = lru[j];
					}
				}
			} else {
				output.missCount++;
				output.hitPattern += "m";

				for (int j = 0; j < cSize; j++) {
					if (cArray[j] == lru[0])
						cArray[j] = request;
					if (j != cSize - 1) {
						newLru[j] = lru[j + 1];
					}
				}
			}

			if (cSize != 0)
				newLru[cSize - 1] = request;
			lru = newLru;
		}

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	static String arrayToString(int[] array, int size) {
		String outString="";

		for (int i=0; i<size; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
	}

}

