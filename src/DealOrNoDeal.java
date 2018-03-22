import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;

import java.text.DecimalFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DealOrNoDeal extends JFrame implements ActionListener {

	TitledBorder titled = new TitledBorder("Please Select Your Case");
	TitledBorder selectTitle = new TitledBorder("Select A Case");
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu();
	JMenuItem exitMenu = new JMenuItem();
	JMenuItem playAgain = new JMenuItem();
	
	DecimalFormat currency = new DecimalFormat("$#,###,###");
	
	Font buttonFont = new Font("Arial", Font.BOLD, 18);
	Font headerFont = new Font("Arial", Font.BOLD, 52);
	
	Color gold = new Color(208, 173, 73);
	
	ImageIcon[] amountsIcon = new ImageIcon[26];
	ImageIcon[] closedCases = new ImageIcon[26];
	ImageIcon[] openCases = new ImageIcon[26];
	ImageIcon[] eliminatedCase = new ImageIcon[26];
	ImageIcon bankerIcon = new ImageIcon("images/Banker.jpg");
	ImageIcon startCase = new ImageIcon("images/yourCaseStart.gif");
	ImageIcon headerIcon = new ImageIcon("images/heading3.jpg");
	ImageIcon telephoneIcon = new ImageIcon("images/blackTelephone.gif");
	ImageIcon yourIcon;
	
	JLabel header = new JLabel();
	JLabel openedCase = new JLabel();
	JLabel yourCase = new JLabel("Your Case");
	JLabel selectedLabel = new JLabel("Cases to Open");
	JLabel telephoneLabel = new JLabel("The Banker IS MAKING AN OFFER");
	JLabel orLabel = new JLabel("OR");
	JLabel yourCaseRevealed = new JLabel();
	JLabel gameOver = new JLabel("Thank you for playing \"Deal or No Deal\"");
	JLabel finalLabel = new JLabel("Do you want to trade your case for the final case?");
	JLabel[] amountLabels = new JLabel[26];
	
	JButton dealButton = new JButton(new ImageIcon("images/Deal.jpg"));
	JButton noDealButton = new JButton(new ImageIcon("images/NoDeal.jpg"));
	JButton yesButton = new JButton("Yes");
	JButton noButton = new JButton("No");
	JButton[] cases = new JButton[26];
	
	ArrayList<Double> shuffledAmounts = new ArrayList<Double>();
	ArrayList<Double> unshuffledAmounts = new ArrayList<Double>();
	
	JPanel topPanel = new JPanel();
	JPanel[] amountsPanel = new JPanel[2];
	JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
	JPanel[] casePanel = new JPanel[4];
	JPanel casesPanel = new JPanel(new GridLayout(6, 1, 0, 10));
	
	int round, count, remainingCases, yourCaseNumber, match, result, disabled;
	
	final double TOTALVALUES = 3418416.01;
	
	double yourCaseAmount, eliminatedValues, bankerOffer, average, winningAmount;
	
	
	public static void main(String[] args) {
		DealOrNoDeal frame = new DealOrNoDeal();
		frame.setTitle("Deal Or No Deal");
		frame.setSize(1300,900);
		frame.setVisible(true);
		
	}
	
	
	
	public DealOrNoDeal() {
		UIManager.put("Label.font", buttonFont);
		
		titled.setTitleFont(buttonFont);
		titled.setTitleColor(Color.WHITE);
		titled.setTitleJustification(TitledBorder.CENTER);
		
		selectTitle.setTitleFont(buttonFont);
		selectTitle.setTitleColor(Color.WHITE);
		selectTitle.setTitleJustification(TitledBorder.CENTER);
		
		createMenu();
		createArrayList();
		createLabels();
		createButtons();
		createPanels();
		
		add(amountsPanel[0], BorderLayout.WEST);
		add(amountsPanel[1], BorderLayout.EAST);
		add(casesPanel, BorderLayout.CENTER);
	}
	
	public void createArrayList() {
		shuffledAmounts.add(.01);
		shuffledAmounts.add((double)1);
		shuffledAmounts.add((double)5);
		shuffledAmounts.add((double)10);
		shuffledAmounts.add((double)25);
		shuffledAmounts.add((double)50);
		shuffledAmounts.add((double)75);
		shuffledAmounts.add((double)100);
		shuffledAmounts.add((double)200);
		shuffledAmounts.add((double)300);
		shuffledAmounts.add((double)400);
		shuffledAmounts.add((double)500);
		shuffledAmounts.add((double)750);
		shuffledAmounts.add((double)1000);
		shuffledAmounts.add((double)5000);
		shuffledAmounts.add((double)10000);
		shuffledAmounts.add((double)25000);
		shuffledAmounts.add((double)50000);
		shuffledAmounts.add((double)75000);
		shuffledAmounts.add((double)100000);
		shuffledAmounts.add((double)200000);
		shuffledAmounts.add((double)300000);
		shuffledAmounts.add((double)400000);
		shuffledAmounts.add((double)500000);
		shuffledAmounts.add((double)750000);
		shuffledAmounts.add((double)1000000);
		
		Collections.shuffle(shuffledAmounts);
		
		File file = new File("ShuffledNumbersFile.txt");
		
		StringBuffer strContent = new StringBuffer("");
		
		try{
			Formatter output = new Formatter(new FileOutputStream(file, true));
			for(int i = 0; i < shuffledAmounts.size(); i++) {
				output.format("$%,.2f%n", shuffledAmounts.get(i));
			}
			output.close();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error creating file");
		}
		
		unshuffledAmounts.add(.01);
		unshuffledAmounts.add((double)1);
		unshuffledAmounts.add((double)5);
		unshuffledAmounts.add((double)10);
		unshuffledAmounts.add((double)25);
		unshuffledAmounts.add((double)50);
		unshuffledAmounts.add((double)75);
		unshuffledAmounts.add((double)100);
		unshuffledAmounts.add((double)200);
		unshuffledAmounts.add((double)300);
		unshuffledAmounts.add((double)400);
		unshuffledAmounts.add((double)500);
		unshuffledAmounts.add((double)750);
		unshuffledAmounts.add((double)1000);
		unshuffledAmounts.add((double)5000);
		unshuffledAmounts.add((double)10000);
		unshuffledAmounts.add((double)25000);
		unshuffledAmounts.add((double)50000);
		unshuffledAmounts.add((double)75000);
		unshuffledAmounts.add((double)100000);
		unshuffledAmounts.add((double)200000);
		unshuffledAmounts.add((double)300000);
		unshuffledAmounts.add((double)400000);
		unshuffledAmounts.add((double)500000);
		unshuffledAmounts.add((double)750000);
		unshuffledAmounts.add((double)1000000);
	}
	
	public void createLabels() {
		header.setIcon(headerIcon);
		header.setFont(headerFont);
		header.setForeground(Color.white);
		
		yourCase.setIcon(startCase);
		yourCase.setBorder(titled);
		yourCase.setPreferredSize(new Dimension(250,100));
		yourCase.setForeground(Color.white);
		yourCase.setHorizontalAlignment(JLabel.CENTER);
		yourCase.setFont(buttonFont);
		
		for(int i = 0; i < amountsIcon.length; i++) {
			amountsIcon[i] = new ImageIcon("images/" + String.valueOf(i + 1) + ".jpg");
		}
		
		for(int i = 0; i < amountLabels.length; i++) {
			amountLabels[i] = new JLabel();
		}
		
		setAmounts();
		
		for(int i = 0; i < eliminatedCase.length; i++) {
			eliminatedCase[i] = new ImageIcon("images/eliminated" + String.valueOf(i+1) + ".jpg");
		}
		
		selectedLabel.setForeground(Color.white);
		selectedLabel.setBorder(selectTitle);
		selectedLabel.setFont(buttonFont);
		selectedLabel.setPreferredSize(new Dimension(200,100));
		
		telephoneLabel.setFont(buttonFont);
		
		orLabel.setForeground(Color.white);
		orLabel.setFont(buttonFont);
		
		gameOver.setFont(new Font("Arial", Font.BOLD, 38));
		gameOver.setForeground(Color.white);
		
		finalLabel.setFont(buttonFont);
		finalLabel.setForeground(Color.white);
		finalLabel.setIcon(yourIcon);
	}
	
	public void createButtons() {
		for( int i = 0; i < closedCases.length; i++) {
			closedCases[i] = new ImageIcon("images/closecase" + String.valueOf(i+1) + ".gif");
			openCases[i] = new ImageIcon("images/amountcase" + String.valueOf(i+1) + ".gif");
		}
		
		for( int i = 0; i < cases.length; i++) {
			cases[i] = new JButton("", closedCases[i]);
			cases[i].addActionListener(this);
		}
		
		dealButton.setPreferredSize(new Dimension(109,37));
		dealButton.addActionListener(this);
		
		noDealButton.setPreferredSize(new Dimension(150,37));
		noDealButton.addActionListener(this);
		
		yesButton.setPreferredSize(new Dimension(109,37));
		yesButton.setFont(buttonFont);
		yesButton.addActionListener(this);
		
		noButton.setPreferredSize(new Dimension(109,37));
		noButton.setFont(buttonFont);
		noButton.addActionListener(this);
	}
	
	public void createPanels() {
		lowerPanel.add(yourCase);
		lowerPanel.add(selectedLabel);
		lowerPanel.add(dealButton);
		lowerPanel.add(orLabel);
		lowerPanel.add(noDealButton);
		
		lowerPanel.setBackground(Color.black);
		dealButton.setEnabled(false);
		noDealButton.setEnabled(false);
		
		for(int i = 0; i < amountsPanel.length; i++) {
			amountsPanel[i] = new JPanel(new GridLayout(13,1));
			amountsPanel[i].setBackground(Color.black);
		}
		
		for(int i = 0; i < casePanel.length; i++) {
			casePanel[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
			casePanel[i].setBackground(gold);
		}
		
		for(int i = 0; i < 13; i++) {
			amountsPanel[0].add(amountLabels[i]);
		}
		
		for(int i = 13; i < amountLabels.length; i++) {
			amountsPanel[1].add(amountLabels[i]);
		}
		
		for(int i = 20; i < cases.length; i++) {
			casePanel[0].add(cases[i]);
		}
		
		for(int i = 13; i <= 19; i++) {
			casePanel[1].add(cases[i]);
		}
		
		for(int i = 6; i <= 12; i++) {
			casePanel[2].add(cases[i]);
		}
		
		for(int i = 0; i <= 5; i++) {
			casePanel[3].add(cases[i]);
		}
		
		casesPanel.setBackground(gold);
		topPanel.setBackground(Color.black);
		topPanel.add(header);
		casesPanel.add(topPanel);
		
		for(int i = 0; i < casePanel.length; i++) {
			casesPanel.add(casePanel[i]);
		}
		
		casesPanel.add(lowerPanel);
	}
	
	public void createMenu() {
		this.setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileMenu.setFont(buttonFont);
		menuBar.add(fileMenu);
		
		exitMenu = new JMenuItem("Exit");
		exitMenu.setMnemonic('E');
		exitMenu.setFont(buttonFont);
		exitMenu.addActionListener(this);
		
		playAgain = new JMenuItem("Play Again");
		playAgain.setFont(buttonFont);
		playAgain.setMnemonic('P');
		playAgain.addActionListener(this);
		
		fileMenu.add(exitMenu);
		fileMenu.add(playAgain);
	}
	
	public void setAmounts() {
		for(int i = 0; i < amountLabels.length; i++) {
			amountLabels[i].setIcon(amountsIcon[i]);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		int a = 0;
		
		if(e.getSource() == exitMenu)
			System.exit(0);
		
		if(e.getSource() == playAgain) 
			newGame();
		
		if(e.getSource() == dealButton)
			deal();
		
		if(e.getSource() == noDealButton)
			noDeal();
		
		if(round == 0) {
			for( a = 0; a < shuffledAmounts.size(); a++) {
				if(e.getSource() == cases[a]) {
					titled.setTitle("Your Briefcase");
					yourCase.setText("");
					yourCase.setIcon(closedCases[a]);
					yourIcon = (closedCases[a]);
					yourCaseNumber = a+1;
					cases[a].setVisible(false);
					yourCaseAmount = shuffledAmounts.get(a);
					round +=1;
					count = 6;
					remainingCases = shuffledAmounts.size() - (count + 1);
					selectedLabel.setText("Cases to Open " + count);
				}
			}
		} else if(round == 1) {
			checkSource(e, 6);
		} else if(round == 2) {
			selectedLabel.setText("Cases to Open " + count);
			checkSource(e, 5);
		} else if(round == 3) {
			selectedLabel.setText("Cases to Open " + count);
			checkSource(e, 4);	
		} else if(round == 4) {
			selectedLabel.setText("Cases to Open " + count);
			checkSource(e, 3);
		} else if(round ==5) {
			selectedLabel.setText("Cases to Open " + count);
			checkSource(e, 2);
		} else if(round == 6 || round == 7 || round == 8 || round == 9) {
			selectedLabel.setText("Cases to Open " + count);
			checkSource(e, 1);
		} else if(round == 10) {
			organizeLowerPanel();
			round += 1;
		} else if (round == 11) {
			finalRound(e);
		}
	}
	
	public void round(int a) {
		for(int i = 0; i < shuffledAmounts.size(); i++) {
			if(shuffledAmounts.get(a).equals(unshuffledAmounts.get(i))) {
				match = i;
				amountLabels[i].setIcon(eliminatedCase[i]);
				cases[a].setVisible(false);
				openedCase.setIcon(openCases[i]);
				eliminatedValues += unshuffledAmounts.get(i);
				JOptionPane.showMessageDialog(null, openedCase, "Selected Case", JOptionPane.PLAIN_MESSAGE);
			} 
		}
	}
	
	public void checkSource(ActionEvent f, int countDown) {
		for(int a = 0; a < shuffledAmounts.size(); a++) {
			if(f.getSource() == cases[a]) {
				if(count > 0 && count <= countDown) {
					round(a);
					count -= 1;
					selectedLabel.setText("Cases to Open " + count);
					if(count == 0) {
						banker(remainingCases, countDown);
					}
				}
			}
		}
	}
	
	public void banker(int cases, int countDown) {
		int newCount;
		String message = "The banker is making you an offer. \nIf you do not accept this offer you will have to open " + countDown + " cases";
		if(countDown > 1) {
			newCount = countDown - 1;
		} else {
			newCount = 1;
		}
		
		JOptionPane.showMessageDialog(null, message, "Call The Banker", JOptionPane.INFORMATION_MESSAGE, telephoneIcon);
		header.setIcon(bankerIcon);
		average = (TOTALVALUES - eliminatedValues) / remainingCases;
		bankerOffer = ((TOTALVALUES - eliminatedValues)/cases) * .34;
		header.setText("Banker's Offer " + String.valueOf(currency.format(bankerOffer)));
		dealButton.setEnabled(true);
		noDealButton.setEnabled(true);
	}
	
	public void finalRound(ActionEvent e) {
		if(e.getSource() == yesButton) {
			findLastCase();
			finalLabel.setText("");
			yesButton.setVisible(false);
			cases[yourCaseNumber - 1].setEnabled(false);
			noButton.setVisible(false);
			yourCase.setVisible(true);
			titled.setTitle("Switch case");
			yourCase.setIcon(closedCases[disabled]);
			JOptionPane.showMessageDialog(null, "You have chosen to switch your case\n First let's see what your case held");
			revealYourCase();
			JOptionPane.showMessageDialog(null, "Now let's see what you traded for");
			revealOtherCase();
			yourCase.setVisible(false);
			if(yourCaseAmount < shuffledAmounts.get(disabled)) {
				finalLabel.setText("Congratulations! You have made a good deal");
			} else {
				finalLabel.setText("Sorry you did not make a good deal");
			}
			
			finalLabel.setForeground(Color.black);
			JOptionPane.showMessageDialog(null,  finalLabel, "Did you make a good deal?", JOptionPane.PLAIN_MESSAGE);
			endingGame();
			lowerPanel.add(gameOver);
		}
		
		if(e.getSource() == noButton) {
				cases[yourCaseNumber -1].setEnabled(false);
				yourCase.setVisible(true);
				JOptionPane.showMessageDialog(null,  "You have chosen to keep your case\n"
						+ "First let's check the case you \n"
						+ "did not trade for.");
				findLastCase();
				revealOtherCase();
				JOptionPane.showMessageDialog(null, "Let's open your case!");
				revealYourCase();
				if(shuffledAmounts.get(disabled) < yourCaseAmount) {
					finalLabel.setText("Congratulations! You have made a good deal");
				} else {
					finalLabel.setText("Sorry you did not make a good deal");
				}
				
				finalLabel.setForeground(Color.black);
				JOptionPane.showMessageDialog(null, finalLabel, "Did you make a good deal?", JOptionPane.PLAIN_MESSAGE);
				endingGame();
				lowerPanel.add(gameOver);	
		}
	}
	
	public void organizeLowerPanel() {
		yourCase.setVisible(false);
		selectedLabel.setVisible(false);
		dealButton.setVisible(false);
		noDealButton.setVisible(false);
		orLabel.setVisible(false);
		
		header.setText("");
		header.setIcon(headerIcon);
		lowerPanel.add(finalLabel);
		lowerPanel.add(yesButton);
		lowerPanel.add(noButton);
		
		cases[yourCaseNumber - 1].setText("Your Case");
		cases[yourCaseNumber -1].setVisible(true);
	}
	
	public void findLastCase() {
		for(int i = 0; i < cases.length; i++) {
			if(cases[i].isVisible() && (i != (yourCaseNumber - 1))) {
				disabled = i;
			}
		}
	}
	
	public void revealYourCase() {
		for(int i = 0; i < unshuffledAmounts.size(); i++) {
			if(unshuffledAmounts.get(i).equals(yourCaseAmount)) {
				yourCaseNumber = i;
			}
		}
		
		yourCaseRevealed.setIcon(openCases[yourCaseNumber]);
		JOptionPane.showMessageDialog(null, yourCaseRevealed, "Your Case Amount", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void revealOtherCase() {
		for(int i = 0; i < shuffledAmounts.size(); i++) {
			if(shuffledAmounts.get(disabled).equals(unshuffledAmounts.get(i))) {
				match = i;
				openedCase.setFont(buttonFont);
				openedCase.setIcon(openCases[i]);
				winningAmount = shuffledAmounts.get(disabled);
				JOptionPane.showMessageDialog(null, openedCase, "Remaining Case Amount", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	public void endingGame() {
		lowerPanel.removeAll();
		lowerPanel.revalidate();
		lowerPanel.repaint();
	}
	
	public void deal() {
		dealButton.setEnabled(false);
		noDealButton.setEnabled(false);
		String message = "You have chosen to accept the banker's offer of " + currency.format(bankerOffer) +
				". But was it a good deal? If we Open your case we reveal";
		String message2 = "";
		
		for(int i = 0; i < unshuffledAmounts.size(); i++) {
			if(unshuffledAmounts.get(i).equals(yourCaseAmount)) {
				yourCaseNumber = i;
			} 
		}
		
		if(bankerOffer > yourCaseAmount) {
			message2 = "You made a good deal";
		} else {
			message2 = "Too bad, you made a bad deal";
		}
		
		selectedLabel.setVisible(false);
		dealButton.setVisible(false);
		noDealButton.setVisible(false);
		orLabel.setVisible(false);
		
		JOptionPane.showMessageDialog(null, message);
		
		yourCaseRevealed.setFont(buttonFont);
		yourCaseRevealed.setIcon(openCases[yourCaseNumber]);
		
		JOptionPane.showMessageDialog(null, yourCaseRevealed, "Your Case Amount", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, message2, "Was It A Good Deal?", JOptionPane.PLAIN_MESSAGE);
		yourCase.setVisible(false);
		
		endingGame();
		
		lowerPanel.add(gameOver);
	}
	
	public void noDeal() {
		dealButton.setEnabled(false);
		noDealButton.setEnabled(false);
		
		round ++;
		
		switch(round) {
			case 2:
				count = 5;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 3:
				count = 4;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 4:
				count = 3;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 5:
				count = 2;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 6:
				count = 1;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 7:
				count = 1;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 8:
				count = 1;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
			case 9:
				count = 1;
				header.setIcon(headerIcon);
				header.setText("");
				remainingCases -= count;
				break;
		}
	}
	
	public void resetValues() {
		yourCaseAmount = 0;
		eliminatedValues = 0;
		bankerOffer = 0;
		average = 0;
		winningAmount = 0;
		round = 0;
		count = 0; 
		remainingCases = 0;
		yourCaseNumber = 0;
		match = 0;
		result = 0;
		disabled = 0;
	}
	
	public void newGame() {
		Collections.shuffle(shuffledAmounts);
		
		titled.setTitle("Please Select Your Case");
		yourCase.setIcon(startCase);
		yourCase.setText("");
		
		resetLowerPanel();
		resetValues();
		setAmounts();
		resetVisibility();
	}
	
	public void resetVisibility() {
		header.setIcon(headerIcon);
		header.setText("");
		for(int i = 0; i < cases.length; i++) {
			cases[i].setVisible(true);
			cases[i].setEnabled(true);
			cases[i].setText("");
		}
		
		yourCase.setVisible(true);
		selectedLabel.setVisible(true);
		dealButton.setVisible(true);
		noDealButton.setVisible(true);
		orLabel.setVisible(true);
		finalLabel.setVisible(true);
		finalLabel.setForeground(Color.white);
		finalLabel.setText("Do you want to trade your case for the final case?");
		
		yesButton.setVisible(true);
		noButton.setVisible(true);
	}
	
	public void resetLowerPanel() {
		lowerPanel.remove(gameOver);
		lowerPanel.revalidate();
		lowerPanel.repaint();
		lowerPanel.add(yourCase);
		lowerPanel.add(selectedLabel);
		lowerPanel.add(dealButton);
		lowerPanel.add(orLabel);
		lowerPanel.add(noDealButton);
		dealButton.setEnabled(false);
		noDealButton.setEnabled(false);
	}
}
