package Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {
    public static String len(int begin, int end,int[][] g){
        int temp = 0;
        if(end<begin){                                                                                                  // так как пользователь может искать путь от большей к меньше вершине, могут возникнуть
            temp = end;                                                                                                 // проблемы при построении пути, для этого необходимо поменять их местами
            end = begin;
            begin = temp;
        }
        int[][] matr = new int[g.length][g.length];                                                                     // построение матрицы весов определенных вершин, в которые мы приходим из предыдущей вершины
        String path_ = "";                                                                                              // строка в которую будет записываться путь из матрицы
        String itog = "";                                                                                               // итоговый вариант пути, который будет предоставлен пользователю
        int end1 = end;                                                                                                 // сохранение в переменную, которая будет изменяться в цикле восстановления пути
        ArrayList<Integer> here = new ArrayList<>();                                                                    // список вершин, которые мы посетили (для чего и как можно изучить из самого алгоритма Дейкстры)
        int[] weight = new int[g.length];                                                                               // массив весов вершин, которые посетил алгоритм проходя от begin вершины к любрй другой вершине
        int[] path = new int[g.length];                                                                                 // массив в котором хранится путь, в ячейке хранится индекс этого же массива из которого мы пришли в эту ячейку
        int[][] p = new int[g.length][g.length];                                                                        // так как на каждом шаге вершина переходит в определенные вершину, матрица в каждой своей строке хранит в какие мы вершины переходим записывая на это место номер вершины из которой мы пришли
        here.add(begin);                                                                                                // записываем в список посещенных вершин нашу вершину, из которой будет строится путь
        int pred = 0;                                                                                                   // так как в алгоритме необходим вес пршлой вершины для построения пути, а первая вершина у нас 0, поэтому присваиваем значение 0
        weight[begin] = 0;                                                                                              // записываем на место нашей вершины её вес
        for(int i=0;i < matr.length;i++){                                                                               // заполняем 1 строку матрицы 0 и 1000, для того, чтобы на последующих итерациях могли сравнивать значения и находить минимальное
            if(i == begin){
                matr[0][i] = 0;
            }else{
                matr[0][i] = 10000;
            }
            p[0][i]=-1;
        }
        int min;                                                                                                        // переменная для нахождения минимального элемента в строке (смотрите алгоритм Дейкстры)
        int index=0;                                                                                                    // индекс минимальной вершины, чтобы записать ей в список посещенных и массив весов
        for(int i=1;i<g.length;i++){
            for(int j=0;j<g.length;j++){
                p[i][j]=p[i-1][j];                                                                                      // копируем предыдущую строку матрицы путь на новую, для того чтоб не терять значения прошлых вершин
                if(availability(j,here)==true){                                                                         // проверяет были ли мы в данной вершине и чтоб больше к ней не возвращаться присваивается значение 1000 (бесконечность)
                    matr[i][j] = 10000;
                }else{
                    if(min(matr[i-1][j],pred+g[here.get(i-1)][j])==true){                                            // нахождение минимального пути сравниваем прошлого значения и значения (вес вершины в которой мы сейчас + вес вершины в которуею переёдем)
                        matr[i][j] = matr[i-1][j];                                                                      // записываем новое, минимальное значение
                        p[i][j] = p[i-1][j];                                                                            // если новый новый путь меньше, то записываем в матрицу пути откуда мы пришли в эту вершину
                    }else{
                        matr[i][j] = pred+g[here.get(i-1)][j];                                                          // записываем предудущее значение
                        p[i][j]=here.get(i-1);                                                                          //
                    }
                }
            }
            min = matr[i][0];
            index = 0;
            for(int c=0;c< matr[i].length;c++){                                                                         // цикл для нахождения минимального значения в строке
                if(matr[i][c]<min){
                    min = matr[i][c];
                    index = c;
                }
            }
            pred = min;                                                                                                 // записываем значение минимального в переменную, для рассмотрения на следующей итерации
            weight[index] = pred;                                                                                       // записываем вес данной вершины
            here.add(index);                                                                                            // обозначаем, что мы уже здесь были и сюда больше цикл не зайдет
        }
        /*for(int i=0;i< matr.length;i++){                                                                              // если необходимоо можно вывести минимальный путь от begin к каждой вершине
            for(int j = 0;j<matr[i].length;j++){
                if(String.valueOf(matr[i][j]).length()==1){
                    System.out.print("    "+matr[i][j]+" ");
                }else if(String.valueOf(matr[i][j]).length()==2){
                    System.out.print("   "+matr[i][j]+" ");
                }else{
                    System.out.print(matr[i][j]+" ");
                }
            }
            System.out.print("\n");
        }*/
        /*for(int i=0;i<p.length;i++){                                                                                  // матрица для отображения пути, как двигались на каждой итерации
            for(int j=0;j<p.length;j++){
                if(String.valueOf(p[i][j]).length()==1){
                    System.out.print(" "+p[i][j]+" ");
                }else if(String.valueOf(p[i][j]).length()==2){
                    System.out.print(p[i][j]+" ");
                }
            }
            System.out.println();
        }*/
        System.out.println();
        for(int i=0;i<path.length;i++){
            path[i] = p[g.length-1][i]+1;                                                                               // последняя строка матрицы пути хранит в себе итоговые значения откуда пришли в заданную ячейку
        }
        path[begin]=0;                                                                                                  // так как begin была начальной вершиной то она равна 0
        while (end1!=begin){                                                                                            // чтоб найти путь к end, необходимо двигаться end->begin
            end1 = path[end1]-1;                                                                                        // извлекаем индекс массива из которого пришли в end и так до тех пор, пока индекс массива не совпадет с begin
            path_+= (end1+1)+"";
        }
        for(int i=path_.length()-1;i>=0;i--){                                                                           // из за того что шли end->begin, а вывод должен быть begin->end, необходимо записывать с конца строки
            itog+=path_.charAt(i)+"->";
        }
        itog+=(end+1)+":"+weight[end];                                                                                  // вывод пути и размера этого пути
        return itog;
    }

    public static boolean availability(int n, ArrayList<Integer> arr){
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)==n){
                return true;
            }
        }
        return false;
    }

    public static boolean min(int x,int y){
        if(x<y)
            return true;
        return false;
    }
}
