package by.it.mialeshka.jd01_12;

import by.it._akhmelev_.jd01_04.TaskA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskA1 {
    private List<Integer> list = new ArrayList<Integer>(){};

    private void clearBad(List<Integer> grades){
        Iterator<Integer> iterator = grades.iterator();
        while(iterator.hasNext()){
            int grade = iterator.next();
            if(grade < 4)
                iterator.remove();
        }
    }

    public static void main(String[] args) {
        TaskA1 task = new TaskA1();
        for (int i = 0; i < 25; i++) {
            task.list.add((int)Math.ceil(Math.random()*9));
        }
        System.out.println(task.list);
        task.clearBad(task.list);
        System.out.println(task.list);
    }
}
