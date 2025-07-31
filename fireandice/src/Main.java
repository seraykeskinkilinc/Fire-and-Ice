import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Main {

	private static boolean gameStarts = false;
	private static boolean displayMenu = false;
	private static boolean ScoreTableOpen = false;

	public static void main(String[] args) throws Exception {

		enigma.console.Console cn = Enigma.getConsole("SCREEN", 150, 50, 13);

        cn.getTextWindow().setCursorPosition(0, 0);
		menu menu = new menu();
		menu.displayMenu();
		cn.getTextWindow().setCursorPosition(60, 30);
		System.out.println("--->");

		KeyListener keyListener = new KeyListener() {
			int selectedOption = 0;

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (gameStarts == false) {
					
					int keyCode = e.getKeyCode();
					if(ScoreTableOpen==false)
					{
						if (keyCode == KeyEvent.VK_S) {
							cn.getTextWindow().setCursorPosition(60, 34);
							System.out.println("--->");
							cn.getTextWindow().setCursorPosition(60, 30);
							System.out.println("    ");
							selectedOption = 1;
						} else if (keyCode == KeyEvent.VK_W) {
							cn.getTextWindow().setCursorPosition(60, 30);
							System.out.println("--->");
							cn.getTextWindow().setCursorPosition(60, 34);
							System.out.println("    ");
							selectedOption = 0;
						}
					}
					

					if (keyCode == KeyEvent.VK_ENTER) {
						cn.getTextWindow().setCursorPosition(0, 0);
						if (ScoreTableOpen == false) {
							if (selectedOption == 0) {
								gameStarts = true;

							} else if (selectedOption == 1) {
								ScoreTableOpen = true;
				
								cn.getTextWindow().setCursorPosition(0, 0);
								for (int y = 0; y < 59; y++) {
									for (int x = 0; x < 200; x++) {
										cn.getTextWindow().setCursorPosition(x, y);
										cn.getTextWindow().output(" ");

									}

								}
								cn.getTextWindow().setCursorPosition(0, 0);
								ScoreTable scoreTable = new ScoreTable();
								System.out.println("Press enter to go back to menu");
							}
						} else {
                            cn.getTextWindow().setCursorPosition(0, 0);
							for (int y = 0; y < 59; y++) {
								for (int x = 0; x < 149; x++) {
									cn.getTextWindow().setCursorPosition(x, y);
									cn.getTextWindow().output(" ");

								}

							}
							ScoreTableOpen = false;
							displayMenu = true;
							cn.getTextWindow().setCursorPosition(60, 30);
							System.out.println("--->");
							selectedOption = 0;
							cn.getTextWindow().setCursorPosition(0, 0);
							menu.displayMenu();

						}

					}
				}

			}

			public void keyReleased(KeyEvent e) {
			}
		};

		cn.getTextWindow().addKeyListener(keyListener);

		while (!gameStarts) {

			Thread.sleep(100);
		}

		if (gameStarts) {
			cn.getTextWindow().setCursorPosition(0, 0);

			for (int y = 0; y < 59; y++) {
				for (int x = 0; x < 149; x++) {
					cn.getTextWindow().setCursorPosition(x, y);
					cn.getTextWindow().output(" ");
				}
			}

		    Game gamestart=new Game();
			gamestart.game();

		}

	}

}
