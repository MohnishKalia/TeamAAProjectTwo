// --== CS400 File Header Information ==--
// Name: Alexander Ulate
// Email: ulate@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: 

package src;

/**
 * This class implements the State of the Gym tracker and loads the Red Black
 * tree with the values passed in from the main application. This specific
 * implementation is from BackEnd2.
 * 
 * @author Alexander Ulate
 *
 */
public class State2 {
  private String fileName = "members.cv"; // The name of the file containing members
  private String getResult; // The String result of the get method
  private RedBlackTree<Member2> tree; // The tree where members are stored
  
  /**
   * This constructor populates the tree from the file upon creating the state
   * This will call upon opening the application
   */
  public State2() {
    tree.insert(new Member2(DataWrangler.readFile(fileName)));
  }
  
  public Member2 getMember(long wiscId) throws IllegalArgumentException {
    return null;
  }
  
  public void insertMember(long wiscId, String name, SchoolYear year) throws IllegalArgumentException {
    
  }
}
