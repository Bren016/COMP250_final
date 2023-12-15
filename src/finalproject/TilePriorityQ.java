package finalproject;

import java.util.ArrayList;
import java.util.Arrays;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	private ArrayList<Tile> priorityQ;

	// TODO level 3: implement the constructor for the priority queue
		public TilePriorityQ (ArrayList<Tile> vertices) {
			priorityQ = vertices;
			buildMinHeap();
	}
	
	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		if (priorityQ.isEmpty()) {
			return null;
		}
		Tile min = priorityQ.set(0, priorityQ.get(priorityQ.size() - 1));
		priorityQ.remove(priorityQ.size() - 1);
		minHeapify(0);
		return min;
	}
	
	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
			for (int i = 0; i < this.priorityQ.size(); i++) {
				if (this.priorityQ.get(i).equals(t)) {
					t.predecessor = newPred;
					t.costEstimate = newEstimate;
					minHeapify(i);
				}
			}
	}

	private void buildMinHeap() {
			int n = this.priorityQ.size();
			for (int i = n/2 -1; i >= 0; i--) {
				minHeapify(i);
			}
	}

	private void minHeapify(int index) {
			int smallest = index;
			int left = 2 * index + 1;
			int right = 2 * index + 2;

			if (left < priorityQ.size() && priorityQ.get(left).costEstimate < priorityQ.get(smallest).costEstimate) {
				smallest = left;
			}

			if (right < priorityQ.size() && priorityQ.get(right).costEstimate < priorityQ.get(smallest).costEstimate) {
				smallest = right;
			}

			if (smallest != index) {
				Tile temp = priorityQ.get(index);
				priorityQ.set(index, priorityQ.get(smallest));
				priorityQ.set(smallest, temp);
				minHeapify(smallest);
			}
	}

	public boolean isEmpty(){
			return priorityQ.isEmpty();
	}

}
