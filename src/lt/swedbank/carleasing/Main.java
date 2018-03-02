package lt.swedbank.carleasing;

public class Main {


    static final double AGREEMENT_FEE = 150d;

    public static void main(String[] args) {

        double carPrice;
        double downPaymentSize;
        double downPayment = 0d;
        double loanSize = 0d;
        boolean isHasZero = true;
        boolean isHasNegative = true;
        boolean isHasTruePaymentDownValid = true;

        try {
            carPrice = Double.parseDouble(args[0]);
        } catch (Exception e) {
            System.out.println("Exception! Not a number: carPrice" );
            return;
        }

        try {
            downPaymentSize = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("Exception! Not a number: downPaymentSize" );
            return;
        }

        if (!isValueNegativeCarPrice(carPrice, isHasZero))
            System.out.println("car price: it cannot be negative");

        if (!isValueZeroCarPrice(carPrice, isHasNegative))
            System.out.println("car price: it cannot be zero");

        if (isHasNegative && isHasNegative) {
            if (isDownPaymentValid(downPayment, isHasTruePaymentDownValid)) {
                System.out.println("Leasing parameters are valid");

                //System.out.println(String.valueOf(args[0]) + " " + String.valueOf(args[1]));

                downPayment = calculateDownPayment(carPrice, downPaymentSize);
                loanSize = calculateLoanSize(carPrice, downPayment);
                System.out.println("Car price including VAT: " + carPrice + " EUR");
                System.out.println("Down payment size: " + args[1] + "%");
                System.out.println("                                  ");
                System.out.println("Down payment: " + downPayment + " EUR");
                System.out.println("Loan soze: " + loanSize + " EUR");
                System.out.println("Agreement fee: " + AGREEMENT_FEE + " EUR");
                System.out.println("Initial installment: " + (downPayment + AGREEMENT_FEE) + " EUR");

            } else {
                System.out.println("Down payment size 100 is invalid: it must be between 0 and 100" +
                        "Leasing parameters are invalid");
            }
        }
    }

    public static double calculateDownPayment(double carPrice, double downPaymentSize) {

        return (carPrice * downPaymentSize) / 100;
    }

    public static double calculateLoanSize(double CarPrice, double downPayment) {

        return CarPrice - downPayment;
    }

    public static boolean isValueNegativeCarPrice(double carPrice, boolean isHas) {

        if (carPrice < 0)
            isHas = false;
        return isHas;
    }

    public static boolean isValueZeroCarPrice(double carPrice, boolean isHas) {

        if (carPrice == 0)
            isHas = false;
        return isHas;
    }

    public static boolean isDownPaymentValid(double downPayment, boolean isHas) {

        if (downPayment > 0 && downPayment < 100)
            isHas = false;
        return isHas;
    }

}