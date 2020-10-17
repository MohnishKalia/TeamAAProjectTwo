import java.util.ArrayList;

public class TreeAndKeys {

	private RedBlackTree<Member> tree;
	private ArrayList<Long> keys;

	public TreeAndKeys(RedBlackTree<Member> tree, ArrayList<Long> keys) {
		this.tree = tree;
		this.keys = keys;
	}

	public RedBlackTree<Member> getTree() {
		return tree;
	}

	public ArrayList<Long> getKeys(){
		return keys;
	}

}
