package webappcraft.reports.report.tex

import webappcraft.reports.report.item.WorkItemList

class ReportTable implements TeXElement {

    WorkItemList items

    ReportTable(WorkItemList items) {
        this.items = items
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
        return items.collect { it, index ->
            formatUserStory(it, index)
        }
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
        String subTasksString = item.subTasks.collect { it, subTaskIndex ->
            String index = "${taskIndex}.${subTaskIndex}"
            String taskLabel = "\\multicolumn{2}{X|}{${item.url} ${item.description}}"
            return "& ${index} & ${taskLabel} & TAK & \\\\\n\\hline\n"
        }

        return "\n${subTasksString}"
    }

}
