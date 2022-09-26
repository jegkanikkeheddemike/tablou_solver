package tablou.solver.values;

import java.util.ArrayList;

import tablou.VarMap;
import tablou.solver.Type;
import tablou.solver.Value;

public class Atomic implements Value {
    String name;
    boolean isSome = false;
    boolean value = false;

    public Atomic(String name, VarMap variables) {
        this.name = name;
        variables.put(name, this);
    }

    private Atomic(String name, boolean value) {
        this.name = name;
        isSome = true;
        this.value = value;
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

        ArrayList<VarMap> solutions = new ArrayList<VarMap>();

        Atomic value = variables.get(name);
        if (value.isSome) {
            if (value.value == target_value) {
                solutions.add(variables);
            }
        } else {
            variables.put(name, new Atomic(name, target_value));
            solutions.add(variables);
        }

        return solutions;
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
