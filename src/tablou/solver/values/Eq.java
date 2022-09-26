package tablou.solver.values;

import java.util.HashMap;

import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Eq implements Value {

    Value first;
    Value second;

    public Eq(String first, String second, HashMap<String,Atomic> variables) throws FailedToParseException {
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
    
}
