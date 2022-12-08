import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;
import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;

/** @author Onsang Yau, Abd */

public class Controller {

    public static void main(String[] args) {
        Controller controller= new Controller();
        FileInfoReader reader= new FileInfoReader();
        reader.readFromStudentFile("studentInfo.txt");
        reader.readFromAdminFile("adminInfo.txt");
        reader.readFromProfFile("profInfo.txt");
        reader.readFromCourseFile("courseInfo.txt");
        controller.run(reader);
    }

    public void run(FileInfoReader reader) {
        boolean QuitTheSystem= false;
        do {
            System.out.println("--------------------------");
            System.out.println("Students Management System");
            System.out.println("--------------------------");
            System.out.println("1 -- Login as a student");
            System.out.println("2 -- Login as a professor");
            System.out.println("3 -- Login as an admin");
            System.out.println("4 -- Quit the system");
            System.out.println("\n");
            Scanner scanner= new Scanner(System.in);
//            boolean QuitTheSystem= false;
//            while (!QuitTheSystem) {
            int LoginType= askForNum("Please enter your option, e.g. '1'", scanner, 1, 4);
            // if input = 1, 2, or 3
            if (LoginType != 4) {
                // if user is a student
                if (LoginType == 1) {
//                    if (!StudentLogin(scanner, reader)) { run(reader); }
                    StudentLogin(scanner, reader);
                } else if (LoginType == 2) {
//                    if (!ProfessorLogin(scanner, reader)) { run(reader); }
                    ProfessorLogin(scanner, reader);

                } else if (LoginType == 3) {
//                    if (!AdminLogin(scanner, reader)) { run(reader); }
                    AdminLogin(scanner, reader);
                }
            } else {
                QuitTheSystem= true;
                System.out.println("Quitted Successfully.");
                scanner.close();

            }
//            }
        } while (!QuitTheSystem);

    }

    /** This is the function handling a professor trying to log in
     *
     * @param scanner: the scanner
     * @param reader:  the reader
     * @return a boolean indicating quitting this professor menu */
    private static boolean ProfessorLogin(Scanner scanner, FileInfoReader reader) {
        // store username
        String username= askForString("Please enter your username, or type 'q' to quit", scanner);
        if (username.equals("q")) { return false; }
        // store password
        String password= askForString("Please enter your password, or type 'q' to quit", scanner);
        if (password.equals("q")) { return false; }

        // find our Student object
        Professor ourProf= checkProfLogin(reader.getProfObj(), username, password);
        if (ourProf != null) {
            boolean loopContinues= true;
            while (loopContinues) {
                printMenus(3, username);
                int answer= askForNum("Please enter your option, e.g. '1'.", scanner, 1, 3);
//                System.out.println("Line 72:" + answer);
                if (answer != 3) {
                    // 1 -- View all courses
                    if (answer == 1) {
//                        System.out.println("Testing testing");
                        for (Course c : ourProf.getGivenCourses(reader.getCourseObj())) {
                            System.out.println(c.print());
                        }
                        // 2 -- Add courses to your list
                    } else if (answer == 2) {
                        boolean enterID= true;
                        // prompt the user
                        do {
                            String CourseID= askForString(
                                "Please enter the CourseID. e.g. CIT590, or type 'q' to quit",
                                scanner);
                            if (CourseID.equals("q")) { break; }
                            // TODO
                            Course courseToView= checkCourseToAdd(reader.getCourseObj(), CourseID);
                            // if the prof entered a course with valid course code
                            if (CourseID != null) {
                                ArrayList<Student> students= ourProf.getStudentList(courseToView,
                                    reader.getStudentObj());
                                for (Student s : students) {
                                    System.out.println(s.getID() + " " + s.getName());
                                }
                                enterID= false;
                            }
                        } while (enterID);

                    }

                    // if answer == 3: quit to main menu
                } else {
                    loopContinues= false;
                }
            }
            // if the student entered wrong combination of username and passwords
        } else {
            System.out
                .println("Your username and password combination is wrong. Please try again!");
            return StudentLogin(scanner, reader);
        }
//        System.out.println("Line 104 reached");
        return false;
    }

