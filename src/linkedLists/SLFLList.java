package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		// TODO Auto-generated method stub
		
		((SNode<E>) nuevo).setNext(first);
		
        first=((SNode<E>) nuevo);
        if(length==0) {
        	last=first;
        }
	       length++;
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
	
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo); 
	
		if((((SNode<E>) target)==last)){
			last= (SNode<E>) nuevo;
		}
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		
		((SNode<E>) nuevo).setNext(((SNode<E>) target)); 
		((SNode<E>) target).setNext((SNode<E>) nuevo); 
		((SNode<E>)(this.getNodeBefore(target))).setNext(((SNode<E>) nuevo));
		if((((SNode<E>) target)==first)){
			first= (SNode<E>) nuevo;
		}
		length++; 
	
	}
	
	
	
	
	
	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 
		
		// the linked list is not empty....
		return first;
	}

	public Node<E> getLastNode()throws NoSuchElementException 
		{
			if (length == 0)
				throw new NoSuchElementException("getLastNode(): Empty list."); 
			
				
				return last; 
			
		}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		// Pre: target is a node in the list
				SNode<E> aNode = ((SNode<E>) target).getNext(); 
				if (aNode == null)  
					throw new NoSuchElementException("getNextNode(...) : target is the last node."); 
				else 
					return aNode;
			}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if (target == first)  {
			throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
			
		}
			else{
			SNode<E> pre =first;
			while(pre !=null && pre.getNext() != target){
				pre = pre.getNext();
			}
			return pre;
		}
			
		
	}

	public int length() {
		
		return this.length;
	}
	public <T1> T1[] toArray(T1[] array) {
		int n=0;
		if(array.length < length) {
			array = (T1[]) Array.newInstance(array.getClass().getComponentType(), length);
		}
		if(array.length > length) {
			for(int y=length;y<array.length;y++) 
				array[y]= null;
			
			while(first!=null) {
				array[n]=(T1) first;
				first=first.getNext();
				n++;
			}
		}
		return array;
	}
	public void removeNode(Node<E> target) {
		if (target == first) 
			first = first.getNext(); 
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		length--; 
	}

	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
