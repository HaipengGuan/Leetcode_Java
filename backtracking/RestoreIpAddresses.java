package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * 
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example: 
 * 
 * Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * @see https://leetcode.com/problems/restore-ip-addresses/
 *
 */
public class RestoreIpAddresses {

	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		dfs(s, 0, 0, "", res);
		return res;
	}
	
	private void dfs(String s, int pos, int layer, String cur, List<String> res) {
		if (layer > 4) { 
			return;
		} else if (layer == 4) {
			if (pos == s.length())
				res.add(cur.substring(0, cur.length() - 1));
		} else {
			int num = 0;
			StringBuffer temp = new StringBuffer(3);
			for (int i = pos; i < s.length() && i < pos + 3 && (i == pos || num != 0); i++) {
				char c = s.charAt(i);
				num = num * 10 + (c - '0');
				if (num > 255) break;
				temp.append(c);
				dfs(s, i + 1, layer + 1, cur + temp + '.', res);
			}
		}
	}

	// ----------------------------
	private static RestoreIpAddresses restore = new RestoreIpAddresses();
	
	private static void test(String s) {
		List<String> res = restore.restoreIpAddresses(s);
		for (String string : res) {
			System.out.println(string);
		}
		System.out.println("--------------");
	}
	
	public static void main(String[] args) {
		test("25525511135");
		test("0000");
		test("00000");
	}


}
