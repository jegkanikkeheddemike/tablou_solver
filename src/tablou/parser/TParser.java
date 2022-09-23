package tablou.parser;

import java.util.ArrayList;

//import Tablou.solver.Value;

public final class TParser {
    static void parse(String raw) {
        System.out.println(split(raw));
    }

    static String[] split(String str) {

        ArrayList<String> result = new ArrayList<String>();

        int depth = 0;
        boolean onword = false;
        boolean hasdepth = false;
        int start = 0;

        char[] chars = new char[str.length()];
        str.getChars(0, str.length() - 1, chars, 0);

        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                onword = true;
            } else if (chars[i] == ' ') {
                onword = false;
            } else if (chars[i] == '(' && !onword) {
                hasdepth = true;
                depth++;
            } else if (chars[i] == ')' && depth == 0) {
                String part;
                if (hasdepth) {
                    part = str.substring(start + 1, i - 1);
                } else {
                    part = str.substring(start, i);
                }

                start = i;
                result.add(part);
            }
        }
        return (String[]) result.toArray();
    }
}
