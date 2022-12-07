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

class ProfessorTest {

	Professor professor;
	Student student;
	FileInfoReader reader= new FileInfoReader();
	FileInfoReader reader2= new FileInfoReader();
	ArrayList<Course> courseInfo;
	ArrayList<Student> studentInfo;

	@BeforeEach
	void setUp() throws Exception {
        //initialize professor
		professor = new Professor("Greenberg", "password", "001", "Clayton Greenberg");
		reader.readFromCourseFile("courseInfo.txt");
		reader2.readFromStudentFile("studentInfo.txt");
		courseInfo = reader.getCourseObj();
		studentInfo = reader2.getStudentObj();
    }

	@Test
	void testgetGivenCourses() {
		System.out.println("testgetgivencourse");
		System.out.println(professor.getName());
		//test if the two courses that professor Clayton Greenberg teach have been included
		Course CIT592 = new Course("CIT592", "Mathematical Foundations of Computer Science", "Clayton Greenberg", "TR", "10:00", "11:00", 72);
		assertEquals(CIT592.getCourseCode(), professor.getGivenCourses(courseInfo).get(0).getCourseCode());
		
		Course CIS105 = new Course("CIS105", "Computational Data Exploration", "Clayton Greenberg", "MWF", "10:00", "11:00", 60);
		assertEquals(CIS105.getCourseCode(), professor.getGivenCourses(courseInfo).get(1).getCourseCode());
		
		//test if the course that professor Clayton Greenberg doesn't teach have been included
		Course CIT902 = new Course("CIT901", "Course", "new professor", "TR", "10:30", "11:30", 72);
		assertFalse(professor.getGivenCourses(courseInfo).contains(CIT902));
		
		System.out.println(professor.getUserName());
	}
	
	@Test
	void testgetStudentList() {
		//test the student list for an empty course CIT800
		Course CIT800 = new Course("CIT800", "Mathematical Foundations of Computer Science", "Clayton Greenberg", "TR", "10:00", "11:00", 72);
		assertFalse(professor.getStudentList(CIT800, studentInfo).contains(studentInfo.get(0)));
		
		//test the student list for a non-empty course 
		//ceate a new student
		student = studentInfo.get(0);
		//the student add the course
		student.addCourse(courseInfo.get(0), courseInfo);
		assertTrue(professor.getStudentList(courseInfo.get(0), studentInfo).contains(student));
	}

}
