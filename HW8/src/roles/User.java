/**
 * @author: Yuxin Meng
 * @author: Jingzhuo Hu
 */

package roles;

import java.util.ArrayList;

import courses.Course;

/** This abstract class describes the characteristics common to all users. */
public abstract class User {

    private String ID;
    private String password;
    private String name;
    private String username;

    public User(String username, String password, String ID, String name) {
        this.username= username;
        this.password= password;
        this.ID= ID;
        this.name= name;
    }

    /** the abstract method for getting the usertype */
    public abstract String getUserType();

    /** the abstract method for making a string for each user to be printed out
     *
     * @return */
    public abstract String print();

    // BELOW are the getter function

    /** Returns the ID correspondingly
     *
     * @return ID an String */
    public String getID() {
        return ID;
    }

    /** Returns the name correspondingly
     *
     * @return name as string */
    public String getName() {
        return name;
    }

    /** Returns the username correspondingly
     *
     * @return username as string */
    public String getUserName() {
        return username;
    }

    /** Returns the password correspondingly
     *
     * @return password as string */
    public String getPassword() {
        return password;
    }

    /** View all courses
     *
     * @return list of all courses */
    public ArrayList<Course> viewAllCourses(ArrayList<Course> courseInfo) {
        return courseInfo;
    }
    
    /**
     * check if there is time conflict
     * @param course
     * @param courseInfo
     * @return
     */
    public Boolean timeConflict (Course course, ArrayList<Course> courseInfo) {
    	for (int i = 0; i < courseInfo.size(); i++) {
    		//check if share the same getDayofClass
    		if ((course.getDayofClass().contains(courseInfo.get(i).getDayofClass())) || (courseInfo.get(i).getDayofClass()).contains(course.getDayofClass())) {
    			if ((course.getStartTimeinNum() > courseInfo.get(i).getStartTimeinNum()) && (course.getStartTimeinNum() < courseInfo.get(i).getEndTimeinNum())) {
    				return true;
    			} else if ((course.getEndTimeinNum() > courseInfo.get(i).getStartTimeinNum()) && (course.getEndTimeinNum() < courseInfo.get(i).getEndTimeinNum())) {
    				return true;
    			}
    		} 
    	}
    	return false;
    }
    
    /**
     * find the course that conflict with current course
     * @param course
     * @param courseInfo
     * @return course that conflicted
     */
    public Course timeConflictCourse (Course course, ArrayList<Course> courseInfo) {
    	for (int i = 0; i < courseInfo.size(); i++) {
    		if ((course.getDayofClass().contains(courseInfo.get(i).getDayofClass())) || (courseInfo.get(i).getDayofClass()).contains(course.getDayofClass())) {
    			if ((course.getStartTimeinNum() >= courseInfo.get(i).getStartTimeinNum()) && (course.getStartTimeinNum() < courseInfo.get(i).getEndTimeinNum())) {
    				return courseInfo.get(i);
    			} else if ((course.getEndTimeinNum() > courseInfo.get(i).getStartTimeinNum()) && (course.getEndTimeinNum() <= courseInfo.get(i).getEndTimeinNum())) {
    				return courseInfo.get(i);
    			}
    		} 
    	}
    	return null;
    }
    
    
    /**
     * find course object according to courseID
     * @param courseID
     * @param courseInfo
     * @return
     */
    public Course findCourseAccordingID (String courseID, ArrayList<Course> courseInfo) {
    	for (int i = 0; i < courseInfo.size(); i++) {
    		if (courseInfo.get(i).getCourseCode().equals(courseID)) {
    			return courseInfo.get(i);
    		}
    	}
    	return null;
    }
    
    /**
     * find professor according to profID
     * @param courseID
     * @param courseInfo
     * @return
     */
    public Professor findProfAccordingID (String profID, ArrayList<Professor> profInfo) {
    	for (int i = 0; i < profInfo.size(); i++) {
    		if (profInfo.get(i).getID().equals(profID)) {
    			return profInfo.get(i);
    		}
    	}
    	return null;
    }
}
