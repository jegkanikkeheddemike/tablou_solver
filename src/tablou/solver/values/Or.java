package tablou.solver.values;

import java.util.ArrayList;

import tablou.VarMap;
import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Or implements Value {

    Value first;
    Value second;

    public Or(String first, String second, VarMap variables) throws FailedToParseException {
        this.first = TParser.parse(first, variables);
        this.second = TParser.parse(second, variables);
    }

    @Override
    public Type type() {
        return Type.Or;
    }

    @Override
    public String printf(int depth) {
        String result = "";

        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }
        result += str + "OR (\n";
        result += first.printf(depth + 1);
        result += second.printf(depth + 1);
        result += str + ")\n";

        return result;
    }

    @Override
    public ArrayList<VarMap> solve(VarMap variables, boolean target_value) {

        ArrayList<VarMap> solutions = new ArrayList<VarMap>();

        if (target_value == true) {

            solutions.addAll(first.solve(variables.clone(), target_value));
            solutions.addAll(second.solve(variables.clone(), target_value));
        } else {
            // To solve OR to false, both need to be false

            ArrayList<VarMap> first_false = first.solve(variables, false);
            for (VarMap solution : first_false) {
                solutions.addAll(second.solve(solution, false));
            }

        }

        return solutions;
    }
}
