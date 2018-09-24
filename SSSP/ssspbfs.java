import java.util.*;

public class ssspbfs {



}
 


class algorithm{
	
	
	
	public void modifiedbfs(int source, ArrayList<ArrayList<Integer>> adjList) {
		Queue<Integer> queue=new LinkedList<Integer>();
		queue.offer(source);
		int V=adjList.size();
		int[] distance=new int[V];
		distance[source]=0;
		for(int i=0;i<V;i++) {
			if(i!=source) {
				distance[i]=V+10;
			}
		}
		while(queue.isEmpty()==false) {
			int curr=queue.poll();
			ArrayList<Integer> neighbors=adjList.get(curr);
			int currDistance=distance[curr];
			for(int j=0;j<neighbors.size();j++) {
				if(distance[neighbors.get(j)]==V+10) {
					distance[neighbors.get(j)]=currDistance+1;
					queue.offer(neighbors.get(j));
				}
			}
		}
	}
	
	public void bellmanFord(int source, ArrayList<IntegerTriple> edgeList) {
		
		
	}
}

class IntegerTriple{
	int first;
	int second;
	int third;
	
	public Integer
	
}