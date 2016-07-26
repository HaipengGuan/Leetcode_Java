package stack;

/**
 * 71. Simplify Path
 * 
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * @see https://leetcode.com/problems/simplify-path/
 *
 */
public class SimplifyPath {
	
    public String simplifyPath(String path) {
		String[] list = path.split("/");
		String[] stack = new String[list.length];
		int top = -1;
		for (String string : list) {
			if (string.length() == 0 || string.equals(".")) continue;
			else if (!string.equals("..")) stack[++top] = string;
			else if (top > -1) top--;
		}
		if (top <= -1) return "/";
		StringBuffer res = new StringBuffer();
		for (int i = 0; i <= top; i++) {
			res.append('/');
			res.append(stack[i]);
		}
		return new String(res);
    }
    
    public static void main(String[] args) {
    	SimplifyPath path = new SimplifyPath();
    	System.out.println(path.simplifyPath("/"));
    	System.out.println(path.simplifyPath("/../"));
    	System.out.println(path.simplifyPath("home"));
    	System.out.println(path.simplifyPath("/home"));
    	System.out.println(path.simplifyPath("home/"));
    	System.out.println(path.simplifyPath("/home/"));
    	System.out.println(path.simplifyPath("/a/./b/../..//c/"));
    	System.out.println(path.simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///")); // /e/f/g
	}
}
