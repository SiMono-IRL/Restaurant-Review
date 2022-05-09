/* Item Class - Ratings Application written by Simon Monaghan April 2021 */

public class Item {

    // declare instance variables
    private static int MAX_NUM_RATINGS = 5; // will store the max num of restuarants that can be rating as a constant.
    private String name;
    private int[] rating;
    private int noOfRatings = 0;

    //constructor
    public Item(String name) {

        this.name = name; // initialize the instance variable name, that is this.name, with the value of the parameter name
        this.rating = new int[MAX_NUM_RATINGS];
    }

    // getter delcared to return the restuarant name
    public String getName() {
        return this.name;
    }

    // add a restuarant rating
    public void addRating(int rating) {

        // validation requirement
        if (noOfRatings >= MAX_NUM_RATINGS) {
            System.out.println("Sorry, the maximum number of ratings has been reached. You cannot add another rating!");
            return;
        }
        this.rating[noOfRatings] = rating;
        this.noOfRatings++;
    }

    // calcuate average rating 
    public double getAverageRating() {
        int total = 0;
        for (int i = 0; i < this.noOfRatings; i++) {
            // calculate running sum 
            total = total + rating[i]; //
        }
        return (double) total / noOfRatings;
    }

    // print average rating
    public void printRatings() {

        // validation requirement
        if (noOfRatings == 0) {
            System.out.println("There is no rating for this restuarant");
            return;
        }

        System.out.println("Rating list of " + this.name + ":");

        for (int i = 0; i < this.noOfRatings; i++) {
            System.out.print(" " + rating[i]);

            if (i != this.noOfRatings - 1) {
                System.out.print(",");
            }
        }
        System.out.println("");
    }

}
