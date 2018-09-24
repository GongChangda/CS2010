// Copy paste this Java Template and save it as "Bleeding.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0162477X
// write your name here: GONG CHANGDA
// write list of collaborators here:
// year 2018 hash code: h7my92W5hykxBmUZwVxB (do NOT delete this line)

class Bleeding {
  private int V; // number of vertices in the graph (number of junctions in Singapore map)
  private int Q; // number of queries
  private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge
  private int[][] distanceArray;
  private static int infinity;
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------



  // --------------------------------------------

  public Bleeding() {
    // Write necessary code during construction
    //
    // write your answer here



  }

  void PreProcess() {
    // Write necessary code to preprocess the graph, if needed
    //
    // write your answer here
    //------------------------------------------------------------------------- 
    distanceArray=new int[10][];
    infinity=1000*1001;
    for(int k=0;k<Math.min(10,V);k++) {
    	ModifiedDijkstra(k);
    }
    //------------------------------------------------------------------------- 
  }
  void ModifiedDijkstra(int source) {
	  int[] distance=new int[V];
	  for(int i=0;i<V;i++) {
		  distance[i]=infinity;
	  }
	  distance[source]=0;
	  PriorityQueue<IntegerPair> queue=new PriorityQueue<IntegerPair>();
	  queue.offer(new IntegerPair(0,source));
	  while (queue.isEmpty()==false) {
		  IntegerPair curr=queue.poll();
		  int dist=curr.first();
		  int vertex=curr.second();
		  if(dist==distance[vertex]) {
			  Vector<IntegerPair> neighbors=AdjList.get(vertex);
			  for(int j=0;j<neighbors.size();j++) {
				  IntegerPair edge=neighbors.get(j);
				  int v=edge.first();
				  int weight=edge.second();
				  if(distance[v]>dist+weight) {
					  distance[v]=dist+weight; //relaxation
					  queue.offer(new IntegerPair(distance[v],v));
				  }
			  }
		  }
	  }
	  distanceArray[source]=distance;
  }
  int Query(int s, int t, int k) {
    int ans = -1;

    // You have to report the shortest path from Ket Fah's current position s
    // to reach the chosen hospital t, output -1 if t is not reachable from s
    // with one catch: this path cannot use more than k vertices      
    //
    // write your answer here
    int[] Distance=distanceArray[s];
    int result=Distance[t];
    if(result>=infinity) {
    	return -1;
    }
    ans=result;

    //------------------------------------------------------------------------- 

    return ans;
  }

  // You can add extra function if needed
  // --------------------------------------------



  // --------------------------------------------

  void run() throws Exception {
    // you can alter this method if you need to do so
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
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (in minutes) is stored here
        }
      }

      PreProcess(); // optional

      Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

      if (TC > 0)
        pr.println();
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
	//System.setIn(new FileInputStream("testcase"));
    Bleeding ps5 = new Bleeding();
    ps5.run();
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