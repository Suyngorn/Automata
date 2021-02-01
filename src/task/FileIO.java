package task;

import model.Model;

import java.io.*;
import java.util.*;

public class FileIO {
    private static String fileName = "automata.txt";

    public static void write(List<Model> models) {
        try {
            FileWriter fw = new FileWriter(fileName, false);

            for (Model teacher : models) {
                fw.append(teacher.toString());
            }
            fw.close();
        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public static List<Model> read() {
        List<Model> teachers = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();

                String[] data = text.split(";");
                Model model = new Model();
                model.setState(con(data[0]));
                model.setSymbols(conS(data[1]));
                model.setStart(Integer.parseInt(data[2]));
                model.setEnd(con(data[3]));
                model.setTransition(tran(data[4]));
                if (data[5].equals("false")){
                    model.setDFA(false);
                }else {
                    model.setDFA(true);
                }

                teachers.add(model);
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }

        return teachers;
    }

    private static List<Integer> con(String data) {
        List<Integer> da = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            char x = data.charAt(i);
            if (x != '[' && x != ']' && x != ',' && x != ' ') {
                da.add(Integer.parseInt(String.valueOf(x)));
            }
        }
        return da;
    }

    private static List<String> conS(String data) {
        List<String> da = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            char x = data.charAt(i);
            if (x != '[' && x != ']' && x != ',' && x != ' ') {
                da.add(String.valueOf(x));
            }
        }
        return da;
    }

    private static Map<Integer, Map<Character, List<Integer>>> tran(String da) {
        Map<Integer, Map<Character, List<Integer>>> train = new TreeMap<>();
        int state=0;
        int tr;
        char sy;
        String[] data = da.split(",");

        for (int i=0; i<data.length;i++){
            if (data[i].contains("S")){
                state = Integer.parseInt(String.valueOf(data[i].charAt(0)));
                if (!train.containsKey(state)) train.put(state, new TreeMap<>());
            }else if (data[i].contains("P")){
                if (!data[i].contains("404")){
                    sy = data[i].charAt(0);
                    if (!train.get(state).containsKey(sy)) train.get(state).put(sy, new ArrayList<>());
                }else {
                    if (!train.get(state).containsKey("404")) train.get(state).put('ε', new ArrayList<>());
                }

            }else if (data[i].contains("T")){
                if (data[i].contains("505")){
                    sy = data[i].charAt(3);
                    tr = Integer.parseInt("505");
                    train.get(state).get(sy).add(tr);
                }else {
                    sy = data[i].charAt(1);
                    tr = Integer.parseInt(String.valueOf(data[i].charAt(0)));
                    train.get(state).get(sy).add(tr);
                }

            }
        }
        return train;
    }
}
