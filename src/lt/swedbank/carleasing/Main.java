package lt.swedbank.carleasing;

public class Main {

    //This field is only used from within this class. It could be (should be) private.
    static final double AGREEMENT_FEE = 150d;

    public static void main(String[] args) {

        //I know that for most people it is more convenient to declare local variables at the start of a method (been there, done that),
        // but this is not a Java way of writing your code. Please, try declaring your variables only when they're needed/first used.
        double carPrice;
        double downPaymentSize;
        double downPayment = 0d;
        double loanSize;

        try {
            carPrice = Double.parseDouble(args[0]);
        } catch (Exception e) {
            //
            //Please see how exception handling was described in task's constraints. Example:
            //System.out.println("Car price including VAT is not valid " + e.getMessage());
            //
            //Also, exception type is too broad. In general, you should only catch exceptions which really can happen (are expected).
            //
            System.out.println("Exception! Not a number: carPrice");
            return;
        }

        try {
            downPaymentSize = Integer.parseInt(args[1]);
        } catch (Exception e) {
            //See comments above ^^^
            System.out.println("Exception! Not a number: downPaymentSize");
            return;
        }

        if (isValueNegativeCarPrice(carPrice))
            System.out.println("car price: it cannot be negative");

        if (isValueZeroCarPrice(carPrice))
            System.out.println("car price: it cannot be zero");

        //You are calling "isValueNegativeCarPrice" and "isValueZeroCarPrice" multiple times, which is considered waste of resources.
        //You should save "isValueNegativeCarPrice" and "isValueZeroCarPrice" results into a variables and using those instead.
        if (!isValueNegativeCarPrice(carPrice) && !isValueZeroCarPrice(carPrice)) {
            if (!isDownPaymentValid(downPayment)) {
                System.out.println("Leasing parameters are valid");

                //System.out.println(String.valueOf(args[0]) + " " + String.valueOf(args[1]));

                downPayment = calculateDownPayment(carPrice, downPaymentSize);
                loanSize = calculateLoanSize(carPrice, downPayment);
                System.out.println("Car price including VAT: " + carPrice + " EUR");
                System.out.println("Down payment size: " + args[1] + "%");
                //Em, what is this - "                                  "? :)
                //If you need an empty line, just print nothing (put literally nothing into "println"), or add "\n" to
                // the end of previous line, or at a start of a next line.
                System.out.println("                                  ");
                System.out.println("Down payment: " + downPayment + " EUR");
                System.out.println("Loan soze: " + loanSize + " EUR");
                System.out.println("Agreement fee: " + AGREEMENT_FEE + " EUR");
                System.out.println("Initial installment: " + (downPayment + AGREEMENT_FEE) + " EUR");

            } else {
                //I believe "100" ("...size 100 is...") should not be hardcoded :)
                System.out.println("Down payment size 100 is invalid: it must be between 0 and 100 " +
                        "Leasing parameters are invalid");
            }
        }

        //"Terminating the program" message is missing.
    }

    //This method is only used by this class. It should be private.
    public static double calculateDownPayment(double carPrice, double downPaymentSize) {

        return (carPrice * downPaymentSize) / 100;
    }

    //This method is only used by this class. It should be private.
    public static double calculateLoanSize(double CarPrice, double downPayment) {

        //"CarPrice" is not a valid name for a variable in Java
        return CarPrice - downPayment;
    }

    //This method is only used by this class. It should be private.
    public static boolean isValueNegativeCarPrice(double carPrice) {

        return (carPrice < 0);

    }

    //This method is only used by this class. It should be private.
    public static boolean isValueZeroCarPrice(double carPrice) {

        return (carPrice == 0);

    }

    //This method is only used by this class. It should be private.
    public static boolean isDownPaymentValid(double downPayment) {

        return (downPayment > 0 && downPayment < 100);

    }

}