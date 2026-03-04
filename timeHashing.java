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
        // Generate test data
        generateTestData();
        
        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");
  
  
  
