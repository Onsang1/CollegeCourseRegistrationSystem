
/** @author Onsang Yau,  */

import files.FileInfoReader;
import roles.Admin;

public class Controller {

    public static void main(String[] args) {

        // way of printing courses
        FileInfoReader reader= new FileInfoReader();
        reader.readFromCourseFile("courseInfo.txt");
//        for (Course c : reader.getCourseObj()) {
//            System.out.println(c.print());
//        }

        // way of printing students
        FileInfoReader reader2= new FileInfoReader();
        // randomly place 10 ships in ocean, 1 battleship, 2 cruiser, 3 destroyer, and 4 submarines
        reader2.readFromStudentFile("studentInfo.txt");
//        for (Student s : reader2.getStudentObj()) {
//            System.out.println(s.print());
//        }

        // way of printing prof
        FileInfoReader reader3= new FileInfoReader();
        // randomly place 10 ships in ocean, 1 battleship, 2 cruiser, 3 destroyer, and 4 submarines
        reader3.readFromProfFile("profInfo.txt");
//        for (Professor p : reader3.getProfObj()) {
//            System.out.println(p.print());
//        }

        // way of printing prof
        FileInfoReader reader4= new FileInfoReader();
        // randomly place 10 ships in ocean, 1 battleship, 2 cruiser, 3 destroyer, and 4 submarines
        reader4.readFromAdminFile("adminInfo.txt");
        for (Admin a : reader4.getAdminObj()) {
            System.out.println(a.print());
        }

    }
}
