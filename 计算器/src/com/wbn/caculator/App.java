/**
 * 
 */
package com.wbn.caculator;

/**
 * @author �Ⲵ��
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Modle modle = new Modle();
		Controller controller = new Controller(modle);
		Ui ui = new Ui();
		ui.setCallback(controller);

	}

}
