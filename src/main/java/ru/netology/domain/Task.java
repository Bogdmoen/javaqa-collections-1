package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    private int id;
    private String name;
    private String author;
    private Set<String> assignee;
    private Set<String> label;
    private boolean open;
}
