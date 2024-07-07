package literalura.service;

public class IdiomaConversao {

    public static String converteIdioma(String idioma) {
        switch (idioma) {
            case "portugues":
                return "pt";
            case "ingles":
                return "en";
            case "espanhol":
                return "es";
            case "frances":
                return "fr";
            }

        return idioma;
    }
}
