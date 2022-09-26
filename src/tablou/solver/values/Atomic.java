package tablou.solver.values;

import java.util.ArrayList;
import java.util.HashMap;

import tablou.VarMap;
import tablou.solver.Type;
import tablou.solver.Value;

public class Atomic implements Value {
    String name;
    boolean isSome = false;
    boolean value = false;

    public Atomic(String name, HashMap<String, Atomic> variables) {
        this.name = name;
        variables.put(name, this);
    }

    public String value() {
        if (isSome) {
            return "" + value;
        } else {
            return "none";
        }
    }

    @Override
    public Type type() {
        return Type.Atomic;
    }

    @Override
    public String printf(int depth) {

        String result = "";

        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }

        result += str + name + "\n";
        return result;
    }

    @Override
    public ArrayList<VarMap> solve(VarMap variables, boolean target_value) {

        return Value.super.solve(variables, target_value);
    }

    @Override
    public Atomic clone() {
        try {
            return (Atomic) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new RuntimeException("Litterraly impossible");
        }
    }
}
