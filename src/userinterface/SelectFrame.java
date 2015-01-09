package userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Context;
import domain.Pattern;

public class SelectFrame extends JFrame {
	private Context con;
	private JButton mb1,mb2,mb3,mb4,mb5;
	private JComboBox<String> mcb1,mcb2,mcb3;
	private JPanel panel,panel_0,panel_1,panel_2;
	private JTextField tf1;

	private static final long serialVersionUID = 1L;
	
	public SelectFrame(Context c) {
		super("Pattern Selector");
		
		con = c;
		
		panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        {
        	panel_0 = new JPanel();
        	panel.add(panel_0);
        	{
        		tf1 = new JTextField();
        		tf1.setColumns( 15 );
        		panel_0.add(tf1);
        		mb5 = new JButton("Search by Term");
        		panel_0.add(mb5);
        		mb5.addActionListener(searchbyTerm);
        	}
        }
        {
            panel_1 = new JPanel();
            panel.add(panel_1);
            {                
                mcb1 = new JComboBox<String>(c.getCategoryString('p'));
        		panel_1.add(mcb1);
        		
        		mcb2 = new JComboBox<String>(c.getCategoryString('s'));
        		panel_1.add(mcb2);
        		
        		mb1 = new JButton("Search by Categories");
        		mb1.addActionListener(searchPattern);
        		panel_1.add(mb1);
            }
        }
        {
            panel_2 = new JPanel();
            panel.add(panel_2);
            panel_2.setVisible(false);
            {                
                mcb3 = new JComboBox<String>();
                panel_2.add(mcb3);        		
        		
        		mb2 = new JButton("Show");
        		panel_2.add(mb2);
        		mb2.addActionListener(searchPatternInfo);
        		
        		mb3 = new JButton("Edit");
        		panel_2.add(mb3);
        		mb3.addActionListener(editPatternInfo);
        		
        		mb4 = new JButton("Delete");
        		panel_2.add(mb4);
        		mb4.addActionListener(deletePattern);
            }
        }

		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}	
	
	ActionListener searchbyTerm = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mcb3.removeAllItems();
			String term = tf1.getText();
			for(Pattern p : con.getPatterns()) {
				if(p.getName().toUpperCase().contains(term.toUpperCase())) {
					mcb3.addItem(p.getName());
				}
			}
			panel_2.setVisible(true);
			if(mcb3.getItemCount()==0) {
				panel_2.setVisible(false);
			}
		}
	};
	
	ActionListener searchPattern = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mcb3.removeAllItems();
			String pur = (String) mcb1.getSelectedItem();
			String scp = (String) mcb2.getSelectedItem();
			for(Pattern p : con.getPatterns()) {
				if(p.getPurpose().getName().equals(pur) && p.getScope().getName().equals(scp)) {
					mcb3.addItem(p.getName());
				}
			}
			panel_2.setVisible(true);
			if(mcb3.getItemCount()==0) {
				panel_2.setVisible(false);
			}
		}
	};
	
	ActionListener searchPatternInfo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String pattern = (String) mcb3.getSelectedItem();
			for(Pattern p : con.getPatterns()) {
				if(p.getName().equals(pattern)) {
					System.out.println(p.getName()+" clicked");
					new ShowFrame(p);
					break;
				}
			}			
		}
	};
	
	ActionListener editPatternInfo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String pattern = (String) mcb3.getSelectedItem();
			for(Pattern p : con.getPatterns()) {
				if(p.getName().equals(pattern)) {
					System.out.println(p.getName()+" clicked");
					new EditFrame(p,con);
					dispose();
					break;
				}
			}	
		}
	};
	
	ActionListener deletePattern = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String pattern = (String) mcb3.getSelectedItem();
			for(Pattern p : con.getPatterns()) {
				if(p.getName().equals(pattern)) {
					int n = JOptionPane.showConfirmDialog(null, "Do you really want to delete "+p.getName()+"?", "Delete "+p.getName(), JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						try {
							File file = new File("objecten/"+p.getName()+".obj");
							file.delete();
							con.removePattern(p);							
							JOptionPane.showMessageDialog(null, p.getName()+" succesfully removed.", p.getName()+" deleted", JOptionPane.PLAIN_MESSAGE);
							dispose();
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(null, p.getName()+" can not be removed.", "Error", JOptionPane.ERROR_MESSAGE);
						}						
					} 
					break;
				}
			}	
		}
	};
	
}
