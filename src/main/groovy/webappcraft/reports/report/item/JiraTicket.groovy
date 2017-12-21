package webappcraft.reports.report.item

class JiraTicket extends WorkItem {

    String ticketId
    String description
    List<JiraTicket> subTasks = []

    JiraTicket(String ticketId, String description) {
        this.ticketId = ticketId
        this.description = description
    }

    JiraTicket() {

    }

    void addSubTask(JiraTicket subTask) {
        subTasks << subTask
    }

    boolean hasSubTasks() {
        return !subTasks.isEmpty()
    }

    String getUrl() {
        "\\href{https://doskomp.atlassian.net/browse/${ticketId}}{${ticketId}}"
    }

}
