package tests.stack;
import java.util.*;

public class stack{
    public static void main(String[] args){
        Vector<Integer> v = new Vector<>();

        v.add(10);
        v.add(20);
        v.add(30);
        v.add(40);
        v.remove(v.size() - 1);

        for(int i : v){
            System.out.println(i);
        }
    }
}