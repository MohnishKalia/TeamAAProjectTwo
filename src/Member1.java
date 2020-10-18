// --== CS400 File Header Information ==--
// Name: Grant Parfrey
// Email: gparfrey@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: n/a
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Member1 implements Comparable<Member1> {
    enum SchoolYear {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER
    }

    public Member1(long iwiscID, String iname, SchoolYear iyearInSchool, LocalDateTime itime){
        this.wiscID = iwiscID;
        this.name = iname;
        this.yearInSchool = iyearInSchool;
        this.time = itime;
    }
    public Member1(long iwiscID, String iname, SchoolYear iyearInSchool){
        this.wiscID = iwiscID;
        this.name = iname;
        this.yearInSchool = iyearInSchool;
        this.time = LocalDateTime.now();
    }

    public int compareTo(Member1 m){
        return this.wiscID.compareTo(m.wiscID);
    }

    //instance fields for Member:
    Long wiscID;
    String name;
    SchoolYear yearInSchool;
    LocalDateTime time;

    @Override
    public String toString(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = time.format(format);
        return (wiscID.toString()+","+name+","+yearInSchool+","+formattedDate);
    }


}