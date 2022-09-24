package tablou.solver;

public class Atomic implements Value {
    String name;
    boolean isSome = false;
    boolean value = false;

    public Atomic(String name) {
        this.name = name;
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

}