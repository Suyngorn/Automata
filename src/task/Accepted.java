package task;

import callback.AutomataCallback;
import model.Model;

import java.util.*;

public class Accepted {
    String startState;
    Set<String> finalState;
    Map<String, Map<Character, List<String>>> transitions;
    Model model;
    AutomataCallback callback;

    public Accepted(Model model, AutomataCallback ca){
        this.model = model;
        this.callback = ca;
    }
    public Accepted(String[] ss, String[] ts){
        finalState = new TreeSet<String>();
        transitions = new TreeMap<String, Map<Character, List<String>>>();
        //State
        for (String v : ss) {
            String[] pieces = v.split(",");
            if (pieces.length > 1) {
                if (pieces[1].equals("S")) startState = pieces[0];
                else if (pieces[1].equals("E")) finalState.add(pieces[0]);
            }
        }

        //Transition
        for (String e : ts) {
            String[] pieces = e.split(",");
            String from = pieces[0], to = pieces[1];
            if (!transitions.containsKey(from)) transitions.put(from, new TreeMap<Character, List<String>>());
            for (int i = 2; i < pieces.length; i++) {
                char c = pieces[i].charAt(0);
                // difference from DFA: list of next states
                if (!transitions.get(from).containsKey(c)) transitions.get(from).put(c, new ArrayList<String>());
                transitions.get(from).get(c).add(to);
            }
        }
//        System.out.println("start:" + startState);
//        System.out.println("end:" + finalState);
//        System.out.println("transitions:" + transitions);
    }

    /**
     * Returns whether or not the DFA accepts the string --
     * follows transitions according to its characters, landing in an end state at the end of the string
     */
    public boolean match(String s) {
        // difference from DFA: multiple current states
        Set<String> currStates = new TreeSet<String>();
        currStates.add(startState);
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            Set<String> nextStates = new TreeSet<String>();
            // transition from each current state to each of its next states
            for (String state : currStates)
                if (transitions.get(state).containsKey(c))
                    nextStates.addAll(transitions.get(state).get(c));
            if (nextStates.isEmpty()) return false; // no way forward for this input
            currStates = nextStates;
        }
        // end up in multiple states -- accept if any is an end state
        for (String state : currStates) {
            if (finalState.contains(state)) return true;
        }
        return false;
    }

    /**
     * Helper method to test matching against a bunch of strings, printing the results
     */

    public void test(String inputs) {
        PrintFA.println("***");
        if (match(inputs)){
            PrintFA.println(inputs+" is accepted by FA");
        }else {
            PrintFA.println(inputs+" is rejected by FA");
        }

    }


    public void convert(){
        String S;   //Start state
        String[] E; //Final state
        String[] State; //State
        //get start state and final state from the list
        S = "q"+model.getStart();
        E = new String[model.getEnd().size()];
        for (int i=0;i<model.getEnd().size();i++){
            E[i] = "q"+model.getEnd().get(i);
        }

        int index = model.getState().size();//1 is the length of the S;
        int count = 0;
        State = new String[index];
        State[count] = S + ",S";
        count++;
        for (int i = 0; i < E.length; i++) {
            State[count] = E[i] + ",E";
            count++;
        }

        for (int i = 0; i < model.getState().size(); i++) {
            String q = "q"+model.getState().get(i);
            for (int j = 0; j < E.length; j++) {
                if (!q.equals(S) && !q.equals(E[j]) && count < index) {
                    State[count] = q;
                    count++;
                    break;
                }
            }
        }

        Map<Integer,Map<Character,List<Integer>>> transition = model.getTransition();
        List<String> lTx = new ArrayList<>();

        for (Integer i : model.getState()){
            for (String j:model.getSymbols()){
                List<Integer> y = transition.get(i).get(j.charAt(0));
                for (Integer k:y){
                    if (k!=404 && k!=505){
                        String x="q"+i;
                        x=x+",q"+k+","+j;
                        lTx.add(x);
                    }
                }
            }
        }

        String[] tx = new String[lTx.size()];

        for (int i=0;i<lTx.size();i++){
            tx[i] = lTx.get(i);
            PrintFA.println(tx[i]);
        }

        Accepted dfa = new Accepted(State, tx);
        Scanner input = new Scanner(System.in);
        PrintFA.print("Input the String you want to test : ");
        String test = input.next();
        if (test.equals("exit")){
            callback.acceptedStringListener();
        }else {
            dfa.test(test);
        }

    }

}
