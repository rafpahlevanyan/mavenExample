package education;

//import homework.author_book.model.UserType;

import education.exception.UserNotFoundException;
import education.model.Lesson;
import education.model.Student;
import education.model.User;
import education.model.UserType;
import education.storage.LessonStorage;
import education.storage.StudentStorage;
import education.storage.UserStorage;
import education.util.Date;


import java.text.ParseException;
import java.util.*;

public class LessonStudentTest implements LessonStudentCommands {

    static Scanner scanner = new Scanner(System.in);
    static LessonStorage lessonStorage = new LessonStorage();
    static StudentStorage studentStorage = new StudentStorage();
    static UserStorage userStorage = new UserStorage();

    public static void main(String[] args) throws UserNotFoundException {

        initData();
        boolean isRun = true;
        while (isRun) {
            LessonStudentCommands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT -> isRun = false;
                case LOGIN -> login();
                case REGISTER -> register();
                default -> System.err.println("invalid command");
            }
        }
    }

    private static void initData() {
        lessonStorage.initData();
        studentStorage.initData();
        userStorage.initData();
    }

    private static void isUser() {
        boolean isRun = true;
        while (isRun) {
            LessonStudentCommands.printCommandsUser();
            String command = scanner.nextLine();


            switch (command) {
                case LOG_OUT -> isRun = false;
                case ADD_LESSON -> addLesson();
                case ADD_STUDENT -> addStudent();
                case PRINT_STUDENT -> studentStorage.print();
                case SEARCH_STUDENT_BY_LESSON -> searchStudentsByLesson();
                case PRINT_LESSONS -> lessonStorage.print();
                default -> System.err.println("invalid command");
            }
        }
    }

    private static void isAdmin() {
        boolean isRun = true;
        while (isRun) {
            LessonStudentCommands.printCommandsAdmin();
            String command = scanner.nextLine();

            switch (command) {
                case EXIT -> isRun = false;
                case ADD_LESSON -> addLesson();
                case ADD_STUDENT -> addStudent();
                case PRINT_STUDENT -> studentStorage.print();
                case SEARCH_STUDENT_BY_LESSON -> searchStudentsByLesson();
                case PRINT_LESSONS -> lessonStorage.print();
                case DELETE_LESSONS_BY_NAME -> deleteLessonByName();
                case DELETE_STUDENTS_BY_EMAIL -> deleteStudentByEmail();
                case CHANGE_LESSON -> changeLesson();
                default -> System.err.println("invalid command");
            }
        }
    }


    private static void login() {
        System.out.println("Please input email");
        String email = scanner.nextLine();
        User byEmail = null;
        try {
            byEmail = userStorage.getByEmail(email);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (byEmail != null) {
            System.out.println("please input password");
            String password = scanner.nextLine();
            if (byEmail.getPassword().equals(password)) {
                if (byEmail.getType() == UserType.ADMIN) {
                    isAdmin();
                } else if (byEmail.getType() == UserType.USER) {
                    isUser();
                }
            } else {
                System.err.println("password is wrong");
            }
        } else {
            System.err.println("User with " + email + " does not exists");
        }
    }


    private static void register() throws UserNotFoundException {
        System.out.println("Please input your email");
        String email = scanner.nextLine();
        if (userStorage.getUserMap().get(email) == null) {
            userStorage.getByEmail(email);
            System.out.println("Please enter your password");
            String password = scanner.nextLine();
            System.out.println("Please enter your name");
            String name = scanner.nextLine();
            System.out.println("Please enter your surname");
            String surname = scanner.nextLine();
            System.out.println("Please enter user type exm 'ADMIN or USER' ");
            try {
                String type = scanner.nextLine();
                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                user.setSurname(surname);
                user.setType(UserType.valueOf(type.toUpperCase(Locale.ROOT)));
                userStorage.add(user);
                System.out.println("you are registered");
            } catch (IllegalArgumentException e) {
                System.err.println("invalid type");
            }


        } else {
            System.err.println("Email is already exists");
        }
    }


    private static void deleteStudentByEmail() {
        if (studentStorage.isEmptyStudent()) {
            System.out.println("please choose student");
            System.out.println("----------------------------");
            studentStorage.print();
            System.out.println("----------------------------");
            String email = scanner.nextLine();
            Student student = studentStorage.getByEmail(email);
            if (student != null) {
                studentStorage.deleteStudentByEmail(student);
            } else {
                System.err.println("Student does not exists");
            }
        } else {
            System.err.println("student does not registered yet");
        }

    }


    private static void deleteLessonByName() {
        if (lessonStorage.isEmpty()) {
            System.out.println("Please choose lesson");
            System.out.println("-----------");
            lessonStorage.print();
            System.out.println("-----------");
            String lessonName = scanner.nextLine();
            Lesson lesson = lessonStorage.getByLessonName(lessonName);
            if (lesson != null) {
                lessonStorage.deleteLessonByName(lesson);
                System.out.println("Lesson was deleted");
            } else {
                System.err.println("Lesson does not exists");
            }
        } else {
            System.err.println("not registered lesson yet");
        }

    }

    private static void printLessonsList() {

        System.out.println("Please choose lesson name");
        System.out.println("--------------------------");
        lessonStorage.print();
        System.out.println("--------------------------");

    }

    private static void addStudent() {
        if (lessonStorage.isEmpty()) {
            printLessonsList();
            String lessonNamesString = scanner.nextLine();
            String[] lessonNames = lessonNamesString.split(",");
            if (lessonNames.length == 0) {
                System.err.println("Please choose lessons with ',' ");
                return;
            }
            Lesson[] lessons = new Lesson[lessonNames.length];
            int foundLessons = 0;
            for (String lessonName : lessonNames) {
                Lesson lesson = lessonStorage.getByLessonName(lessonName);
                if (lesson != null) {
                    lessons[foundLessons++] = lesson;

                } else {
                    System.err.println("PLease input correct lesson name");
                    return;
                }
            }


            System.out.println("Please input student email ");
            String email = scanner.nextLine();
            if (studentStorage.getByEmail(email) == null) {
                System.out.println("Please input student name ");
                String name = scanner.nextLine();
                System.out.println("PLease input student surname");
                String surname = scanner.nextLine();
                System.out.println("please input student age");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("please input student phone number");
                String phoneNumber = scanner.nextLine();
                System.out.println("please input student date of birth  'dd.mm.yyyy'    ");
                java.util.Date date;
                try {
                    date = Date.stringToDate(scanner.nextLine());
                } catch (ParseException e) {
                    System.out.println("Invalid date format, please input this format 'dd,mm,yyyy' ");
                    return;
                }

                Student student = new Student(name, surname, age, email, phoneNumber, date, new LinkedList<>(Arrays.asList(lessons)));

                studentStorage.add(student);
                System.out.println("Student was added");
                System.out.println();
            } else {
                System.err.println("Student with this email: " + email + " is exists");
            }
        }
    }


    private static void addLesson() {
        System.out.println("PLease input lesson name, duration, lecturer name, price");
        String lessonDataStr = scanner.nextLine();
        String[] lessonData = lessonDataStr.split(",");
        if (lessonData.length == 4) {
            try {
                int duration = Integer.parseInt(lessonData[1]);
                int price = Integer.parseInt(lessonData[3]);
                Lesson lesson = new Lesson(lessonData[0], duration, lessonData[2], price);
                if (lessonStorage.getByLessonName(lesson.getLessonName()) != null) {
                    System.err.println("Lesson with this name is already exists");
                } else {
                    lessonStorage.add(lesson);
                    System.out.println("Lesson was added");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Please input correct arguments");
                addLesson();
            }


        } else {
            System.err.println("invalid data");
        }
//        System.out.println("Please input lesson name");
//        String lessonName = scanner.nextLine();
//        if (lessonStorage.getByLessonName(lessonName) == null) {
//            System.out.println("Please input lesson duration");
//
//            try {
//                int duration = Integer.parseInt(scanner.nextLine());
//                System.out.println("Please input lecturer name");
//                String lecturerName = scanner.nextLine();
//                System.out.println("Please input lesson price");
//                int price = Integer.parseInt(scanner.nextLine());
//
//                Lesson lesson = new Lesson(lessonName, duration, lecturerName, price);
//                lessonStorage.add(lesson);
//                System.out.println("Lesson was added");
//                System.out.println();
//            } catch (NumberFormatException e) {
//                System.err.println("Please write correct duration");
//                System.out.println();
//                addLesson();
//            }
//
//        }else {
//            System.out.println("Lesson with this name is already exists");
    }
//        System.out.println("PLease input lesson name, duration, lecturer name, price");
//        String lessonDataStr = scanner.nextLine();
//        String[] lessonData = lessonDataStr.split(",");
//        if (lessonData.length == 4) {
//            String name =
//            int duration = Integer.parseInt(lessonData[1]);
//            int price = Integer.parseInt(lessonData[3]);
//            Lesson lesson = new Lesson(name , duration, lessonData[2], price);
//            if (lessonStorage.getByLessonName(lesson.getLessonName()) != null) {
//                System.err.println("Lesson with this name is already exists");
//            } else {
//                lessonStorage.add(lesson);
//                System.out.println("Lesson was added");
//                System.out.println();
//            }
//        } else {
//            System.err.println("invalid data");
//        }


    private static void searchStudentsByLesson() {
        printLessonsList();
        String lessonName = scanner.nextLine();
        Lesson lesson = lessonStorage.getByLessonName(lessonName);
        if (lesson != null) {
            studentStorage.searchByLesson(lesson);
        } else {
            System.err.println("lesson does not exists");
        }
    }

    private static void changeLesson() {
        if (studentStorage.isEmptyStudent()) {
            System.out.println("PLease choose student email");
            System.out.println("------------");
            studentStorage.print();
            System.out.println("------------");
            String email = scanner.nextLine();
            Student student = studentStorage.getByEmail(email);
            if (student != null) {
                printLessonsList();
                String lessonName = scanner.nextLine();
                String[] lessonArray = lessonName.split(",");
                if (lessonArray.length == 0) {
                    System.err.println("Please choose lesson");
                    return;
                }
                Lesson[] lessons = new Lesson[lessonArray.length];
                int size = 0;
                for (String lessonName1 : lessonArray) {
                    Lesson lesson1 = lessonStorage.getByLessonName(lessonName1);
                    if (lesson1 != null) {
                        lessons[size++] = lesson1;
                    } else {
                        System.err.println("Please input correct lesson");
                        return;
                    }
                }

                student.setLessonList(List.of(lessons));
                lessonStorage.serialize();
                System.out.println("Lessons was changed");
            } else {
                System.err.println("Student does not exists");
                System.out.println();
            }
        } else {
            System.err.println("Student does not registered yet");
        }
    }

}
