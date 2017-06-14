import model.Population;

/**
 * Created by LC on 2017/5/21.
 */
public class Main {
    public static void main(String[] args){
        int populationSize = 8; // 种群规模
        double pc = 0.7;  //杂交概率
        double pm = 0.02;  //突变概率
        final int generationP = 20; //设置迭代总次数为1000，作为结束迭代的条件
        int count = 0;  //计数器

        //这里可以写与用户交互的，让用户自定义种群规模、杂交概率、突变概率和迭代次数
        //
        //

        Population p = new Population(populationSize, pc, pm);  //构建一个种群
        p.init();     //种群初始化（内部将初始化各个个体的基因）
        p.calFiness();     //计算种群中每个个体的适应值
        p.calRelativeFiness(); //计算种群中每个个体的相对适应值 以及 繁殖数量（使用‘繁殖池选择’）

        System.out.print("\n初代:   ");
        for (int i = 0; i<p.getIndividuals().length; i++){
            System.out.print("个体"+ i +":"+p.getIndividuals()[i].getFitness()+"  ");
        }
        System.out.print("\n");

        while (count<generationP){
            p.cross();     //杂交
            p.mutation();  //变异
            p.chooseBest();  //计算适应值 并同时 选择“优秀”

            //计算优秀的个体的各种...
            p.calFiness();
            p.calRelativeFiness();

            count++;
            System.out.print("\n第"+count+"代:  ");
            for (int i = 0; i<p.getIndividuals().length; i++){
                System.out.print("个体"+ i +": "+p.getIndividuals()[i].getFitness()+"  ");
            }
        }

        System.out.print("\n【最优秀】 \n");
        System.out.print(p.getIndividuals()[0].getFitness()+"  ");
        System.out.print("\n基因1 ：x1： ");
        for (int i = 0; i<p.getIndividuals()[0].getS1().length; i++){
            System.out.print(p.getIndividuals()[0].getS1()[i]);
        }
        System.out.print("\n基因2 ：x2： ");
        for (int i = 0; i<p.getIndividuals()[0].getS2().length; i++){
            System.out.print(p.getIndividuals()[0].getS2()[i]);
        }

    }
}
