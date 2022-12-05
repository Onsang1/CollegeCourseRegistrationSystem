package files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import courses.Course;

public class FileInfoReader {
    // initiate an ArrayList of Courses
    private ArrayList<Course> allCoursesObj= new ArrayList<>();
    // initiate an array of strings
    private String[] allCoursesStr;

    /** Read all courses from the course Info file
     *
     * @param fileName = name of file
     * @return an array containing all the courses */
    public void readFromCourseFile(String fileName) {

        allCoursesStr= new String[0];
        try {
            int count= getCountOfCourses(fileName);
            allCoursesStr= new String[count];
            // populate both the arraylist and the array of string for later use
            populateCourses(allCoursesObj, allCoursesStr, fileName);
            // exception handling
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateCourses(ArrayList<Course> c, String[] CourseListString, String inputFile)
        throws IOException {

        // create buffer reader
        BufferedReader br= new BufferedReader(new FileReader(inputFile));
        // used to to populate String[] later
        int index= 0;
        String line= "";
        while ((line= br.readLine()) != null) {
            // strip each line in case there are leading/ trailing white spaces
            line= line.strip();
            // if line is empty, skip it
            if (!line.isEmpty()) {
                // populate ArrayList c with a course obj made from each line of file
                c.add(makeACourse(line));
                // populate String[] with each line (strings)
                CourseListString[index]= line;
                index++ ;
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

    /** Get the count of lines in the given file
     *
     * @param inputFile to read
     * @return count of (lines)
     * @throws IOException if I/O error occurs */
    public int getCountOfCourses(String inputFile) throws IOException {

        // create buffered reader
        BufferedReader br= new BufferedReader(new FileReader(inputFile));

        int count= 0;

        String line= "";
        while ((line= br.readLine()) != null) {
            line= line.strip();
            if (!line.isEmpty()) {
                count++ ;
            }
        }

        br.close();

        return count;
    }

    // Getter Methods
    /** return the arraylist with all courses in obj form
     *
     * @return */
    public ArrayList<Course> getCourseObj() {
        return allCoursesObj;
    }

    /** return the string array with all courses in string form
     *
     * @return */
    public String[] getCourseStr() {
        return allCoursesStr;
    }
}
