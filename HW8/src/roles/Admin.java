package roles;

import java.util.ArrayList;

import roles.User;
import courses.Course;

/**
 * This class describes a ship of length 4
 */
public class Admin extends User {
	private static final String USERTYPE= "Admin";

    /** constructor for Admin
     *
     * @param username
     * @param password
     * @param ID
     * @param name */
    public Admin(String username, String password, String ID, String name) {
        super(username, password, ID, name);
    }

    /** return the user type in String */
    @Override
    public String getUserType() {
        return USERTYPE;
    }

    /** return a string form of printout */
    @Override
    public String print() {
        String output= getID() + "; " + getName() + "; " + getUserName() + "; " + getPassword();
        return output;
    }
    
    /**
     * check if the professor is already in the profInfo
     * @param professor
     * @param profInfo
     * @return a boolean, true if prof exists, false if not.
     */
    public Boolean professorExists(Professor professor, ArrayList<Professor> profInfo) {
    	if(profInfo.contains(professor)) {
    		return true;
    	}
    	return false;
    }
	
	/**
	 * Add new courses
	 * @return list of all courses
	 */
	public ArrayList<Course> addNewCourse(Course course, ArrayList<Course> courseInfo, Boolean professorExists, Boolean timeConflict) {
		if (courseInfo.contains(course)){
			//if the course is not in the courseInfo
			System.out.println("The course already exist");
			// if prof not in system
		} else if (!professorExists) {
			System.out.println("The professor isn't in the system, please add this professor first");
			// if course has time conflict
		} else if (timeConflict) {
			System.out.println("The new added course has time conflict with course" + this.timeConflictCourse (course, courseInfo));
			System.out.println("Unable to add new course: " + course.getCourseCode() + "|" + course.getCourseName() +
					", " + course.getStartTimeinString() + "-" + course.getEndTimeinString() + " on " + course.getDayofClass() +
					", with course capacity: " + course.getClassSize() + ", students:0, lecturer: Professor " + course.getProfName());
			// if no problem, add the course
		} else {
			courseInfo.add(course);
			System.out.println("Successfully added the course: " + course.getCourseCode() + "|" + course.getCourseName() +
					", " + course.getStartTimeinString() + "-" + course.getEndTimeinString() + " on " + course.getDayofClass() +
					", with course capacity: " + course.getClassSize() + ", students:0, lecturer: Professor " + course.getProfName());
		}
		return courseInfo;
		
	}
	
	/**
	 * delete courses
	 * @return list of all courses
	 */
	public ArrayList<Course> deleteCourse(Course course, ArrayList<Course> courseInfo) {
		if(courseInfo.contains(course)){
			//if the course is in the courseInfo
			courseInfo.remove(course);
			System.out.println("Successfully removed the course: " + course.getCourseCode());
		} else {
			//else the course isn't in the courseInfo
			System.out.println("The course doens't exists");
		}
		return courseInfo;
	}
	
	
	/**
	 * Add new professor
	 * @param professor name
	 * @return list of updated professor
	 */
	public ArrayList<Professor> addProfessor(Professor professor, ArrayList<Professor> profInfo) {
		//check if the ID and username already exists
		int x = 0;
		for(int i = 0; i < profInfo.size(); i++) {
			if (professor.getID().equals(profInfo.get(i).getID())) {
				System.out.println("The ID already exists");
				x = 1;
				break;
				//return profInfo;
			} else if (professor.getUserName().equals(profInfo.get(i).getUserName())) {
				System.out.println("The username already exists");
				x = 1;
				break;
			} else {
				continue;
			}
		}
		if (x == 0) {
			profInfo.add(professor);
			System.out.println("Successfully added the new professor: " + professor.getID() + " " + professor.getName());
		}
		return profInfo;
	}
	
	/**
	 * delete professor
	 * @param professor name
	 */
	public ArrayList<Professor> deleteProfessor(Professor professor, ArrayList<Professor> profInfo) {
		if(profInfo.contains(professor)){
			//if the course is in the courseInfo
			profInfo.remove(professor);
			System.out.println("Successfully removed the new professor: " + professor.getID() + " "+ professor.getName());
		} else {
			//else the course isn't in the courseInfo
			System.out.println("The ID doesn't exists");
		}
		return profInfo;
	}
	
	/**
	 * Add new student
	 * @param student name
	 */
	public ArrayList<Student> addStudent(Student student, ArrayList<Student> studentInfo) {
		//check if the ID and username already exists
		int x = 0;
		for(int i = 0; i < studentInfo.size(); i++) {
			if (student.getID().equals(studentInfo.get(i).getID())) {
				System.out.println("The ID already exists");
				x = 1;
				break;
				//return profInfo;
			} else if (student.getUserName().equals(studentInfo.get(i).getUserName())) {
				System.out.println("The username already exists");
				x = 1;
				break;
			} else {
				continue;
			}
		}
		if (x == 0) {
			studentInfo.add(student);
			System.out.println("Successfully added the new student: " + student.getID() + " " + student.getName());
		}
		return studentInfo;
	}
	
	
	/**
	 * helps delete the student
	 * @param student: an Object of student
	 * @param : an Arraylist of Student object
	 * @return: an ArrayList of Student
	 */
	public ArrayList<Student> deleteStudent(Student student, ArrayList<Student> studentInfo) {
		if(studentInfo.contains(student)){
			//if the course is in the courseInfo
			studentInfo.remove(student);
			System.out.println("Successfully removed the new student: " + student.getID() + " " + student.getName());
		} else {
			//else the course isn't in the courseInfo
			System.out.println("The ID doesn't exists");
		}
		return studentInfo;
	}
}
