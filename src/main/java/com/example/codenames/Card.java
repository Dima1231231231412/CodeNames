package com.example.codenames;

import java.util.List;

public class Card {
    public List<String> word;
    public List<String> color;

    public Card(List<String> word, List<String> color){
        this.word = word;
        this.color = color;
    }
}
