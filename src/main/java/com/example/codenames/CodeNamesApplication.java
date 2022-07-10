package com.example.codenames;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;

import static java.lang.Math.round;

@SpringBootApplication
public class CodeNamesApplication {
    public static void main(String[] args) throws IOException, JSONException {
        SpringApplication.run(CodeNamesApplication.class, args);
        dict();
    }

    public static void dict() throws IOException, JSONException {
        FileReader reader = new FileReader("D:\\Java Projects\\CodeNames\\src\\main\\java\\com\\example\\codenames\\dict.txt");
        int c;
        StringBuilder str = new StringBuilder();
        JSONObject sampleObject = new JSONObject();


        while((c=reader.read())!=-1){
            str.append((char) c);
        }

        String[] words = str.toString().strip().split(", ");

        int max = words.length;
        List<String> selectionWords = new ArrayList<>();

        for (int i=0; i < 25; i++)
        {
            long rnd = round(Math.random()*max);
            selectionWords.add(words[(int) rnd]);
            sampleObject.put("cлова",selectionWords);
        }
        System.out.println(sampleObject);
/*        FileWriter file = new FileWriter("D:\\Java Projects\\CodeNames\\src\\main\\java\\com\\example\\codenames\\sample.json");
        file.write(sampleObject.toString());
        file.flush();
        file.close();*/
    }
}

