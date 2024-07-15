package view;

import model.Movie;
import model.enums.Rating;
import model.interfaces.Media;
import util.ScanValidation;

import java.util.Scanner;

public class RatingMenu {

    public static void Show(Media media) {
        boolean active = true;
        Scanner sc = new Scanner(System.in);
        while (active) {
            System.out.println("Você deseja avaliar este título? Digite S - para SIM ou N - para NÃO ");
            String option = ScanValidation.getValidStringInput(sc, 1);
            switch (option.toUpperCase()) {
                case "S": {
                    avaliarTitulo(sc, media);
                    active = false;
                    break;
                }
                case "N":
                    active = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void avaliarTitulo(Scanner sc, Media media) {
        System.out.println("Qual sua nota para este títul de 1 a 5 estrelas?");
        while (true) {
            int nota = ScanValidation.getValidIntInput(sc);
            if (nota >= 1 && nota <= 5) {
                Rating rating = Rating.values()[nota - 1];
                System.out.println("Você escolheu: ");
                System.out.println(rating.toString());
                media.getRatings().add(rating);
                System.out.println("Título avaliado com sucesso.");
                break;
            }
            System.out.println("Opção inválida. Por favor, digite um número de 1 a 5.");
        }
    }
}
