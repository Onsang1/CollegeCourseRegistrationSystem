package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;
import files.FileInfoReader;

/**
 * This is the test file for admin
 * @author Onsang Yau, Jingzhuo Hu
 *
 */
class AdminTest {

	Admin admin;
	FileInfoReader reader= new FileInfoReader();
	FileInfoReader reader2= new FileInfoReader();
	FileInfoReader reader3= new FileInfoReader();
	ArrayList<Course> courseInfo;
	ArrayList<Student> studentInfo;
	ArrayList<Professor> profInfo;

	@BeforeEach
	void setUp() throws Exception {
        //initialize professor
		admin = new Admin("001", "admin", "admin01", "password590");
		reader.readFromCourseFile("courseInfo.txt");
		reader2.readFromStudentFile("studentInfo.txt");
		reader3.readFromProfFile("profInfo.txt");
		courseInfo = reader.getCourseObj();
		studentInfo = reader2.getStudentObj();
		profInfo = reader3.getProfObj();
    }
	
	@Test
	void testprofessorExists() {
		System.out.println("test professorExists");
		// a condition that professor exists
		Professor professor4 = profInfo.get(0);
		assertTrue(admin.professorExists(professor4, profInfo));
		
		// a condition that professor doesn't exists
		Professor professor5 = new Professor("This is 5", "prof5", "prof5", "prof5");
		assertFalse(admin.professorExists(professor5, profInfo));
	}
	
	@Test
	void testaddNewCourse() {
		//test if a course with exist professor and no time conflict can be added successfully
		System.out.println("testaddNewCourse");
		ArrayList<Course> courseInfo2 = new ArrayList<Course>();
		Course CIT901 = new Course("CIT901", "Course", "Clayton Greenberg", "TR", "10:30", "11:30", 72);
		courseInfo2.add(CIT901);
		Course CIT900 = new Course("CIT900", "Course", "Clayton Greenberg", "MW", "16:30", "18:00", 72);
		assertTrue(admin.addNewCourse(CIT900, courseInfo2, true, false).contains(CIT900));
		
		//test if a course without exist professor 
		Course CIT902 = new Course("CIT901", "Course", "new professor", "TR", "10:30", "11:30", 72);
		assertFalse(admin.addNewCourse(CIT902, courseInfo2, false, false).contains(CIT902));
		
		//test if a course with time conflict can be added successfully
		System.out.println("hello");
		Course CIT904 = new Course("CIT904", "Course", "Clayton Greenberg", "TR", "10:30", "11:30", 72);
		assertFalse(admin.addNewCourse(CIT904, courseInfo2, true, true).contains(CIT904));
	}
	
	
	@Test
	void testdeleteCourse() {
		System.out.println("deleteCourse");
		//test if delete a course that already exists
		Course course = courseInfo.get(0);
		//output should be Successfully removed the course: CIT590
		assertFalse(admin.deleteCourse(course, courseInfo).contains(course));
		
		//test if delete a course that doesn't exists
		Course CIT592 = new Course("CIT592", "Course", "Clayton Greenberg", "TR", "10:00", "11:00", 72);
		//output should be The course doens't exists
		assertFalse(admin.deleteCourse(CIT592, courseInfo).contains(CIT592));	
	}
	
	@Test
	void testaddProfessor() {
		System.out.println("addProfessor");
		//test if add a professor that already exists
		Professor professor1 = profInfo.get(0);
		//output should be The ID already exists
		assertTrue(admin.addProfessor(professor1, profInfo).contains(professor1));
		
		//test if add a professor that doesn't exists
		Professor professor2 = new Professor("Lio", "00", "00", "name");
		//output should be The professor successfully added
		assertTrue(admin.addProfessor(professor2, profInfo).contains(professor2));
	}
	
	@Test
	void testdeleteProfessor() {
		System.out.println("deleteProfessor");
		//test if delete a professor that exists
		Professor professor3 = profInfo.get(1);
		assertFalse(admin.deleteProfessor(professor3, profInfo).contains(professor3));
		
		//test if delete a professor doesn't exists
		Professor professor4 = new Professor("This is name", "999", "liu", "lio");
		//output should be The course doens't exists
		assertFalse(admin.deleteProfessor(professor4, profInfo).contains(professor4));	
	}
	
	@Test
	void testaddStudent() {
		System.out.println("addStudent");
		//test if add a student that already exists
		Student student1 = studentInfo.get(0);
		//output should be The ID already exists
		assertTrue(admin.addStudent(student1, studentInfo).contains(student1));
		
		//test if add a professor that doesn't exists
		ArrayList<String> grade1 = new ArrayList<String>();
		grade1.add("CIT593: A");
		Student student2 = new Student("This is name", "this is id", "00", "student", grade1);
		//output should be The student successfully added
		assertTrue(admin.addStudent(student2, studentInfo).contains(student2));
	}
	
	@Test
	void testdeleteStudent() {
		System.out.println("deleteStudent");
		//test if delete a student that exists
		Student student3 = studentInfo.get(1);
		assertFalse(admin.deleteStudent(student3, studentInfo).contains(student3));
		
		//test if delete a student doesn't exists
		ArrayList<String> grade2 = new ArrayList<String>();
		grade2.add("CIT592: A");
		Student student4 = new Student("This is name", "this is id", "liu", "password", grade2);
		//output should be The course doens't exists
		assertFalse(admin.deleteStudent(student4, studentInfo).contains(student4));	
	}
}
