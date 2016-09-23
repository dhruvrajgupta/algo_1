import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    public Map<Integer,Vertex> vertices = new TreeMap<Integer,Vertex>();
    public List<Edge> edges = new ArrayList<Edge>();

    public void addVertex(Vertex v){
        vertices.put(v.lbl,v);
    }

    public Vertex getVertex(int lbl){
        Vertex v;
        if((v=vertices.get(lbl))==null){
            v = new Vertex(lbl);
            addVertex(v);
        }
        return v;
    }
}
