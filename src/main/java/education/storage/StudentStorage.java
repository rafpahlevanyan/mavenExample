package education.storage;

import education.model.Lesson;
import education.model.Student;
import education.util.FileUtil;


import java.util.LinkedList;
import java.util.List;

public class StudentStorage {
    private List<Student> students = new LinkedList<>();


    //    private Student[] students = new Student[20];
//    private int size;

    public void add(Student student) {
        students.add(student);
        FileUtil.serializeStudents(students);
//        if (size == students.length) {
//            extend();
//        }
//        students[size++] = student;
    }

//    private void extend() {
//        Student[] tmp = new Student[students.length * 2];
//        System.arraycopy(students, 0, tmp, 0, size);
//        students = tmp;
//
//    }

    public void print() {
        if (!students.isEmpty()) {
            System.out.println(students);

        } else {
            System.err.println("Student does not registered yet");
        }
//        students.forEach(System.out::println);

//        for (int i = 0; i < size; i++) {
//            System.out.println(students[i]);
//        }
    }

    public Student getByEmail(String email) {
        return students.stream()
                .filter(s -> email.equals(s.getEmail()))
                .findFirst()
                .orElse(null);
//        for (int i = 0; i < size; i++) {
//            if (students[i].getEmail().equals(email)) {
//                return students[i];
//            }
//        }
//        return null;
    }

    public void searchByLesson(Lesson lesson) {
        students.stream()
                .filter(s -> s.getLessonList().contains(lesson))
                .forEach(System.out::println);
//        for (int i = 0; i < size; i++) {
//            if (students[i].getLesson().equals(lesson)) {
//                System.out.println(students[i]);
//            }
//        }
    }

    public void deleteStudentByEmail(Student student) {
        students.remove(student);
        FileUtil.serializeStudents(students);
//        for (int i = 0; i < size; i++) {
//            if (students[i].getEmail().equals(student)) {
//                Array.deleteByIndex(students, i, size);
//            }
//            size--;
//            return;
//        }
    }

    public boolean isEmptyStudent() {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i) != null) {
                return true;
            }

        }
        return false;
    }

    public void initData() {
        List<Student> studentList = FileUtil.deSerializeStudents();
        if (studentList != null) {
            students = studentList;
        }
    }

}
