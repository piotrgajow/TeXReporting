
class JiraTicket {

    String ticketId
    String url

    String getUrl() {
        "\\href{https://doskomp.atlassian.net/browse/${ticketId}}{${ticketId}}"
    }

}
