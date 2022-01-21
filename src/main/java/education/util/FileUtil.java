package education.util;


import education.model.Lesson;
import education.model.Student;
import education.model.User;


import java.io.*;
import java.util.List;
import java.util.Map;

public class FileUtil {

    private static final String dataPath = "C:\\Users\\User\\IdeaProjects\\mavenExample\\src\\main\\resources\\data";

    public static void serializeLessons(List<Lesson> lessonsList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                dataPath + "/lessons.dat"))) {

            out.writeObject(lessonsList);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void serializeStudents(List<Student> studentList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                dataPath + "/students.dat"))) {

            out.writeObject(studentList);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void serializeUserMap(Map<String, User> userMap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                dataPath + "/userMap.dat"))) {
            out.writeObject(userMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static List<Lesson> deSerializeLessons() {
        File lessonFile = new File(dataPath + "/lessons.dat");

        if (!lessonFile.exists()) {
            try {
                lessonFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    lessonFile))) {

                Object obj = in.readObject();
                return (List<Lesson>) obj;
            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static List<Student> deSerializeStudents() {
        File studentFile = new File(dataPath + "/students.dat");

        if (!studentFile.exists()) {
            try {
                studentFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    studentFile))) {

                Object obj = in.readObject();
                return (List<Student>) obj;

            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map<String, User> deSerializeUserMap() {
        File userFile = new File(dataPath + "/userMap.dat");

        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    userFile))) {

                Object obj = in.readObject();
                return (Map<String, User>) obj;
            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
