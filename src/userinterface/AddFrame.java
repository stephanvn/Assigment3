package userinterface;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.Context;

public class AddFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton mb1;
	private JFileChooser fc;
	private Context con;
	private JLabel lab,lab1,lab2;
	private GridBagConstraints c = new GridBagConstraints();
	
	public AddFrame(Context cn) {
		super("Add Pattern");
		
		con = cn;
		
		panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridBagLayout());
        
        lab = new JLabel(" ");     
        c.gridx = 0;
		c.gridy = 0;		
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab,c);
        
        lab1 = new JLabel("Upload pattern(s) by selecting .txt or .xml file(s).");     
        c.gridx = 0;
		c.gridy = 1;		
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab1,c);
		
		lab2 = new JLabel(" ");     
        c.gridx = 0;
		c.gridy = 2;		
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab2,c);
        
        mb1 = new JButton("Select patterns");
        c.gridx = 0;
		c.gridy = 3;		
		c.anchor = GridBagConstraints.CENTER;
		panel.add(mb1,c);
        mb1.addActionListener(selectPatterns);

        

		setSize(500, 150);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener selectPatterns = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fc = new JFileChooser();
			fc.setDialogTitle("Choose a pattern");
			fc.setMultiSelectionEnabled(true);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt, .xml", "txt","xml");
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(panel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = fc.getSelectedFiles();
				StringBuilder filesRead = new StringBuilder();
				for(int i = 0;i<files.length;i++) {
					String ext = files[i].getName().substring(files[i].getName().lastIndexOf('.'));
					if(ext!=null && ext!="") {
						if(con.parseToPattern(files[i].getPath(),files[i].getName(),ext)) {
							filesRead.append(files[i].getName()+" succesfully read\n");
							dispose();
						}
						else {
							filesRead.append(files[i].getName()+" invalid\n");
						}
						
					}
				}
				JOptionPane.showMessageDialog(null, filesRead, "File(s) read",JOptionPane.PLAIN_MESSAGE);
			}	
		}
	};

}
