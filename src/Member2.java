// --== CS400 File Header Information ==--
// Name: Alexander Ulate
// Email: ulate@wisc.edu
// Team: AA
// Role: Back End 2
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: 


import java.time.LocalDateTime; // Used to create the time

/**
 * This class implements the Member to be stored in the Gym tracker. This member
 * holds the information of each member that entered in a Red Black Tree. 
 * This specific implementation is the Member2 from BackEnd2.
 * @author Alexander Ulate
 *
 */
public class Member2 implements Comparable<Member2>{

  /**
   * This is the implementation of the options for the year in school field
   * The member can be one of five categories, with other including faculty and
   * fifth years
   */
  enum SchoolYear {
    FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER;
  }
  
  protected Long wiscID; // The ID of the member, must be 9 digits long
  private String memberName; // name of the member
  private SchoolYear year; // the year in school of the member
  private LocalDateTime time; // the time the member entered the gym

  /**
   * This constructor creates all data for the member as well as sets the time
   * at which the member was created for the tree (time of arrival to gym)
   * 
   * @param wiscID - The WiscID of the member
   * @param name - The name of the member
   * @param year - The Year of the member
   */
  public Member2(long wiscID, String name, SchoolYear year) {
    this.wiscID = wiscID; // set the WiscID
    this.memberName = name; // set the name
    this.year = year; // set the year
    this.time = LocalDateTime.now(); // This calls LocalDateTime.now() function
                                    // to place the current time and date into the field
  }
  
  /**
   * This method prints out a string representation of the Member in the following
   * format: WiscID, Name, Year, Date and time (
   */
  @Override
  public String toString() {
    String result = "" + this.wiscID + "," + this.memberName + "," + this.year;
    result = result + "," + time.toString().replace('T',' ');
    return result.trim(); 
  }
  
  /**
   * This method allows for the comparison of the WiscID's between two members. 
   * This will override any other compareTo methods for the Red Black Tree that 
   * the gym is using to store the members
   * @param member - The member that is being compared to this current member
   * @return the comparison between the two WisdID's as an integer
   */
  @Override
  public int compareTo(Member2 member) {
    return this.wiscID.compareTo(member.wiscID);
  }
}
