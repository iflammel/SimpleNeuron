package web;

public class MainWeb {
    public int [][] mul;        //отмаштабированные сигналы
    public int weight [][] = {{1, 2, 1}, {1, 0, -4}, {1, 2, 1}, {-4, 0, 1}, {1, 1, 0}};    //веса для обученого нейрона. обучен на цифру 5
    //public int [][] weight; //массив для хранения весов
    public int [][] input;      //входная информация
    public  int limit = 9;      //порог

    public int sum;             //сумма отмаштабированных сигналов

    public  MainWeb(int sizey, int sizex, int[][]inP){

        //weight = new int[sizex][sizey];
        mul = new int[sizex][sizey];
        input = new int[sizex][sizey];

        /*for (int x = 0; x < 5; x++){
            for(int y = 0; y < 3; y++){
                input[x][y] = inP[x][y];
            }
        } */

        input = inP.clone();
    }

    public void mul_w(int[][] inP){
        for(int x = 0; x < 5; x++){
            for (int y = 0; y < 3; y++){
                mul[x][y] = inP[x][y]*weight[x][y];
            }
        }
    }

    public void sum(){
        sum = 0;
        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 3; y++){
                sum += mul[x][y];
            }
        }
    }

    public boolean rez(){
        if (sum >= limit)
            return true;
        else{
            return false;
        }

    }

    public void incW(int[][] inP){
        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 3; y++){
                weight[x][y] += inP[x][y];
            }
        }
    }

    public  void decW(int [][] inP){
        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 3; y++){
                weight[x][y] -= inP[x][y];
            }
        }
    }


}
