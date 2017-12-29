package webappcraft.reporting.tex

import spock.lang.Specification
import spock.lang.Unroll

class TeXElementSpec extends Specification {

    @Unroll
    void 'escapeText should escape given string to be valid text in LaTeX'() {
        expect:
        TeXElement.escapeText(input) == expected

        where:
        input                           | expected
        ''                              | ''
        'regular text'                  | 'regular text'
        'url with params /instance/$id' | 'url with params /instance/\\$id'
        'text with {braces}'            | 'text with \\{braces\\}'

    }

}
