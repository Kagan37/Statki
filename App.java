import java.util.Scanner;

enum Choice {
    ROCK, PAPER, SCISSORS;

    public int compare(Choice other) {
        if (this == other) {
            return 0;
        }

        switch (this) {
            case ROCK:
                return (other == SCISSORS) ? 1 : -1;
            case PAPER:
                return (other == ROCK) ? 1 : -1;
            case SCISSORS:
                return (other == PAPER) ? 1 : -1;
        }

        return 0;
    }
}

class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Choice makeChoice() {
        System.out.println(name + ", wybierz kamień (1), papier (2) lub nożyce (3):");
        Scanner scanner = new Scanner(System.in);

        int choiceNumber;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                scanner.next(); // Wyczyszczenie bufora wejściowego
            }
            choiceNumber = scanner.nextInt();
        } while (choiceNumber < 1 || choiceNumber > 3);

        switch (choiceNumber) {
            case 1:
                return Choice.ROCK;
            case 2:
                return Choice.PAPER;
            case 3:
                return Choice.SCISSORS;
            default:
                // Nie powinno się zdarzyć
                return null;
        }
    }

    public String getName() {
        return name;
    }
}

class Game {
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        do {
            playRound();
            System.out.println("Czy chcesz zagrać jeszcze jedną rundę? (tak/nie):");
        } while (shouldContinue());

        System.out.println("Dziękujemy za grę!");
    }

    private void playRound() {
        Choice choice1 = player1.makeChoice();
        Choice choice2 = player2.makeChoice();

        System.out.println(player1.getName() + " wybrał/a: " + choice1);
        System.out.println(player2.getName() + " wybrał/a: " + choice2);

        int result = choice1.compare(choice2);
        if (result == 0) {
            System.out.println("Remis!");
        } else if (result > 0) {
            System.out.println(player1.getName() + " wygrywa!");
        } else {
            System.out.println(player2.getName() + " wygrywa!");
        }
    }

    private boolean shouldContinue() {
        Scanner scanner = new Scanner(System.in);
        String response;
        do {
            response = scanner.next().toLowerCase();
        } while (!response.equals("tak") && !response.equals("nie"));

        return response.equals("tak");
    }
}

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Gracz 1, podaj swoje imię:");
            String name1 = scanner.nextLine();
            Player player1 = new Player(name1);

            System.out.println("Gracz 2, podaj swoje imię:");
            String name2 = scanner.nextLine();
            Player player2 = new Player(name2);

            Game game = new Game(player1, player2);
            game.play();
        }
    }
}
