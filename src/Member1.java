// --== CS400 File Header Information ==--
// Name: Grant Parfrey
// Email: gparfrey@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: We decided for members to only have the time
//that it was created rather than being able to be passed a time.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Member1 from BackEnd1 on Project 2, Team AA
 * @author Grant Parfrey
 *
 * These are the data of the nodes to be passed into the tree.
 * The key (or comparable) is the wiscID.
 */
public class Member1 implements Comparable<Member1> {

    /**
     * Instance fields for the data that each Member1 contains
     *
     * SchoolYear is an enum with the possible years in school
     *      that each member can be
     * wiscID is the key that is a 9 digit long
     * name is the name of the member
     * time is the time of the local machine when a member is added
     */
    enum SchoolYear {FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER}
    Long wiscID;
    String name;
    SchoolYear yearInSchool;
    LocalDateTime time;

    /**
     * This is the constructor for creating a new Member1. Because this
     *      program is to be run in RAM, the time is not passed to the object
     *      but instead will be added on creation. Therefore, on a retrieve from
     *      member.csv, all the loaded members will have the same entry time.
     *
     * @param iwiscID is the key that is a long of length 9
     * @param iname is the associated String of the member
     * @param iyearInSchool is an enum that contains the year of the member
     */
    public Member1(long iwiscID, String iname, SchoolYear iyearInSchool){
        this.wiscID = iwiscID;
        this.name = iname;
        this.yearInSchool = iyearInSchool;
        this.time = LocalDateTime.now();
    }

    /**
     * Compares a member to the current member. This is needed for the
     *      members to be the data in a RedBlackTree
     * @param m is the member to be compared to
     * @return is an integer that functions in the same way as
     *      any other compareTo method
     */
    @Override
    public int compareTo(Member1 m){
        return this.wiscID.compareTo(m.wiscID);
    }

    /**
     * Returns the data that is stored inside a Member1 class.
     *
     * @return a string with the data
     */
    @Override
    public String toString(){
        //Sets the correct format that is readable
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = time.format(format);
        //Returns the member in a .csv format
        return (wiscID.toString()+","+name+","+yearInSchool+","+formattedDate);
    }


}