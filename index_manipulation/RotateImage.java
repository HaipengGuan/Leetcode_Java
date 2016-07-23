package index_manipulation;

import java.util.Arrays;

/**
 * 48. Rotate Image
 * 
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: 
 * Could you do this in-place?
 * 
 * @see https://leetcode.com/problems/rotate-image/
 *
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
    	int n = matrix.length;
    	if (n == 0) return;
    	int rowBegin = 0, rowEnd = n - 1;
    	int colBegin = 0, colEnd = n - 1;
    	int temp;
    	while (rowBegin < rowEnd) {
			for (int i = 0; i < colEnd - colBegin; i++) {
				temp = set(matrix, rowBegin+i, colEnd, matrix[rowBegin][colBegin+i]);
				temp = set(matrix, rowEnd, colEnd-i, temp);
				temp = set(matrix, rowEnd-i, colBegin, temp);
				set(matrix, rowBegin, colBegin+i, temp);
			}
			rowBegin++; colBegin++;
			rowEnd--; colEnd--;
		}
    }
    
    private int set(int[][] matrix, int i, int j, int val) {
		int temp = matrix[i][j];
		matrix[i][j] = val;
		return temp;
	}
    
    private static int[][] getMatrix(int N) {
		int num = 1;
		int[][] m = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				m[i][j] = num++;
			}
		}
		return m;
	}
    
	public static void main(String[] args) {
		RotateImage rotate = new RotateImage();
		int[][] m = null;
		for (int i = 0; i < 7; i++) {
			m = getMatrix(i+1);
			rotate.rotate(m);
			for (int j = 0; j < m.length; j++) {
				System.out.println(Arrays.toString(m[j]));
			}
			System.out.println("------------");
		}
	}

}
