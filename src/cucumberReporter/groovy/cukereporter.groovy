import net.masterthought.cucumber.*

println "LOL!"
File reportOutputDirectory = new File("./cucumber-html-reports")
reportFiles = []
reportFiles.add('./cukes.json')

buildNumber = ""
buildProjectName = "#DeployDB"
skippedFails = false
undefinedFails = false
flashCharts = false
runWithJenkins = false
artifactsEnabled = false
artifactConfig = ''
highCharts = true


reportBuilder = new ReportBuilder(reportFiles,
                                  reportOutputDirectory,
                                  "", /* pluginUrlPath */
                                  buildNumber,
                                  buildProjectName,
                                  skippedFails,
                                  undefinedFails,
                                  flashCharts,
                                  runWithJenkins,
                                  artifactsEnabled,
                                  artifactConfig,
                                  highCharts)
reportBuilder.generateReports()
