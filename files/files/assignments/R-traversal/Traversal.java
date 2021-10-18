import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

// a non-empty binary tree
abstract class BT<X> {
  X value;

  BT(X value) {
    this.value = value;
  }

 // produce an iterator for the left child, or an empty iterator if one does not exist
 abstract Iterator<X> leftIterator(DFTIterator<X> f);

 // produce an iterator for the right child, or an empty iterator if one does not exist
 abstract Iterator<X> rightIterator(DFTIterator<X> f);

 // add this node's children, if they exist, to the queue (left then right)
 abstract void addChildrenToQueue(Queue<BT<X>> queue);
}

class Leaf<X> extends BT<X> {

  Leaf(X value) {
    super(value);
  }
}

class Node<X> extends BT<X> {
  BT<X> left;
  BT<X> right;

  Node(X value, BT<X> left, BT<X> right) {
    super(value);
    this.left = left;
    this.right = right;
  }
}

// a depth first iterator for a BT
interface DFTIterator<X> extends Iterator<X> {
  // produce a depth first traversal iterator for the given BT
  DFTIterator<X> iterate(BT<X> x);
}

abstract class DFT<X> implements DFTIterator<X> {
  BT<X> bt;
  Iterator<X> left;
  Iterator<X> right;
  // has the top-level value of bt been iterated over yet
  boolean nodeGiven;

  DFT(BT<X> bt) {
    this.bt = bt;
    this.left = bt.leftIterator(this);
    this.right = bt.rightIterator(this);
  }

  public boolean hasNext() {
    return left.hasNext() || !nodeGiven || right.hasNext();
  }

  // check that there is a next element
  public X next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }
    return this.getNext();
  }

  // actually get the next
  abstract X getNext();
}

class InOrder<X> extends DFT<X> {

  InOrder(BT<X> bt) {
    super(bt);
  }

}

class PreOrder<X> extends DFT<X> {

  PreOrder(BT<X> bt) {
    super(bt);
  }

}

class PostOrder<X> extends DFT<X> {

  PostOrder(BT<X> bt) {
    super(bt);
  }
}

// a breadth first iterator for a BT
class BFT<X> implements Iterator<X> {

  Queue<BT<X>> queue = new LinkedList<BT<X>>();

  BFT(BT<X> bt) {
    queue.add(bt);
  }
}
