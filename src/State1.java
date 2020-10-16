// --== CS400 File Header Information ==--
// Name: Grant Parfrey
// Email: gparfrey@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: n/a

public class State1{

    private RedBlackTree<Member> tree = DataUtils.loadData();
    private LinkedList<long> keys;


    private class Member{
        public enum SchoolYear {
            FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER
        }
        public Member(long iwiscID, String iname, SchoolYear iyearInSchool){
            this.wiscID = iwiscID;
            this.name = iname;
            this.yearInSchool = iyearInSchool;
        }
        //instance fields for Member:
        Long wiscID;
        String name;
        SchoolYear yearInSchool;
    }




}