// Copy paste this Java Template and save it as "GettingFromHereToThere.java"
import java.util.*;
import java.io.*;

// write your matric number here:A0162477X
// write your name here: GONG CHANGDA
// write list of collaborators here: professor Zimmermann, Lab TA Mukesh, Wang Jinyi.
// year 2018 hash code: FVZ7FKgjKBghyMkHGu39 (do NOT delete this line) <-- change this

class GettingFromHereToThere {
  private int V; // number of vertices in the graph (number of rooms in the building)
  private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------

  private ArrayList<ArrayList<IntegerPair>> mst; 
  private int[] visited;
  private int[][] result;

 

  // --------------------------------------------

  public GettingFromHereToThere() {
    // Write necessary codes during construction;
    //
    // write your answer here



  }
  void printMST() {
	  for(int i=0;i<mst.size();i++) {
		  ArrayList<IntegerPair> neighbors=mst.get(i);
		  
		  for(int j=0;j<neighbors.size();j++) {
			  System.out.print(neighbors.get(j).first()+" ");
		  }
		  System.out.println();
	  }
  }
  
  // In preprocess we want to construct edge list out of adjacency list.
  void PreProcess() {
	visited=new int[V];
	result=new int[Math.min(10, V)][V];
	mst=new ArrayList<ArrayList<IntegerPair>>();
	//below is Prim's algorithm to build MST.
	int[] visitArray=new int[V];
	visitArray[0]=1;
	for(int i=0;i<V;i++) {
		mst.add(new ArrayList<IntegerPair>());
	}
	PriorityQueue<IntegerTriple> queue=new PriorityQueue<IntegerTriple>();
	for(int j=0;j<AdjList.get(0).size();j++) {
		IntegerPair curr=AdjList.get(0).get(j);
		int end=curr.first();
		int weight=curr.second();
		queue.offer(new IntegerTriple(0,end,weight));
	}
	while(queue.isEmpty()==false) {
		IntegerTriple currEdge=queue.poll();
		if(visitArray[currEdge.second()]==0) {
			int starting=currEdge.first();
			int ending=currEdge.second();
			int weight=currEdge.third();
			mst.get(starting).add(new IntegerPair(ending,weight));
			mst.get(ending).add(new IntegerPair(starting,weight));
			visitArray[ending]=1;
			
			for(int i=0;i<AdjList.get(currEdge.second()).size();i++) {
				IntegerPair edge=AdjList.get(currEdge.second()).get(i);
				queue.offer(new IntegerTriple(currEdge.second(),edge.first(),edge.second()));
			}
		}
	}
  
	
	
	
    // write your answer here
    // you can leave this method blank if you do not need it
    
    
    
    for(int source=0;source<Math.min(10,V);source++) {
        restoreVisited();
    	BFS(source);
    }
  }
  public void restoreVisited() {
	  for(int i=0;i<V;i++) {
		  visited[i]=0;
	  }
  }
  public void BFS(int source) {
	  Queue<Integer> queue=new LinkedList<Integer>();
	  queue.offer(source);
	  while(queue.isEmpty()==false) {
		  int curr=queue.poll();
		  ArrayList<IntegerPair> neighbors=mst.get(curr);
		  for(int i=0;i<neighbors.size();i++) {
			  
			  IntegerPair neighbor=neighbors.get(i);
			  int end=neighbor.first();
			  if(visited[end]==0) {
				  visited[end]=1;
			      int weight=neighbor.second();
			      queue.offer(end);
			      result[source][end]=Math.max(weight, result[source][curr]);
			  }
		  }
	  }
  }

  

  int Query(int source, int destination) {
    int ans = 0;

    // You have to report the weight of a corridor (an edge)
    // which has the highest effort rating in the easiest path from source to destination for the wheelchair bound
    //
    // write your answer here
    ans=result[source][destination];
    return ans;
  }

  // You can add extra function if needed
  // --------------------------------------------



  // --------------------------------------------

  void run() throws Exception {
    // do not alter this method
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // clear the graph and read in a new graph as Adjacency List
      AdjList = new Vector < Vector < IntegerPair > >();
      for (int i = 0; i < V; i++) {
        AdjList.add(new Vector < IntegerPair >());

        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt(), w = sc.nextInt();
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
        }
      }

      PreProcess(); // you may want to use this function or leave it empty if you do not need it

      int Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt()));
      pr.println(); // separate the answer between two different graphs
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
	//System.setIn(new FileInputStream("testcaseB"));

    GettingFromHereToThere ps4 = new GettingFromHereToThere();
    ps4.run();
  }
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }
  
  public int nextInt() {
    int result = 0;
    try {
      int cur = bis.read();
      if (cur == -1)
        return -1;

      while ((cur < 48 || cur > 57) && cur != 45) {
        cur = bis.read();
      }

      boolean negate = false;
      if (cur == 45) {
        negate = true;
        cur = bis.read();
      }

      while (cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }

      if (negate) {
        return -result;
      }
      return result;
    }
    catch (IOException ioe) {
      return -1;
    }
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



class IntegerTriple implements Comparable < IntegerTriple > {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.third().equals(o.third()))
      return this.third() - o.third();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.first() - o.first();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
  public String toString() {
	  return "("+_first+","+_second+","+_third+")";
  }

}

