package webappcraft.reporting.reader

import webappcraft.reporting.item.WorkItemList
import webappcraft.reporting.item.JiraTicket

class WorkSummaryReader implements WorkItemsParser {

    private JiraTicket userStory

    @Override
    WorkItemList parse(List<String> input) {
        WorkItemList result = new WorkItemList()
        input.each {
            parseInputLine(it, result)
        }
        return result
    }

    private void parseInputLine(String line, WorkItemList list) {
        if (!line.startsWith('-')) {
            userStory = parseLine(line)
            list << userStory
        } else {
            JiraTicket task = parseLine(line[1..-1])
            userStory.addSubTask(task)
        }
    }

    private static JiraTicket parseLine(String line) {
        List<String> tokens = line.trim().replaceAll(' +', ' ').split(' ')
        String ticketId = tokens[0]
        String description = tokens[1..-1].join(' ')
        return new JiraTicket(ticketId, description)
    }

}
