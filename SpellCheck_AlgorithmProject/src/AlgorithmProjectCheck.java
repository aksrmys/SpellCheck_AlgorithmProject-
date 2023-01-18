import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AlgorithmProjectCheck {

    static String[] dictionaryArray = new String[5000];
    static double[] sumPenalty = new double[25];//The length of the text you enter should be a maximum of 25 words.
    static int indexPenalty = 0;
    public static String differentChar;
    public static boolean check = false;

    static class PenaltyClass {

        String word;
        Integer integer;

        public PenaltyClass(String word, Integer integer) {
            this.word = word;
            this.integer = integer;
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

//The part where we do the file reading and get a word or phrase from the user
        int index = 0;
        File file = new File("dictionary.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String str = scan.nextLine();
            dictionaryArray[index++] = str;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string for spell check: ");
        String[] usersWord = scanner.nextLine().replaceAll("\\p{Punct}", "").split(" ");;
        for (int j = 0; j < usersWord.length; j++) {
            System.out.println("**************\n");
            //The distance of the entered word to each suggested word, that is, the arraylist where we store the penalty points.
            ArrayList<PenaltyClass> penaltyList = new ArrayList();
            char[] charArray = new char[usersWord[j].length()];
            for (int i = 0; i < usersWord[j].length(); i++) {
                charArray[i] = usersWord[j].charAt(i);

            }
//We add all the letters on our keyboard as grapha vertex
            ArrayList<Character> let = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("letters.txt"));
            String line;
            QwertyGraph myGraph = new QwertyGraph(26);
            while ((line = reader.readLine()) != null) {

                for (char c : line.toCharArray()) {
                    let.add(c);
                    myGraph.addVertex(c);
                }
            }
            reader.close();
//We define the edges of the vertexes we get from the adjacency list according to their neighborhood status.
            int[][] adjList;

            adjList = AdjacencyMatrix.buildMatrix();
            myGraph.edges = adjList;

            check = check(usersWord[j]);
            if (!check) {
                String[] trueWords = new String[25];//Array size 25 because number of letters that can replace incorrectly entered letter
                boolean flag = false;
                int trueWordCount = 0;
                int count = 0;
                try {
                    count = (Integer) (differentChar(usersWord[j], dictionaryArray, charArray).keys().nextElement());
                    if (count != 0) {
                        flag = true;
                    }
                } catch (Exception e) {
                }
                myGraph.bfs(charArray[count]);
                int count2 = let.indexOf(charArray[count]);
                for (int k = 0; k < adjList[count2].length; k++) {
                    String possibleWords;
                    charArray[count] = myGraph.neighbours[k];
                    possibleWords = String.valueOf(charArray);
                    for (int i = 0; i < 5000; i++) {
                        if (possibleWords.equals(dictionaryArray[i])) {
                            trueWords[trueWordCount] = possibleWords;
                            trueWordCount++;
                        }
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (trueWords[i] != null) {
                        //Sorts suggested words from near to far by looking at the degree of neighborhood of the wrongly entered letter on the keyboard.
                        System.out.print("Do you mean: " + trueWords[i]);

                        for (int k = 0; k < myGraph.levelChar.size(); k++) {
                            if (trueWords[i].charAt(count) == (Character) myGraph.levelChar.get(k).character) { //We assign penalty points according to the neighborhood level of the wrong letter.
                                penaltyList.add(new PenaltyClass(trueWords[i], myGraph.levelChar.get(k).integer));
                                break;
                            }

                        }
                        System.out.println("--->Penalty point:" + penaltyList.get(i).integer);
                        System.out.println("The minimum number of operations required to convert the entered wrong word to the correct string:"
                                + editDistance(usersWord[j], trueWords[i]));//The number of operations required to convert the word to the correct word, such as displacement, update
                        flag = true;
                    }
                }

                if (!flag) {
                    System.out.println("Do you mean(SWAP): " + swap(usersWord[j]));
                    System.out.println("The minimum number of operations required to convert the entered wrong word to the correct string(SWAP):" + editDistance(usersWord[j], swap(usersWord[j])));
                    sumPenalty[indexPenalty] = 0.75;
                    indexPenalty++;
                }

                System.out.print("Enter the word you want from the suggested words: ");

                String newWord = scanner.nextLine();
                System.out.print("\n");

                for (int i = 0; i < trueWordCount; i++) { //We choose the correct word to be written from the suggested words and save the penalty score.
                    if (newWord.equalsIgnoreCase(trueWords[i])) {
                        sumPenalty[indexPenalty] = penaltyList.get(i).integer;
                        indexPenalty++;
                        break;
                    }

                }

            }
        }
        double sum = 0;
        System.out.println("**************");

        // Total point penalty for converting every wrong word in the text to the correct one
        for (int i = 0; i < sumPenalty.length; i++) {
            sum += sumPenalty[i];
        }

        System.out.println("Sum of penalty points in the text entered: " + sum);

    }
//We compare the equality of the ascii values of all the words in our dictionary with the word entered by the user.

    public static boolean isAsciiEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int s1Ascii = 0;
        int s2Ascii = 0;
        for (int i = 0; i < s1.length(); i++) {
            s1Ascii += (int) s1.charAt(i);
            s2Ascii += (int) s2.charAt(i);
        }
        return s1Ascii == s2Ascii;
    }

    /*We provide letter check for each word with equal ascii value and assign 1 to the index of an array for each equal letter, 
  and 0 for different characters. If the number of different characters is more than 2, 
  we cannot swap, but if the number of different characters is 2, we can swap.*/
    public static int[] compare(String s1) {

        int[] arr = null;
        for (int j = 0; j < dictionaryArray.length; j++) {
            arr = new int[s1.length()];
            int countDifferentLetter = 0;

            if (isAsciiEqual(s1, dictionaryArray[j])) {
                for (int k = 0; k < s1.length(); k++) {
                    if (s1.charAt(k) == dictionaryArray[j].charAt(k)) {
                        arr[k] = 1;
                    } else {
                        arr[k] = 0;
                        countDifferentLetter++;
                    }
                }
                if (countDifferentLetter == 2) {
                    break;
                }
            }
        }
        int count = 0;
        int[] index = new int[2];
        int index_array = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count++;
            }
        }
        if (count == 2) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 0) {
                    index[index_array++] = j;
                }
            }
        }

        return index;
    }
