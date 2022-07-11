package com.example.codenames;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import static java.lang.Math.round;

@SpringBootApplication
public class CodeNamesApplication {
    public static void main(String[] args) throws IOException, JSONException {
        SpringApplication.run(CodeNamesApplication.class, args);
    }
}

