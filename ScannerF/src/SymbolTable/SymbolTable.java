package SymbolTable;

/**
 * SymbolClass that implements a tree
 */
public class SymbolTable {

    private final Tree<String> tree;

    public SymbolTable(){
        tree = new Tree();
    }

    public void add(String value){
        tree.addNode(value);
    }

    public void remove(String value){
        tree.removeNode(value);
    }

    public String get(String value){
       return tree.searchNode(value).getInfo();
    }

}