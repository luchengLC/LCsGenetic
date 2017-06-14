package model;

import utils.Operations;
import utils.RoundTool;

/**
 * Created by LC on 2017/5/21.
 */
public class Individual {
    private int[] s1 = new int[23];   //二进制的x1的基因型
    private int[] s2 = new int[23];   //二进制的x2的基因型

    private double fitness_S1 = 0; //适应度
    private double fitness_S2 = 0; //适应度
    private double fitness = 0;   //个体适应值
    private double relativeFitness = 0;   //个体相对适应值
    private int N = 0;  //个体繁殖数量



    public void init(){
        for (int i = 0; i<23; i++){
            s1[i] = Operations.randomS();
            s2[i] = Operations.randomS();

            N = 0; //初始化 繁殖数量
        }

        System.out.print("\ns1 = ");
        for (int i = 0; i<23; i++){
            System.out.print(s1[i]);
        }

        System.out.print("\ns2 = ");
        for (int i = 0; i<23; i++){
            System.out.print(s2[i]);
        }
    }

    public void calFiness(){
        fitness_S1 = Operations.calFinessActual(s1);
        fitness_S2 = Operations.calFinessActual(s2);
        //System.out.print("fi_1:"+fitness_S1+"  fi_2:"+fitness_S2+"\n"); !!!!!!!!!!!!!!!!不是第一代的代有错
        fitness = Operations.calFiness(fitness_S1, fitness_S2);
    }

    public double getFitness() {
        return fitness;
    }

    public void calRelativeFiness(double fitnessSum) {
        relativeFitness = fitness/fitnessSum;
        relativeFitness = RoundTool.roundDouble(relativeFitness);
        N = RoundTool.roundIntCross((relativeFitness*10));
    }

    public int getN() {
        return N;
    }

    public int[] getS1() {
        return s1;
    }

    public int[] getS2() {
        return s2;
    }

    public void setS1(int[] s1) {
        this.s1 = s1;
    }


    public double getRelativeFitness() {
        return relativeFitness;
    }

    //变异时 调用的 改某个 0 -1 的方法
    public void setS(int[] s, int p) {
        switch (s[p]){
            case 0:
                s[p] = 1;
                break;
            case 1:
                s[p] = 0;
                break;
        }
    }
}
