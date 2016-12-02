package Assignment.TextAnalyser;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FilePicker {
	
    private JFrame jFrame;
    public FilePicker() {
        jFrame = new JFrame();

        jFrame.setVisible(true);
        BringToFront();
    }
    public File getFile() {
        JFileChooser fc = new JFileChooser();
        if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)){
            jFrame.setVisible(true);
            return fc.getSelectedFile();
        }else {
            System.out.println("You did not select a file.");
            System.exit(1);
        }
        return null;
    }

    private void BringToFront() {                  
            jFrame.setExtendedState(JFrame.NORMAL);

    }

}
