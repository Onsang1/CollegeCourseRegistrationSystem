import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;
import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;

/** @author Onsang Yau, Chelsea Hu */

public class Controller {
    /** This is the main function of this class, responsible for running the program
     *
     * @param args */
    public static void main(String[] args) {
        Controller controller= new Controller();
        FileInfoReader reader= new FileInfoReader();
        reader.readFromStudentFile("studentInfo.txt");
        reader.readFromAdminFile("adminInfo.txt");
        reader.readFromProfFile("profInfo.txt");
        reader.readFromCourseFile("courseInfo.txt");
        controller.run(reader);
    }

    /** This is the function that display the management main menu and calls different functions
     * according to login user type
     *
     * @param reader is a FileInfoReader type reader */
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
                    StudentLogin(scanner, reader);
                    // if user is a professor
                } else if (LoginType == 2) {
                    ProfessorLogin(scanner, reader);
                    // if user is an admin
                } else if (LoginType == 3) {
                    AdminLogin(scanner, reader);
                }
                // if user want to log off the system
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
        if (username.toLowerCase().equals("q")) { return false; }
        // store password
        String password= askForString("Please enter your password, or type 'q' to quit", scanner);
        if (password.toLowerCase().equals("q")) { return false; }

        // find our Professor object
        Professor ourProf= checkProfLogin(reader.getProfObj(), username, password);
        // if professor exists
        if (ourProf != null) {
            boolean loopContinues= true;
            while (loopContinues) {
                printMenus(3, username);
                // this is the type of action that user does
                int answer= askForNum("Please enter your option, e.g. '1'.", scanner, 1, 3);
                if (answer != 3) {
                    // 1 -- View all courses
                    if (answer == 1) {
                        for (Course c : ourProf.getGivenCourses(reader.getCourseObj())) {
                            System.out.println(c.print());
                        }
                        // 2 -- view student list of the given course
                    } else if (answer == 2) {
                        boolean enterID= true;
                        // prompt the user until they quit
                        do {
                            String CourseID= askForString(
                                "Please enter the CourseID. e.g. CIT590, or type 'q' to quit",
                                scanner);
                            if (CourseID.equals("q") || CourseID.equals("Q")) { break; }
                            Course courseToView= checkCourseToAdd(reader.getCourseObj(), CourseID);
                            // if the prof entered a course with valid course code
                            if (CourseID != null) {
                                // then we print out all the students of the given course
                                ArrayList<Student> students= ourProf.getStudentList(courseToView,
                                    reader.getStudentObj());
                                enterID= false;
                            }
                        } while (enterID);

                    }

                    // if answer == 3: quit to main menu
                } else {
                    loopContinues= false;
                }
            }
            // if the student entered wrong combination of username and passwords, reprompt them
        } else {
            System.out
                .println("Your username and password combination is wrong. Please try again!");
            return ProfessorLogin(scanner, reader);
        }
        return false;
    }

    /** This is the main function that controls the login flow of Admin
     *
     * @param scanner: a Scanner object
     * @param reader:  a FileInfoReader object
     * @return: a boolean */
    private static boolean AdminLogin(Scanner scanner, FileInfoReader reader) {

        // Store username
        String username= askForString("Please enter your username, or type 'q' to quit",
            scanner);
        if (username.toLowerCase().equals("q")) { return false; }
        // store password
        String password= askForString("Please enter your password, or type 'q' to quit",
            scanner);
        if (password.toLowerCase().equals("q")) { return false; }
        // establish our admin obj
        Admin ourAdmin= checkAdminLogin(reader.getAdminObj(), username, password);
        // if password and username are corret
        if (ourAdmin != null) {
            boolean loopcontinues= true;
            while (loopcontinues) {

                printMenus(2, username);
                // this stores the action that the user wants to perform
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
                        if (CourseID.toLowerCase().equals("q")) { continue; }
                        // stores the name of the new course
                        String name= askForString(
                            "Please enter the course name, or type 'q' to quit. e.g. 'Java' ",
                            scanner);
                        if (name.toLowerCase().equals("q")) { continue; }
                        // stores the start time of the new course
                        String startTime= askForString(
                            "Please enter the course start time, or type 'q' to quit. e.g. '20:00'",
                            scanner);
                        if (startTime.toLowerCase().equals("q")) { continue; }
                        // stores the end time of the new course
                        String endTime= askForString(
                            "Please enter the course end time, or type 'q' to quit. e.g. '21:00'",
                            scanner);
                        if (endTime.toLowerCase().equals("q")) { continue; }
                        // stores the date of the new course
                        String date= askForString(
                            "Please enter the course's day of week, or type 'q' to quit. e.g. 'MWF'",
                            scanner);
                        if (date.toLowerCase().equals("q")) { continue; }
                        // change date to upper case
                        date= date.toUpperCase();
                        // stores the capacity of the new course
                        String capacity= askForString(
                            "Please enter the course capacity, or type 'q' to quit. e.g. '72'",
                            scanner);
                        if (capacity.toLowerCase().equals("q")) { continue; }
                        // change capacity from String to an integer
                        int classSize= Integer.parseInt(capacity);
                        // stores the id of the lecturer of the main course
                        String lecturerId= askForString(
                            "Please enter the course lecturer's id, or type 'q' to quit. e.g. '001'",
                            scanner);
                        if (lecturerId.toLowerCase().equals("q")) { continue; }

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
                            // if admin chooses to quit, we return to the admin menu
                            if (newProf == null) {
                                continue;
                                //
                            } else {
                                Course courseToAdd2= new Course(CourseID, name, newProf.getName(),
                                    date,
                                    startTime, endTime, classSize);
                                // professor exists but doesn't have time conflict; can add course
                                if (!newProf.timeConflict(courseToAdd2,
                                    newProf.getGivenCourses(reader.getCourseObj()))) {
                                    System.out.println("Successfully added the professor " +
                                        newProf.getID() + " " + newProf.getName());
                                    ourAdmin.addNewCourse(courseToAdd2, reader.getCourseObj(), true,
                                        false);

                                    // professor exists but has time conflict
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
                            // stores the id of the course that the user wants to delete
                            String courseIDToDelete= askForString(
                                "Please enter the course code that you want to delete, or type 'q' to quit. e.g. 'CIS550' ",
                                scanner);
                            if (courseIDToDelete.toLowerCase().equals("q")) { break; }
                            // check if the course exists in db
                            Course courseToDelete= checkCourseToAdd(reader.getCourseObj(),
                                courseIDToDelete);
                            // if the course the admin wants to delete is valid
                            if (courseToDelete != null) {
                                // delete the course
                                ourAdmin.deleteCourse(courseToDelete, reader.getCourseObj());
                                loopContinues= false;
                                // if course obj == null, then it means it doesn't exist in db
                            } else {
                                System.out.println(
                                    "The course you are trying to delete is not in the database. try again!!!");
                            }

                        } while (loopContinues);
                        // 5 -- delete professor
                    } else if (answer == 5) {
                        boolean loopContinues= true;
                        do {
                            // stores the id of the professor that user wants to delete
                            String ProfIDToDelete= askForString(
                                "Please enter the ID of the professor that you want to delete, or type 'q' to quit. e.g. 'CIS550' ",
                                scanner);
                            if (ProfIDToDelete.toLowerCase().equals("q")) { break; }
                            // check if the prof is in db; null means it is not in db
                            Professor profToDelete= getProfByID(reader.getProfObj(),
                                ProfIDToDelete);
                            // if the professor is in the database
                            if (profToDelete != null) {
                                // delete the professor
                                ourAdmin.deleteProfessor(profToDelete, reader.getProfObj());
                                loopContinues= false;
                                // if prof doesn't exist, prompt the user again
                            } else {
                                System.out.println(
                                    "The professor you are trying to delete is not in the database. try again!!!");
                            }

                        } while (loopContinues);
                        // 4 -- add a professor
                    } else if (answer == 4) {
                        boolean loopContinues= true;
                        do {
                            // stores the id of the professor that the user wants to add
                            String ProfIDToAdd= askForString(
                                "Please enter the ID of the professor that you want to add, or type 'q' to quit. e.g. '001' ",
                                scanner);
                            if (ProfIDToAdd.equals("q") || ProfIDToAdd.equals("Q")) { break; }
                            // check if the object returned by the function is null or not
                            Professor checkExist= getProfByID(reader.getProfObj(),
                                ProfIDToAdd);
                            // if the professor is in the database means that the prof obj is not
                            // null
                            if (checkExist == null) {
                                // stores the new prof's name
                                String profName= askForString(
                                    "Please enter the name of the professor that you want to , or type 'q' to quit. e.g. '001' ",
                                    scanner);
                                if (profName.equals("q") || profName.equals("Q")) { break; }
                                // stores the username of the new prof
                                String profUsername= askForString(
                                    "Please enter the username of the professor that you want to , or type 'q' to quit. e.g. '001' ",
                                    scanner);
                                if (profUsername.equals("q") || profUsername.equals("Q")) { break; }
                                // if username already exists, prompt again
                                if (getProfByUsername(reader.getProfObj(), profUsername) != null) {
                                    System.out.println(
                                        "Sorry, but the username and password combination already exists in our database, please re-enter the information.");
                                    continue;
                                }
                                // stores the password of the new prof
                                String profPassword= askForString(
                                    "Please enter the password of the professor that you want to , or type 'q' to quit. e.g. '001' ",
                                    scanner);
                                if (profPassword.equals("q") || profPassword.equals("Q")) { break; }

                                // make a new professor
                                Professor ProfToAdd= new Professor(profUsername, profPassword,
                                    ProfIDToAdd, profName);
                                // add the professor
                                ourAdmin.addProfessor(ProfToAdd, reader.getProfObj());
                                loopContinues= false;

                                // if professor already exists, prompt the user again
                            } else {
                                System.out.println(
                                    "The professor you are trying to add is already in the db. try again!!!");
                            }

                        } while (loopContinues);
                        // add a new student
                    } else if (answer == 6) {
                        boolean loopContinues= true;
                        do {

                            // ask for student ID
                            String StudentToAdd= askForString(
                                "Please enter the ID of the student that you want to add, or type 'q' to quit. e.g. '001' ",
                                scanner);
                            if (StudentToAdd.toLowerCase().equals("q")) { break; }
                            // check if the ID already exists
                            Student checkNotExist= getStudentByID(reader.getStudentObj(),
                                StudentToAdd);
                            // if the ID already exists, prompt the user again
                            if (checkNotExist != null) {
                                System.out.println(
                                    "The student with this ID already exist. please try again !!!");
                                continue;
                            }
                            // if the ID doesn't exist

                            // ask for username
                            String StudentUsername= askForString(
                                "Please enter the student's username, or type 'q' to quit. e.g. 'studnet1'",
                                scanner);
                            if (StudentUsername.toLowerCase().equals("q")) { break; }

                            // check if username and password combination already exists in db, if
                            // so re-prompt the user
                            if (getStudentByUsername(reader.getStudentObj(),
                                StudentUsername) != null) {
                                System.out.println(
                                    "The student with this username  already exist. please try again !!!");
                                continue;
                            }
                            // ask for password
                            String StudentPassword= askForString(
                                "Please enter the student's password, or type 'q' to quit. e.g. '12344444'",
                                scanner);
                            if (StudentPassword.toLowerCase().equals("q")) { break; }

                            // ask for full name
                            String StudentName= askForString(
                                "Please enter the student's full name, or type 'q' to quit. e.g. 'Stephen straww'",
                                scanner);
                            if (StudentName.toLowerCase().equals("q")) { continue; }

                            // ask for grade in ArrayList of String
                            ArrayList<String> grades= CreateGrades(reader, scanner);
                            Student AdminChoice5= new Student(StudentUsername, StudentPassword,
                                StudentToAdd,
                                StudentName, grades);
                            ourAdmin.addStudent(AdminChoice5, reader.getStudentObj());
                            System.out
                                .println("The newly added student is " + AdminChoice5.print());
                            loopContinues= false;
                            // if not, prompt the user again
                        }

                        while (loopContinues);

                        // answer == 7, Delete a student
                    } else if (answer == 7) {
                        boolean loopContinues= true;
                        do {
                            // stores the id of the student that admin wants to delete
                            String StudentIdDelete= askForString(
                                "Please enter the ID of the student you want to delete. e.g. '001' or press 'q' to exit",
                                scanner);
                            if (StudentIdDelete.toLowerCase().equals("q")) { break; }
                            // check if student is in db or not
                            Student studentToDelete= getStudentByID(reader.getStudentObj(),
                                StudentIdDelete);
                            // if the student is in the database
                            if (studentToDelete != null) {
                                // perform the delete a student action
                                ourAdmin.deleteStudent(studentToDelete, reader.getStudentObj());
                                loopContinues= false;
                                // if student not in db, prompt the user again
                            } else {
                                System.out.println(
                                    "The student you are trying to delete is not in the database. try again!!!");
                            }

                        } while (loopContinues);

                    }

                    // answer == 8
                } else {
                    // this is for the biggest while loop
                    loopcontinues= false;
                }

            }

            // if ourAdmin == null
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
        ArrayList<String> output= new ArrayList<>();
        do {
            // stores the id of the course that the admin wants to add grades for
            System.out.println(
                "Please enter the ID of a course which this student already took, one in a time. Type 'q' to quit, type 'n' to stop adding.");
            String CourseID= scanner.next().strip().toUpperCase();
            if (CourseID.toLowerCase().equals("q")) {
                break;
            } else if (CourseID.toLowerCase().equals("n")) {
                break;
            }
            // check if course is currently in db
            if (getCourseByID(reader.getCourseObj(), CourseID.toUpperCase().strip()) == null) {
                System.out.println("Sorry, but this course doesn't exist in our db, ");
                continue;
            }
            // stores the grade of the course
            String CourseGrade= askForString(
                "Please enter the CourseGrade of a course which this student already took, one in a time. Type 'q' to quit, type 'n' to stop adding.",
                scanner);
            if (CourseGrade.equals("q") || CourseGrade.equals("n")) { break; }

            String Grade= CourseID + ": " + CourseGrade;
            output.add(Grade);

        } while (loop);
        return output;
    }

    /** This function returns a boolean that shows whether the student with the username and
     * password exists in our db
     *
     * @param scanner: a Scanner object
     * @param reader:  a FileInfoReader obj
     * @return: a boolean; true if exists, vice versa. */
    private static boolean StudentLogin(Scanner scanner, FileInfoReader reader) {
        // store username
        String username= askForString("Please enter your username, or type 'q' to quit", scanner);
        if (username.toLowerCase().equals("q")) { return false; }
        // store password
        String password= askForString("Please enter your password, or type 'q' to quit", scanner);
        if (password.toLowerCase().equals("q")) { return false; }

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
                        Course course= StudentaddCourses(ourStudent, scanner, reader);
//                        System.out.println("The courses in your list are: ");// TODO

                        // 3 -- View enrolled courses
                    } else if (answer == 3) {
                        for (Course c : ourStudent.getCourseList()) {
                            System.out.println(c.print());
                        }
                        // 4 -- Drop courses in your list
                    } else if (answer == 4) {
                        StudentdeleteCourses(ourStudent, scanner, reader);
                        for (Course s : ourStudent.getCourseList()) {
                            System.out.println(s.print());
                        }

                        // 5 -- View grades
                    } else if (answer == 5) {
                        System.out.println(
                            "Here are the courses you have already taken, with the grades in a letter format: ");
                        for (String s : ourStudent.getGrades()) {
                            System.out.println(s);
                        }
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
            // professor
        } else if (type == 3) {
            System.out.println("1 -- View given courses");
            System.out.println("2 -- View student list of the given courses");
            System.out.println("3 -- Return to the previous menu");

        }
    }

    /** This function check if the professor with the username and password exist
     *
     * @param list:     an arraylist of Professor
     * @param username: a String
     * @param password: a String
     * @return a Professor obj, can be null */
    private static Professor checkProfLogin(ArrayList<Professor> list, String username,
        String password) {
        // loop through the entire list to check if any object's username and password matches the
        // input username and password
        for (Professor s : list) {
            if (s.getUserName().equals(username) && s.getPassword().equals(password)) { return s; }
        }
        return null;
    }

    /** get a Professor object by Username
     *
     * @param list:     an ArrayList of Professor
     * @param username: a string of username
     * @return a Professor object */
    private static Professor getProfByUsername(ArrayList<Professor> list, String username) {
        // loop through the entire list to check if any object's username and password matches the
        // input username and password
        for (Professor s : list) {
            if (s.getUserName().toLowerCase().equals(username.toLowerCase())) { return s; }
        }
        return null;
    }

    /** This function gets the Prof with the given id
     *
     * @param list:     an arraylist of professor
     * @param professor ID
     * @return a professor obj */
    private static Professor getProfByID(ArrayList<Professor> list, String ID) {
        // loop through the entire list to check if any object's id matches the
        // input id
        for (Professor s : list) {
            if (s.getID().toLowerCase().equals(ID.toLowerCase())) { return s; }
        }
        return null;
    }

    /** This function gets the Student with the given id
     *
     * @param list:   an arraylist of Student
     * @param student ID
     * @return a student obj, can be null */
    private static Student getStudentByID(ArrayList<Student> list, String ID) {
        // loop through the entire list to check if any object's id matches the
        // input id
        for (Student s : list) {
            if (s.getID().equals(ID)) { return s; }
        }
        return null;
    }

    /** This function gets the Course with the given id
     *
     * @param list:  an arraylist of Course
     * @param Course ID
     * @return a Course obj */
    private static Course getCourseByID(ArrayList<Course> list, String ID) {
        // loop through the entire list to check if any object's id matches the
        // input id
        for (Course s : list) {
            if (s.getCourseCode().equals(ID)) { return s; }
        }
        return null;
    }

    /** This function check if the admin with the username and password exist
     *
     * @param list:     an arraylist of Admin
     * @param username: String
     * @param password: String
     * @return an Admin obj, can be null */
    private static Admin checkAdminLogin(ArrayList<Admin> list, String username,
        String password) {
        // loop through the entire list to check if any object's username and password matches the
        // input combination
        for (Admin a : list) {
            if (a.getUserName().equals(username) && a.getPassword().equals(password)) { return a; }
        }
        return null;
    }

    /** This function check if the student with the username and password exist
     *
     * @param list:     an ArrayList of Student
     * @param username: String
     * @param password: String
     * @return: a Student obj, can be null */
    private static Student checkStudentLogin(ArrayList<Student> list, String username,
        String password) {
        // loop through the entire list to check if any object's username and password matches the
        // input combination
        for (Student s : list) {
            if (s.getUserName().equals(username) && s.getPassword().equals(password)) { return s; }
        }
        return null;
    }

    /** get a Student object by Username
     *
     * @param list:     an ArrayList of Student
     * @param username: a string of username
     * @return a Student object */
    private static Student getStudentByUsername(ArrayList<Student> list, String username) {
        // loop through the entire list to check if any object's username matches the
        // input username
        for (Student s : list) {
            if (s.getUserName().toLowerCase().equals(username.toLowerCase())) { return s; }
        }
        return null;
    }

    /** check if the professor is already in the profInfo
     *
     * @param Id:       String
     * @param profInfo: an ArrayList of Professor
     * @return a boolean, true if prof exists, false otherwise */
    private static boolean professorExists(String Id, ArrayList<Professor> profInfo) {
        // loop through the entire list to check if any object's id matches the
        // input id
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
        // loop through the entire list to check if any object's id matches the
        // input id
        for (Course c : course) {
            if (c.getCourseCode().equals(courseCode.strip().toUpperCase())) { return c; }
        }
        return null;
    }

    /** do the action of adding a course that user selected to the user's cart
     *
     * @param student: a Student object
     * @param scanner: a scanner
     * @param reader:  the fileinforeader
     * @return: a Course object */
    private static Course StudentaddCourses(Student student, Scanner scanner,
        FileInfoReader reader) {

        boolean keeptheloop2= true;
        System.out.println("Which course do you want to add to your cart?");

        do {
            // stores the course code
            String addCourse= askForString(
                "Please enter the course code that you want to add; if you want to quit press 'q'",
                scanner);
            // quitting the system
            if (addCourse.equals("q") || addCourse.equals("Q")) {
                break;
            }
            // change all input to uppercase
            addCourse= addCourse.toUpperCase();
            // find the corresponding course obj, can be null
            Course courseToAdd= checkCourseToAdd(reader.getCourseObj(), addCourse);
            // check if the course code is in current database
            if (courseToAdd == null) {
                // means the course doesn't exist
                System.out
                    .println(
                        "The course code you entered is wrong/ not a valid course in the current database. Please try again!");
                continue;
                // if course exists, check if the course has conflict with other courses in the
                // student's cart
            } else {
                if (checkCourseToAdd(student.getCourseList(),
                    courseToAdd.getCourseCode()) != null) {
                    System.out.println("This course already exist in your cart. Try again!");
                    continue;
                }
                // if course has time conflict with other course in cart, reprompt the user
                else if (student.timeConflict(courseToAdd, student.getCourseList())) {
                    System.out.println("The course you selected has time conflict");
                    continue;
                    // if no conflict, add the course
                } else {
                    student.addCourse(courseToAdd, reader.getCourseObj());
                    System.out.println(
                        "You have successfully added " + courseToAdd.getCourseCode() +
                            " in your cart!");
                    keeptheloop2= false;
                    return courseToAdd;
                }
            }

        } while (keeptheloop2);
        return null;
    }

    /** help the student to delete the course
     *
     * @param: student is a Student object
     * @param: scanner is a Scanner
     * @param reader is a FileInfoReader */
    private static void StudentdeleteCourses(Student student, Scanner scanner,
        FileInfoReader reader) {
        boolean loop= true;
        do {
            // store the id of the course that the student wants to delete from cart
            String courseIDToDelete= askForString(
                "Please enter the course code that you want to delete, or type 'q' to quit. e.g. 'CIS550' ",
                scanner);
            if (courseIDToDelete.toLowerCase().equals("q")) { break; }
            Course courseToDelete= checkCourseToAdd(student.getCourseList(),
                courseIDToDelete);
            // if the course the student wants to delete is valid
            if (courseToDelete != null) {
                loop= false;
                // delete the course
                student.getCourseList().remove(courseToDelete);
                System.out.println(
                    "You have successfully deleted courrse " + courseToDelete.getCourseCode());
                System.out.println("Your course list (cart) is now: ");

            } else {
                System.out.println(
                    "The course you are trying to delete is not in your cart. try again!!!");
            }

        } while (loop);
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
            // stores the course code that admin wants to add
            answer= askForString(prompt, scanner);
            if (answer.toLowerCase().equals("q")) loop= false;
            // if answer represents an existing course, then checkCourseToAdd == a real Course
            // object

            else if (checkCourseToAdd(reader.getCourseObj(), answer.strip()) != null) {
                System.out.println(
                    "Sorry, the course with the ID already exists in our database. Please enter a new course only.");
            } else {
                loop= false;
            }

        } while (loop);
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
        do {
            System.out.println(
                "This professor hasn't been established in our system yet. Let's create the professor.");
            // stores the ID of the prof that admin wants to create
            ID= askForString("Please enter the professor's ID, or type 'q' to quit. ", scanner);
            if (ID.toLowerCase().equals("q")) {
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
                if (name.toLowerCase().equals("q")) {
                    loop= false;
                    break;
                } else {
                    // continue stores a username of the new prof
                    String username= askForString("Enter a username", scanner);
                    // check if username already exists
                    if (getProfByUsername(reader.getProfObj(), username) != null) {
                        System.out.println(
                            "Sorry, but this username already exists in our db, please try again!");
                        continue;
                    }
                    // if username doesn't exist yet, prompt for password
                    String password= askForString("Enter a password", scanner);
                    // create the new prof finally
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
     * @param: prompt: some String to be printed out in the console to ask user
     * @param scanner for user input
     * @return a String answer that is stripped */
    private static String askForString(String prompt, Scanner scanner) {
        System.out.println(prompt);
        // stores the answer of the user to the prompt in a variable
        String answer= scanner.next();
        // strip the answer
        answer= answer.strip();
        return answer;

    }

}
