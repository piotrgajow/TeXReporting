package webappcraft.reports.report

class UserStory extends JiraTicket {

    String number
    String description
    String status

    UserStory(String line, int index) {
        def tokens = line.replaceAll(' +', ' ').split(' ')
        ticketId = tokens[0]
        description = tokens[1..-2].join(' ')
        status = tokens[-1] == '-' ? '' : tokens[-1]
        number = index
    }

    String getReportEntry() {
        "${number} & \\multicolumn{2}{X|}{${url} ${description}} & ${status} & \\\\\n\\hline"
    }

}