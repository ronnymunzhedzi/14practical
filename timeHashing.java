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
        double chainedTime = timeChainedHashing(m, alpha);
         System.out.println("        " + fiveD.format(chainedTime));
        }
        
        System.out.println("==========================================================");
    }
  static void generateTestData () {
    System.out.println("Generating " + N + "key value pairs...");
    Integer[] keys = new Integer [N];
    for (int i =0; i< N; i++) {
      keys[i] = i+1;
    }
    Random rand = new Random(42); 
        for (int i = N-1; i > 0; i--) {
            int j = rand.nextInt(i+1);
            Integer temp = keys[i];
            keys[i] = keys[j];
            keys[j] = temp;
        }
     data = new KeyValue[N];
        for (int i = 0; i < N; i++) {
            String keyStr = String.valueOf(keys[i]);
            String valueStr = String.valueOf(i + 1); 
            data[i] = new KeyValue(keyStr, valueStr);
        }
        
        System.out.println("Data generation complete. First 950,000 will be used.\n");
    }
  static double timeOpenHashing (int m,double alpha ) {
    int repetitions = 30;
    double totalTime = 0;
    for ( int rep =0; rep < repetitions ;rep ++) {
      openHash hashTable = new openHash(m);
      long start = System.nanoTime();
      for (int i =0 ;i < USE_N ; i++) {
        hashTable .insert( data[i].key, data[i].value);
      }
      long end =System.nanoTime();
       totalTime += (end - start) / 1_000_000.0;
        }
        
        return totalTime / repetitions;
    }
  static double timeChainedHashing(int m, double alpha) {
        int repetitions = 30;
        double totalTime = 0;
        
        for (int rep = 0; rep < repetitions; rep++) {
            chainedHash hashTable = new chainedHash(m);
            long start = System.nanoTime();
            for (int i = 0; i < USE_N; i++) {
                hashTable.insert(data[i].key, data[i].value);
            }
            long end = System.nanoTime();
            
            totalTime += (end - start) / 1_000_000.0; 
        }
    return totalTime / repetitions;
  }
}

    
  
    
      
    
    
        
  
    
    
    
        
        
        
        
        
      
  
  
