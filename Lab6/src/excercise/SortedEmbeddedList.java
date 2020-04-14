package excercise;

import list.LinkedList;
import list.List;

/**
 * @author Ydiel Z. Flores Torres
 *
 */
public class SortedEmbeddedList<E extends Comparable<? super E>> extends AbstractSortedList<E> {

	/*************************************************************************
	 * IMPORTANT NOTE
	 * 
	 * We're initially using a LinkedList, but we could have used ArrayList.
	 * The point of this class is to delegate most of the work to the List,
	 * regardless of its implementation.
	 * You will later be asked about this class' performance according to the
	 * different possible implementations for the underlying List object.
	 ************************************************************************/
	private List<E> list;

	public SortedEmbeddedList() {
		list = new LinkedList<>();
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		/* Do NOT use binary search here! */
		if(list.size() ==0) { //adds in the first position if list is empty
			list.add(e);
		}else if(firstIndex(e)>-1) { //if it finds an elements equals to 'e', add 'e' a position in front 
			list.add(firstIndex(e) + 1, e);
		}else {
			int count = 0;
			while(count < size() && e.compareTo(get(count))>0) { //search for the position where 'e' belongs
				count++;
			}
			list.add(count, e);
		}
	}

	@Override
	public boolean remove(E e) {
		// TODO Auto-generated method stub
		return list.remove(e);
	}

	@Override
	public E removeIndex(int index) {
		// TODO Auto-generated method stub
		return list.remove(index);
	}

	@Override
	public int firstIndex(E e) {
		// TODO Auto-generated method stub
		return list.firstIndex(e);
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		/* Can't use list.toArray() because it returns Object[], not Comparable[] */
		E[] asArray = (E[]) new Comparable[size()]; // Cannot use Object here
		for (int i = 0; i < list.size(); i++)
			asArray[i] = list.get(i);
		return asArray;
	}

	/* To take full advantage of the embedded list, override the following methods.
	 * Most implementations should be EXTREMELY simple (delegate the work to the list) */

	public boolean contains(E e) {
		/* TODO ADD CODE HERE */
		return list.contains(e);
	}
	
	public boolean isEmpty() {
		/* TODO ADD CODE HERE */
		return size() == 0;
	}
	
	public void clear() {
		/* TODO ADD CODE HERE */
		list.clear();
	}
	
	public int size() {
		/* TODO ADD CODE HERE */
		return list.size();
	}

}