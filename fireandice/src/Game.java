import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import enigma.console.TextAttributes;
import enigma.core.Enigma;
public class Game {
    MultiLinkedList mll = new MultiLinkedList();
    Computer computer = new Computer(1, 1);
    TextAttributes blue = new TextAttributes(Color.decode("#0aeef2"));
    TextAttributes yellow = new TextAttributes(Color.decode("#FFFF00"));
    TextAttributes lightgray = new TextAttributes(Color.lightGray,Color.lightGray);
    TextAttributes blueıce = new TextAttributes(Color.BLUE,Color.BLUE);
    TextAttributes darkgray = new TextAttributes(Color.darkGray,Color.darkGray);
    TextAttributes magenta = new TextAttributes(Color.MAGENTA,Color.MAGENTA);
    TextAttributes red = new TextAttributes(Color.decode("#FF0000"));
    TextAttributes red1 = new TextAttributes(Color.decode("#BF0000"));
    TextAttributes red2 = new TextAttributes(Color.RED, Color.RED);
    TextAttributes red4 = new TextAttributes(Color.decode("#E35335"));
    TextAttributes purple = new TextAttributes(Color.decode("#9400D3"));
    TextAttributes gray = new TextAttributes(Color.decode("#B0C4DE"));

    private int fireCount = 0;
    private int ices = 0;
    private int tempX = 0;
    private int tempY = 0;
    int counter = 0;
    private Fire[] fire = new Fire[150];
    private Ice[] ice = new Ice[150];
    private int direction = 0;
    int timer = 0;
    int x = 3;
    int y = 2;
    int a = 0;
    int b = 0;
    enigma.console.Console cn = Enigma.getConsole("SCREEN", 75, 25, 20);
    //    private TextAttributes green = new TextAttributes(Color.GREEN);
    public KeyListener klis;
    public int keypr;   // is key pressed?
    public int rkey;    // key (for press/release)
    char[][] map = new char[23][53];
    int pscore = 0;
    int plife = 1000;
    int iceCount = 0;
    Random random = new Random();
    CircularQueue queue = new CircularQueue(10);

