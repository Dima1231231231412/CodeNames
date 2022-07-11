package com.example.codenames;

import java.util.List;

public class Card {
    public String word;
    public String color;

    public Card(List<String> word, List<String> color){
        this.word = String.valueOf(word);
        this.color = String.valueOf(color);
    }
}
