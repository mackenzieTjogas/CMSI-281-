import java.lang.Iterable;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

//we are assuming we cannot add nodes to an empty binary tree 
public class BinaryTree implements Iterable {
    private Node root;
    private Node cursor;
    private int size;
    private BinaryTree owner;
    private static int successes;
    private static int attempts;
    
    public BinaryTree() {
        owner = this;
        size = 0;
    }
    public BinaryTree(Object obj) {
        owner = this;
        root = new Node(obj);
        size++;
    }
    public boolean contains(Object obj) {
        for (Object o: this) {
            if ((o.equals(obj))) {
                return true;
            }
        }
        return false;
    }
    public boolean similar(Object obj) {
        if (!(obj instanceof BinaryTree)) {
            return false;
        }          
        BinaryTree bt = (BinaryTree) obj; 
        if (this.size() != bt.size()) {
            return false;
        }
        Iterator it1 = this.preOrderNode();
        Iterator it2 = bt.preOrderNode();
        while (it1.hasNext()) {
            Node o1 = (Node)it1.next();
            Node o2 = (Node)it2.next();

            if ((o1.left() != null && o2.left() == null) || (o1.left() == null && o2.left() != null)) {
                return false;
            }
            if ((o1.right() != null && o2.right() == null) || (o1.right() == null && o2.right() != null)) {
                return false;
            }
        }
        
        return true;
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryTree)) {
            return false;
        }          
        BinaryTree bt = (BinaryTree) obj; 
        if (this.size() != bt.size()) {
            return false;
        }

        Iterator it1 = this.iterator();
        Iterator it2 = bt.iterator();
        while (it1.hasNext()) {
            Object o1 = it1.next();
            Object o2 = it2.next();
            if (!o1.equals(o2)) {
                return false;
            }
        }
        it1 = this.inOrder();
        it2 = bt.inOrder();
        while (it1.hasNext()) {
            Object o1 = it1.next();
            Object o2 = it2.next();
            if (!o1.equals(o2)) {
                return false;
            }
        }
        return true;
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return size;
    }
    public int hashCode() {
        return super.hashCode();
    }
    public Iterator iterator() {
        return preOrder();
    }
    private Iterator preOrder() {
        return new Iterator() {
            Iterator it = owner.preOrderNode();
            public Object next() {return ((Node)it.next()).data();}
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public boolean hasNext() {return it.hasNext();}
        };
    }
    private Iterator preOrderNode() {
        return new Iterator() {
            Node current;
            int count;
            Stack<Object> stack = new Stack<Object>();
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (current == null) {
                    count++;
                    current = root;
                    stack.push(current);
                    return current;
                }
                while (true) {
                    if (stack.peek() == current) {
                        if (current.left() != null) {
                            count++;
                            current = current.left();
                            stack.push(current);
                            return current;
                        }
                        if (current.right() != null) {
                            count++;
                            current = current.right();
                            stack.push(current);
                            return current;
                        }
                        current = current.parent();
                        continue;
                    }   
                    if (stack.peek() == current.left()) {
                        if (current.right() != null) {
                            count++;
                            current = current.right();
                            stack.pop();
                            stack.push(current);
                            return current;
                        }
                        current = current.parent();
                        continue;
                    }
                    if (stack.peek() == current.right()) {
                        stack.pop();
                        current = current.parent();
                        continue;
                    }
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public boolean hasNext() {
                return count != owner.size();
            }
        };
    }
    public Iterator inOrder() {
        return new Iterator() {
            Iterator it = owner.inOrderNode();
            public Object next() {return ((Node)it.next()).data();}
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public boolean hasNext() {return it.hasNext();}
        };
    }
    public Iterator inOrderNode() {
        return new Iterator() {
            Node current;
            int count;
            Stack<Object> stack = new Stack<Object>();
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (current == null) {
                    current = root;
                }
                while (true) {
                    if ((stack.size() > 0 && (stack.peek() == current))) {
                        if (current.right() != null) {
                            current = current.right();
                            continue;
                        }

                        current = current.parent();
                        continue;
                    }   
                    if ((stack.size() > 0) && (stack.peek() == current.left())) {
                        count++;
                        stack.pop();
                        stack.push(current);
                        return current;
                    }
                    if ((stack.size() > 0) && (stack.peek() == current.right())) {
                        stack.pop();
                        current = current.parent();
                        continue;
                    }
                    if (current.left() != null) {
                        current = current.left();
                        continue;
                    }
                    count++;
                    stack.push(current);
                    return current;
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public boolean hasNext() {
                 return count != owner.size();
            }
        };
    }
    public boolean putCursorAtRoot() {
        if (root == null) {
            return false;
        } 
        cursor = root;
        return true;
    }
    public boolean putCursorAtLeftSon() {
        if ((this.isEmpty()) || (cursor.left() == null)) {
            return false;
        } 
        cursor = cursor.left();
        return true;

    }
    public boolean putCursorAtRightSon() {
        if ((this.isEmpty()) || (cursor.right() == null)) {
            return false;
        } 
        cursor = cursor.right();
        return true;
    }
    public boolean putCursorAtFather() {
        if ((this.isEmpty()) || (cursor.parent() == null)) {
            return false;
        } 
        cursor = cursor.parent();
        return true;
    }
    public boolean attachLeftSonAtCursor(Object obj) {
        if (cursor.left() != null) {
            return false;
        } 
        cursor.left(new Node(obj));
        cursor.left().parent(cursor);
        size++;
        return true;
    }
    public boolean attachRightSonAtCursor(Object obj) {
        if (cursor.right() != null) {
            return false;
        } 
        cursor.right(new Node(obj));
        cursor.right().parent(cursor);
        size++;
        return true;
    }
    public int trimSize(Node n) {
        return 1 + (n.left() != null ? trimSize(n.left()) : 0) + (n.right() != null ? trimSize(n.right()) : 0);
    }

    public boolean pruneFromCursor() {
        if (cursor == null) {
            return false;
        }
        size -= trimSize(cursor);
        if (cursor.parent() != null) {
            Node oldNode = cursor;
            cursor = cursor.parent();
            if (cursor.left() == oldNode) {
                cursor.left(null); 
            }
            if (cursor.right() == oldNode) {
                cursor.right(null); 
            }
        }
        cursor = root;
        return true;
    }
    public class Node {
        private Object data;
        private Node leftNode;
        private Node rightNode;
        private Node parentNode;

        public Node(Object data) {
            this.data = data;
        }
        public Node(Object data, Node leftNode, Node rightNode, Node parentNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
        }
        public Object data() {
            return data; 
        }
        public Node left() {
            return this.leftNode;
        }
        public Node right() {
            return this.rightNode;
        }
        public Node parent() {
            return this.parentNode;
        }
        public void left(Node obj) {
            this.leftNode = obj;
        }
        public void right(Node obj) {
            this.rightNode = obj;
        }
        public void parent(Node obj) {
            this.parentNode = obj;
        }
    }
    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_constructors();
        test_contains();
        test_similar();
        test_equals();
        test_isEmpty();
        test_size();
        test_preOrder();
        test_inOrder();
        test_pruneFromCursor();

        System.out.println(successes + "/" + attempts + " tests passed.");
    } 

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "successes" : "failure");
    }
    private static void test_constructors() {
        System.out.println("Testing constructors...");
        BinaryTree tree = new BinaryTree();

        try {
            displaySuccessIfTrue(tree.size() == 0);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    private static void test_contains() {
        System.out.println("Testing contains...");
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        BinaryTree bt2 = new BinaryTree("z");
        bt2.putCursorAtRoot();
        bt2.attachLeftSonAtCursor("y");
        bt2.attachRightSonAtCursor((BinaryTree) bt);

        try {
            displaySuccessIfTrue(bt.contains("b"));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(bt2.contains(bt));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!(bt.contains("4")));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!(bt.contains("bt")));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(bt.contains("a"));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
     private static void test_similar() {
        System.out.println("Testing similar...");
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        BinaryTree bt2 = new BinaryTree("b");
        bt2.putCursorAtRoot();
        bt2.attachLeftSonAtCursor("f");
        bt2.attachRightSonAtCursor("g");
        bt2.putCursorAtRightSon();
        bt2.attachLeftSonAtCursor("q");
        bt2.attachRightSonAtCursor("z");
        bt2.putCursorAtRightSon();
        bt2.attachLeftSonAtCursor("u");
        bt2.attachRightSonAtCursor("m");
        BinaryTree bt3 = new BinaryTree("b");
        bt3.putCursorAtRoot();
        bt3.attachLeftSonAtCursor("f");
        bt3.attachRightSonAtCursor("g");
        bt3.putCursorAtLeftSon();
        bt3.attachLeftSonAtCursor("q");
        bt3.attachRightSonAtCursor("z");
        bt3.putCursorAtRightSon();
        bt3.attachLeftSonAtCursor("u");
        bt3.attachRightSonAtCursor("m");

        try {
            displaySuccessIfTrue(bt.similar(bt2));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!(bt.similar(bt3)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!(bt2.similar(bt3)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    private static void test_equals() {
        System.out.println("Testing equals...");

        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        BinaryTree bt2 = new BinaryTree("b");
        bt2.putCursorAtRoot();
        bt2.attachLeftSonAtCursor("f");
        bt2.attachRightSonAtCursor("g");
        bt2.putCursorAtRightSon();
        bt2.attachLeftSonAtCursor("q");
        bt2.attachRightSonAtCursor("z");
        bt2.putCursorAtRightSon();
        bt2.attachLeftSonAtCursor("u");
        bt2.attachRightSonAtCursor("m");
        BinaryTree bt3 = new BinaryTree("b");
        bt3.putCursorAtRoot();
        bt3.attachLeftSonAtCursor("f");
        bt3.attachRightSonAtCursor("g");
        bt3.putCursorAtRightSon();
        bt3.attachLeftSonAtCursor("q");
        bt3.attachRightSonAtCursor("z");
        bt3.putCursorAtRightSon();
        bt3.attachLeftSonAtCursor("u");
        bt3.attachRightSonAtCursor("m");
        
        
        try {
            displaySuccessIfTrue(!(bt.equals(bt2)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(bt2.equals(bt3));
        } catch(Exception e) {
            e.printStackTrace();
            displaySuccessIfTrue(false);
        }
    }
    private static void test_isEmpty() {
        System.out.println("Testing isEmpty...");
        BinaryTree tree = new BinaryTree();
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        try {
            displaySuccessIfTrue(tree.isEmpty());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!(bt.isEmpty()));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    private static void test_size() {
        System.out.println("Testing size...");
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");

        try {
            displaySuccessIfTrue(bt.size() == 7);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!(bt.size() == 16));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    private static void test_preOrder() {
        System.out.println("Testing preOrder...");
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        String result = ("abcdefg");
        String result2 = ("abcdgef");

        Iterator preIt = bt.iterator();
        String str = "";

        while (preIt.hasNext()) {
            str += (String) preIt.next();
        }
        displaySuccessIfTrue(str.equals(result));
        displaySuccessIfTrue(!(str.equals(result2)));
    }
    private static void test_inOrder() {
        System.out.println("Testing inOrder...");
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        String result = "badcfeg";
        Iterator inOrder = bt.inOrder();
        String str = "";
        Object obj = null;
        while (inOrder.hasNext()) {
            obj = inOrder.next();
            str += obj.toString();
        }
        displaySuccessIfTrue(str.equals(result));
    }
    private static void test_pruneFromCursor() {
        System.out.println("Testing pruneFromCursor...");
        BinaryTree bt = new BinaryTree("a");
        bt.putCursorAtRoot();
        bt.attachLeftSonAtCursor("b");
        bt.attachRightSonAtCursor("c");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("d");
        bt.attachRightSonAtCursor("e");
        bt.putCursorAtRightSon();
        bt.attachLeftSonAtCursor("f");
        bt.attachRightSonAtCursor("g");
        bt.pruneFromCursor();
        BinaryTree bt2 = new BinaryTree("a");
        bt2.putCursorAtRoot();
        bt2.attachLeftSonAtCursor("b");
        bt2.attachRightSonAtCursor("c");
        bt2.putCursorAtRightSon();
        bt2.attachLeftSonAtCursor("d");
        //bt2.attachRightSonAtCursor("e");
        //bt2.putCursorAtRightSon();


        try {
            displaySuccessIfTrue(bt2.equals(bt));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }



}
