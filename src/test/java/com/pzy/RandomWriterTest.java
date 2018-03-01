package com.pzy;

import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RandomWriterTest {
    RandomWriter randomWriter=new RandomWriter();
    @Test
    public void testConstruct(){
        Assert.assertNotNull(randomWriter);
    }
    @Test
    public void testInitandProcess() throws IOException{
        String fileName="test.txt\r\n";
        String input="3\r\n";
        InputStream stdin=System.in;
        try{
            System.setIn(new ByteArrayInputStream(fileName.getBytes()));
            randomWriter.init();
            Assert.assertNotNull(randomWriter.inputFile);
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            randomWriter.process();
            Assert.assertNotNull(randomWriter.dic);
            Queue<String> queue=new LinkedList<>();
            queue.add("123");queue.add("234");
            ArrayList<String> sub=randomWriter.dic.get(queue);
            Assert.assertNotNull(sub);
            String obj="345";
            Assert.assertEquals(obj,sub.get(0));
        }
        catch (IOException e){}
    }

}
