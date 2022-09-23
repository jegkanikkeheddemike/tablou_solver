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
        if (isSome) {
            System.out.println(str + value + "\n");
        } else {
            System.out.println(str + "None\n");
        }
    }

}
