import tex.TeXReport

def fileName = args[0]
def inFile = new File("${fileName}.txt")
def outFile = new File("${fileName}.tex")

TeXReport report = new TeXReport()
report.month = args[1]
report.addContent(inFile.readLines())
outFile.text = report.text
