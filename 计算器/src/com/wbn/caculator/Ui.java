package com.wbn.caculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdk.jfr.Percentage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

public class Ui extends JFrame {

	/**
	 * 算式
	 */
	private String equation = "";

	/**
	 * 原式
	 */
	private String preEquation = "";

	/**
	 * 按键
	 */
	String btText[];

	ActionListener btListener;

	KeyListener keyListener;

	private JPanel contentPane;

	private JLabel lblNewLabel;
	private JLabel label;

	JPanel jPanel1;

	/**
	 * 回调接口
	 */
	Callback callback;

	/**
	 * 左右括号配对标记
	 */
	int left = 0, right = 0;

	/**
	 * 小数点标记
	 */
	boolean hasPoint = false;

	/**
	 * 数字标记
	 */
	boolean isNum = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ui frame = new Ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jPanel1 = new JPanel();
		jPanel1.setLayout(new BorderLayout());

		label = new JLabel(" ");
		label.setFont(new Font("幼圆", Font.PLAIN, 30));
		jPanel1.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(SwingConstants.RIGHT);

		lblNewLabel = new JLabel("请输入：");
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 添加键盘监听器
//		lblNewLabel.addKeyListener(keyListener);
		jPanel1.add(lblNewLabel, BorderLayout.CENTER);

		contentPane.add(jPanel1, BorderLayout.NORTH);

		// 按钮面板
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 4, 1, 1));

		String btText[] = { "AC", "Del", ".", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "(", "0",
				")", "=" };
		JButton button[] = new JButton[20];

		// 按钮监听器
		initActionListener();

		// 键盘监听器
		keyListener();
		lblNewLabel.addKeyListener(keyListener);

//		addKeyListener(keyListener);

		// 添加按钮
		for (int i = 0; i < btText.length; i++) {
			button[i] = new JButton(btText[i]);
			button[i].addActionListener(btListener);
			button[i].setActionCommand(btText[i]);
			panel.add(button[i]);
		}
		setVisible(true);

	}

	/**
	 * 初始化监听器
	 */
	public void initActionListener() {
		btListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				String command = button.getActionCommand();
				switch (command) {
				case "=":
					if (left - right == 0) {
						preEquation = equation;
						callback.setEquation(equation);
						equation = callback.getResult();
						lblNewLabel.setText(equation);
						label.setText(preEquation);

						hasPoint = false;
						left = 0;
						right = 0;
					} else {
						JOptionPane.showMessageDialog(null, "括号不能配对", "括号不能配对", JOptionPane.ERROR_MESSAGE);
					}

					break;
				case ".":
					if (!hasPoint) {
						if (!isNum) {
							equation += "0.";
							hasPoint = true;
						} else {
							hasPoint = true;
							equation = equation + button.getText();
						}
						lblNewLabel.setText(equation);
					}
					break;
				case "AC":
					equation = "";
					preEquation = "";
					lblNewLabel.setText(" ");
					label.setText(" ");

					isNum = false;
					hasPoint = false;
					left = 0;
					right = 0;
					break;
				case "Del":
					String dels = equation.substring(equation.length() - 1, equation.length());
					if (dels.equals(".") || dels.equals("*") || dels.equals("/") || dels.equals("+")
							|| dels.equals("-"))
						hasPoint = false;
					else if (dels.equals("("))
						left--;
					else if (dels.equals(")"))
						right--;
					equation = equation.substring(0, equation.length() - 1);
					lblNewLabel.setText(equation);
					break;
				case "+":
				case "-":
				case "*":
				case "/":
					if (equation.length() > 0) {
						if (!isNum) {
							equation = equation.substring(0, equation.length() - 1) + button.getText();
						} else {
							isNum = false;
							hasPoint = false;
							equation = equation + button.getText();
						}
						lblNewLabel.setText(equation);
					}
					break;
				case "(":
					isNum = false;
					left++;
					equation = equation + button.getText();
					lblNewLabel.setText(equation);
					break;
				case ")":
					isNum = false;
					right++;
					equation = equation + button.getText();
					lblNewLabel.setText(equation);
					break;

				default:
					isNum = true;
					equation = equation + button.getText();
					lblNewLabel.setText(equation);
					break;

				}

			}
		};
	}

	/**
	 * 键盘监听器
	 */
	public void keyListener() {
		keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				String string = e.getKeyChar() + "";
				System.out.println(string);
				if (isKey(string)) {
					equation += string;
					lblNewLabel.setText(equation);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		};

	}

	/**
	 * 判断是否是计算器上的按钮
	 * 
	 * @param string
	 * @return
	 */
	public boolean isKey(String string) {
		for (int i = 0; i < btText.length; i++) {
			if (string == btText[i]) {
				return true;
			}
		}
		return false;
	}

	public void setCallback(Controller controller) {
		this.callback = controller;
	}

	public interface Callback {

		void setEquation(String equation);

		String getResult();
	}

}
