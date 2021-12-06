package juegoCraps;

import java.util.Random;
/**
 * Class Dado generate a random value between 1 and 6
 * @author Alexander Valencia
 * @version 1.0.0
* */
public class Dado {
    private int cara;

    /**
     * Method that generates a random value to cara
     * @return number between (1,6)
     */
    public int getCara() {
        Random aleatorio= new Random();
        cara=aleatorio.nextInt(6)+1;
        return cara;
    }
}
