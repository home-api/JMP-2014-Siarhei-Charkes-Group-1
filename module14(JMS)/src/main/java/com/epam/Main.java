package com.epam;

import com.epam.jms.client.InfoContactClient;

import java.util.Scanner;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        loadContact("egor");
    }

    private void read() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Integer contactId = scanner.nextInt();

            if (contactId <= 0) {
                return;
            } else {
                loadContact("");
            }
        }
    }


    private static void loadContact(String contactName) {
        InfoContactClient client = new InfoContactClient();
        client.sendAndReadMessage(contactName);
    }
}