    private static boolean AdminLogin(Scanner scanner, FileInfoReader reader) {

        // Store username and password
        String username= askForString("Please enter your username, or type 'q' to quit",
            scanner);
        if (username.equals("q")) { return false; }
        // store password
        String password= askForString("Please enter your password, or type 'q' to quit",
            scanner);
        if (password.equals("q")) { return false; }

        Admin ourAdmin= checkAdminLogin(reader.getAdminObj(), username, password);
        if (ourAdmin != null) {
            boolean loopcontinues= true;
            while (loopcontinues) {

                printMenus(2, username);
                int answer= askForNum("Please enter your option, e.g. '1'.", scanner, 1, 8);
                // if answers = 1 - 7
                if (answer != 8) {
                    // 1 - View all courses
                    if (answer == 1) {
                        for (Course c : reader.getCourseObj()) {
                            System.out.println(c.print());
                        }
                        // if answer = 2 - Add new courses
                    } else if (answer == 2) {
//                      // check if the course exists
                        String CourseID= AdminAddCourse(reader,
                            "Please enter the course ID, or type 'q' to quit. e.g. 'CIT800'",
                            scanner);
                        if (CourseID.equals("q")) { continue; }
                        String name= askForString(
                            "Please enter the course name, or type 'q' to quit. e.g. 'Java' ",
                            scanner);
                        if (name.equals("q")) { continue; }
                        String startTime= askForString(
                            "Please enter the course start time, or type 'q' to quit. e.g. '20:00'",
                            scanner);
                        if (startTime.equals("q")) { continue; }
                        String endTime= askForString(
                            "Please enter the course end time, or type 'q' to quit. e.g. '21:00'",
                            scanner);
                        if (endTime.equals("q")) { continue; }
                        String date= askForString(
                            "Please enter the course's day of week, or type 'q' to quit. e.g. 'MWF'",
                            scanner);
                        if (date.equals("q")) { continue; }
                        String capacity= askForString(
                            "Please enter the course capacity, or type 'q' to quit. e.g. '72'",
                            scanner);
                        if (capacity.equals("q")) { continue; }
                        int classSize= Integer.parseInt(capacity);
                        String lecturerId= askForString(
                            "Please enter the course lecturer's id, or type 'q' to quit. e.g. '001'",
                            scanner);
                        if (lecturerId.equals("q")) { continue; }
                        // 1. check if professor exists
                        boolean ProfExists= professorExists(lecturerId, reader.getProfObj());
                        if (ProfExists) {
                            // get the existing professor
                            Professor prof= getProfByID(reader.getProfObj(), lecturerId);
                            // 2. check if the course conflicts with other courses that the prof is
                            // teaching
                            ArrayList<Course> currentCourse= prof
                                .getGivenCourses(reader.getCourseObj());
                            Course courseToAdd= new Course(CourseID, name, prof.getName(), date,
                                startTime, endTime, classSize);
                            // if no conflict
                            if (!prof.timeConflict(courseToAdd, currentCourse)) {
                                // add the course to the db
                                ourAdmin.addNewCourse(courseToAdd, reader.getCourseObj(), true,
                                    false);

                                // if there is a time Conflict
                            } else {
                                ourAdmin.addNewCourse(courseToAdd, reader.getCourseObj(), true,
                                    true);

                            }
                            // if professor doesn't exist, create a new prof
                        } else {
                            Professor newProf;
                            newProf= AdminCreateProf(reader, scanner);
                            if (newProf == null) {
                                continue;
                            } else {
                                Course courseToAdd2= new Course(CourseID, name, newProf.getName(),
                                    date,
                                    startTime, endTime, classSize);
                                if (!newProf.timeConflict(courseToAdd2,
                                    newProf.getGivenCourses(reader.getCourseObj()))) {
                                    ourAdmin.addNewCourse(courseToAdd2, reader.getCourseObj(), true,
                                        false);
                                } else {
                                    ourAdmin.addNewCourse(courseToAdd2, reader.getCourseObj(), true,
                                        true);
                                }
                            }

                        }
                        // 3 -- Delete courses
                    } else if (answer == 3) {
                        boolean loopContinues= true;

                        do {
                            String courseIDToDelete= askForString(
                                "Please enter the course code that you want to delete, or type 'q' to quit. e.g. 'CIS550' ",
                                scanner);
                            if (courseIDToDelete.equals("q")) { break; }
                            Course courseToDelete= checkCourseToAdd(reader.getCourseObj(),
                                courseIDToDelete);
                            // if the course the admin wants to delete is valid
                            if (courseToDelete != null) {
                                ourAdmin.deleteCourse(courseToDelete, reader.getCourseObj());
                                loopContinues= false;
                            } else {
                                System.out.println(
                                    "The course you are trying to delete is not in the database. try again!!!");
                            }

                        } while (loopContinues);
                        // delete professor
                    } else if (answer == 4) {
                        boolean loopContinues= true;
                        do {
                            String ProfIDToDelete= askForString(
                                "Please enter the ID of the professor that you want to delete, or type 'q' to quit. e.g. 'CIS550' ",
                                scanner);
                            if (ProfIDToDelete.equals("q")) { break; }
                            Professor profToDelete= getProfByID(reader.getProfObj(),
                                ProfIDToDelete);
                            // if the professor is in the database
                            if (profToDelete != null) {
                                ourAdmin.deleteProfessor(profToDelete, reader.getProfObj());
                                loopContinues= false;
                                // if not, prompt the user again
                            } else {
                                System.out.println(
                                    "The professor you are trying to delete is not in the database. try again!!!");
                            }

                        } while (loopContinues);
                    } else if (answer == 5) {
                        boolean loopContinues= true;
                        do {
                            String StudentIDToDelete= askForString(
                                "Please enter the ID of the student that you want to delete, or type 'q' to quit. e.g. 'CIS550' ",
                                scanner);
                            if (StudentIDToDelete.equals("q")) { break; }
                            Student StudentToDelete= getStudentByID(reader.getStudentObj(),
                                StudentIDToDelete);
                            // if the professor is in the database
                            if (StudentToDelete != null) {
                                ourAdmin.deleteStudent(StudentToDelete, reader.getStudentObj());
                                loopContinues= false;
                                // if not, prompt the user again
                            } else {
                                System.out.println(
                                    "The professor you are trying to delete is not in the database. try again!!!");
                            }

                        } while (loopContinues);
                        // add a new student
                    } else if (answer == 6) {
                        boolean loopContinues= true;
                        do {
                            String StudentToAdd= askForString(
                                "Please enter the ID of the student that you want to add, or type 'q' to quit. e.g. '001' ",
                                scanner);
                            if (StudentToAdd.equals("q")) { break; }
                            Student checkNotExist= getStudentByID(reader.getStudentObj(),
                                StudentToAdd);
                            // if the professor is in the database
                            if (checkNotExist == null) {
                                String StudentUsername= askForString(
                                    "Please enter the student's username, or type 'q' to quit. e.g. 'studnet1'",
                                    scanner);
                                if (StudentUsername.equals("q")) { continue; }
                                String StudentPassword= askForString(
                                    "Please enter the student's password, or type 'q' to quit. e.g. '12344444'",
                                    scanner);
                                if (StudentPassword.equals("q")) { continue; }
                                if (checkStudentLogin(reader.getStudentObj(), StudentUsername,
                                    StudentPassword) != null) {
                                    System.out.println(
                                        "The student with this username and password already exist. please try again !!!");
                                    continue;
                                }
                                String StudentID= askForString(
                                    "Please enter the student's ID, or type 'q' to quit. e.g. '02'",
                                    scanner);
                                if (StudentUsername.equals("q")) { continue; }
                                if (getStudentByID(reader.getStudentObj(), StudentID) != null) {
                                    System.out.println(
                                        "The student with this ID already exist. please try again !!!");
                                    continue;
                                }
                                String StudentName= askForString(
                                    "Please enter the student's name, or type 'q' to quit. e.g. 'Stephen straww'",
                                    scanner);
                                if (StudentName.equals("q")) { continue; }

                                Student AdminChoice5= new Student(StudentUsername, StudentPassword,
                                    StudentID,
                                    StudentName, grade2);
                                ourAdmin.addStudent(StudentToAdd, reader.getStudentObj());
                                loopContinues= false;
                                // if not, prompt the user again
                            } else {
                                System.out.println(
                                    "The student you are trying to add already exists in the database. try again!!!");
                            }

                        } while (loopContinues);
                    }

                    // answer == 8
                } else {
                    loopcontinues= false;
                }

            }
        } else {
            System.out
                .println("Your username and password combination is wrong. Please try again!");
            return AdminLogin(scanner, reader);

        }

        return false;
    }

