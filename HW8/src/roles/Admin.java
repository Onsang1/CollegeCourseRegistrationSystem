package roles;

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
}
