package roles;

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

    /** return a string form of printout */
    @Override
    public String print() {
        String output= getName() + "; " + getID() + "; " + getUserName() + "; " + getPassword();
        return output;
    }

}
