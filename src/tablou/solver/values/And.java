package tablou.solver.values;

import java.util.ArrayList;

import tablou.VarMap;
import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class And implements Value {

    public Value first;
    public Value second;

    public And(String first, String second, VarMap variables) throws FailedToParseException {
        this.first = TParser.parse(first, variables);
        this.second = TParser.parse(second, variables);
    }

    @Override
    public Type type() {
        return Type.And;
    }

    @Override
    public String printf(int depth) {

        String result = "";

        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }
        result += str + "AND (\n";
        result += first.printf(depth + 1);
        result += second.printf(depth + 1);
        result += str + ")\n";

        return result;

    }

    @Override
    public ArrayList<VarMap> solve(VarMap variables, boolean target_value) {
        ArrayList<VarMap> solutions = new ArrayList<VarMap>();

        if (target_value == true) {

            ArrayList<VarMap> first_solutions = first.solve(variables, true);

            for (VarMap solution : first_solutions) {
                solutions.addAll(second.solve(solution, true));
            }
        } else {

            // To solve AND to false. We need either one to be false (i think)

            solutions.addAll(first.solve(variables.clone(), false));
            solutions.addAll(second.solve(variables.clone(), false));

        }

        return solutions;
    }

}
