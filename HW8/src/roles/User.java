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

//    /** in controller
//     * return to the previous menu
//     */
//    public void returnToPrev() {
//    	return to an object called menu;
//    }

    /** View all courses
     *
     * @return list of all courses */
    public ArrayList<Course> viewAllCourses(ArrayList<Course> courseInfo) {
        return courseInfo;
    }
}