    /** create an ArrayList of string that represent the Grades of the student
     *
     * @param scanner
     * @param reader
     * @return */
    private static ArrayList<String> CreateGrades(FileInfoReader reader, Scanner scanner) {
        boolean loop= true;
        ArrayList<String> output;
        do {
            String CourseID= askForString(
                "Please enter the ID of a course which this student already took, one in a time. Type 'q' to quit, type 'n' to stop adding.",
                scanner);
            if

        } while (loop);
    }

    private static boolean StudentLogin(Scanner scanner, FileInfoReader reader) {
        // store username
        String username= askForString("Please enter your username, or type 'q' to quit", scanner);
        if (username.equals("q")) { return false; }
        // store password
        String password= askForString("Please enter your password, or type 'q' to quit", scanner);
        if (password.equals("q")) { return false; }

        // find our Student object
        Student ourStudent= checkStudentLogin(reader.getStudentObj(), username, password);
        if (ourStudent != null) {
            boolean loopContinues= true;
            while (loopContinues) {
                printMenus(1, username);
                int answer= askForNum("Please enter your option, e.g. '1'.", scanner, 1, 6);
//                System.out.println("Line 72:" + answer);
                if (answer != 6) {
                    // 1 -- View all courses
                    if (answer == 1) {
//                        System.out.println("Testing testing");
                        for (Course c : reader.getCourseObj()) {
                            System.out.println(c.print());
                        }
                        // 2 -- Add courses to your list
                    } else if (answer == 2) {
                        Course course= StudentaddCourses(scanner, reader);
                        System.out.println("The courses in your list are: ");// TODO

                        // 3 -- View enrolled courses
                    } else if (answer == 3) {
//                        System.out.println(ourStudent.g) // TODO

                        // 4 -- Drop courses in your list
                    } else if (answer == 4) {
                        // ourStudent.dropxxx

                        // 5 -- View grades
                    } else if (answer == 5) {

                    }

                    // if answer == 6: quit to main menu
                } else {
                    loopContinues= false;
                }
            }
            // if the student entered wrong combination of username and passwords
        } else {
            System.out
                .println("Your username and password combination is wrong. Please try again!");
            return StudentLogin(scanner, reader);
        }
//        System.out.println("Line 104 reached");
        return false;
    }

