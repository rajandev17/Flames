package Code;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import build.BuildConfig;

/**
 * created by rajan.ks 
 */
public class Flames extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private String imagePath = "src/res/images/";
	public static int Strength = 0;
	private JTextField nameField,crushField;
	private JLabel strengthLabel,relationLabel;
	private JProgressBar strengthProgress;
	private JButton calculateButton;
	public static String nameValue;
	public static String crushValue;
	
	public Flames(){
		mainFrame = new JFrame("Flames");
		mainFrame.setSize(300, 245);
		initComponents();
		initListeners();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setBackground(Color.white);
		mainFrame.setVisible(true);
	}
	
	private void initComponents() {
		JPanel mainPanel = new JPanel(null);
		mainPanel.setBackground(Color.white);

		ImageIcon image = new ImageIcon(imagePath + "icon.jpg");
		JLabel imageLabel = new JLabel("",image, JLabel.CENTER);
		imageLabel.setSize(50, 50);
		imageLabel.setLocation(125, 10);
		mainPanel.add(imageLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(40, 70, 50, 20);
		mainPanel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(80, 70, 180, 22);
		mainPanel.add(nameField);
		
		JLabel crushLabel = new JLabel("Crush");
		crushLabel.setBounds(40, 100, 50, 20);
		mainPanel.add(crushLabel);
		
		crushField = new JTextField();
		crushField.setBounds(80, 100, 180, 22);
		mainPanel.add(crushField);
		
		calculateButton = new JButton("Calculate");
		calculateButton.setBounds(100,130,100,25);
		mainPanel.add(calculateButton);
		

		relationLabel = new JLabel("0%",JLabel.CENTER);
		relationLabel.setBounds(10,160,280,20);
		relationLabel.setVisible(false);
		mainPanel.add(relationLabel);
		
		strengthLabel = new JLabel("0%",JLabel.CENTER);
		strengthLabel.setBounds(30,190,240,20);
		strengthLabel.setVisible(false);
		mainPanel.add(strengthLabel);
		
		strengthProgress = new JProgressBar();
		strengthProgress.setBounds(0,205,300,30);
		strengthProgress.setMinimum(0);
		strengthProgress.setMaximum(100);
		mainPanel.add(strengthProgress);
		
		mainFrame.setLayout(null);
		mainPanel.setBounds(0,0,300,245);
		mainFrame.add(mainPanel);
	}
	
	private void initListeners(){
		calculateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(areFieldsNotValid()){
					strengthLabel.setVisible(true);
					strengthLabel.setText("Please enter names");
					return;
				}
				Strength = getStrengthBasedOnNames();
				strengthLabel.setVisible(true);
				relationLabel.setVisible(true);
				String relationShip = getRelationShipBasedOnNames();
				relationLabel.setText(relationShip);
				
				if(BuildConfig.isDefaultVersion()){
					int newVersion = BuildConfig.DEBUG_VERSION;
					BuildConfig.UseDebbugerVersion(newVersion);
				}
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						for(int i=0 ; i <= Strength; i++){
							try {
								Thread.sleep(80);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							strengthLabel.setText("Relationship Strength : "+i+"%");
							strengthProgress.setValue(i);
						}
					}
				}).start();
			}
		});
	}
	
	private String getRelationShipBasedOnNames(){
		StringBuffer relationshipStatus = new StringBuffer(Capitalize(nameValue)+" and "+Capitalize(crushValue));
		char[] nameArray = nameValue.toCharArray();
		char[] crushArray = crushValue.toCharArray();
		int nameLength = nameArray.length , crushLength = crushArray.length;
		int common = 0;
			for(int i = 0; i < nameLength ; i++) {
				for(int j = 0; j < crushLength ; j++) {
					if(nameArray[i] == crushArray[j]){
						common++;
						crushArray[j] = '\t';
						break;
					}
				}
			}
			int unMatchedLettersCount = (nameLength + crushLength) - 2 * common;
			int key = getPositionBasedonCount(unMatchedLettersCount);
			switch(key) {
				case 1	: relationshipStatus.append(" are Good Friends");
								break;
				case 2	: relationshipStatus.append(" love each other <3");
								break;
				case 16 : relationshipStatus.append(" have lot of Affection");
								break;
				case 4	: relationshipStatus.append(" are going to Marry");
								break;
				case 5	: relationshipStatus.append(" hate each other");
								break;
				case 6  : relationshipStatus.append(" are sibblings");
								break;
				default : relationshipStatus.append(" are made for each other");
			}
			
			if(BuildConfig.isDefaultVersion()){
				return BuildConfig.GetDebuggerVersion();
			}
		
		return relationshipStatus.toString();
	}
	
	public static String Capitalize(String name){
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	private int getPositionBasedonCount(int unMatchedLettersCount) {
		int finalPosition = 0;
		char flames[]={'f','l','a','m','e','s'};
		if(unMatchedLettersCount <= 6) {
				switch(unMatchedLettersCount)
				 	{
						case 3:
						case 5:finalPosition = 1;break;
						case 2:
						case 4:finalPosition = 5;break;
						case 6:finalPosition = 4;break;
						case 1:finalPosition = 6;break;
				 	}
		}else {
			char[] swap=new char[100];
			char store[]=new char[100];
			int i = 6;
			for(int j = 0; j < 5; j++, i--) {
				int del = unMatchedLettersCount % i;
				 if(del == 0)
				     del = i;
				 for(int f = 0;f < i; f++, ++del)
				 {
				     if(del >= i)
				     {
				         del = 0;
				     }
				     store[f] = flames[del]; 
				 }
				 for (int h=0;h<i;h++)
					{
						swap[h] = store[h];
						store[h] = flames[h];
						flames[h] = swap[h];
					}
			 }
			switch(flames[0])
				{
					case 'f':finalPosition = 1; break;
					case 'l':finalPosition = 2; break;
					case 'a':finalPosition = 3; break;
					case 'm':finalPosition = 4; break;
					case 'e':finalPosition = 5; break;
					case 's':finalPosition = 6; break;
				}
		 }  
		return finalPosition;
	}

	private int getStrengthBasedOnNames() {
		nameValue = nameField.getText();
		crushValue = crushField.getText();
		int common = 0;
		for(int i=0;i< nameValue.length();i++){  
		    for(int j=0;j< crushValue.length();j++){  
		        if(nameValue.charAt(i) == crushValue.charAt(j)){  
		            common++;  
		            break;
		        }  
		    }  
		}  
		int strength = 0;
		switch(common){
		case 0 : strength = 50;  break;
		case 1 : strength = 60;  break;
		case 2 : strength = 70;  break;
		case 3 : strength = 80;  break;
		default : strength = 90; break;
 		}
		strength += new Random().nextInt(10);
		return strength;
	}

	private boolean areFieldsNotValid() {
		if(nameField.getText() != null && nameField.getText().trim().length() > 0
		&& crushField.getText() != null && crushField.getText().trim().length() > 0)
			return false;
		return true;
	}

	public static void main(String[] args){
		new Flames();
	}

}
