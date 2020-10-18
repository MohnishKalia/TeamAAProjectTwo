// --== CS400 File Header Information ==--
// Name: Grant Parfrey
// Email: gparfrey@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: The team decided to not maintain the time that the member
//is added, rather a retrieve from save will set that time for all of the
//saved members

public class State1{
    String file = "log.csv";
    public RedBlackTree<Member1> tree;

    /**
     * TEMPORARY CONSTRUCTOR UNTIL DATAUTILS WORKS
     */
    public State1(){
        tree = DataWrangler1.readFile(file).getTree();
    }
    public void insertMember(long wiscID, String name, Member1.SchoolYear yearInSchool) throws IllegalArgumentException{
        //Makes sure that the wiscID is 9 digits long. First digit must not be zero
        if(wiscID < Long.valueOf(1000000000*10) && wiscID > Long.valueOf(999999999)){
            throw new IllegalArgumentException("WiscID was not 9 digits long or had a starting digit of 0.");
        }
        tree.insert(new Member1(wiscID, name, yearInSchool));
    }
    public Member1 getMember(long wiscID) throws IllegalArgumentException{
        Member1 memberToReturn = null;
        //TODO: find member with traversal method
        if(tree.root == null){
            throw new IllegalArgumentException("Tree is empty.");
        }
        boolean continueSearching = true;
        RedBlackTree.Node testNode = tree.root;
        Member1 testMember;
        while(continueSearching){
            testMember = (Member1) testNode.data;
            //This is the correct member
            if(testMember.wiscID == wiscID){
                return testMember;
            }
            else if(testMember.wiscID < wiscID){
                if(testNode.rightChild == null){
                    break;
                }else{
                    testNode = testNode.rightChild;
                }
            }else if(testMember.wiscID > wiscID){
                if(testNode.leftChild == null){
                    break;
                }else{
                    testNode = testNode.leftChild;
                }

            }
        }

        if(memberToReturn == null)
            throw new IllegalArgumentException("Member with wiscID of: "+wiscID+" does not exist.");
        return memberToReturn;
    }



    String toReturn = "";
    @Override
    public String toString(){
        toReturn = "";
        inOrderTraversal(currentMember -> toReturn += "\n"+currentMember);

        return toReturn.trim();
    }

    public static interface Traverser<Member1>{
        public void visit(Member1 currentMember);
    }
    public void inOrderTraversal(Traverser<Member1> t) {
        this.inOrderTraversal(tree.root, t);
    }
    private void inOrderTraversal(RedBlackTree.Node n, Traverser<Member1> t) {
        if (n != null) {
            inOrderTraversal(n.leftChild, t);
            t.visit((Member1) n.data);
            inOrderTraversal(n.rightChild, t);
        }
    }

    public void save() {
        if(tree.root !=null)
            DataWrangler1.writeToFile(file,tree);
    }


}