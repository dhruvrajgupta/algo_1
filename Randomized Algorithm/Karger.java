import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Karger {

    public static int arr[][];

    public static int minCut(Graph gr){
        Random rnd = new Random();
         while(gr.vertices.size()>2){
             Edge e = gr.edges.remove(rnd.nextInt(gr.edges.size()));
             Vertex v1 = cleanVertex(gr,e.ends.get(0),e);
             Vertex v2 = cleanVertex(gr,e.ends.get(1),e);

             Vertex mergedVertex = new Vertex(v1.lbl);
             redirectEdges(gr,v1,mergedVertex);
             redirectEdges(gr,v2,mergedVertex);
             gr.addVertex(mergedVertex);
         }
         return gr.edges.size();
    }

    public static Vertex cleanVertex(Graph gr,Vertex v,Edge e){
        gr.vertices.remove(v.lbl);
        v.edges.remove(e);
        return v;
    }

    public static void redirectEdges(Graph gr,Vertex fromV, Vertex toV){
        for (Iterator<Edge> iter = fromV.edges.iterator(); iter.hasNext();){
            Edge e = iter.next();
            iter.remove();
            if(e.getOppositeVertex(fromV)==toV){
                toV.edges.remove(e);
                gr.edges.remove(e);
            }else{
                e.replaceVertex(fromV,toV);
                toV.addEdge(e);
            }
        }
    }

    public static void getInput(String fileName)throws IOException{
        Map<Integer,ArrayList<Integer>> vertices = new LinkedHashMap<Integer,ArrayList<Integer>>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while((line=br.readLine())!=null){
            String[] ele = line.split("\t");
            ArrayList<Integer> adjList = new ArrayList<Integer>();
            for(int i=1; i<ele.length; i++){
                adjList.add(Integer.parseInt(ele[i]));
            }
            vertices.put(Integer.parseInt(ele[0]),adjList);
        }

        arr = new int[vertices.size()+1][];
        for(Map.Entry<Integer,ArrayList<Integer>> entry : vertices.entrySet()){
            int vertexNo = entry.getKey();
            ArrayList<Integer> adjList = entry.getValue();
            int[] adj = new int[adjList.size()];
            for(int i=0; i<adjList.size(); i++){
                adj[i]=adjList.get(i);
            }
            arr[vertexNo]=adj;
        }
    }

    static void printInput(){
        for(int i=1; i<arr.length; i++){
            System.out.print(i+":\t");
            for(int j=0; j<arr[i].length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static Graph createGraph(){
        Graph gr = new Graph();
        for(int i=1; i<arr.length; i++){
            Vertex v1 = gr.getVertex(i);
            for(int edgeTo : arr[i]){
                Vertex v2 = gr.getVertex(edgeTo);
                Edge e;
                if((e=v1.getEdgeTo(v2))==null){
                    e = new Edge(v1,v2);
                    gr.edges.add(e);
                    v1.addEdge(e);
                    v2.addEdge(e);
                }
            }
        }
        return gr;
    }

    public static void printGraph(Graph gr){
        System.out.println("Printing Graph : ");
        for(Vertex v : gr.vertices.values()){
            System.out.print(v.lbl+":\t");
            for(Edge e : v.edges){
                System.out.print(e.getOppositeVertex(v).lbl+" ");
            }
            System.out.println();
        }
        System.out.println("Printing Edges : ");
        for(Edge e : gr.edges){
            System.out.println(e.ends.get(0).lbl+"---"+e.ends.get(1).lbl);
        }
    }

    public static void main(String args[]) throws IOException {

        Map<Integer,Integer> stats = new LinkedHashMap<Integer,Integer>();

        getInput("D:\\Geek Stuff\\Coding\\Graphs\\src\\graphinput.txt");
        //printInput();

        int min = arr.length;
        int iter = arr.length*arr.length;
        for(int i=0; i<iter; i++){
            Graph gr = createGraph();
            //printGraph(gr);
            int curMin = minCut(gr);
            min=Math.min(min,curMin);

            Integer counter;
            if((counter = stats.get(curMin))==null){
                counter=0;
            }
            stats.put(curMin,counter+1);
            System.out.println("Current Min : "+curMin);
            printGraph(gr);
        }
        System.out.println("Min : "+min+" stats : "+(stats.get(min)*100/iter)+" %");
    }
}
