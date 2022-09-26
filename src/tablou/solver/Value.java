package tablou.solver;

import java.util.ArrayList;

import tablou.VarMap;


public interface Value extends Cloneable {
    Type type();

    String printf(int depth);

    default ArrayList<VarMap> solve(VarMap variables, boolean target_value) {
        throw new UnsupportedOperationException("SOLVE NOT IMPLEMENTED FOR " + type() + " YET!");
    }
}
