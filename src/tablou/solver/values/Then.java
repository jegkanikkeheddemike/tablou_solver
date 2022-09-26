package tablou.solver.values;

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

}
