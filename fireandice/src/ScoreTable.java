import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreTable {
    String filePath = "/Users/seray/Desktop/highscoretable.txt";

    
    public ScoreTable(boolean winner, int score) {
        Scanner scanner = new Scanner(System.in);

        if (winner) {
        	System.out.println("\n");
            System.out.println("\nWhat is your name?");
            
        
            String playername = scanner.next();

            DoubleLinkedList dLLScoreTable = new DoubleLinkedList();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
                bw.write(playername + " " + score);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    dLLScoreTable.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Sort(dLLScoreTable);
            System.out.println("\n");
            System.out.println("----------- SCORE TABLE -----------");
            System.out.println("\n");
        
            dLLScoreTable.display();
        }
    }
    
    public ScoreTable() {
        Scanner scanner = new Scanner(System.in);
        String filePath = "/Users/seray/Desktop/highscoretable.txt";
        DoubleLinkedList dLLScoreTable = new DoubleLinkedList();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dLLScoreTable.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sort(dLLScoreTable);
        dLLScoreTable.display();
        
        
    }

    public static void Sort(DoubleLinkedList dLLScoreTable) {
        Node current = dLLScoreTable.head;
        while (current != null) {
            Node nextNode = current.getNext();
            while (nextNode != null) {
                String[] currentParts = current.getData().toString().split(" ");
                String[] nextParts = nextNode.getData().toString().split(" ");

                int currentScore = Integer.parseInt(currentParts[1]);
                int nextScore = Integer.parseInt(nextParts[1]);

                if (currentScore < nextScore) {
                    Object tempData = current.getData();
                    current.setData(nextNode.getData());
                    nextNode.setData(tempData);
                }

                nextNode = nextNode.getNext();
            }
            current = current.getNext();
        }
    }
    
    
}