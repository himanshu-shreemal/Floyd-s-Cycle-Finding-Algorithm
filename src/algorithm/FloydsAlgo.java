package algorithm;

import java.util.HashSet;

/**
 * A very simple linked list implementation Not to use in application, created
 * to show the linked list operations, hence very minimal features
 * 
 *
 */

class Node {

	public Node next;

	public Object data;

	public Node(Object data) {
		this.data = data;
		next = null;
	}

	public Node() {

	}
}

public class FloydsAlgo {

	public static Node head;
	public static Node prev;

	public static void main(String[] args) {

		FloydsAlgo myLinkedList = new FloydsAlgo();

		myLinkedList.push(1);
		myLinkedList.push(2);
		myLinkedList.push(3);
		myLinkedList.push(4);
		myLinkedList.push(5);

		head.next.next.next.next.next = head;

		long startTime = System.nanoTime();
		System.out.println("Has Loop ? " + FloydsCycleFinding(head));
		long endTime = System.nanoTime();
		System.out.println("Time taken by algo " + (endTime - startTime) / 1000);

		startTime = System.nanoTime();
		System.out.println("\nHas Loop check via hashMap ? " + putInHashMap(head));
		endTime = System.nanoTime();
		System.out.println("Time taken by Hashset " + (endTime - startTime) / 1000);
		
		startTime = System.nanoTime();
		System.out.println("\nHas Loop check via without modifying the linked list ? " + detectLoop(head));
		endTime = System.nanoTime();
		System.out.println("Time taken by linked list " + (endTime - startTime) / 1000);

		
	}

	/* Inserts a new Node at last of the list. */
	public void push(int new_data) {
		/*
		 * 1 & 2: Allocate the Node & Put in the data
		 */
		Node new_node = new Node(new_data);

		/* 3. Make next of new Node as head */
		if (head == null) {
			head = new_node;
			prev = head;
			return;
		}
		prev.next = new_node;

		/* 4. Move the head to point to new Node */
		prev = new_node;
	}

	// Implementation of Floyd’s Cycle-Finding Algorithm
	private static boolean FloydsCycleFinding(Node head) {

		Node slow = head;
		Node fast = head;

		boolean loop = false;

		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				loop = true;
				break;
			}
		}

		return loop;
	}

	// The same can be detected with the help of hashing. Which is faster than above
	// algo.
	private static boolean putInHashMap(Node head) {
		boolean hasloop = false;
		HashSet<Node> nodeSet = new HashSet();
		while (head != null) {
			if (nodeSet.contains(head)) {
				hasloop = true;
				break;
			}
			nodeSet.add(head);
			head = head.next;
		}

		return hasloop;
	}

	// Function to detect first node of loop
	// in a linked list that may contain loop
	static boolean detectLoop(Node head) {

		// Create a temporary node
		Node temp = new Node();
		while (head != null) {

			// This condition is for the case
			// when there is no loop
			if (head.next == null) {
				return false;
			}

			// Check if next is already
			// pointing to temp
			if (head.next == temp) {
				return true;
			}

			// Store the pointer to the next node
			// in order to get to it in the next step
			Node nex = head.next;

			// Make next point to temp
			head.next = temp;

			// Get to the next node in the list
			head = nex;
		}

		return false;
	}

}
