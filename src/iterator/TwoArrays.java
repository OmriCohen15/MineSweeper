package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> {
	private int[] a1;
	private int[] a2;
	
	public TwoArrays(int[] a1, int[] a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	private class TwoArraysIterator implements Iterator<Integer>{
		private int i = 0, j = 0;
		private boolean fromA1 = true; //indicate if we should take from a1 (true), or a2(false).
		
		@Override
		public boolean hasNext() {
			return (i + j < a1.length + a2.length); //if one of the arrays still has elements to return.
		}

		@Override
		public Integer next() {
			if (i < a1.length) {
				if(j < a2.length) {
					if(fromA1) {
						fromA1 = !fromA1;
						return a1[i++]; //if both of the arrays still have elements to read (a1 turn)
					}
					fromA1 = !fromA1;
					return a2[j++]; //if both of the arrays still have elements to read (a2 turn)
				}
				return a1[i++]; //if only array a2 finished, take only from a1.
			}
			return a2[j++]; //if only array a1 finished, take only from a2.
		}
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new TwoArraysIterator();
	}
	
}
