package tablou.solver.operators;

import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Not implements Value {
    public Not(String value) throws FailedToParseException {
        this.value = TParser.parse(value);
    }

    public Value value;

    @Override
    public Type type() {
        return Type.Not;
    }

    @Override
    public String printf(int depth) {
        String result = "";

        String str = "";
        for (int i = 0; i < depth ; i++) {
            str += "    ";
        }

        result += str +"NOT ( \n";
        result += value.printf(depth+1);
        result += str + ")\n";
        
        return result;
    }
    
}
