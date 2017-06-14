package utils;

import java.math.BigDecimal;

/**
 * 用于控制计算过程的精度
 *
 * Created by LC on 2017/5/21.
 */
public class RoundTool {

    public static double roundDouble(double value){
        int scale = 7;  //精确到小数点之后7位
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, 5);  //5:  "ROUND_HALF_DOWN"
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    public static int roundInt(double value){
        int result;

        int scale = 0;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale,5);
        double d = bd.doubleValue();
        result = (int) d;
        return result;
    }

    public static int roundIntCross(double value){
        int result;

        int scale = 0;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale,3);
        double d = bd.doubleValue();
        result = (int) d;
        return result;
    }

}
