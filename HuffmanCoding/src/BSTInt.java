public interface BSTInt {
    void add(int value, String symbol); 
    boolean searchValue(int value); 
    //boolean searchSymbol(String symbol); 
    void printInOrder();  //may have to customize this to print breadth first 
    void printBreadthFirst(); 
    //Node delete(int value); 
} 