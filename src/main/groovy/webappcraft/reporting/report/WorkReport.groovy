package webappcraft.reporting.report

import webappcraft.reporting.item.WorkItemList
import webappcraft.reporting.tex.TeXDocument
import webappcraft.reporting.tex.TeXElement
import webappcraft.reporting.tex.TeXPackage
import webappcraft.reporting.tex.TeXParameter

class WorkReport extends TeXDocument {

    WorkReport(String month, String year, WorkItemList itemList) {
        this.packages = buildPackages()
        this.configurations = buildConfigurations()
        this.content = buildContent(month, year, itemList)
    }

    private static List<TeXPackage> buildPackages() {
        return [
                new TeXPackage('geometry', ['a4paper', 'left=1in', 'right=1in', 'top=1in', 'bottom=1in']),
                new TeXPackage('polski'),
                new TeXPackage('inputenc', ['utf8']),
                new TeXPackage('tabularx'),
                new TeXPackage('hyperref'),
        ]
    }

    private static List<TeXParameter> buildConfigurations() {
        return [
            new TeXParameter("\\hypersetup{\n\tcolorlinks=true,\n\turlcolor=blue\n}")
        ]
    }

    private static List<TeXElement> buildContent(String month, String year, WorkItemList itemList) {
        return [
                new ReportHeader(month, year),
                new ReportTable(itemList),
        ]
    }

}
