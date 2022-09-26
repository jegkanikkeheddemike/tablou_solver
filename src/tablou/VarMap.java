package tablou;

import java.util.HashMap;

import tablou.solver.values.Atomic;

public class VarMap extends HashMap<String, Atomic> {
    @Override
    public Object clone() {

        VarMap clone = new VarMap();

        for (String key : keySet()) {
            clone.put(key + "", this.get(key).clone());
        }

        return clone;
    }
}
