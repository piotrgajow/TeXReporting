package webappcraft.reporting.item

import webappcraft.reporting.main.Config

class JiraTicket extends WorkItem {

    String ticketId
    String description
    List<JiraTicket> subTasks = []

    JiraTicket(String ticketId, String description) {
        this.ticketId = ticketId
        this.description = description
    }

    void addSubTask(JiraTicket subTask) {
        subTasks << subTask
    }

    boolean hasSubTasks() {
        return !subTasks.isEmpty()
    }

    String getUrl() {
        "\\href{${Config.jiraUrl}${ticketId}}{${ticketId}}"
    }

}
