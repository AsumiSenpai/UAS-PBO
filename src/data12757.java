import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class data12757 extends JFrame {
    private JTextField tfname;
    private JTextField tfnim;
    private JTextField tfmatkul;
    private JButton btnsub;
    private JPanel panel;
    private JTextField tfipk;
    private JButton btnpic;
    private JLabel getpic;

    public data12757() {
        setContentPane(panel);
        setTitle("Student Data");
        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnpic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String com = e.getActionCommand();

                if (com.equals("save")) {
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    int r = j.showSaveDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        getpic.setText(j.getSelectedFile().getAbsolutePath());
                    } else
                        getpic.setText("the user cancelled the operation");
                }
                else {
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    int r = j.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        getpic.setText(j.getSelectedFile().getAbsolutePath());
                    } else
                        getpic.setText("the user cancelled the operation");
                }
            }
        });

        btnsub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = tfname.getText();
                String nim = tfnim.getText();
                String ipk = tfipk.getText();
                String matkul = tfmatkul.getText();
                String paspoto = getpic.getText();

                sub subm = new sub();
                subm.getData(nama, nim, ipk, matkul, paspoto);
                subm.setVisible(true);
                dispose();

                mhs = addDatabase(nama, nim, ipk, matkul, paspoto);
            }

            public db12757 mhs;
            private db12757 addDatabase(String nama, String nim, String ipk, String matkul, String paspoto) {
                db12757 mhs = null;
                final String url = "jdbc:mysql://localhost/MahasiswaDB?serverTimezone=UTC";
                final String username ="root";
                final String password = "";

                try {
                    Connection conn = DriverManager.getConnection(url, username, password);

                    Statement statement = conn.createStatement();
                    String sql = "INSERT INTO users (nama, nim, ipk, matkul, foto)" +
                            "VALUES (?, ?, ? ,? ,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, nama);
                    preparedStatement.setString(2, nim);
                    preparedStatement.setString(3, ipk);
                    preparedStatement.setString(4, matkul);
                    preparedStatement.setString(5, paspoto);

                    int addRows = preparedStatement.executeUpdate();
                    if (addRows > 0) {
                        mhs = new db12757();
                        mhs.nama = nama;
                        mhs.nim = nim;
                        mhs.ipk = ipk;
                        mhs.matkul = matkul;
                        mhs.foto = paspoto;
                    }

                    statement.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mhs;
            }
        });
    }

    public static void main(String[] args) {
        data12757 result = new data12757();
    }
}
