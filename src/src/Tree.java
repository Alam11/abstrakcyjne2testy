package src;

import java.util.*;

public class Tree<T> implements Iterable<T> {
    private T value;
    private boolean isLeaf;
    private List<Tree<T>> children;

    public EnumeratorOrder getOrder() {
        return order;
    }

    private EnumeratorOrder order;


    public void setOrder(EnumeratorOrder order) {
        this.order = order;
    }

    public Tree(T value, EnumeratorOrder order){
        this.isLeaf = true;
        this.value = value;
        this.order = order;
        this.children = null;
    }

    public Tree(T value, EnumeratorOrder order, List<Tree<T>> children){
        this.isLeaf = false;
        this.value = null;
        this.order = order;
        this.children = new LinkedList<Tree<T>>();
        this.children.add( new Tree<>(value,order));
        this.children.addAll(children);
    }


    public void print(){
        for(T t : this){
            System.out.println(t);
        }
    }

    public int size(){
        int counter = 0;
        for(T t : this){
            counter++;
        }
        return counter;
    }

    public void add(Tree child){
        if (isLeaf){
            this.isLeaf = false;
            this.children = new LinkedList<Tree<T>>();
            this.children.add( new Tree<>(this.value,order));
            this.children.add(child);
            this.value = null;
        } else {
            this.children.add(child);
        }
    }

    public void add(T child){
        this.add(new Tree(child, this.order));
    }

    public List<Tree<T>> getChildren(){
        return children;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }


    private class TreeIterator implements Iterator<T> {

        private int cursor;
        private Queue<T> res;

        public Queue<T> getRes(){
            return res;
        }


        public TreeIterator(){
            cursor = 0;
            if(isLeaf){
                Queue<T> t = new ArrayDeque<>();
                t.add(value);
                res = t;
            } else {
                res = searchInOrder(new ArrayDeque<T>(), children, order);
            }
        }

        private Queue<T> searchInOrder(Queue<T> res, List<Tree<T>> children, EnumeratorOrder order) {
            if (order == EnumeratorOrder.BFS){
                for(Tree<T> child : children){
                    if (child.isLeaf){
                        res.add(child.value);
                    }
                }
                for(Tree<T> child : children){
                    if (!child.isLeaf){
                        res.addAll( searchInOrder(new ArrayDeque<T>(),child.children, child.order));
                    }
                }
            } else {
                for(Tree<T> child : children){
                    if (child.isLeaf){
                        res.add(child.value);
                    } else {
                        res.addAll( searchInOrder(new ArrayDeque<T>(),child.children, child.order));
                    }
                }
            }
            return res;
        }


        public boolean hasNext() {
            return (cursor < this.res.size());
        }

        @Override
        public T next() {
            return res.poll();
        }

        @Override
        public void remove() {
            res.poll();
        }
    }
}
