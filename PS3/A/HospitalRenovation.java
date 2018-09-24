// Copy paste this Java Template and save it as "HospitalRenovation.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0162477X
// write your name here:Gong Changda
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line) <- generate a new hash code

class HospitalRenovation {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private int[][] AdjMatrix; // the graph (the hospital)
  private int[] RatingScore; // the weight of each vertex (rating score of each room)

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  public HospitalRenovation() {
    // Write necessary code during construction
    //
    // write your answer here



  }
  public int[] BFS(int[][] matrix, int exclusion,int initial) {
	  int numOfVertices=V;
	  int[] visit=new int[numOfVertices];
	  Queue<Integer> queue=new LinkedList<Integer>();
	  visit[initial]=1;
	  visit[exclusion]=1;
	  queue.offer(initial);
	  while(queue.isEmpty()==false) {
		  int curr=queue.poll();
		  ArrayList<Integer> neighbors=new ArrayList<Integer>();
		  for(int i=0;i<numOfVertices;i++) {
			  if(i!=exclusion) {
				  if(matrix[curr][i]==1) {
					  neighbors.add(i);
				  }
			  }
		  }
		  for(int j=0;j<neighbors.size();j++) {
			  int vertex=neighbors.get(j);
			  if(visit[vertex]==0) {
				  queue.offer(vertex);
				  visit[vertex]=1;
			  }
		  }
	  }
	  return visit;
  }
  ArrayList<Integer> getCritical() {
	  ArrayList<Integer> result=new ArrayList<Integer>();
	  for(int i=0;i<V;i++) {
		  int[] visited;
		  if(i==0)
			  visited=BFS(AdjMatrix,0,1);
		  else
			  visited=BFS(AdjMatrix,i,0);
		  for(int j=0;j<visited.length;j++) {
			  if(visited[j]==0) {
				  result.add(i);
				  break;
			  }
		  }
	  }
	  return result;
  }
  int Query() {
    int ans = 0;

    // You have to report the rating score of the critical room (vertex)
    // with the lowest rating score in this hospital
    //
    // or report -1 if that hospital has no critical room
    //
    // write your answer here
    if(V==1) {
    	return -1;
    }
    ArrayList<Integer> cutVertices;
    cutVertices=getCritical();
    if(cutVertices.size()==0)
    	return -1;
    ans=RatingScore[cutVertices.get(0)];
    for(int index=1;index<cutVertices.size();index++) {
    	if(RatingScore[cutVertices.get(index)]<ans)
    		ans=RatingScore[cutVertices.get(index)];
    }

    return ans;
  }

  // You can add extra function if needed
  // --------------------------------------------



  // --------------------------------------------
  
  void run() throws Exception {
    // for this PS3, you can alter this method as you see fit

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int TC = Integer.parseInt(br.readLine()); // there will be several test cases
    while (TC-- > 0) {
      br.readLine(); // ignore dummy blank line
      V = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
      RatingScore = new int[V];
      for (int i = 0; i < V; i++)
        RatingScore[i] = Integer.parseInt(st.nextToken());

      // clear the graph and read in a new graph as Adjacency Matrix
      AdjMatrix = new int[V][V];
      for (int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
          int j = Integer.parseInt(st.nextToken());
          AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
        }
      }

      pr.println(Query());
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
	System.setIn(new FileInputStream("testingdata"));
    HospitalRenovation ps3 = new HospitalRenovation();
    ps3.run();
  }
}



class IntegerPair implements Comparable < IntegerPair > {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}