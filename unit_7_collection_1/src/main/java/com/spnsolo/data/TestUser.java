package com.spnsolo.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class TestUser implements Comparable<TestUser>{
    private static int numbers = 0;
    private Integer id;
    private String name;


    public TestUser(){
        numbers++;
        id = numbers;
        name = "Unknown";
    }

    @Override
    public int compareTo(TestUser o) {return id - o.getId(); }
    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
