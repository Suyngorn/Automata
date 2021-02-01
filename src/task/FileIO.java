package task;

import model.Model;

import java.io.*;
import java.util.*;

public class FileIO {
    private static String fileName = "automata.txt";

    public static List<Model> read(){
        List<Model> teachers = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String text = scanner.nextLine();

                String[] data = text.split(",");

                Model mo = new Model();
                mo.setDFA(false);

                teachers.add(mo);

            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }

        return teachers;
    }
    public static void write(Model models){
        try {
            FileWriter fw = new FileWriter(fileName, false);
            fw.append(models.toString());
//            for (Model teacher : models) {
//                fw.append(String.valueOf(teacher.getID())).append(",").append(teacher.getFirstName()).append(",").append(teacher.getLastName()).append(",").append(teacher.getGender()).append(",").append(teacher.getDateOfBirth()).append(",").append(teacher.getPhone()).append(",").append(teacher.getEmail()).append("\n");
//            }
            fw.close();
        } catch(Exception error) {
            System.out.println(error);
        }
    }

    /*public static void showListName(ArrayList<TeacherObj> teachers){
        out.println("ID\tFirst Name\tLast Name\tGender\tDate of Birth\tPhone Number\tEmail");
        for (TeacherObj teacher : teachers) {
            out.println(teacher.getID() + "\t" + teacher.getFirstName() +
                    "\t\t" + teacher.getLastName() + "\t" + teacher.getGender() + "\t" + teacher.getDateOfBirth() +
                    "\t" + teacher.getPhone() + "\t" + teacher.getEmail());
        }
    }*/
}
