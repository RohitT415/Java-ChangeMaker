import java.util.Scanner;

/**
 * Simulates a cashier processing a line of customers and their transactions.
 * Each transaction involves a purchase price, cash payment, and change distribution.
 * 
 * @author  Rohit Tallapragada
 * @version  Sep 16 2020
 */
public class ChangeMaker
{
    //instance variables
    int purchasePrice;
    int tenderedPrice;
    Scanner in = new Scanner (System.in);
    double userResponse = 0.0;

    /**
     * Constructs ChangeMaker object.
     * 
     * @return
     */
    public ChangeMaker()
    {

    }

    /**
     * Prompts the customer for the purchase price and returns it in cents.
     * 
     * @return   amount of the purchase in cents (as an integer)
     */
    public void getPurchasePrice( )
    {   
        System.out.print("Purchase Price: $");
        userResponse = in.nextDouble();
        purchasePrice = (int)(userResponse * 100 + 0.5);
    }

    /**
     * Prompts the customer for the amount of cash tendered.
     * 
     * If the customer does not give enough cash to cover the expenses, give the customer
     * two options: the customer can add for more money until there is enough or the
     * customer may terminate the program.
     *
     * @param     purchasePrice
     * @return    the amount of cash in cents
     */
    public void getCashTendered()
    {
        System.out.print("Tendered Price: $");

        userResponse = in.nextDouble();
        tenderedPrice = (int)(userResponse * 100 + 0.5);

        while (tenderedPrice < purchasePrice)
        {   
            System.out.print("Please add more or type '0' to exit the program: ");
            userResponse = in.nextDouble();
            if (userResponse != 0)
            {
                tenderedPrice = tenderedPrice + (int)(userResponse * 100 + 0.5);
            }
            else
            {
                System.exit(0);
            }
        }

    }

    /**
     * Calculates and prints out change 
     * (the number of dollars, quarters, dimes, nickels, and pennies) 
     * the customer is due in the standard $00.00 format.
     * 
     * @param       purchasePrice and tenderedPrice
     */
    public void calculateChange()
    {
        int change; //In cents
        int numberOfDollars; 
        int numberOfQuarters;
        int numberOfDimes;
        int numberOfNickels;
        int numberOfPennies;

        change = tenderedPrice - purchasePrice;
        numberOfDollars = change / 100;
        numberOfQuarters = (change % 100) / 25;
        numberOfDimes = ((change % 100) % 25) / 10;
        numberOfNickels = (((change % 100) % 25) % 10) / 5;
        numberOfPennies = (((change % 100) % 25) % 10) % 5;

        System.out.println("You are due: $" + (((double)change) / 100) + ".");
        if ((((double)change) / 100) > 0)
        {
            System.out.println("That is: ");
        }
        if (numberOfDollars > 0)
        {
            System.out.println(numberOfDollars + " dollars");
        }
        if (numberOfQuarters > 0)
        {
            System.out.println(numberOfQuarters + " quarters");
        }   
        if (numberOfDimes > 0)
        {
            System.out.println(numberOfDimes + " dimes");
        }
        if (numberOfNickels > 0)
        {
            System.out.println(numberOfNickels + " nickels");
        }
        if (numberOfPennies > 0)
        {
            System.out.println(numberOfPennies + " pennies");
        }
    }

    /**
     * Handles one customer’s transaction.
     */
    public void handleTransaction()
    {
        getPurchasePrice();
        getCashTendered();
        calculateChange();
    }

    /**
     * Oversees the transactions of all customers.
     */
    public void overseeTransaction()
    {
        handleTransaction();
        System.out.println("To terminate the program, type '0'." );
        System.out.print("Enter any random number to continue using the program.");
        userResponse = in.nextDouble();
        while(userResponse != 0)
        {
            System.out.println();
            handleTransaction();
            System.out.println("To terminate the program, type '0'.");
            System.out.print("Enter any random number to continue using the program.");
            userResponse = in.nextDouble();
        }
        System.exit(0);
    }

    /** 
     * Simulates a transaction.
     * 
     * @param   args  information from the command line
     */
    public static void main(String [ ] args)
    {
        ChangeMaker bob = new ChangeMaker();
        bob.overseeTransaction();
    }

}
