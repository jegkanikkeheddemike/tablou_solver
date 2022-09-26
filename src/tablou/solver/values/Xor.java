package tablou.solver.values;

import java.util.ArrayList;

import tablou.VarMap;
import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Xor implements Value {

    Value first;
    Value second;

    public Xor(String first, String second, VarMap variables) throws FailedToParseException {
        this.first = TParser.parse(first, variables);
        this.second = TParser.parse(second, variables);
    }

    @Override
    public Type type() {
        return Type.Xor;
    }

    @Override
    public String printf(int depth) {
        String result = "";

        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }
        result += str + "XOR (\n";
        result += first.printf(depth + 1);
        result += second.printf(depth + 1);
        result += str + ")\n";

        return result;
    }

    @Override
    public ArrayList<VarMap> solve(VarMap variables, boolean target_value) {

        ArrayList<VarMap> solutions = new ArrayList<VarMap>();

        if (target_value == true) {
            // XOR is true is either but NOT both

            // Solve for first true second false
            ArrayList<VarMap> first_true = first.solve(variables.clone(), true);
            for (VarMap solution : first_true) {
                solutions.addAll(second.solve(solution, false));
            }

            // Solve for first false second true
            ArrayList<VarMap> first_false = first.solve(variables.clone(), false);
            for (VarMap solution : first_false) {
                solutions.addAll(second.solve(solution, true));
            }

        } else {
            // Needs both true or both false

            // Both true

            ArrayList<VarMap> first_true = first.solve(variables.clone(), true);
            for (VarMap solution : first_true) {
                solutions.addAll(second.solve(solution, true));
            }

            // Both false
            ArrayList<VarMap> first_false = first.solve(variables.clone(), false);
            for (VarMap solution : first_false) {
                solutions.addAll(second.solve(solution, false));
            }

        }

        return solutions;
    }

}
