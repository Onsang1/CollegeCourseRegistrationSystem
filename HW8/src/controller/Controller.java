
/** @author Onsang Yau, Abd */

import courses.Course;
import files.FileInfoReader;

public class Controller {

    public static void main(String[] args) {

        FileInfoReader reader= new FileInfoReader();
        // randomly place 10 ships in ocean, 1 battleship, 2 cruiser, 3 destroyer, and 4 submarines
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
