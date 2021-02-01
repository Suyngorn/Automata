package model;

import task.PrintFA;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Model implements Serializable {
    private List<Integer> state;
    private List<Integer> end;
    private List<String> symbols;
    private int start;
    private Map<Integer, Map<Character, List<Integer>>> transition;
    private boolean DFA = true;


    public List<Integer> getState() {
        return state;
    }

    public void setState(List<Integer> state) {
        this.state = state;
    }

    public List<Integer> getEnd() {
        return end;
    }

    public void setEnd(List<Integer> end) {
        this.end = end;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Map<Integer, Map<Character, List<Integer>>> getTransition() {
        return transition;
    }

    public void setTransition(Map<Integer, Map<Character, List<Integer>>> transition) {
        this.transition = transition;
    }

    public boolean isDFA() {
        return DFA;
    }

    public void setDFA(boolean DFA) {
        this.DFA = DFA;
    }

    private String train() {
        String t = "";
        for (Map.Entry<Integer, Map<Character, List<Integer>>> key : getTransition().entrySet()) {
            int sta = key.getKey();
            t = t + sta + "S,";
            Map<Character, List<Integer>> mapSys = key.getValue();
            for (Map.Entry<Character, List<Integer>> key1 : mapSys.entrySet()) {
                char sy = key1.getKey();
                t = t + sy + "P,";
                List<Integer> tr = key1.getValue();
                for (int i : tr) {
                    t = t + i + sy + "T" + ",";
                }
            }
        }

        return t;
    }

    @Override
    public String toString() {
        return state +
                ";" + symbols +
                ";" + start +
                ";" + end +
                ";" + train() +
                ";" + DFA +
                "\n";
    }
}
