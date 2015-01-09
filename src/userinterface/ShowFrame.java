package userinterface;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.imgscalr.Scalr;

import domain.Pattern;

public class ShowFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lab,lab1,lab2,lab3,lab4,lab5,lab6,lab7,labImage,lab8,lab9,lab10,lab11,lab12,lab13;
	private Pattern pattern;
	private GridBagConstraints c = new GridBagConstraints();
	public ShowFrame(Pattern p) {
		super("Show Pattern");
		JScrollPane scroll;
		pattern = p;
		panel = new JPanel();
		getContentPane().add(panel,BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		scroll = new JScrollPane(panel);
		add(scroll);
		
		lab = new JLabel("Name:");
		c.gridx = 0;
		c.gridy = 0;
		
		c.anchor = GridBagConstraints.WEST;
		panel.add(lab,c);
		lab1 = new JLabel(pattern.getName());
		c.gridx = 1;
		c.gridy = 0;
		panel.add(lab1,c);
		lab8 = new JLabel("Context:");
		c.gridx = 0;
		c.gridy = 1;
		
		panel.add(lab8,c);
		lab4 = new JLabel("<html><body style='width: 300 px'>"+pattern.getContext());
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 300;
		c.ipadx = 10;
		c.ipady = 10;
		panel.add(lab4,c);
		
		lab9 = new JLabel("Problem:");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(lab9,c);
		lab5 = new JLabel("<html><body style='width: 300 px'>"+pattern.getProblem());
		c.gridx = 2;
		c.gridy = 2;
		panel.add(lab5,c);
		
		lab10 = new JLabel("Solution:");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(lab10,c);
		lab6 = new JLabel("<html><body style='width: 300 px'>"+pattern.getSolution());
		c.gridx = 2;
		c.gridy = 3;
		panel.add(lab6,c);
		
		lab11 = new JLabel("Consequences:");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(lab11,c);
		lab7 = new JLabel("<html><body style='width: 300 px'>"+pattern.getConsequences());
		c.gridx = 2;
		c.gridy = 4;
		panel.add(lab7,c);
		
		lab2 = new JLabel("Purpose:");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(lab2,c);
		lab12 = new JLabel(pattern.getPurpose().getName());
		c.gridx = 2;
		c.gridy = 5;
		panel.add(lab12,c);
		
		lab3 = new JLabel("Scope:");
		c.gridx = 0;
		c.gridy = 6;
		panel.add(lab3,c);
		lab13 = new JLabel(pattern.getScope().getName());
		c.gridx = 2;
		c.gridy = 6;
		panel.add(lab13,c);
		
		try{
			URL url  = new URL(pattern.getDiagram());
			BufferedImage image = ImageIO.read(url);
			BufferedImage resizedImg = Scalr.resize(image,450, 240, Scalr.OP_ANTIALIAS);
			labImage = new JLabel(new ImageIcon(resizedImg));
			c.gridx = 0;
			c.gridy = 10;
			panel.add(labImage,c);
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
		setSize(900, 650);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
