package com.epam;

import com.epam.jms.client.InfoContactClient;

import java.util.Scanner;

/**
 * Hello world!
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please, enter login name. If you want to exit, please enter 'exit'");
            String contactName = scanner.nextLine();
            if (contactName.equals("exit")) {
                System.out.println("Bye..");
                return;
            } else {
                loadContact(contactName);
            }
        }
    }

    private static void loadContact(String contactName) {
        System.out.println("Loading contact..");
        InfoContactClient client = new InfoContactClient();
        client.sendAndReadMessage(contactName);
    }
}
