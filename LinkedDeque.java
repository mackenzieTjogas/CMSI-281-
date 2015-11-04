public class LinkedDeque {
    private Node leftEnd;
    private Node rightEnd;
    private int size;
    private static int successes = 0;
    private static int attempts = 0;

    public LinkedDeque() {
    }

    public static LinkedDeque createFrom(Object[] given) {
        LinkedDeque ret = new LinkedDeque();
        for (int i = 0; i < given.length; i++) {
            ret.insertRight(given[i]);
        }
        return ret;
    }

    public void insertLeft(Object o) {
        leftEnd = new Node(o, null, leftEnd);
        if (size() == 0) {
            rightEnd = leftEnd;
        } else {
            leftEnd.right().setLeft(leftEnd);
        }
        size++;
    }

    public void insertRight(Object o) {
        rightEnd = new Node(o, rightEnd, null);
        if (size() == 0) {
            leftEnd = rightEnd;
        } else {
            rightEnd.left().setRight(rightEnd);
        }
        size++;
    }
    
    public void deleteLeft() {
        if (leftEnd == null) {
            return;
        }
        leftEnd = leftEnd.rightNode;
        leftEnd.leftNode = null;
        size--;
    }

    public void deleteRight() {
        if (rightEnd == null) {
            return;
        }
        rightEnd = rightEnd.leftNode;
        rightEnd.rightNode = null;
        size--;
    }

    public Object left() {
        return leftEnd.getData(); 
    }

    public Object right() {
        return rightEnd.getData(); 
    }

    public int size() {
        return size;
    }

    public String toString() {
        String finalString = "";
        Node currentElement = leftEnd;
        while (currentElement != null) {
            finalString += "[" + currentElement.getData() + "]";
            currentElement = currentElement.right();
            
        }
        return finalString;
    } 

    public boolean equals(LinkedDeque second) {
        if (!(second instanceof LinkedDeque)) {
            return false;
        }
        if (this.size() != second.size()) {
            return false;
        }
        Node currentElement = rightEnd;
        Node otherElement = second.rightEnd;
        while (currentElement != null) {
            if (!currentElement.getData().equals(otherElement.getData())){
                return false;
            }
            otherElement = otherElement.left();
            currentElement = currentElement.left();
        }
        return true;
    }  

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_constructors();
        test_insertLeft();
        test_insertRight();
        test_deleteLeft();
        test_deleteRight();
        test_left();
        test_right();
        test_size();
        test_toString();

        System.out.println(successes + "/" + attempts + " tests passed.");
    } 

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "successes" : "failure");
    }

    private static void test_constructors() {
        System.out.println("Testing constructors...");
        LinkedDeque deque = new LinkedDeque();

        try {
            displaySuccessIfTrue(deque.size() == 0);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue((deque.leftEnd == null) && (deque.rightEnd == null));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_insertLeft() {
        System.out.println("Testing insertLeft...");
        LinkedDeque deque1 = new LinkedDeque();
        deque1.createFrom(new Integer[] {1,2, 3});
        deque1.insertLeft(4);

        try {
            displaySuccessIfTrue(deque1.leftEnd.getData().equals(new Integer(4)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_insertRight() {
        System.out.println("Testing insertRight...");
        LinkedDeque deque2 = new LinkedDeque();
        deque2.createFrom(new String[] {"7" ,"8", "9"});
        deque2.insertRight("6");

        try {
            displaySuccessIfTrue(deque2.rightEnd.getData().equals("6"));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_deleteLeft() {
        System.out.println("Testing deleteLeft...");
        LinkedDeque deque3 = LinkedDeque.createFrom(new String[] {"4" ,"2", "1"});
        deque3.deleteLeft();
        LinkedDeque check = LinkedDeque.createFrom(new String[] {"2", "1"});
        
        System.out.println("check: " + check.toString());
        System.out.println("deque3: " + deque3.toString());
        
        try {
            displaySuccessIfTrue(deque3.equals(check));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_deleteRight() {
        System.out.println("Testing deleteRight...");
        LinkedDeque deque4 = LinkedDeque.createFrom(new String[] {"6" ,"18", "4"});
        deque4.deleteRight();
        LinkedDeque check2 = LinkedDeque.createFrom(new String[] {"6", "18"});
        
        try {
            displaySuccessIfTrue(deque4.equals(check2));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_left() {
        System.out.println("Testing left...");
        LinkedDeque deque5 = LinkedDeque.createFrom(new String[] {"7" ,"12", "5"});
        
        try {
            displaySuccessIfTrue(deque5.left().equals("7"));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_right() {
        System.out.println("Testing right...");
        LinkedDeque deque6 = LinkedDeque.createFrom(new String[] {"7" ,"12", "5"});
        
        try {
            displaySuccessIfTrue(deque6.right().equals("5"));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_size() {
        System.out.println("Testing size...");
        LinkedDeque deque6 = LinkedDeque.createFrom(new String[] {"7" ,"12", "5"});
        
        try {
            displaySuccessIfTrue(deque6.size() == 3);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void test_toString() {
        System.out.println("Testing toString...");
        LinkedDeque deque7 = LinkedDeque.createFrom(new String[] {"7" ,"12", "5"});
        
        try {
            displaySuccessIfTrue(deque7.toString().equals("[7][12][5]"));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    public class Node {
        private Object data;
        private Node leftNode;
        private Node rightNode;

        public Node(Object data) {
            this.data = data;
        }
        public Node(Object data, Node leftNode, Node rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
        public void setLeft(Node leftN) {
            leftNode = leftN;
        }
        public void setRight(Node rightN) {
            rightNode = rightN;
        }

        public Object getData() {
            return data; 
        }
        public Node left() {
            return this.leftNode;
        }
        public Node right() {
            return this.rightNode;
        }
    }

}
