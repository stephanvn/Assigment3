package userinterface;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.Category;
import domain.Context;
import domain.Pattern;

public class EditFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lab, lab2,lab3,lab4,lab5,lab6,lab7,white1,white2,white3,white4,white5,white6,white7;
	private JTextArea tf1,tf2,tf3,tf4,tf5;
	private JComboBox<String> cb1,cb2;
	private JButton jb1;
	private Pattern oldPattern;
	private Context con;
	private GridBagConstraints c = new GridBagConstraints();
	public EditFrame(Pattern p,Context cn){
		super("Edit Pattern");
		
		oldPattern = p;
		con = cn;
		panel = new JPanel();
		getContentPane().add(panel,BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		
		lab = new JLabel("Context:");
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab,c);
		
		tf1 = new JTextArea(oldPattern.getContext());
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(tf1,c);
		
		white1 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(white1,c);
		
		lab2 = new JLabel("Problem:");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab2,c);
		
		tf2 = new JTextArea(oldPattern.getProblem());
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		panel.add(tf2,c);
		
		white2 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(white2,c);
		
		lab3 = new JLabel("Solution:");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab3,c);
		
		tf3 = new JTextArea(oldPattern.getSolution());
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panel.add(tf3,c);
		
		white3 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(white3,c);
		
		lab4 = new JLabel("Diagram:");
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab4,c);
		
		tf4 = new JTextArea(oldPattern.getDiagram());
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		panel.add(tf4,c);
		
		white4 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 7;
		panel.add(white4,c);
		
		lab5 = new JLabel("Consequences:");
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab5,c);
		
		tf5 = new JTextArea(oldPattern.getConsequences());
		c.gridx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		panel.add(tf5,c);
		
		white5 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 9;
		panel.add(white5,c);
		
		lab6 = new JLabel("Purpose:");
		c.gridx = 0;
		c.gridy = 10;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab6,c);

		cb1 = new JComboBox<String>(con.getCategoryString('p'));
		cb1.setSelectedItem(oldPattern.getPurpose().getName());
		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		panel.add(cb1,c);
		
		white6 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 11;
		panel.add(white6,c);
		
		lab7 = new JLabel("Scope:");
		c.gridx = 0;
		c.gridy = 12;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(lab7,c);
		
		cb2 = new JComboBox<String>(con.getCategoryString('s'));
		cb2.setSelectedItem(oldPattern.getScope().getName());
		c.gridx = 1;
		c.gridy = 12;
		c.anchor = GridBagConstraints.WEST;
		panel.add(cb2,c);
		
		white7 = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 13;
		panel.add(white7,c);
		
		jb1 = new JButton("Submit");
		c.gridx = 1;
		c.gridy = 14;
		c.anchor = GridBagConstraints.WEST;
		panel.add(jb1,c);
		jb1.addActionListener(editPattern);
		
		setSize(900, 900);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	ActionListener editPattern = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("") || tf4.getText().equals("") || tf5.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				Pattern newPattern = con.searchPattern(oldPattern.getName());
				newPattern.setContext(tf1.getText());
				newPattern.setProblem(tf2.getText());
				newPattern.setSolution(tf3.getText());
				newPattern.setDiagram(tf4.getText());
				newPattern.setConsequences(tf5.getText());
				Category pur = con.searchCategory((String)cb1.getSelectedItem());
				newPattern.setPurpose(pur);
				Category scp = con.searchCategory((String)cb2.getSelectedItem());
				newPattern.setScope(scp);
				con.writeObject(newPattern);
				JOptionPane.showMessageDialog(null, newPattern.getName()+" is succesfully changed", newPattern.getName()+" changed",JOptionPane.PLAIN_MESSAGE);
				dispose();
			}
		}
	};

}
