package roles;

import java.util.ArrayList;

import courses.Course;

/**
 * This describe the function for professor
 * @author Onsang Yau, Jingzhuo Hu
 *
 */

public class Professor extends User {
    private static final String USERTYPE= "Professor";

    /** constructor for Professor
     *
     * @param username
     * @param password
     * @param ID
     * @param name */
    public Professor(String username, String password, String ID, String name) {
        super(username, password, ID, name);
    }

    /** return the user type in String */
    @Override
    public String getUserType() {
        return USERTYPE;
    }
 
    /**
	 * View given courses information.
	 * @param professor
	 * @param courseInfo
	 * @return list of given courses
	 */
	public ArrayList<Course> getGivenCourses(ArrayList<Course> courseInfo) {
		//create a new arraylist givenCourses
		ArrayList<Course> givenCourses = new ArrayList<Course> ();
		for (int i = 0; i < courseInfo.size(); i++) {
			//add element into the givenCourses
			if (this.getName().toLowerCase().trim().equals(courseInfo.get(i).getProfName().toLowerCase().trim())) {
				//System.out.println(courseInfo.get(i));
				givenCourses.add(courseInfo.get(i));
			}
		}
		return givenCourses;
	}
	
	/**
	 * View student list of the given courses
	 * @param coursename
	 * @param studentInfo
	 * @return list of student in the given course
	 */
	
	public ArrayList<Student> getStudentList(Course course, ArrayList<Student> studentInfo) {
		//create a new arraylist givenCourses
		ArrayList<Student> studentsList = new ArrayList<Student> ();
		//access each student, and check if the sutdent's course list includes that course
		for (int i = 0; i < studentInfo.size(); i++) {
			if (studentInfo.get(i).getCourseList().contains(course)) {
				studentsList.add(studentInfo.get(i));
			} else {
				continue;
			}
		}
		//print out the list
		System.out.println("Student in your course " + course.getCourseCode() + " " + course.getCourseName() + ":");
		if (studentsList.size() == 0) {
			return studentsList;
		} else {
			for (int j = 0; j < studentsList.size(); j++) {
				System.out.println(studentsList.get(j).getID() + " " + studentsList.get(j).getName());
			}
		}
		return studentsList;
	}
	/**
	 * the function that returns the printout format of professor
	 * @return
	 */
	@Override
    public String print() {
        String output= getName() + "; " + getID() + "; " + getUserName() + "; " + getPassword();
        return output;
    }
	
}
