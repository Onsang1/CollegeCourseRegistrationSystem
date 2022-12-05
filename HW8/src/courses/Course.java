package courses;

public class Course {
    // the course code e.g. CIT591
    private String CourseCode;
    // the course name e.g. Intro to Computing
    private String CourseName;
    // the prof name of course
    private String ProfName;
    // the day of class (MTWRF)
    private String DayofClass;
    // time of class
    private String Time;
    // time of class in terms of number, for comparison
    private double TimeinNum;
    // size of class
    private int ClassSize;

    /** This is the constructor of Course class
     *
     * @param CourseCode: a string
     * @param CourseName: a string
     * @param ProfName:   a string
     * @param DayofClass: a string
     * @param Time:       a string
     * @param ClassSize:  an int */
    public Course(String CourseCode, String CourseName, String ProfName, String DayofClass,
        String Time, int ClassSize) {
        this.CourseCode= CourseCode;
        this.CourseName= CourseName;
        this.ProfName= ProfName;
        this.DayofClass= DayofClass;
        this.Time= Time;
        this.ClassSize= ClassSize;

    }

    // BELOW are the getter methods

    /** getter method for coursecode
     *
     * @return: the coursecode */
    public String getCourseCode() {
        return CourseCode;
    }

    /** getter method for coursename
     *
     * @return: the coursename */
    public String getCourseName() {
        return CourseName;
    }

    /** getter method for profname
     *
     * @return: the profname */
    public String getProfName() {
        return ProfName;
    }

    /** getter method for dayofclass
     *
     * @return: the dayofclass */
    public String getDayofClass() {
        return DayofClass;
    }

    /** getter method for time
     *
     * @return: the time of class in string */
    public String getTimeinString() {
        return Time;
    }

    /** getter method for time in number form
     *
     * @return: the time of class in double */
    public double getTimeinNum() {
        return TimeinNum;
    }

    /** getter method for classsize
     *
     * @return: the size of class */
    public int getClassSize() {
        return ClassSize;
    }

    // BELOW are the setter methods
    /** a method that set the value of CourseCode
     *
     * @param: a string that represents the coursecode */
    public void setCourseCode(String x) {
        CourseCode= x;
    }

    /** a method that sets the value of CourseName
     *
     * @param: a string that represents the coursename */
    public void setCourseName(String x) {
        CourseName= x;
    }

    /** a method that sets the value of ProfName
     *
     * @param: a string that represents the profname */
    public void setProfName(String x) {
        ProfName= x;
    }

    /** a method that sets the value of DayofClass
     *
     * @param: a string that represents the dayofclass */
    public void setDayofClass(String x) {
        DayofClass= x;
    }

    /** a method that sets the value of time in string
     *
     * @param: a string that represents the time */
    public void setTime(String x) {
        Time= x;
    }

    /** a helper method that translate any time in string into a comparable double
     *
     * @param a string */
    public double TimeToNum(String x) {
        // TODO
        return 0;
    }

    /** a method that translate the string time into double form
     *
     * @param: a string */
    public void setTimeinNum() {
        TimeinNum= TimeToNum(Time);
    }

    /** a method that sets the value of ClassSize
     *
     * @param: an int that represents the class size */
    public void setClassSize(int x) {
        ClassSize= x;
    }

}
