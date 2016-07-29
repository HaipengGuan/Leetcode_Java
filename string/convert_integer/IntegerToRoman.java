package string.convert_integer;

/**
 * 12. Integer to Roman
 * 
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @see https://leetcode.com/problems/integer-to-roman/
 *
 */
public class IntegerToRoman {
	
	private final static String[][] S = {
        {"","I","II","III","IV","V","VI","VII","VIII","IX"},
        {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
        {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
        {"","M","MM","MMM"},	
	};
	
    public String intToRoman(int num) {
    	return S[3][num/1000%10] + S[2][num/100%10] + S[1][num/10%10] + S[0][num%10];
    }

    // ----------------------------
    private static void test() {
    	IntegerToRoman toRoman = new IntegerToRoman();
    	RomanToInteger toInteger = new RomanToInteger();
		for (int i = 1; i < 4000; i++) {
			if (toInteger.romanToInt(toRoman.intToRoman(i)) ==  i) {
				System.out.println(i + ": PASS");
			} else {
				System.out.println(i + ": NOT PASS");
			}
		}
	}
    
    public static void main(String[] args) {
		test();
	}
}

// ----------------------------
// works for any number.
class UniversalMethod extends IntegerToRoman {
	
	private final static int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
	private final static String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	
    public String intToRoman(int num) {
    	StringBuffer buffer = new StringBuffer();
    	for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				buffer.append(strs[i]);
			}
		}
    	return buffer.toString();
    }
	
}