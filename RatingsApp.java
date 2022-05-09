/* RatingApp Class written by Simon Monaghan April 2021 */

import java.util.Scanner;

public class RatingsApp {

    // declaring variables
    private static int MAX_NUM_ITEMS = 5;
    private static int MAX_NUM_RATINGS = 5;

    // creating method to show menu
    public static void showMenu() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("1 - Add a Restuarant");
        System.out.println("2 - Display all Restuarants");
        System.out.println("3 - Add a rating for a Restuarant");
        System.out.println("4 - Display all Restuarant Ratings");
        System.out.println("5 - Calculate and display the average rating for each Restuarant");
        System.out.println("6 - Display the Restuarant with the lowest average rating");
        System.out.println("7 - Exit Application");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Please enter your choice");
    }

    public static void main(String[] args) {

        // declaring and initialising local variables
        boolean check = true;
        int selection = 0;
        int index = 0;
        int rating = 0;
        int worst_item = 0;
        Item[] itemList;
 
        // creating an object of datatype String
        String itemName = new String();

        // creating an array
        itemList = new Item[MAX_NUM_ITEMS];

        // creating an object of type Scanner to allow input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // welcome information and advising the max restuarants/ratings allowed.
        System.out.println("Welcome to our Ratings Application for Restuarants!");
        System.out.println("The maximum number of restuarants that can be rated is " + MAX_NUM_ITEMS);
        System.out.println("The maximum number of ratings is " + MAX_NUM_RATINGS);

        // main loop until exit
        while (true) {
            // take user input and validate until correct integer received
            showMenu(); // calling the menu
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("error - this is not a valid input!");
                showMenu();
            }
            selection = scanner.nextInt();

            if (selection < 1 || selection > 7) {
                System.out.println("Invalid choice!");
                continue;
            }

            /* once the int is a valid choice - validation to check if max num of
             * restuarants have already been added.
             */
            switch (selection) {
            case 1:
                if (index >= MAX_NUM_ITEMS) {
                    System.out.println("The maximum number of restuarants has been reached!");
                    break;
                }

                // Taking the restuarant name from the user
                System.out.println("Enter the restuarant name:");
                itemName = scanner.next();

                // Checking for duplicate items
                check = true;
                for (int i = 0; i < index; i++) {
                    if (itemList[i].getName().equals(itemName)) {
                        System.out.println("This restuarant is already in the list!");
                        check = false;
                        break;
                    }
                }

                // Add the restuarant to the itemList at index 0 and increment 
                if (check) {
                    itemList[index] = new Item(itemName);
                    index++;
                }
                break;

            // display the restuarant list
            case 2:
                if (index == 0) {
                    System.out.println("There are no restuarants to display");
                    break;
                }
                for (int i = 0; i < index; i++) {
                    System.out.println("Restuarant " + (i + 1) + ":" + itemList[i].getName());
                }
                break;

            // add a rating
            case 3:
                // Taking the retuarant name from the user
                System.out.println("Enter the restuarant name:");
                itemName = scanner.next();

                check = true;

                for (int i = 0; i < index; i++) {
                    if (itemList[i].getName().equals(itemName)) {

                        // Taking the rating from the user and validate it
                        System.out.println("Enter the Rating(1-5):");
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Invalid input");
                        }
                        rating = scanner.nextInt();

                        // For invalid rating values
                        while (rating < 1 || rating > 5) {
                            System.out.println("Invalid input, Enter the Rating(1-5):");
                            while (!scanner.hasNextInt()) {
                                String input = scanner.next();
                                System.out.println("invalid input");
                            }
                            rating = scanner.nextInt();
                        }

                        itemList[i].addRating(rating);
                        check = false;
                        break;
                    }
                }
                if (check) {
                    System.out.println("This restuarant is not in the list!");
                }
                break;

            case 4:
                // Taking the retuarant name from the user
                System.out.println("Enter the restuarant name:");
                itemName = scanner.next();

                check = true;
                // Check for duplicate items
                for (int i = 0; i < index; i++) {
                    if (itemList[i].getName().equals(itemName)) {

                        itemList[i].printRatings();
                        check = false;
                        break;
                    }
                }
                if (check) {
                    System.out.println("This restuarant is not in the list!");
                }
                break;

            case 5:
                // loop through the list to display each resturant in the array and the average
                // rating for each
                for (int i = 0; i < index; i++) {
                    System.out.println(itemList[i].getName() + " - " + itemList[i].getAverageRating());
                }
                break;

            case 6:
                // display the restuarant with the worst average rating
                if (index == 0) {
                    System.out.println("Sorry there are no restuarants to show");
                }
                worst_item = 0;
                if (index > 1) {
                    for (int i = 0; i < index - 1; i++) {
                        if (itemList[i].getAverageRating() < itemList[i + 1].getAverageRating()) {
                            worst_item = 1;
                        } else {
                            worst_item = i + 1;
                        }
                    }
                }
                System.out.println("The resturant with the worst average rating is " + itemList[worst_item].getName()
                        + itemList[worst_item].getAverageRating());
                break;

            case 7:
                // exit the application
                scanner.close();
                System.exit(0);
                break;
            }

        }

    }

}
