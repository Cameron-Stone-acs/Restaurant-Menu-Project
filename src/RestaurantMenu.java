/*
Cameron Stone
P4
12/4/2025
did not work with teammate because they used AI to do all the programming.
*/
import java.util.Scanner;

public class RestaurantMenu {
    static Object[][] food =
            {//{Food Name, Description, Price}
                    {"Cheese Pizza", "New York style cheese pizza", 16.99},
                    {"Margherita Pizza", "Classic Italian pizza topped with basil", 15.99},
                    {"Carbonara", "Spaghetti covered in a creamy egg sauce with pan fried guanciale (no cream)", 19.99},
                    {"Fettuccine Alfredo", "Fettuccine pasta covered in a creamy alfredo sauce pared with sliced grilled chicken", 14.99},
                    {"Lasagna", "Long thin pasta layered in a bolognese sauce and creamy bechamel", 20.99},
                    {"Tortellini", "Tortellini pasta cooked in a creamy and cheesy sauce", 11.99},
                    {"Ravioli", "Chicken and cheese ravioli covered in a bolognese sauce", 13.99},
                    {"Amatriciana", "Spaghetti covered in a rich tomato sauce topped with pan fried guanciale", 18.99},
                    {"Gnocchi", "potato dumpling covered in a rich butter sauce with speck", 17.99},
                    {"Spaghetti and Meatballs", "Classic spaghetti and Meatballs with a rich tomato sauce", 12.99},
            };
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String restaurantName = "giardino degli ulivi"; //Olive Garden in italian
        String[] order = new String[1]; //Current order
        double tax = 8.38; //Tax rate
        double taxTotal = 0; //Total tax in dollars
        double subTotal = 0; //Price before tax
        double Total = 0; //Price after tax
        double cashAmount = 0; //Amount of cash given
        double cashBack = 0; //Cash to give back
        int orderQuantity = 0; //How many of an item to add to order
        Object input;
        ColoredPrintln(restaurantName, "green");
        System.out.println();
        ColoredPrint("welcome to the ", "green");
        ColoredPrintln("Restaurant Ordering System", "red");
        System.out.println();
        for (int i = 0; i < food.length; i++)
        {
            ColoredPrint(i + 1 + ":" + food[i][0].toString(), "blue");
            System.out.println(", "+ food[i][1].toString() + ":");
            ColoredPrintln("$" + food[i][2].toString(), "yellow");
        }
        System.out.println();
        System.out.println("Please input your order using the number or name:");
        boolean done = false;
        while (!done)
        {
            boolean valid = false;
            input = scan.nextLine();
            for (int i = 0; i < food.length; i++)
            {
                if (input.toString().equals(food[0][i]) || input - 1 == i)
                {
                    valid = true;
                    System.out.println(food[0][i] + " added to order");
                    for (int j = 0; j < order.length; j++)
                    {
                        if (order[j] != null) order[j] = food[0][i].toString();
                    }
                    break;
                }
            }
            if (!valid)
            {
                System.out.println("Please input a valid item using the number or name:");
            }
            else if (valid)
            {
                System.out.print("Would you like to add another item to your order?: ");
                ColoredPrintln("y/n", "blue");
            }
        }

    }
    //Prints inputted text in a chosen color using ansi escape codes
    private static void ColoredPrintln(String txt, String color)
    {
        if (color.equalsIgnoreCase("black")) {System.out.println("\u001B[30m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("red")) {System.out.println("\u001B[31m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("green")) {System.out.println("\u001B[32m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("yellow")) {System.out.println("\u001B[33m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("blue")) {System.out.println("\u001B[34m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("purple")) {System.out.println("\u001B[35m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("cyan")) {System.out.println("\u001B[36m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("white")) {System.out.println("\u001B[37m" + txt + "\u001B[0m");}
    }
    private static void ColoredPrint(String txt, String color)
    {
        if (color.equalsIgnoreCase("black")) {System.out.print("\u001B[30m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("red")) {System.out.print("\u001B[31m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("green")) {System.out.print("\u001B[32m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("yellow")) {System.out.print("\u001B[33m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("blue")) {System.out.print("\u001B[34m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("purple")) {System.out.print("\u001B[35m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("cyan")) {System.out.print("\u001B[36m" + txt + "\u001B[0m");}
        if (color.equalsIgnoreCase("white")) {System.out.print("\u001B[37m" + txt + "\u001B[0m");}
    }
    //Clears the console
    private static void ClearScreen() {System.out.print("\033[2J");}
}