package task;

import callback.AutomataCallback;
import model.Model;

import java.util.*;

public class CreateFA {
    private int amountState;
    private List<Integer> state = new ArrayList<>();
    private List<String> symbols = new ArrayList<>();
    private int start;
    private List<Integer> end = new ArrayList<>();
    Map<Integer, Map<Character, List<Integer>>> transition;
    private AutomataCallback callback;

    public CreateFA(AutomataCallback callback) {
        transition = new TreeMap<Integer, Map<Character,List<Integer>>>();
        this.callback = callback;
    }

    public void createFA() {
        Model model = new Model();
        //declare to user input the values;
        Scanner input = new Scanner(System.in);

        PrintFA.println("\n****Create");
        PrintFA.print("How many state in FA? : ");
        while (!input.hasNextInt()) {
            PrintFA.println("Incorrect! Please input type is Integer!");
            PrintFA.print("How many state in FA? : ");
            input.next();
        }
        amountState = input.nextInt();
        for (int i = 0; i < amountState; i++) {
            state.add(i);
        }


        PrintFA.print("How many symbol? : ");
        while (!input.hasNextInt()) {
            PrintFA.println("Incorrect! Please input type is Integer!");
            PrintFA.print("How many symbol? : ");
            input.next();
        }
        int iSymbol = input.nextInt();
        if (iSymbol < 2) {
            PrintFA.print("What is it? : ");
            symbols.add(input.next());
        } else {
            PrintFA.println("What are they? :");
            for (int j = 0; j < iSymbol; j++) {
                String sy = input.next();
                if (sy.equals("404")){
                    symbols.add("ε");
                    model.setDFA(false);
                }else {
                    symbols.add(sy);

                }
            }
        }
        boolean a = true;
        do {
            PrintFA.print("What is the start state? : q");
            while (!input.hasNextInt()) {
                PrintFA.println("Incorrect! Please input type is Integer!");
                PrintFA.print("What is the start state? : q");
                input.next();
            }
            int start1 = input.nextInt();
            if (start1 < amountState) {
                this.start = start1;
                a = true;
            } else {
                PrintFA.print("You don't have this state in your FA!\nPlease input again!\n");
                a = false;
            }
        } while (!a);

        PrintFA.print("How many Final state? : ");
        while (!input.hasNextInt()) {
            PrintFA.println("Incorrect! Please input type is Integer!");
            PrintFA.print("How many Final state : ");
            input.next();
        }
        int end1 = input.nextInt();
        if (end1 < amountState) {
            if (end1 == 1) {
                PrintFA.print("What is it? : q");
                while (!input.hasNextInt()) {
                    PrintFA.println("Incorrect! Please input type is Integer!");
                    PrintFA.print("What is it? : q");
                    input.next();
                }
                end.add(input.nextInt());

            } else {
                PrintFA.println("What are they? :");
                for (int i = 0; i < end1; i++) {
                    PrintFA.print((i + 1) + ". q");
                    while (!input.hasNextInt()) {
                        PrintFA.println("Incorrect! Please input type is Integer!");
                        PrintFA.print((i + 1) + ". q");
                        input.next();
                    }
                    end.add(input.nextInt());
                }
            }
        }

        //Insert the transition
        for (int i=0; i<state.size();i++){
            String from = "q"+state.get(i);
            for (int j=0; j<symbols.size();j++){
                String on = symbols.get(j);
                char c = on.charAt(0);
                PrintFA.print("How many time which "+from+" make transition on "+on+" : ");
                while (!input.hasNextInt()) {
                    PrintFA.println("Incorrect! Please input type is Integer!");
                    PrintFA.print("How many time which "+from+" make transition on "+on+" : ");
                    input.next();
                }
                int k = input.nextInt();
                if (k==0){
                    if (!transition.containsKey(state.get(i))) transition.put(state.get(i), new TreeMap<Character, List<Integer>>());
                    if (!transition.get(state.get(i)).containsKey(c)) transition.get(state.get(i)).put(c,new ArrayList<>());
                    transition.get(state.get(i)).get(c).add(505);
                    model.setDFA(false);
                }
                for (int l=0; l<k; l++){
                    a = true;
                    do {
                        PrintFA.println((k-l)+"left");
                        PrintFA.print(from+" make transition on "+on+" to : q");
                        Scanner input1 = new Scanner(System.in);
                        while (!input1.hasNextInt()) {
                            PrintFA.println("Incorrect! Please input type is Integer!");
                            PrintFA.print(from+" make transition on "+on+" to : q");
                            input1.next();
                        }
                        int to = input1.nextInt();
                        if (to>=amountState && to!=404){
                            PrintFA.println("You don't have this state in your FA!");
                            a = false;
                        }else {
                            a = true;
                            if (!transition.containsKey(state.get(i))) transition.put(state.get(i), new TreeMap<Character, List<Integer>>());
                            if (!transition.get(state.get(i)).containsKey(c)) transition.get(state.get(i)).put(c,new ArrayList<>());
                            transition.get(state.get(i)).get(c).add(to);
                            if (transition.get(state.get(i)).get(c).size()>1){
                                model.setDFA(false);
                            }
                        }
                    }while (!a);

                }

            }
        }


        model.setEnd(end);
        model.setStart(start);
        model.setState(state);
        model.setSymbols(symbols);
        model.setTransition(transition);
        PrintFA.printState(model);
        callback.createFAListener(model);

    }

}
