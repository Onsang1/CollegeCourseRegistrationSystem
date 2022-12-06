package files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;

public class FileInfoReader {
    // initiate an ArrayList of Courses
    private ArrayList<Course> allCoursesObj= new ArrayList<>();
    // initiate an array of strings
//    private String[] allCoursesStr;
    // an ArrayList of Admin
    private ArrayList<Admin> allAdminObj= new ArrayList<>();
    // an ArrayList of Professor
    private ArrayList<Professor> allProfObj= new ArrayList<>();
    // an ArrayList of Student
    private ArrayList<Student> allStudentObj= new ArrayList<>();
    // an ArrayList of User
    private ArrayList<User> allUserObj= new ArrayList<>();

    /** Read all Student from the ProfInfo file
     *
     * @param fileName = name of file
     * @return an array of containing all the student obj */
    public void readFromStudentFile(String fileName) {
        try {
            populateStudent(allStudentObj, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Read all Prof from the ProfInfo file
     *
     * @param fileName = name of file
     * @return an array of containing all the admin obj */
    public void readFromProfFile(String fileName) {
        try {
            populateProf(allProfObj, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Read all admins from the adminInfo file
     *
     * @param fileName = name of file
     * @return an array of containing all the admin obj */
    public void readFromAdminFile(String fileName) {
        try {
            populateAdmin(allAdminObj, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Read all courses from the course Info file
     *
     * @param fileName = name of file
     * @return an array containing all the courses */
    public void readFromCourseFile(String fileName) {

//        allCoursesStr= new String[0];
        try {
//            int count= getCountOfLines(fileName);
//            allCoursesStr= new String[count];
            // populate both the arraylist and the array of string for later use
            populateCourses(allCoursesObj, fileName);
            // exception handling
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** populate the Course arraylist with read files
     *
     * @param c:         is any ArrayList of Course obj
     * @param inputFile: input file name
     * @throws IOException: input output exception */
    public void populateCourses(ArrayList<Course> c, String inputFile)
        throws IOException {

        // create buffer reader
        BufferedReader br= new BufferedReader(new FileReader(inputFile));
        // used to to populate String[] later
//        int index= 0;
        String line= "";
        while ((line= br.readLine()) != null) {
            // strip each line in case there are leading/ trailing white spaces
            line= line.strip();
            // if line is empty, skip it
            if (!line.isEmpty()) {
                // populate ArrayList c with a course obj made from each line of file
                c.add(makeACourse(line));
                // populate String[] with each line (strings)
//                CourseListString[index]= line;
//                index++ ;
            }
        }
        br.close();
    }

    /** populate the Admin arraylist with read files
     *
     * @param c:         is any ArrayList of Admin obj
     * @param inputFile: input file name
     * @throws IOException: input output exception */
    public void populateAdmin(ArrayList<Admin> c, String inputFile)
        throws IOException {

        // create buffer reader
        BufferedReader br= new BufferedReader(new FileReader(inputFile));
        // used to to populate String[] later
//       int index= 0;
        String line= "";
        while ((line= br.readLine()) != null) {
            // strip each line in case there are leading/ trailing white spaces
            line= line.strip();
            // if line is empty, skip it
            if (!line.isEmpty()) {
                // populate ArrayList c with an admin obj made from each line of file
                c.add(makeAAdmin(line));
            }
        }
        br.close();
    }

    /** populate the Prof arraylist with read files
     *
     * @param c:         is any ArrayList of Prof obj
     * @param inputFile: input file name
     * @throws IOException: input output exception */
    public void populateProf(ArrayList<Professor> c, String inputFile)
        throws IOException {

        // create buffer reader
        BufferedReader br= new BufferedReader(new FileReader(inputFile));
        // used to to populate String[] later
        String line= "";
        while ((line= br.readLine()) != null) {
            // strip each line in case there are leading/ trailing white spaces
            line= line.strip();
            // if line is empty, skip it
            if (!line.isEmpty()) {
                // populate ArrayList c with a prof obj made from each line of file
                c.add(makeAProf(line));
            }
        }
        br.close();
    }

    /** populate the Student arraylist with read files
     *
     * @param c:         is any ArrayList of Student obj
     * @param inputFile: input file name
     * @throws IOException: input output exception */
    public void populateStudent(ArrayList<Student> c, String inputFile)
        throws IOException {

        // create buffer reader
        BufferedReader br= new BufferedReader(new FileReader(inputFile));
        // used to to populate String[] later
        String line= "";
        while ((line= br.readLine()) != null) {
            // strip each line in case there are leading/ trailing white spaces
            line= line.strip();
            // if line is empty, skip it
            if (!line.isEmpty()) {
                // populate ArrayList c with a prof obj made from each line of file
                c.add(makeAStudent(line));
            }
        }
        br.close();
    }

    /** This function parse each line of info from courseinfo.txt and make a course obj
     *
     * @param line
     * @return a Course object */
    public Course makeACourse(String line) {
        // split the line by ";"
        String info[]= line.split(";");
        // assign each field of a course as below:
        String CourseCode= info[0].strip();
        String CourseName= info[1].strip();
        String ProfName= info[2].strip();
        String DayofClass= info[3].strip();
        String StartTime= info[4].strip();
        String EndTime= info[5].strip();
        int ClassSize= Integer.parseInt(info[6].strip());
        // make a course obj
        Course result;
        result= new Course(CourseCode, CourseName, ProfName, DayofClass, StartTime, EndTime,
            ClassSize);
        return result;
    }

    /** make an Admin obj
     *
     * @param line: a string that should be a line from the txt file
     * @return an Admin obj */
    public Admin makeAAdmin(String line) {
        String info[]= line.split(";");
        String ID= info[0].strip();
        String name= info[1].strip();
        String username= info[2].strip();
        String password= info[3].strip();
        // username, password, ID, name
        Admin administrator= new Admin(username, password, ID, name);
        return administrator;
    }

    /** make a Prof obj
     *
     * @param line: a string that should be a line from the txt file
     * @return a Prof obj */
    public Professor makeAProf(String line) {
        String info[]= line.split(";");
        String name= info[0].strip();
        String ID= info[1].strip();
        String username= info[2].strip();
        String password= info[3].strip();
        // username, password, ID, name
        Professor prof= new Professor(username, password, ID, name);
        return prof;
    }

    /** make a Student obj
     *
     * @param line: a string that should be a line from the txt file
     * @return a Student obj */
    public Student makeAStudent(String line) {
        String info[]= line.split(";");
        String ID= info[0].strip();
        String name= info[1].strip();
        String username= info[2].strip();
        String password= info[3].strip();
        String grades[]= info[4].strip().split(",");
        ArrayList<String> gradeList= new ArrayList<>();
        for (String i : grades) {
            gradeList.add(i.strip());
        }
        // username, password, ID, name
        Student student= new Student(username, password, ID, name, gradeList);
        return student;
    }

//    /** Get the count of lines in the given file
//     *
//     * @param inputFile to read
//     * @return count of (lines)
//     * @throws IOException if I/O error occurs */
//    private int getCountOfLines(String inputFile) throws IOException {
//
//        // create buffered reader
//        BufferedReader br= new BufferedReader(new FileReader(inputFile));
//
//        int count= 0;
//
//        String line= "";
//        while ((line= br.readLine()) != null) {
//            line= line.strip();
//            if (!line.isEmpty()) {
//                count++ ;
//            }
//        }
//
//        br.close();
//
//        return count;
//    }

    // Getter Methods
    /** return the arraylist with all courses in obj form
     *
     * @return */
    public ArrayList<Course> getCourseObj() {
        return allCoursesObj;
    }

    /** return the arraylist with all admins in obj form
     *
     * @return */
    public ArrayList<Admin> getAdminObj() {
        return allAdminObj;
    }

    /** return the arraylist with all Prof in obj form
     *
     * @return */
    public ArrayList<Professor> getProfObj() {
        return allProfObj;
    }

    /** return the arraylist with all Student in obj form
     *
     * @return */
    public ArrayList<Student> getStudentObj() {
        return allStudentObj;
    }

//    /** return the string array with all courses in string form
//     *
//     * @return */
//    public String[] getCourseStr() {
//        return allCoursesStr;
//    }

}
