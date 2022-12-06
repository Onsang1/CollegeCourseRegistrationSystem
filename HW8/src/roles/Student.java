package roles;

import java.util.ArrayList;

public class Student extends User {
    private static final String USERTYPE= "Student";

    private ArrayList<String> Grades= new ArrayList<>();

    /** constructor for Student
     *
     * @param username
     * @param password
     * @param ID
     * @param name */
    public Student(String username, String password, String ID, String name,
        ArrayList<String> grade) {
        super(username, password, ID, name);
        Grades= grade;
    }

    /** return the user type in String */
    @Override
    public String getUserType() {
        return USERTYPE;
    }

    /** return a string form of printout */
    @Override
    public String print() {
        String output= getID() + "; " + getName() + "; " + getUserName() + "; " + getPassword() +
            "; ";
        for (String s : Grades) {
            output= output + s;
            output+= ", ";
        }
        return output;
    }

    // SETTER METHODS
    /** set the student's grade in ArrayList */
    public void setGrades(ArrayList<String> x) {
        Grades= x;
    }

}
