package aibg23.selection.logic;

import aibg23.selection.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogicClass {
//    private Assignment assignment;

    public String getAss(User user) {
        Assignment assignment = new Assignment();

        assignment.setA((int) (Math.random() * 100 + 1));
        assignment.setB((int) (Math.random() * 100 + 1));
        assignment.setC((int) (Math.random() * 100 + 1));
        assignment.setD((int) (Math.random() * 100 + 1));
        assignment.setE((int) (Math.random() * 100 + 1));

        user.setAssignment(assignment);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(assignment);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public void calculateTrueResult(User user) {
        int rez = 0;
        Assignment ass = user.getAssignment();

        // (a + b) * d - c * c - e;
        rez = (ass.getA() + ass.getB()) * ass.getD() - ass.getC() * ass.getC() + ass.getE();
        user.setTrueResult(rez);

    }

}
