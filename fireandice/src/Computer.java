public class Computer {
    int life;
    int x;
    int y;
    static int cscore = 0 ;
    int clife = 1000 ;
    public static int getCscore() {
        return cscore;
    }
    public void setCscore(int cscore) {
        this.cscore = cscore;
    }
    Computer(int x, int y) {
        this.x = x;
        this.clife = 1000;
        this.y = y;
    }
    public int getLife() {
        return clife;
    }
    public void setLife(int life) {
        this.clife = life;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void computermove(MultiLinkedList mll, char[][] map) {
        int min = 999;
        LocatioanNode computernode = mll.head.getRight();
        Computer computer = (Computer) computernode.getObject();
        Treasure bestTreasure = new Treasure(1000, 1000, '2');

        while (computer != null) {
            int x = computer.getX();
            int y = computer.getY();
            // Get Treasurers with Value 1
            LocatioanNode temp = mll.head.getDown().getRight();

            while (temp != null) {
                Treasure treasure = (Treasure) temp.getObject();
                int a = Math.abs(x - treasure.getX());
                int b = Math.abs(y - treasure.getY());
                int result = (a * a) + (b * b);
                if (result < min) {
                    min = result;
                    bestTreasure = (Treasure) temp.getObject();
                }
                temp = temp.getNext();
            }

            temp = mll.head.getDown().getDown().getRight();

            while (temp != null) {
                Treasure treasure = (Treasure) temp.getObject();
                int a = Math.abs(x - treasure.getX());
                int b = Math.abs(y - treasure.getY());
                int result = (a * a) + (b * b);
                if (result < min) {
                    min = result;
                    bestTreasure = (Treasure) temp.getObject();
                }
                temp = temp.getNext();
            }

            temp = mll.head.getDown().getDown().getDown().getRight();

            while (temp != null) {
                Treasure treasure = (Treasure) temp.getObject();
                int a = Math.abs(x - treasure.getX());
                int b = Math.abs(y - treasure.getY());
                int result = (a * a) + (b * b);
                if (result < min) {
                    min = result;
                    bestTreasure = (Treasure) temp.getObject();
                }
                temp = temp.getNext();
            }
            int a;
            int b;
             a = Math.abs(x - bestTreasure.getX());
             b = Math.abs(y - bestTreasure.getY());

            if (a > b) {

                if (x > bestTreasure.getX()) {
                    if (map[y][x - 1] != '#' && map[y][x - 1] != 'C' && map[y][x - 1] != 'P' && map[y][x - 1] != '@' && map[y][x - 1] != '-' && map[y][x - 1] != '+') {
                        map[y][x] = ' ';
                        if(map[y][x - 1] == '1'){
                            cscore = cscore+9;
                        }
                        if(map[y][x - 1] == '2'){
                            cscore = cscore+30;
                        }
                        if(map[y][x - 1] == '3'){
                            cscore = cscore+90;
                        }
                        map[y][x - 1] = 'C';
                        computer.setX(x - 1);
                    }
                }
                else {
                    if (map[y][x + 1] != '#' && map[y][x + 1] != 'C' && map[y][x + 1] != 'P' && map[y][x + 1] != '@' && map[y][x + 1] != '-' && map[y][x + 1] != '+') {
                        map[y][x] = ' ';
                        if(map[y][x + 1] == '1'){
                            cscore = cscore+9;
                        }
                        if(map[y][x + 1] == '2'){
                            cscore = cscore+30;
                        }
                        if(map[y][x + 1] == '3'){
                            cscore = cscore+90;
                        }
                        map[y][x + 1] = 'C';
                        computer.setX(x + 1);
                    }
                }
            }
            else if (a < b)
            {
                if (y < bestTreasure.getY()) {
                    if (map[y + 1][x] != '#' && map[y + 1][x] != 'C' && map[y + 1][x] != 'P' && map[y + 1][x] != '@' && map[y + 1][x] != '-' && map[y + 1][x] != '+') {
                        map[y][x] = ' ';
                        if(map[y+1][x] == '1'){
                            cscore = cscore+9;
                        }
                        if(map[y+1][x] == '2'){
                            cscore = cscore+30;
                        }
                        if(map[y+1][x] == '3'){
                            cscore = cscore+90;
                        }

                        map[y + 1][x] = 'C';
                        computer.setY(y + 1);
                    }
                }
                else {
                    if (map[y - 1][x] != '#' && map[y - 1][x] != 'C' && map[y - 1][x] != 'P' && map[y - 1][x] != '@' && map[y - 1][x] != '-' && map[y - 1][x] != '+') {
                        map[y][x] = ' ';
                        if(map[y-1][x] == '1'){
                            cscore = cscore+9;
                        }
                        if(map[y-1][x] == '2'){
                            cscore = cscore+30;
                        }
                        if(map[y-1][x] == '3'){
                            cscore = cscore+90;
                        }
                        map[y - 1][x] = 'C';
                        computer.setY(y - 1);
                    }
                }
            }
            else
            {
                if (x > bestTreasure.getX()) {
                    if (map[y][x - 1] != '#' && map[y][x - 1] != 'C' && map[y][x - 1] != 'P' && map[y][x - 1] != '@' && map[y][x - 1] != '-' && map[y][x - 1] != '+') {
                        map[y][x] = ' ';
                        if(map[y][x - 1] == '1'){
                            cscore = cscore+9;
                        }
                        if(map[y][x - 1] == '2'){
                            cscore = cscore+30;
                        }
                        if(map[y][x - 1] == '3'){
                            cscore = cscore+90;

                        }
                        map[y][x - 1] = 'C';
                        computer.setX(x - 1);
                    }
                } else {
                    if (map[y][x + 1] != '#' && map[y][x + 1] != 'C' && map[y][x + 1] != 'P' && map[y][x + 1] != '@' && map[y][x + 1] != '-' && map[y][x + 1] != '+') {
                        map[y][x] = ' ';
                        if(map[y][x + 1] == '1'){
                            cscore = cscore+9;
                        }
                        if(map[y][x + 1] == '2'){
                            cscore = cscore+30;
                        }
                        if(map[y][x + 1] == '3'){
                            cscore = cscore+90;
                        }
                        map[y][x + 1] = 'C';
                        computer.setX(x + 1);
                    }
                }
            }
            if (computer.getX() == bestTreasure.getX() && computer.getY() == bestTreasure.getY()){
                mll.DeleteTreasure(bestTreasure);
            }
            computernode = computernode.getNext();
            if (computernode != null) {
                computer = (Computer) computernode.getObject();
            }
            else {
                break;
            }
        }
    }
}

