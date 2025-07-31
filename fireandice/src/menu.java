import java.awt.Color;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class menu {
	enigma.console.Console cn = Enigma.getConsole("SCREEN", 75, 25, 13);
	
	public void displayMenu()
	{
	
		TextAttributes blue = new TextAttributes(Color.decode("#0aeef2"));
		TextAttributes lightblue = new TextAttributes(Color.decode("#7DF9FF"));
		TextAttributes red = new TextAttributes(Color.decode("#FF0000"));
		TextAttributes red1 = new TextAttributes(Color.decode("#BF0000"));
		TextAttributes red2 = new TextAttributes(Color.decode("#FF0000"));
		TextAttributes red3 = new TextAttributes(Color.decode("#FF4500"));
		TextAttributes red4 = new TextAttributes(Color.decode("#E35335"));
		TextAttributes red5 = new TextAttributes(Color.decode("#EA651B"));
		TextAttributes white = new TextAttributes(Color.WHITE);
		TextAttributes yellow = new TextAttributes(Color.YELLOW);

		cn.getTextWindow().setCursorPosition(27, 5);
		cn.getTextWindow().output("░     ░      ░  ░  ▒ ░   ", red5);
		cn.getTextWindow().setCursorPosition(27, 6);
		cn.getTextWindow().output("░   ▒ ░   ▒ ░  ░░   ░    ░    ", red4);
		cn.getTextWindow().setCursorPosition(27, 7);
		cn.getTextWindow().output(" ░ ▒ ░   ▒ ░  ░▒ ░ ▒░ ░ ░", red3);
		cn.getTextWindow().setCursorPosition(27, 8);
		cn.getTextWindow().output("▒ ░   ░▓  ░ ▒▓ ░▒▓░░░ ▒░ ░ ", red2);
		cn.getTextWindow().setCursorPosition(29, 9);
		cn.getTextWindow().output("█████ ▒██  ██▀███  ▓████▄   ", red);
		cn.getTextWindow().setCursorPosition(27, 10);
		cn.getTextWindow().output(" ▓██   ▒▓██ ▓██ ▒ █  ▓█   ▀", red);
		cn.getTextWindow().setCursorPosition(27, 12);
		cn.getTextWindow().output(" ░▓█▒  ░░██ ▒██▀▀█▄  ▒▓█  ▄ ", red);
		cn.getTextWindow().setCursorPosition(27, 13);
		cn.getTextWindow().output(" ░▒█░   ░██ ░██▓ ▒██ ▒████▒   ", red);
		cn.getTextWindow().setCursorPosition(27, 11);
		cn.getTextWindow().output(" ▒████ ░▒██ ▓██ ░▄█  ▒▒███", red);

		cn.getTextWindow().setCursorPosition(58, 9);
		cn.getTextWindow().output("    ▄███▄   ███▄    █  ▓█████▄ ", white);
		cn.getTextWindow().setCursorPosition(60, 10);
		cn.getTextWindow().output("▒██  ▀█▄  ▓██ ▀█ ██▒ ██   █▌", white);
		cn.getTextWindow().setCursorPosition(58, 11);
		cn.getTextWindow().output(" ▒██▄▄▄▄██ ▓██▒  ▐▌██  ▓█▄   ▌ ", white);
		cn.getTextWindow().setCursorPosition(58, 12);
		cn.getTextWindow().output("  ▒█   ▓██  ██     ██  ▒█   ▓   ", white);
		cn.getTextWindow().setCursorPosition(58, 13);
		cn.getTextWindow().output("  ▒▒   ▓▒█  █▒█    █▒  ▒▒█▓▒   ", white);

		cn.getTextWindow().setCursorPosition(86, 9);
		cn.getTextWindow().output("       █▓   ▄███▄  ▓████▄", blue);
		cn.getTextWindow().setCursorPosition(92, 10);
		cn.getTextWindow().output(" ██  ██▀ ▀█  ▓█   ▀", blue);
		cn.getTextWindow().setCursorPosition(92, 11);
		cn.getTextWindow().output(" ██  ▓█    ▄ ▒███", blue);
		cn.getTextWindow().setCursorPosition(92, 12);
		cn.getTextWindow().output(" ██  ▓▓▄ ▄██▒▒▓█  ▄", blue);
		cn.getTextWindow().setCursorPosition(92, 13);
		cn.getTextWindow().output(" ██░ ░▓███▀ ░░▒████▒", blue);
		cn.getTextWindow().setCursorPosition(92, 14);
		cn.getTextWindow().output(" ░▓  ░░▒ ▒   ░░ ▒░ ░  ", lightblue);
		cn.getTextWindow().setCursorPosition(92, 15);
		cn.getTextWindow().output("  ▓  ░ ▒  ▒  ░░  ▒░  ", lightblue);
		cn.getTextWindow().setCursorPosition(92, 16);
		cn.getTextWindow().output(" ░▓  ░ ▒ ░    ▒░ ░  ", lightblue);
		cn.getTextWindow().setCursorPosition(92, 17);
		cn.getTextWindow().output("   ░  ░        ░ ", lightblue);

		cn.getTextWindow().setCursorPosition(68, 30);
		System.out.println("Play");
		cn.getTextWindow().setCursorPosition(68, 34);
		System.out.println("Score Table");
	}
	
	
	
}

