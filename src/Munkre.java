import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * @author zhuofu
 */
public class Munkre {

	public void demo() {
		int[][] input = { { 1, 2, 3 ,4}, { 2, 4, 3 ,2}, { 3, 2, 2,3 } };
		int[] result = Munkre(input, 3);
		for (int i = 0; i < result.length; i++)
			System.out.println(result[i] + " " + input[i][result[i]]);

	}

	/**
	 * 
	 * @param input_data
	 *            The input matrix representing the initial cost
	 * @param n
	 *            Dimension of the matrix
	 * @return Positions of stared zeros
	 */
	public int[] Munkre(int[][] input_data, int n) {
		int[][] input = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				input[i][j] = input_data[i][j];
			}

		/********************
		 * Structure definition & initialization
		 ********************/
		// covered columns&rows:
		// 0: not covered; 1: covered
		int[] covered_columns = new int[n];
		int[] covered_rows = new int[n];
		// starred_zero_columns/rows:
		// the value is the idex of the row/column covered;
		// if no starred zero, the value is -1
		// Note: each line and column can only have one starred zero, that's why
		// we can use an array for starred zeros in column and row
		int[] starred_zero_in_columns = new int[n];
		int[] starred_zero_in_rows = new int[n];
		// prime_zero_in_rows[i] is a list of sorted prime zeros in the row
		LinkedList<Integer>[] prime_zero_in_rows = new LinkedList[n];

		for (int i = 0; i < n; i++) {
			LinkedList l = new LinkedList();
			prime_zero_in_rows[i] = l;
		}

		for (int i = 0; i < n; i++) {
			covered_columns[i] = 0;
			covered_rows[i] = 0;
			starred_zero_in_columns[i] = -1;
			starred_zero_in_rows[i] = -1;
		}

		/****************
		 * Initialization
		 ****************/
		for (int i = 0; i < n; i++) {
			// get the minimum integer of the row
			int min = min(input[i]);
			// substract min from every number in the row
			for (int j = 0; j < n; j++) {
				input[i][j] = input[i][j] - min;
				// Assign star to zero if no zero star exists in the row or
				// column
				if (input[i][j] == 0) {
					if (starred_zero_in_rows[i] == -1
							&& starred_zero_in_columns[j] == -1) {
						starred_zero_in_rows[i] = j;
						starred_zero_in_columns[j] = i;
					}
				}
			}
		}

		/***********************
		 * Node assignment steps
		 ***********************/

		while (true) {
			/********
			 * STEP 1: for each column containing a starred zero, cover the
			 * column
			 ********/
			int k = 0;
			for (int i = 0; i < n; i++) {
				if (starred_zero_in_columns[i] != -1) {
					covered_columns[i] = 1;
					k++;
				}
			}
			if (k == n) {
				// goto done;
				break;
			}
			// goto step 2
			while (true) {
				/********
				 * STEP 2
				 ********/
				// check if input contains an uncovered zero
				int z0_x = -1;
				int z0_y = -1;
				for (int i = 0; i < n; i++) {
					int flag = 0;
					if (covered_rows[i] == 1)
						continue;
					for (int j = 0; j < n; j++) {
						if (covered_columns[j] == 1)
							continue;
						if (input[i][j] == 0) {
							z0_x = i;
							z0_y = j;
							flag = 1;
							break;
						}
					}
					if (flag == 1)
						break;
				}

				// if an uncovered zero is found
				if (z0_x != -1) {
					// Prime the found zero
					prime_zero_in_rows[z0_x].add(z0_y);
					// If there is no starred zero in the same row of Z0
					if (starred_zero_in_rows[z0_x] == -1) {
						// goto step 3
						/********
						 * STEP 3
						 ********/
						ArrayList<Point> S = new ArrayList();
						// Insert Z0 into S
						S.add(new Point(z0_x, z0_y));
						// if there is a starred zero in the column of Z0
						while (starred_zero_in_columns[z0_y] != -1) {
							// insert Z1 into S
							int z1_x = starred_zero_in_columns[z0_y];
							int z1_y = z0_y;
							S.add(new Point(z1_x, z1_y));
							// find a prime zero in the same row as z1, and
							// replace z0 with it
							if (prime_zero_in_rows[z1_x].isEmpty())
								break;
							z0_x = z1_x;
							z0_y = prime_zero_in_rows[z1_x].get(0);
							S.add(new Point(z0_x, z0_y));
						}
						// Unstar each starred zero in S and replace all primes
						// with stars
						// first unstar
						for (int i = 0; i < S.size(); i++) {
							Point p = S.get(i);
							// Odd numbers: un-star
							if (i % 2 != 0) {
								starred_zero_in_rows[p.x] = -1;
								starred_zero_in_columns[p.y] = -1;
							}
						}
						// then star
						for (int i = 0; i < S.size(); i++) {
							Point p = S.get(i);
							// Even numbers: star
							if (i % 2 == 0) {
								starred_zero_in_rows[p.x] = p.y;
								starred_zero_in_columns[p.y] = p.x;
							}
						}
						// Uncover every line in C and erase all other primes
						for (int i = 0; i < n; i++) {
							covered_columns[i] = 0;
							covered_rows[i] = 0;
							prime_zero_in_rows[i].clear();
						}
						// goto step 1
						break;
					} else {
						// Cover this row and uncover the column containing the
						// starred zero
						covered_rows[z0_x] = 1;
						covered_columns[starred_zero_in_rows[z0_x]] = 0;
						// goto step 2
						continue;
					}

				} else {
					// Get the smallest uncovered element e_min
					int e_min = Integer.MAX_VALUE;
					for (int i = 0; i < n; i++) {
						if (covered_rows[i] == 1)
							continue;
						for (int j = 0; j < n; j++) {
							if (covered_columns[j] == 1)
								continue;
							if (input[i][j] < e_min)
								e_min = input[i][j];
						}
					}
					// goto step 4
					/********
					 * STEP 4
					 ********/
					// Add e_min to every element that are covered twice in
					// input
					for (int i = 0; i < n; i++)
						for (int j = 0; j < n; j++) {
							if (covered_rows[i] == 1 && covered_columns[j] == 1)
								input[i][j] = input[i][j] + e_min;
							if (covered_rows[i] == 0 && covered_columns[j] == 0)
								input[i][j] = input[i][j] - e_min;
						}
					// goto step2
					continue;

				}
			}
		}

		/******
		 * DONE
		 ******/
		/**
		 * System.out.println("Munkre result"); for(int i=0; i<n; i++) { for(int
		 * j=0; j<n; j++) System.out.print(input[i][j]+" ");
		 * System.out.print("\n"); }
		 * 
		 * **/
		return starred_zero_in_rows;

	}

	private int min(int[] input) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (input[i] < min)
				min = input[i];
		}
		return min;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Munkre s = new Munkre();
		s.demo();
	}
}