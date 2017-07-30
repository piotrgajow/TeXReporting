class Task extends JiraTicket {

    String number
    String description
    String status

    Task(String line, String aNumber) {
        def tokens = line[1..-1].trim().replaceAll(' +', ' ').split(' ')
        number = aNumber
        ticketId = tokens[0]
        description = tokens[1..-2].collect {
            it == '&' ? '\\&' : it
        }.join(' ')
        status = tokens[-1] == '-' ? '' : tokens[-1]
    }

    String getReportEntry() {
        return "& ${number} & ${url} ${description} & ${status} & \\\\\n\\hline"
    }

}