    public Game() {
    	startArrowAnimation();
        String filename = "maze.txt";
        try {

            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(new File(filename));
            String satir;
            int satirIndex = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != "") {
                    map[satirIndex] = line.toCharArray();
                    satirIndex++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // headler
        mll.addComputerTreasure('C');
        mll.addComputerTreasure('1');
        mll.addComputerTreasure('2');
        mll.addComputerTreasure('3');

        // Haritayı ekrana yazdır
        Random random = new Random();
        //ilk 10
        for (int i = 0; i < 10; i++) {
            do {
                a = random.nextInt(22)+1;
                b = random.nextInt(52)+1;
            }
            while (map[a][b] != ' ');
            int j = random.nextInt(5)+1;

            if (j == 1) {
                map[a][b] = '1';
                Treasure treasure = new Treasure(b, a, '1');
                mll.addLocation(treasure, '1');
            }
            if (j == 2) {
                map[a][b] = '2';
                Treasure treasure = new Treasure(b, a, '2');
                mll.addLocation(treasure, '2');
            }
            if (j == 3) {
                map[a][b] = '3';
                Treasure treasure = new Treasure(b, a, '3');
                mll.addLocation(treasure, '3');
            }
            if (j == 4) {
                map[a][b] = '@';
            }
        }
        printmap();

    }

    public void game() throws Exception {
    
        queue.inputqueue();
        printinputqueue();
        klis = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (keypr == 0) {
                    keypr = 1;
                    rkey = e.getKeyCode();
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        cn.getTextWindow().addKeyListener(klis);
        boolean flag = true;

        map[y][x] = 'P';
        printmap();

        while (flag) {

            printlifeandscores();
            //PLAYER MOVEMENTS
            playermove();
            counter++;
            //LIFE UPDATE FOR PLAYER
            healthdecreaseP('-', 1);
            healthdecreaseP('C', 50);
            //LIFE UPDATE FOR COMPUTER
            LocatioanNode temp = mll.head.getRight();
            while (temp != null) {
                Computer current = (Computer) temp.getObject() ;
                healthdecreaseC(current,50);
                if(current.clife <= 0){
                    map[current.getY()][current.getX()] = ' ';
                    mll.DeleteComputer(current);
                }
                temp = temp.getNext();
            }
            if (plife <= 0) {
                gameoverscreen();
           
                break;
            }
            Thread.sleep(100);
            if (counter % 10 == 0) {
                timer++;
            }
            if (counter % 4 == 0) {
                if (mll.head.getRight() != null) {
                    Computer computer = (Computer) mll.head.getRight().getObject();
                    computer.computermove(mll, map);
                    printmap();
                }
            }

            if (counter % 20 == 0) {
                do {
                    Random random = new Random();
                    a = random.nextInt(22)+1;
                    b = random.nextInt(52)+1;
                }
                while (map[a][b] != ' ');

                if ((char) queue.peek() == '-') {
                    fire[fireCount] = new Fire(b, a, map);
                    fireCount++;
                }
                if (map[a][b] != '-') {
                    map[a][b] = (char) queue.peek();
                }

                if (map[a][b] == '1') {
                    Treasure treasure = new Treasure(b, a, '1');
                    mll.addLocation(treasure, '1');
                }
                if (map[a][b] == '2') {
                    Treasure treasure = new Treasure(b, a, '2');
                    mll.addLocation(treasure, '2');
                }
                if (map[a][b] == '3') {
                    Treasure treasure = new Treasure(b, a, '3');
                    mll.addLocation(treasure, '3');
                }
                if (map[a][b] == 'C') {
                    Computer computer = new Computer(b, a);
                    mll.addLocation(computer, 'C');
                }
            }
            cn.getTextWindow().setCursorPosition(0, 0);
            printmap();

            int iceCounter = 0;
            if (ices != 0) {
                while (iceCounter < ices) {
                    ice[iceCounter].nextIce(map);
                    map = ice[iceCounter].getMap();
                    iceCounter++;
                }
            }
            int fireCounter = 0;
            if (fireCount != 0) {
                while (fireCounter < fireCount) {
                    fire[fireCounter].nextFire(map);
                    map = fire[fireCounter].getMap();
                    fireCounter++;
                }

            }
            if (counter % 20 == 0) {
                queue.dequeue();
                int choice = random.nextInt(6);
                if (choice == 0) {
                    queue.enqueue('1');
                }
                if (choice == 1) {
                    queue.enqueue('2');
                }
                if (choice == 2) {
                    queue.enqueue('3');
                }
                if (choice == 3) {
                    queue.enqueue('-');
                }
                if (choice == 4) {
                    queue.enqueue('@');
                }
                if (choice == 5) {
                    queue.enqueue('C');
                }
                printinputqueue();
            }
        }
    }

    int score;  
    boolean winner;

    public void gameoverscreen(){
     
        cn.getTextWindow().setCursorPosition(30, 25);
        System.out.println();
        cn.getTextWindow().output("   _____          __  __ ______    ______      ________ _____  \n" +
                "  / ____|   /\\   |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\ \n" +
                " | |  __   /  \\  | \\  / | |__    | |  | \\ \\  / /| |__  | |__) |\n" +
                " | | |_ | / /\\ \\ | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  / \n" +
                " | |__| |/ ____ \\| |  | | |____  | |__| | \\  /  | |____| | \\ \\ \n" +
                "  \\_____/_/    \\_\\_|  |_|______|  \\____/   \\/   |______|_|  \\_\\\n" +
                "                                                               \n" +
                "                                                               ",red);
        plife = 0;
        printlifeandscores();

        if(pscore>Computer.cscore) {
        
            cn.getTextWindow().setCursorPosition(30, 31);
            System.out.println();
            cn.getTextWindow().output("  _____  _           __     ________ _____   __          _______ _   _ \n" +
                    " |  __ \\| |        /\\\\ \\   / /  ____|  __ \\  \\ \\        / /_   _| \\ | |\n" +
                    " | |__) | |       /  \\\\ \\_/ /| |__  | |__) |  \\ \\  /\\  / /  | | |  \\| |\n" +
                    " |  ___/| |      / /\\ \\\\   / |  __| |  _  /    \\ \\/  \\/ /   | | | . ` |\n" +
                    " | |    | |____ / ____ \\| |  | |____| | \\ \\     \\  /\\  /   _| |_| |\\  |\n" +
                    " |_|    |______/_/    \\_\\_|  |______|_|  \\_\\     \\/  \\/   |_____|_| \\_|\n" +
                    "                                                                       \n" +
                    "                                                                       ", blue);
        }
        else if(pscore<Computer.cscore){
      
        	
            cn.getTextWindow().setCursorPosition(30, 31);
            System.out.println();
            cn.getTextWindow().output("   _____ ____  __  __ _____  _    _ _______ ______ _____   __          _______ _   _ \n" +
                    "  / ____/ __ \\|  \\/  |  __ \\| |  | |__   __|  ____|  __ \\  \\ \\        / /_   _| \\ | |\n" +
                    " | |   | |  | | \\  / | |__) | |  | |  | |  | |__  | |__) |  \\ \\  /\\  / /  | | |  \\| |\n" +
                    " | |   | |  | | |\\/| |  ___/| |  | |  | |  |  __| |  _  /    \\ \\/  \\/ /   | | | . ` |\n" +
                    " | |___| |__| | |  | | |    | |__| |  | |  | |____| | \\ \\     \\  /\\  /   _| |_| |\\  |\n" +
                    "  \\_____\\____/|_|  |_|_|     \\____/   |_|  |______|_|  \\_\\     \\/  \\/   |_____|_| \\_|\n" +
                    "                                                                                     \n" +
                    "                                                                                     ",red);
        }
        else {
        	
            cn.getTextWindow().setCursorPosition(30, 31);
            System.out.println();
            cn.getTextWindow().output("  _____  _____       __          __\n" +
                    " |  __ \\|  __ \\     /\\ \\        / /\n" +
                    " | |  | | |__) |   /  \\ \\  /\\  / / \n" +
                    " | |  | |  _  /   / /\\ \\ \\/  \\/ /  \n" +
                    " | |__| | | \\ \\  / ____ \\  /\\  /   \n" +
                    " |_____/|_|  \\_\\/_/    \\_\\/  \\/    \n" +
                    "                                   \n" +
                    "                                   ",blue);
        }
        winner=true;
    	score=pscore;
        ScoreTable scoreTable=new ScoreTable(winner,score);
    }
    public void healthdecreaseP(char c, int value) {
        if (map[y - 1][x] == c) {
            plife = plife - value;
        }
        if (map[y + 1][x] == c) {
            plife = plife - value;
        }
        if (map[y][x + 1] == c) {
            plife = plife - value;
        }
        if (map[y][x - 1] == c) {
            plife = plife - value;
        }
    }

    public void healthdecreaseC(Computer comp, int value) {

        if (map[comp.getY() - 1][comp.getX()] == '+') {
            comp.setLife(comp.getLife() - value);
        }
        if (map[comp.getY() + 1][comp.getX()] == '+') {
            comp.setLife(comp.getLife() - value);
        }
        if (map[comp.getY()][comp.getX() + 1] == '+') {
            comp.setLife(comp.getLife() - value);
        }
        if (map[comp.getY()][comp.getX() - 1] == '+') {
            comp.setLife(comp.getLife() - value);
        }
    }
    public  void  playermove(){
        tempX = 0;
        tempY = 0;
        if (keypr == 1)
        {
            if(direction == 1){
                tempX = 1;
            }else if(direction ==2){
                tempY = -1;
            }else if(direction ==3){
                tempX = -1;
            }else if(direction == 4){
                tempY = 1;
            }
            map[y][x]=' ';
            if (rkey == KeyEvent.VK_UP && (map[y-1][x]==' ')) {
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                direction = 2;
                y--;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_UP && (map[y-1][x]=='1' || map[y-1][x]=='2' || map[y-1][x]=='3'||map[y-1][x]=='@')) {
                if (map[y - 1][x] == '1') {
                    Treasure treasure = new Treasure(x,y-1,'1');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore + 1;
                }
                if (map[y - 1][x] == '2') {
                    Treasure treasure = new Treasure(x,y-1,'2');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore + 10;
                }
                if (map[y - 1][x] == '3') {
                    Treasure treasure = new Treasure(x,y-1,'3');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore + 30;
                }
                if(map[y-1][x]=='@'){
                    iceCount++;
                }
                direction = 2;
                map[y][x] = ' ';
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                y--;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_RIGHT && (map[y][x+1]==' ')) {
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                direction = 1;
                x++;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_RIGHT && (map[y][x+1]=='1' || map[y][x+1]=='2' || map[y][x+1]=='3'||map[y][x+1]=='@')) {
                if (map[y][x+1] == '1') {
                    Treasure treasure = new Treasure(x+1,y,'1');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore + 1;
                }
                if (map[y][x+1] == '2') {
                    Treasure treasure = new Treasure(x+1,y,'2');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore + 10;
                }
                if (map[y][x+1] == '3') {
                    Treasure treasure = new Treasure(x+1,y,'3');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore + 30;
                }
                if(map[y][x+1]=='@'){
                    iceCount++;
                }
                direction = 1;
                map[y][x] = ' ';
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                x++;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_DOWN && (map[y+1][x]==' ')) {
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                y++;
                direction = 4;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_DOWN && (map[y+1][x]=='1' || map[y+1][x]=='2'|| map[y+1][x]=='3'||map[y+1][x]=='@')) {
                if (map[y+1][x]=='1'){
                    Treasure treasure = new Treasure(x,y+1,'1');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore+1;
                }
                if (map[y+1][x]=='2'){
                    Treasure treasure = new Treasure(x,y+1,'2');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore+10;
                }
                if (map[y+1][x]=='3'){
                    Treasure treasure = new Treasure(x,y+1,'3');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore+30;
                }
                if(map[y+1][x]=='@'){
                    iceCount++;
                }
                direction = 4;
                map[y][x] = ' ';
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                y++;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_LEFT &&(map[y][x-1]==' ')) {
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                x--;
                direction = 3;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }
            else if (rkey == KeyEvent.VK_LEFT &&(map[y][x-1]=='1' || map[y][x-1]=='2' || map[y][x-1]=='3'||map[y][x-1]=='@'
            )) {
                if(map[y][x-1]=='1'){
                    Treasure treasure = new Treasure(x-1,y,'1');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore +1;
                }
                if(map[y][x-1]=='2'){
                    Treasure treasure = new Treasure(x-1,y,'2');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore +10;
                }
                if(map[y][x-1]=='3'){
                    Treasure treasure = new Treasure(x-1,y,'3');
                    mll.DeleteTreasure(treasure);
                    pscore = pscore +30;
                }
                if(map[y][x-1]=='@'){
                    iceCount++;
                }
                direction = 3;
                map[y][x]=' ';
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println(' ');
                x--;
                cn.getTextWindow().setCursorPosition(x, y);
                System.out.println('P');
            }else if (rkey == KeyEvent.VK_SPACE && map[y+tempY][x+tempX] == ' ' && iceCount>0){
                ice[ices] = new Ice(x,y,map,direction);
                ices++;
                iceCount--;
            }
            map[y][x]='P';
            keypr = 0;
        }
    }
    public void printlifeandscores() {
        cn.getTextWindow().setCursorPosition(65, 8);
        System.out.print("TİMER :" + timer);
        cn.getTextWindow().setCursorPosition(65, 9);
        System.out.print("P.SCORE :" + pscore);
        cn.getTextWindow().setCursorPosition(65, 10);
        System.out.print("P.LIFE :" + plife + " ");
        cn.getTextWindow().setCursorPosition(65, 11);
        System.out.print("Ice Count :" + iceCount+" ");
        cn.getTextWindow().setCursorPosition(65, 13);
        System.out.print("C.SCORE :" + Computer.cscore+" ");
    }

    public void printmap() {
        cn.getTextWindow().setCursorPosition(0, 0);
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 53; j++) {

                if(map[i][j] == '#'){
                    cn.getTextWindow().output('#',darkgray);
                }
                else if(map[i][j] == '-'){
                    cn.getTextWindow().output('-',red);
                }
                else if(map[i][j] == '+'){
                    cn.getTextWindow().output('+',blue);
                }
                else if(map[i][j] == 'C'){
                    cn.getTextWindow().output('C',yellow);
                }
                else if(map[i][j] == '1'){
                    cn.getTextWindow().output('1',purple);
                }
                else if(map[i][j] == '2'){
                    cn.getTextWindow().output('2',purple);
                }
                else if(map[i][j] == '3'){
                    cn.getTextWindow().output('3',purple);
                }
                else
                    cn.getTextWindow().output(map[i][j]);
            }
            System.out.println();
        }
        printlifeandscores();
    }

