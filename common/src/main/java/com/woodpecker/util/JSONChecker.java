package com.woodpecker.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * JSON格式字符串验证器
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public final class JSONChecker {
    private CharacterIterator ci;
    private char tmp;
    private int num;

    /**
     * 验证API
     *
     * @param arg JSON字符串
     * @return true-合法 ，false-非法
     */
    public boolean checker(String arg) {
        arg = arg.trim();
        if ("".equals(arg)) {
            return true;
        }
        boolean result = true;
        ci = new StringCharacterIterator(arg);
        tmp = ci.first();
        num = 1;
        if (!value()) {
            result = false;
        } else {
            while (Character.isWhitespace(tmp)) {
                nextCharacter();
            }
            if (tmp != CharacterIterator.DONE) {
                result = false;
            }
        }
        return result;
    }

    /**
     * return the char num of the JSON string
     *
     * @return the char num
     */
    public int getNum() {
        return num;
    }

    private boolean value() {
        return literal("true") || literal("false") || literal("null") || string() || number() || aggregate('{', '}', true) || aggregate('[', ']', false);
    }

    private boolean literal(String text) {
        CharacterIterator ci = new StringCharacterIterator(text);
        char t = ci.first();
        if (tmp != t) return false;

        boolean ret = true;
        for (t = ci.next(); t != CharacterIterator.DONE; t = ci.next()) {
            if (t != nextCharacter()) {
                ret = false;
                break;
            }
        }
        nextCharacter();
        return ret;
    }

    private boolean aggregate(char entryCharacter, char exitCharacter, boolean prefix) {
        if (tmp != entryCharacter) return false;
        nextCharacter();
        while (Character.isWhitespace(tmp)) {
            nextCharacter();
        }
        if (tmp == exitCharacter) {
            nextCharacter();
            return true;
        }

        for (;;) {
            if (prefix) {
                if (!string()) return false;
                while (Character.isWhitespace(tmp)) {
                    nextCharacter();
                }
                if (tmp != ':') return false;
                nextCharacter();
                while (Character.isWhitespace(tmp)) {
                    nextCharacter();
                }
            }
            if (value()) {
                while (Character.isWhitespace(tmp)) {
                    nextCharacter();
                }
                if (tmp == ',') {
                    nextCharacter();
                } else if (tmp == exitCharacter) {
                    break;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            while (Character.isWhitespace(tmp)) {
                nextCharacter();
            }
        }

        nextCharacter();
        return true;
    }

    private boolean number() {
        if (!Character.isDigit(tmp) && tmp != '-') return false;
        if (tmp == '-') nextCharacter();
        if (tmp == '0') {
            nextCharacter();
        } else if (Character.isDigit(tmp)) {
            while (Character.isDigit(tmp))
                nextCharacter();
        } else {
            return false;
        }
        if (tmp == '.') {
            nextCharacter();
            if (Character.isDigit(tmp)) {
                while (Character.isDigit(tmp))
                    nextCharacter();
            } else {
                return false;
            }
        }
        if (tmp == 'e' || tmp == 'E') {
            nextCharacter();
            if (tmp == '+' || tmp == '-') {
                nextCharacter();
            }
            if (Character.isDigit(tmp)) {
                while (Character.isDigit(tmp))
                    nextCharacter();
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean string() {
        if (tmp != '"') return false;
        boolean escaped = false;
        for (nextCharacter(); tmp != CharacterIterator.DONE; nextCharacter()) {
            if (!escaped && tmp == '\\') {
                escaped = true;
            } else if (escaped) {
                if (!escape()) {
                    return false;
                }
                escaped = false;
            } else if (tmp == '"') {
                nextCharacter();
                return true;
            }
        }
        return false;
    }

    private boolean escape() {
        if (" \\\"/bfnrtu".indexOf(tmp) < 0) {
            return false;
        }
        if (tmp == 'u') {
            if ("0123456789abcdefABCDEF".indexOf(tmp) >= 0) {
                return false;
            }
        }
        return true;
    }

    private char nextCharacter() {
        tmp = ci.next();
        ++num;
        return tmp;
    }

    public static void main(String[] args){
        JSONChecker validator = new JSONChecker();
        String jsonStr = "{\"website\":\"china.net\"}";
        System.out.println(validator.checker(jsonStr));
    }

}
