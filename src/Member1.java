import java.time.LocalTime;
public class Member1 implements Comparable<Member1> {
    enum SchoolYear {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER
    }

    public Member1(long iwiscID, String iname, SchoolYear iyearInSchool, LocalTime itime){
        this.wiscID = iwiscID;
        this.name = iname;
        this.yearInSchool = iyearInSchool;
        this.time = itime;
    }
    public Member1(long iwiscID, String iname, SchoolYear iyearInSchool){
        this.wiscID = iwiscID;
        this.name = iname;
        this.yearInSchool = iyearInSchool;
        this.time = LocalTime.now();
    }

    public int compareTo(Member1 m){
        return this.wiscID.compareTo(m.wiscID);
    }

    //instance fields for Member:
    Long wiscID;
    String name;
    SchoolYear yearInSchool;
    LocalTime time;

    @Override
    public String toString(){
        return (wiscID.toString()+","+name+","+yearInSchool+","+time);
    }


}