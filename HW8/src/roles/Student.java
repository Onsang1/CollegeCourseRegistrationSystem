package roles;

import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;

/** This class describes a ship of length 4 */
public class Student extends User {
    private static final String USERTYPE= "Student";

    private ArrayList<String> Grades= new ArrayList<>();
    private ArrayList<Course> courseList= new ArrayList<>();

    // the grades in a hashmap
    private HashMap<String, String> GradeMap= new HashMap<>();

    // create an object of course
    Course course;

    /** constructor for Student
     *
     * @param username
     * @param password
     * @param ID
     * @param name */
    public Student(String username, String password, String ID, String name,
        ArrayList<String> grade) {
        super(username, password, ID, name);
        Grades= grade;
        setGradeMap(Grades);
    }

    /** return the user type in String */
    @Override
    public String getUserType() {
        return USERTYPE;
    }

    /** return a string form of printout */
    @Override
    public String print() {
        String output= getID() + "; " + getName() + "; " + getUserName() + "; " + getPassword() +
            "; ";
        for (String s : Grades) {
            output= output + s;
            output+= ", ";
        }
        return output;
    }

    // SETTER METHODS
    /** set the student's grade in ArrayList */
    public void setGrades(ArrayList<String> x) {
        Grades= x;
    }

    private void setGradeMap(ArrayList<String> x) {
        for (String s : Grades) {
            String info[]= s.split(":");
            GradeMap.put(info[0].strip(), info[1].strip());
        }
    }

    // GETTER METHODS
    /** get the student's grade (in ArrayList) */
    public ArrayList<String> getGrades() {
        return Grades;
    }

    /** get the student's grade (in HashMap) */
    public HashMap<String, String> getGradesInMap() {
        setGradeMap(Grades);
        return GradeMap;
    }

    /** get the course object according to the courseID
     *
     * @param courseID
     * @param courseInfo
     * @return */
    public Course getCourseInfo(String courseID, ArrayList<Course> courseInfo) {
        for (int i= 0; i < courseInfo.size(); i++ ) {
            if (courseID.toLowerCase().trim()
                .equals(courseInfo.get(i).getCourseCode().toLowerCase().trim())) {
                course= courseInfo.get(i);
            }
        }
        return course;
    }

    /** Add courses to courseList
     *
     * @param course
     * @param courseInfo
     * @return courseList */
    public ArrayList<Course> addCourse(Course course, ArrayList<Course> courseInfo) {
        Boolean hasConflict= timeConflict(course, getCourseList());
        // check if new course is valid
        if (!courseInfo.contains(course)) {
            System.out.println("The course you selected does not exist");
            return courseList;
        } else if (courseList.contains(course)) {
            // check if new course is already in the courseList
            System.out.println("The course you selected already exists");
            return courseList;
        } else if (hasConflict) {
            // check if time conflict
            System.out.println("The course you selected has time conflict");
            return courseList;
        }
        courseList.add(course);
        System.out.println("The course in your list:");
        System.out.println(courseList);
        return courseList;

    }

    /** View selected courses
     *
     * @return selected course list */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    /** Drop courses in your list
     *
     * @param course name
     * @return course list */
    public ArrayList<Course> dropCourses(Course course) {
        if (getCourseList().contains(course)) {
            getCourseList().remove(course);
            System.out.println("Course dropped successfully");
        } else {
            System.out.println("The course isn't in your schedule");
        }
        return getCourseList();
    }

    /** view grades
     *
     * @param student name
     * @return grades */
    public String getGrades(ArrayList<Student> studentInfo) {
        String x= null;
        getGradesInMap();
        System.out
            .println("Here are the courses you already taken, with your grade in letter format");
        for (String course : getGradesInMap().keySet()) {
            System.out.println("Grade for " + course + ": " + getGradesInMap().get(course));
        }
        return x;
    }
}
