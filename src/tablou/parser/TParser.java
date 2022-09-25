package tablou.parser;

import java.util.ArrayList;
import java.util.HashMap;

import tablou.solver.Atomic;
import tablou.solver.Value;
import tablou.solver.operators.And;
import tablou.solver.operators.Not;
import tablou.solver.operators.Or;

public final class TParser {

    static HashMap<String, Atomic> VARIABLES = new HashMap<String, Atomic>();

    public static Value start_parse(String raw) throws FailedToParseException {

        VARIABLES = new HashMap<String, Atomic>();

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

            return new Atomic(split.get(0));

        } else if (split.size() == 2) {

            return new Not(split.get(1));

        } else {
            String splits = "";
            for (String part : split) {
                splits += "\n\"" + part + "\"";
            }

            throw new FailedToParseException("Parathesis split failed to split " + raw
                    + " to three or one substrings\nResulting splits: " + splits);
        }

    }

    static ArrayList<String> split(String str) throws FailedToParseException {
        str = str.trim();
        str = str.replaceAll("\\s+", " "); // REMOVES DUPLICATE SPACES
        str = str + ' ';
        ArrayList<String> result = new ArrayList<String>();

        // IF IS A NOT, THEN JUST KINDA DONT DO ANYHING :)
        if (str.length() > 6 && str.subSequence(0, 3).equals("NOT")) {
            if (str.length() != str.indexOf(')') + 2) {
                throw new FailedToParseException("Expected \")\" to be the end of the string after NOT(X) but found: \""
                        + str.substring(str.indexOf(')')) + "\"\nMaybe surround the NOT(X) with some parenthesis!");
            }

            String inner = str.substring(3, str.length() - 1);
            result.add("NOT");
            result.add(inner);
            return result;
        }

        int depth = 0;
        boolean hasdepth = false;
        boolean any1hasdepth = false;
        int start = 0;

        char[] chars = new char[str.length()];
        str.getChars(0, str.length(), chars, 0);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                if (depth == 0) {
                    String part;
                    if (hasdepth) {
                        part = str.substring(start + 1, i - 1);
                    } else {
                        part = str.substring(start, i);
                    }

                    hasdepth = false;

                    start = i + 1;
                    result.add(part);
                }

            } else if (chars[i] == '(') {
                hasdepth = true;
                any1hasdepth = true;
                depth++;
            } else if (chars[i] == ')') {
                if (depth == 0) {
                    throw new FailedToParseException(
                            "Reached negative depth at " + str + " by closing parenthesis before opening.");
                }
                depth--;

            }
        }
        if (depth != 0) {
            throw new FailedToParseException("Failed to split \"" + str + "\". Missing parenthesis.");
        }

        if (result.size() == 1 && any1hasdepth) {
            return split(result.get(0));
        }

        return result;

    }
}