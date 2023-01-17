import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int input;
        ArrayList<Burger> initBurgers = new ArrayList<>();
        initBurgers.add(new Burger(0, "Double Burger BBQ", 2, new BigDecimal("2.90")));
        initBurgers.add(new Burger(1, "Crispy McBacon", 2, new BigDecimal("6.50")));
        initBurgers.add(new Burger(2, "Double Chicken BBQ", 2, new BigDecimal("2.90")));
        initBurgers.add(new Burger(3, "Double Cheeseburger", 2, new BigDecimal("2.90")));
        initBurgers.add(new Burger(4, "McChicken", 2, new BigDecimal("6.20")));
        McDonald mcDonald = new McDonald(initBurgers);
        do {
            System.out.println("""
                    [0] Print Menu
                    [1] Buy burgers
                    [2] Exit
                    """);
            input = scanner.nextInt();
            switch (input) {
                case 0 -> mcDonald.printMenu(mcDonald.getBurgers());
                case 1 -> mcDonald.buyItems(mcDonald.getBurgers());
            }
        } while (input != 2);
    }
}