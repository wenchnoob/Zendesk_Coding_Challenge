package com.zendesk.ticketviewer.utils;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class CLIUtils {

    private final static Scanner in = new Scanner(System.in);
    private final static Pattern yesPat = Pattern.compile("[Yy[Ee[Ss]\\*]\\*]*");
    private final static Pattern noPat = Pattern.compile("[Nn[Oo]\\*]*");

    public void initializeCLI(String url) {
        System.out.println("Welcome to the Zendesk Ticket Viewer CLI!");
        System.out.println("Would you like to view the tickets for this Sub Domain: " + url + "\n");
        UserChoice choice = getChoice();
    }

    public void viewNextPage() {
        System.out.println("Would you like to view the next page?");
        UserChoice choice = getChoice();
    }

    public UserChoice getChoice() {
        System.out.println("Input yes or no: ");
        String res = in.nextLine();
        return parseChoice(res);
    }

    public UserChoice parseChoice(String choice) {
        if(yesPat.matcher(choice).matches()) {
            return UserChoice.YES;
        } else if (noPat.matcher(choice).matches()) {
            return UserChoice.NO;
        } else  {
            return UserChoice.UNKNOWN;
        }
    }

    enum UserChoice {
        YES, NO, BACK, NEXT, UNKNOWN;
        UserChoice() {}
    }

}
