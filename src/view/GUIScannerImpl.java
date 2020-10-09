package view;

import java.util.Scanner;

public class GUIScannerImpl implements GUI {

	private Scanner scanner;

	public GUIScannerImpl(String title) {
		scanner = new Scanner(System.in);
		System.out.println(title);
	}

	@Override
	public String getString() {
		return scanner.nextLine();
	}

	@Override
	public void addString(String s) {
		System.out.println(s);

	}

	@Override
	public void clear() {
		System.out.println();
	}

	@Override
	public void exit() {
		System.exit(0);

	}

	@Override
	public void showStackEmptyMessage() {
		System.out.println("Pop - stack empty, returning 0!");

	}

}
