package webappcraft.reports.report.tex

class TeXPackage implements TeXElement {

    String name
    List<String> params

    TeXPackage(String name) {
        this.name = name
    }

    TeXPackage(String name, List<String> params) {
        this(name)
        this.params = params
    }

    @Override
    String texCode() {
        return "\\usepackage${paramsTeX()}{${name}}"
    }

    private String paramsTeX() {
        if (!params) {
            return ''
        }

        String paramsString = params.join(',')
        return "[${paramsString}]"
    }

}
