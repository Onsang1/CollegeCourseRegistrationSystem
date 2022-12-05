package files;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileInfoReader {
    
    /**
     * Read all courses from the course Info file
     * @param fileName = name of file
     * @return an array containing all the courses
     */
    public ArrayList<Course> readFromCourseFile(String fileName) {
        ArrayList<Course> allCourses = new ArrayList<Course>();
        try {
            int count = this.getCountOfCourses(filename);
//            allCourses = new String[count];
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
