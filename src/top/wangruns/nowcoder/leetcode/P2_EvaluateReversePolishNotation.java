package top.wangruns.nowcoder.leetcode;

import java.util.Stack;

/**
 * 
 * 题目描述
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are+,-,*,/. Each operand may be an integer or another expression.
Some examples: 
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

分析
看到这个示例，可以选择用栈来操作，把符号依次入栈，遇到计算符号就弹出
最近两个计算，然后将结果继续入栈
 *
 */
public class P2_EvaluateReversePolishNotation {

	public int evalRPN (String[] tokens) {
		Stack<String> stack=new Stack<>();
		for(int i=0;i<tokens.length;i++) {
			if(tokens[i].equals("+")||tokens[i].equals("-")||tokens[i].equals("*")||tokens[i].equals("/")) {
				Integer operand1=Integer.valueOf(stack.pop());
				Integer operand2=Integer.valueOf(stack.pop());
				int tempRes=0;
				if(tokens[i].equals("+")) tempRes=operand2+operand1;
				else if(tokens[i].equals("-")) tempRes=operand2-operand1;
				else if(tokens[i].equals("*")) tempRes=operand2*operand1;
				else tempRes=operand2/operand1;
				stack.push(tempRes+"");
			}else stack.push(tokens[i]);
		}
		return Integer.valueOf(stack.peek());
	}
}
