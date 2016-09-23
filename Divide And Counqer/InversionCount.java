import java.util.*;
import java.io.*;
public class InversionCount
{
    static int arr[]=new int[100000];
    static int newarr[]=new int[arr.length];
    static long invcnt = 0;
    static int count=-1;
    
    public static void getData()throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("integers.txt"));
        String line = br.readLine();
        System.out.println("Getting data : ");
        while(line!=null){
            count++;
            int num=Integer.parseInt(line);
            System.out.println(num);
            arr[count]=num;
            line=br.readLine();
        }
        br.close();
        System.out.println(arr);
    }
    
    public static void mergeSort(int left, int right){
        if(left<right){
            int mid=left+(right-left)/2;
            mergeSort(left,mid);
            mergeSort(mid+1,right);
            merge(left,mid,right);
        }
    }
    
    public static void merge(int left,int mid,int right){
        for(int i=left; i<=right; i++){
            newarr[i]=arr[i];
        }
        int i=left;
        int j=mid+1;
        int k=left;
        while(i<=mid && j<=right){
            if(newarr[i]<=newarr[j]){
                arr[k]=newarr[i];
                i++;
            }else{
                arr[k]=newarr[j];
                j++;
                invcnt+=(mid-i+1);
            }
            k++;
        }
        while(i<=mid){
            arr[k]=newarr[i];
            k++;
            i++;
        }
    }
    
    public static void main(String args[])throws IOException{
        getData();
        System.out.println("Original Array : ");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+"\n");
        }
        System.out.println();
        
        mergeSort(0,arr.length-1);
        
        System.out.println("Sorted Array : ");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+"\n");
        }
        System.out.println();
        System.out.println("Inversion Count : "+invcnt);
    }
}