package main;

import controller.Controller;
import model.Calculator;
import view.GUI;
import view.GUISimpleWindowImpl;

public class RPNCalculator {

	public static void main(String[] args) {
		GUI gameWindow = new GUISimpleWindowImpl("Calculator");
		Calculator calculator = new Calculator();
		Controller controller = new Controller(gameWindow, calculator);
		
		controller.start();
	}


}
