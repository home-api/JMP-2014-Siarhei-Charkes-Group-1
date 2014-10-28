package task01;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Yahor_Karabitsyn on 10/28/2014.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the input file name:");
        String inputFileName = scanner.nextLine();

        System.out.println("Please enter the output file name:");
        String outputFileName = scanner.nextLine();

        Copier fileCopier = new Copier();
        List<String> errors = fileCopier.copy(new File(inputFileName), new File(outputFileName));

        if (!errors.isEmpty()) {
            printAllExceptions(errors);
        }
    }

    private static void printAllExceptions(List<String> errors) {
        System.out.println("Something went wrong...");
        for (String error : errors) {
            System.out.println(error);
        }
    }

}
