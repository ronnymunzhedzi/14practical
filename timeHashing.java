import java.lang.Math.*;
import java.io.*;
import java.text.*;
import java.util.*;
public class timeHashing{
  static class KeyValue {
        String key;    
        String value;   
        
        KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
  public static final int N = 1 << 20; 
    public static final int USE_N = 950000; 
    
    public static KeyValue[] data; 
    
    public static void main(String args[]) {
      
        generateTestData();
    
        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");
  double[] loadFactors = {0.75, 0.80, 0.85, 0.90, 0.95};
        
        System.out.println("\n\n\t\tHASHING PERFORMANCE COMPARISON");
        System.out.println("==========================================================");
        System.out.println("Data size: " + USE_N + " key-value pairs");
        System.out.println("Total generated: " + N + " pairs");
        System.out.println("==========================================================\n");
        System.out.println("Load Factor | Open Hashing (ms) | Chained Hashing (ms)");
        System.out.println("------------|-------------------|---------------------");
      for (double alpha : loadFactors){
        int m = (int)Math.ceil(USE_N/alpha);
        if (m % 2 == 0)m++;
        System.out.print("   " + (int)(alpha*100) + "%    |");
        double openTime = timeOpenHashing (m,alpha);
        System.out.print( "    " + fiveD.format(openTime) + "     |");
        
        
        
      
  
  
