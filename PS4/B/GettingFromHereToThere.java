// Copy paste this Java Template and save it as "GettingFromHereToThere.java"
import java.util.*;
import java.io.*;

// write your matric number here:A0162477X
// write your name here: GONG CHANGDA
// write list of collaborators here:
// year 2018 hash code: FVZ7FKgjKBghyMkHGu39 (do NOT delete this line) <-- change this

class GettingFromHereToThere {
  private int V; // number of vertices in the graph (number of rooms in the building)
  private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  private ArrayList<IntegerTriple> edgeList;
  private ArrayList<IntegerTriple> mstEdges;
  private ArrayList<ArrayList<IntegerPair>> mst; 
  private int[] visited;
  private IntegerPair[] parent;
  private int[][] matrix;
  private int[][] result;
  private static boolean flag=false;
  private int size;

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
	parent=new IntegerPair[V];
	visited=new int[V];
	result=new int[V][V];
	matrix=new int[V][V];
	edgeList = new ArrayList<IntegerTriple>();
	mstEdges=new ArrayList<IntegerTriple>();
	UnionFind ufds=new UnionFind(V);
	size=0;
    // write your answer here
    // you can leave this method blank if you do not need it
    for(int i=0;i<V;i++) {
    	Vector<IntegerPair> neighbors=AdjList.get(i);
    	for(int j=0;j<neighbors.size();j++) {
    		IntegerPair curr=neighbors.get(j);
    		if(matrix[i][curr.first()]==0) {
    	        edgeList.add(new IntegerTriple(i,curr.first(),curr.second()));
    	        matrix[i][curr.first()]=1;
    	        matrix[curr.first()][i]=1;
    		}
    	}
    }
    Collections.sort(edgeList);
    for(int k=0;k<edgeList.size();k++) {
    	if(size==V-1) {
    		break;
    	}	
    	IntegerTriple edge=edgeList.get(k);
    	int start=edge.first();
    	int end=edge.second();
    	if(!ufds.isSameSet(start, end)) {
    		ufds.unionSet(start, end);
    		mstEdges.add(edge);
    		size++;
    	}
    }
    mst=new ArrayList<ArrayList<IntegerPair>>();
    // initialize the adjacency list for the minimum spanning tree.
    for(int idx=0;idx<V;idx++) {
    	mst.add(new ArrayList<IntegerPair>());
    }
    // fill in our adjacency list for the minimum spanning tree.
    for(int index=0;index<mstEdges.size();index++) {
    	IntegerTriple currEdge=mstEdges.get(index);
    	int start=currEdge.first();
    	int end=currEdge.second();
    	int weight=currEdge.third();
    	mst.get(start).add(new IntegerPair(end,weight));
    	mst.get(end).add(new IntegerPair(start,weight));
    	
    }
    //System.out.println(edgeList);
    //printMST();
    
  }
  public void preDFS(int source){
	  flag=false;
	  //visited=new int[V];
	  //parent=new IntegerPair[V];
	  //parent[source]=new IntegerPair(-1,0);
	  parent[source]=new IntegerPair(-1,0);
	  for(int i=0;i<V;i++) {
		  visited[i]=0;
	  }
  }
  public void DFS(int source,int destination) {

	  visited[source]=1;
	  ArrayList<IntegerPair> neighbors=mst.get(source);
	  for(int i=0;i<neighbors.size();i++) {
		  if(flag) {
			  return ;
		  }
		  int curr=neighbors.get(i).first();
		  int weight=neighbors.get(i).second();
		  if(curr==destination) {
			  parent[curr]=new IntegerPair(source,weight);
			  flag=true;
			  return ;
		  }
		 
		  if(visited[curr]==0) {
			  parent[curr]=new IntegerPair(source,weight);
			  DFS(curr,destination);
		  }

	  }


  }
  public int findMax(int dest) {
	  int curr=dest;
	  int max=0;
	  //System.out.println(dest);
	  while(parent[curr].first()!=-1) {
		  int currWeight=parent[curr].second();
		  if(currWeight>max)
			  max=currWeight;
		  curr=parent[curr].first();
		  //System.out.print(currWeight+" ");
	  }
	  //System.out.println();
	  
	  return max;
  }
  

  int Query(int source, int destination) {
    int ans = 0;

    // You have to report the weight of a corridor (an edge)
    // which has the highest effort rating in the easiest path from source to destination for the wheelchair bound
    //
    // write your answer here
    if(result[source][destination]==0) {
        preDFS(source);
        DFS(source,destination);
        ans= findMax(destination);
        result[source][destination]=ans;
        result[destination][source]=ans;
    }
    else {
    	ans=result[source][destination];
    }

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
	System.setIn(new FileInputStream("testcaseB"));

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

class UnionFind{
	private ArrayList<Integer> parent;
	private ArrayList<Integer> rank;
	
	
	public UnionFind(int number) {
		parent=new ArrayList<Integer>(number);
		rank=new ArrayList<Integer>(number);
		for(int i=0;i<number;i++) {
			parent.add(i);
			rank.add(0);
		}
	}
	
	public int findSet(int i) {
		if(parent.get(i)==i)
			return i;
		else {
			int ret=findSet(parent.get(i));
			parent.set(i,ret);
			return ret;
		}
	}
	public boolean isSameSet(int i, int j) {
		return findSet(i)==findSet(j);
	}
	public void unionSet(int i,int j) {
		if(!isSameSet(i,j)) {
			int x=findSet(i);
			int y=findSet(j);
			if(rank.get(x)>rank.get(y)) {
				parent.set(y, x);
				
			}
			else {
				parent.set(x, y);
				if(rank.get(x)==rank.get(y))
					rank.set(y, rank.get(y)+1);
			}

		}
	}
	

}