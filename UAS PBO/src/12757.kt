import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.sql.DriverManager
import javax.swing.*
import javax.swing.filechooser.FileSystemView

class data12757 : JFrame() {
    private val tfname: JTextField? = null
    private val tfnim: JTextField? = null
    private val tfmatkul: JTextField? = null
    private val btnsub: JButton? = null
    private val panel: JPanel? = null
    private val tfipk: JTextField? = null
    private val btnpic: JButton? = null
    private val getpic: JLabel? = null

    init {

        btnsub!!.addActionListener(object : ActionListener {

            var mhs: db12757? = null
            private fun addDatabase(nama: String, nim: String, ipk: String, matkul: String, paspoto: String): db12757? {
                var mhs: db12757? = null
                val url = "jdbc:mysql://localhost/MahasiswaDB?serverTimezone=UTC"
                val username = "root"
                val password = ""
                try {
                    val conn = DriverManager.getConnection(url, username, password)
                    val statement = conn.createStatement()
                    val sql = "INSERT INTO users (nama, nim, ipk, matkul, foto)" +
                            "VALUES (?, ?, ? ,? ,?)"
                    val preparedStatement = conn.prepareStatement(sql)
                    preparedStatement.setString(1, nama)
                    preparedStatement.setString(2, nim)
                    preparedStatement.setString(3, ipk)
                    preparedStatement.setString(4, matkul)
                    preparedStatement.setString(5, paspoto)
                    val addRows = preparedStatement.executeUpdate()
                    if (addRows > 0) {
                        mhs = db12757()
                        mhs.nama = nama
                        mhs.nim = nim
                        mhs.ipk = ipk
                        mhs.matkul = matkul
                        mhs.foto = paspoto
                    }
                    statement.close()
                    conn.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return mhs
            }

            override fun actionPerformed(e: ActionEvent) {
                val nama = tfname!!.text
                val nim = tfnim!!.text
                val ipk = tfipk!!.text
                val matkul = tfmatkul!!.text
                val paspoto = getpic!!.text
                val subm = sub()
                subm.getData(nama, nim, ipk, matkul, paspoto)
                subm.isVisible = true
                dispose()
                mhs = addDatabase(nama, nim, ipk, matkul, paspoto)
            }


        })

        contentPane = panel
        title = "Student Data"
        setSize(500, 600)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
        btnpic!!.addActionListener { e ->
            val com = e.actionCommand
            if (com == "save") {
                val j = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
                val r = j.showSaveDialog(null)
                if (r == JFileChooser.APPROVE_OPTION) {
                    getpic!!.text = j.selectedFile.absolutePath
                } else getpic!!.text = "the user cancelled the operation"
            } else {
                val j = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)
                val r = j.showOpenDialog(null)
                if (r == JFileChooser.APPROVE_OPTION) {
                    getpic!!.text = j.selectedFile.absolutePath
                } else getpic!!.text = "the user cancelled the operation"
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = data12757()
        }
    }
}