package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T> > extends BinaryTree<T> {
	
	/**
	 * @see tree.BinaryTree#insert(Object)
	 */
	@Override
	public void insert(T element) {
		
		if (this.isEmpty()) {
			
			this.root = new BinaryNode<T>(element);
			
		} else {
			
			this.insert(element, this.root);
			
		}
		
	}

	private void insert(T element, BinaryNode<T> node) {
		
		int compareValue = element.compareTo(node.getData());
		
		if (compareValue != 0) {
			
			if (compareValue < 0) {
				
				if (node.getLeft() == null) {
					
					node.setLeft(new BinaryNode<T>(element, node));
					
				} else {
					
					this.insert(element, node.getLeft());
					
				}
				
			} else {
				
				if (node.getRight() == null) {
					
					node.setRight(new BinaryNode<T>(element, node));
					
				} else {
					
					this.insert(element, node.getRight());
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * @see tree.BinaryTree#contains(Object)
	 */
	@Override
	public boolean contains(T element) {
		
		return this.search(element, this.root) != null;
		
	}
	
	/**
	 * @see tree.BinaryTree#search(Object)
	 */
	@Override
	public BinaryNode<T> search(T element) {
		
		return this.search(element, this.root);
		
	}
	
	private BinaryNode<T> search(T element, BinaryNode<T> node) {
		
		BinaryNode<T> found = null;
		
		if (node != null) {
			
			int compareValue = element.compareTo(node.getData());
			
			if (compareValue == 0) {
				
				found = node;
				
			} else if (compareValue < 0) {
				
				found = this.search(element, node.getLeft());
				
			} else {
				
				found = this.search(element, node.getRight());
				
			}
			
		}
		
		return found;
		
	}
	
	/**
	 * @see tree.BinaryTree#remove(Object)
	 */
	@Override
	public void remove(T element) {
		
		BinaryNode<T> node = this.search(element);
		
		if (node != null) {
			
			if (node.exitDegree() == 0) {
				
				if (node.getParent() == null) {
					
					this.root = null;
					
				} else {
				
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(null);
					} else {
						node.getParent().setRight(null);
					}
					
				}
				
			} else if (node.exitDegree() == 1) {
				
				if (node.getParent() == null) {
					
					if (node.getLeft() != null) {
						
						node.getLeft().setParent(null);
						this.root = node.getLeft();
						
					} else {
						
						node.getRight().setParent(null);
						this.root = node.getRight();
						
					}
					
				} else {
					
					
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						
						if (node.getLeft() != null) {
							
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
							
						} else {
							
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
							
						}
						
					} else {
						
						if (node.getLeft() != null) {
							
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
							
						} else {
							
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
							
						}
						
					}
					
				}
				
			} else {
				
				BinaryNode<T> suc = this.successor(element);
				
				node.setData(suc.getData());
				
				remove(suc.getData());
				
			}
			
		}
		
		
	}
	
	/**
	 * @see tree.BinaryTree#predecessor(Object)
	 */
	@Override
	public BinaryNode<T> predecessor(T data) {
		
		BinaryNode<T> node = this.search(data);
		
		if (node != null) {
			
			if (node.getLeft() != null) {
				
				return max(node.getLeft());
			
			} else {
				
				while (node != null && node.getParent().getData().compareTo(node.getData()) > 0) {

					node = node.getParent();
					
				}
				
				return node.getParent();
				
			}
			
		}
		
		return node;
		
	}
	
	/**
	 * @see tree.BinaryTree#successor(Object)
	 */
	@Override
	public BinaryNode<T> successor(T data) {
		
		BinaryNode<T> node = this.search(data);
		
		if (node != null) {
			
			if (node.getRight() != null) {
				
				return min(node.getRight());
			
			} else {
				
				while (node != null && node.getParent().getData().compareTo(node.getData()) < 0) {

					node = node.getParent();
					
				}
				
				return node.getParent();
				
			}
			
		}
		
		return node;

		
	}
	
	/**
	 * @see tree.BinaryTree#inOrderArray()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] inOrderArray() {
		List<T> list = new ArrayList<>();
		inOrderArray(this.root, list);
		return (T[]) list.toArray();
	}
	
	private void inOrderArray(BinaryNode<T> node, List<T> list) {
		
		if (node.getLeft() != null)
			inOrderArray(node.getLeft(), list);
		
		list.add(node.getData());
		
		if (node.getRight() != null)
			inOrderArray(node.getRight(), list);

	}

	/**
	 * @see tree.BinaryTree#preOrderArray()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrderArray() {
		List<T> list = new ArrayList<>();
		preOrderArray(this.root, list);
		return (T[]) list.toArray();
	}
	
	private void preOrderArray(BinaryNode<T> node, List<T> list) {
		
		list.add(node.getData());
		
		if (node.getLeft() != null)
			inOrderArray(node.getLeft(), list);
		
		if (node.getRight() != null)
			inOrderArray(node.getRight(), list);

	}
	
	/**
	 * @see tree.BinaryTree#postOrderArray()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrderArray() {
		List<T> list = new ArrayList<>();
		postOrderArray(this.root, list);
		return (T[]) list.toArray();
	}
	
	private void postOrderArray(BinaryNode<T> node, List<T> list) {
		
		if (node.getLeft() != null)
			inOrderArray(node.getLeft(), list);
		
		if (node.getRight() != null)
			inOrderArray(node.getRight(), list);
		
		list.add(node.getData());

	}
	
	/**
	 * @see tree.BinaryTree#size()
	 */
	@Override
	public int size() {
		
		return this.size(this.root);
		
	}

	private int size(BinaryNode<T> node) {
		
		int size = 0;
		
		if (node != null) {
			
			size = 1 + this.size(node.getLeft()) + this.size(node.getRight());
			
		}
		
		return size;
		
	}
	
	/**
	 * @see tree.BinaryTree#height()
	 */
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Get the value of the minimum element in the tree.
	 * 
	 * @return
	 * 		Value of the minimum element in the tree.
	 */
	public T min() {
		
		if (this.isEmpty()) {
			
			return null;
			
		}
		
		return this.min(this.root).getData();
		
	}
	
	private BinaryNode<T> min(BinaryNode<T> node) {
		
		if (node.getLeft() == null) {
			
			return node;
			
		}
		
		return this.min(node.getLeft());
		
	}

	/**
	 * Get the value of the maximum element in the tree.
	 * 
	 * @return
	 * 		Value of the maximum element in the tree.
	 */
	public T max() {
		
		if (this.isEmpty()) {
			
			return null;
			
		}
		
		return this.max(this.root).getData();
		
	}
	
	private BinaryNode<T> max(BinaryNode<T> node) {
		
		if (node.getRight() == null) {
			
			return node;
			
		}
		
		return this.max(node.getRight());
		
	}
	
	/**
	 * Get the floor of the given element in the tree.
	 * 
	 * @param element
	 * 		Element with floor has to be picked up.
	 * 
	 * @return
	 * 		Element's floor.
	 */
	public T floor(T element) {
		return null;
	}
	
	/**
	 * Get the ceiling of the given element in the tree.
	 * 
	 * @param element
	 * 		Element with ceiling has to be picked up.
	 * 
	 * @return
	 * 		Element's ceiling.
	 */
	public T ceiling(T element) {
		return null;
	}
	
	/**
	 * Remove the node with minimum value of the tree.
	 */
	public void removeMin() {
		
		if (!this.isEmpty()) {
			
			if (this.root.exitDegree() == 0 || this.root.getLeft() == null) {
				
				this.root = this.root.getRight();
				
			} else {
				
				this.removeMin(this.root, this.root.getLeft());
				
			}
			
		}
		
	}
	
	private void removeMin(BinaryNode<T> parentNode, BinaryNode<T> node) {
		
		if (node.getLeft() == null) {
			
			parentNode.setLeft(node.getRight());
			
		} else {
			
			this.removeMin(node, node.getLeft());
			
		}
		
	}
	
	/**
	 * Remove the node with maximum value of the tree.
	 */
	public void removeMax() {
		
		if (!this.isEmpty()) {
			
			if (this.root.exitDegree() == 0 || this.root.getRight() == null) {
				
				this.root = this.root.getLeft();
				
			} else {
				
				this.removeMax(this.root, this.root.getRight());
				
			}
			
		}
		
	}

	private void removeMax(BinaryNode<T> parentNode, BinaryNode<T> node) {
		
		if (node.getRight() == null) {
			
			parentNode.setRight(node.getLeft());
			
		} else {
			
			this.removeMin(node, node.getRight());
			
		}
		
		
	}

}
