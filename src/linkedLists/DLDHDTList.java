package linkedLists;

import java.util.NoSuchElementException;

import linkedLists.AbstractSLList.SNode;
import java.lang.reflect.Array;
public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
     length =0;
     header = new DNode<>(null,null,trailer);
		trailer = new DNode<>(null, header, null);
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nTarget = (DNode<E>) target; 
		DNode<E> nAfter=nTarget.getNext();
		
		nTarget.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nTarget); 
		dnuevo.setNext(nAfter); 
		length++; 
		
		
	
		
		
      
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nTarget = (DNode<E>) target; 
		
		
		dnuevo.setPrev(nTarget.getPrev());
		dnuevo.setNext(nTarget);
		nTarget.getPrev().setNext(dnuevo);
		nTarget.setPrev(dnuevo);
		length++; 
		
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if(((DNode <E>)target).getNext()==trailer){
			throw new NoSuchElementException("List is empty."); 
		}else
		{
		return ((DNode <E>)target).getNext(); 
		}
		
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if(((DNode <E>)target).getPrev()==header){
			throw new NoSuchElementException("No such element.");
		}else{
			return ((DNode <E>)target).getPrev(); 
		}
		
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> targetNode = (DNode<E>) target;
	
		((DNode<E>) targetNode).getNext().setPrev( targetNode.getPrev());
		((DNode<E>)targetNode).getPrev().setNext(targetNode.getNext());
		targetNode = null;
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	public <T1> T1[] toArray(T1[] array) {
		int n=0;
		if(array.length < length) {
			array = (T1[]) Array.newInstance(array.getClass().getComponentType(), length);
		}
		if(array.length > length) {
			for(int y=length;y<array.length;y++) 
				array[y]= null;
			
			while(header!=null) {
				array[n]=(T1) header;
				header=header.getNext();
				n++;
			}
		}
		return array;
	}
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
	      destroy();
		length = 0; 
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}

}
