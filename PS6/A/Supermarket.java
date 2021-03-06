// Copy paste this Java Template and save it as "Supermarket.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0162477X
// write your name here: GONG CHANGDA
// write list of collaborators here:
// year 2018 hash code: AwrBr3czNrkqgCv4HeF3 (do NOT delete this line)

class Supermarket {
  private int N; // number of items in the supermarket. V = N+1 
  private int K; // the number of items that Ketfah has to buy
  private int[] shoppingList; // indices of items that Ketfah has to buy
  private int[][] T; // the complete weighted graph that measures the direct wheeling time to go from one point to another point in seconds

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  private int minTime;
  private int infinity;
  private int[] visited;


  public Supermarket() {
    // Write necessary code during construction
    //
    // write your answer here
    infinity=1000000;
    minTime=infinity;
    

  }
  public void DFSrec(int source, int visitedNum, int totalTime) {
	  visited[source]=1;
	  if (visitedNum==N+1) {
		  minTime=Math.min(minTime, totalTime+T[source][0]);
	  }
	  else {
		  for(int j=1;j<=N;j++) {
			  if(visited[j]==0) {
				  DFSrec(j,visitedNum+1,totalTime+T[source][j]);
			  }
		  }
	  }
	  visited[source]=0;
  }

  int Query() {
    int ans = 0;
    minTime=infinity;
    // You have to report the quickest shopping time that is measured
    // since Ketfah enters the supermarket (vertex 0),
    // completes the task of buying K items in that supermarket,
    // then reaches the cashier of the supermarket (back to vertex 0).
    //
    // write your answer here
    visited=new int[N+1];
    for(int i=0;i<N+1;i++) {
    	visited[i]=0;
    }
    DFSrec(0,1,0);
    ans=minTime;

    return ans;
  }

  // You can add extra function if needed
  // --------------------------------------------



  void run() throws Exception {
    // do not alter this method to standardize the I/O speed (this is already very fast)
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      // read the information of the complete graph with N+1 vertices
      N = sc.nextInt(); K = sc.nextInt(); // K is the number of items to be bought

      shoppingList = new int[K];
      for (int i = 0; i < K; i++)
        shoppingList[i] = sc.nextInt();

      T = new int[N+1][N+1];
      for (int i = 0; i <= N; i++)
        for (int j = 0; j <= N; j++)
          T[i][j] = sc.nextInt();

      pw.println(Query());
    }

    pw.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
	//System.setIn(new FileInputStream("testdata"));
    Supermarket ps6 = new Supermarket();
    ps6.run();
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