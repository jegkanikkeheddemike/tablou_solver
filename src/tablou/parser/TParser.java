package tablou.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Set;

import tablou.solver.Atomic;
import tablou.solver.Value;
import tablou.solver.operators.And;
import tablou.solver.operators.Or;

public final class TParser {

    static AbstractMap<String, Atomic> VARIABLES = new AbstractMap<String, Atomic>() {
        @Override
        public Set<Entry<String, Atomic>> entrySet() {
            return null;
        }
    };

    public static Value start_parse(String raw) throws FailedToParseException {

        VARIABLES = new AbstractMap<String, Atomic>() {
            @Override
            public Set<Entry<String, Atomic>> entrySet() {
                return null;
            }
        };

        return parse(raw);

    }

    public static Value parse(String raw) throws FailedToParseException {
        ArrayList<String> split = split(raw);

        if (split.size() == 3) {
            String first = split.get(0);
            String operator = split.get(1);
            String second = split.get(2);

            if (operator.equals("AND")) {
                return new And(first, second);
            } else if (operator.equals("OR")) {
                return new Or(first, second);
            } else {
                throw new FailedToParseException("Failed to parse \"" + operator + "\" to a valid operator at " + raw
                        + "\nfirst: " + first + "\noperator: " + operator + "\nsecond: " + second);
            }

        } else if (split.size() == 1) {
            return new Atomic(raw);
        } else {
            String splits = "";
            for (String part : split) {
                splits += "\n" + part;
            }


            throw new FailedToParseException("Parathesis split failed to split " + raw + " to three or one substrings\nResulting splits: " + splits);
        }

    }

    static ArrayList<String> split(String str) throws FailedToParseException {
        System.out.println("SPLITTING: " + str);
        System.out.println("_________________");

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
                if (depth == 0) {

                    System.out.println("INTERVAL: " + str.substring(start,i));


                    String part;
                    if (hasdepth) {
                        part = str.substring(start + 1, i - 1);
                    } else {
                        part = str.substring(start, i);
                    }
                    System.out.println("PART: " + part);

                    hasdepth = false;

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
        if (depth != 0) {
            throw new FailedToParseException("Failed to split \"" + str + "\". Missing parenthesis.");
        }

        for (String strpart : result) {
            System.out.println(strpart);
        }

        return result;
    }
}