package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ScanValidation {
    public static int getValidIntInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } else {
                System.out.println("Entrada inválida. Digite um número inteiro: ");
                scanner.nextLine();
            }
        }
    }


    public static int getValidIntBetweenInput(Scanner scanner, int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if(choice >= min && choice <= max) {
                    scanner.nextLine();
                    return choice;
                }
                else{
                    System.out.printf("Entrada inválida. Digite um número entre %d e %d ", min+1, max);
                }
            } else {
                System.out.println("Entrada inválida. Digite um número válido: ");
                scanner.nextLine();
            }
        }
    }

    public static String getValidStringInput(Scanner scanner, int minLength) {
        while (true) {
            String input = scanner.nextLine();
            if (input.length() >= minLength) {
                return input;
            } else {
                System.out.printf("Entrada inválida. Digite uma palavra com pelo menos %d caracteres: ", minLength);
            }
        }
    }

    public static String getValidDayInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                String choiceParsed = String.valueOf(choice);

                if(choice >= 1 && choice <= 9){
                     choiceParsed = "0" + choice;
                }

                if(choice >= 1 && choice <= 31 && choiceParsed.length() == 2) {
                    return choiceParsed  + "/";
                }
                else{
                    System.out.println("Entrada inválida. Digite um dia com exatos " + 2 + " números:" +
                            String.valueOf(choice).length());
                }
            }
            else {
                System.out.println("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    public static String getValidMonthInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                String choiceParsed = String.valueOf(choice);

                if(choice >= 1 && choice <= 9){
                    choiceParsed = "0" + choice;
                }

                if(choice >= 1 && choice <= 12 && choiceParsed.length() == 2) {
                    return choiceParsed + "/";
                }
                else{
                    System.out.println("Entrada inválida. Digite um mês com exatos " + 2 + " números: ");
                }
            }
            else {
                System.out.println("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    public static String getValidYearInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if(choice >= 1 && choice <= LocalDate.now().getYear() && String.valueOf(choice).length() == 4) {
                    return String.valueOf(choice);
                }
                else{
                    System.out.println("Entrada inválida. Digite um ano com exatos " + 4 + " números: ");
                }
            }
            else {
                System.out.println("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }
}
