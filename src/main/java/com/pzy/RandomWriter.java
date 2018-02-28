package com.pzy;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RandomWriter {
    private Scanner inputFile;
    private Map<Queue<String>,ArrayList<String >> dic;
    public static void main(String[] args) throws IOException{
        RandomWriter randomWriter=new RandomWriter();
        randomWriter.init();
        randomWriter.process();
        randomWriter.start();
        System.exit(0);
    }
    private void init() throws IOException{
        String fileName;
        Scanner in=new Scanner(System.in);
        PrintWriter out=new PrintWriter(System.out);
        out.println("Please input the file:");
        fileName=in.next();
        //out=new PrintWriter("123");
        //out.println("123");
        //out.close();
        inputFile=new Scanner(new File(fileName));
        //while (in.hasNextLine()){
        //    out.println("1");
        //    out.print(in.nextLine());
        //}
        //in.close();
    }
    private void process(){
        int N;
        String sub;
        dic=new HashMap<Queue<String>, ArrayList<String>>();
        Queue<String> key=new LinkedList<>();
        ArrayList<String> value;
        System.out.println("Please input the N (N>=2):");
        Scanner in=new Scanner(System.in);
        N=in.nextInt();
        for (int i=0;i<N-1;i++){
            key.add(inputFile.next());
        }
        while(inputFile.hasNext()){
            sub=inputFile.next();
            value=dic.getOrDefault(key,new ArrayList<>()); value.add(sub);
            key=new LinkedList<>(key);
            key.remove();
            key.add(sub);
        }
    }
    private Queue<String> getStart(){
        long nr= (int) (System.currentTimeMillis()%dic.size());
        int i=0;
        for(Queue<String> queue: dic.keySet()){
            if(i==nr) return queue;
            i++;
        }
        return null;
    }
    private void start(){
        while(true) {
            Queue<String> queue = getStart();
            Scanner in=new Scanner(System.in);
            System.out.println("# input random words to generate (0 to quit):");
            int N=in.nextInt();
            if (N==0) break;
            while (N!=0){
                Queue<String> sub=new LinkedList<>(queue);
                ArrayList<String> arrayList = dic.get(sub);
                int nr = (int) (System.currentTimeMillis() % arrayList.size());
                String val = arrayList.get(nr);
                for (String s:sub) System.out.print(s+" ");
                sub.remove();
                sub.add(val);
                queue=sub;
                N--;
            }
        }
    }
}

