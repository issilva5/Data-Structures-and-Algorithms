package tree;

import static util.Util.*;

@SuppressWarnings("unchecked")
public class Heap< T extends Comparable<T> > implements Tree<T> {
	
	private T[] heap;
	private int end;
	
	public Heap() {
		this.heap = (T[]) new Comparable[32];
		this.end = -1;
	}
	
	public void add(T element) {
		
		if (this.end == this.heap.length - 1) {
			
			this.resize();
			
		}
		
		this.heap[++this.end] = element;
		
		// subir elemento pra posicao TODO
		
	}
	
	private void resize() {
		
		T[] newHeap = (T[]) new Comparable[this.heap.length * 2];
		
		for (int i = 0; i < this.heap.length; i++) {
			newHeap[i] = this.heap[i];
		}
		
		this.heap = newHeap;
		
	}

	public T remove() {
		
		if (this.end == -1)
			throw new UnsupportedOperationException("Heap is empty");
		
		T value = this.heap[0];
		this.heap[0] = this.heap[this.end--];
		this.heapify(0);
		
		return value;
	}
	
	public T top() {
		
		if (this.end == -1)
			throw new UnsupportedOperationException("Heap is empty");
		
		return this.heap[0];
		
	}
	
	public void buildHeap(T[] tree) {
		//TODO
	}
	
	public void buildHeap(BinaryTree<T> tree) {
		//TODO
	}
	
	public T[] toArray() {
		
		return this.heap;
		
	}
	
	private void heapify(int index) {
		
		int indexMax = this.indexOfMax(index, this.left(index), this.right(index));
		
		if (indexMax != index) {
			
			swap(this.heap, index, indexMax);
			
			heapify(indexMax);
			
		}
		
	}
	
	private int parent(int index) {
		
		if (index == 0)
			return -1;
		
		return (index - 1)/2;
		
	}
	
	private int left(int index) {
		
		return 2 * index + 1;
		
	}
	
	private int right(int index) {
		
		return 2 * index + 2;
		
	}
	
	private int indexOfMax(int index1, int index2, int index3) {
		
		return 0;
		
	}
	
}
