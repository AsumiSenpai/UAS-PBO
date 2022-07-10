import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;

public class sub extends JFrame{
    private JPanel panel2;
    private JLabel inpname;
    private JLabel inpid;
    private JLabel inpipk;
    private JLabel inpmatkul;
    private JLabel inppic;

    public void getData(String nama, String nim, String ipk, String matkul, String paspoto) {
        inpname.setText(nama);
        inpid.setText(nim);
        inpipk.setText(ipk);
        inpmatkul.setText(matkul);
        ImageIcon Paspoto = new ImageIcon(new ImageIcon(paspoto).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        inppic.setIcon(Paspoto);
    }

    public sub() {
        setContentPane(panel2);
        setTitle("Data Mahasiswa");
        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        sub result = new sub();
    }
}


