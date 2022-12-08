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

class UserTest {

	User user;
	FileInfoReader reader= new FileInfoReader();
	FileInfoReader reader2= new FileInfoReader();
	FileInfoReader reader3= new FileInfoReader();
	ArrayList<Course> courseInfo;
	ArrayList<Student> studentInfo;
	ArrayList<Professor> profInfo;

	@BeforeEach
	void setUp() throws Exception {
        //initialize professor
		user = new Professor("Clayton Greenberg", "001", "Greenberg", "password590");
		reader.readFromCourseFile("courseInfo.txt");
		reader2.readFromStudentFile("studentInfo.txt");
		reader3.readFromProfFile("profInfo.txt");
		courseInfo = reader.getCourseObj();
		studentInfo = reader2.getStudentObj();
		profInfo = reader3.getProfObj();
    }
	
	@Test
	void testviewAllCourses() {
		//test if the output include the first element in the courseInfo
		Course courseTestFirst = courseInfo.get(0);
		assertTrue(user.viewAllCourses(courseInfo).contains(courseTestFirst));
		
		//test if the output include the 20th element in the courseInfo
		Course courseTestSecond = courseInfo.get(20);
		assertTrue(user.viewAllCourses(courseInfo).contains(courseTestSecond));
		
		//test if a course not in the courseInfo exists
		Course courseTestThird = new Course("CIT592", "Mathematical", "Clayton Greenberg", "TR", "10:00", "11:00", 72);
		assertFalse(user.viewAllCourses(courseInfo).contains(courseTestThird));
	}
	
	@Test 
	void testtimeConflict() {
		System.out.println("testtimeconflict");
		// a course that has time conflict with the courseInfo
		Course CIT900 = new Course("CIT900", "Course", "Clayton Greenberg", "MW", "16:00", "18:00", 72);
		assertTrue(user.timeConflict(CIT900, courseInfo));
		
		// a course that doesn't have time conflict
		ArrayList<Course> courseInfo2 = new ArrayList<Course>();
		Course CIT901 = new Course("CIT901", "Course", "Clayton Greenberg", "TR", "10:30", "11:30", 72);
		courseInfo2.add(CIT901);
		assertFalse(user.timeConflict(CIT900, courseInfo2));
		Course CIT800 = new Course("CIT800", "Course", "Clayton Greenberg", "TR", "10:30", "11:30", 72);
		//courseInfo2.add(CIT800);
		assertTrue(user.timeConflict(CIT800, courseInfo2));
	}
	
	@Test
	void timeConflictCourse () {
		//a course that doesn't have time conflict
		ArrayList<Course> courseInfo3 = new ArrayList<Course>();
		Course CIT980 = new Course("CIT980", "Course", "Clayton Greenberg", "F", "10:30", "11:30", 72);
		Course CIT901 = new Course("CIT901", "Course", "Clayton Greenberg", "TR", "10:30", "11:30", 72);
		courseInfo3.add(CIT901);
		assertEquals(null, user.timeConflictCourse(CIT980, courseInfo3));
		
		//a course that have time conflict
		Course CIT904 = new Course("CIT904", "Course", "Clayton Greenberg", "TR", "11:00", "11:30", 72);
		assertTrue(user.timeConflict(CIT904, courseInfo3));
		assertEquals(CIT901, user.timeConflictCourse(CIT904, courseInfo3));
	}
	
	@Test
	void findCourseAccordingID () {
		//find an existing course
		Course course1 = user.findCourseAccordingID("CIT590", courseInfo);
		assertEquals(course1, courseInfo.get(0));
		
		//find an non-existing course
		Course course2 = user.findCourseAccordingID("CIT000", courseInfo);
		assertEquals(null, course2);
	}
	
	@Test
	void findProfAccordingID () {
		//find an existing course
		Professor professor1 = user.findProfAccordingID("001", profInfo);
		assertEquals(professor1, profInfo.get(0));
		
		//find an non-existing course
		Professor professor2 = user.findProfAccordingID("9999", profInfo);
		assertEquals(null, professor2);
	}

}
