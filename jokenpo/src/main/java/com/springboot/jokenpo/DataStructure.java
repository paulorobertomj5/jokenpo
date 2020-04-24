package com.springboot.jokenpo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataStructure {
    public static void main(String[] args) {

//        // remove palavras repetidas
        //final String regex = "\\b(\\w+)\\b\\s*(?=.*\\b\\1\\b)";
//        final String input = "Goodbye bye bye world world world\n";
//
//        final String result = input.replaceAll(regex, "");
//        System.out.println(result);


        Pattern p = Pattern.compile("\\b(\\w+)\\b(?=.*\\b(\\1)\\b)", Pattern.CASE_INSENSITIVE);
        //Mostra palavras repetidas e as posições
        String s = "I Want to eat eat eat apple. apple is a fruit.\r\n I really want fruit.";
        final String sNovo = s.replaceAll(p.toString(), "");
        System.out.println("sNovo = " + sNovo);
//        Matcher m = p.matcher(s);
//        while (m.find()) {
//            System.out.println("at: " + m.start(1) + " " + m.group(1));
//            System.out.println("    " + m.start(2) + " " + m.group(2));
//        }

        //Mostra quantas vezes a palavra want esta na frase
        Pattern patternWant = Pattern.compile("want", Pattern.CASE_INSENSITIVE);
        Matcher matcherWant = patternWant.matcher(s);

        int i = 0;
        while (matcherWant.find()){
            i++;
        }
        System.out.println("Want esta repetido = "+i);

        Pattern patternEat = Pattern.compile("eat", Pattern.CASE_INSENSITIVE);
        Matcher matcherEat = patternEat.matcher(s);
        int j = 0;
        while (matcherEat.find()){
            j++;
        }
        System.out.println("matcherEat esta repetido = "+j);
    }
}

