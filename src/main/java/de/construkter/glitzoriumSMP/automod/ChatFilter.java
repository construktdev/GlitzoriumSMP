package de.construkter.glitzoriumSMP.automod;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatFilter {

    private static final Map<Character, Character> LEET = Map.of(
            '0', 'o', '1', 'i', '3', 'e', '4', 'a', '5', 's', '7', 't', '€', 'e'
    );

    private static final List<Pattern> BAD_WORDS = List.of(
            word("hure"),
            word("hurensohn"),
            word("nutte"),
            word("fotze"),
            word("wichser"),
            word("arschloch"),
            word("fick"),
            word("schwanz"),

            word("idiot"),
            word("vollidiot"),
            word("spast"),
            word("mongo"),
            word("missgeburt"),
            word("retard"),
            word("opfer"),

            word("assi"),
            word("penner"),
            word("clown"),
            word("lappen"),
            word("depp"),

            word("drecksack"),
            word("scheisskerl"),
            word("kackopfer"),

            word("schlampe"),
            word("bimbo"),

            word("schwuchtel"),
            word("faggot"),

            word("nigger"),
            word("nigga"),
            word("untermensch"),

            word("hitler"),
            word("heil"),
            word("siegheil"),
            word("nazi")
    );


    public static boolean containsBadLanguage(String message) {
        String normalized = normalize(message);
        return BAD_WORDS.stream().anyMatch(p -> p.matcher(normalized).find());
    }

    private static Pattern word(String base) {
        StringBuilder regex = new StringBuilder("\\b");
        for (char c : base.toCharArray()) {
            regex.append(c).append("+[^a-zA-Z0-9]*");
        }
        regex.append("\\b");
        return Pattern.compile(regex.toString());
    }

    private static String normalize(String input) {
        String s = input.toLowerCase();

        s = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        s = s.replace("ae", "a")
                .replace("oe", "o")
                .replace("ue", "u");

        StringBuilder out = new StringBuilder();
        for (char c : s.toCharArray()) {
            out.append(LEET.getOrDefault(c, c));
        }

        return out.toString().replaceAll("[^a-z0-9 ]", "");
    }
}
