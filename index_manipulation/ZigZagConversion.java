package index_manipulation;

/**
 * 6. ZigZag Conversion
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * string convert(string text, int nRows);
 * 
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 * @author guanhaipeng
 *
 */
public class ZigZagConversion {
	
    public String convert(String s, int numRows) {
		if (numRows == 1) return s;
		int n = s.length();
		char[] res = new char[n];
		int ind = 0, offset = 0, cycle = 2 * numRows - 2;
		for (int i = 0; i < numRows; i++) {
			offset = cycle - 2 * i;
			for (int j = i; j < n; j += cycle) {
				res[ind++] = s.charAt(j);
				if (j + offset < n && 0 < offset && offset < cycle) {
					res[ind++] = s.charAt(j + offset);
				}
			}
		}
    	return new String(res);
    }
    
    private static String getString(int n) {
		char[] res = new char[n];
		for (int i = 0; i < n; i++) {
			res[i] = (char) ('0' + i % 10);
		}
		return new String(res);
	}
    
    public static void main(String[] args) {
    	ZigZagConversion conversion = new ZigZagConversion();
    	String testStr = getString(20);
		System.out.println(conversion.convert(testStr, 1));
		System.out.println(conversion.convert(testStr, 2));
		System.out.println(conversion.convert(testStr, 3));
		System.out.println(conversion.convert(testStr, 4));
		System.out.println(conversion.convert(testStr, 5));
		System.out.println(conversion.convert(testStr, 6));
	}
}
