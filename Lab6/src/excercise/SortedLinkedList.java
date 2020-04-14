package excercise;

/**
 * @author Ydiel Z. Flores Torres
 *
 */
public class SortedLinkedList<E extends Comparable<? super E>> extends AbstractSortedList<E> {
	
	@SuppressWarnings("unused")
	private static class Node<E> {

		private E value;
		private Node<E> next;
		
		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
		
		public Node(E value) {
			this(value, null); // Delegate to other constructor
		}
		
		public Node() {
			this(null, null); // Delegate to other constructor
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
			
		public void clear() {
			value = null;
			next = null;
		}				
	} // End of Node class

	private Node<E> head; // First DATA node (This is NOT a dummy header node)

	public SortedLinkedList() {
		head = null;
		currentSize = 0;
	}

	@Override
	public void add(E e) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when the new value is the smallest */
		Node<E> addNode;
		if(currentSize==0) { //at the start if 'e' is the first element in the list
			head = addNode = new Node<E>(e);
		}else if(head.getValue().compareTo(e)>0) { //at the start if 'e' is the smallest
			addNode = new Node<E>(e,head);
			head = addNode;
		}else {
			Node<E> currentSmall = head;
			Node<E> currentBig = head.getNext();
			while(true) {
				if(currentBig == null) { //at the end
					addNode = new Node<E>(e);
					currentSmall.setNext(addNode);
					break;
				}else if(e.compareTo(currentSmall.getValue()) > 0 && e.compareTo(currentBig.getValue()) < 0) { //check if 'e' is bigger than 'currentSmall' and smaller than 'currentBig'
					addNode = new Node<E>(e, currentBig); //create the new Node with 'currentBig' as the next Node
					currentSmall.setNext(addNode); //update the next node for 'currentNode' to the new Node, 'addNode'
					break;
				}else if(e.compareTo(currentSmall.getValue()) > 0 && e.compareTo(currentBig.getValue()) ==0 || e.compareTo(currentSmall.getValue()) == 0 && e.compareTo(currentBig.getValue()) <0) { 
					/*Before || :
					 * 	Check if 'e' is bigger than 'currentSmall' and equals to 'currentBig'
					 * 
					 * After || :
					 * 	Check if 'e' is equals to 'currentSmall' and smaller than 'currentBig'
					 */
					addNode = new Node<E>(e, currentBig); //create the new Node with 'currentBig' as the next Node
					currentSmall.setNext(addNode); //update the next node for 'currentNode' to the new Node, 'addNode'
					break;
				}else {
					currentSmall = currentBig;
					currentBig = currentBig.getNext();
				}
			}
		}
		currentSize++;
	}

	@Override
	public boolean remove(E e) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when the value is found at the head node */

		Node<E> searchNode = head,toRemove;
		Node<E> prevNode = new Node<E>();
		
		while(true) {
			if(searchNode == null) {
				break;
			}else if(e.equals(searchNode.getValue()) && searchNode ==  head) { //checks if 'e' is in the head
				toRemove =  head;
				head = toRemove.getNext();
				toRemove = null;
				currentSize--;
				return true;
			}else if(e.equals(searchNode.getValue())) {
				toRemove = searchNode;
				prevNode.setNext(searchNode.getNext());
				toRemove = null;
				currentSize--;
				return true;
			}
			prevNode =  searchNode;
			searchNode = searchNode.getNext();
		}
		
		return false;
	}

	@Override
	public E removeIndex(int index) {
		/* TODO ADD CODE HERE */
		/* Special case: Be careful when index = 0 */
		E toReturn;
		if(index == 0) {
			Node<E> holder = head;
			head = head.getNext();
			toReturn = holder.getValue();
			holder = null;
			currentSize--;
			return toReturn;
		}
		int count = 1;
		Node<E> searchNode = head.getNext();
		Node<E> prevNode = new Node<E>();
		while(true) {
			if(count == index) {
				prevNode.setNext(searchNode.getNext());
				toReturn = searchNode.getValue();
				searchNode = null;
				currentSize--;
				return toReturn;
			}else if(count>index) {
				break;
			}
			count++;
			prevNode = searchNode;
			searchNode = searchNode.getNext();
		}
		return null;
	}

	@Override
	public int firstIndex(E e) {
		/* TODO ADD CODE HERE */
		Node<E> runner = head;
		for (int i = 0; i < currentSize; i++) {
			if(e.equals(runner.getValue())){
				return i;
			}
			runner = runner.getNext();
		}
		return -1;
	}

	@Override
	public E get(int index) {
		/* TODO ADD CODE HERE */
		if(index < 0 || index>currentSize) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> runner = head;
		int count = 0;
		while(true) {
			if(count>index) {
				break;
			}
			if(count == index) {
				return runner.getValue();
			}
			count++;
			runner = runner.getNext();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		/* TODO ADD CODE HERE */
		E[] theArray = (E[]) new Comparable[size()]; // Cannot use Object here
		int count = 0;
		Node<E> runner = head;
		while (count < currentSize) {
			theArray[count] = runner.getValue();
			runner = runner.getNext();
			count++;
		}
		return theArray;
	}
}