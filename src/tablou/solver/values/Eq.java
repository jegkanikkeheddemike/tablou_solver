package tablou.solver.values;

import java.util.ArrayList;

import tablou.VarMap;
import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Eq implements Value {

    Value first;
    Value second;

    public Eq(String first, String second, VarMap variables) throws FailedToParseException {
        this.first = TParser.parse(first, variables);
        this.second = TParser.parse(second, variables);
    }

    @Override
    public Type type() {
        return Type.Eq;
    }

    @Override
    public String printf(int depth) {
        String result = "";

        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }
        result += str + "EQ (\n";
        result += first.printf(depth + 1);
        result += second.printf(depth + 1);
        result += str + ")\n";

        return result;
    }

    @Override
    public ArrayList<VarMap> solve(VarMap variables, boolean target_value) {

        ArrayList<VarMap> solutions = new ArrayList<VarMap>();

        if (target_value == true) {
            // Find solutions for both true
            ArrayList<VarMap> first_true = first.solve(variables.clone(), true);
            for (VarMap solution : first_true) {
                solutions.addAll(second.solve(solution, true));
            }


            // Find solutions for both false
            ArrayList<VarMap> first_false = first.solve(variables.clone(), false);
            for (VarMap solution : first_false) {
                solutions.addAll(second.solve(solution, false));
            }
        } else {
            throw new UnsupportedOperationException("False is not yet implemented for EQ");
        }

        return solutions;
    }
}
