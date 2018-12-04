/**
 * 
 */
package com.wbn.caculator;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * °´Å¥¼àÌýÆ÷
 * @author Îâ²´Äþ
 *
 */
public class Actionlistener implements java.awt.event.ActionListener{
	
	Ui ui;
	public Actionlistener(Ui ui) {
		this.ui = ui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String command = button.getActionCommand();
		switch (command) {
		case "=":
			if (ui.left - ui.right == 0) {
				ui.preEquation = ui.equation;
				ui.callback.setEquation(ui.equation);
				ui.equation = ui.callback.getResult();
				ui.lblNewLabel.setText(ui.equation);
				ui.label.setText(ui.preEquation);

				ui.hasPoint = false;
				ui.left = 0;
				ui.right = 0;
			} else {
				JOptionPane.showMessageDialog(null, "À¨ºÅ²»ÄÜÅä¶Ô", "À¨ºÅ²»ÄÜÅä¶Ô", JOptionPane.ERROR_MESSAGE);
			}

			break;
		case ".":
			if (!ui.hasPoint) {
				if (!ui.isNum) {
					ui.equation += "0.";
					ui.hasPoint = true;
				} else {
					ui.hasPoint = true;
					ui.equation = ui.equation + button.getText();
				}
				ui.lblNewLabel.setText(ui.equation);
			}
			break;
		case "AC":
			ui.equation = "";
			ui.preEquation = "";
			ui.lblNewLabel.setText(" ");
			ui.label.setText(" ");

			ui.isNum = false;
			ui.hasPoint = false;
			ui.left = 0;
			ui.right = 0;
			break;
		case "Del":
			String dels = ui.equation.substring(ui.equation.length() - 1, ui.equation.length());
			if (dels.equals(".") || dels.equals("*") || dels.equals("/") || dels.equals("+")
					|| dels.equals("-"))
				ui.hasPoint = false;
			else if (dels.equals("("))
				ui.left--;
			else if (dels.equals(")"))
				ui.right--;
			ui.equation = ui.equation.substring(0, ui.equation.length() - 1);
			ui.lblNewLabel.setText(ui.equation);
			break;
		case "+":
		case "-":
		case "*":
		case "/":
			if (ui.equation.length() > 0) {
				if (!ui.isNum) {
					ui.equation = ui.equation.substring(0, ui.equation.length() - 1) + button.getText();
				} else {
					ui.isNum = false;
					ui.hasPoint = false;
					ui.equation = ui.equation + button.getText();
				}
				ui.lblNewLabel.setText(ui.equation);
			}
			break;
		case "(":
			ui.isNum = false;
			ui.left++;
			ui.equation = ui.equation + button.getText();
			ui.lblNewLabel.setText(ui.equation);
			break;
		case ")":
			ui.isNum = false;
			ui.right++;
			ui.equation = ui.equation + button.getText();
			ui.lblNewLabel.setText(ui.equation);
			break;

		default:
			ui.isNum = true;
			ui.equation = ui.equation + button.getText();
			ui.lblNewLabel.setText(ui.equation);
			break;

		}

	}
		

}
