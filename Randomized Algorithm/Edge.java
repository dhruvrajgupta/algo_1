import java.util.ArrayList;
import java.util.List;

public class Edge {
    public List<Vertex> ends = new ArrayList<Vertex>();

    public Edge(Vertex v1,Vertex v2){
        if(v1!=null && v2!=null){
            ends.add(v1);
            ends.add(v2);
        }
    }

    public boolean contains(Vertex v1,Vertex v2){
        return (ends.contains(v1)&&ends.contains(v2));
    }

    public Vertex getOppositeVertex(Vertex v2){
        return ends.get(1-ends.indexOf(v2));
    }

    public void replaceVertex(Vertex oldV, Vertex newV){
        ends.remove(oldV);
        ends.add(newV);
    }
}
