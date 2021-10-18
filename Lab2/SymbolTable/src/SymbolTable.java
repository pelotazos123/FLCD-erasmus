public class SymbolTable {

    private static Tree<String> tree = new Tree<String>();

    public static void main(String[] args){
        tree.addNode("hola");
        tree.addNode("adios");
    }

}
