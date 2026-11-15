public class Node {
    int _value; 
    String _symbol; 
    Node _right; 
    Node _left; 
    Node _parent; 

    public Node (int value, String symbol){ 
        _value = value; 
        _symbol = symbol; 
    }

    boolean isLeaf() {
        return _left == null && _right == null;
    }
}

