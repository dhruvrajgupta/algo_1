import java.io.*;
class QuicksortRight
{
    static int[] arr = new int[10000];
    static int comp=0;
    
    static void quicksort(int arr[],int left, int right){
        if(left<right){
            int pi=partition(arr,left,right);
            quicksort(arr,left,pi-1);
            quicksort(arr,pi+1,right);
        }
    }
    
    static int partition(int arr[],int left, int right){
        comp+=right-left;
        int pi = arr[right];
        
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left]=temp;
        
        int s=left+1;
        for(int i=left+1; i<=right; i++){
            if(arr[i]<=pi){
                temp=arr[s];
                arr[s]=arr[i];
                arr[i]=temp;
                s++;
                
                //display(left,right);
            }
        }
        
        temp=arr[left];
        arr[left]=arr[s-1];
        arr[s-1]=temp;
        display(left,right);
        
        return s-1;
    }
    
    static void display(int left,int right){
        for(int i=left; i<=right; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
    static void readFile()throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("quicksort.txt"));
        String line = br.readLine();
        int i=-1;
        while(line!=null){
            i++;
            int num=Integer.parseInt(line);
            arr[i]=num;
            line=br.readLine();
        }
        br.close();
    }
    
    public static void main()throws IOException{
        readFile();
        display(0,arr.length-1);
        quicksort(arr,0,arr.length-1);
        display(0,arr.length-1);
        System.out.println("Comparisons : "+comp);
        
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=(i+1)){
                System.out.println("Error at pos : "+i+" which has value : "+arr[i]);
            }
        }
    }
}