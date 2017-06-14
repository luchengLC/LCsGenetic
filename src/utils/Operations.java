package utils;

import model.Individual;

import static java.lang.StrictMath.random;

/**
 * 封装那些老是重复的底层计算
 *
 * Created by LC on 2017/5/21.
 */
public class Operations {

    /**
     * 随机初始化个体基因
     * @return
     */
    public static int randomS(){
        int result = 0;
        double a = random();
        if (a<0.5){
            result=0;
        }
        else result=1;

        return result;
    }

    /**
     * 计算一个适应度
     * @param s
     * @return
     */
    public static double calFinessActual(int[] s){
        Long sum = Long.valueOf(0);
        double result = 0;
        for (int i = 0; i<s.length; i++){
            if (s[i] == 1){
                sum += multiplicative_2((s.length-i-1));
            }
        }

        result = (6*(double)sum/(double) ((Operations.multiplicative_2(23)-1)));
        result = RoundTool.roundDouble(result);
        return result;
    }

    /**
     * 求2的i次累乘
     * @param i
     * @return
     */
    public static Long multiplicative_2(int i){
        Long result = Long.valueOf(1);
        while (i>0){
            result = 2*result ;
            i--;
        }
        return result;
    }

    /**
     * 计算个体适应值
     * @param finessRate_1
     * @param finessRate_2
     * @return
     */
    public static double calFiness(double finessRate_1, double finessRate_2){
        double result = 0;
        result = Function.getFunctionValue(finessRate_1, finessRate_2);
        result = RoundTool.roundDouble(result);
        return result;
    }

    public static Individual[] BubbleSort(Individual[] individuals){
        Individual tmp;
        for (int i = 0; i<individuals.length-1; i++){
            for (int j = 0; j<individuals.length-i-1; j++){
                if ((individuals[j+1].getFitness() < individuals[j].getFitness()) && (individuals[j+1].getFitness()!=0)){
                    tmp = individuals[j];
                    individuals[j] = individuals[j+1];
                    individuals[j+1] = tmp;
                }
            }
        }

        return individuals;
    }
}