    public void printinputqueue() {
        cn.getTextWindow().setCursorPosition(65, 1);
        cn.getTextWindow().output("INPUT", blue);
        cn.getTextWindow().setCursorPosition(65, 2);

        cn.getTextWindow().setCursorPosition(65, 3);
        for (int i = 0; i < 10; i++) {
            System.out.print(queue.peek());
            queue.enqueue(queue.dequeue());
        }
 
    }
    public void printarrows() {
    		TextAttributes red = new TextAttributes(Color.RED);
    		TextAttributes white = new TextAttributes(Color.WHITE);

    		// Initial string
    		String str = "<<<<<<<<<<";

    		// Length of the string
    		int length = str.length();

    		while (true) {
    	
    			for (int i = length - 1; i >= 0; i--) {

    				for (int j = 0; j < length; j++) {
    					cn.getTextWindow().output(j + 65, 2, ' ', white);
    					cn.getTextWindow().output(j + 65, 4, ' ', white);
    				}

    
    				for (int j = 0; j < length; j++) {
    					if ((j - i + length) % 4 < 2) {
    						cn.getTextWindow().output(j + 65, 2, str.charAt(j), white);
    						cn.getTextWindow().output(j + 65, 4, str.charAt(j), white);
    					} else {
    						cn.getTextWindow().output(j + 65, 2, str.charAt(j), red);
    						cn.getTextWindow().output(j + 65, 4, str.charAt(j), red);
    					}
    				}

    		
    				try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
	
						e.printStackTrace();
					}
    			}
    		}
    		

    	
    }
    public void startArrowAnimation() {
        Thread arrowThread = new Thread(new Runnable() {
            public void run() {
                printarrows();
            }
        });
        arrowThread.setDaemon(true); // Ensures the thread will not block JVM shutdown
        arrowThread.start();
    }
}


