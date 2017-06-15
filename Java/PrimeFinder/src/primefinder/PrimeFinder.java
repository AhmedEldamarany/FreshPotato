
package primefinder;
import java.util.*;

public class PrimeFinder {

   
    public static void main(String[] args) {
Scanner cin=new Scanner(System.in);
int limit;
        System.out.println("Enter The Upper Limit");
       limit= cin.nextInt();
        for(int i=0;i<limit;i++)
        if(isPrime(i))
            System.out.println(i);


    }
    
  static boolean isPrime(int number){
      if(number!=2 &&number%2==0 )return false;
      for(int i=3;i<number;i++)
          if(number%i==0) return false;
      return true;
  } 
    
}


