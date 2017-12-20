package webappcraft.reports.main

import tex.TeXReport
import webappcraft.reports.ui.UserInterface

import java.util.concurrent.TimeUnit

class ReportGenerator {

    private static final String OUTPUT_FILE_NAME = 'report'
    private static final String TEX = 'tex'
    private static final String OUT = 'out'
    private static final String LOG = 'log'
    private static final String AUX = 'aux'
    private static final UserInterface UI = new UserInterface()
    private static final long TIMEOUT_S = 5

    String year
    String month
    List<String> reportInput

    void generateReport() {
        readInput()
        generateTexFile()
        generateReportPdf()
        cleanup()
    }

    private void readInput() {
        String fileName = UI.askFor('input file name')
        File inFile = new File(fileName)
        reportInput = inFile.readLines()
        month = UI.askFor('month')
        year = UI.askFor('year')
    }

    private void generateTexFile() {
        TeXReport report = new TeXReport()
        report.month = month
        report.year = year
        report.addContent(reportInput)

        File outFile = new File("${OUTPUT_FILE_NAME}.${TEX}")
        outFile.text = report.text
    }

    private static void generateReportPdf() {
        Process process = "pdflatex ${OUTPUT_FILE_NAME}.${TEX}".execute()
        process.waitFor(TIMEOUT_S, TimeUnit.SECONDS)
    }

    private static void cleanup() {
        List<String> extensions = [TEX, AUX, LOG, OUT]
        String filesToBeRemoved = extensions.collect {
            return "${OUTPUT_FILE_NAME}.${it}"
        }.join(' ')
        String cleanupCommand = "rm ${filesToBeRemoved}"
        Process process = cleanupCommand.execute()
        process.waitFor(TIMEOUT_S, TimeUnit.SECONDS)
    }

}
