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
    public void printf(int depth) {
        String str = "";
        for (int i = 0; i < depth; i++) {
            str += "    ";
        }

        System.out.println(str + name);

    }

}
