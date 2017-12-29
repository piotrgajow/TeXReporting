package webappcraft.reporting.tex

abstract class TeXElement {

    abstract String texCode()

    protected static String escapeText(String input) {
        return input
                .replaceAll('\\$', '\\\\\\$')
                .replaceAll('\\{', '\\\\\\{')
                .replaceAll('\\}', '\\\\\\}')
    }

}
