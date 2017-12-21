package webappcraft.reporting.tex

class TeXDocument implements TeXElement {

    List<TeXPackage> packages
    List<TeXElement> content

    @Override
    String texCode() {
        return """
\\documentclass[]{article}
${packagesTex()}

\\hypersetup{
    colorlinks=true,
    urlcolor=blue
}

\\begin{document}
${contentTex()}
\\end{document}"""
    }

    private String packagesTex() {
        packages*.texCode().join('\n')
    }

    private String contentTex() {
        content*.texCode().join('\n')
    }

}
