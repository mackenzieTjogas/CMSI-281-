public class LinkedDeque {
    private Node leftEnd;
    private Node rightEnd;
    public int size;
    //default constructor
    public LinkedDeque() {
        leftEnd = null;
        rightEnd = null;
        size = 0;
    }

    public void insertLeft(Object o) {
        if ((leftEnd == null) && (rightEnd == null)) {
            Node value = new Node(o);
            leftEnd = value;
            rightEnd = value;
        } else {
            Node value = new Node(o, rightEnd);
            leftEnd.leftNode = value;
            value.rightNode = leftEnd;
            leftEnd = value;
        }
        size++;
    }

    public void insertRight(Object o) {
        if ((leftEnd == null) && (rightEnd == null)) {
            Node value = new Node(o);
            leftEnd = value;
            rightEnd = value;
        } else {
            Node value = new Node(leftEnd, o);
            rightEnd.rightNode = value;
            value.leftNode = rightEnd;
            rightEnd = value;
        }
        size++;
    }
    public void deleteLeft() {
        leftEnd = leftEnd.rightNode;
        leftEnd.leftNode = null;
        size--;
    }
    public void deleteRight() {
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
        Node currentElement = rightEnd;
        while (currentElement != null) {
            finalString += "[" + currentElement.getData() + "]";
            System.out.println("leftNode: " + currentElement.left() + ", rightNode: " + currentElement.rightNode);
            System.out.println("////////");
            currentElement = currentElement.left();
            
        }
        return finalString;
    } 
    public static void main(String[] args) {
        LinkedDeque test = new LinkedDeque();
        test.insertRight("dog");
        test.insertRight("cat");
        test.insertRight("lamb");
        test.insertRight("bunny");
        System.out.println(test);
        System.out.println(test.size);

    }

    public class Node {
        private Object data;
        private Node leftNode;
        private Node rightNode;

        public Node(Object data) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }

        public Node(Node leftNode, Object data) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = null;
        }

        public Node(Object data, Node rightNode) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = rightNode;
        }

        public Node(Node leftNode, Object data, Node rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Object getData() {
            return data; 
        }
        public Node left() {
            return this.leftNode;
        }
    }

}
