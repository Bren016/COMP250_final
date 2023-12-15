package finalproject;

import java.util.ArrayList;
import java.util.HashSet;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.*;

public class Graph {
	// TODO level 2: Add fields that can help you implement this data type
    public ArrayList<Tile> vertices;
    private ArrayList<Edge> edges;

    // TODO level 2: initialize and assign all variables inside the constructor
	public Graph(ArrayList<Tile> vertices) {
		this.vertices = vertices;
        this.edges = new ArrayList<>();
	}
	
    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
    	Edge newEdge = new Edge(origin, destination, weight);
        edges.add(newEdge);
    }
    
    // TODO level 2: return a list of all edges in the graph
	public ArrayList<Edge> getAllEdges() {
		return edges;
	}
  
	// TODO level 2: return list of tiles adjacent to t
	public ArrayList<Tile> getNeighbors(Tile t) {
        ArrayList<Tile> neighbours = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getStart().equals(t)) {
                neighbours.add(edge.getEnd());
            }
        }
    	return neighbours;
    }
	
	// TODO level 2: return total cost for the input path
	public double computePathCost(ArrayList<Tile> path) {
        double totalCost = 0.0;
        for (int i = 1; i < path.size(); i++) {
            Tile cur = path.get(i - 1);
            Tile next = path.get(i);
            Edge edge = findEdge(cur, next);

            if (edge != null) {
                totalCost += edge.weight;
            }
        }
		return totalCost;
	}

    public Edge findEdge(Tile start, Tile end) {
        for (Edge edge : edges) {
            if ( edge.getStart().equals(start) && edge.getEnd().equals(end)) {
                return edge;
            }
        }
        return null;
    }
	
   
    public static class Edge{
    	Tile origin;
    	Tile destination;
    	double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
        	this.origin = s;
            this.destination = d;
            this.weight = cost;
        }
        
        // TODO level 2: getter function 1
        public Tile getStart(){
            return origin;
        }

        
        // TODO level 2: getter function 2
        public Tile getEnd() {
            return destination;
        }
        
    }

   }