//package application;
class Node
{
    Node next;
    int element;

    public Node()
    {
    	next = null;
    }

    // Node constructor
    public Node(int elementValue)
    {
        next = null;
        element = elementValue;
    }//Node

    public Node getNext()
    {
        return next;
    }//getNext

    public void setNext(Node nextValue)
    {
        next = nextValue;
    }//setNext
}//class Node
