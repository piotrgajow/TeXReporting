package webappcraft.reporting.report

import webappcraft.reporting.item.WorkItemList
import webappcraft.reporting.tex.TeXElement

class WorkReport implements TeXElement {

    ReportHeader header
    ReportTable table

    WorkReport(String month, String year, WorkItemList itemList) {
        this.header = new ReportHeader(month, year)
        this.table = new ReportTable(itemList)
    }

    @Override
    String texCode() {
        return """
\\documentclass[]{article}

\\usepackage[a4paper,left=1in,right=1in,top=1in,bottom=1in]{geometry}
\\usepackage{polski}
\\usepackage[utf8]{inputenc}
\\usepackage{tabularx}
\\usepackage{hyperref}

\\hypersetup{
	colorlinks=true,
	urlcolor=blue
}

\\begin{document}

${header.texCode()}
${table.texCode()}

\\end{document}"""
    }

}


