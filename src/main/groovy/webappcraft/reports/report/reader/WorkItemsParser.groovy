package webappcraft.reports.report.reader

import webappcraft.reports.report.item.WorkItemList

interface WorkItemsParser {

    WorkItemList parse(List<String> input)

}
