package atm;
import java.util.Random;

/**
 * Created by vedantshah on 2019-03-27.
 */
public class StockPortfolio {
    static double price = 5;
    static double fee = 0.1;

    /**
     * This method returns the price of the stock
     * @return price of stock
     */

    public static double getPrice() {
        return price;
    }

    /**
     * This method returns the fees on the stock
     * @return fees on the stock
     */

    public static double getFee() {
        return fee;
    }

    /**
     * This method is used to update the stock prices.
     */

    public static void update_price(){
        Random r = new Random();
        double move_d = r.nextInt(3) - 0.814;
        double move = (r.nextDouble() * move_d)/100.0;
        //System.out.println(move);
        price = (1 + move)*price;
        price = Math.round(price*100) / 100.0;
    }

}
