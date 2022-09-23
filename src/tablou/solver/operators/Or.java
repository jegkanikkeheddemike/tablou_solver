package tablou.solver.operators;

import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Type;
import tablou.solver.Value;

public class Or implements Value {

    Value first;
    Value second;

    public Or(String first, String second) throws FailedToParseException {
        this.first = TParser.parse(first);
        this.second = TParser.parse(second);
    }

    @Override
    public Type type() {
        return Type.Or;
    }

}
