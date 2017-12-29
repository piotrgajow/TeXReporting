package webappcraft.reporting.tex

class TeXParameter extends TeXElement {

    String text

    TeXParameter(String text) {
        this.text = text
    }

    @Override
    String texCode() {
        return text
    }

}
