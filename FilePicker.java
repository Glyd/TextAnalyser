package Assignment.TextAnalyser;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FilePicker {
	
    private JFrame jFrame;
    private JFileChooser fc;
    
    public FilePicker() {
    	fc = new JFileChooser();
        jFrame = new JFrame();
        System.out.println("jframe built");
        jFrame.setVisible(true);
    }
    public File getFile() {
        JFileChooser fc = new JFileChooser();
        System.out.println("file chooser built");
        if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
        	System.out.println("file chosen");
            fc.removeAll();
            return fc.getSelectedFile();            
        } else {
            System.out.println("You did not select a file.");
            System.exit(1);
        }
        
        return null;
    }

}
