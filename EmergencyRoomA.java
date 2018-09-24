// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2018 hash code: FxYTABbg8BUwrG7T76qY (do NOT delete this line)
class Patient{
	public static int number=1;
	private String name;
	private int emergencyLevel;
	private int num;
	public Patient(String name , int emergencyLevel) {
		this.name=name;
		this.emergencyLevel=emergencyLevel;
		this.num=number;
		number++;
	}
	public void setLevel(int newLevel) {
		this.emergencyLevel=newLevel;
	}
	public String getName() {
		return name;
	}
	public int getLevel() {
		return emergencyLevel;
	}
	public int getNum() {
		return num;
	}
}
public class EmergencyRoomB {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
    ArrayList<Patient> patients;
    int size;

  public EmergencyRoom() {
    // Write necessary code during construction
    //
    // write your answer here
    patients=new ArrayList<Patient>();
    patients.add(null);
    size=1;   
   
  
  
  }
  int findParent(int index) {
	  int parentIndex= index / 2;
	  return parentIndex;
  }
  void swap(int index, int parentIndex) {
	  Patient temp=patients.get(index);
	  patients.set(index, patients.get(parentIndex));
	  patients.set(parentIndex, temp);
  }
  void ArriveAtHospital(String patientName, int emergencyLvl) {
    // You have to insert the information (patientName, emergencyLvl)
    // into your chosen data structure
    //
    // write your answer here
    Patient newPatient=new Patient(patientName,emergencyLvl);
    patients.add(size, newPatient);;
    size=size+1;
    int index=size-1;
    int parentIndex=findParent(index);
    while (index>1) {
    	if (patients.get(index).getLevel()>patients.get(parentIndex).getLevel() || 
    			(patients.get(index).getLevel()== patients.get(parentIndex).getLevel() && patients.get(index).getNum()< patients.get(parentIndex).getNum()) ) {
    		swap(index,parentIndex);
    		index=parentIndex;
    		parentIndex=findParent(index);
    	}
    	else {
    		break;
    	}
    }

  }

  void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
    // You have to update the emergencyLvl of patientName to
    // emergencyLvl += incEmergencyLvl
    // and modify your chosen data structure (if needed)
    //
    // write your answer here
	int index=0;
    for(int i=1;i<size;i++) {
    	if (patients.get(i).getName().equals(patientName)  ) {
    		index=i;
    		Patient currPatient=patients.get(i);
    		int newLevel=currPatient.getLevel()+incEmergencyLvl;
    		currPatient.setLevel(newLevel);
    		break;
    	}
    	
    }
    ShiftUp(index);


  }
  void ShiftUp(int index) {
	  while(index>1) {
		  if(patients.get(index).getLevel()>patients.get(index/2).getLevel() || 
				  (patients.get(index).getLevel() == patients.get(index/2).getLevel() && patients.get(index).getNum()<patients.get(index/2).getNum())) {
			  swap(index,index/2);
			  index=index/2;
		  }
		  else {
			  break;
		  }
	  }
  }
  int FindDeepestChild(int index) {
	  while (true) {
		  if(index*2+1<size) {
			  index=index*2+1;
			  continue;
		  }
		  if(index*2<size) {
			  index=index*2;
			  continue;
		  }
		  break;
	  }
	  return index;
  }
  int getLeft(int index) {
	  return index*2;
  }
  int getRight(int index) {
	  return index*2+1;
  }
  int selectHighest(int curr,int left, int right) {
	  int currLevel=patients.get(curr).getLevel();
	  int leftLevel=patients.get(left).getLevel();
	  int rightLevel=patients.get(right).getLevel();
	  if (currLevel>leftLevel && currLevel>rightLevel)
		  return curr;
	  if (leftLevel>rightLevel && leftLevel>currLevel)
		  return left;
	  if (rightLevel>leftLevel && rightLevel>currLevel)
		  return right;
	  int currNum=patients.get(curr).getNum();
	  int leftNum=patients.get(left).getNum();
	  int rightNum=patients.get(right).getNum();
	  if (currNum<leftNum && currNum<rightNum)
		  return curr;
	  else if(leftNum<currNum && leftNum<rightNum)
		  return left;
	  else
		  return right;
	  
  }
  void ShiftDown(int index) {
	  while(index*2+1<size) {
		  int curr=index;
		  int left=getLeft(curr);
		  int right=getRight(curr);
		  index=selectHighest(curr,left,right);
		  if(index==curr)
			  break;
		  else if (index==right) 
			  swap(curr,right);

		  else
			  swap(curr,left);
	  }
	  if(index*2==size-1) {
		  if (patients.get(index).getLevel()>patients.get(index*2).getLevel() || (
				  patients.get(index).getLevel()== patients.get(index*2).getLevel() &&
				  patients.get(index).getNum()<patients.get(index*2).getNum())) {
			  swap(index,index*2);
		  }
	  }
  }
  void Treat(String patientName) {
    // This patientName is treated by the doctor
    // remove him/her from your chosen data structure
    //
    // write your answer here
	int index=0;
    for(int i=1;i<size;i++) {
    	if(patients.get(i).getName().equals(patientName)) {
    		index=i;
    		break;
    	}
    	
    }
    int deepestChild=FindDeepestChild(index);
    patients.set(index, patients.get(deepestChild));
    patients.set(deepestChild, null);
    for(int j=deepestChild; j<size-1;j++) {
    	patients.set(j, patients.get(j+1));
    	
    }
    size=size-1;
    ShiftDown(index);
  }

  String Query() {
    String ans = "The emergency suite is empty";

    // You have to report the name of the patient that the doctor
    // has to give the most attention to currently. If there is no more patient to
    // be taken care of, return a String "The emergency suite is empty"
    //
    // write your answer here
    if (size!=1)
    	ans=patients.get(1).getName();
    	


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
	
    EmergencyRoom ps1 = new EmergencyRoom();
    ps1.run();
  }
}