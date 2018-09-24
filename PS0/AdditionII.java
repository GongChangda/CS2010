import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class AdditionII { // as the class name that contains the main method is "Addition", you have to save this file as "Addition.java", and submit "Addition.java" to Codecrunch
  public static void main(String[] args)  {
    Scanner sc = new Scanner(System.in);
    
    while (true) {
      // Here is the full solution for this super simple practice task in pseudo code
      // read two integers A and B
      // if both are -1, stop
      // output A+B
     String str1=sc.next();
     String str2=sc.next();
     if (str1.equals("-1") && str2.equals("-1"))
    	 break;
     else {
    	 BigInteger big1=new BigInteger(str1);
    	 BigInteger big2=new BigInteger(str2);
    	 BigInteger sum=big1.add(big2);
    	 System.out.println(sum);
     }
    	 
    }
  }
}

class IntegerScanner { // coded by Ian Leow, we will use this quite often in CS2010 PSes
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
        result = result*10 + (cur-48);
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