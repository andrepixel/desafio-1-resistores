package br.com.desafio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        ArrayList<String> valueResistor = parseArgsToArraList(args);

        validateValueResitor(valueResistor);
    }

    private static ArrayList<String> parseArgsToArraList(String[] args) {
        ArrayList<String> valueResistor = new ArrayList<String>();

        valueResistor.add(args[0]);

        if (args.length < 3) {
            valueResistor.add(args[1]);
        }

        return valueResistor;
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
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(array.get(0));

        String numberResistor = "";

        if (matcher.find()) {
            numberResistor = matcher.replaceAll("");

            return numberResistor;
        }

        if (matcher2.find()) {
            numberResistor = matcher2.replaceAll("");

            return numberResistor;
        }

        return array.get(0);
    }

    private static String returnColorsResistor(String valuesResistor, String typeResistor) {
        String[] typeColorsResistors = { "preto", "marrom", "vermelho", "verde" };
        String colorDefaultResistor = "dourado";
        String[] colorsResistors = { "preto", "marrom", "vermelho", "laranja", "amarelo", "verde", "azul", "violeta",
                "cinza",
                "branco" };

        ArrayList<String> colorsReturn = new ArrayList<String>();
        ArrayList<String> arrayColorsResistors = new ArrayList<String>(Arrays.asList(colorsResistors));

        String[] valuesSeparetedResistor = valuesResistor.split("");

        Boolean isDotValue = verifyIfDotValueExists(valuesResistor);
        Integer valueParsed = 0;

        if (!isDotValue) {
            valueParsed = Integer.parseInt(valuesResistor);
        } else {
            String valuesResistorDontTheDot = removeDotOfValuesResistor(valuesResistor);

            valueParsed = Integer.parseInt(valuesResistorDontTheDot);

            valuesSeparetedResistor = valuesResistor.split("[.]");
        }

        if (valuesSeparetedResistor.length > 2) {
            for (int i = 0; i < valuesSeparetedResistor.length - 1; i++) {
                String colorRelated = arrayColorsResistors.get(Integer.parseInt(valuesSeparetedResistor[i]));
                colorsReturn.add(colorRelated);
            }
        } else {
            for (int i = 0; i < valuesSeparetedResistor.length; i++) {
                String colorRelated = arrayColorsResistors.get(Integer.parseInt(valuesSeparetedResistor[i]));
                colorsReturn.add(colorRelated);
            }
        }

        if (typeResistor == null) {
            if (valueParsed < 100) {
                colorsReturn.add(typeColorsResistors[0]);

                colorsReturn.add(colorDefaultResistor);

                return colorsReturn.toString();
            }

            if (valueParsed >= 100 && valueParsed <= 1000) {
                colorsReturn.add(typeColorsResistors[1]);

                colorsReturn.add(colorDefaultResistor);

                return colorsReturn.toString();
            }
        }

        if (valuesResistor.length() == 1) {
            colorsReturn.add(typeColorsResistors[0]);
        }

        if (typeResistor != null && typeResistor.equals("k")) {
            if (!isDotValue) {
                colorsReturn.add(typeColorsResistors[0]);
                colorsReturn.add(typeColorsResistors[2]);
            } else {
                colorsReturn.add(typeColorsResistors[2]);
            }
        }

        if (typeResistor != null && typeResistor.equals("M")) {
            if (!isDotValue) {
                colorsReturn.add(typeColorsResistors[0]);
                colorsReturn.add(typeColorsResistors[3]);
            } else {
                colorsReturn.add(typeColorsResistors[3]);
            }
        }

        colorsReturn.add(colorDefaultResistor);

        return colorsReturn.toString();
    }

    private static String removeDotOfValuesResistor(String valuesResistor) {
        return valuesResistor.replace(".", "");
    }

    private static Boolean verifyIfDotValueExists(String valuesResistor) {
        return valuesResistor.contains(".");
    }

    private static String validateSizeResistor(ArrayList<String> array) {
        String regex = "[k]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(array.get(0));

        String regex2 = "[M]";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(array.get(0));

        if (matcher.find()) {
            return "k";
        }

        if (matcher2.find()) {
            return "M";
        }

        return null;
    }
}
