/*
Cameron Stone
P4
12/4/2025
did not work with teammate because they used AI to do all the programming.
*/
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
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
    static String user;
    static double balance;
    static Scanner scan = new Scanner(System.in);
    static String restaurantName = "giardino degli ulivi"; //Olive Garden in italian
    static ArrayList<String> accounts = new ArrayList<String>();
    static  ArrayList<String> order = new ArrayList<String>(); //Current order
    public static void main(String[] args)
    {
        String input;
        ColoredPrintln(restaurantName, "green");
        ColoredPrint("welcome to the ", "green");
        ColoredPrintln("Restaurant Ordering System", "red");
        String tempUsername = "";
        String tempPassword = "";
        boolean valid = false;
        boolean file = true;
        try
        {
            Scanner reader = new Scanner(new File("Accounts.txt"));
            for (int i = 0; reader.hasNextLine(); i++)
            {
                accounts.add(reader.nextLine());
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            file = false;
        }
        boolean loop = true;
        while (loop)
        {
            if (file)
            {
                System.out.print("Would you like to sign in or create a new account ");
                ColoredPrint("1/2: ", "blue");
            }
            else
            {
                System.out.print("Would you like to create a new account ");
                ColoredPrint("y/n: ", "blue");
            }
            input = scan.next();
            switch (input)
            {
                case "1" ->
                {
                    while (!valid)
                    {
                        loop = false;
                        ColoredPrint("Please enter your username: ", "blue");
                        tempUsername = scan.next();
                        ColoredPrint("Please enter your password: ", "blue");
                        tempPassword = scan.next();
                        for (int i = 0; i < accounts.size(); i++)
                        {
                            if (tempUsername.equals(accounts.get(i)) && !valid && tempPassword.equals(accounts.get(i+1)))
                            {
                                valid = true;
                                user = accounts.get(i);
                                balance = Double.parseDouble(accounts.get(i+2));
                                break;
                            }
                        }
                        if(!valid) ColoredPrintln("Wrong username or password, please try again:", "red");
                    }
                }
                case "2", "y", "Y" ->
                {
                    ColoredPrint("Please enter your username: ", "blue");
                    tempUsername = scan.next();
                    ColoredPrint("Please enter your password: ", "blue");
                    tempPassword = scan.next();
                    FileManager(tempUsername, true);
                    FileManager(tempPassword, true);
                    FileManager("", false);
                    file = true;
                    accounts.add(tempUsername);
                    accounts.add(tempPassword);
                    accounts.add("0");
                }
                case "n", "N" ->
                {
                    ColoredPrint("Thank you for using the ", "green");
                    ColoredPrintln("Restaurant Ordering System", "red");
                    System.exit(0);
                }
                default ->
                {
                    ColoredPrintln("error please try again", "red");
                }
            }
        }
        System.out.print("Welcome ");
        ColoredPrintln(user, "green");
        loop = true;
        while (loop)
        {
            System.out.print("Would you like to continue to ordering, add funds, or exit ");
            ColoredPrintln("1/2/3: ", "blue");
            input = scan.next();
            switch (input)
            {
                case "1" ->
                {
                    ColoredPrintln(restaurantName, "green");
                    loop = false;
                    Menu();
                }
                case "2" ->
                {
                    System.out.println("Balance: $" + balance);
                    valid = false;
                    while (!valid)
                    {
                        ColoredPrint("please input the amount you would like to add: ", "blue");
                        input = scan.next();
                        try
                        {
                            balance += Double.parseDouble(input);
                            valid = true;
                        }
                        catch (NumberFormatException e)
                        {
                            ColoredPrintln("error please try again", "red");
                        }
                        System.out.println("Balance: $" + balance);
                    }

                }
                case "3" ->
                {
                    exit();
                }
                default ->
                {
                    ColoredPrintln("error please try again", "red");
                }
            }
        }
    }
    private static void FileManager(String txt, boolean wipe)
    {
        try
        {
            File accounts =  new File("Accounts.txt");
            FileWriter fw = new FileWriter(accounts, true);
            if(!txt.equals("null")) fw.write(txt + "\n");
            fw.flush();
            fw.close();
            if(wipe) new FileOutputStream("Accounts.txt").close();
        }
        catch (Exception e)
        {
            ColoredPrintln("There was an error when trying to write to file", "red");
            System.exit(0);
        }
    }

    private static void Menu()
    {
        String input;
        int numInput;
        boolean loop = true;
        boolean check = true;
        for (int i = 0; i < food.length; i++)
        {
            ColoredPrint(i + 1 + ":" + food[i][0].toString(), "blue");
            System.out.println(", "+ food[i][1].toString() + ":");
            ColoredPrintln("$" + food[i][2].toString(), "yellow");
        }
        boolean error = false;
        while(loop)
        {
            if (error)
            {
                ColoredPrintln("error please try again", "red");
                error = false;
            }
            else ColoredPrint("Please enter the item number to add it to your order: ", "blue");
            numInput = scan.nextInt();
            for (int i = 0; i < food.length; i++)
            {
                if (numInput - 1 == i)
                {
                    order.add(food[i][0].toString());
                    ColoredPrintln(food[i][0].toString() + " added", "green");
                    error = false;
                    break;
                }
                else error = true;
            }
            if (!error)
            {
                check = true;
                System.out.print("would you like to continue to payment or add another item ");
                ColoredPrintln("1/2:", "blue");
                while(check)
                {
                    input = scan.next();
                    switch (input)
                    {
                        case "1" ->
                        {
                            check = false;
                            loop = false;
                            order();
                        }
                        case "2" ->
                        {
                            check = false;
                        }
                        default -> ColoredPrintln("error please try again", "red");
                    }
                }
            }
        }
    }
    private static void order() {
        String input;

        for (int i = 0; i < order.size(); i++)
        {
            for (int j = 0; j < food.length; j++)
            {
                if (order.get(i) == food[j][0].toString())
                {
                    System.out.println(order.get(i) + " " + food[j][2]);
                }
            }
        }
        System.out.print("Would you like to change your order or continue to payment ");
        ColoredPrint("1/2: ", "blue");
        boolean loop = true;
        while (loop)
        {
            input = scan.next();
            switch (input)
            {
                case "1" ->
                {
                    for (int i = order.size(); i > 0; i--)
                    {
                        order.remove(i-1);
                    }
                    Menu();
                    loop = false;
                }
                case "2" ->
                {
                    loop = false;
                    receipt();
                }
                default -> ColoredPrintln("error please try again", "red");
            }
        }
    }
    private static void receipt()
    {
        double tax = 8.38; //Tax rate
        double taxTotal = 0; //Total tax in dollars
        double subTotal = 0; //Price before tax
        double Total = 0; //Price after tax
        double cashAmount = 0; //Amount of cash given
        double cashBack = 0; //Cash to give back
        for (int i = 0; i < order.size(); i++)
        {
            for (int j = 0; j < food.length; j++)
            {
                if (order.get(i) == food[j][0].toString())
                {
                    subTotal += Double.parseDouble(food[j][2].toString());
                }
            }
        }
        subTotal = Math.round(subTotal * 100.0) / 100.0;
        taxTotal = Math.round((subTotal * tax) * 0.01 * 100.0) / 100.0;
        Total = Math.round((subTotal + taxTotal) * 100.0) / 100.0;
        System.out.println("");
        System.out.println("subTotal:  $" + subTotal);
        System.out.println("tax:       $" + taxTotal);
        System.out.println("Total:     $" + Total);
        System.out.println("balance:   $" + balance);
        if (Total > balance)
        {
            System.out.println("remainder: $" + (Total - balance));
            balance = 0;
            System.out.println("Please input cash amount: ");
            cashAmount = scan.nextDouble();
            cashBack = Math.round((cashAmount - Total) * 100.0) / 100.0;
            System.out.println("cashBack:  $" + cashBack);
            exit();
        }
        else
        {
            System.out.println("remainder: $0");
            balance -= Total;
            balance = Math.round(balance * 100.0) / 100.0;
            System.out.println("remaining balance: $" + balance);
            exit();
        }
    }
    private static void exit()
    {
        ColoredPrint("Thank you for using the ", "green");
        ColoredPrintln("Restaurant Ordering System", "red");
        for(int i = 0; i < accounts.size(); i++)
        {
            if (user.equals(accounts.get(i)))
            {
                accounts.add(i+2, String.valueOf(balance));
                accounts.remove(i+3);
            }
        }
        FileManager("null", true);
        for (int i = 0; i < accounts.size(); i++)
        {
            FileManager(accounts.get(i), false);
        }
        System.exit(0);
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
}