    /** This function prints the menus based on user type
     *
     * @param: type     is an integer; 1 = student, 2= admin, 3 = prof
     * @param: username as String */
    private static void printMenus(int type, String username) {
        System.out.println("--------------------------");
        System.out.println("Welcome, " + username);
        System.out.println("--------------------------");
        // student
        if (type == 1) {
            System.out.println("1 -- View all courses");
            System.out.println("2 -- Add courses to your list");
            System.out.println("3 -- View enrolled courses");
            System.out.println("4 -- Drop courses in your list");
            System.out.println("5 -- View grades");
            System.out.println("6 -- Return to previous menu");
        }
        // admin
        else if (type == 2) {

            System.out.println("1 -- View all courses");
            System.out.println("2 -- Add new courses");
            System.out.println("3 -- Delete courses");
            System.out.println("4 -- Add new professor");
            System.out.println("5 -- Delete professor");
            System.out.println("6 -- Add new student");
            System.out.println("7 -- Delete a student");
            System.out.println("8 -- return to previous menu");

        } else if (type == 3) {
            System.out.println("1 -- View given courses");
            System.out.println("2 -- View student list of the given courses");
            System.out.println("3 -- Return to the previous menu");

        }
    }

    /** This function check if the professor with the username and password exist
     *
     * @param list
     * @param username
     * @param password
     * @return */
    private static Professor checkProfLogin(ArrayList<Professor> list, String username,
        String password) {
        for (Professor s : list) {
            if (s.getUserName().equals(username) && s.getPassword().equals(password)) { return s; }
        }
        return null;
    }

