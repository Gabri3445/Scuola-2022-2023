import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static McDonald mcDonald;

    public static void main(String[] args) {
        int input;
        ArrayList<Burger> initBurgers = new ArrayList<>();
        initBurgers.add(new Burger(0, "Double Burger BBQ", 2, new BigDecimal("2.90")));
        initBurgers.add(new Burger(1, "Crispy McBacon", 2, new BigDecimal("6.50")));
        initBurgers.add(new Burger(2, "Double Chicken BBQ", 2, new BigDecimal("2.90")));
        initBurgers.add(new Burger(3, "Double Cheeseburger", 2, new BigDecimal("2.90")));
        initBurgers.add(new Burger(4, "McChicken", 2, new BigDecimal("6.20")));
        mcDonald = new McDonald(initBurgers);
        do {
            System.out.println("""
                    [0] Print Menu
                    [1] Buy burgers
                    [2] Exit
                    """);
            input = scanner.nextInt();
            switch (input) {
                case 0 -> printMenu(mcDonald.Burgers);
                case 1 -> buyItems(mcDonald.Burgers);
            }
        } while (input != 2);
    }

    static void printMenu(ArrayList<Burger> burgers) {
        System.out.println("Burgers available");
        printBurgers(burgers, true);
    }

    static void printBurgers(ArrayList<Burger> burgers, boolean showQuantity) {
        System.out.println();
        for (Burger burger : burgers) {
            System.out.println("------------------");
            System.out.println(burger.description);
            if (showQuantity) {
                System.out.println("Quantity Available: " + burger.quantity);
            } else {
                System.out.println("Quantity bought: " + burger.boughtQuantity);
            }
            System.out.println("Price: " + burger.price);
            System.out.println("ID: " + burger.id);
            System.out.println();
        }
    }

    static void buyItems(ArrayList<Burger> burgers) {
        ArrayList<Burger> receipt = new ArrayList<>();
        int input;
        do {
            boolean exit = false;
            System.out.println("""
                    [0] Buy burgers
                    [1] Finalize
                    [2] Exit back to menu
                    """);
            input = scanner.nextInt();
            switch (input) {
                case 0 -> {
                    System.out.println("Enter id of the burger you wish to buy");
                    printMenu(burgers);
                    input = scanner.nextInt();
                    try {
                        Burger burger = getBurger(input, burgers);
                        if (burger.quantity >= 0) {
                            burger.boughtQuantity++;
                            burger.quantity--;
                            receipt.add(burger);
                        } else {
                            System.out.println("Not enough burgers");
                        }
                    } catch (RuntimeException rE) {
                        System.out.println("Burger not found");
                    }
                }
                case 1 -> {
                    showReceipt(receipt);
                    exit = true;
                }
            }
            if (exit) {
                break;
            }
        } while (input != 2);
    }

    static Burger getBurger(int id, ArrayList<Burger> burgers) throws RuntimeException {
        for (Burger burger : burgers) {
            if (burger.id == id) {
                return burger;
            }
        }
        throw new RuntimeException();
    }

    static void showReceipt(ArrayList<Burger> burgers) {
        BigDecimal money;
        BigDecimal required = new BigDecimal(0);
        for (Burger burger : burgers) {
            required = required.add(burger.price);
        }
        boolean temp = false;
        do {
            System.out.println("Enter " + required + " or more");
            money = scanner.nextBigDecimal();
            if (money.compareTo(required) > 0) {
                temp = true;
            }
        } while (!temp);
        System.out.println("Receipt");
        printBurgers(burgers, false);
        for (Burger burger : burgers) {
            burger.boughtQuantity = 0;
        }
        System.out.println("Remainder = " + (money.subtract(required)));
    }
}

class McDonald {
    ArrayList<Burger> Burgers;

    public McDonald(ArrayList<Burger> burgers) {
        Burgers = burgers;
    }

}

class Burger {
    int id;
    String description;
    int quantity;
    BigDecimal price;

    int boughtQuantity;

    public Burger(int id, String description, int quantity, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}