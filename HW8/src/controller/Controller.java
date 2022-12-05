
/** @author Onsang Yau, Abd */

import courses.Course;
import files.FileInfoReader;

public class Controller {

    public static void main(String[] args) {

        FileInfoReader reader= new FileInfoReader();

        reader.readFromCourseFile("courseInfo.txt");
        String[] result= reader.getCourseStr();
//        for (String a : result) {
//            System.out.println(a);
//        }
        for (Course c : reader.getCourseObj()) {
            System.out.println(c.print());
        }

    }
}
