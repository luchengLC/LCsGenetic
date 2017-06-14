package model;

import utils.Operations;
import utils.RoundTool;

import static java.lang.Math.random;

/**
 * Created by LC on 2017/5/21.
 */
public class Population {
    int populationSize; // 种群规模
    double pc;  //杂交概率
    double pm;  //突变概率


    private Individual[] individuals;
    private double finessSum = 0;
    private Individual[] individualSon;  //存储一次迭代交叉后的子代
    private Individual[] individualMutation;  //存储一次迭代变异后的个体

    private Individual[] individualsTmp;  //用于过渡处理的

    public Population(int populationSize, double pc, double pm) {
        this.populationSize = populationSize;
        this.pc = pc;
        this.pm = pm;
        individuals = new Individual[populationSize];
        for (int i = 0; i<individuals.length; i++){
            individuals[i] = new Individual();
        }
    }

    //种群初始化
    public void init(){
        for (int i = 0; i < populationSize; i++){
            individuals[i].init();
        }
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    //种群计算各个个体的适应值
    public void calFiness(){
        for (int i = 0; i<individuals.length; i++){
            individuals[i].calFiness();
        }
    }

    //有 参数，为了individualsTmp而设
    public void calFiness(Individual[] individualsTmp){
        for (int i = 0; i<individualsTmp.length; i++){
            individualsTmp[i].calFiness();
        }
    }

    //计算种群总的适应值
    public void calFinessSum(){
        finessSum = 0;
        for (int i = 0; i<individuals.length; i++){
            finessSum += individuals[i].getFitness();
        }
    }


    //计算种群各个个体的相对适应值 & 繁殖数量N          ——已存在各个个体的属性N中
    public void calRelativeFiness(){
        calFinessSum();
        for (int i = 0; i<individuals.length; i++){
            individuals[i].calRelativeFiness(finessSum);
        }
    }


    //杂交
    public void cross(){
        //每次杂交都 重新new 一个子代
        //避免 空指针异常
        individualSon = new Individual[populationSize*populationSize];
        for (int i = 0; i<individualSon.length; i++){
            individualSon[i] = new Individual();
        }
        int k = 0;
        for (int i= 0; i<individuals.length; i++){
            for (int j = 0; j<individuals.length; j++){
                double b = random();
                if (b < pc && individuals[i].getN()!=0 && individuals[j].getN()!=0){  //如果两两配对，随机概率大于pc就进行杂家

                    //杂交染色体 s1
                    int pointIndex_1 = RoundTool.roundInt((random()*(individuals[i].getS1().length-1)));

                    for (int a = 0; a<pointIndex_1+1; a++){
                        individualSon[k].getS1()[a] = individuals[i].getS1()[a];
                    }

                    for (int a = pointIndex_1+1; a<individuals[j].getS1().length; a++){
                        individualSon[k].getS1()[a] = individuals[j].getS1()[a];
                    }

                    //杂交染色体 s2
                    int pointIndex_2 = RoundTool.roundInt((random()*(individuals[i].getS2().length-1)));
                    for (int a = 0; a<pointIndex_2+1; a++){
                        individualSon[k].getS2()[a] = individuals[i].getS2()[a];
                    }
                    for (int a = pointIndex_2+1; a<individuals[j].getS2().length; a++){
                        individualSon[k].getS2()[a] = individuals[j].getS2()[a];
                    }

                    k++;
                }

            }
        }
    }

    //变异
    public void mutation(){

        individualMutation = new Individual[populationSize];
        //避免 空指针异常
        for (int i = 0; i<individualMutation.length; i++){
            individualMutation[i] = new Individual();
        }

        int k = 0;
        for (int i = 0; i<individuals.length; i++){
            if (random()<pm) {
                int pointIndex_m_1 = RoundTool.roundInt((random()*(individuals[i].getS1().length-1)));
                individualMutation[k].setS1(individuals[i].getS1());
                individualMutation[k].setS(individualMutation[k].getS1(),pointIndex_m_1);

                int pointIndex_m_2 = RoundTool.roundInt((random()*(individuals[i].getS1().length-1)));
                individualMutation[k].setS1(individuals[i].getS2());
                individualMutation[k].setS(individualMutation[k].getS2(),pointIndex_m_2);

                k++;
            }

        }

    }

    public void chooseBest(){ //计算适应值 并同时 选择“优秀”

        //全部copy到individualsTmp，方便处理
        individualsTmp = new Individual[populationSize*populationSize+2*populationSize];
        //避免 空指针异常
        for (int i = 0; i<individualsTmp.length; i++){
            individualsTmp[i] = new Individual();
        }

        for (int i = 0; i<individuals.length; i++){
            individualsTmp[i] = individuals[i];
        }
        for (int i = 0; i<individualSon.length; i++) {
            individualsTmp[i + individuals.length] = individualSon[i];
        }

        //这段得到的很可能是0，因为变异率低
        for (int i = 0; i<individualMutation.length; i++){
            individualsTmp[i+individuals.length+individualSon.length] = individualMutation[i];
        }

        //计算fitness
        calFiness(individualsTmp);



        //排序
        individualsTmp = Operations.BubbleSort(individualsTmp);


        //把前individuals.length个传给individuals，以更新
        for (int i = 0; i<individuals.length; i++){
            individuals[i] = individualsTmp[i];
        }
    }



}
