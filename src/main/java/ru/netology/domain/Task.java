package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    int id;
    String name;
    int authorId;
    int assigneeId;
    String label;
    boolean open;
}
