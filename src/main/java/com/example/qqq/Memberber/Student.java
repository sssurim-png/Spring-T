package com.example.qqq.Memberber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private  String email;
    private List<Scores> score;



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Scores{
        private String subject;
        private int point;
    }
}
