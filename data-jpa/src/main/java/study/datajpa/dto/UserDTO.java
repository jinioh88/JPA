package study.datajpa.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private int age;
    private String teamName;

    public UserDTO(String name, int age, String teamName) {
        this.name = name;
        this.age = age;
        this.teamName = teamName;
    }
}
