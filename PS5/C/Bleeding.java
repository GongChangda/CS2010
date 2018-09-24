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
  private static int infinity=1000000000;
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



    //------------------------------------------------------------------------- 
  }
  int ModifiedDijkstra(int s,int t, int k) {
	  // the following is initializaton
	  PriorityQueue<IntegerTriple> queue=new PriorityQueue<IntegerTriple>();
	  queue.offer(new IntegerTriple(s,0,1));
	  int[][] distanceArray=new int[V][k+1]; // the first column is not used at all so we have to have k+1 columns to hold shortest path estimate with time ranging from 1 to k.
	  
	  for(int i=0;i<V;i++) {
		  for(int j=1;j<k+1;j++) {
			  distanceArray[i][j]=infinity;
		  }
	  }
	  distanceArray[s][1]=0;
	  // In the following while loop, one vertex can be enqueued multiple times.
	  while(queue.isEmpty()==false) {
		  IntegerTriple curr=queue.poll();
		  if(curr.first()==t)
			  return curr.second();
		  if(curr.third()==k) // Number of junctions via this route will definitely exceed k, we ignore it.
			  continue;
		  Vector<IntegerPair> neighbors=AdjList.get(curr.first());
		  for(int index=0;index<neighbors.size();index++) {
			  IntegerPair neighbor=neighbors.get(index);
			  if(distanceArray[neighbor.first()][curr.third()+1]>distanceArray[curr.first()][curr.third()]+neighbor.second()) {
				  distanceArray[neighbor.first()][curr.third()+1]=distanceArray[curr.first()][curr.third()]+neighbor.second();
				  queue.offer(new IntegerTriple(neighbor.first(),distanceArray[neighbor.first()][curr.third()+1],curr.third()+1));
			  }
				  
		  }
	  }
	  
	  
	  
	  return -1; //we cannot find a path within k junctions.
  }
  
  
  
  int Query(int s, int t, int k) {
    

    // You have to report the shortest path from Ket Fah's current position s
    // to reach the chosen hospital t, output -1 if t is not reachable from s
    // with one catch: this path cannot use more than k vertices      
    //
    // write your answer here



    //------------------------------------------------------------------------- 

    return ModifiedDijkstra(s,t,k);
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
	//System.setIn(new FileInputStream("test"));
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

class IntegerTriple implements Comparable<IntegerTriple>{
	Integer _first,_second, _third;
	// _first: vertex number _second: distance estimate _third: number of junctions needed to achieve the distance estimate.
	public IntegerTriple(Integer first, Integer second, Integer third) {
		_first=first;
		_second=second;
		_third=third;
	}
	public int compareTo(IntegerTriple another){
		if(!this.second().equals(another.second()))
			return this.second()-another.second();
		else
			return this.first()-another.first();
	}
	public Integer first() {
		return _first;
	}
	public Integer second() {
		return _second;
	}
	public Integer third() {
		return _third;
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