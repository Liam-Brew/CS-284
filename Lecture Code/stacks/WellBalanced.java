public class WellBalanced {
	
	private static final String OPEN = "([{";
	private static final String CLOSE = ")]}";
	
	public static boolean isBalanced(String expr)  {
		int i=0;
		StackSLL<Character> s = new StackSLL<>();
		
		while (i<expr.length()) {
			if (isOpen(expr.charAt(i))) {
				s.push(expr.charAt(i));
			} else {  // Closing delimiter
				try {
					if (OPEN.indexOf(s.peek())==CLOSE.indexOf(expr.charAt(i))) {
						s.pop();
					} else {
						return false;
					}
				} catch (IllegalStateException e) {
					return false;
				}
				
			}
			i++;
		}
		return s.isEmpty();
	 }
	
	private static boolean isOpen(char ch) { 
		return OPEN.indexOf(ch) > -1;
	}
	
	private static boolean isClose(char ch) { 
		return CLOSE.indexOf(ch) > -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println(isBalanced("()[]"));
		System.out.println(isBalanced("([)]"));
	}
}
