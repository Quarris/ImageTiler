import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageTiler extends JFrame implements ActionListener {

    private Tiler tiler;
    private JButton reloadButton;
    private JButton fileChooserButton;
    private JTextField pathField;
    private JFileChooser fileChooser;

    private Container contents;

    public ImageTiler() {
        super("Image Tiler");
        this.setMinimumSize(new Dimension(500, 500));

        contents = this.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        tiler = new Tiler();

        reloadButton = new JButton("Reload");
        fileChooserButton = new JButton("Select");
        fileChooser = new JFileChooser();
        pathField = new JTextField(24);
        fileChooser = new JFileChooser();

        layout.setConstraints(tiler, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10,10,0,10), 0, 0));
        layout.setConstraints(pathField, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10,10,10,0), 0, 0));
        layout.setConstraints(fileChooserButton, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,10,0,10), 0, 0));
        layout.setConstraints(reloadButton, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,10,0,0), 0, 0));
        contents.setLayout(layout);

        reloadButton.addActionListener(this);
        fileChooserButton.addActionListener(this);

        contents.add(tiler);
        contents.add(pathField);
        contents.add(reloadButton);
        contents.add(fileChooserButton);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileChooserButton) {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                pathField.setText(fileChooser.getSelectedFile().getPath());
                System.out.println(pathField.getText());
                tiler.reload(pathField.getText());
            }
        }
        else if (e.getSource() == reloadButton) {
            tiler.reload(pathField.getText());
        }
    }

    public static void main(String[] args) {
        new ImageTiler();
    }
}
