package webappcraft.reporting.reader

import webappcraft.reporting.item.WorkItemList

interface WorkItemsParser {

    WorkItemList parse(List<String> input)

}
