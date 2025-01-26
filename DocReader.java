
//
// Krittameth Tansuwan 672115002
// Programming Assignment 2
//

import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.File;

public class DocReader {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis(); // Start counting execution time
        Scanner sc = new Scanner(new File(args[0]));
        ArrayList<String> arr = new ArrayList<>(); // Store each line of the file in an array list
        ArrayList<Integer> characterCountArr = new ArrayList<>(); //Store the count of each character in an array list
        int tokenIndex = 0;
        int totalLine = 0;

        totalLine = tokenizeString(sc, totalLine, arr);
        countCharacter(sc, totalLine, arr, characterCountArr, tokenIndex);
        int totalCharacter = (int) countTotalCharacters(characterCountArr, tokenIndex);
        int totalEmoticon = countEmoticon(arr);
        double avgToken = averageTokenSize(characterCountArr, tokenIndex);
        int longestToken = longestTokenSize(characterCountArr, tokenIndex);

        System.out.println("Character count: " + totalCharacter);
        System.out.println("Total emoticons: " + totalEmoticon);
        System.out.println("Total line: " + totalLine);
        System.out.println("Total token: " + tokenSize(arr));
        System.out.println("Average token size is " + avgToken);
        System.out.println("Longest token size is " + longestToken);
        countPalindrome(arr, characterCountArr);

        long endsTime = System.currentTimeMillis();
        long totalTime = endsTime - startTime; // Summarize total execution time
        System.out.println("Total execution time: " + totalTime + " ms");
        System.out.println("Program terminated properly!");
    }

    public static int tokenizeString(Scanner sc, int totalLine, ArrayList<String> arr) { // Start tokenizeString method
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            if (!line.trim().isEmpty()) {
                totalLine++;
                while (st.hasMoreTokens()) {
                    arr.add(st.nextToken().trim());
                }
            }
        }
        return totalLine;
    } // End tokenizeString method

    public static void countCharacter(Scanner sc, int totalLine, ArrayList<String> arr, ArrayList<Integer> characterCountArr, int tokenIndex) { // Start countCharacter method
        for (int i = 0; i < arr.size(); i++) {
            int countCharacter = 0;
            while (arr.get(i).length() > countCharacter) {
                countCharacter++;
            }
            characterCountArr.add(countCharacter);
        }
    } // End of countCharacter method

    public static double countTotalCharacters(ArrayList<Integer> characterCountArr, int tokenIndex) { // Start countTotalCharacters
        if (tokenIndex >= characterCountArr.size()) {
            return 0;
        }
        return characterCountArr.get(tokenIndex) + countTotalCharacters(characterCountArr, tokenIndex + 1);
    } // End of countTotalCharacters method

    public static double averageTokenSize(ArrayList<Integer> characterCountArr, int tokenIndex) { // Start averageTokenSize method
        return countTotalCharacters(characterCountArr, tokenIndex)/ characterCountArr.size();
    } // End of averageTokenSize method

    public static int longestTokenSize(ArrayList<Integer> characterCountArr, int tokenIndex) { // Start longestTokenSize method
        if (tokenIndex >= characterCountArr.size()) {
            return 0;
        }
        return Math.max(characterCountArr.get(tokenIndex), longestTokenSize(characterCountArr, tokenIndex + 1));
    } // End of longestTokenSize method

    public static int countEmoticon(ArrayList<String> arr) { // Start countEmoticon method
        int count = 0;
        ArrayList<String> emoticons = new ArrayList<>();
        emoticons.add(":)"); emoticons.add(":-)"); emoticons.add(":D");
        emoticons.add(":-D");emoticons.add(":]");  emoticons.add(":-]");
        emoticons.add("^_^");emoticons.add(":P");  emoticons.add(":-P");
        emoticons.add(":3"); emoticons.add("xD");  emoticons.add("=)");
        emoticons.add("^^"); emoticons.add(":(");  emoticons.add(":-(");
        emoticons.add("D:"); emoticons.add(":'("); emoticons.add(">.<");
        emoticons.add("T_T");emoticons.add(";-;"); emoticons.add(";)");
        emoticons.add(";-)");emoticons.add(":*>"); emoticons.add("^_~");
        emoticons.add(":O"); emoticons.add(":-O"); emoticons.add("o_O");
        emoticons.add("O_O");emoticons.add(">:D"); emoticons.add(">:(");
        emoticons.add(":|"); emoticons.add(":-|"); emoticons.add("._.");
        emoticons.add("-_-");emoticons.add(">w<"); emoticons.add(":^)");
        emoticons.add("<3"); emoticons.add("@_@");
        for (int i = 0; i < arr.size(); i++) {
            for (String emoticon : emoticons) {
                if (arr.get(i).contains(emoticon)) {
                    count++;
                }
            }
        }
        return count;
    } // End of countEmoticon method

    public static void countPalindrome(ArrayList<String> arr, ArrayList<Integer> characterCountArr) { // Start countPalindrome method
        int totalPalindrome = 0;
        for (int i = 0; i < arr.size(); i++) {
            boolean isPalindrome = true;
            int textLength = arr.get(i).length();
            for (int front = 0, back = textLength -1  ; front != back; front++, back--) {
                if (arr.get(i).charAt(front) != arr.get(i).charAt(back)) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) {
                totalPalindrome +=1;
            }
        }
        System.out.println("Total palindrome: " + totalPalindrome);
    } // End of countPalindrome method

    public static int tokenSize(ArrayList<String> arr) { // Start tokenSize method
        return arr.size(); 
    } // End of tokenSize method

}
