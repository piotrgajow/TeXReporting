package webappcraft.reporting.tex

class TeXDocument implements TeXElement {

    List<TeXPackage> packages
    List<TeXElement> content

    @Override
    String texCode() {
        return """
\\\\documentclass[]{article}

\\\\usepackage[a4paper,left=1in,right=1in,top=1in,bottom=1in]{geometry}
\\\\usepackage{polski}
\\\\usepackage[utf8]{inputenc}
\\\\usepackage{tabularx}
\\\\usepackage{hyperref}

\\\\hypersetup{
\tcolorlinks=true,
\turlcolor=blue
}

\\\\begin{document}

Raport z wykonanych prac za miesiąc ${month} ${year} do umowy o świadczenie usług z dnia 07.04.2017 roku. \\\\\\\\

\\\\begin{tabularx}{\\\\textwidth}{|c|c|X|c|c|}
\\\\hline
Lp. & \\\\multicolumn{2}{l|}{Zadanie} & Ukończono & Uwagi \\\\\\\\
\\\\hline
${texContent}
\\\\end{tabularx}

\\\\end{document}"""
    }

}
