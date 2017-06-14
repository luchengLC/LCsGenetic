package utils;

import static java.lang.StrictMath.sin;

/**
 * Created by LC on 2017/5/21.
 */
public class Function {
    public static double getFunctionValue(double x1, double x2){
        double f = 0;
        f= (3- sin((2*x1))*sin((2*x1)) - sin((2*x2))*sin((2*x2)));
        f = RoundTool.roundDouble(f);
        return f;
    }
}
