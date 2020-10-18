// --== CS400 File Header Information ==--
// Name: Grant Parfrey
// Email: gparfrey@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: The team decided to not maintain the time that the member
//is added, rather a retrieve from save will set that time for all of the
//saved members

/**
 * State1 from BackEnd1 on Project 2, Team AA
 * @author Grant Parfrey
 */
public class State1{

    /**
     * Instance Fields
     *
     * file is the file path that is passed to the DataWrangler class
     * tree is the tree that holds all the current members
     * toReturn is a String that will be populated during a traversal
     *     to return all the members
     */
    String file = "member.csv";
    public RedBlackTree<Member1> tree;
    String toReturn;

    /**
     * Populates the RedBlackTree tree on creation from data provided by DataWrangler1.java
     *      which should return a RedBlackTree object.
     */
    public State1(){
        tree = DataWrangler1.readFile(file);
    }

    /**
     * Inserts a member into the RedBlackTree.
     *
     * @param wiscID is the key that will be used in the Tree. Must be
     *               9 digits long.
     * @param name is the String associated with the wiscID
     * @param yearInSchool is an enum that is also a piece of data included
     *                     with the member.
     * @throws IllegalArgumentException when the wiscID is not the right length.
     */
    public void insertMember(long wiscID, String name, Member1.SchoolYear yearInSchool)
            throws IllegalArgumentException{

        //Makes sure that the wiscID is 9 digits long. First digit must not be zero.
        if(wiscID < Long.valueOf(1000000000*10) && wiscID > Long.valueOf(999999999)){
            throw new IllegalArgumentException("WiscID was not 9 digits long or had a starting digit of 0.");
        }

        tree.insert(new Member1(wiscID, name, yearInSchool));

    }

    /**
     * Gets a member from a long wiscID. Throws an IllegalArgument Exception
     *          when the member does not exist
     *
     * @param wiscID is the wiscID that the user is looking for.
     * @return returns the member object that the user requested
     * @throws IllegalArgumentException when the member does not exist in the tree
     */
    public Member1 getMember(long wiscID) throws IllegalArgumentException{

        Member1 memberToReturn = null;
        //If the tree is empty, the member does not exist
        if(tree.root == null){
            throw new IllegalArgumentException("Tree is empty.");
        }

        //Reference to the current node that is being tested
        //along with the data of that node (which is a member object)
        RedBlackTree.Node testNode = tree.root;
        Member1 testMember;

        //This traverses down the tree depending on if the node is greater
        //than, less than, or equal to the provided wiscID. The loop will
        //either break when it hits a null node or return the member (if it exists)
        while(true){
            testMember = (Member1) testNode.data;
            //This is the correct member
            if(testMember.wiscID == wiscID){
                return testMember;
            }

            //The desired member wiscID is greater than the current node wiscID
            else if(testMember.wiscID < wiscID) {
                if (testNode.rightChild == null) {
                    break;
                } else {
                    testNode = testNode.rightChild;
                }
            }

            //The desired member wiscID is less than the current node wiscID
            else if(testMember.wiscID > wiscID){
                if(testNode.leftChild == null){
                    break;
                }else{
                    testNode = testNode.leftChild;
                }
            }
        }

        //If the while loop broke and did not return, then the member does not exist
        //and it will throw an exception.
        if(memberToReturn == null)
            throw new IllegalArgumentException("Member with wiscID of: "+wiscID+" does not exist.");
        //This will never be returned
        return memberToReturn;
    }

    /**
     * This is used to print the whole tree with all the members.
     *
     * @return a string with each line containing it's own member.
     */
    @Override
    public String toString(){
        toReturn = "";

        //Lambda expression to implement the visit method
        inOrderTraversal(currentMember -> toReturn += "\n"+currentMember);
        return toReturn.trim();
    }

    /**
     * An Interface for implementing a traverser when printing the whole tree.
     * @param <Member1> requires the type to be of Member1
     */
    public static interface Traverser<Member1>{
        public void visit(Member1 currentMember);
    }

    /**
     * Starts the traversal to return all the members.
     * @param t is what will be carried out
     */
    public void inOrderTraversal(Traverser<Member1> t) {
        this.inOrderTraversal(tree.root, t);
    }

    /**
     * Continues the traversal by being called from the initial traversal
     * @param n is the current node.
     * @param t is the traversal to be run.
     */
    private void inOrderTraversal(RedBlackTree.Node n, Traverser<Member1> t) {
        if (n != null) {
            inOrderTraversal(n.leftChild, t);
            t.visit((Member1) n.data);
            inOrderTraversal(n.rightChild, t);
        }
    }

    /**
     * Saves the current state of the tree by passing the filepath and current
     *      RedBlackTree to the DataWrangler1.
     */
    public void save() {
        if(tree.root !=null)
            DataWrangler1.writeToFile(file,tree);
    }

}