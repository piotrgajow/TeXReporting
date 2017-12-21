package webappcraft.reporting.reader

import spock.lang.Specification

class WorkSummaryReaderSpec extends Specification {

    WorkSummaryReader subject = new WorkSummaryReader()

    void 'should properly parse user story'() {
        given:
        List<String> input = ['TEST-1 Test user story 1']

        when:
        def result = subject.parse(input)

        then:
        result.size() == 1
        result[0].ticketId == 'TEST-1'
        result[0].description == 'Test user story 1'
    }

    void 'should properly parse two user stories'() {
        given:
        List<String> input = ['TEST-1 Test user story 1', 'TEST-2 US-2']

        when:
        def result = subject.parse(input)

        then:
        result.size() == 2
        result[0].ticketId == 'TEST-1'
        result[0].description == 'Test user story 1'
        result[1].ticketId == 'TEST-2'
        result[1].description == 'US-2'
    }

    void 'should properly parse user story with sub tasks'() {
        given:
        List<String> input = ['TEST-1 Test user story 1', '- TEST-2 Subtask 1', '- TEST-3 Subtask 2']

        when:
        def result = subject.parse(input)

        then:
        result.size() == 1
        result[0].ticketId == 'TEST-1'
        result[0].description == 'Test user story 1'
        result[0].subTasks.size() == 2
        result[0].subTasks*.ticketId == ['TEST-2', 'TEST-3']
        result[0].subTasks.description == ['Subtask 1', 'Subtask 2']
    }


}
