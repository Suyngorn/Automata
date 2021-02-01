package task;

import model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintFA {

    public static void print(String a) {
        System.out.print(a);
    }

    public static void println(String a) {
        System.out.println(a);
    }

    public static void printState(Model model){
        String row1 = "";

        String br = "\n";
        String t = "\t";
        String row = "State| ";

        for (String s:model.getSymbols()){
            row = row +t+s+t+"|";
        }

        for (int v:model.getState()){
            if (v==model.getStart()){
                row1 = "->q"+v+" |";
            }else {
                boolean a = true;
                for (int z:model.getEnd()){
                    if (v==z){
                        row1="*q"+v+t+" |";
                        a = false;
                    }
                }
                if (a){
                    row1 = "q"+v+t+" |";
                }
            }
            for (String s:model.getSymbols()){
                String row2 = "";
                List<Integer> state = new ArrayList<>();
                state = model.getTransition().get(v).get(s.charAt(0));
                int b = 1;
                for (Integer i:state){
                    if (i==505){
                        row1 = row1+"  {Ø} |";
                    } else if (state.size()>1){
                        if (b==1){
                            b++;
                            if (i==404){
                                row2 = row2+"{ε";
                            }else {
                                row2 = row2+"{q"+i;
                            }
                        }else if (b==state.size()){
                            if (i==404){
                                row2 = row2+",ε}|";
                            }else {
                                row2 = row2+",q"+i+"}|";
                            }
                        }else {
                            b++;
                            if (i==404){
                                row2 = row2+",ε"+i;
                            }else{
                                row2 = row2+",q"+i;
                            }
                        }
                    }else {
                        row1 = row1+" {q"+i+"} "+"|";
                    }
                }
                row1 = row1 +row2;
            }
            row = row+br+row1;

        }

        println("\n==========**********\n*****=====");
        println(row);
        if (!model.isDFA()){
            println("\nThis FA is NFA");
        }else {
            println("\nThis FA is DFA");
        }
        println("**********\n=====");

    }

}
