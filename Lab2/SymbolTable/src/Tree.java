public class Tree<T extends Comparable<T>> {

    private Node<T> root;

    public Tree(){
        root = null;
    }

    /**
     * Add the node to the tree
     * @param info
     * @return
     */
    public boolean addNode(T info){
        if (info != null){
            if (searchNode(info) == null){
                if (root == null) {
                    root = new Node<T>(info);
                    return true;
                }
                else
                    return addNodeR(root, info);
            }
            return false;
        }
        return false;
    }

    private boolean addNodeR(Node<T> node, T info) {
        if (info.compareTo(node.getInfo()) == -1) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<T>(info));
                return true;
            }
            return addNodeR(node.getLeft(), info);
        } else if (info.compareTo(node.getInfo()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<T> (info));
                return true;
            }
            return addNodeR(node.getRight(), info);
        }
        return false;
    }

    /**
     * Search a node in the tree with a given key
     * @param info
     * @return
     */
    public Node<T> searchNode(T info){
        if (info != null) {
            if (root == info) {
                return root;
            } else {
                return searchNodeR(root, info);
            }
        }
        return null;
    }

    private Node<T> searchNodeR(Node<T> node, T info) {
        if (node != null) {
            int comp = node.getInfo().compareTo(info);
            if (comp > 0) {
                return searchNodeR(node.getLeft(), info);
            } else if (comp < 0) {
                return searchNodeR(node.getRight(), info);
            } else if(comp == 0) {
                return node;
            }
        }
        return null;
    }

    /**
     * Remove a node from the tree if it's equals to the given key
     * @param info
     * @return
     */
    public boolean removeNode(T info) {

        if (info != null && root != null) {
            if (searchNode(info) != null) {
                root = removeNodeR(root, info);
                return true;
            }
            return false;
        }
        return false;
    }

    private Node<T> removeNodeR(Node<T> node, T info) {
        if (info.compareTo(node.getInfo()) < 0) {
            node.setLeft(removeNodeR(node.getLeft(), info));
        } else if (info.compareTo(node.getInfo()) > 0) {
            node.setRight(removeNodeR(node.getRight(), info));
        } else if (info.compareTo(node.getInfo()) == 0){
            if (node.getLeft() != null || node.getRight() != null) {
                if (node.getLeft() == null) {
                    return node.getRight();
                } else if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    node.setInfo((T) getMax(node.getLeft()));
                    node.setLeft(removeNodeR(node.getLeft(), node.getInfo()));
                    return node;
                }
            }
            return null;

        }
        return node;
    }

    /**
     * Returns the biggest node
     * @param node
     * @return
     */
    private T getMax(Node<T> node) {
        if (node == null)
            return null;
        else if (node.getRight() == null)
            return node.getInfo();
        else
            return (T) getMax(node.getRight());
    }

}
