package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> {
	private E first, second;
	@SuppressWarnings("unchecked")
	
	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first = (E) first;
		this.second = (E) second;
	}
	
	
	private class CombinedIterator implements Iterator<E> {
		private boolean fromFirst = true; //indicate if we should take from first (true), or second(false).
		
		@SuppressWarnings("unchecked")
		private Iterator<E> itF = ((Iterable<E>) first).iterator(); 
		@SuppressWarnings("unchecked")
		private Iterator<E> itS = ((Iterable<E>) second).iterator(); 
		
		@Override
		public boolean hasNext() {
			return (itF.hasNext() || itS.hasNext());  //if one of the object still has elements to return.
		}

		@Override
		public E next() {
			if(itF.hasNext()) {
				if(itS.hasNext()) {
					if(fromFirst) {
						fromFirst = !fromFirst;
						return itF.next(); //if both of the iterable objects still have elements to read (first turn)
					}
					fromFirst = !fromFirst;
					return itS.next(); //if both of the iterable objects still have elements to read (second turn)
				}
				return itF.next(); //if only second finished, take only from first.
			}
			return itS.next(); //if only first finished, take only from second.
		}	
	}
	
	@Override
	public Iterator<E> iterator() {
		return new CombinedIterator();
	}

}
