// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2018 hash code: edyFpB4rcbU64QHhV8S9 (do NOT delete this line)

class PatientNames {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  // --------------------------------------------
  TreeMap<String,Integer> map1;
  TreeMap<String,Integer> map2;
  HashMap<String,Integer> map3;

  // --------------------------------------------

  public PatientNames() {
    // Write necessary code during construction;
    //
    // write your answer here

    // --------------------------------------------
    map1=new TreeMap<String,Integer>();
    map2=new TreeMap<String,Integer>();
    map3=new HashMap<String,Integer>();

    // --------------------------------------------
  }

  void AddPatient(String patientName, int gender) {
    // You have to insert the information (patientName, gender)
    // into your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    if(gender==1)
    	map1.put(patientName,gender);
    else
    	map2.put(patientName, gender);
    map3.put(patientName, gender);
    // --------------------------------------------
  }

  void RemovePatient(String patientName) {
    // You have to remove the patientName from your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    Integer gender=map3.get(patientName);
    if (gender==1) {
    	map1.remove(patientName);
    }
    else {
    	map2.remove(patientName);
    }


    // --------------------------------------------
  }

  int Query(String START, String END, int gender) {
    int ans = 0;

    // You have to answer how many patient name starts
    // with prefix that is inside query interval [START..END)
    //
    // write your answer here

    // --------------------------------------------
    SortedMap<String,Integer> subMap1=map1.subMap(START, END);
    SortedMap<String,Integer> subMap2=map2.subMap(START, END);
    if (gender==0) {
    	ans=subMap1.size()+subMap2.size();
    	
    }
    else if (gender==1) {
    	ans=subMap1.size();
    }
    else {
    	ans=subMap2.size();
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