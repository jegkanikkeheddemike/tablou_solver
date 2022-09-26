package tablou.solver.values;

import java.util.ArrayList;

import tablou.VarMap;
import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Then implements Value {

    Value first;
    Value second;

    public Then(String first, String second, VarMap variables) throws FailedToParseException {
        this.first = TParser.parse(first, variables);
        this.second = TParser.parse(second, variables);
    }

    @Override
    public Type type() {
        return Type.Then;
    }

    @Override
    public String printf(int depth) {
        String result = "";

        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }
        result += str + "THEN (\n";
        result += first.printf(depth + 1);
        result += second.printf(depth + 1);
        result += str + ")\n";

        return result;
    }

    @Override
    public ArrayList<VarMap> solve(VarMap variables, boolean target_value) {

        ArrayList<VarMap> solutions = new ArrayList<VarMap>();

        if (target_value == true) {
            // find solutions for first = false
            solutions.addAll(first.solve(variables.clone(), false));

            // Find solutions for both true

            ArrayList<VarMap> first_true = first.solve(variables, true);

            for (VarMap solution : first_true) {
                solutions.addAll(second.solve(solution, true));
            }
        } else {
            throw new UnsupportedOperationException("FALSE is not implemented for THEN yet!");
        }

        return solutions;
    }

}
