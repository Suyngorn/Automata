package model;

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

    @Override
    public String toString() {
        return "Model{" +
                "state=" + state +
                ", end=" + end +
                ", symbols=" + symbols +
                ", start=" + start +
                ", transition=" + transition +
                ", DFA=" + DFA +
                '}';
    }
}
