package src;

public class Main {
    public static void main(String[] args){

        Tree<Integer> subtree = new Tree<Integer>(5,EnumeratorOrder.DFS);
        subtree.add(1);
        subtree.add(2);

        //Tree<Integer> tree = new Tree<Integer>(70, EnumeratorOrder.BFS);
        src.Tree<Integer> tree = new src.Tree<Integer>(70, src.EnumeratorOrder.DFS);

        tree.add(subtree);
        tree.add(90);
        tree.add(subtree);

        tree.print();
    }

}
