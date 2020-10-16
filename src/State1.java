// --== CS400 File Header Information ==--
// Name: Grant Parfrey
// Email: gparfrey@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: n/a

import java.util.EnumSet;
import java.util.LinkedList;

public class State1{

    private RedBlackTree<Member> tree;// = DataUtils.loadData();
    private LinkedList<Long> keys;


    private static class Member implements Comparable<Member> {
        enum SchoolYear {
            FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER
        }

        public Member(long iwiscID, String iname, SchoolYear iyearInSchool){
            this.wiscID = iwiscID;
            this.name = iname;
            this.yearInSchool = iyearInSchool;
        }

        public int compareTo(Member m){
            return this.wiscID.compareTo(m.wiscID);
        }

        //instance fields for Member:
        Long wiscID;
        String name;
        SchoolYear yearInSchool;
    }

    //public Member getMember(long iwiscID){


      //  return ;
    //}

    /*
    TODO:
        Member getMember(long wiscID)
        Member removeMember(long wiscID)
        void insertMember(long wiscID, String name, SchoolYear yearInSchool)
        String toString()
        save()

     */



}