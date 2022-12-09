package roles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import roles.User;
import courses.Course;
import files.FileInfoReader;
import courses.Course;

/**
 * This tests the student function
 * @author Onsang Yau, Jingzhuo Hu
 *
 */

class StudentTest {

	Student student;
	FileInfoReader reader= new FileInfoReader();
	FileInfoReader reader2= new FileInfoReader();
	ArrayList<Course> courseInfo;
	ArrayList<Student> studentInfo;
	ArrayList<Course> courseList;

	@BeforeEach
	void setUp() throws Exception {
        //initialize professor
		courseList = new ArrayList<Course>();
		reader2.readFromStudentFile("studentInfo.txt");
		ArrayList<String> grade = reader2.getStudentObj().get(1).getGrades();
		student = new Student("0731"
				+ "01", "StudentName1", "testStudent01", "password590", grade);
		reader.readFromCourseFile("courseInfo.txt");
		courseInfo = reader.getCourseObj();
    }
	
	@Test
	void testaddCourse() {
		//add a course that doesn't in the courseList
		Course newCourse = new Course("CIT593", "Mathematical Foundations of Computer Science", "Clayton Greenberg", "TR", "10:00", "11:00", 72);
		courseList = student.addCourse(newCourse, courseInfo);
		assertFalse(student.getCourseList().contains(newCourse));
		
		//add a course that in the courseInfo
		courseList = student.addCourse(courseInfo.get(0), courseInfo);
		assertTrue(student.getCourseList().contains(courseInfo.get(0)));
		
		//add a course that already in coureList
		courseList = student.addCourse(courseInfo.get(0), courseInfo);
		assertTrue(student.getCourseList().contains(courseInfo.get(0)));
		
		//add a course that has time confliction
		courseList = student.addCourse(courseInfo.get(47), courseInfo);
		assertFalse(student.getCourseList().contains(courseInfo.get(47)));
	}
	
	@Test
	void getCourseList() {
		// test the empty course list
		assertFalse(student.getCourseList().contains(courseInfo.get(0)));
		
		//add a course that in the courseInfo
		courseList = student.addCourse(courseInfo.get(0), courseInfo);
		assertTrue(student.getCourseList().contains(courseInfo.get(0)));
	}
	
	
	@Test
	void testdropCourses() {
		System.out.println("testdropcourse");
		//test a course isn't in the schedule
		courseList = student.dropCourses(courseInfo.get(0));
		assertFalse(courseList.contains(courseInfo.get(0)));
		
		//test a course in the schedule
		courseList = student.addCourse(courseInfo.get(44), courseInfo);
		courseList = student.addCourse(courseInfo.get(0), courseInfo);
		courseList = student.dropCourses(courseInfo.get(0));
		assertTrue(courseList.contains(courseInfo.get(44)));
		assertFalse(courseList.contains(courseInfo.get(0)));
	}
}
