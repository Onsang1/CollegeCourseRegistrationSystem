package courses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import courses.Course;
import files.FileInfoReader;

/**
 * this tests the courses
 * @author Onsang Yau, Jingzhuo Hu
 *
 */

class CourseTest {

	Course course1;
	Course course2;
	FileInfoReader reader= new FileInfoReader();
	FileInfoReader reader2= new FileInfoReader();
	ArrayList<Course> courseInfo;
	
	@BeforeEach
	void setUp() throws Exception {
        //initialize professor
		course1 = new Course("CIT593", "Mathematical Foundations of Computer Science", "Clayton Greenberg", "TR", "10:00", "11:00", 72);
		course2 = new Course("CIT594", "Mathematical Foundations of Computer Science", "Clayton Greenberg", "TR", "12:30", "14:00", 72);
		reader.readFromCourseFile("courseInfo.txt");
		courseInfo = reader.getCourseObj();
    }
	
	@Test
	void getStartTimeinNum() {
		//test course1's start time
		double startTime1 = course1.getStartTimeinNum();
		System.out.println(startTime1);
		assertEquals(10.00,startTime1);
		
		//test course2's start time
		double startTime2 = course2.getStartTimeinNum();
		System.out.println(startTime2);
		assertEquals(12.30,startTime2);
	}
	
	@Test
	void getEndTimeinNum() {
		//test course1's end time
		double endTime1 = course1.getEndTimeinNum();
		System.out.println(endTime1);
		assertEquals(11.00,endTime1);
		
		//test course2's end time
		double endTime2 = course2.getEndTimeinNum();
		System.out.println(endTime2);
		assertEquals(14.00,endTime2);
	}

}