    /** This function gets the Prof with the given id
     *
     * @param list:     an arraylist of professor
     * @param professor ID
     * @return a professor obj */
    private static Professor getProfByID(ArrayList<Professor> list, String ID) {
        for (Professor s : list) {
            if (s.getID().equals(ID)) { return s; }
        }
        return null;
    }

    /** This function gets the Student with the given id
     *
     * @param list:   an arraylist of Student
     * @param student ID
     * @return a student obj */
    private static Student getStudentByID(ArrayList<Student> list, String ID) {
        for (Student s : list) {
            if (s.getID().equals(ID)) { return s; }
        }
        return null;
    }

    /** This function check if the admin with the username and password exist
     *
     * @param list
     * @param username
     * @param password
     * @return */
    private static Admin checkAdminLogin(ArrayList<Admin> list, String username,
        String password) {
        for (Admin a : list) {
            if (a.getUserName().equals(username) && a.getPassword().equals(password)) { return a; }
        }
        return null;
    }

    /** This function check if the student with the username exist
     *
     * @param list
     * @param username
     * @param password
     * @return */
    private static Student checkStudentLogin(ArrayList<Student> list, String username,
        String password) {
        for (Student s : list) {
            if (s.getUserName().equals(username) && s.getPassword().equals(password)) { return s; }
        }
        return null;
    }

    /** check if the professor is already in the profInfo
     *
     * @param professorID
     * @param profInfo
     * @return */
    private static boolean professorExists(String Id, ArrayList<Professor> profInfo) {
        for (Professor p : profInfo) {
            if (p.getID().equals(Id)) { return true; }
        }
        return false;
    }

    /** check if an entered coursecode is in the current course list
     *
     * @param course:     an arraylist of Course obj
     * @param courseCode: a string
     * @return */
    private static Course checkCourseToAdd(ArrayList<Course> course, String courseCode) {
        for (Course c : course) {
            if (c.getCourseCode().equals(courseCode)) { return c; }
        }
        return null;
    }

    /** do the action of adding a course that user selected to the user's cart
     *
     * @param scanner: a scanner
     * @param reader:  the fileinforeader */
    private static Course StudentaddCourses(Scanner scanner, FileInfoReader reader) {
        boolean keeptheloop2= true;
        System.out.println("Which course do you want to add to your cart?");

        do {
            String addCourse= askForString("Please enter the course number that you want to add: ",
                scanner);
            Course courseToAdd= checkCourseToAdd(reader.getCourseObj(), addCourse);
            if (courseToAdd == null) {
                System.out
                    .println(
                        "The course code you entered is wrong/ not a valid course. Please try again!");
            } else {
                keeptheloop2= false;
                // TODO: student.addCourse();
                System.out.println(
                    "You have successfully added " + courseToAdd.getCourseCode() +
                        " in your cart!");
                return courseToAdd;

            }

        } while (keeptheloop2);
        return null;
    }

