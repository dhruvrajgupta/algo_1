import java.util.HashSet;
import java.util.Set;

public class Vertex {
    int lbl;
    public Set<Edge> edges = new HashSet<Edge>();

    public Vertex(int lbl){
        this.lbl = lbl;
    }

    public void addEdge(Edge e){
        edges.add(e);
    }

    public Edge getEdgeTo(Vertex v){
        for(Edge edge : edges){
            if(edge.contains(this,v)){
                return edge;
            }
        }
        return null;
    }
}
