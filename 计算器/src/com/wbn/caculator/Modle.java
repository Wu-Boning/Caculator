/**
 * 
 */
package com.wbn.caculator;

/**
 * 运算逻辑
 * 
 * @author 吴泊宁
 *
 */
public class Modle {

	/**
	 * 算式
	 */
	private String equation;

	/**
	 * 计算结果
	 */
	private float result;

	/**
	 * 栈的最大长度
	 */
	private int maxSize = 30;

	/**
	 * 构造方法
	 * 
	 * @param equation 算式
	 */
	public Modle(String equation) {
		this.equation = equation;
		caculate(this.equation);
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public Modle() {
	}

	/**
	 * 计算
	 */
	private void caculate(String equation) {
		// 定义一个栈
		char stack[] = new char[maxSize];

		// 存储算式
		char str[] = new char[maxSize];

		// 逆波兰表达式
		char ex[] = new char[maxSize];

		// 栈顶指针
		int top = 0;

		// 当前正在操作的字符
		char ch = '0';

		// 为算式添加结束符
		equation = equation + '#';

		int i = 1, j, t = 0;
		while (ch != '#') {
			ch = equation.charAt(i - 1);
			str[i] = ch;
			i++;
		}
		t = 1;
		i = 1;
		ch = str[i];
		i++;
		while (ch != '#') {
			switch (ch) {
			case '(': /* 判定为左括号 */
				stack[++top] = ch;
				break;
			case ')': /* 判定为右括号 */
				while (stack[top] != '(')
					ex[t++] = stack[top--];
				top--;
				break;
			case '+': /* 判定为加减号 */
			case '-':
				while (top != 0 && stack[top] != '(')
					ex[t++] = stack[top--];
				stack[++top] = ch;
				break;
			case '*': /* 判定为乘除号 */
			case '/':
				while (stack[top] == '*' || stack[top] == '/')
					ex[t++] = stack[top--];
				stack[++top] = ch;
				break;
			case '#':
				break;
			default:
				while (ch >= '0' && ch <= '9' || ch == '.') /* 判定为数字 */
				{
					ex[t++] = ch;
					ch = str[i++];

				}
				i--;
				ex[t++] = ' ';

			}
			ch = str[i];
			i++;
		}
		while (top != 0)
			ex[t++] = stack[top--];
		ex[t] = ' ';
		
//		for (j = 1; j < t; j++)
//			System.out.print(ex[j]);
//		System.out.println();

		// 对逆波兰式计算
		float stack2[] = new float[maxSize], d; /* 作为栈使用 */

		t = 1;
		top = 0; /* t为ex下标，top为stack下标 */
		ch = ex[t++];
		String digit;
		while (ch != ' ') {
			switch (ch) {
			case '+':
				stack2[top - 1] = stack2[top - 1] + stack2[top];
				top--;
				break;
			case '-':
				stack2[top - 1] = stack2[top - 1] - stack2[top];
				top--;
				break;
			case '*':
				stack2[top - 1] = stack2[top - 1] * stack2[top];
				top--;
				break;
			case '/':
				if (stack2[top] != 0)
					stack2[top - 1] = stack2[top - 1] / stack2[top];
				else {

				}
				top--;
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				digit = "";
				while (ch >= '0' && ch <= '9' || ch == '.') { /* 将数字字符转化为对应的数值 */
					digit += ch;
					ch = ex[t];
					t++;
				}
				d = Float.parseFloat(digit);
				top++;
				stack2[top] = d;
				break;
			default:
				top++;
				break;
			}
			ch = ex[t];
			t++;
		}
		result = stack2[top];

	}

	public String getResult() {
		caculate(equation);
		return String.format("%.3f", result);
	}

	public static void main(String[] args) {
		String string = "1-0.9";
		Modle modle = new Modle(string);
		System.out.println("结果：" + modle.getResult());

	}

}
