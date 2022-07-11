package com.example.codenames;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public void cards(HttpServletResponse response) throws JSONException, IOException {
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
        List<String> colors = new ArrayList<>();
        String colorString = "rbna";
        String colorStr = "";
        for (int i=0; i < 25; i++)
        {
            int rndWord = round((int) (Math.random()*max));
            selectionWords.add(words[rndWord]);
            int rndColor = round((int) (Math.random() * 4));
            colors.add(String.valueOf(colorString.charAt(rndColor)));
            colorStr += colorString.charAt(rndColor);
        }

        int countA=0, countB = 0, countR = 0, countN = 0;
        for (char element : colorStr.toCharArray()){
            if (element == 'a') countA++;
            if (element == 'b') countB++;
            if (element == 'r') countR++;
            if (element == 'n') countN++;
        }
        System.out.println(colorStr);

        System.out.println(countA);
        System.out.println(countB);
        System.out.println(countR);
        System.out.println(countN);


        Card card = new Card(selectionWords,colors);
        String json = new Gson().toJson(card);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
