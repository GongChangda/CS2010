// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0162477X
// write your name here: GONG CHANGDA
// write list of collaborators here:
// year 2018 hash code: edyFpB4rcbU64QHhV8S9 (do NOT delete this line)

class PatientNames {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  // --------------------------------------------

  TreeMap<String,Integer> map;

  // --------------------------------------------

  public PatientNames() {
    // Write necessary code during construction;
    //
    // write your answer here

    // --------------------------------------------
    map=new TreeMap<String,Integer>();


    // --------------------------------------------
  }

  void AddPatient(String patientName, int gender) {
    // You have to insert the information (patientName, gender)
    // into your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    map.put(patientName, gender);


    // --------------------------------------------
  }

  void RemovePatient(String patientName) {
    // You have to remove the patientName from your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    map.remove(patientName);


    // --------------------------------------------
  }

  int Query(String START, String END, int gender) {
    int ans = 0;

    // You have to answer how many patient name starts
    // with prefix that is inside query interval [START..END)
    //
    // write your answer here

    // --------------------------------------------
    SortedMap<String,Integer> result=map.subMap(START, END);
    if(gender==0) {
    	ans=result.size();
    }
    else if(gender==1) {
    	for(String name: result.keySet()) {
    		if(result.get(name)==1)
    			ans+=1;
    	}
    }
    else {
    	for(String name: result.keySet()) {
    		if (result.get(name)==2)
    			ans+=1;
    	}
    }


    // --------------------------------------------

    return ans;
  }

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      if (command == 0) // end of input
        break;
      else if (command == 1) // AddPatient
        AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
      else if (command == 2) // RemovePatient
        RemovePatient(st.nextToken());
      else // if (command == 3) // Query
        pr.println(Query(st.nextToken(), // START
                         st.nextToken(), // END
                         Integer.parseInt(st.nextToken()))); // GENDER
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
	//System.setIn(new FileInputStream("testdata1"));
    PatientNames ps2 = new PatientNames();
    ps2.run();
  }
}