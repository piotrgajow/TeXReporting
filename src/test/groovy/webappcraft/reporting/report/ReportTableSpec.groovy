package webappcraft.reporting.report

import spock.lang.Specification
import webappcraft.reporting.item.JiraTicket
import webappcraft.reporting.item.WorkItemList
import webappcraft.reporting.main.Config

class ReportTableSpec extends Specification {

    WorkItemList subject

    def setup() {
        subject = new WorkItemList()
        Config.values.jiraUrl = 'JIRAURL-'
    }

    void 'should generate empty table'() {
        expect:
        new ReportTable(subject).texCode() == expectedResult

        where:
        expectedResult = """
\\begin{tabularx}{\\textwidth}{|c|c|X|c|c|}
\\hline
Lp. & \\multicolumn{2}{l|}{Zadanie} & Ukończono & Uwagi \\\\
\\hline

\\end{tabularx}"""
    }

    void 'should generate table with single item'() {
        given:
        subject << new JiraTicket('TEST', 'Test ticket')

        expect:
        new ReportTable(subject).texCode() == expectedResult

        where:
        expectedResult = """
\\begin{tabularx}{\\textwidth}{|c|c|X|c|c|}
\\hline
Lp. & \\multicolumn{2}{l|}{Zadanie} & Ukończono & Uwagi \\\\
\\hline
1 & \\multicolumn{2}{X|}{\\href{JIRAURL-TEST}{TEST} Test ticket} & TAK & \\\\
\\hline
\\end{tabularx}"""
    }

    void 'should generate table with item and subitems'() {
        given:
        def item = new JiraTicket('TEST', 'Test ticket')
        def sub = new JiraTicket('SUB', 'Test sub ticket')
        item.addSubTask(sub)
        subject << item

        expect:
        new ReportTable(subject).texCode() == expectedResult

        where:
        expectedResult = """
\\begin{tabularx}{\\textwidth}{|c|c|X|c|c|}
\\hline
Lp. & \\multicolumn{2}{l|}{Zadanie} & Ukończono & Uwagi \\\\
\\hline
1 & \\multicolumn{2}{X|}{\\href{JIRAURL-TEST}{TEST} Test ticket} &  & \\\\
\\hline
& 1.1 & \\href{JIRAURL-SUB}{SUB} Test sub ticket & TAK & \\\\
\\hline
\\end{tabularx}"""
    }

}
