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

    private RedBlackTree<Member1> tree;// = DataUtils.loadData();
    private LinkedList<Long> keys;

    /**
     * TEMPORARY CONSTRUCTOR UNTIL DATAUTILS WORKS
     */
    public State1(){
        tree = new RedBlackTree<Member1>();
    }
    public void insertMember(long wiscID, String name, Member1.SchoolYear yearInSchool) throws IllegalArgumentException{
        tree.insert(new Member1(wiscID, name, yearInSchool));
    }
    public Member1 getMember(long wiscID){
        //TODO
        return new Member1(123123, "Must fix", Member1.SchoolYear.valueOf("OTHER"));
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

    public void save() {
        //DataUtils.saveData(tree);
    }


}