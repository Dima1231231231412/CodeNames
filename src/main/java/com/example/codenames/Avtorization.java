package com.example.codenames;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import org.springframework.cache.annotation.EnableCaching;


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

}