//We swap the positions of the wrong letters we checked with Compare according to the correct word.

    public static String swap(String s1) {

        int[] index = compare(s1);
        char[] chars = s1.toCharArray();
        char temp = chars[index[0]];
        chars[index[0]] = chars[index[1]];
        chars[index[1]] = temp;

        return new String(chars);

    }
//It compares each entered word with all the words in the dictionary and checks for words with only one misspelling between them.

    public static Dictionary differentChar(String usersWord, String[] array, char[] c1) {
        int dictionaryWord = 0;
        if (check == false) {
            while (dictionaryWord < 5000) {
                Hashtable diff = new Hashtable();
                if (usersWord.length() == array[dictionaryWord].length()) {

                    char[] c2 = new char[array[dictionaryWord].length()];
                    for (int i = 0; i < array[dictionaryWord].length(); i++) {
                        c2[i] = array[dictionaryWord].charAt(i);
                    }
                    for (int i = 0; i < c1.length; i++) {
                        if (c1[i] != c2[i]) {
                            diff.put(i, c1[i]);

                        }
                    }
                    if (diff.size() == 1) {
                        return diff;
                    }

                }
                dictionaryWord++;
            }
        }
        return null;
    }
//Minimum number of operations to convert one string to another string.

    public static int editDistance(String s1, String s2) {
        int lengthOfS1 = s1.length();
        int lengthOfS2 = s2.length();
        int[][] distance = new int[lengthOfS1 + 1][lengthOfS2 + 1];
        for (int i = 0; i <= lengthOfS1; i++) {
            for (int j = 0; j <= lengthOfS2; j++) {
                if (i == 0) {
                    distance[i][j] = j;
                } else if (j == 0) {
                    distance[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = 1 + Math.min(distance[i][j - 1],
                            Math.min(distance[i - 1][j], distance[i - 1][j - 1]));
                }
            }
        }
        return distance[lengthOfS1][lengthOfS2];
    }
//We check if the entered string is in the dictionary.
    public static boolean check(String str) {
        for (int j = 0; j < str.length(); j++) {
            boolean flag = false;
            for (int i = 0; i < dictionaryArray.length; i++) {
                if (str.equalsIgnoreCase(dictionaryArray[i])) {
                    flag = true;
                    return true;
                }
            }
            if (flag == false) {
                System.out.println(str + " is not valid!!!");
            }
            return false;
        }
        return false;
    }
}