    /** return a String of course code (check if course is already in list; if so prompt again
     *
     * @param reader:  a FileInfoReader
     * @param prompt:  a string
     * @param scanner: a scanner
     * @return: a string of course code (valid) */
    private static String AdminAddCourse(FileInfoReader reader, String prompt, Scanner scanner) {
        boolean loop= true;
        String answer;
        do {
            answer= askForString(prompt, scanner);
            if (answer.equals("q")) loop= false;
            // if answer represents an existing course, then checkCourseToAdd == a real Course
            // object
            else if (checkCourseToAdd(reader.getCourseObj(), answer.strip()) != null) {
                System.out.println(
                    "Sorry, the course with the ID already exists in our database. Please enter a new course only.");
            } else {
                loop= false;
            }

        } while (loop);
        System.out.println("The course to be added is " + answer);
        return answer;
    }

    /** return a new Professor
     *
     * @param reader:  a FileInfoReader
     * @param prompt:  a string
     * @param scanner: a scanner
     * @return: a string of course code (valid) */
    private static Professor AdminCreateProf(FileInfoReader reader, Scanner scanner) {
        boolean loop= true;
        String ID;
        Professor newProf;
//        String notice;
        do {
            System.out.println(
                "This professor hasn't been established in our system yet. Let's create the professor.");
            ID= askForString("Please enter the professor's ID, or type 'q' to quit. ", scanner);
            if (ID.equals("q")) {
                loop= false;
                break;
            }
            // the case where prof already exists
            if (professorExists(ID, reader.getProfObj())) {
                System.out.println(
                    "This professor already exists in the system, please enter a new prof to add only.");

                // make certain that the professor doesn't exist in db yet
            } else {
                String name= askForString("Enter their full name or type 'q' to quit", scanner);
                if (name.equals("q")) {
                    loop= false;
                    break;
                } else {
                    String username= askForString("Enter a username", scanner);
                    String password= askForString("Enter a password", scanner);

                    newProf= new Professor(username, password, ID, name);
                    loop= false;
                    return newProf;
                }
            }

        } while (loop);
        return null;
    }

    /** Asks the user for a number to log in to the system . Prompts again if the user input is not
     * a valid integer, or if the number is not between the start and end number, inclusive.
     *
     * @param scanner for user input
     * @param start   = the start number
     * @param end     = the end number
     * @return an integer indicating the type of user the user wants to log in with */
    private static int askForNum(String prompt, Scanner scanner, int start, int end) {

        // TODO Add your code here
        // create an array that is from 0 to 9
        ArrayList<Integer> array1= new ArrayList<>();
        for (int i= start; i < end + 1; i++ ) { array1.add(i); }

        int answer= -1;
        boolean keeptheloop2= true;
        // if the user answers something other than an integer/ the interger
        // is not between 0 to 9, inclusive, prompt the method again.
        System.out.println(prompt);
        do {
            System.out
                .println("Enter an integer between " + start + " - " + end + ", inclusively:");
            // if user entered an integer
            if (scanner.hasNextInt()) {
                // store that input
                int tempAnswer= scanner.nextInt();
                // if that integer is actually between the number
                if (!array1.contains(tempAnswer)) {
                    System.out.println("Your input is either out-of-bound or it is negative");
                } else {
                    answer= tempAnswer;
                    keeptheloop2= false;
                }
            } else {
                // catch for non-integer input
                System.out.println("Your input is not an integer.");
                scanner.next();
            }
        } while (keeptheloop2);
        return answer;

    }

    /** Asks the user for a String. Prompts again if the user input is not a valid String
     *
     * @param scanner for user input
     * @return a String answer */
    private static String askForString(String prompt, Scanner scanner) {
//        String answer= "";
        System.out.println(prompt);
        String answer= scanner.next().strip();
        return answer;

    }

//    /** Asks the user for a String. Prompts again if the user input is not a valid String
//     *
//     * @param scanner for user input
//     * @return a String answer */
//    private static String askForArrayList(String prompt, Scanner scanner) {
////       String answer= "";
//        System.out.println(prompt);
//        String answer= scanner.next().strip();
//        return answer;
//
//    }
}
