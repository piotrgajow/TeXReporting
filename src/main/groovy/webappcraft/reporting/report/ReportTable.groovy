package webappcraft.reporting.report

import webappcraft.reporting.item.WorkItemList
import webappcraft.reporting.tex.TeXElement

class ReportTable extends TeXElement {

    WorkItemList itemList

    ReportTable(WorkItemList items) {
        this.itemList = items
    }

    @Override
    String texCode() {
        return """
\\begin{tabularx}{\\textwidth}{|c|c|X|c|c|}
\\hline
Lp. & \\multicolumn{2}{l|}{Zadanie} & Ukończono & Uwagi \\\\
\\hline
${itemsTeX()}
\\end{tabularx}"""
    }

    private String itemsTeX() {
        //TODO - ADD SORTING
        return itemList.withIndex().collect { it, i ->
            return formatUserStory(it, i+1)
        }.join('\n')
    }

    static String formatUserStory(item, index) {
        String status = item.hasSubTasks() ? '' : 'TAK'
        String taskLabel = "\\multicolumn{2}{X|}{${item.url} ${item.description}}"
        return "${index} & ${taskLabel} & ${status} & \\\\\n\\hline${formatSubTasks(item, index)}"
    }

    static String formatSubTasks(item, taskIndex) {
        if (!item.hasSubTasks()) {
            return ''
        }

        //TODO - Add subtasks sorting
        String subTasksString = item.subTasks.withIndex().collect { it, i ->
            String index = "${taskIndex}.${i+1}"
            String subTaskLabel = "${it.url} ${it.description}"
            return "& ${index} & ${subTaskLabel} & TAK & \\\\\n\\hline"
        }.join('\n')

        return "\n${subTasksString}"
    }

}
