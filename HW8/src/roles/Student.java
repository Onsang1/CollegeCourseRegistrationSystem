package roles;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    // the type of user
    private static final String USERTYPE= "Student";
    // the Grades in an ArrayList of String for printing
    private ArrayList<String> Grades= new ArrayList<>();
    // the grades in a hashmap
    private HashMap<String, String> GradeMap= new HashMap<>();

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
        setGradeMap(Grades);

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

    private void setGradeMap(ArrayList<String> x) {
        for (String s : Grades) {
            String info[]= s.split(":");
            GradeMap.put(info[0].strip(), info[1].strip());
        }
    }

    // GETTER METHODS
    /** get the student's grade (in ArrayList) */
    public ArrayList<String> getGrades() {
        return Grades;
    }

    /** get the student's grade (in HashMap) */
    public HashMap<String, String> getGradesInMap() {
        return GradeMap;
    }

}
