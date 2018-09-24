// Copy paste this Java Template and save it as "HospitalRenovation.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line) <- generate a new hash code

class HospitalRenovation {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private int[][] AdjMatrix; // the graph (the hospital)
  private int[] RatingScore; // the weight of each vertex (rating score of each room)
  private int[] low;
  private int[] predecessor;
  private int[] discoverTime;
  private int[] visited;
  private int time;
  private int ans;
  private ArrayList<Integer> cutVertices;
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  public HospitalRenovation() {
    // Write necessary code during construction
    //
    // write your answer here
    


  }
  void modifiedDfs(int initial) {
	  
	  visited[initial]=1;
	  discoverTime[initial]=++time;
	  low[initial]=discoverTime[initial];
	  ArrayList<Integer> children=new ArrayList<Integer>();
	  for(int k=0;k<V;k++) {
		  if(AdjMatrix[initial][k]==1) {
		      int vertex=k;
		      if(visited[vertex]==0) {
			      predecessor[vertex]=initial;
			      children.add(vertex);
			      modifiedDfs(vertex);
			      low[initial]=Math.min(low[initial], low[vertex]);
			     
			    	
			  
		      }
		      else if(vertex!=predecessor[initial]) {
			      low[initial]=Math.min(low[initial],discoverTime[vertex] );
		      }
	      }
	  }
	  if(predecessor[initial]==-1 && children.size()>1) {
		  cutVertices.add(initial);
		  if(RatingScore[initial]<ans)
			  ans=RatingScore[initial];
		 
	  }
	  else {
		  for(int idx=0;idx<children.size();idx++) {
			  int currVertex=children.get(idx);
			  if(low[currVertex]>=discoverTime[initial]) {
				  cutVertices.add(initial);
				  if(RatingScore[initial]<ans)
					  ans=RatingScore[initial];
			      break;
			  }    
		 	  
		  } 
		  
	  }
  }


  int Query() {
	
    

    // You have to report the rating score of the critical room (vertex)
    // with the lowest rating score in this hospital
    //
    // or report -1 if that hospital has no critical room
    //
    // write your answer here
    if(V==1)
    	return -1;

    modifiedDfs(0);
    

    
    if(cutVertices.size()==0)
    	return -1;
    /*ans=RatingScore[cutVertices.get(0)];
    for(int i=1;i<cutVertices.size();i++) {
    	if(RatingScore[cutVertices.get(i)]<ans)
    		ans=RatingScore[cutVertices.get(i)];
    }*/
    


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
      ans=Integer.MAX_VALUE;
      time=0;
      visited=new int[V];
	  low=new int[V];
	  predecessor=new int[V];
	  discoverTime=new int[V];
	  cutVertices=new ArrayList<Integer>();
	  for(int index=0;index<V;index++) {
		  predecessor[index]=-1;
	  }
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
	//System.setIn(new FileInputStream("testingdata"));
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