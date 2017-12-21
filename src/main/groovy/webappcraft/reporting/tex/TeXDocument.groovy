package webappcraft.reporting.tex

class TeXDocument implements TeXElement {

    List<TeXPackage> packages
    List<TeXParameter> configurations
    List<TeXElement> content

    @Override
    String texCode() {
        return """
\\documentclass[]{article}
${formatTex(packages)}

${formatTex(configurations)}

\\begin{document}
${formatTex(content)}
\\end{document}"""
    }

    private static String formatTex(List<TeXElement> elements) {
        return elements*.texCode().join('\n')
    }

}
