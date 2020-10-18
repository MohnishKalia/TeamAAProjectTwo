import java.util.ArrayList;

public class TreeAndKeys {

	private RedBlackTree<Member1> tree;
	private ArrayList<Long> keys;

	public TreeAndKeys(RedBlackTree<Member1> tree, ArrayList<Long> keys) {
		this.tree = tree;
		this.keys = keys;
	}

	public RedBlackTree<Member1> getTree() {
		return tree;
	}

	public ArrayList<Long> getKeys(){
		return keys;
	}

}
