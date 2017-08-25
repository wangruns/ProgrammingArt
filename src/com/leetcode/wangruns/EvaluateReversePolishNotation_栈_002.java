package com.leetcode.wangruns;

import java.util.LinkedList;

//2��evaluate-reverse-polish-notation[ջ]
/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are+,-,*,/. Each operand may be an integer or another
 * expression. Some examples. ["2", "1", "+", "3", "*"] -> ((2 + 1)* 3) -> 9.
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6.
 * 
 * ��������ջ��������ģ����
 */
public class EvaluateReversePolishNotation_ջ_002 {
	public int evalRPN(String[] tokens) {
		int len = tokens.length;
		LinkedList<String> stack = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			if (tokens[i].equals("*") || tokens[i].equals("/") || tokens[i].equals("+") || tokens[i].equals("-")) {
				int operand1 = Integer.valueOf(stack.pop());
				int operand2 = Integer.valueOf(stack.pop());
				int tempRes = 0;
				if (tokens[i].equals("*"))
					tempRes = operand1 * operand2;
				else if (tokens[i].equals("/"))
					tempRes = operand2 / operand1;
				else if (tokens[i].equals("+"))
					tempRes = operand1 + operand2;
				else
					tempRes = operand2 - operand1;
				stack.push(String.valueOf(tempRes));
			} else
				stack.push(tokens[i]);
		}
		return Integer.valueOf(stack.pop());
	}
}
