
/** @author Onsang Yau, Abd */

import files.FileInfoReader;

public class Controller {

    public static void main(String[] args) {

        // way of printing courses
        FileInfoReader reader= new FileInfoReader();
        reader.readFromCourseFile("courseInfo.txt");
//        for (Course c : reader.getCourseObj()) {
//            System.out.println(c.print());
//        }
        System.out.println(reader.getCourseObj().get(0).getStartTimeinNum());
        System.out.println(reader.getCourseObj().get(0).getEndTimeinNum());
        System.out.println(reader.getCourseObj().get(0).getDuration());

        // way of printing students
        FileInfoReader reader2= new FileInfoReader();
        reader2.readFromStudentFile("studentInfo.txt");
//        for (Student s : reader2.getStudentObj()) {
//            System.out.println(s.print());
//        }
        System.out.println(reader2.getStudentObj().get(1).getGrades());
        // return a set view of keys contained in the map
        System.out.println(reader2.getStudentObj().get(1).getGradesInMap().keySet());

        // way of printing prof
        FileInfoReader reader3= new FileInfoReader();
        reader3.readFromProfFile("profInfo.txt");
//        for (Professor p : reader3.getProfObj()) {
//            System.out.println(p.print());
//        }

        // way of printing prof
        FileInfoReader reader4= new FileInfoReader();
        reader4.readFromAdminFile("adminInfo.txt");
//        for (Admin a : reader4.getAdminObj()) {
//            System.out.println(a.print());
//        }

    }
}
