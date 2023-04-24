import java.io.PrintWriter

open class DimenFactory : DefaultTask() {
    private val dimens = intArrayOf(
        320, 360, 384, 411, 480, 540, 600, 720, 800, 960, 1024, 1080, 1280, 1440, 2560, 3840
    )
    private val fromDimen = 360f
    private val positiveMaxDP = 400
    private val positiveMaxSP = 60
    private val negativeMaxDP = 60
    private val resFolder = project.projectDir.path + "/src/main/res/"

    @TaskAction
    fun create() {
        autoCreateDimen()
    }

    private fun autoCreateDimen() {
        // write dimen.xml
        val defaultFolder = resFolder + "values"
        val defaultDimensFile = "$defaultFolder/dimens.xml"
        File(defaultFolder).mkdir()
        File(defaultDimensFile).createNewFile()
        writeAutoDimen(defaultDimensFile, 360)

        // write other auto_dimens.xml
        for (dimen in dimens) {
            val folder = resFolder + "values-sw" + dimen + "dp"
            val fileName = "$folder/auto_dimens.xml"
            File(folder).mkdir()
            File(fileName).createNewFile()
            writeAutoDimen(fileName, dimen)
        }
    }

    private fun writeAutoDimen(fileName: String, dimen: Int) {
        println("Auto create dimension file and values $fileName")
        val printWriter = PrintWriter(fileName)
        printWriter.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
        printWriter.println("<resources>")
        for (i in 0..positiveMaxDP) {
            val ratio = dimen / fromDimen
            val dp = ratio * i
            printWriter.printf("\t<dimen name=\"dp_%d\">%.2fdp</dimen>\r\n", i, dp)
        }
        printWriter.println()
        for (i in 1..negativeMaxDP) {
            val ratio = dimen / fromDimen
            val dp = ratio * i
            printWriter.printf("\t<dimen name=\"dp_minus%d\">%.2fdp</dimen>\r\n", i, -dp)
        }
        printWriter.println()
        for (i in 1..positiveMaxSP) {
            val ratio = dimen / fromDimen
            val sp = ratio * i
            printWriter.printf("\t<dimen name=\"sp_%d\">%.2fsp</dimen>\r\n", i, sp)
        }
        printWriter.println("</resources>")
        printWriter.close()
    }
}

tasks.register("createDimen", DimenFactory::class) {
    create()
}
