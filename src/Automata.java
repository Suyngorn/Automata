import callback.AutomataCallback;
import model.Model;
import task.*;

import java.util.*;

public class Automata {
    private static Model model;
    private static boolean stop=true;
    static Scanner input = new Scanner(System.in);
    static List<Model> models = new ArrayList<>();
    public static void main(String[] args) {
        boolean b = true;
        List<Model> lModel1 = FileIO.read();
        models.addAll(lModel1);
        while (b){
            switch (choosingMenu()){
                case 1:
                    CreateFA createFA = new CreateFA(callback);
                    createFA.createFA();
                    break;
                case 2:
                    testFA();
                    break;
                case 7:
                    b=false;
                    break;
                case 3:
                    if (model!=null){
                        stop = true;
                        while (stop){
                            Accepted accepted = new Accepted(model,callback);
                            accepted.convert();

                        }

                    }else {
                        println("Your FA is empty!");
                    }
                    break;
                case 4:
                    if (model!=null){
                        ConvertNFA convertNFA = new ConvertNFA(model);
                        convertNFA.match();
                    }else {
                        println("Your FA is empty!");
                    }
                    break;
                case 6:
                    List<Model> lModel = FileIO.read();

                    int ti =1;
                    for (Model model1:lModel){
                        print("\n=======\n"+"***"+ti);
                        ti++;
                        PrintFA.printState(model1);
                    }
                    break;

                default:
                    println("Incorrect!!");
                    break;
            }
        }


    }

    private static void testFA(){
        if (model!=null){
            if (model.isDFA()){
                println("Your FA is DFA!");
            }else {
                println("Your FA is NFA!");
            }
        }else {
            println("The FA is empty!");
        }
    }

    static int choosingMenu(){
        println("\n***Note***");
        println("Value of ε is 404");
        println("Value of ø is 0");
        print("==============*****==============\n" +
                "      Choosing A Menu\n");
        print("1. Create a Finite Automata\n" +
                "2. Testing FA is a DFA or NFA\n" +
                "3. Testing a String is accepted by a FA\n" +
                "4. Construct an equivalent DFA from an NFA\n" +
                "5. Minimize a DFA\n" +
                "6. History\n" +
                "7. Exit");
        println("\n         ===================");
        print("Chose a symbol : ");
        while (!input.hasNextInt()){
            print("Incorrect! Input type is a char!\n" +
                    "Chose a symbol : ");
            input.next();

        }
        return input.nextInt();
    }

    static void println(String a){
        System.out.println(a);
    }
    static void print(String a){
        System.out.print(a);
    }

    private static AutomataCallback callback = new AutomataCallback() {
        @Override
        public void createFAListener(Model model) {
            Automata.model = model;

            models.add(model);
            FileIO.write(models);
        }

        @Override
        public void acceptedStringListener() {
            stop = false;
        }
    };

    private static void addModel(){
        List<Integer> state = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        List<String> symbol = new ArrayList<>();
        Map<Integer, Map<Character,List<Integer>>> transition = new TreeMap<Integer, Map<Character, List<Integer>>>();
        int start = 0;

        state.add(0);
        state.add(1);
        state.add(2);

        end.add(2);

        symbol.add("a");
        symbol.add("b");

        transition.put(0,new TreeMap<Character,List<Integer>>());
        transition.put(1,new TreeMap<Character,List<Integer>>());
        transition.put(2,new TreeMap<Character,List<Integer>>());

        transition.get(0).put('a',new ArrayList<>());
        transition.get(0).put('b',new ArrayList<>());
        transition.get(2).put('a',new ArrayList<>());
        transition.get(2).put('b',new ArrayList<>());
        transition.get(1).put('a',new ArrayList<>());
        transition.get(1).put('b',new ArrayList<>());

        List<Integer> tt = new ArrayList<>();
        tt.add(505);

        List<Integer> tt1 = new ArrayList<>();
        tt1.add(0);
        tt1.add(1);

        List<Integer> tt2 = new ArrayList<>();
        tt2.add(2);

        List<Integer> tt3 = new ArrayList<>();

        List<Integer> tt4 = new ArrayList<>();

        List<Integer> tt5 = new ArrayList<>();

        List<Integer> tt6 = new ArrayList<>();

        List<Integer> tt7 = new ArrayList<>();

    }
}
