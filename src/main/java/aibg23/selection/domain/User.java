package aibg23.selection.domain;

import aibg23.selection.logic.Assignment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String password;
    private int result;
    private int trueResult;
    private boolean success;
    private Assignment assignment;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", result=" + result +
                ", trueResult=" + trueResult +
                ", success=" + success +
                ", assignment=" + assignment +
                '}';
    }
}
