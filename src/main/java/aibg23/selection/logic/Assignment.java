package aibg23.selection.logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Assignment {
    private int a, b, c, d, e;
//    private double result;
//    private String ass;


    @Override
    public String toString() {
        return "Assignment{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                '}';
    }
}
