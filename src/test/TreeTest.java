package src;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Piotrek on 2016-01-06.
 */
public class TreeTest {

    public Tree<Integer> single;
    public Tree<Integer> complex;

    @Before
    public void setUp() {
        single = new Tree<Integer>(1, EnumeratorOrder.BFS);

        Tree<Integer> subtree = new Tree<Integer>(5,EnumeratorOrder.BFS);
        subtree.add(1);
        subtree.add(2);

        Tree<Integer> tree = new Tree<Integer>(70, EnumeratorOrder.BFS);
        //src.Tree<Integer> tree = new src.Tree<Integer>(70, src.EnumeratorOrder.DFS);

        tree.add(subtree);
        tree.add(90);
        tree.add(subtree);

        complex = tree;
    }


    @Test
    public void isIterable() throws Exception {
        //given
        //when
        //then
        assertNotNull(single.iterator());
        assertNotNull(complex.iterator());
    }

    @Test
    public void containsAll() throws Exception {
        //given
        int complexSum = 0;
        for(Integer i : complex){
            complexSum += i;
        }
        int singleSum = 0;
        for(Integer i : single){
            singleSum += i;
        }
        //when
        //then
        assertEquals(singleSum, 1);
        assertEquals(complexSum, 176);
    }

    @Test
    public void lengthEqual() throws Exception {
        //given
        int before = complex.size();
        //when
        complex.setOrder(EnumeratorOrder.DFS);
        //then
        assertEquals(before, complex.size());
    }

    @Test
    public void enumerateSingle() throws Exception {
        //given
        int before = single.iterator().next();
        single.setOrder(EnumeratorOrder.DFS);
        //when
        int after = single.iterator().next();
        //then
        assertEquals(before, after);
    }

    @Test
    public void orderNotChange() throws Exception {
        //given
        single.setOrder(EnumeratorOrder.DFS);
        complex.add(single);
        //when
        Tree<Integer> lastTree = complex.getChildren().get(complex.getChildren().size() - 1);
        //then
        assertEquals(lastTree.getOrder(), EnumeratorOrder.DFS);
    }

    @Test
    public void orderMatters() throws Exception {
        //given

        Tree<Integer> subtree = new Tree<Integer>(5,EnumeratorOrder.DFS);
        subtree.add(1);
        subtree.add(2);

        Tree<Integer> treeBFS = new Tree<Integer>(70, EnumeratorOrder.BFS);
        src.Tree<Integer> treeDFS = new src.Tree<Integer>(70, src.EnumeratorOrder.DFS);

        treeBFS.add(subtree);
        treeBFS.add(90);
        treeBFS.add(subtree);

        treeDFS.add(subtree);
        treeDFS.add(90);
        treeDFS.add(subtree);

        int BFSSum = 0;
        int DFSSum = 0;
        int j = 0;
        for(int i : treeBFS){
            if( j < 3)
            BFSSum += i;
            j++;
        }
        j = 0;
        for(int i : treeDFS){
            if( j < 3)
                DFSSum += i;
            j++;
        }
        //when
        //then
        assertNotEquals(DFSSum, BFSSum);
    }

}