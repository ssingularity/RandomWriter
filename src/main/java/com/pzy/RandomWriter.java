package com.pzy;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RandomWriter {
    public Scanner inputFile;
    public Map<Queue<String>,ArrayList<String >> dic;
    public static void main(String[] args) throws IOException{
        RandomWriter randomWriter=new RandomWriter();
        randomWriter.init();
        randomWriter.process();
        randomWriter.start();
        System.exit(0);
    }
    public void init() throws IOException{
        String fileName;
        Scanner in=new Scanner(System.in);
        System.out.println("Please input the file:");
        fileName=in.next();
        inputFile=new Scanner(new File(fileName));
        //System.out.println(inputFile.next());
        //out=new PrintWriter("123
        //out.println("123");
        //out.close();
        //inputFile=new Scanner(Paths.get(fileName));
        //while (in.hasNextLine()){
        //    out.println("1");
        //    out.print(in.nextLine());
        //}
        //in.close();
        //File file=new File(fileName);
    }
    public void process(){
        int N;
        String sub;
        dic=new HashMap<Queue<String>, ArrayList<String>>();
        Queue<String> key=new LinkedList<>();
        ArrayList<String> value;
        System.out.println("Please input the N (N>=2):");
        Scanner in=new Scanner(System.in);
        N=in.nextInt();
        System.out.println(inputFile.hasNext());
        for (int i=0;i<N-1;i++){
            key.add(inputFile.next());
        }
        while(inputFile.hasNext()){
            sub=inputFile.next();
            value=dic.getOrDefault(key,new ArrayList<>());
            value.add(sub);
            dic.put(key,value);
            key=new LinkedList<>(key);
            key.remove();
            key.add(sub);
        }
        //System.out.println(dic.size());
    }
    public Queue<String> getStart(){
        long nr= (int) (System.currentTimeMillis()%dic.size());
        int i=0;
        for(Queue<String> queue: dic.keySet()){
            if(i==nr) return queue;
            i++;
        }
        return null;
    }
    public void start(){
        while(true) {
            Queue<String> queue = getStart();
            Scanner in=new Scanner(System.in);
            System.out.println("# input random words to generate (0 to quit):");
            int N=in.nextInt();
            if (N==0) break;
            for (String s:queue) System.out.print(s+" ");
            while (N!=0){
                //System.out.println(N);
                Queue<String> sub=new LinkedList<>(queue);
                ArrayList<String> arrayList = dic.get(sub);
                int nr = (int) (System.currentTimeMillis() % arrayList.size());
                String val = arrayList.get(nr);
                System.out.print(val+" ");
                sub.remove();
                sub.add(val);
                queue=sub;
                N--;
            }
        }
    }
}

