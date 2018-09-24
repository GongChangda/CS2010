import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class bfs {
    public static void main(String [] args) throws FileNotFoundException {
    	bfs instance=new bfs();
    	ArrayList<ArrayList<Integer>> adjList=new ArrayList<ArrayList<Integer>>();
    	System.setIn(new FileInputStream("adjList"));
    	Scanner sc=new Scanner(System.in);
    	
    	while(true) {
    		int number=sc.nextInt();
    		if(number==0) {
    			break;
    		}	
    		else {
    			ArrayList<Integer> neighbors=new ArrayList<Integer>();
    			for(int i=0;i<number;i++) {
    				int neighbor=sc.nextInt();
    				neighbors.add(neighbor);
    			}
    			adjList.add(neighbors);
    		}
    	}
    	ArrayList<int[]> result=instance.sssp(0,adjList);
    	int[] distance=result.get(0);
    	int[] parent=result.get(1);
    	instance.printArray(distance);
    	instance.printArray(parent);
    	
    }
  
    public void printArray(int[] arr) {
    	for(int i=0;i<arr.length;i++) {
    		System.out.print(arr[i]+" ");
    	}
    	System.out.println();
    }
    public ArrayList<int[]> sssp(int source, ArrayList<ArrayList<Integer>> adjList){
	   Queue<Integer> queue=new LinkedList<Integer>();
	   queue.offer(source);
	   int V=adjList.size();
	   int[] parent=new int[V];
	   int[] distance=new int[V];
	   int[] visited=new int[V];
	   // initialize the distance array.
	   for(int i=0;i<V;i++) {
		   if(i!=source)
			   distance[i]=1000; //1000 represents infinity here.
		   parent[i]=-1;
			   
	   }
	   while(queue.isEmpty()==false) {
		   int curr=queue.poll();
		   ArrayList<Integer> neighbors=adjList.get(curr);
		   for(int j=0;j<neighbors.size();j++) {
			   int neighbor=neighbors.get(j);
			   if(neighbor==0)
				   continue;
			   if(visited[neighbor]==0) {
				   parent[neighbor]=curr;
				   distance[neighbor]=distance[curr]+1;
				   visited[neighbor]=1;
				   queue.offer(neighbor);
			   }
		   }
		   
	   }
	   ArrayList<int[]> result=new ArrayList<int[]>();
	   result.add(distance);
	   result.add(parent);
	   return result;
    }
}


