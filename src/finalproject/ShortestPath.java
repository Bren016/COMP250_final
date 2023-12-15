package finalproject;


import finalproject.system.Tile;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
        generateGraph();
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        this.g = new Graph(GraphTraversal.DFS(this.source));
        //this.g = new Graph(null);
        for (Tile vertex : this.g.vertices) {
            for (Tile neighbour : vertex.neighbors) {
                if (neighbour.isWalkable()) {
                    double distance = neighbour.distanceCost;
                    this.g.addEdge(vertex, neighbour, distance);
                }
            }
        }
	}
    
}