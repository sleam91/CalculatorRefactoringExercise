package model;

import static java.util.Map.entry;

import java.util.Map;

import java.util.Stack;

public class Calculator {

	private Stack<Double> stack = new Stack<>();
	private Map<String, Runnable> operations;

	public Calculator() {
		operations = createOperations();
	}

	public void calculate(String input) {
		operations.get(input).run();
	}

	public void clear() {
		stack.clear();
	}

	public Stack<Double> getStack() {
		return stack;
	}

	public Map<String, Runnable> getOperations() {
		return operations;
	}

	private double popNextValue() {
		if (!stack.isEmpty()) {
			return stack.pop();
		}
		throw new UnsupportedOperationException();

	}

	private Map<String, Runnable> createOperations() {
		return Map.ofEntries(entry("+", () -> stack.push(add())), entry("-", () -> stack.push(subtract())),
				entry("*", () -> stack.push(multiply())), entry("/", () -> stack.push(divide())),
				entry("c", () -> stack.clear()), entry("sin", () -> stack.push(sinus())));

	}

	private double sinus() {
		return Math.sin(popNextValue());
	}

	private double add() {
		return popNextValue() + popNextValue();
	}

	private double subtract() {
		double value1 = popNextValue();
		double value2 = popNextValue();
		return value2 - value1;
	}

	private double multiply() {
		return popNextValue() * popNextValue();
	}

	private double divide() {
		double value1 = popNextValue();
		double value2 = popNextValue();
		return value2 / value1;
	}

}
