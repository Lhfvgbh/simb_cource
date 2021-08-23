package simbirSoft;

import java.util.Arrays;
import java.util.Scanner;

public class Execution {
    private String url;
    private String text;
    private TextReader textReader;
    private int arraySize;
    private char[] splitters;
    private boolean ignoreRegisterFlag;
    private Scanner scanner;

    public Execution() {
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.textReader = new TextReader();
    }

    public void enterURL() {
        System.out.println("Welcome to the WordCounter program!\n" +
                "Please, enter the URL to be read in the format of https://www.simbirsoft.com/");

        while (this.scanner.hasNext()) {
            url = this.scanner.next();
            if (url.isEmpty()) {
                System.out.println("URL is empty, please try again");
            } else {
                try {
                    this.text = this.textReader.readPage(url);
                    break;
                } catch (CanNotReadURLException e) {
                    System.out.println("URL is incorrect, please try again");
                }
            }
        }
    }

    public void enterCase() {
        System.out.println("Good!\n" +
                "Please enter Y to ignore the case, N to consider it");
        while (this.scanner.hasNext()) {
            String flag = this.scanner.next();
            if (!(flag.equals("Y") || flag.equals("N"))) {
                System.out.println("Please enter Y to ignore the case, N to consider it");
            } else {
                this.ignoreRegisterFlag = flag.equals("Y");
                break;
            }
        }
    }

    public void enterSplitters() {
        System.out.println("Great!\n" +
                "Please enter the amount of split marks you would like to use");
        while (this.scanner.hasNextInt()) {
            this.arraySize = this.scanner.nextInt();
            if (this.arraySize < 1) {
                System.out.println("Number in incorrect, please enter integer number greater than 0");
            } else {
                this.splitters = new char[this.arraySize];
                break;
            }
        }

        System.out.println("Cool!\n" +
                "Please enter " + this.arraySize + " of split marks one by one");
        for (int i = 0; i < this.arraySize; i++) {
            this.splitters[i] = this.scanner.next().charAt(0);
        }
        this.scanner.close();
    }

    public void showResult() {
        System.out.println(
                "The result word counter of text from URL " + this.url +
                        " with the following split marks: " + Arrays.toString(this.splitters));

        this.textReader.countWords(this.text, this.splitters, this.ignoreRegisterFlag);
    }

}
