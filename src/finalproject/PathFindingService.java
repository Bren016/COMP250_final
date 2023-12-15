package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;
	
	public PathFindingService(Tile start) {
    	this.source = start;
        generateGraph();
    }

	public abstract void generateGraph();
    
    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        setAllCostEstimatesToInfinity(startNode);
        startNode.isStart = true;
        TilePriorityQ Q = new TilePriorityQ(g.vertices);
        Tile destination = null;
        while (!Q.isEmpty() && destination == null) {
            Tile u = Q.removeMin();
            for (Tile v : g.getNeighbors(u)) {
                if (v.isWalkable()) {
                    if (v.isDestination) {
                        destination = v;
                    }
                    relax(u, v, g.findEdge(u, v).weight, g.vertices);
                }
            }
        }
        ArrayList<Tile> path = new ArrayList<>();
        Tile cur = destination;
        while (cur != null) {
            path.add(0, cur);
            cur = cur.predecessor;
        }
        return path;

    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
        setAllCostEstimatesToInfinity(start);
        System.out.println("--- Print vertices ---");
        for (Tile tile : g.vertices) {
            System.out.println(tile);
        }
        System.out.println("--- done ---\n");
        TilePriorityQ Q = new TilePriorityQ(g.vertices);
        Tile destination = null;
        while (!Q.isEmpty()) {
            Tile u = Q.removeMin();
            for (Tile v : g.getNeighbors(u)) {
                if (v.isWalkable()) {
                    if (v.equals(end)) {
                        destination = v;
                    }
                    relax(u, v, g.findEdge(u, v).weight, g.vertices);
                }
            }
        }
        ArrayList<Tile> path = new ArrayList<>();
        Tile cur = end;
        while (cur != null) {
            path.add(0, cur);
            cur = cur.predecessor;
        }
        return path;
    }


    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
    	ArrayList<Tile> path = new ArrayList<>();
        Tile end = null;
        for (Tile tile : g.vertices) {
            if (tile.isDestination) {
                end = tile;
                break;
            }
        }
        waypoints.addFirst(start);
        waypoints.addLast(end);
        for (int i = 0; i < waypoints.size() - 1; i++) {
            path.addAll(findPath(waypoints.get(i), waypoints.get(i + 1)));
        }
        return path;
    }


    private void relax(Tile u, Tile v, double weight, ArrayList<Tile> S) {
        TilePriorityQ Q = new TilePriorityQ(S);
        /*
        System.out.println("In relax");
        System.out.println("v.costEstimate: " + v.costEstimate);
        System.out.println("u.costEstimate: " + u.costEstimate);
        System.out.println("weight u -> v: " + weight);
         */
        if (v.costEstimate > u.costEstimate + weight) {
            //System.out.println("About to update keys");
            Q.updateKeys(v, u, u.costEstimate + weight);
            //System.out.println("v.costEstimate: " + v.costEstimate);
            //System.out.println("u.costEstimate: " + u.costEstimate);
        }
    }

    public void setAllCostEstimatesToInfinity(Tile startNode) {
        for (Tile tile : g.vertices) {
            if (tile != startNode) {
                tile.costEstimate = Double.POSITIVE_INFINITY;
            }
        }
    }

}

