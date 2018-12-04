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
	 * ��ʽ
	 */
	String equation = "";

	/**
	 * ԭʽ
	 */
	String preEquation = "";

	/**
	 * ����
	 */
	String btText[];

	Actionlistener btListener;

	Keylistener keyListener;

	JPanel contentPane;

	JLabel lblNewLabel;
	JLabel label;

	JPanel jPanel1;

	/**
	 * �ص��ӿ�
	 */
	Callback callback;

	/**
	 * ����������Ա��
	 */
	int left = 0, right = 0;

	/**
	 * С������
	 */
	boolean hasPoint = false;

	/**
	 * ���ֱ��
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

		//��ʾ���
		jPanel1 = new JPanel();
		jPanel1.setLayout(new BorderLayout());
		//��ʾԭʽ
		label = new JLabel(" ");
		label.setFont(new Font("��Բ", Font.PLAIN, 30));
		jPanel1.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		//��ʾ��ͽ��
		lblNewLabel = new JLabel("�����룺");
		lblNewLabel.setFont(new Font("��Բ", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jPanel1.add(lblNewLabel, BorderLayout.CENTER);

		contentPane.add(jPanel1, BorderLayout.NORTH);

		// ��ť���
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 4, 1, 1));
		//��ť��ʾ
		String btText[] = { "AC", "Del", ".", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "(", "0",
				")", "=" };
		JButton button[] = new JButton[20];

		// ��ť������
		btListener = new Actionlistener(this);

		// ���̼�����
		keyListener = new Keylistener(this);
		lblNewLabel.addKeyListener(keyListener);

		// ��Ӱ�ť
		for (int i = 0; i < btText.length; i++) {
			button[i] = new JButton(btText[i]);
			button[i].addActionListener(btListener);
			button[i].setActionCommand(btText[i]);
			panel.add(button[i]);
		}
		setVisible(true);

	}

	

	/**
	 * �ж��Ƿ��Ǽ������ϵİ�ť
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
	 * �ص��ӿ�
	 * @author �Ⲵ��
	 *
	 */
	public interface Callback {

		void setEquation(String equation);

		String getResult();
	}

}
