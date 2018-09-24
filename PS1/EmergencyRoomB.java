// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2018 hash code: FxYTABbg8BUwrG7T76qY (do NOT delete this line)
class Patient implements Comparable{
	public static int number=1;
	private String name;
	private int level;
	private int num;
	public Patient(String name,int level) {
		this.name=name;
		this.level=level;
		this.num=number;
		number++;
	}
	public String getName() {
		return this.name;
	}
	public int getLevel() {
		return this.level;
	}
	public int getNum() {
		return this.num;
	}
	public void setLevel(int level) {
		this.level=level;
	}
	// the below method will help us to compare the priority between two
	// patient objects.
	public int compareTo(Object another) {
		Patient other=new Patient("dummy",30);
		if (another instanceof Patient) {
			other=(Patient) another;
		} 
		if (this.getLevel()>other.getLevel()) {
			return this.getLevel()-other.getLevel();
		}
		else if (other.getLevel()>this.getLevel()) {
			return this.getLevel()-other.getLevel();
		}
		else {
			return other.getNum()-this.getNum();
		}
	}
}
public class EmergencyRoomB {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  private HashMap<String,Integer> map;
  private ArrayList<Patient> patients;
  private int size;
  
  public EmergencyRoom() {
    // Write necessary code during construction
    //
    // write your answer here
    patients=new ArrayList<Patient>();
    patients.add(null);
    size=1;
    map=new HashMap<String,Integer>();


  }

  void ArriveAtHospital(String patientName, int emergencyLvl) {
    // You have to insert the information (patientName, emergencyLvl)
    // into your chosen data structure
    //
    // write your answer here
	patients.add(size,new Patient(patientName,emergencyLvl));
	map.put(patientName, size);
	int index=size;
	size++;
	shiftUp(index);



  }
  int getParent(int index) {
	  return index / 2;
  }
  void shiftUp(int index) {
	  while(index>1 && patients.get(getParent(index)).compareTo(patients.get(index))<0){
          String name=patients.get(index).getName();
          String parentName=patients.get(getParent(index)).getName();
          map.put(name, getParent(index));
          map.put(parentName,index );
		  swap(index,getParent(index));
		  index=getParent(index);
	  }
  }
  void swap(int index1,int index2) {
	  Patient temp=patients.get(index1);
	  patients.set(index1, patients.get(index2));
	  patients.set(index2, temp);
  }
  void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
    // You have to update the emergencyLvl of patientName to
    // emergencyLvl += incEmergencyLvl
    // and modify your chosen data structure (if needed)
    //
    // write your answer here
	int index=map.get(patientName);
    Patient curr=patients.get(index);
    int newLevel=curr.getLevel()+incEmergencyLvl;
    curr.setLevel(newLevel);
    shiftUp(index);



  }

  void Treat(String patientName) {
    // This patientName is treated by the doctor
    // remove him/her from your chosen data structure
    //
    // write your answer here
	int index=map.get(patientName);
    swap(index,size-1);
    if(index != size-1) {
        String name=patients.get(index).getName();
        map.put(name,index);
        patients.set(size-1, null);
        size--;
        shiftDown(index);
        int currIndex=map.get(name);
        shiftUp(currIndex);
    }
    else {
    	patients.set(size-1, null);
    	size--;
    	
    }

  }
  int left(int i) {
	  return 2*i;
  }
  int right(int i) {
	  return 2*i+1;
  }
  void shiftDown(int i) {
	  while(i<size) {
		  int max=i;
		  if(left(i)<size && patients.get(left(i)).compareTo(patients.get(max))>0) {
			  max=left(i);
		  }
		  if(right(i)<size && patients.get(right(i)).compareTo(patients.get(max))>0) {
			  max=right(i);
		  }
		  if(max!=i) {
			  String name1=patients.get(i).getName();
			  String name2=patients.get(max).getName();
			  map.put(name1, max);
			  map.put(name2, i);
			  swap(i,max);
			  i=max;
		  }
		  else
			  break;
	  }
  }

  String Query() {
    String ans = "The emergency suite is empty";

    // You have to report the name of the patient that the doctor
    // has to give the most attention to currently. If there is no more patient to
    // be taken care of, return a String "The emergency suite is empty"
    //
    // write your answer here
    if(patients.get(1)!=null) {
    	return patients.get(1).getName();
    }


    return ans;
  }

  void run() throws Exception {
    // do not alter this method

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
    while (numCMD-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      switch (command) {
        case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 2: Treat(st.nextToken()); break;
        case 3: pr.println(Query()); break;
      }
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
	// Patient patient1=new Patient("durant",50);
	//Patient patient2=new Patient("james",50);
	//System.out.println(search("durant"));
	//System.setIn(new FileInputStream("test.txt"));
    EmergencyRoom ps1 = new EmergencyRoom();
    ps1.run();
  }
}