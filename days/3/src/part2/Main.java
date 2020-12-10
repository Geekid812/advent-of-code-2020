package part2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int checkSlope(int right, int down) {
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
        int y = down;
        int maxX = rows.get(0).length();
        int maxY = rows.size();
        int treeCount = 0;

        while (y < maxY) {
            for (int i = 0; i < right; i++) {
                x++;
                if (x >= maxX) {x = 0;}
            }

            if (rows.get(y).charAt(x) == '#') {
                // We bonked into a tree
                treeCount++;
            }
            y = y + down;
        }

        return treeCount;
    }

    public static void main(String[] args) {
        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        long result = 1;

        for (int[] slope : slopes) {
            int treeCount = checkSlope(slope[0], slope[1]);

            System.out.println(slope[0]+" right, "+slope[1]+" down: We hit "+treeCount+" trees!");
            result = result * treeCount;
        }

        System.out.println("The result is "+result+"!");
    }
}
