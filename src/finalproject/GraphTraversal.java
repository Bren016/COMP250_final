package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphTraversal
{


	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s) {
		ArrayList<Tile> tilesVisited = new ArrayList<>();
		LinkedList<Tile> tilesToVisit = new LinkedList<>();
		tilesToVisit.addLast(s);
		while (!tilesToVisit.isEmpty()) {
			Tile cur = (tilesToVisit.removeFirst());
			if (!tilesVisited.contains(cur)) {
				tilesVisited.add(cur);

				for (Tile neighbour : cur.neighbors) {
					if (neighbour.isWalkable()){
						tilesToVisit.addLast(neighbour);
					}
				}
			}
		}
		return tilesVisited;
	}


	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		ArrayList<Tile> tilesVisited = new ArrayList<>();
		LinkedList<Tile> tilesToVisit = new LinkedList<>();
		tilesToVisit.addFirst(s);
		while (!tilesToVisit.isEmpty()) {
			Tile cur = (tilesToVisit.removeFirst());
			if (!tilesVisited.contains(cur)) {
				tilesVisited.add(cur);

				for (Tile neighbour : cur.neighbors) {
					if (neighbour.isWalkable()){
						tilesToVisit.addFirst(neighbour);
					}
				}
			}
		}
		return tilesVisited;
	}

}  