// --== CS400 File Header Information ==--
// Name: Alexander Ulate
// Email: ulate@wisc.edu
// Team: AA
// Role: Back End 2
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: 


/**
 * This class implements the State of the Gym tracker and loads the Red Black
 * tree with the values passed in from the main application. This specific
 * implementation is from BackEnd2.
 * 
 * @author Alexander Ulate
 *
 */
public class State2 {
  private RedBlackTree<Member2> tree; // The tree where members are stored
  private long idSearch; // The WiscID that is being searched for by getMember()
  private String printResult; // The result of the print() method
  
  /**
   * This constructor populates the tree from the file upon creating the state
   * This will call upon opening the application
   */
  public State2() {
    tree = DataWrangler1.readFile();
  }
  
  /**
   * This helper method checks if the tree is empty
   * @return True if empty, false otherwise
   */
  private boolean empty() {
    return tree.root == null;
  }
  
  /**
   * This helper method helps with the recursive calls of getMember
   * @param wiscId - The ID of the member you are searching for
   * @return The member you are looking for, null if not found
   */
  private Member2 find(RedBlackTree.Node<Member2> node, Member2 member) {
    if(member.wiscID == this.idSearch) // Check if they equal
      return member;
    // Check if the search is less than current member
    else if(this.idSearch < member.wiscID) {
      if (node.leftChild == null) // Check if child is null
        return null; // Member is not in the tree
      return find(node.leftChild, (Member2) node.leftChild.data); // recursive call
    } else { // search must be greater than current member
      if (node.rightChild == null) // check if child is null
        return null; // Member is not in the tree
      return find(node.rightChild, (Member2) node.rightChild.data); // recursive call
    }
  }
  
  /**
   * This method gets a Member in the Red Black Tree based on the passed
   * WiscID
   * 
   * @param wiscId - The ID of the member you are looking for
   * @return returns the Member that was being searched for
   * @throws IllegalArgumentException thrown if the member is not in the tree
   */
  public Member2 getMember(long wiscId) throws IllegalArgumentException {
    // Check if tree is empty
    if (empty()) {
      throw new IllegalArgumentException("ERROR: Tree is empty or does not exist!");
    }
    // Set the global search ID variable
    this.idSearch = wiscId;
    // Begin Traversing the tree left or right depending on if the WiscID given
    // is less than, greater than, or equal to the root Member
    Member2 member = find(tree.root, (Member2) tree.root.data);
    // Check if the member was found
    if (member == null)
      throw new IllegalArgumentException("Member of WiscID: " + wiscId + " was not"
          + " found in the tracker.");
    // Before leaving the method, reset the idSearch
    this.idSearch = 0;
    return member;
  }
  
  /**
   * This method inserts a member into the Red Black Tree that stores the Gym
   * members
   * 
   * @param wiscId - The Wisc ID of the member that must be 9 digits long and must
   *                not start with a 0
   * @param name - Name of the member
   * @param year - The year of the member
   * @throws IllegalArgumentException - Thrown when the WiscID is not the correct
   *                                    length or format
   */
  public void insertMember(long wiscId, String name, Member2.SchoolYear year) throws IllegalArgumentException {
    // Check that the WiscID is formated correctly
    String check = Long.toString(wiscId);
    // Check the length and first digit
    if(check.length() != 9 || check.charAt(0) == '0') 
      throw new IllegalArgumentException("WiscID is not formated correctly. Not "
          + "9 digits long or starts with 0. WiscID returned: "+ check);
    // Insert the member into the Red Black Tree
    tree.insert(new Member2(wiscId, name, year));
  }
  
  /**
   * This method searches recursively through the tree in InOrder method of
   * traversing the tree.
   * @return whether the print was successfull or not
   */
  private boolean print(RedBlackTree.Node<Member2> node) {
    if (node != null) { // Check that it was passed a valid node
      if (node.leftChild != null) {
        print(node.leftChild);
      } 
      this.printResult = this.printResult + "\n" + node.data.toString();
      if (node.rightChild != null) {
        print(node.rightChild);
      } 
      return true;
    }
    return false;
  }
  
  /**
   * This method creates a string representation of the data found in the Red
   * Black Tree
   */
  @Override
  public String toString() {
    this.printResult = ""; // Clear previous print results
    print(tree.root); // Call the recursive helper method on the root
    return printResult.trim(); // Trim out extra spaces
  }
  
  /**
   * This method saves the state of the app with the current tree and writes to
   * the file to save.
   */
  public void save() {
    // Check that the tree exists and is not empty
    if(!empty())
      DataWrangler1.writeToFile(tree);
  }
}
