package Graph;

import java.util.Scanner;

public class Control {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[][] graph1 = {{0,3,4,2,1000,1000,1000,1000,1000,1000},
                {1000,0,1000,1000,3,1000,1000,1000,1000,1000},
                {1000,1000,0,1000,6,1000,1000,1000,1000,1000},
                {1000,1000,1000,0,3,1,1000,1000,1000,1000},
                {1000,1000,1000,1000,0,1,8,1000,7,1000},
                {1000,1000,1000,1000,1000,0,6,12,1000,1000},
                {1000,1000,1000,1000,1000,1000,0,1000,1000,14},
                {1000,1000,1000,1000,1000,1000,1000,0,6,11},
                {1000,1000,1000,1000,1000,1000,1000,1000,0,3},
                {1000,1000,1000,1000,1000,1000,1000,1000,1000,0}};
        int[][] graph = {{0,2,6,8,10000,10000,3,10000,10000},
                {2,0,9,3,10000,4,10000,10000,10000},                       // создание матрицы смежности, для просмотра направления ребер и веса вершин
                {6,9,0,7,10000,10000,10000,10000,10000},
                {8,3,7,0,5,10000,10000,10000,10000},                            // можно ввести через клавиатуру, но слишком долго и возможна ошибка ввода
                {10000,10000,10000,10000,0,10000,8,9,10000},
                {10000,4,10000,5,10000,0,10000,6,4},
                {3,9,10000,10000,8,10000,0,10000,10000},
                {10000,10000,10000,10000,9,10000,10000,0,1},
                {10000,10000,10000,10000,10000,4,10000,1,0}};
        String[][] sgr = {{"0","2","6","8","█","█","3","█","█"},{"2","0","9","3","█","4","█","█","█"},                  // такая же матрица смежности, но для вывода в консоль
                {"6","9","0","7","█","█","█","█","█"},{"8","3","7","0","5","█","█","█","█"},                            // █ - означает, что такого пути нет
                {"█","█","█","█","0","█","8","9","█"},{"█","4","█","5","█","0","█","6","4"},
                {"3","9","█","█","8","█","0","█","█"},{"█","█","█","█","9","█","█","0","1"},
                {"█","█","█","█","█","4","█","1","0"}};

        char[] sym = {'1','2','3','4','5','6','7','8','9'};                                                             // массив, соответствующий вершинам графа, для его вывода в консоль
        int begin = 0, end = 0;                                                                                         // переменные для записи вершин, от begin строится путь к end
        System.out.println("╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗");                            // часть интерфейса вывода графа
        System.out.println("║     ║  1  ║  2  ║  3  ║  4  ║  5  ║  6  ║  7  ║  8  ║  9  ║");
        for(int i=0;i< sgr.length;i++){
            System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
            System.out.print("║  " + sym[i] + "  ");
            for(int j = 0;j<sgr[0].length;j++){
                if(len(sgr[i][j])==1){
                    System.out.print("║  "+sgr[i][j]+"  ");
                }else{
                    System.out.print("║ "+sgr[i][j]+"  ");
                }
            }
            System.out.print("║\n");

        }
        System.out.println("╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝");
        System.out.println("Введите начальную вершину: ");
        begin = s.nextInt();                                                                                            // считывание начальной вершины от которой будет строится путь
        begin-=1;                                                                                                       // -1 необходимо так как вершины 1-9, а алгоритм строится на массивах и для индексации необходим элемент на 1 меньше заданного
        System.out.println("Введите конечную вершину: ");
        end = s.nextInt();
        end-=1;                                                                                                         // аналогично как и с begin
        long startTime = System.currentTimeMillis();
        System.out.println(Algorithm.len(begin,end,graph));
        long endTime = System.currentTimeMillis();
        System.out.println("Время, потраченное на расчёт пути в графе с" + graph.length + " вершинами = " + (endTime - startTime) + " миллисекунд");
        startTime = System.currentTimeMillis();
        System.out.println(Algorithm.len(begin,end,graph1));
        endTime = System.currentTimeMillis();
        System.out.println("Время, потраченное на расчёт пути в графе с" + graph1.length + " вершинами = " + (endTime - startTime) + " миллисекунд"); // можем сделать вывод, что алгоритм считает путь очень быстро, но также на скорость влияет направленность рёбер графа
    }

    public static int len(String n) {
        String s = n;
        if (s.length() > 1) {
            return 2;
        }
        return 1;
    }
}