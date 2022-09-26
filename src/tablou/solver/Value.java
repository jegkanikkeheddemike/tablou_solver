package tablou.solver;

import java.util.ArrayList;
import java.util.HashMap;

import tablou.solver.values.Atomic;


public interface Value {
    Type type();

    String printf(int depth);

    default ArrayList<HashMap<String, Atomic>> solve(HashMap<String, Atomic> variables, boolean target_value) {
        throw new UnsupportedOperationException("SOLVE NOT IMPLEMENTED FOR " + type() + " YET!");
    }

}
