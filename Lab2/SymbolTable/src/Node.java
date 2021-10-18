public class Node <T extends Comparable<T>>{

    private T info;
    private Node left;
    private Node right;

    public Node(T info){
        this.info = info;
        left = null;
        right = null;
    }

    public void setInfo(T info){
        this.info = info;
    }

    public T getInfo(){
        return info;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String toString(){
        return info.toString();
    }



}
