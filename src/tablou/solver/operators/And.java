package tablou.solver.operators;

import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class And implements Value {

    public Value first;
    public Value second;

    public And(String first, String second) throws FailedToParseException {
        this.first = TParser.parse(first);
        this.second = TParser.parse(second);
    }

    @Override
    public Type type() {
        return Type.And;
    }

    @Override
    public void printf(int depth) {
        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }
        System.out.println(str + "AND (");
        first.printf(depth+1);
        second.printf(depth+1);
        System.out.println(str + ")");

        
        
    }

}
