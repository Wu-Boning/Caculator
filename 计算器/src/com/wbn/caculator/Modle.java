/**
 * 
 */
package com.wbn.caculator;

/**
 * �����߼�
 * 
 * @author �Ⲵ��
 *
 */
public class Modle {

	/**
	 * ��ʽ
	 */
	private String equation;

	/**
	 * ������
	 */
	private float result;

	/**
	 * ջ����󳤶�
	 */
	private int maxSize = 30;

	/**
	 * ���췽��
	 * 
	 * @param equation ��ʽ
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
	 * ����
	 */
	private void caculate(String equation) {
		// ����һ��ջ
		char stack[] = new char[maxSize];

		// �洢��ʽ
		char str[] = new char[maxSize];

		// �沨�����ʽ
		char ex[] = new char[maxSize];

		// ջ��ָ��
		int top = 0;

		// ��ǰ���ڲ������ַ�
		char ch = '0';

		// Ϊ��ʽ��ӽ�����
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
			case '(': /* �ж�Ϊ������ */
				stack[++top] = ch;
				break;
			case ')': /* �ж�Ϊ������ */
				while (stack[top] != '(')
					ex[t++] = stack[top--];
				top--;
				break;
			case '+': /* �ж�Ϊ�Ӽ��� */
			case '-':
				while (top != 0 && stack[top] != '(')
					ex[t++] = stack[top--];
				stack[++top] = ch;
				break;
			case '*': /* �ж�Ϊ�˳��� */
			case '/':
				while (stack[top] == '*' || stack[top] == '/')
					ex[t++] = stack[top--];
				stack[++top] = ch;
				break;
			case '#':
				break;
			default:
				while (ch >= '0' && ch <= '9' || ch == '.') /* �ж�Ϊ���� */
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

		// ���沨��ʽ����
		float stack2[] = new float[maxSize], d; /* ��Ϊջʹ�� */

		t = 1;
		top = 0; /* tΪex�±꣬topΪstack�±� */
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
				while (ch >= '0' && ch <= '9' || ch == '.') { /* �������ַ�ת��Ϊ��Ӧ����ֵ */
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
		System.out.println("�����" + modle.getResult());

	}

}
