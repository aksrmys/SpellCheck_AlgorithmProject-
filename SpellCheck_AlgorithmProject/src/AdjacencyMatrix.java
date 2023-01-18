import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//This class is the class where we create our keyboard and record the distances of each letter from other letters to the matrix.
public class AdjacencyMatrix {

    private static final char[][] keyboard = {
        {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', ' '},
        {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ' '},
        {'z', 'x', 'c', 'v', 'b', 'n', 'm', ' '},};

    private static final int numOfLetters = 26;

    private static final int[][] AdjacencyMatrix = new int[numOfLetters][numOfLetters];

    public static int[][] buildMatrix() {

        ArrayList<Character> letters = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("Letters.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    letters.add(c);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not found");
        } catch (IOException ex) {
            System.out.println("");
        }

        int[][] adjList = new int[26][26];

        try {
            File myObj = new File("weightKeyboard.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String temp[] = data.split(" ");
                for (int j = 0; j < temp.length; j++) {
                    adjList[i][j] = Integer.parseInt(temp[j]);
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }

        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length; j++) {
                char c = keyboard[i][j];
                int rowIndex = letters.indexOf(c);

                try {

                    if (j + 1 < keyboard[i].length) {
                        char rightNeighbor = keyboard[i][j + 1];
                        int colIndex = letters.indexOf(rightNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (j - 1 >= 0) {
                        char leftNeighbor = keyboard[i][j - 1];
                        int colIndex = letters.indexOf(leftNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (i + 1 < keyboard.length) {
                        char subNeighbor = keyboard[i + 1][j];
                        int colIndex = letters.indexOf(subNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (i - 1 >= 0) {
                        char parentNeighbor = keyboard[i - 1][j];
                        int colIndex = letters.indexOf(parentNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (i + 1 < keyboard.length && j + 1 < keyboard[i].length) {
                        char lowerRightDiagonalNeighbor = keyboard[i + 1][j + 1];
                        int colIndex = letters.indexOf(lowerRightDiagonalNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (i - 1 >= 0 && j + 1 < keyboard[i].length) {
                        char upperRightDiagonalNeighbor = keyboard[i - 1][j + 1];
                        int colIndex = letters.indexOf(upperRightDiagonalNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (i + 1 < keyboard.length && j - 1 >= 0) {
                        char lowerLeftDiagonalNeighbor = keyboard[i + 1][j - 1];
                        int colIndex = letters.indexOf(lowerLeftDiagonalNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        char lowerLeftDiagonalNeighbor = keyboard[i - 1][j - 1];
                        int colIndex = letters.indexOf(lowerLeftDiagonalNeighbor);
                        AdjacencyMatrix[rowIndex][colIndex] = 1;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                }

            }
        }
        return AdjacencyMatrix;
    }
}