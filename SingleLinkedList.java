//package application;
class SingleLinkedList
{
	private Node head;
    private int elementCount;

	public SingleLinkedList()
	{
		head = new Node();
		elementCount = 0;
	}//Default

	//Removes the first element in list
	public void removeFirstElement()
	{
        Node current = head;
        head = current.getNext();//current.getNext() is the second node who is set to be the first (head.getNext())
        elementCount--;//Decrease number of elements
	}//removeFirstElement

	//Removes the last element in list
	public void removeLastElement()
	{
	        Node current = head;
	        while (current.getNext().getNext() != null)//find next node until last is found
	        {
	            current = current.getNext();
	        }//while
	        current.setNext(null);//Sets the second last node to point at "null" (This deletes the last node)
	        elementCount--;//Decrease number of elements
	}//removeLastElement

	//Deletes the first occurrence of a given value
	public boolean deleteFirstValue(Integer value)
	{
		Node current = head;
		for (int counter = 0 ; counter <= elementCount-1; counter++)//goes through list
		{
			if (current.getNext().element == value)
			{
				current.setNext(current.getNext().getNext());
				elementCount--;//Decrease number of elements
				return true;//returns true if node with value is found and deleted
			}//if
			current = current.getNext();
		}//for
		return false;//returns false if node with value is not found
	}//deleteFirstValue


	//Deletes any occurrence of a given value
		public int deleteValue(Integer value)
		{
			Node current = head;
			int deleted = 0;
			for (int counter = 0 ; counter <= elementCount-1; counter++)
			{
				while (current.getNext().element == value)
				{
					current.setNext(current.getNext().getNext());//Sets current node point to the second next node
					elementCount--;//Decrease number of elements
					deleted++;//Increase if value is found
					if (current.getNext() == null)
						break;
				}//while
				current = current.getNext();
			}//for
		return deleted;//returne value of deleted elements
		}//deleteValue

	//Add value at the beginning of the list
	public void addBeginning(Integer value)
	{
		 Node temp = new Node(value);
		 Node current = head;
		 //System.out.print(current.element);
		 //if (head.next != null)
		 temp.setNext(current.getNext());//sets the new node to point at the node after (head)
		 current.setNext(temp);//set "head" to point at the new node
		 elementCount++;// increment the number of elements variable
	}//addBeginning


	//Add value at the end of the list
	 public void addLast(Integer value)
    {
        Node temp = new Node(value);
        Node current = head;
        while (current.getNext() != null)//find next node until last is found
        {
            current = current.getNext();
        }
        current.setNext(temp);//set new node as last node
        elementCount++;//Increase number of elements
    }//addLast

	//Add new value after given value if found
	 public boolean addAfter(Integer newValue, Integer afterValue)
    {
        Node temp = new Node(newValue);
        Node current = head;
        while (current.getNext() != null)
        {
	        if (current.getNext().element == afterValue)//find next node until afterValue is found
	        {
	        	current = current.getNext();
	        	temp.setNext(current.getNext());
	        	current.setNext(temp);
	        	elementCount++;//Increase number of elements
	        	return true;//return true if afterValue is found and new Node is inserted
	        }//if
	        current = current.getNext();
        }//while
        return false;//return false if afterValue is not found
    }//addAfter

	 //Add new value before given value if found
	 public boolean addInFront(Integer newValue, Integer beforeValue)
	    {
	        Node temp = new Node(newValue);
	        Node current = head;
	        while (current.getNext() != null)
	        {
		        if (current.getNext().element == beforeValue)//find next node until beforeValue is found
		        {
		        	temp.setNext(current.getNext());
		        	current.setNext(temp);
		        	elementCount++;//Increase number of elements
		        	return true;
		        }//if
		        current = current.getNext();
	        }//while
	        return false;
	    }//addInFront

	 //Count occurrences of given value
	 public int counter(Integer Value)
	    {
	        Node current = head;
	        int counter = 0;
	        while (current.getNext() != null)
	        {
		        if (current.getNext().element == Value)//check if current node is equal to value
		        {
		        	counter++;//Increase counter if value is found
		        }//if
		        current=current.getNext();
	        }//while
	        return counter;//returns number of hits
	    }//counter

	 //Delete all nodes
	 public void deleteAll()
	 {
		 Node current = head;
		 current.setNext(null);//sets "head" to point at "null" (Everything in list deleted)
		 elementCount = 0;
	 }//deleteAll

	 //returns the number of elements in this list.
	 public int getElementCount()
	    {
	        return elementCount;
	    }//ElementCount

	 //Generates a string with all elements (five elements per line)
    public String toString()
    {
        Node current = head.getNext();
        String output = "";
        //int counter = 0;
        while (current != null)
        {
            output += "[" + current.element + "]     ";
            /*counter ++;
            if (counter == 5)//set "ENTER" in string after five elements and counter to zero
            {
            	output += "\n";
            	counter = 0;
            }//if*/
            current = current.getNext();
        }//while
        output += "\n";
        return output;
    }//toString
}//class
