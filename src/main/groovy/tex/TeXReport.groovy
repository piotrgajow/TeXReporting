
class TeXReport {

    def month
    def year
    def texContent = ''
    def index = 0
    def subIndex = 0

    void addContent(inputLines) {
        texContent = inputLines.collect {
            it[0] == '-' ? getTaskEntry(it) : getUserStoryEntry(it)
        }.join('\n')
    }

    // & 3.1 & CES-14 Spike: Wybór biblioteki REST - Jersey vs RestEasy vs Spring & TAK & \\\\
    // \\hline
    String getTaskEntry(line) {
        def task = new Task(line, "${index}.${++subIndex}")
        return task.reportEntry
    }

    String getUserStoryEntry(String line) {
        def us = new UserStory(line, ++index)
        return us.reportEntry
    }

    String getJiraTicketUrl(ticket) {
        "\\href{https://doskomp.atlassian.net/browse/${ticket}}{${ticket}}"
    }

    String getText() {
        """
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

Raport z wykonanych prac za miesiąc ${month} ${year} do umowy o świadczenie usług z dnia 07.04.2017 roku. \\\\

\\begin{tabularx}{\\textwidth}{|c|c|X|c|c|}
\\hline
Lp. & \\multicolumn{2}{l|}{Zadanie} & Ukończono & Uwagi \\\\
\\hline
${texContent}
\\end{tabularx}

\\end{document}
"""
    }

}
