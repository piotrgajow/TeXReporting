package webappcraft.reports.report.tex

class ReportHeader implements TeXElement {

    String month
    String year

    ReportHeader(String month, String year) {
        this.month = month
        this.year = year
    }

    @Override
    String texCode() {
        return "Raport z wykonanych prac za miesiąc ${month} ${year} do umowy o świadczenie usług z dnia 07.04.2017.\\\\"
    }

}
