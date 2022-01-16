package education.storage;

import education.model.Lesson;
import education.util.FileUtil;


import java.util.LinkedList;
import java.util.List;

public class LessonStorage {
    private List<Lesson> lessons = new LinkedList<>();


    //    private Lesson[] lessons = new Lesson[20];
//    private int size;
//
    public void add(Lesson lesson) {
        lessons.add(lesson);
        serialize();
//        if (lessons.length == size) {
//            extend();
//        }
//        lessons[size++] = lesson;
    }
//
//    private void extend() {
//        Lesson[] tmp = new Lesson[lessons.length * 2];
//        System.arraycopy(lessons, 0, tmp, 0, lessons.length);
//        lessons = tmp;
//    }

    public void print() {
        if (!lessons.isEmpty()) {
            System.out.println(lessons.toString());
        } else {
            System.err.println("lesson does not registered yet");
            return;
        }
//        for (int i = 0; i < size; i++) {
//            System.out.println(lessons[i]);
//        }
    }

    public Lesson getByLessonName(String lessonName) {
        return lessons.stream()
                .filter(s -> lessonName.equals(s.getLessonName()))
                .findFirst()
                .orElse(null);
    }
//        for (int i = 0; i < size; i++) {
//            if (lessons[i].getLessonName().equals(lessonName)) {
//                return lessons[i];
//            }
//        }
//        return null;
//    }


    public void deleteLessonByName(Lesson lesson) {
        lessons.remove(lesson);
        serialize();
//        for (int i = 0; i < size; i++) {
//            if (lessons[i].getLessonName().equals(lesson)) {
//                Array.deleteByIndex(lessons, i, size);
//            }
//            size--;
//            return;
//        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i) != null) {
                return true;
            }

        }
        return false;
    }

    public void initData() {
        List<Lesson> lessonList = FileUtil.deSerializeLessons();
        if (lessonList != null) {
            lessons = lessonList;
        }
    }


    public void serialize() {
        FileUtil.serializeLessons(lessons);

    }
}