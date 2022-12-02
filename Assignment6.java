//Dalton Jenkins, Jordan Wampler, Noah Sturgill, Nickolas Ryan
//CSC 2220 group assignment/assignment 6
//Combining our GUI with the command line baseball game we made in assignment 1
//12-2-22

//package Baseball;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment6 {
	//variable declarations
	private JFrame frame;
	private int frameNum = 0;
	private int awayPlayerNum = 0;
	private int homePlayerNum = 0;
	private int homeNumRuns = 0;
	private int awayNumRuns = 0;
	private int min = 1;
	private int max = 6;
	private int numOuts = 0;
	private Boolean base1 = false;
	private Boolean base2 = false;
	private Boolean base3 = false;
	private JTextField txtBase1;
	private JTextField txtBase2;
	private JTextField txtBase3;
	private JTextField txtHome;
	
	//Make JLables
	JLabel homeTotalHits = new JLabel("");
	JLabel awayTotalHits = new JLabel("");
	JLabel homeTotalWalks = new JLabel("");
	JLabel awayTotalWalks = new JLabel("");
	JLabel homeTotalRuns = new JLabel("");
	JLabel awayTotalRuns = new JLabel("");
	JLabel outs = new JLabel("");
	JLabel playResultLabel = new JLabel(" ");
	JLabel currentPlayerLabel = new JLabel(" ");
	JLabel playerAtBatsLabel = new JLabel("");
	JLabel playerHitsLabel = new JLabel("");
	JLabel playerWalksLabel = new JLabel("");
	JLabel playerDoublesLabel = new JLabel("");
	JLabel playerTriplesLabel = new JLabel("");
	JLabel playerHomersLabel = new JLabel("");
	//Make JButtons
	JButton AwayNextButton = new JButton("Next");
	JButton HomeNextButton = new JButton("Next");
	JButton startGameButton = new JButton("Start"); //create start and action listener for start
	
	//Make ArrayLists
	ArrayList<JLabel> homePlayers = new ArrayList<JLabel>();//create homePlayers arraylist for homePlayers labels
	ArrayList<JPanel> homePlayersPanels = new ArrayList<JPanel>();//create arraylist homePlayersPanels home players
	ArrayList<JLabel> awayPlayers = new ArrayList<JLabel>();//create awayPlayers arraylist for awayPlayers labels
	ArrayList<JPanel> awayPlayersPanels = new ArrayList<JPanel>();//create arraylist awayPlayersPanels away players
	ArrayList<JLabel> inningsLabels = new ArrayList<JLabel>();  //create arraylist inningslabels for labels of innings
	ArrayList<JPanel> innings = new ArrayList<JPanel>(); //create arraylist innings with innings scorepanels
	//home players' stats arrayLists
	ArrayList<Integer> homeAtBats = new ArrayList<Integer>();
	ArrayList<Integer> homeHits = new ArrayList<Integer>();
	ArrayList<Integer> homeWalks = new ArrayList<Integer>();
	ArrayList<Integer> homeDoubles = new ArrayList<Integer>();
	ArrayList<Integer> homeTriples = new ArrayList<Integer>();
	ArrayList<Integer> homeHomers = new ArrayList<Integer>();
	//away players' stats arrayLists
	ArrayList<Integer> awayAtBats = new ArrayList<Integer>();
	ArrayList<Integer> awayHits = new ArrayList<Integer>();
	ArrayList<Integer> awayWalks = new ArrayList<Integer>();
	ArrayList<Integer> awayDoubles = new ArrayList<Integer>();
	ArrayList<Integer> awayTriples = new ArrayList<Integer>();
	ArrayList<Integer> awayHomers = new ArrayList<Integer>();
	//Home players cumulative stats arrayLists
	ArrayList<Integer> cumulativeHomeAtBats = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeHomeHits = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeHomeWalks = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeHomeDoubles = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeHomeTriples = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeHomeHomers = new ArrayList<Integer>();
	// Away players cumulative stats arrayLists
	ArrayList<Integer> cumulativeAwayAtBats = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeAwayHits = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeAwayWalks = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeAwayDoubles = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeAwayTriples = new ArrayList<Integer>();
	ArrayList<Integer> cumulativeAwayHomers = new ArrayList<Integer>();
	
	//Function for adding a hit when an appropriate play is generated
	private void addHit(){
		
		if(frameNum % 2 != 0) {//if home frame
			homePlayers.get(homePlayerNum).setForeground(Color.cyan);
			homePlayersPanels.get(homePlayerNum).setBackground(Color.black);//set current player to default color and increment to next player
			homePlayerNum = (homePlayerNum+1) % 9;
			homePlayers.get(homePlayerNum).setForeground(Color.black);
			homePlayersPanels.get(homePlayerNum).setBackground(Color.cyan);
			int tempHits = Integer.valueOf(homeTotalHits.getText());
			tempHits++;//add to home hits and update for home team
			homeTotalHits.setText(String.valueOf(tempHits));
			currentPlayerLabel.setText(homePlayers.get(homePlayerNum).getText());
			int tempAtBats = homeAtBats.get(homePlayerNum);
			tempAtBats++;
			homeAtBats.set(homePlayerNum, tempAtBats);
			// Update cumulative stats
			int cumTempAtBats = cumulativeHomeAtBats.get(homePlayerNum);
			cumTempAtBats++;
			cumulativeHomeAtBats.set(homePlayerNum, cumTempAtBats);
			playerAtBatsLabel.setText(String.valueOf(homeAtBats.get(homePlayerNum)));
			playerHitsLabel.setText(String.valueOf(homeHits.get(homePlayerNum)));
			playerWalksLabel.setText(String.valueOf(homeWalks.get(homePlayerNum)));
			playerDoublesLabel.setText(String.valueOf(homeDoubles.get(homePlayerNum)));
			playerTriplesLabel.setText(String.valueOf(homeTriples.get(homePlayerNum)));
			playerHomersLabel.setText(String.valueOf(homeHomers.get(homePlayerNum)));
		}
		
		else{//otherwise away frame
			awayPlayers.get(awayPlayerNum).setForeground(Color.cyan);
			awayPlayersPanels.get(awayPlayerNum).setBackground(Color.black);//set current player to default color and increment to next player
			awayPlayerNum = (awayPlayerNum+1) % 9;
			awayPlayers.get(awayPlayerNum).setForeground(Color.black);
			awayPlayersPanels.get(awayPlayerNum).setBackground(Color.cyan);
			int tempHits = Integer.valueOf(awayTotalHits.getText());
			tempHits++;//add to away hits and update for away team
			awayTotalHits.setText(String.valueOf(tempHits));
			currentPlayerLabel.setText(awayPlayers.get(awayPlayerNum).getText());
			int tempAtBats = awayAtBats.get(awayPlayerNum);
			tempAtBats++;
			awayAtBats.set(awayPlayerNum, tempAtBats);
			// Update cumulative stats
			int cumTempAtBats = cumulativeAwayAtBats.get(awayPlayerNum);
			cumTempAtBats++;
			if (cumTempAtBats < tempAtBats)
				cumTempAtBats = tempAtBats;
			cumulativeAwayAtBats.set(awayPlayerNum, cumTempAtBats);
			currentPlayerLabel.setText(awayPlayers.get(awayPlayerNum).getText());
			playerAtBatsLabel.setText(String.valueOf(awayAtBats.get(awayPlayerNum)));
			playerHitsLabel.setText(String.valueOf(awayHits.get(awayPlayerNum)));
			playerWalksLabel.setText(String.valueOf(awayWalks.get(awayPlayerNum)));
			playerDoublesLabel.setText(String.valueOf(awayDoubles.get(awayPlayerNum)));
			playerTriplesLabel.setText(String.valueOf(awayTriples.get(awayPlayerNum)));
			playerHomersLabel.setText(String.valueOf(awayHomers.get(awayPlayerNum)));
			
		}
	}
	
	//Function for adding a walk when an appropriate play is generated
	private void addWalk(){
		
		if(frameNum % 2 != 0) {//if home inning
			homePlayers.get(homePlayerNum).setForeground(Color.cyan);
			homePlayersPanels.get(homePlayerNum).setBackground(Color.black);//set current player to default color and increment to next player
			homePlayerNum = (homePlayerNum + 1 ) % 9;
			homePlayers.get(homePlayerNum).setForeground(Color.black);
			homePlayersPanels.get(homePlayerNum).setBackground(Color.cyan);
			int tempWalks = Integer.valueOf(homeTotalWalks.getText());
			tempWalks++;//add to home walks and update for home team
			homeTotalWalks.setText(String.valueOf(tempWalks));
			currentPlayerLabel.setText(homePlayers.get(homePlayerNum).getText());
			int tempAtBats = homeAtBats.get(homePlayerNum);
			tempAtBats++;
			int cumTempAtBats = cumulativeHomeAtBats.get(homePlayerNum);
			cumTempAtBats++;
			homeAtBats.set(homePlayerNum, tempAtBats);
			cumulativeHomeAtBats.set(homePlayerNum, cumTempAtBats); // update cumulative stats
			playerAtBatsLabel.setText(String.valueOf(homeAtBats.get(homePlayerNum)));
			playerHitsLabel.setText(String.valueOf(homeHits.get(homePlayerNum)));
			playerWalksLabel.setText(String.valueOf(homeWalks.get(homePlayerNum)));
			playerDoublesLabel.setText(String.valueOf(homeDoubles.get(homePlayerNum)));
			playerTriplesLabel.setText(String.valueOf(homeTriples.get(homePlayerNum)));
			playerHomersLabel.setText(String.valueOf(homeHomers.get(homePlayerNum)));
		}
		else
		{//otherwise away inning
			awayPlayers.get(awayPlayerNum).setForeground(Color.cyan);
			awayPlayersPanels.get(awayPlayerNum).setBackground(Color.black);//set current player to default color and increment to next player
			awayPlayerNum = (awayPlayerNum+1) % 9;
			awayPlayers.get(awayPlayerNum).setForeground(Color.black);
			awayPlayersPanels.get(awayPlayerNum).setBackground(Color.cyan);
			int tempWalks = Integer.valueOf(awayTotalWalks.getText());
			tempWalks++;//add to away walks and update for away team
			awayTotalWalks.setText(String.valueOf(tempWalks));
			int tempAtBats = awayAtBats.get(awayPlayerNum);
			tempAtBats++;
			int cumTempAtBats = cumulativeAwayAtBats.get(awayPlayerNum);
			cumTempAtBats++;
			awayAtBats.set(awayPlayerNum, tempAtBats);
			cumulativeAwayAtBats.set(awayPlayerNum, cumTempAtBats); // Update cumulative stats
			currentPlayerLabel.setText(awayPlayers.get(awayPlayerNum).getText());
			playerAtBatsLabel.setText(String.valueOf(awayAtBats.get(awayPlayerNum)));
			playerHitsLabel.setText(String.valueOf(awayHits.get(awayPlayerNum)));
			playerWalksLabel.setText(String.valueOf(awayWalks.get(awayPlayerNum)));
			playerDoublesLabel.setText(String.valueOf(awayDoubles.get(awayPlayerNum)));
			playerTriplesLabel.setText(String.valueOf(awayTriples.get(awayPlayerNum)));
			playerHomersLabel.setText(String.valueOf(awayHomers.get(awayPlayerNum)));
		}
	}
	
	//Function for adding a run when an appropriate play is generated
	private void addRun(){
		
		if(frameNum % 2 != 0) {// if home half-innning
			try {//try catch for adding first run
			int runsInInning = Integer.valueOf(inningsLabels.get(frameNum).getText());//increment runs in half-inning 
			runsInInning++;
			inningsLabels.get(frameNum).setText(String.valueOf(runsInInning));
			}
			catch(Exception E){
				inningsLabels.get(frameNum).setText("1");
				inningsLabels.get(frameNum).setForeground(Color.black);//if first run set runs to one and text color to black
			}
			homeNumRuns++;//increment and update home total runs
			homeTotalRuns.setText(String.valueOf(homeNumRuns));
		}
		else
		{//if away half-inning
			try {//try catch for adding first run
				int runsInInning = Integer.valueOf(inningsLabels.get(frameNum).getText());//increment runs in half-inning
				runsInInning++;
				inningsLabels.get(frameNum).setText(String.valueOf(runsInInning));
				}
				catch(Exception E){
					inningsLabels.get(frameNum).setText("1");
					inningsLabels.get(frameNum).setForeground(Color.black);//if first run set runs to one and text color to black
				}
			awayNumRuns++;
			awayTotalRuns.setText(String.valueOf(awayNumRuns));//increment away total runs
		}

	}

	private void writeToFile(String Path){
		String FilePath = Path;
		try{
			File cumulativeFile = new File(FilePath);
			if (!cumulativeFile.exists()){
				cumulativeFile.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter((new FileWriter(FilePath)));
			bw.write(cumulativeAwayAtBats.toString());
			bw.newLine();
			bw.write(cumulativeAwayHits.toString());
			bw.newLine();
			bw.write(cumulativeAwayWalks.toString());
			bw.newLine();
			bw.write(cumulativeAwayDoubles.toString());
			bw.newLine();
			bw.write(cumulativeAwayTriples.toString());
			bw.newLine();
			bw.write(cumulativeAwayHomers.toString());
			bw.newLine();
			bw.write(cumulativeHomeAtBats.toString());
			bw.newLine();
			bw.write(cumulativeHomeHits.toString());
			bw.newLine();
			bw.write(cumulativeHomeWalks.toString());
			bw.newLine();
			bw.write(cumulativeHomeDoubles.toString());
			bw.newLine();
			bw.write(cumulativeHomeTriples.toString());
			bw.newLine();
			bw.write(cumulativeHomeHomers.toString());
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		  }
	}
	
	//Function for adding an out when an appropriate play is generated
	private void addOut(){
		int tempOuts = Integer.valueOf(outs.getText());
		tempOuts++;//get value of outs and then increment value
		if (tempOuts == 3) {//if outs is three
			frameNum++;//increment inning
			if (frameNum==18) {//if game is over
				outs.setText("0");//set outs to zero
				if(inningsLabels.get((frameNum-1)).getText() == ""){
					inningsLabels.get((frameNum-1)).setText("0");//if no runs scored in inning set value to zero
				}
				homePlayers.get(homePlayerNum).setForeground(Color.cyan);//set colors for last player back to default
				homePlayersPanels.get(homePlayerNum).setBackground(Color.black);
				awayPlayersPanels.get(awayPlayerNum).setBackground(Color.black);
				awayPlayers.get(awayPlayerNum).setForeground(Color.cyan);
				innings.get((frameNum-1)).setBackground(Color.black);
				inningsLabels.get((frameNum-1)).setForeground(Color.cyan);//set last frame to default color
				currentPlayerLabel.setText(null);
				playResultLabel.setText(playResultLabel.getText()+", End of game");
				writeToFile("F:\\Repositories\\Java-Baseball\\gameStats.txt"); // Change later
				
				playerAtBatsLabel.setText("");
				playerHitsLabel.setText("");
				playerWalksLabel.setText("");
				playerDoublesLabel.setText("");
				playerTriplesLabel.setText("");
				playerHomersLabel.setText("");
			}
			else if(frameNum % 2 != 0) {//if inning not over and away team
				playResultLabel.setText(playResultLabel.getText()+", End of frame");//Prints playResult to gui
				innings.get((frameNum-1)).setBackground(Color.black);//set last frame back to default
				inningsLabels.get((frameNum-1)).setForeground(Color.cyan);
				if(inningsLabels.get((frameNum-1)).getText() == ""){
					inningsLabels.get((frameNum-1)).setText("0");//if no runs set runs to zero
				}
				
				outs.setText("0");//set outs to zero for next inning
				base1 = false;
				base2 = false;
				base3 = false;
				awayPlayersPanels.get(awayPlayerNum).setBackground(Color.black);
				awayPlayers.get(awayPlayerNum).setForeground(Color.cyan);//set current away player to default and increment
				awayPlayerNum = (awayPlayerNum+1) % 9;
				homePlayersPanels.get(homePlayerNum).setBackground(Color.cyan);
				homePlayers.get(homePlayerNum).setForeground(Color.black);//set active away player
				currentPlayerLabel.setText(homePlayers.get(homePlayerNum).getText());
				innings.get(frameNum).setBackground(Color.cyan);
				inningsLabels.get((frameNum)).setForeground(Color.black);
				
				
				int tempAtBats = homeAtBats.get(homePlayerNum);
				tempAtBats++;
				int cumTempAtBats = cumulativeHomeAtBats.get(homePlayerNum);
				cumTempAtBats++;
				homeAtBats.set(homePlayerNum,tempAtBats);
				cumulativeHomeAtBats.set(homePlayerNum, cumTempAtBats);
				playerAtBatsLabel.setText(String.valueOf(homeAtBats.get(homePlayerNum)));
				playerHitsLabel.setText(String.valueOf(homeHits.get(homePlayerNum)));
				playerWalksLabel.setText(String.valueOf(homeWalks.get(homePlayerNum)));
				playerDoublesLabel.setText(String.valueOf(homeDoubles.get(homePlayerNum)));
				playerTriplesLabel.setText(String.valueOf(homeTriples.get(homePlayerNum)));
				playerHomersLabel.setText(String.valueOf(homeHomers.get(homePlayerNum)));
			}
			else {
				if(inningsLabels.get((frameNum-1)).getText() == ""){
					inningsLabels.get((frameNum-1)).setText("0");//if no runs scored in inning set value to zero
				}
				playResultLabel.setText(playResultLabel.getText()+", End of inning");//Prints playResult to gui
				outs.setText("0");//set outs to zero
				base1 = false;
				base2 = false;
				base3 = false;
				innings.get((frameNum-1)).setBackground(Color.black);//set inning back to default
				inningsLabels.get((frameNum-1)).setForeground(Color.cyan);
				homePlayersPanels.get(homePlayerNum).setBackground(Color.black);
				homePlayers.get(homePlayerNum).setForeground(Color.cyan);//set home player back to default and increment
				homePlayerNum = (homePlayerNum+1) % 9;
				awayPlayersPanels.get(awayPlayerNum).setBackground(Color.cyan);
				awayPlayers.get(awayPlayerNum).setForeground(Color.black);//set active away player and inning
				currentPlayerLabel.setText(awayPlayers.get(awayPlayerNum).getText());
				innings.get(frameNum).setBackground(Color.cyan);
				inningsLabels.get((frameNum)).setForeground(Color.black);
				
				int tempAtBats = awayAtBats.get(awayPlayerNum);
				tempAtBats++;
				int cumTempAtBats = cumulativeAwayAtBats.get(awayPlayerNum);
				cumTempAtBats++;
				awayAtBats.set(awayPlayerNum,tempAtBats);
				cumulativeAwayAtBats.set(awayPlayerNum,cumTempAtBats);
				playerAtBatsLabel.setText(String.valueOf(awayAtBats.get(awayPlayerNum)));
				playerHitsLabel.setText(String.valueOf(awayHits.get(awayPlayerNum)));
				playerWalksLabel.setText(String.valueOf(awayWalks.get(awayPlayerNum)));
				playerDoublesLabel.setText(String.valueOf(awayDoubles.get(awayPlayerNum)));
				playerTriplesLabel.setText(String.valueOf(awayTriples.get(awayPlayerNum)));
				playerHomersLabel.setText(String.valueOf(awayHomers.get(awayPlayerNum)));
			}	
		}
		else {//if adding an out and inning not over
			outs.setText(String.valueOf(tempOuts));//set outs to new value
			if (frameNum % 2 != 0) {//if home half-inning
				homePlayers.get(homePlayerNum).setForeground(Color.cyan);//set current player to default colors and increment to next
				homePlayersPanels.get(homePlayerNum).setBackground(Color.black);
				homePlayerNum = (homePlayerNum+1) % 9;
				homePlayers.get(homePlayerNum).setForeground(Color.black);
				homePlayersPanels.get(homePlayerNum).setBackground(Color.cyan);
				int tempAtBats = homeAtBats.get(homePlayerNum);
				tempAtBats++;
				int cumTempAtBats = cumulativeHomeAtBats.get(homePlayerNum);
				cumTempAtBats++;
				homeAtBats.set(homePlayerNum,tempAtBats);
				cumulativeHomeAtBats.set(homePlayerNum,cumTempAtBats); // update cumulative stats
				currentPlayerLabel.setText(homePlayers.get(homePlayerNum).getText());
				playerAtBatsLabel.setText(String.valueOf(homeAtBats.get(homePlayerNum)));
				playerHitsLabel.setText(String.valueOf(homeHits.get(homePlayerNum)));
				playerWalksLabel.setText(String.valueOf(homeWalks.get(homePlayerNum)));
				playerDoublesLabel.setText(String.valueOf(homeDoubles.get(homePlayerNum)));
				playerTriplesLabel.setText(String.valueOf(homeTriples.get(homePlayerNum)));
				playerHomersLabel.setText(String.valueOf(homeHomers.get(homePlayerNum)));
			
			}
			else {//for away half-inning
				awayPlayers.get(awayPlayerNum).setForeground(Color.cyan);//set current player to default colors and increment to next
				awayPlayersPanels.get(awayPlayerNum).setBackground(Color.black);
				awayPlayerNum = (awayPlayerNum+1) % 9;
				awayPlayers.get(awayPlayerNum).setForeground(Color.black);
				awayPlayersPanels.get(awayPlayerNum).setBackground(Color.cyan);
				currentPlayerLabel.setText(awayPlayers.get(awayPlayerNum).getText());
				int tempAtBats = awayAtBats.get(awayPlayerNum);
				tempAtBats++;
				int cumTempAtBats = cumulativeAwayAtBats.get(awayPlayerNum);
				cumTempAtBats++;
				awayAtBats.set(awayPlayerNum,tempAtBats);
				cumulativeAwayAtBats.set(awayPlayerNum,cumTempAtBats); // update cumulative stats
				playerAtBatsLabel.setText(String.valueOf(awayAtBats.get(awayPlayerNum)));
				playerHitsLabel.setText(String.valueOf(awayHits.get(awayPlayerNum)));
				playerWalksLabel.setText(String.valueOf(awayWalks.get(awayPlayerNum)));
				playerDoublesLabel.setText(String.valueOf(awayDoubles.get(awayPlayerNum)));
				playerTriplesLabel.setText(String.valueOf(awayTriples.get(awayPlayerNum)));
				playerHomersLabel.setText(String.valueOf(awayHomers.get(awayPlayerNum)));
			
			}
		}
	}
	
	//Function that generates the next play, and then updates scores/field values accordingly
	public void next() {
		
		//Generate two random ints for play generation
		int n1 = (int)Math.floor(Math.random()*(max-min+1)+min);
		int n2 = (int)Math.floor(Math.random()*(max-min+1)+min);
		
		//Track number of outs in order to correctly score double play and sacfly
		numOuts = Integer.valueOf(outs.getText());
		
		Play p = playResult(n1, n2);//Call play result to return which play the random ints represent
		playResultLabel.setText(n1+" , "+ n2 + " " + p);//Prints playResult to gui 
		
		
		//Switch statement, switch on which play has been generated, carry out correct steps for each play
		switch(p){
		
		//HOMER case
		case HOMER:
			if(frameNum % 2 == 0) {
				int tempHomers = cumulativeAwayHomers.get(awayPlayerNum);
				tempHomers++;
				awayHomers.set( awayPlayerNum, awayHomers.get(awayPlayerNum) + 1);
				cumulativeAwayHomers.set(awayPlayerNum, tempHomers); // Update cumulative stat
				playerAtBatsLabel.setText(String.valueOf(awayAtBats.get((awayPlayerNum+1)%9)));
				playerHitsLabel.setText(String.valueOf(awayHits.get((awayPlayerNum+1)%9)));
				playerWalksLabel.setText(String.valueOf(awayWalks.get((awayPlayerNum+1) % 9)));
				playerDoublesLabel.setText(String.valueOf(awayDoubles.get((awayPlayerNum + 1) % 9)));
				playerTriplesLabel.setText(String.valueOf(awayTriples.get((awayPlayerNum + 1) % 9)));
				playerHomersLabel.setText(String.valueOf(awayHomers.get((awayPlayerNum + 1) % 9)));
			}
			else {
				int tempHomers = cumulativeAwayHomers.get(awayPlayerNum);
				tempHomers++;
				homeHomers.set( homePlayerNum, homeHomers.get(homePlayerNum) + 1);
				cumulativeHomeHomers.set( homePlayerNum, tempHomers); // Update cumulative stat
				playerAtBatsLabel.setText(String.valueOf(homeAtBats.get((homePlayerNum + 1) % 9)));
				playerHitsLabel.setText(String.valueOf(homeHits.get((homePlayerNum + 1) % 9)));
				playerWalksLabel.setText(String.valueOf(homeWalks.get((homePlayerNum + 1) % 9)));
				playerDoublesLabel.setText(String.valueOf(homeDoubles.get((homePlayerNum + 1) % 9)));
				playerTriplesLabel.setText(String.valueOf(homeTriples.get((homePlayerNum + 1) % 9)));
				playerHomersLabel.setText(String.valueOf(homeHomers.get((homePlayerNum + 1) % 9)));
			}
			
			addHit(); //Always results in a hit
			
			
			//HOMER clears any bases, adds one run for batter, and one run per occupied base
			if(base3 == false && base2 == false && base1 == false) {//No bases occupied 
				
				addRun();
			}
			
			else if(base3 == true && base2 == false && base1 == false) {//Only 3rd base occupied 
				
				base3 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == false) {//Only 2nd base occupied
				
				base2 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == false && base2 == false && base1 == true) {//Only 1st base occupied 
				
				base1 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd bases occupied
				
				base3 = false;
				base2 = false;
				addRun();
				addRun();
				addRun();
			}
			
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd bases occupied
				
				base3 = false;
				base1 = false;
				addRun();
				addRun();
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd bases occupied
				
				base2 = false;
				base1 = false;
				addRun();
				addRun();
				addRun();
			}
			
			else if(base3 == true && base2 == true && base1 == true) {//Bases loaded
				
				base3 = false;
				base2 = false;
				base1 = false;
				addRun();
				addRun();
				addRun();
				addRun();
			}
		
		break;

		//SINGLE case
		case SINGLE:
			if(frameNum % 2 == 0) {
				int tempHits = awayHits.get(awayPlayerNum);
				tempHits++;
				int cumTempHits = cumulativeAwayHits.get(awayPlayerNum);
				cumTempHits++;
				awayHits.set( awayPlayerNum, tempHits);
				cumulativeAwayHits.set( awayPlayerNum, cumTempHits); // update cumulative stat
			}
			else {
				int tempHits = cumulativeHomeHits.get(homePlayerNum);
				tempHits++;
				homeHits.set( homePlayerNum, homeHits.get(homePlayerNum) + 1);
				cumulativeHomeHits.set( homePlayerNum, tempHits); // Update cumulative stat
			}
			addHit();//Always results in a hit
			
			//SINGLE shifts all bases runners by 1, if a runner is on 3rd a run will occur
			if(base3 == false && base2 == false && base1 == false) {//Bases empty
				
				base1 = true;
			}
			
			else if(base3 == true && base2 == false && base1 == false) {//3rd occupied
				
				base3 = false;
				addRun();
				base1 = true;
			}
			
			else if(base3 == false && base2 == true && base1 == false) {//2nd occupied
				
				base2 = false;
				base3 = true;
				base1 = true;
			}
			
			else if(base3 == false && base2 == false && base1 == true) {//1st occupied
				
				base2 = true;
			}
			
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd occupied
				
				base2 = false;
				base1 = true;
				addRun();
			}
			
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
				
				base3 = false;
				base2 = true;
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied 
				
				base3 = true;
			}
			
			else if(base3 == true && base2 == true && base1 == true) {//bases loaded
				
				addRun();
			}
			
		break;
		
		//DOUBLE case	
		case DOUBLE:
			
			if(frameNum % 2 == 0) {
				int tempHits = awayHits.get(awayPlayerNum);
				tempHits++;
				int cumTempHits = cumulativeAwayHits.get(awayPlayerNum);
				cumTempHits++;
				awayHits.set( awayPlayerNum, tempHits);
				cumulativeAwayHits.set( awayPlayerNum, cumTempHits); // update cumulative stat
			}
			else {
				int tempHits = cumulativeHomeHits.get(homePlayerNum);
				tempHits++;
				homeHits.set( homePlayerNum, homeHits.get(homePlayerNum) + 1);
				cumulativeHomeHits.set( homePlayerNum, tempHits); // Update cumulative stat
				
			}
			if(frameNum % 2 == 0) {
				int temp = cumulativeAwayDoubles.get(awayPlayerNum);
				temp++;
				awayDoubles.set( awayPlayerNum, awayDoubles.get(awayPlayerNum) + 1);
				cumulativeAwayDoubles.set( awayPlayerNum, temp); // Update cumulative stat
			}
			else {
				int temp = cumulativeHomeDoubles.get(awayPlayerNum);
				temp++;
				homeDoubles.set( homePlayerNum, homeDoubles.get(homePlayerNum) + 1);
				cumulativeHomeDoubles.set( homePlayerNum, temp); // Update cumulative stat
			}
			addHit();//Always results in a hit
			
			//Shifts all bases runners by 2, if a runner is on 2nd or 3rd a run will occur 
			if(base3 == false && base2 == false && base1 == false) {//bases empty
				
				base2 = true;
			}
			
			else if(base3 == true && base2 == false && base1 == false) {//3rd occupied
				
				base3 = false;
				base2 = true;
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == false) {//2nd occupied
				
				addRun();
			}
			
			else if(base3 == false && base2 == false && base1 == true) {//1st occupied
				
				base3 = true;
				base2 = true;
				base1 = false;
			}
			
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd occupied
				
				base3 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
				
				base1 = false;
				base2 = true;
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied
				
				base1 = false;
				base3 = true;
				addRun();
			}
			
			else if(base3 == true && base2 == true && base1 == true) {//bases loaded
				
				base1 = false;
				addRun();
				addRun();
			}
			
		break;	
		
		//TRIPLE case
		case TRIPLE:
			if(frameNum % 2 == 0) {
				int tempHits = awayHits.get(awayPlayerNum);
				tempHits++;
				int cumTempHits = cumulativeAwayHits.get(awayPlayerNum);
				cumTempHits++;
				awayHits.set( awayPlayerNum, tempHits);
				cumulativeAwayHits.set( awayPlayerNum, cumTempHits); // update cumulative stat
				
			}
			else {
				int temp = cumulativeHomeHits.get(homePlayerNum);
				temp++;
				homeHits.set( homePlayerNum, homeHits.get(homePlayerNum) + 1);
				cumulativeHomeHits.set( homePlayerNum, temp); // Update cumulative stat
			}
			if(frameNum % 2 == 0) {
				int temp = cumulativeAwayTriples.get(awayPlayerNum);
				temp++;
				awayTriples.set( awayPlayerNum, awayDoubles.get(awayPlayerNum) + 1);
				cumulativeAwayTriples.set( awayPlayerNum, temp); // update cumulative
			}
			else {
				int temp = cumulativeHomeTriples.get(awayPlayerNum);
				temp++;
				homeTriples.set( homePlayerNum, homeDoubles.get(homePlayerNum) + 1);
				cumulativeHomeTriples.set( homePlayerNum, temp); // update cumulative
			}
			addHit();//always results in a hit
			
			//Shifts bases runners by 3, any base occupied before play will be a run
			if(base3 == false && base2 == false && base1 == false) {//bases empty
				
				base3 = true;
			}
			
			else if(base3 == true && base2 == false && base1 == false) {//3rd occupied
				
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == false) {//2nd occupied
				
				base3 = true;
				base2 = false;
				addRun();
			}
			
			else if(base3 == false && base2 == false && base1 == true) {//1st occupied
				
				base3 = true;
				base1 = false;
				addRun();
			}
			
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd occupied
				
				base2 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
				
				base1 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied
				
				base3 = true;
				base2 = false;
				base1 = false;
				addRun();
				addRun();
			}
			
			else if(base3 == true && base2 == true && base1 == true) {//bases loaded
				
				base2 = false;
				base1 = false;
				addRun();
				addRun();
				addRun();
			}
			
		break;
		
		//POPOUT case
		case POPOUT:
			
			addOut();//Only outcome possible is adding an out 
			
		break;	
	
		//GROUNDOUT case	
		case GROUNDOUT:
			
			if(base3 == false && base2 == false && base1 == false) {//bases empty
				
				addOut();
			}
			
			else if(base3 == true && base2 == false && base1 == false) {//3rd occupied
				
				base3 = false;
				base1 = true;
				addOut();
			}
			
			else if(base3 == false && base2 == true && base1 == false) {//2nd occupied
				
				base2 = false;
				base1 = true;
				addOut();
			}
			
			else if(base3 == false && base2 == false && base1 == true) {//1st occupied 
				
				addOut();
			}
			
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd occupied
				
				base2 = false;
				base1 = true;
				addOut();
			}
			
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
				
				base3 = false;
				base2 = true;
				addOut();
			}
			
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied
				
				addOut();
			}
			
			else if(base3 == true && base2 == true && base1 == true) {//bases loaded
				
				addOut();
			}
			
		break;	
			
		//STRIKEOUT case
		case STRIKEOUT:
			
			addOut();//Only possible outcome is adding an out 
			
		break;
			
		//FLYOUT case
		case FLYOUT:
			
			addOut();//Only possible outcome is adding an out 
			
		break;
		 	
		//SACFLY case
		case SACFLY:
			
			//if less than 2 outs prior to play
			if(numOuts < 2) {
				
				if(base3 == false && base2 == false && base1 == false) {//bases empty
					
					addOut();//empty bases, treat as flyout 
				}
				
				else if(base3 == true && base2 == false && base1 == false) {//3rd occupied
					
					base3 = false;
					addRun();
					addOut();
				}
				
				else if(base3 == false && base2 == true && base1 == false) {//2nd occupied
					
					base2 = false;
					base3 = true;
					addOut();
				}
				
				else if(base3 == false && base2 == false && base1 == true) {//1st occupied
					
					base1 = false;
					base2 = true;
					addOut();
				}
				
				else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd occupied
					
					base2 = false;
					addOut();
					addRun();
				}
				
				else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
					
					base1 = false;
					base2 = true;
					base3 = false;
					addOut();
					addRun();
				}
				
				else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied
					
					base3 = true;
					base1 = false;
					addOut();
				}
				
				else if(base3 == true && base2 == true && base1 == true) {//bases loaded
					
					base1 = false;
					addOut();
					addRun();
				}
			
			
			}
			
			//else, treat as fly out 
			else {
				
				addOut();
			}
			
		break;
		
		//DOUBLEPLAY case
		case DOUBLEPLAY:
			
			if(base3 == false && base2 == false && base1 == false) {//empty bases
					
				addOut();//empty bases, treat as groundout 
			}
				
			else if(base3 == true && base2 == false && base1 == false) {//3rd occupied
					
				base3 = false;
				addOut();
				addOut();
			}
				
			else if(base3 == false && base2 == true && base1 == false) {//2nd occupied
					
				base2 = false;
				addOut();
				addOut();
			}
				
			else if(base3 == false && base2 == false && base1 == true) {//1st occupied
					
				base1 = false;
				addOut();
				addOut();
			}
				
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd occupied
					
				base2 = false;
				base3 = false;
				base1 = true;
				addOut();
				addOut();
			}
				
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
					
				base3 = false;
				addOut();
				addOut();
			}
				
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied
					
				base2 = false;
				addOut();
				addOut();
			}
				
			else if(base3 == true && base2 == true && base1 == true) {//bases loaded
					
				base3 = false;
				addOut();
				addOut();
			}
			
		break;
		
		//WALK case
		case WALK:
			
			if(frameNum % 2 == 0) {
				int temp = cumulativeAwayWalks.get(awayPlayerNum);
				temp++;
				awayWalks.set( awayPlayerNum, awayWalks.get(awayPlayerNum) + 1);
				cumulativeAwayWalks.set( awayPlayerNum, temp); // update cumulative stats
				
			}
			else {
				int temp = cumulativeHomeWalks.get(awayPlayerNum);
				temp++;
				homeWalks.set( homePlayerNum, homeWalks.get(homePlayerNum) + 1);
				cumulativeHomeWalks.set( homePlayerNum, temp); // update cumulative stats
			}
			addWalk();//Always results in adding a walk
			
			//WALK shifts all bases runners by 1, loaded bases will create a run 
			if(base3 == false && base2 == false && base1 == false) {//bases empty
				
				base1 = true;
			}
			
			else if(base3 == true && base2 == false && base1 == false) {//3rd is occupied
				
				base1 = true;
			}
			
			else if(base3 == false && base2 == true && base1 == false) {//2nd is occupied
				
				base1 = true;
			}
			
			else if(base3 == false && base2 == false && base1 == true) {//1st is occupied
				
				base2 = true;
			}
			
			else if(base3 == true && base2 == true && base1 == false) {//2nd and 3rd are occupied
				
				base1 = true;
			}
			
			else if(base3 == true && base2 == false && base1 == true) {//1st and 3rd occupied
				
				base2 = true;
			}
			
			else if(base3 == false && base2 == true && base1 == true) {//1st and 2nd occupied
				
				base3 = true;
			}
			
			else if(base3 == true && base2 == true && base1 == true) {//bases loaded
				
				addRun();
			}
			
			break;					
		}
		
		//These if/else statements make the bases change colors depending on whether or not they are true 
		if(base1 == true) {//1st base
			
			txtBase1.setBackground(Color.CYAN);
		}
		
		else {
			
			txtBase1.setBackground(Color.BLACK);
		}
		
		if(base2 == true) {//2nd base
			
			txtBase2.setBackground(Color.CYAN);				
		}
		
		else {
			
			txtBase2.setBackground(Color.BLACK);
		}
		
		if(base3 == true) {//3rd base
			
			txtBase3.setBackground(Color.CYAN);				
		}
		
		else {
			
			txtBase3.setBackground(Color.BLACK);
		}
		
		//Track number of outs in order to correctly score double play and sacfly
		numOuts = Integer.valueOf(outs.getText());
	}
	
	//Enumerated type for the play results
    enum Play{

        SINGLE, DOUBLE, TRIPLE, HOMER, WALK, FLYOUT, STRIKEOUT, 
        GROUNDOUT, SACFLY, POPOUT, DOUBLEPLAY, NONE
    };

    //Initialize rollResult
    Play rollResult = Play.NONE;
    
    //Play result functions takes the random ints as args and returns the correct play
    public static Play playResult(int n1, int n2){
        
        Play rollResult = Play.NONE;//Initialize rollResult
        int swapVariable; //Swap dice values if n1 is bigger than n2 
        
        if(n1 > n2){
            
            swapVariable = n1;
            n1 = n2;
            n2 = swapVariable;
        }
        
        	//switch statement, compares random ints to choose correct play
            switch(n1){
            
            case 1://n1 = 1

                if (n2 == 1){
                    
                    rollResult = Play.HOMER;
                }

                else if (n2 == 2){
                    
                    rollResult = Play.DOUBLE;
                }

                else if (n2 == 3){
                    
                    rollResult = Play.FLYOUT;
                }

                else if (n2 == 4){
                    
                    rollResult = Play.WALK;
                }

                else if (n2 == 5){
                    
                    rollResult = Play.POPOUT;
                }

                else if (n2 == 6){
                    
                    rollResult = Play.SINGLE;
                }
                
            break;

            case 2://n1 = 2

                if (n2 == 2){
                    
                    rollResult = Play.DOUBLEPLAY;
                }

                else if (n2 == 3){
                    
                    rollResult = Play.GROUNDOUT;
                }

                else if (n2 == 4){
                    
                    rollResult = Play.STRIKEOUT;
                }

                else if (n2 == 5){
                    
                    rollResult = Play.SINGLE;
                }

                else if (n2 == 6){
                    
                    rollResult = Play.STRIKEOUT;
                }    

            break;

            case 3://n1 = 3

                if (n2 == 3){
                    
                    rollResult = Play.WALK;
                }

                else if (n2 == 4){
                    
                    rollResult = Play.TRIPLE;
                }

                else if (n2 == 5){
                    
                    rollResult = Play.GROUNDOUT;
                }

                else if (n2 == 6){
                    
                    rollResult = Play.FLYOUT;
                }

            break;

            case 4://n1 = 4 

                if (n2 == 4){
                    
                    rollResult = Play.WALK;
                }

                else if (n2 == 5){
                    
                    rollResult = Play.POPOUT;
                }

                else if (n2 == 6){
                    
                    rollResult = Play.STRIKEOUT;
                }

            break;

            case 5://n1 = 5

                if (n2 == 5){
                    
                    rollResult = Play.DOUBLE;
                }

                else if (n2 == 6){
                    
                    rollResult = Play.SACFLY;
                }

            break;

            case 6://n1 = 6

                if (n2 == 6){
                    
                    rollResult = Play.HOMER;    
                }

            break;
        }

        return rollResult;
    }
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment6 window = new Assignment6();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Assignment6() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//start of frame creations
		frame = new JFrame();
		frame.setBounds(100, 100, 819, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel scoreboardPanel = new JPanel();
		scoreboardPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scoreboardPanel.setBackground(new Color(0, 100, 0));
		scoreboardPanel.setBounds(10, 10, 785, 70);
		frame.getContentPane().add(scoreboardPanel);
		scoreboardPanel.setLayout(null);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBackground(Color.BLACK);
		homePanel.setBounds(10, 40, 165, 20);
		scoreboardPanel.add(homePanel);
		
		JLabel homeLabel = new JLabel("Home");
		homeLabel.setForeground(Color.CYAN);
		homeLabel.setBounds(0, 0, 165, 20);
		homePanel.add(homeLabel);
		
		JPanel awayScorePanel1 = new JPanel();
		awayScorePanel1.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel1.setBackground(new Color(0, 0, 0));
		awayScorePanel1.setBounds(200, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel1);
		awayScorePanel1.setLayout(null);
		
		JLabel awayInning1 = new JLabel("");
		awayInning1.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning1.setForeground(new Color(0, 255, 255));
		awayInning1.setBounds(0, 0, 20, 20);
		awayScorePanel1.add(awayInning1);
		
		JPanel homeScorePanel1 = new JPanel();
		homeScorePanel1.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel1.setBackground(new Color(0, 0, 0));
		homeScorePanel1.setBounds(200, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel1);
		homeScorePanel1.setLayout(null);
		
		JLabel homeInning1 = new JLabel("");
		homeInning1.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning1.setForeground(new Color(0, 255, 255));
		homeInning1.setBounds(0, 0, 20, 20);
		homeScorePanel1.add(homeInning1);
		
		JPanel awayPanel = new JPanel();
		awayPanel.setLayout(null);
		awayPanel.setBackground(Color.BLACK);
		awayPanel.setBounds(10, 20, 165, 20);
		scoreboardPanel.add(awayPanel);
		
		JLabel awayLabel = new JLabel("Away");
		awayLabel.setForeground(Color.CYAN);
		awayLabel.setBounds(0, 0, 165, 20);
		awayPanel.add(awayLabel);
		
		JPanel inningOnePanel = new JPanel();
		inningOnePanel.setBackground(new Color(0, 100, 0));
		inningOnePanel.setBounds(200, 0, 20, 20);
		scoreboardPanel.add(inningOnePanel);
		inningOnePanel.setLayout(null);
		
		JLabel inningOne = new JLabel("1");
		inningOne.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningOne.setHorizontalAlignment(SwingConstants.CENTER);
		inningOne.setBounds(0, 0, 20, 20);
		inningOnePanel.add(inningOne);
		
		JPanel awayScorePanel2 = new JPanel();
		awayScorePanel2.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel2.setLayout(null);
		awayScorePanel2.setBackground(Color.BLACK);
		awayScorePanel2.setBounds(230, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel2);
		
		JLabel awayInning2 = new JLabel("");
		awayInning2.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning2.setForeground(Color.CYAN);
		awayInning2.setBounds(0, 0, 20, 20);
		awayScorePanel2.add(awayInning2);
		
		JPanel homeScorePanel2 = new JPanel();
		homeScorePanel2.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel2.setLayout(null);
		homeScorePanel2.setBackground(Color.BLACK);
		homeScorePanel2.setBounds(230, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel2);
		
		JLabel homeInning2 = new JLabel("");
		homeInning2.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning2.setForeground(Color.CYAN);
		homeInning2.setBounds(0, 0, 20, 20);
		homeScorePanel2.add(homeInning2);
		
		JPanel inningTwoPanel = new JPanel();
		inningTwoPanel.setLayout(null);
		inningTwoPanel.setBackground(new Color(0, 100, 0));
		inningTwoPanel.setBounds(230, 0, 20, 20);
		scoreboardPanel.add(inningTwoPanel);
		
		JLabel inningTwo = new JLabel("2");
		inningTwo.setHorizontalAlignment(SwingConstants.CENTER);
		inningTwo.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningTwo.setBounds(0, 0, 20, 20);
		inningTwoPanel.add(inningTwo);
		
		JPanel awayScorePanel3 = new JPanel();
		awayScorePanel3.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel3.setLayout(null);
		awayScorePanel3.setBackground(Color.BLACK);
		awayScorePanel3.setBounds(260, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel3);
		
		JLabel awayInning3 = new JLabel("");
		awayInning3.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning3.setForeground(Color.CYAN);
		awayInning3.setBounds(0, 0, 20, 20);
		awayScorePanel3.add(awayInning3);
		
		JPanel homeScorePanel3 = new JPanel();
		homeScorePanel3.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel3.setLayout(null);
		homeScorePanel3.setBackground(Color.BLACK);
		homeScorePanel3.setBounds(260, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel3);
		
		JLabel homeInning3 = new JLabel("");
		homeInning3.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning3.setForeground(Color.CYAN);
		homeInning3.setBounds(0, 0, 20, 20);
		homeScorePanel3.add(homeInning3);
		
		JPanel inningThreePanel = new JPanel();
		inningThreePanel.setLayout(null);
		inningThreePanel.setBackground(new Color(0, 100, 0));
		inningThreePanel.setBounds(260, 0, 20, 20);
		scoreboardPanel.add(inningThreePanel);
		
		JLabel inningThree = new JLabel("3");
		inningThree.setHorizontalAlignment(SwingConstants.CENTER);
		inningThree.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningThree.setBounds(0, 0, 20, 20);
		inningThreePanel.add(inningThree);
		
		JPanel awayScorePanel4 = new JPanel();
		awayScorePanel4.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel4.setLayout(null);
		awayScorePanel4.setBackground(Color.BLACK);
		awayScorePanel4.setBounds(290, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel4);
		
		JLabel awayInning4 = new JLabel("");
		awayInning4.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning4.setForeground(Color.CYAN);
		awayInning4.setBounds(0, 0, 20, 20);
		awayScorePanel4.add(awayInning4);
		
		JPanel homeScorePanel4 = new JPanel();
		homeScorePanel4.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel4.setLayout(null);
		homeScorePanel4.setBackground(Color.BLACK);
		homeScorePanel4.setBounds(290, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel4);
		
		JLabel homeInning4 = new JLabel("");
		homeInning4.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning4.setForeground(Color.CYAN);
		homeInning4.setBounds(0, 0, 20, 20);
		homeScorePanel4.add(homeInning4);
		
		JPanel inningFourPanel = new JPanel();
		inningFourPanel.setLayout(null);
		inningFourPanel.setBackground(new Color(0, 100, 0));
		inningFourPanel.setBounds(290, 0, 20, 20);
		scoreboardPanel.add(inningFourPanel);
		
		JLabel inningFour = new JLabel("4");
		inningFour.setHorizontalAlignment(SwingConstants.CENTER);
		inningFour.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningFour.setBounds(0, 0, 20, 20);
		inningFourPanel.add(inningFour);
		
		JPanel awayScorePanel5 = new JPanel();
		awayScorePanel5.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel5.setLayout(null);
		awayScorePanel5.setBackground(Color.BLACK);
		awayScorePanel5.setBounds(320, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel5);
		
		JLabel awayInning5 = new JLabel("");
		awayInning5.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning5.setForeground(Color.CYAN);
		awayInning5.setBounds(0, 0, 20, 20);
		awayScorePanel5.add(awayInning5);
		
		JPanel homeScorePanel5 = new JPanel();
		homeScorePanel5.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel5.setLayout(null);
		homeScorePanel5.setBackground(Color.BLACK);
		homeScorePanel5.setBounds(320, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel5);
		
		JLabel homeInning5 = new JLabel("");
		homeInning5.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning5.setForeground(Color.CYAN);
		homeInning5.setBounds(0, 0, 20, 20);
		homeScorePanel5.add(homeInning5);
		
		JPanel inningFivePanel = new JPanel();
		inningFivePanel.setLayout(null);
		inningFivePanel.setBackground(new Color(0, 100, 0));
		inningFivePanel.setBounds(320, 0, 20, 20);
		scoreboardPanel.add(inningFivePanel);
		
		JLabel inningFive = new JLabel("5");
		inningFive.setHorizontalAlignment(SwingConstants.CENTER);
		inningFive.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningFive.setBounds(0, 0, 20, 20);
		inningFivePanel.add(inningFive);
		
		JPanel awayScorePanel6 = new JPanel();
		awayScorePanel6.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel6.setLayout(null);
		awayScorePanel6.setBackground(Color.BLACK);
		awayScorePanel6.setBounds(350, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel6);
		
		JLabel awayInning6 = new JLabel("");
		awayInning6.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning6.setForeground(Color.CYAN);
		awayInning6.setBounds(0, 0, 20, 20);
		awayScorePanel6.add(awayInning6);
		
		JPanel homeScorePanel6 = new JPanel();
		homeScorePanel6.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel6.setLayout(null);
		homeScorePanel6.setBackground(Color.BLACK);
		homeScorePanel6.setBounds(350, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel6);
		
		JLabel homeInning6 = new JLabel("");
		homeInning6.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning6.setForeground(Color.CYAN);
		homeInning6.setBounds(0, 0, 20, 20);
		homeScorePanel6.add(homeInning6);
		
		JPanel inningSixPanel = new JPanel();
		inningSixPanel.setLayout(null);
		inningSixPanel.setBackground(new Color(0, 100, 0));
		inningSixPanel.setBounds(350, 0, 20, 20);
		scoreboardPanel.add(inningSixPanel);
		
		JLabel inningSix = new JLabel("6");
		inningSix.setHorizontalAlignment(SwingConstants.CENTER);
		inningSix.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningSix.setBounds(0, 0, 20, 20);
		inningSixPanel.add(inningSix);
		
		JPanel awayScorePanel7 = new JPanel();
		awayScorePanel7.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel7.setLayout(null);
		awayScorePanel7.setBackground(Color.BLACK);
		awayScorePanel7.setBounds(380, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel7);
		
		JLabel awayInning7 = new JLabel("");
		awayInning7.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning7.setForeground(Color.CYAN);
		awayInning7.setBounds(0, 0, 20, 20);
		awayScorePanel7.add(awayInning7);
		
		JPanel homeScorePanel7 = new JPanel();
		homeScorePanel7.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel7.setLayout(null);
		homeScorePanel7.setBackground(Color.BLACK);
		homeScorePanel7.setBounds(380, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel7);
		
		JLabel homeInning7 = new JLabel("");
		homeInning7.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning7.setForeground(Color.CYAN);
		homeInning7.setBounds(0, 0, 20, 20);
		homeScorePanel7.add(homeInning7);
		
		JPanel inningSevenPanel = new JPanel();
		inningSevenPanel.setLayout(null);
		inningSevenPanel.setBackground(new Color(0, 100, 0));
		inningSevenPanel.setBounds(380, 0, 20, 20);
		scoreboardPanel.add(inningSevenPanel);
		
		JLabel inningSeven = new JLabel("7");
		inningSeven.setHorizontalAlignment(SwingConstants.CENTER);
		inningSeven.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningSeven.setBounds(0, 0, 20, 20);
		inningSevenPanel.add(inningSeven);
		
		JPanel awayScorePanel8 = new JPanel();
		awayScorePanel8.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel8.setLayout(null);
		awayScorePanel8.setBackground(Color.BLACK);
		awayScorePanel8.setBounds(410, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel8);
		
		JLabel awayInning8 = new JLabel("");
		awayInning8.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning8.setForeground(Color.CYAN);
		awayInning8.setBounds(0, 0, 20, 20);
		awayScorePanel8.add(awayInning8);
		
		JPanel homeScorePanel8 = new JPanel();
		homeScorePanel8.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel8.setLayout(null);
		homeScorePanel8.setBackground(Color.BLACK);
		homeScorePanel8.setBounds(410, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel8);
		
		JLabel homeInning8 = new JLabel("");
		homeInning8.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning8.setForeground(Color.CYAN);
		homeInning8.setBounds(0, 0, 20, 20);
		homeScorePanel8.add(homeInning8);
		
		JPanel inningEightPanel = new JPanel();
		inningEightPanel.setLayout(null);
		inningEightPanel.setBackground(new Color(0, 100, 0));
		inningEightPanel.setBounds(410, 0, 20, 20);
		scoreboardPanel.add(inningEightPanel);
		
		JLabel inningEight = new JLabel("8");
		inningEight.setHorizontalAlignment(SwingConstants.CENTER);
		inningEight.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningEight.setBounds(0, 0, 20, 20);
		inningEightPanel.add(inningEight);
		
		JPanel awayScorePanel9 = new JPanel();
		awayScorePanel9.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayScorePanel9.setLayout(null);
		awayScorePanel9.setBackground(Color.BLACK);
		awayScorePanel9.setBounds(440, 20, 20, 20);
		scoreboardPanel.add(awayScorePanel9);
		
		JLabel awayInning9 = new JLabel("");
		awayInning9.setHorizontalAlignment(SwingConstants.CENTER);
		awayInning9.setForeground(Color.CYAN);
		awayInning9.setBounds(0, 0, 20, 20);
		awayScorePanel9.add(awayInning9);
		
		JPanel homeScorePanel9 = new JPanel();
		homeScorePanel9.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeScorePanel9.setLayout(null);
		homeScorePanel9.setBackground(Color.BLACK);
		homeScorePanel9.setBounds(440, 40, 20, 20);
		scoreboardPanel.add(homeScorePanel9);
		
		JLabel homeInning9 = new JLabel("");
		homeInning9.setHorizontalAlignment(SwingConstants.CENTER);
		homeInning9.setForeground(Color.CYAN);
		homeInning9.setBounds(0, 0, 20, 20);
		homeScorePanel9.add(homeInning9);
		
		JPanel inningNinePanel = new JPanel();
		inningNinePanel.setLayout(null);
		inningNinePanel.setBackground(new Color(0, 100, 0));
		inningNinePanel.setBounds(440, 0, 20, 20);
		scoreboardPanel.add(inningNinePanel);
		
		JLabel inningNine = new JLabel("9");
		inningNine.setHorizontalAlignment(SwingConstants.CENTER);
		inningNine.setFont(new Font("Tahoma", Font.BOLD, 12));
		inningNine.setBounds(0, 0, 20, 20);
		inningNinePanel.add(inningNine);
		
		JPanel awayTotalWalksPanel = new JPanel();
		awayTotalWalksPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayTotalWalksPanel.setLayout(null);
		awayTotalWalksPanel.setBackground(Color.BLACK);
		awayTotalWalksPanel.setBounds(568, 20, 20, 20);
		scoreboardPanel.add(awayTotalWalksPanel);
		
		awayTotalWalks.setHorizontalAlignment(SwingConstants.CENTER);
		awayTotalWalks.setForeground(Color.CYAN);
		awayTotalWalks.setBounds(0, 0, 20, 20);
		awayTotalWalksPanel.add(awayTotalWalks);
		
		JPanel homeTotalWalksPanel = new JPanel();
		homeTotalWalksPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeTotalWalksPanel.setLayout(null);
		homeTotalWalksPanel.setBackground(Color.BLACK);
		homeTotalWalksPanel.setBounds(568, 40, 20, 20);
		scoreboardPanel.add(homeTotalWalksPanel);
		
		homeTotalWalks.setHorizontalAlignment(SwingConstants.CENTER);
		homeTotalWalks.setForeground(Color.CYAN);
		homeTotalWalks.setBounds(0, 0, 20, 20);
		homeTotalWalksPanel.add(homeTotalWalks);
		
		JPanel totalWalksPanel = new JPanel();
		totalWalksPanel.setLayout(null);
		totalWalksPanel.setBackground(new Color(0, 100, 0));
		totalWalksPanel.setBounds(568, 0, 20, 20);
		scoreboardPanel.add(totalWalksPanel);
		
		JLabel totalWalks = new JLabel("BB");
		totalWalks.setHorizontalAlignment(SwingConstants.CENTER);
		totalWalks.setFont(new Font("Tahoma", Font.BOLD, 12));
		totalWalks.setBounds(0, 0, 20, 20);
		totalWalksPanel.add(totalWalks);
		
		JPanel awayTotalHitsPanel = new JPanel();
		awayTotalHitsPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayTotalHitsPanel.setLayout(null);
		awayTotalHitsPanel.setBackground(Color.BLACK);
		awayTotalHitsPanel.setBounds(538, 20, 20, 20);
		scoreboardPanel.add(awayTotalHitsPanel);
		
		awayTotalHits.setHorizontalAlignment(SwingConstants.CENTER);
		awayTotalHits.setForeground(Color.CYAN);
		awayTotalHits.setBounds(0, 0, 20, 20);
		awayTotalHitsPanel.add(awayTotalHits);
		
		JPanel homeTotalHitsPanel = new JPanel();
		homeTotalHitsPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeTotalHitsPanel.setLayout(null);
		homeTotalHitsPanel.setBackground(Color.BLACK);
		homeTotalHitsPanel.setBounds(538, 40, 20, 20);
		scoreboardPanel.add(homeTotalHitsPanel);
		
		homeTotalHits.setHorizontalAlignment(SwingConstants.CENTER);
		homeTotalHits.setForeground(Color.CYAN);
		homeTotalHits.setBounds(0, 0, 20, 20);
		homeTotalHitsPanel.add(homeTotalHits);
		
		JPanel totalHitsPanel = new JPanel();
		totalHitsPanel.setLayout(null);
		totalHitsPanel.setBackground(new Color(0, 100, 0));
		totalHitsPanel.setBounds(538, 0, 20, 20);
		scoreboardPanel.add(totalHitsPanel);
		
		JLabel totalHits = new JLabel("H");
		totalHits.setHorizontalAlignment(SwingConstants.CENTER);
		totalHits.setFont(new Font("Tahoma", Font.BOLD, 12));
		totalHits.setBounds(0, 0, 20, 20);
		totalHitsPanel.add(totalHits);
		
		JPanel awayTotalRunsPanel = new JPanel();
		awayTotalRunsPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		awayTotalRunsPanel.setLayout(null);
		awayTotalRunsPanel.setBackground(Color.BLACK);
		awayTotalRunsPanel.setBounds(508, 20, 20, 20);
		scoreboardPanel.add(awayTotalRunsPanel);
		
		awayTotalRuns.setHorizontalAlignment(SwingConstants.CENTER);
		awayTotalRuns.setForeground(Color.CYAN);
		awayTotalRuns.setBounds(0, 0, 20, 20);
		awayTotalRunsPanel.add(awayTotalRuns);
		
		JPanel homeTotalRunsPanel = new JPanel();
		homeTotalRunsPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		homeTotalRunsPanel.setLayout(null);
		homeTotalRunsPanel.setBackground(Color.BLACK);
		homeTotalRunsPanel.setBounds(508, 40, 20, 20);
		scoreboardPanel.add(homeTotalRunsPanel);
		
		homeTotalRuns.setHorizontalAlignment(SwingConstants.CENTER);
		homeTotalRuns.setForeground(Color.CYAN);
		homeTotalRuns.setBounds(0, 0, 20, 20);
		homeTotalRunsPanel.add(homeTotalRuns);
		
		JPanel totalRunsPanel = new JPanel();
		totalRunsPanel.setLayout(null);
		totalRunsPanel.setBackground(new Color(0, 100, 0));
		totalRunsPanel.setBounds(508, 0, 20, 20);
		scoreboardPanel.add(totalRunsPanel);
		
		JLabel totalRuns = new JLabel("R");
		totalRuns.setHorizontalAlignment(SwingConstants.CENTER);
		totalRuns.setFont(new Font("Tahoma", Font.BOLD, 12));
		totalRuns.setBounds(0, 0, 20, 20);
		totalRunsPanel.add(totalRuns);
		
		JPanel outsLabelPanel = new JPanel();
		outsLabelPanel.setBackground(new Color(0, 100, 0));
		outsLabelPanel.setBounds(660, 0, 40, 30);
		scoreboardPanel.add(outsLabelPanel);
		outsLabelPanel.setLayout(null);
		
		JLabel outsLabel = new JLabel("Outs");
		outsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		outsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outsLabel.setBounds(0, 0, 40, 30);
		outsLabelPanel.add(outsLabel);
		
		JPanel outsPanel = new JPanel();
		outsPanel.setLayout(null);
		outsPanel.setBackground(new Color(0, 0, 0));
		outsPanel.setBounds(660, 30, 40, 30);
		scoreboardPanel.add(outsPanel);
		
		outs.setHorizontalAlignment(SwingConstants.CENTER);
		outs.setFont(new Font("Tahoma", Font.BOLD, 12));
		outs.setBounds(0, 0, 40, 30);
		outsPanel.add(outs);
		
		JPanel awayTeamPanel = new JPanel();
		awayTeamPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		awayTeamPanel.setBackground(new Color(0, 100, 0));
		awayTeamPanel.setBounds(10, 90, 180, 392);
		frame.getContentPane().add(awayTeamPanel);
		awayTeamPanel.setLayout(null);
		
		JPanel awayPlayer1Panel = new JPanel();
		awayPlayer1Panel.setName("");
		awayPlayer1Panel.setBackground(new Color(0, 0, 0));
		awayPlayer1Panel.setBounds(10, 10, 160, 20);
		awayTeamPanel.add(awayPlayer1Panel);
		awayPlayer1Panel.setLayout(null);
		
		JLabel awayPlayer1 = new JLabel("Away Player 1");
		awayPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer1.setForeground(new Color(64, 224, 208));
		awayPlayer1.setName("");
		awayPlayer1.setBounds(0, 0, 160, 20);
		awayPlayer1Panel.add(awayPlayer1);
		
		JPanel awayPlayer2Panel = new JPanel();
		awayPlayer2Panel.setLayout(null);
		awayPlayer2Panel.setName("");
		awayPlayer2Panel.setBackground(Color.BLACK);
		awayPlayer2Panel.setBounds(10, 40, 160, 20);
		awayTeamPanel.add(awayPlayer2Panel);
		
		JLabel awayPlayer2 = new JLabel("Away Player 2");
		awayPlayer2.setName("");
		awayPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer2.setForeground(new Color(64, 224, 208));
		awayPlayer2.setBounds(0, 0, 160, 20);
		awayPlayer2Panel.add(awayPlayer2);
		
		JPanel awayPlayer3Panel = new JPanel();
		awayPlayer3Panel.setLayout(null);
		awayPlayer3Panel.setName("");
		awayPlayer3Panel.setBackground(Color.BLACK);
		awayPlayer3Panel.setBounds(10, 74, 160, 20);
		awayTeamPanel.add(awayPlayer3Panel);
		
		JLabel awayPlayer3 = new JLabel("Away Player 3");
		awayPlayer3.setName("");
		awayPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer3.setForeground(new Color(64, 224, 208));
		awayPlayer3.setBounds(0, 0, 160, 20);
		awayPlayer3Panel.add(awayPlayer3);
		
		JPanel awayPlayer4Panel = new JPanel();
		awayPlayer4Panel.setLayout(null);
		awayPlayer4Panel.setName("");
		awayPlayer4Panel.setBackground(Color.BLACK);
		awayPlayer4Panel.setBounds(10, 104, 160, 20);
		awayTeamPanel.add(awayPlayer4Panel);
		
		JLabel awayPlayer4 = new JLabel("Away Player 4");
		awayPlayer4.setName("");
		awayPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer4.setForeground(new Color(64, 224, 208));
		awayPlayer4.setBounds(0, 0, 160, 20);
		awayPlayer4Panel.add(awayPlayer4);
		
		JPanel awayPlayer5Panel = new JPanel();
		awayPlayer5Panel.setLayout(null);
		awayPlayer5Panel.setName("");
		awayPlayer5Panel.setBackground(Color.BLACK);
		awayPlayer5Panel.setBounds(10, 134, 160, 20);
		awayTeamPanel.add(awayPlayer5Panel);
		
		JLabel awayPlayer5 = new JLabel("Away Player 5");
		awayPlayer5.setName("");
		awayPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer5.setForeground(new Color(64, 224, 208));
		awayPlayer5.setBounds(0, 0, 160, 20);
		awayPlayer5Panel.add(awayPlayer5);
		
		JPanel awayPlayer6Panel = new JPanel();
		awayPlayer6Panel.setLayout(null);
		awayPlayer6Panel.setName("");
		awayPlayer6Panel.setBackground(Color.BLACK);
		awayPlayer6Panel.setBounds(10, 164, 160, 20);
		awayTeamPanel.add(awayPlayer6Panel);
		
		JLabel awayPlayer6 = new JLabel("Away Player 6");
		awayPlayer6.setName("");
		awayPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer6.setForeground(new Color(64, 224, 208));
		awayPlayer6.setBounds(0, 0, 160, 20);
		awayPlayer6Panel.add(awayPlayer6);
		
		JPanel awayPlayer7Panel = new JPanel();
		awayPlayer7Panel.setLayout(null);
		awayPlayer7Panel.setName("");
		awayPlayer7Panel.setBackground(Color.BLACK);
		awayPlayer7Panel.setBounds(10, 194, 160, 20);
		awayTeamPanel.add(awayPlayer7Panel);
		
		JLabel awayPlayer7 = new JLabel("Away Player 7");
		awayPlayer7.setName("");
		awayPlayer7.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer7.setForeground(new Color(64, 224, 208));
		awayPlayer7.setBounds(0, 0, 160, 20);
		awayPlayer7Panel.add(awayPlayer7);
		
		JPanel awayPlayer8Panel = new JPanel();
		awayPlayer8Panel.setLayout(null);
		awayPlayer8Panel.setName("");
		awayPlayer8Panel.setBackground(Color.BLACK);
		awayPlayer8Panel.setBounds(10, 224, 160, 20);
		awayTeamPanel.add(awayPlayer8Panel);
		
		JLabel awayPlayer8 = new JLabel("Away Player 8");
		awayPlayer8.setName("");
		awayPlayer8.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer8.setForeground(new Color(64, 224, 208));
		awayPlayer8.setBounds(0, 0, 160, 20);
		awayPlayer8Panel.add(awayPlayer8);
		
		JPanel awayPlayer9Panel = new JPanel();
		awayPlayer9Panel.setLayout(null);
		awayPlayer9Panel.setName("");
		awayPlayer9Panel.setBackground(Color.BLACK);
		awayPlayer9Panel.setBounds(10, 254, 160, 20);
		awayTeamPanel.add(awayPlayer9Panel);
		
		JLabel awayPlayer9 = new JLabel("Away Player 9");
		awayPlayer9.setName("");
		awayPlayer9.setHorizontalAlignment(SwingConstants.CENTER);
		awayPlayer9.setForeground(new Color(64, 224, 208));
		awayPlayer9.setBounds(0, 0, 160, 20);
		awayPlayer9Panel.add(awayPlayer9);
		
		JPanel homeTeamPanel = new JPanel();
		homeTeamPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		homeTeamPanel.setBackground(new Color(0, 100, 0));
		homeTeamPanel.setBounds(615, 90, 180, 392);
		frame.getContentPane().add(homeTeamPanel);
		homeTeamPanel.setLayout(null);
		
		JPanel homePlayer1Panel = new JPanel();
		homePlayer1Panel.setLayout(null);
		homePlayer1Panel.setName("");
		homePlayer1Panel.setBackground(Color.BLACK);
		homePlayer1Panel.setBounds(10, 10, 160, 20);
		homeTeamPanel.add(homePlayer1Panel);
		
		JLabel homePlayer1 = new JLabel("Home Player 1");
		homePlayer1.setName("");
		homePlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer1.setForeground(new Color(64, 224, 208));
		homePlayer1.setBounds(0, 0, 160, 20);
		homePlayer1Panel.add(homePlayer1);
		
		JPanel homePlayer2Panel = new JPanel();
		homePlayer2Panel.setLayout(null);
		homePlayer2Panel.setName("");
		homePlayer2Panel.setBackground(Color.BLACK);
		homePlayer2Panel.setBounds(10, 40, 160, 20);
		homeTeamPanel.add(homePlayer2Panel);
		
		JLabel homePlayer2 = new JLabel("Home Player 2");
		homePlayer2.setName("");
		homePlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer2.setForeground(new Color(64, 224, 208));
		homePlayer2.setBounds(0, 0, 160, 20);
		homePlayer2Panel.add(homePlayer2);
		
		JPanel homePlayer3Panel = new JPanel();
		homePlayer3Panel.setLayout(null);
		homePlayer3Panel.setName("");
		homePlayer3Panel.setBackground(Color.BLACK);
		homePlayer3Panel.setBounds(10, 70, 160, 20);
		homeTeamPanel.add(homePlayer3Panel);
		
		JLabel homePlayer3 = new JLabel("Home Player 3");
		homePlayer3.setName("");
		homePlayer3.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer3.setForeground(new Color(64, 224, 208));
		homePlayer3.setBounds(0, 0, 160, 20);
		homePlayer3Panel.add(homePlayer3);
		
		JPanel homePlayer4Panel = new JPanel();
		homePlayer4Panel.setLayout(null);
		homePlayer4Panel.setName("");
		homePlayer4Panel.setBackground(Color.BLACK);
		homePlayer4Panel.setBounds(10, 100, 160, 20);
		homeTeamPanel.add(homePlayer4Panel);
		
		JLabel homePlayer4 = new JLabel("Home Player 4");
		homePlayer4.setName("");
		homePlayer4.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer4.setForeground(new Color(64, 224, 208));
		homePlayer4.setBounds(0, 0, 160, 20);
		homePlayer4Panel.add(homePlayer4);
		
		JPanel homePlayer5Panel = new JPanel();
		homePlayer5Panel.setLayout(null);
		homePlayer5Panel.setName("");
		homePlayer5Panel.setBackground(Color.BLACK);
		homePlayer5Panel.setBounds(10, 130, 160, 20);
		homeTeamPanel.add(homePlayer5Panel);
		
		JLabel homePlayer5 = new JLabel("Home Player 5");
		homePlayer5.setName("");
		homePlayer5.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer5.setForeground(new Color(64, 224, 208));
		homePlayer5.setBounds(0, 0, 160, 20);
		homePlayer5Panel.add(homePlayer5);
		
		JPanel homePlayer6Panel = new JPanel();
		homePlayer6Panel.setLayout(null);
		homePlayer6Panel.setName("");
		homePlayer6Panel.setBackground(Color.BLACK);
		homePlayer6Panel.setBounds(10, 160, 160, 20);
		homeTeamPanel.add(homePlayer6Panel);
		
		JLabel homePlayer6 = new JLabel("Home Player 6");
		homePlayer6.setName("");
		homePlayer6.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer6.setForeground(new Color(64, 224, 208));
		homePlayer6.setBounds(0, 0, 160, 20);
		homePlayer6Panel.add(homePlayer6);
		
		JPanel homePlayer7Panel = new JPanel();
		homePlayer7Panel.setLayout(null);
		homePlayer7Panel.setName("");
		homePlayer7Panel.setBackground(Color.BLACK);
		homePlayer7Panel.setBounds(10, 190, 160, 20);
		homeTeamPanel.add(homePlayer7Panel);
		
		JLabel homePlayer7 = new JLabel("Home Player 7");
		homePlayer7.setName("");
		homePlayer7.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer7.setForeground(new Color(64, 224, 208));
		homePlayer7.setBounds(0, 0, 160, 20);
		homePlayer7Panel.add(homePlayer7);
		
		JPanel homePlayer8Panel = new JPanel();
		homePlayer8Panel.setLayout(null);
		homePlayer8Panel.setName("");
		homePlayer8Panel.setBackground(Color.BLACK);
		homePlayer8Panel.setBounds(10, 220, 160, 20);
		homeTeamPanel.add(homePlayer8Panel);
		
		JLabel homePlayer8 = new JLabel("Home Player 8");
		homePlayer8.setName("");
		homePlayer8.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer8.setForeground(new Color(64, 224, 208));
		homePlayer8.setBounds(0, 0, 160, 20);
		homePlayer8Panel.add(homePlayer8);
		
		JPanel homePlayer9Panel = new JPanel();
		homePlayer9Panel.setLayout(null);
		homePlayer9Panel.setName("");
		homePlayer9Panel.setBackground(Color.BLACK);
		homePlayer9Panel.setBounds(10, 250, 160, 20);
		homeTeamPanel.add(homePlayer9Panel);
		
		JLabel homePlayer9 = new JLabel("Home Player 9");
		homePlayer9.setName("");
		homePlayer9.setHorizontalAlignment(SwingConstants.CENTER);
		homePlayer9.setForeground(new Color(64, 224, 208));
		homePlayer9.setBounds(0, 0, 160, 20);
		homePlayer9Panel.add(homePlayer9);
		
		//Here begins more handwritten code
		
		
		
		
		innings.add(awayScorePanel1);//add elements to arraylist
		innings.add(homeScorePanel1);
		innings.add(awayScorePanel2);
		innings.add(homeScorePanel2);
		innings.add(awayScorePanel3);
		innings.add(homeScorePanel3);
		innings.add(awayScorePanel4);
		innings.add(homeScorePanel4);
		innings.add(awayScorePanel5);
		innings.add(homeScorePanel5);
		innings.add(awayScorePanel6);
		innings.add(homeScorePanel6);
		innings.add(awayScorePanel7);
		innings.add(homeScorePanel7);
		innings.add(awayScorePanel8);
		innings.add(homeScorePanel8);
		innings.add(awayScorePanel9);
		innings.add(homeScorePanel9);
		
		inningsLabels.add(awayInning1);//add elements to arraylist
		inningsLabels.add(homeInning1);
		inningsLabels.add(awayInning2);
		inningsLabels.add(homeInning2);
		inningsLabels.add(awayInning3);
		inningsLabels.add(homeInning3);
		inningsLabels.add(awayInning4);
		inningsLabels.add(homeInning4);
		inningsLabels.add(awayInning5);
		inningsLabels.add(homeInning5);
		inningsLabels.add(awayInning6);
		inningsLabels.add(homeInning6);
		inningsLabels.add(awayInning7);
		inningsLabels.add(homeInning7);
		inningsLabels.add(awayInning8);
		inningsLabels.add(homeInning8);
		inningsLabels.add(awayInning9);
		inningsLabels.add(homeInning9);
		
		homePlayersPanels.add(homePlayer1Panel);//add elements to arraylist
		homePlayersPanels.add(homePlayer2Panel);
		homePlayersPanels.add(homePlayer3Panel);
		homePlayersPanels.add(homePlayer4Panel);
		homePlayersPanels.add(homePlayer5Panel);
		homePlayersPanels.add(homePlayer6Panel);
		homePlayersPanels.add(homePlayer7Panel);
		homePlayersPanels.add(homePlayer8Panel);
		homePlayersPanels.add(homePlayer9Panel);
		
		awayPlayersPanels.add(awayPlayer1Panel);//add elements to arraylist
		awayPlayersPanels.add(awayPlayer2Panel);
		awayPlayersPanels.add(awayPlayer3Panel);
		awayPlayersPanels.add(awayPlayer4Panel);
		awayPlayersPanels.add(awayPlayer5Panel);
		awayPlayersPanels.add(awayPlayer6Panel);
		awayPlayersPanels.add(awayPlayer7Panel);
		awayPlayersPanels.add(awayPlayer8Panel);
		awayPlayersPanels.add(awayPlayer9Panel);
		
		homePlayers.add(homePlayer1);//add elements to arraylist
		homePlayers.add(homePlayer2);
		homePlayers.add(homePlayer3);
		homePlayers.add(homePlayer4);
		homePlayers.add(homePlayer5);
		homePlayers.add(homePlayer6);
		homePlayers.add(homePlayer7);
		homePlayers.add(homePlayer8);
		homePlayers.add(homePlayer9);
		
		awayPlayers.add(awayPlayer1);//add elements to arraylist
		awayPlayers.add(awayPlayer2);
		awayPlayers.add(awayPlayer3);
		awayPlayers.add(awayPlayer4);
		awayPlayers.add(awayPlayer5);
		awayPlayers.add(awayPlayer6);
		awayPlayers.add(awayPlayer7);
		awayPlayers.add(awayPlayer8);
		awayPlayers.add(awayPlayer9);
		
		//Setting up some more panels and text fields
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(new Color(0, 100, 0));
		panel.setBounds(202, 92, 401, 188);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(new Color(0, 100, 0));
		displayPanel.setBounds(202, 293, 401, 189);
		frame.getContentPane().add(displayPanel);
		displayPanel.setLayout(null);
		
		JPanel playResultPanel = new JPanel();
		playResultPanel.setBackground(new Color(0, 0, 0));
		playResultPanel.setBounds(90, 137, 209, 39);
		displayPanel.add(playResultPanel);
		playResultPanel.setLayout(null);
		
		
		playResultLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		playResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playResultLabel.setForeground(new Color(0, 255, 255));
		playResultLabel.setBounds(0, 0, 209, 39);
		playResultPanel.add(playResultLabel);
		
		JPanel currentPlayerPanel = new JPanel();
		currentPlayerPanel.setBackground(new Color(0, 0, 0));
		currentPlayerPanel.setBounds(90, 13, 209, 39);
		displayPanel.add(currentPlayerPanel);
		currentPlayerPanel.setLayout(null);
		
		
		currentPlayerLabel.setForeground(new Color(0, 255, 255));
		currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentPlayerLabel.setBounds(0, 0, 209, 39);
		currentPlayerPanel.add(currentPlayerLabel);
		
		JPanel playerAtBatsPanel = new JPanel();
		playerAtBatsPanel.setLayout(null);
		playerAtBatsPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		playerAtBatsPanel.setBackground(Color.BLACK);
		playerAtBatsPanel.setBounds(90, 104, 20, 20);
		displayPanel.add(playerAtBatsPanel);
		
		
		playerAtBatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerAtBatsLabel.setForeground(Color.CYAN);
		playerAtBatsLabel.setBounds(0, 0, 20, 20);
		playerAtBatsPanel.add(playerAtBatsLabel);
		
		JPanel playerHitsPanel = new JPanel();
		playerHitsPanel.setLayout(null);
		playerHitsPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		playerHitsPanel.setBackground(Color.BLACK);
		playerHitsPanel.setBounds(122, 104, 20, 20);
		displayPanel.add(playerHitsPanel);
		
		
		playerHitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerHitsLabel.setForeground(Color.CYAN);
		playerHitsLabel.setBounds(0, 0, 20, 20);
		playerHitsPanel.add(playerHitsLabel);
		
		JPanel playerDoublesPanel = new JPanel();
		playerDoublesPanel.setLayout(null);
		playerDoublesPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		playerDoublesPanel.setBackground(Color.BLACK);
		playerDoublesPanel.setBounds(184, 104, 20, 20);
		displayPanel.add(playerDoublesPanel);
		
		
		playerDoublesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerDoublesLabel.setForeground(Color.CYAN);
		playerDoublesLabel.setBounds(0, 0, 20, 20);
		playerDoublesPanel.add(playerDoublesLabel);
		
		JPanel playerTriplesPanel = new JPanel();
		playerTriplesPanel.setLayout(null);
		playerTriplesPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		playerTriplesPanel.setBackground(Color.BLACK);
		playerTriplesPanel.setBounds(216, 104, 20, 20);
		displayPanel.add(playerTriplesPanel);
		
		
		playerTriplesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerTriplesLabel.setForeground(Color.CYAN);
		playerTriplesLabel.setBounds(0, 0, 20, 20);
		playerTriplesPanel.add(playerTriplesLabel);
		
		JPanel playerHomersPanel = new JPanel();
		playerHomersPanel.setLayout(null);
		playerHomersPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		playerHomersPanel.setBackground(Color.BLACK);
		playerHomersPanel.setBounds(248, 104, 20, 20);
		displayPanel.add(playerHomersPanel);
		
		
		playerHomersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerHomersLabel.setForeground(Color.CYAN);
		playerHomersLabel.setBounds(0, 0, 20, 20);
		playerHomersPanel.add(playerHomersLabel);
		
		JPanel atBatsPanel = new JPanel();
		atBatsPanel.setLayout(null);
		atBatsPanel.setBackground(new Color(0, 100, 0));
		atBatsPanel.setBounds(90, 71, 20, 20);
		displayPanel.add(atBatsPanel);
		
		JLabel atBatsLabel = new JLabel("AB");
		atBatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		atBatsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		atBatsLabel.setBounds(0, 0, 20, 20);
		atBatsPanel.add(atBatsLabel);
		
		JPanel hitsPanel = new JPanel();
		hitsPanel.setLayout(null);
		hitsPanel.setBackground(new Color(0, 100, 0));
		hitsPanel.setBounds(122, 71, 20, 20);
		displayPanel.add(hitsPanel);
		
		JLabel hitsLabel = new JLabel("H");
		hitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hitsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		hitsLabel.setBounds(0, 0, 20, 20);
		hitsPanel.add(hitsLabel);
		
		JPanel doublesPanel = new JPanel();
		doublesPanel.setLayout(null);
		doublesPanel.setBackground(new Color(0, 100, 0));
		doublesPanel.setBounds(184, 71, 20, 20);
		displayPanel.add(doublesPanel);
		
		JLabel doublesLabel = new JLabel("D");
		doublesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		doublesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		doublesLabel.setBounds(0, 0, 20, 20);
		doublesPanel.add(doublesLabel);
		
		JPanel triplesPanel = new JPanel();
		triplesPanel.setLayout(null);
		triplesPanel.setBackground(new Color(0, 100, 0));
		triplesPanel.setBounds(216, 71, 20, 20);
		displayPanel.add(triplesPanel);
		
		JLabel triplesLabel = new JLabel("T");
		triplesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		triplesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		triplesLabel.setBounds(0, 0, 20, 20);
		triplesPanel.add(triplesLabel);
		
		JPanel homersPanel = new JPanel();
		homersPanel.setLayout(null);
		homersPanel.setBackground(new Color(0, 100, 0));
		homersPanel.setBounds(248, 71, 20, 20);
		displayPanel.add(homersPanel);
		
		JLabel homersLabel = new JLabel("HR");
		homersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homersLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		homersLabel.setBounds(0, 0, 20, 20);
		homersPanel.add(homersLabel);
		
		JPanel playerWalksPanel = new JPanel();
		playerWalksPanel.setLayout(null);
		playerWalksPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		playerWalksPanel.setBackground(Color.BLACK);
		playerWalksPanel.setBounds(154, 104, 20, 20);
		displayPanel.add(playerWalksPanel);
		
		playerWalksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerWalksLabel.setForeground(Color.CYAN);
		playerWalksLabel.setBounds(0, 0, 20, 20);
		playerWalksPanel.add(playerWalksLabel);
		
		JPanel walksPanel = new JPanel();
		walksPanel.setLayout(null);
		walksPanel.setBackground(new Color(0, 100, 0));
		walksPanel.setBounds(154, 71, 20, 20);
		displayPanel.add(walksPanel);
		
		JLabel walksLabel = new JLabel("W");
		walksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		walksLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		walksLabel.setBounds(0, 0, 20, 20);
		walksPanel.add(walksLabel);
		
		txtBase1 = new JTextField();
		txtBase1.setHorizontalAlignment(SwingConstants.CENTER);
		txtBase1.setText("B1");
		txtBase1.setForeground(Color.CYAN);
		txtBase1.setBackground(Color.BLACK);
		txtBase1.setBounds(280, 60, 76, 46);
		txtBase1.setEditable(false);
		panel.add(txtBase1);
		txtBase1.setColumns(10);
		
		txtBase2 = new JTextField();
		txtBase2.setText("B2");
		txtBase2.setHorizontalAlignment(SwingConstants.CENTER);
		txtBase2.setForeground(Color.CYAN);
		txtBase2.setColumns(10);
		txtBase2.setBackground(Color.BLACK);
		txtBase2.setBounds(152, 6, 76, 46);
		txtBase2.setEditable(false);
		panel.add(txtBase2);
		
		txtBase3 = new JTextField();
		txtBase3.setText("B3");
		txtBase3.setHorizontalAlignment(SwingConstants.CENTER);
		txtBase3.setForeground(Color.CYAN);
		txtBase3.setColumns(10);
		txtBase3.setBackground(Color.BLACK);
		txtBase3.setBounds(43, 60, 76, 46);
		txtBase3.setEditable(false);
		panel.add(txtBase3);
		
		txtHome = new JTextField();
		txtHome.setText("Home");
		txtHome.setHorizontalAlignment(SwingConstants.CENTER);
		txtHome.setForeground(Color.CYAN);
		txtHome.setColumns(10);
		txtHome.setBackground(Color.BLACK);
		txtHome.setBounds(152, 136, 76, 46);
		txtHome.setEditable(false);
		panel.add(txtHome);
		
		
		
		//start game button begins the game, then disables itself
				startGameButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(int i = 0; i < 9; i++) {
							homeAtBats.add(0);
							homeHits.add(0);
							homeWalks.add(0);
							homeDoubles.add(0);
							homeTriples.add(0);
							homeHomers.add(0);
							awayAtBats.add(0);
							awayHits.add(0);
							awayWalks.add(0);
							awayDoubles.add(0);
							awayTriples.add(0);
							awayHomers.add(0);
							// test
							cumulativeHomeAtBats.add(0);
							cumulativeHomeHits.add(0);
							cumulativeHomeWalks.add(0);
							cumulativeHomeDoubles.add(0);
							cumulativeHomeTriples.add(0);
							cumulativeHomeHomers.add(0);
							cumulativeAwayAtBats.add(0);
							cumulativeAwayHits.add(0);
							cumulativeAwayWalks.add(0);
							cumulativeAwayDoubles.add(0);
							cumulativeAwayTriples.add(0);
							cumulativeAwayHomers.add(0);
						}
						
						startGameButton.setEnabled(false);
						AwayNextButton.setVisible(true);
						outs.setText("0");//set text and colors for tracking boxes
						outs.setForeground(Color.cyan);
						homeTotalRuns.setText("0");
						homeTotalRuns.setForeground(Color.cyan);
						awayTotalRuns.setText("0");
						awayTotalRuns.setForeground(Color.cyan);
						homeTotalHits.setText("0");
						homeTotalHits.setForeground(Color.cyan);
						awayTotalHits.setText("0");
						awayTotalHits.setForeground(Color.cyan);
						homeTotalWalks.setText("0");
						homeTotalWalks.setForeground(Color.cyan);
						awayTotalWalks.setText("0");
						awayTotalWalks.setForeground(Color.cyan);
						awayPlayer1Panel.setBackground(Color.cyan);//set away player colors and scorepanel colors
						awayPlayer1.setForeground(Color.black);
						awayScorePanel1.setBackground(Color.cyan);
						currentPlayerLabel.setText(awayPlayers.get(awayPlayerNum).getText());
						HomeNextButton.setEnabled(false);
						HomeNextButton.setVisible(false);
						awayAtBats.set(awayPlayerNum, awayAtBats.get(awayPlayerNum)+1);
						cumulativeAwayAtBats.set(awayPlayerNum, cumulativeAwayAtBats.get(awayPlayerNum)+1);
						playerAtBatsLabel.setText(String.valueOf(awayAtBats.get(awayPlayerNum)));
						playerWalksLabel.setText(String.valueOf(awayWalks.get(awayPlayerNum)));
						playerHitsLabel.setText(String.valueOf(awayHits.get(awayPlayerNum)));
						playerDoublesLabel.setText(String.valueOf(awayDoubles.get(awayPlayerNum)));
						playerTriplesLabel.setText(String.valueOf(awayTriples.get(awayPlayerNum)));
						playerHomersLabel.setText(String.valueOf(awayHomers.get(awayPlayerNum)));
					}
				});
				startGameButton.setBounds(202, 493, 123, 29);
				frame.getContentPane().add(startGameButton);
		
		//Away next button only appears when it is the away half-inning, it calls the next function
		AwayNextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				next();//call next
				
				//disable and hide one button while enabling and showing the other
				if(frameNum % 2 != 0 && frameNum != 18) {
					
					AwayNextButton.setEnabled(false);
					AwayNextButton.setVisible(false);
					HomeNextButton.setEnabled(true);
					HomeNextButton.setVisible(true);
				}
				else if(frameNum != 18){

					AwayNextButton.setEnabled(true);
					AwayNextButton.setVisible(true);
					HomeNextButton.setEnabled(false);
					HomeNextButton.setVisible(false);
				}
				else {
					AwayNextButton.setEnabled(false);
					AwayNextButton.setVisible(false);
					HomeNextButton.setEnabled(false);
					HomeNextButton.setVisible(false);
					txtBase1.setForeground(Color.CYAN);
					txtBase1.setBackground(Color.BLACK);
					txtBase2.setForeground(Color.CYAN);
					txtBase2.setBackground(Color.BLACK);
					txtBase3.setForeground(Color.CYAN);
					txtBase3.setBackground(Color.BLACK);
					txtHome.setForeground(Color.CYAN);
					txtHome.setBackground(Color.BLACK);
				}
			}
		});
		AwayNextButton.setBounds(33, 493, 117, 29);
		frame.getContentPane().add(AwayNextButton);
		AwayNextButton.setVisible(false);
		HomeNextButton.setVisible(false);
		//Home next button only appears when it is the home half-inning, it calls the next function
		HomeNextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				next();//call next 
				
				//disable and hide one button while enabling and showing the other
				if(frameNum % 2 != 0 && frameNum != 18) {
					AwayNextButton.setEnabled(false);
					AwayNextButton.setVisible(false);
					HomeNextButton.setEnabled(true);
					HomeNextButton.setVisible(true);
				}
				else if(frameNum!= 18){

					AwayNextButton.setEnabled(true);
					AwayNextButton.setVisible(true);
					HomeNextButton.setEnabled(false);
					HomeNextButton.setVisible(false);
				}
				else {
					AwayNextButton.setEnabled(false);
					AwayNextButton.setVisible(false);
					HomeNextButton.setEnabled(false);
					HomeNextButton.setVisible(false);
					txtBase1.setForeground(Color.CYAN);
					txtBase1.setBackground(Color.BLACK);
					txtBase2.setForeground(Color.CYAN);
					txtBase2.setBackground(Color.BLACK);
					txtBase3.setForeground(Color.CYAN);
					txtBase3.setBackground(Color.BLACK);
					txtHome.setForeground(Color.CYAN);
					txtHome.setBackground(Color.BLACK);
				}
			}
		});
		HomeNextButton.setBounds(651, 493, 117, 29);
		frame.getContentPane().add(HomeNextButton);		
		
		
	}
}
