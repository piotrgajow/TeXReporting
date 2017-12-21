package webappcraft.reports.report.tex

import spock.lang.Specification
import spock.lang.Unroll

class TeXPackageSpec extends Specification {

    @Unroll
    void 'should create entry with only package name'() {
        expect:
        new TeXPackage(name).texCode() == expectedResult

        where:
        name       | expectedResult
        'polski'   | '\\usepackage{polski}'
        'tabularx' | '\\usepackage{tabularx}'
        'hyperref' | '\\usepackage{hyperref}'
    }

    @Unroll
    void 'should create entry with parameters'() {
        expect:
        new TeXPackage(name, params).texCode() == expectedResult

        where:
        name       | params                                                        | expectedResult
        'inputenc' | ['utf8']                                                      | '\\usepackage[utf8]{inputenc}'
        'geometry' | ['a4paper', 'left=1in', 'right=1in', 'top=1in', 'bottom=1in'] | '\\usepackage[a4paper,left=1in,right=1in,top=1in,bottom=1in]{geometry}'
    }

}
