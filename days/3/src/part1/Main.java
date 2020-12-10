package part1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> rows = new ArrayList<>();

        try {
            File inputFile = new File("input.txt");
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                rows.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int x = 0;
        int y = 1;
        int maxX = rows.get(0).length();
        int maxY = rows.size();
        int treeCount = 0;

        while (y < maxY) {
            for (int i = 0; i < 3; i++) {
                x++;
                if (x >= maxX) {x = 0;}
            }

            if (rows.get(y).charAt(x) == '#') {
                // We bonked into a tree
                treeCount++;
            }
            y++;
        }

        System.out.println("We hit "+treeCount+" trees!");
    }
}
