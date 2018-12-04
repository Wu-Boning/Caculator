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
import java.awt.Font;

public class Ui extends JFrame {

	/**
	 * 算式
	 */
	String equation = "";

	/**
	 * 原式
	 */
	String preEquation = "";

	/**
	 * 按键
	 */
	String btText[];

	Actionlistener btListener;

	Keylistener keyListener;

	JPanel contentPane;

	JLabel lblNewLabel;
	JLabel label;

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

		//显示面板
		jPanel1 = new JPanel();
		jPanel1.setLayout(new BorderLayout());
		//显示原式
		label = new JLabel(" ");
		label.setFont(new Font("幼圆", Font.PLAIN, 30));
		jPanel1.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		//显示算和结果
		lblNewLabel = new JLabel("请输入：");
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jPanel1.add(lblNewLabel, BorderLayout.CENTER);

		contentPane.add(jPanel1, BorderLayout.NORTH);

		// 按钮面板
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 4, 1, 1));
		//按钮显示
		String btText[] = { "AC", "Del", ".", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "(", "0",
				")", "=" };
		JButton button[] = new JButton[20];

		// 按钮监听器
		btListener = new Actionlistener(this);

		// 键盘监听器
		keyListener = new Keylistener(this);
		lblNewLabel.addKeyListener(keyListener);

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

	/**
	 * 回调接口
	 * @author 吴泊宁
	 *
	 */
	public interface Callback {

		void setEquation(String equation);

		String getResult();
	}

}
