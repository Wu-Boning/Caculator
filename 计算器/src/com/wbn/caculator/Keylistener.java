/**
 * 
 */
package com.wbn.caculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ���̼�����
 * @author �Ⲵ��
 *
 */
public class Keylistener implements KeyListener {
	
	Ui ui ;

	public Keylistener(Ui ui) {
		this.ui = ui;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String string = e.getKeyChar() + "";
		System.out.println(string);
		if (ui.isKey(string)) {
			ui.equation += string;
			ui.lblNewLabel.setText(ui.equation);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
