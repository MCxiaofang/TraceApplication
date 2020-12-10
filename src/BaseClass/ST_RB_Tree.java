package BaseClass;

public class ST_RB_Tree {
    private ST_RB_Node root;
    private ST_RB_Node nullNode;
    LinkStack<ST_RB_Node> stack=new LinkStack<>();

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public ST_RB_Tree() {
        this.nullNode = new ST_RB_Node(BLACK,  0, null, null, null, null);
        this.nullNode.parent = nullNode;
        this.nullNode.leftChild = nullNode;
        this.nullNode.rightChild = nullNode;
        this.root = nullNode;
    }

    public ST_RB_Node search(long key) {
        ST_RB_Node searchLoc=root;
        while(searchLoc.key!=key&&searchLoc!=nullNode){
            if(key<searchLoc.key){
                searchLoc=searchLoc.leftChild;
            }
            else{
                searchLoc=searchLoc.rightChild;
            }
        }
        return searchLoc;
    }

    public boolean insert(TPerson data) {
        ST_RB_Node insertLoc = root;
        ST_RB_Node insertLocPar = nullNode;
        long key = Long.parseLong(data.personID);
        if (root == nullNode) {
            root = new ST_RB_Node(BLACK, Long.parseLong(data.personID), data, nullNode, nullNode, nullNode);
            return true;
        }
        ST_RB_Node newNode = new ST_RB_Node(RED, Long.parseLong(data.personID), data, insertLocPar, nullNode, nullNode);

        do {
            insertLocPar = insertLoc;
            if (key < insertLoc.key) {
                insertLoc = insertLoc.leftChild;

            } else if (key > insertLoc.key) {
                insertLoc = insertLoc.rightChild;
            } else {
                while(insertLoc.next!=null){
                    insertLoc=insertLoc.next;
                }
                insertLoc.next=newNode;
                return true;
            }
        } while (insertLoc != nullNode);

        if (key < insertLocPar.key) {
            insertLocPar.leftChild = newNode;
        } else {
            insertLocPar.rightChild = newNode;
        }

        maintain(newNode);
        return true;
    }

    public void maintain(ST_RB_Node newNode) {
        ST_RB_Node fixNode = newNode;
        while (fixNode.parent.color == RED) {
            ST_RB_Node uncle;
            /*make sure uncle node*/
            if (fixNode.parent == fixNode.parent.parent.leftChild) {
                uncle = fixNode.parent.parent.rightChild;
            } else {
                uncle = fixNode.parent.parent.leftChild;
            }
            /*uncle and others exchange color*/
            if (uncle.color == RED) {
                uncle.color = BLACK;
                fixNode.parent.color = BLACK;
                fixNode.parent.parent.color = RED;

                if (fixNode.parent.parent == this.root) {
                    this.root.color = BLACK;
                    break;
                }
                fixNode = fixNode.parent.parent;
            }
            /*rotate for four method*/
            else {
                if (fixNode.parent == fixNode.parent.parent.leftChild) {
                    if (fixNode == fixNode.parent.leftChild) {
                        fixLL(fixNode);
                        break;
                    } else {
                        fixLR(fixNode);
                        break;
                    }
                } else {
                    if (fixNode == fixNode.parent.leftChild) {
                        fixRL(fixNode);
                        break;
                    } else {
                        fixRR(fixNode);
                        break;
                    }
                }
            }
        }
    }

    public void fixRL(ST_RB_Node fixNode) {
        ST_RB_Node maxNode = fixNode.parent;
        ST_RB_Node midNode = fixNode;
        ST_RB_Node minNode = fixNode.parent.parent;

        minNode.rightChild = midNode.leftChild;
        maxNode.leftChild = midNode.rightChild;
        midNode.leftChild.parent = minNode;
        midNode.rightChild.parent = maxNode;

        if (minNode.parent != nullNode) {
            if (minNode == minNode.parent.leftChild) {
                minNode.parent.leftChild = midNode;
            } else {
                minNode.parent.rightChild = midNode;
            }
        } else {
            this.root = midNode;
        }
        midNode.parent = minNode.parent;

        minNode.parent = midNode;
        maxNode.parent = midNode;
        midNode.leftChild = minNode;
        midNode.rightChild = maxNode;

        midNode.color = BLACK;
        minNode.color = RED;
    }

    public void fixLR(ST_RB_Node fixNode) {
        ST_RB_Node maxNode = fixNode.parent.parent;
        ST_RB_Node midNode = fixNode;
        ST_RB_Node minNode = fixNode.parent;

        minNode.rightChild = midNode.leftChild;
        maxNode.leftChild = midNode.rightChild;
        midNode.leftChild.parent = minNode;
        midNode.rightChild.parent = maxNode;

        if (maxNode.parent != nullNode) {
            if (maxNode == maxNode.parent.leftChild) {
                maxNode.parent.leftChild = midNode;
            } else {
                maxNode.parent.rightChild = midNode;
            }
        } else {
            this.root = midNode;
        }
        midNode.parent = maxNode.parent;

        minNode.parent = midNode;
        maxNode.parent = midNode;
        midNode.leftChild = minNode;
        midNode.rightChild = maxNode;

        midNode.color = BLACK;
        maxNode.color = RED;
    }

    public void fixLL(ST_RB_Node fixNode) {
        ST_RB_Node maxNode = fixNode.parent.parent;
        ST_RB_Node midNode = fixNode.parent;
        ST_RB_Node minNode = fixNode;

        maxNode.leftChild = midNode.rightChild;
        midNode.rightChild.parent = maxNode;

        if (maxNode.parent != nullNode) {
            if (maxNode == maxNode.parent.leftChild) {
                maxNode.parent.leftChild = midNode;
            } else {
                maxNode.parent.rightChild = midNode;
            }
        } else {
            this.root = midNode;
        }
        midNode.parent = maxNode.parent;

        maxNode.parent = midNode;
        midNode.rightChild = maxNode;

        midNode.color = BLACK;
        maxNode.color = RED;
    }

    public void fixRR(ST_RB_Node fixNode) {
        ST_RB_Node maxNode = fixNode;
        ST_RB_Node midNode = fixNode.parent;
        ST_RB_Node minNode = fixNode.parent.parent;

        minNode.rightChild = midNode.leftChild;
        midNode.leftChild.parent = minNode;

        if (minNode.parent != nullNode) {
            if (minNode == minNode.parent.leftChild) {
                minNode.parent.leftChild = midNode;
            } else {
                minNode.parent.rightChild = midNode;
            }
        } else {
            this.root = midNode;
        }
        midNode.parent = minNode.parent;

        minNode.parent = midNode;
        midNode.leftChild = minNode;

        midNode.color = BLACK;
        minNode.color = RED;
    }

    public ST_RB_Node getRoot() {
        return root;
    }

    public LinkStack<ST_RB_Node> PreOrder(ST_RB_Node root) {
        if (root != nullNode) {
            stack.push(root);
            PreOrder(root.leftChild);
            PreOrder(root.rightChild);
        }
        return stack;
    }

    public String toString() {
        return "not implement";
    }
}
