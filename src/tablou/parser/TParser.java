package tablou.parser;

import java.util.ArrayList;

import tablou.solver.Value;
import tablou.solver.operators.And;
import tablou.solver.operators.Or;

public final class TParser {

    // PARSE TEST
    public static void main(String[] args) {

        try {
            parse("A OR (B or C)");
        } catch (FailedToParseException e) {
            System.out.println(e.toString());
        }

    }

    public static Value parse(String raw) throws FailedToParseException {
        ArrayList<String> split = split(raw);

        if (split.size() == 3) {
            String first = split.get(0);
            String operator = split.get(1);
            String second = split.get(2);

            if (operator == "AND") {
                return new And(first, second);
            } else if (operator == "OR") {
                return new Or(first, second);
            } else {
                throw new FailedToParseException();
            }

        } else {
            throw new FailedToParseException();
        }
    }

    static ArrayList<String> split(String str) {
        str = str + ' ';
        ArrayList<String> result = new ArrayList<String>();

        int depth = 0;
        boolean onword = false;
        boolean hasdepth = false;
        int start = 0;

        char[] chars = new char[str.length()];
        str.getChars(0, str.length(), chars, 0);

        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                onword = true;
            } else if (chars[i] == ' ') {
                onword = false;
                System.out.println("SPACE with depth " + depth);

                if (depth == 0) {

                    String part;
                    if (hasdepth) {
                        part = str.substring(start + 1, i - 1);
                    } else {
                        part = str.substring(start, i);
                    }

                    start = i + 1;
                    result.add(part);
                }

            } else if (chars[i] == '(' && !onword) {
                hasdepth = true;
                depth++;
            } else if (chars[i] == ')') {
                depth--;

            }
        }
        return result;
    }
}