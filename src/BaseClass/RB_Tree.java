package BaseClass;

import java.util.concurrent.BlockingDeque;

public class RB_Tree {
    private RB_Node root;
    private RB_Node nullNode;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RB_Tree() {
        this.nullNode = new RB_Node(BLACK, 0, 0, null, null, null, null);
        this.nullNode.parent = nullNode;
        this.nullNode.leftChild = nullNode;
        this.nullNode.rightChild = nullNode;
        this.root = nullNode;
    }

    public RB_Node search(long min, long max, RB_Node searchLoc) {
        if (searchLoc == nullNode)
            return null;
        //当前节点重叠，搜索左子树 右子树
        if (min < searchLoc.data.getMax() && max > searchLoc.data.getMin()) {
            searchLoc.setNext(search(min, max, searchLoc.leftChild));
            RB_Node temp = searchLoc;         //用于下降来连接末尾next指针，但不能改动searchLoc指针
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.setNext(search(min, max, searchLoc.rightChild));
        }
        //当前节点不重叠，搜索右子树
        else if(searchLoc.leftChild!=nullNode&&searchLoc.leftChild.max<min){
            searchLoc=search(min,max,searchLoc.rightChild);
        }
        //当前节点不重叠，搜索左子树
        else if(searchLoc.data.getMin()>max){
            searchLoc=search(min,max,searchLoc.leftChild);
        }
        //当前节点不重叠，搜索左子树 右子树
        else{
            RB_Node temp = search(min, max, searchLoc.leftChild);
            if (temp != null) {
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.setNext(search(min, max, searchLoc.rightChild));
                return temp;
            } else {//如果左子树为空指针，则直接返回右子树就行
                return search(min, max, searchLoc.rightChild);
            }

        }
        return searchLoc;
    }

    public boolean insert(TPerson data) {
        RB_Node insertLoc = root;
        RB_Node insertLocPar = nullNode;
        long key = data.getMin();

        if (root == nullNode) {
            root = new RB_Node(BLACK, data.getMin(), data.getMax(), data, nullNode, nullNode, nullNode);
            return true;
        }

        do {
            insertLocPar = insertLoc;
            if (key < insertLoc.key) {
                insertLoc = insertLoc.leftChild;
            } else if (key > insertLoc.key) {
                insertLoc = insertLoc.rightChild;
            } else {
                return false;
            }
        } while (insertLoc != nullNode);

        RB_Node newNode = new RB_Node(RED, data.getMin(), data.getMax(), data, insertLocPar, nullNode, nullNode);

        if (key < insertLocPar.key) {
            insertLocPar.leftChild = newNode;
        } else {
            insertLocPar.rightChild = newNode;
        }

        maintain(newNode);
        return true;
    }

    public void maintain(RB_Node newNode) {
        RB_Node fixNode = newNode;
        while (fixNode.parent.color == RED) {
            RB_Node uncle;
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

    public void fixRL(RB_Node fixNode) {
        RB_Node maxNode = fixNode.parent;
        RB_Node midNode = fixNode;
        RB_Node minNode = fixNode.parent.parent;

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

    public void fixLR(RB_Node fixNode) {
        RB_Node maxNode = fixNode.parent.parent;
        RB_Node midNode = fixNode;
        RB_Node minNode = fixNode.parent;

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

    public void fixLL(RB_Node fixNode) {
        RB_Node maxNode = fixNode.parent.parent;
        RB_Node midNode = fixNode.parent;
        RB_Node minNode = fixNode;

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

    public void fixRR(RB_Node fixNode) {
        RB_Node maxNode = fixNode;
        RB_Node midNode = fixNode.parent;
        RB_Node minNode = fixNode.parent.parent;

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

    public RB_Node getRoot() {
        return root;
    }

    public void maintainMax(RB_Node root) {
        if (root != nullNode) {
            maintainMax(root.leftChild);
            maintainMax(root.rightChild);

            RB_Node fixNode = root;
            while (fixNode.parent != nullNode && fixNode.parent.max < root.max) {
                fixNode.parent.max = root.max;
                fixNode = fixNode.parent;
            }
        }
    }

    public String PreOrder(RB_Node root) {
        String str = "";
        if (root != nullNode) {
            //str+=root.data.toString();
            System.out.println(root.color + root.data.personName + " " + root.data.arrTime + " " + root.data.outTime);
            if (root.leftChild != nullNode)
                System.out.println("左 " + root.leftChild.data.personName);
            if (root.rightChild != nullNode)
                System.out.println("右 " + root.rightChild.data.personName);
            if (root.parent != nullNode)
                System.out.println("父母 " + root.parent.data.personName);
            str += PreOrder(root.leftChild);

            str += PreOrder(root.rightChild);
        }
        return str;
    }

    public String toString() {
        return PreOrder(getRoot());
    }
}

























