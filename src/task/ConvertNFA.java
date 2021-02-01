package task;

import model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConvertNFA {
    Model model;
    Map<Integer, Map<Character,List<Integer>>> nfaTransition;
    public ConvertNFA(Model model) {
        this.model = model;
        nfaTransition = new TreeMap<Integer,Map<Character,List<Integer>>>();
        nfaTransition = model.getTransition();
    }

    public void match(){
        if (isNFA()){

            Map<String,Map<Character, List<String>>> dfaTransition = new TreeMap<String,Map<Character, List<String >>>();
            String state = "q"+model.getStart();
            if (!dfaTransition.containsKey(state)) dfaTransition.put(state, new TreeMap<Character,List<String>>());
            for (String a:model.getSymbols()){
                char aa = a.charAt(0);
                List<Integer> tran = nfaTransition.get(model.getStart()).get(aa);
                if (tran.size()>1){
                    if (!dfaTransition.get(state).containsKey(aa)) dfaTransition.get(state).put(aa,new ArrayList<>());
                    String tx ="";
                    for (Integer i:tran){
                        tx = tx+"q"+i;
                    }
                    dfaTransition.get(state).get(aa).add(tx);
                    dfaTransition.put(tx,new TreeMap<Character,List<String>>());
                }else if (tran.size()==1){
                    if (!dfaTransition.get(state).containsKey(aa)) dfaTransition.get(state).put(aa,new ArrayList<>());
                    String tx = "q"+nfaTransition.get(model.getStart()).get(aa).get(0);
                    dfaTransition.get(state).get(aa).add(tx);
                    if (!dfaTransition.containsKey(tx)) dfaTransition.put(tx,new TreeMap<Character, List<String>>());
                }
            }



            String nextState;
            String newState;
            for (Map.Entry<String,Map<Character,List<String>>> key:dfaTransition.entrySet()){
                nextState = key.getKey();
                for (String x:model.getSymbols()){
                    char a = x.charAt(0);
                    if (!dfaTransition.get(nextState).containsKey(a)){
                        dfaTransition.get(nextState).put(a,new ArrayList<>());
                        String q = "";
                        boolean l = true;
                        String current = "";
                        String numState = "";
                        for (int i=0;i<nextState.length();i++){
                            if (l && nextState.charAt(i)=='q'){
                                q="q";
                                l=false;
                            }else if (!l && nextState.charAt(i)!='q'){
                                q=q+nextState.charAt(i);
                                numState = numState+nextState.charAt(i);
                            }else if ((!l && nextState.charAt(i)=='q') || i==nextState.length()-1){
                                l=true;
                                List<Integer> newTran = nfaTransition.get(Integer.parseInt(numState)).get(a);
                                for (Integer n:newTran){
                                    current = current+"q"+n;
                                }
                            }
                        }
                        dfaTransition.get(nextState).get(a).add(current);
                        if (!dfaTransition.containsKey(current)) dfaTransition.put(current, new TreeMap<Character, List<String>>());
                    }
                }
            }
            PrintFA.println("DFA Transition : "+dfaTransition);
        }


    }

    private boolean isNFA(){
        if (model.isDFA()){
            PrintFA.println("Your FA is DFA already!!");
            return false;
        }
        return true;
    }



}
