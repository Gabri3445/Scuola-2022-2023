import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static McDonald mcDonald;

    public static void main(String[] args) {
        int input;
        mcDonald.Burgers = new ArrayList<>();
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
        for (Burger burger : burgers) {
            System.out.println("------------------");
            System.out.println(burger.description + "\nQuantity Available: " + burger.quantity);
            System.out.println("Price: " + burger.price);
            System.out.println("ID :" + burger.id);
            System.out.println();
        }
    }

    static void buyItems(ArrayList<Burger> burgers) {
        ArrayList<Burger> receipt = new ArrayList<>();
        int input;
        do {
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
                }
                case 1 -> showReceipt(receipt);
            }
        } while (input != 2);
    }

    static void showReceipt(ArrayList<Burger> burgers) {

    }
}

class McDonald {
    ArrayList<Burger> Burgers;

    public McDonald(ArrayList<Burger> burgers) {
        Burgers = burgers;
    }

}

class Burger {
    String id;
    String description;
    int quantity;
    float price;

    public Burger(String id, String description, int quantity, float price) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}