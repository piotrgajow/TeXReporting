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
    private static final UserInterface UI = new UserInterface()
    private static final long TIMEOUT_S = 5

    ConfigObject config
    String year
    String month
    List<String> reportInput
    WorkItemList itemList

    void generateReport() {
        loadConfiguration()
        readInput()
        parseWorkItems()
        generateTexFile()
        generateReportPdf()
        cleanup()
    }

    private void loadConfiguration() {
        File configFile = new File(CONFIG_FILE_NAME)
        config = new ConfigSlurper().parse(configFile.toURI().toURL())
        Config.setConfig(config)
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
