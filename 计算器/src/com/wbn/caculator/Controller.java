/**
 * 
 */
package com.wbn.caculator;


import com.wbn.caculator.Ui.Callback;

/**
 * @author �Ⲵ��
 *
 */
public class Controller implements Callback {
	
	Modle modle ;
	
	public Controller(Modle modle) {
		this.modle = modle;
	}

	@Override
	public void setEquation(String equation) {
		modle.setEquation(equation);
	}

	@Override
	public String getResult() {
		
		return modle.getResult();
	}

	
}
