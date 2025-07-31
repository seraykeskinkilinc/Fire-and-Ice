public class MultiLinkedList {
    CompTreasureNode head;

    public void addComputerTreasure(Character dataToAdd) {
        if (head == null) {
            CompTreasureNode compTreasureNode = new CompTreasureNode(dataToAdd);
            head = compTreasureNode;
        } else {
            CompTreasureNode temp = head;
            while (temp.getDown() != null) {
                temp = temp.getDown();
            }
            CompTreasureNode compTreasureNode = new CompTreasureNode(dataToAdd);
            temp.setDown(compTreasureNode);
        }
    }

    public void addLocation(Object dataToAdd, char name) {
        if (head == null) {

        } else {
            CompTreasureNode temp = head;
            while (temp != null) {
                if (name == temp.getNames()) {
                    LocatioanNode temp2 = temp.getRight();
                    if (temp2 == null) {
                        LocatioanNode locatioanNode = new LocatioanNode(dataToAdd);
                        temp.setRight(locatioanNode);
                    } else {
                        while (temp2.getNext() != null) {
                            temp2 = temp2.getNext();
                        }
                        LocatioanNode locatioanNode = new LocatioanNode(dataToAdd);
                        temp2.setNext(locatioanNode);
                    }
                }
                temp = temp.getDown();
            }
        }
    }

    public void DeleteTreasure(Treasure treasure) {
        if (head == null) {
            System.out.println("zort");
        } else {
            CompTreasureNode temp_head = head;
            LocatioanNode temp;
            for (int i = 0; i < 3; i++) {
                temp_head = temp_head.getDown();
                temp = temp_head.getRight();

                if (temp == null) {

                } else {
                    while (temp != null && ((Treasure) temp.getObject()).getX() == treasure.getX() && ((Treasure) temp.getObject()).getY() == treasure.getY()) {
                        temp = temp.getNext();
                    }
                    temp_head.setRight(temp);
                    LocatioanNode prev = null;

                    while (temp != null) {
                        if (((Treasure) temp.getObject()).getX() == treasure.getX() && ((Treasure) temp.getObject()).getY() == treasure.getY()) {
                            prev.setNext(temp.getNext());
                            temp = prev;
                        }
                        prev = temp;
                        temp = temp.getNext();
                    }
                }
            }
        }
    }

    public void DeleteComputer(Computer computer) {

        if (head == null) {
            System.out.println("zort");
        } else {
            CompTreasureNode temp_head = head;
            LocatioanNode temp;
            temp = temp_head.getRight();

            if (temp == null) {

            } else {
                while (temp != null && ((Computer) temp.getObject()).getX() == computer.getX() && ((Computer) temp.getObject()).getY() == computer.getY()) {
                    temp = temp.getNext();
                }
                temp_head.setRight(temp);
                LocatioanNode prev = null;

                while (temp != null) {
                    if (((Computer) temp.getObject()).getX() == computer.getX() && ((Computer) temp.getObject()).getY() == computer.getY()) {
                        prev.setNext(temp.getNext());
                        temp = prev;
                    }
                    prev = temp;
                    temp = temp.getNext();
                }
            }

        }

    }
}