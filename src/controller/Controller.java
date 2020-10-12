package controller;

import java.util.Map;
import java.util.OptionalDouble;

import model.Calculator;
import utils.Utility;
import view.GUI;

public class Controller {

    private GUI gameWindow;
    private Calculator calculator;
    private Map<String, Runnable> commands;
    private boolean running = true;

    public Controller(GUI gameWindow, Calculator calculator) {
	this.gameWindow = gameWindow;
	this.calculator = calculator;
	commands = Map.of("q", this::quit);
    }

    public void start() {
	showInstructions();
	while (running) {
	    handleUserInput(gameWindow.getString().trim());
	    showStackResults();
	}
	gameWindow.exit();

    }

    public void showInstructions() {
	gameWindow.addString("[empty]\n" + "Commands: q=quit c=clear + - * / number");
    }

    public void handleUserInput(String input) {
	OptionalDouble value = Utility.getDouble(input);
	if (value.isPresent()) {
	    calculator.acceptNumber(value.getAsDouble());
	} else {
	    this.execute(input);
	}
    }

    public void showStackResults() {
	gameWindow.clear();
	if (calculator.isEmpty()) {
	    showInstructions();
	} else {
	    gameWindow.addString(calculator.toString());
	}
    }

    private void execute(String input) {
	if (calculator.getOperations().containsKey(input)) {
	    calculate(input);
	} else {
	    commands.getOrDefault(input, this::showErrorMessage).run();
	}
    }

    private void calculate(String input) {
	try {
	    calculator.calculate(input);
	} catch (UnsupportedOperationException e) {
	    showStackEmptyMessage();
	    calculator.acceptNumber(0);
	}

    }

    private void quit() {
	calculator.clear();
	running = false;
    }

    private void showStackEmptyMessage() {
	gameWindow.showStackEmptyMessage();
    }

    private void showErrorMessage() {
	gameWindow.addString("\nIllegal command\n");
	Utility.holdErrorMessage();
    }

}
