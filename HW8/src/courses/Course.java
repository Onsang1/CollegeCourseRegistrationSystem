package courses;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Course {
    // the course code e.g. CIT591
    private String CourseCode;
    // the course name e.g. Intro to Computing
    private String CourseName;
    // the prof name of course
    private String ProfName;
    // the day of class (MTWRF)
    private String DayofClass;
    // start time of class
    private String StartTime;
    // end of class
    private String EndTime;
    // time of class in terms of number, for comparison
//    private double TimeinNum;
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
        String StartTime, String EndTime, int ClassSize) {
        this.CourseCode= CourseCode;
        this.CourseName= CourseName;
        this.ProfName= ProfName;
        this.DayofClass= DayofClass;
        this.StartTime= StartTime;
        this.EndTime= EndTime;
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
     * @return: the start time of class in string */
    public String getStartTimeinString() {
        return StartTime;
    }

    /** getter method for time
     *
     * @return: the end time of class in string */
    public String getEndTimeinString() {
        return EndTime;
    }

    /** get start time in double */
    public double getStartTimeinNum() {
        // TODO
        String info[]= StartTime.split(":");
        double hours= Integer.parseInt(info[0].strip());
        double minsInt= Integer.parseInt(info[0].strip());
        double minsDouble= minsInt / 60;
        double result= hours + minsDouble;
        return rounding(result);
    }

    /** get start time in double */
    public double getEndTimeinNum() {
        // TODO
        String info[]= EndTime.split(":");
        double hours= Integer.parseInt(info[0].strip());
        double minsInt= Integer.parseInt(info[0].strip());
        double minsDouble= minsInt / 60;
        double result= hours + minsDouble;
        return rounding(result);
    }

    /** getter method for time in number form
     *
     * @return: the time of class in double */
    public double getDuration() {
        return rounding(getEndTimeinNum() - getStartTimeinNum());
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
    public void setTime(String x, String y) {
        StartTime= x;
        EndTime= y;
    }

    /** a method that sets the value of ClassSize
     *
     * @param: an int that represents the class size */
    public void setClassSize(int x) {
        ClassSize= x;
    }

    /** a print method that can print a course with all info with correct format
     *
     * @return a string */
    public String print() {
        String result= CourseCode + "; " + CourseName + "; " + ProfName + "; " + DayofClass + "; " +
            StartTime + "; " + EndTime + "; " + ClassSize;
        return result;
    }

    /** round any double number into 2 decimal
     *
     * @param c: any doubles
     * @return: a double with 2 decimal places */
    private static Double rounding(double c) {
        // create big decimal with double value
        BigDecimal bd= new BigDecimal(c);
        // set config for big decimal
        bd= bd.setScale(2, RoundingMode.HALF_UP);
        // get rounded value
        Double roundedValue= bd.doubleValue();
        return roundedValue;
    }



}
