package webappcraft.reporting.main

import webappcraft.reporting.item.WorkItemList
import webappcraft.reporting.reader.WorkSummaryReader
import webappcraft.reporting.report.WorkReport
import webappcraft.reporting.ui.UserInterface

import java.util.concurrent.TimeUnit

class ReportGenerator {

    private static final String CONFIG_FILE_NAME = 'config.groovy'
    private static final String OUTPUT_FILE_NAME = 'report'
    private static final String TEX = 'tex'
    private static final String OUT = 'out'
    private static final String LOG = 'log'
    private static final String AUX = 'aux'
    private static final String PDF = 'pdf'
    private static final UserInterface UI = new UserInterface()
    private static final long TIMEOUT_S = 10

    String year
    String month
    List<String> reportInput
    WorkItemList itemList

    void generateReport() {
        try {
            loadConfiguration()
            readInput()
            parseWorkItems()
            generateTexFile()
            generateReportPdf()
            cleanup()
        } catch (e) {
            UI.inform("Error: ${e.message}")
            System.exit(1)
        }
    }

    private static void loadConfiguration() {
        File configFile = new File(CONFIG_FILE_NAME)
        Config.values = new ConfigSlurper().parse(configFile.toURI().toURL())
    }

    private void readInput() {
        String fileName = UI.askFor('input file name')
        File inFile = new File(fileName)
        reportInput = inFile.readLines()
        month = UI.askFor('month')
        year = UI.askFor('year')
    }

    private void parseWorkItems() {
        WorkSummaryReader reader = new WorkSummaryReader()
        itemList = reader.parse(reportInput)
    }

    private void generateTexFile() {
        WorkReport report = new WorkReport(month, year, itemList)
        File outFile = new File("${OUTPUT_FILE_NAME}.${TEX}")
        outFile.text = report.texCode()
    }

    private static void generateReportPdf() {
        Process process = "pdflatex -halt-on-error -enable-installer ${OUTPUT_FILE_NAME}.${TEX}".execute()
        process.waitFor(TIMEOUT_S, TimeUnit.SECONDS)
        if (process.exitValue() == 0) {
            UI.inform("Report generated ${OUTPUT_FILE_NAME}.${PDF}")
        } else {
            throw new RuntimeException("PDF file generation failed - see log file ${OUTPUT_FILE_NAME}.${LOG}")
        }
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
