package br.com.desafio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ArrayList<String> valueResistor = new ArrayList<String>();
        valueResistor.add(args[0]);

        if (args.length < 3) {
            valueResistor.add(args[1]);
        }

        validateValueResitor(valueResistor);
    }

    public static void validateValueResitor(ArrayList<String> array) {
        if (!array.contains("ohms")) {
            System.out.println("Você não escreveu da forma correta");
        }

        String typeResistor = validateSizeResistor(array);
        String valueResistor = removeSizeResitor(array);

        String colorsResistor = returnColorsResistor(valueResistor, typeResistor);

        System.out.println(colorsResistor);
    }

    private static String removeSizeResitor(ArrayList<String> array) {
        String regex = "[k]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(array.get(0));

        String regex2 = "[M]";
        Pattern pattern2 = Pattern.compile(regex);
        Matcher matcher2 = pattern.matcher(array.get(0));

        String numberResistor = "";

        if (matcher.find()) {
            numberResistor = matcher.replaceAll("");

            return numberResistor;
        }

        if (matcher2.find()) {
            numberResistor = matcher2.replaceAll("");

            return numberResistor;
        }


        return null;
    }

    private static String returnColorsResistor(String numericResistor, String typeResistor) {
        String[] colorsDefault = { "vermelho", "verde" };
        String colorDefault = "dourado";
        String[] colors = { "preto", "marrom", "vermelho", "laranja", "amarelo", "verde", "azul", "violeta", "cinza",
                "branco" };

        ArrayList<String> colorsReturn = new ArrayList<String>();
        ArrayList<String> arrayColors = new ArrayList<String>(Arrays.asList(colors));

        String[] newr = numericResistor.split("");

        for (String number : newr) {
            String colorRelated = arrayColors.get(Integer.parseInt(number));
            colorsReturn.add(colorRelated);
        }

        if (typeResistor.equals("")) {
            colorsReturn.add(colorsDefault[0]);
        }

        if (typeResistor.equals("M")) {
            colorsReturn.add(colorsDefault[1]);
        }

        colorsReturn.add(colorDefault);

        return colorsReturn.toString();
    }

    private static String validateSizeResistor(ArrayList<String> array) {
        String regex = "[k]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(array.get(0));

        String regex2 = "[M]";
        Pattern pattern2 = Pattern.compile(regex);
        Matcher matcher2 = pattern.matcher(array.get(0));

        if (matcher.find()) {
            return "k";
        }

        if (matcher2.find()) {
            return "M";
        }

        return null;
    }
}
