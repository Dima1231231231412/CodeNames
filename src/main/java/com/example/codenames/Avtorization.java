package com.example.codenames;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import com.google.gson.Gson;

import static java.lang.Math.round;


@Component
@RestController
public class Avtorization {

    @GetMapping("/player")
    @CachePut(value="addresses")
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Player player = new Player(request.getParameter("id"),request.getParameter("name"),"игрок");
        String json = new Gson().toJson(player);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @GetMapping("/cards")
    public void cards(HttpServletResponse response) throws IOException {
        FileReader reader = new FileReader("D:\\Java Projects\\CodeNames\\src\\main\\java\\com\\example\\codenames\\dict.txt");
        int c;
        StringBuilder str = new StringBuilder();

        while((c=reader.read())!=-1){
            str.append((char) c);
        }

        String[] words = str.toString().strip().split(", ");

        int max = words.length;
        List<String> selectionWords = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        String str2 = "rrrrrrrrrbbbbbbbbnnnnnnna";

        List<String> letters = Arrays.asList(str2.split(""));
        Collections.shuffle(letters);
        String shuffled = "";

        for (String letter : letters) {
            shuffled += letter;
        }

        for (int i=0; i < 25; i++)
        {
            int rndWord = round((int) (Math.random()*max));
            selectionWords.add(words[rndWord]);

            colors.add(String.valueOf(shuffled.charAt(i)));
        }

        Card card = new Card(selectionWords,colors);
        String json = new Gson().toJson(card);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(json);
    }
}
