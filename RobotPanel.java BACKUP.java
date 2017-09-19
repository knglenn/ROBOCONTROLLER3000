//Kevin Glenn

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import java.math.*;
import javax.swing.event.*;
import java.awt.event.*;

public class RobotPanel extends JPanel {

	private final int w = 5;
 	private final int h = 3;
 	private int i = 0;
 	private JButton buttons[][];
 	String command;
 	String stop = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
 	ButtonModel bModel[][];

	public RobotPanel() {

		RoboConnection roboConnection = new RoboConnection();

		int timerDelay = 100;
		final Timer timer = new Timer(timerDelay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button pressed");
			}
		});
		
		
		
		GridLayout layout = new GridLayout(w,h);
		setLayout(layout);
		layout.setHgap(1);
		layout.setVgap(3);

 		buttons = new JButton[w][h];
 		bModel = new ButtonModel[w][h];

 		fillGrid();

 		buttons[0][0].setText("Velocity (-)");
 		buttons[0][1].setText("Up");
 		buttons[0][2].setText("Velocity (+)");
 		buttons[1][0].setVisible(false);
 		buttons[1][1].setText("Forward");
 		buttons[1][2].setVisible(false);
 		buttons[2][0].setText("Left");
 		buttons[2][1].setText("Takeoff/land");
 		buttons[2][2].setText("Right"); 
 		buttons[3][0].setVisible(false);
 		buttons[3][1].setText("Backward"); 
 		buttons[3][2].setVisible(false);
 		buttons[4][0].setText("Turn Left"); 
 		buttons[4][1].setText("Down"); 
 		buttons[4][2].setText("Turn Right"); 




 		ActionListener takeoff = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass");
 				
 				if(i%2 == 0) {
 					command = "{\"op\":\"publish\",\"topic\":\"/ardrone/takeoff\",\"msg\":{}}";
 				}
 				else {
 					command = "{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}";		
 				}
 				roboConnection.instruct(command);
 				i++;

 			}
 		};

 		ActionListener forward = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass2");
 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
 				roboConnection.instruct(command);
 			}
 		};

 		ActionListener land = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass3");
 				if(i%2 ==0) {
	 				command = "{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}";
	 				roboConnection.instruct(command);
 				}
 				else {
 					roboConnection.instruct(stop);
 				}

 				i++;
 			}
 		};

 		ActionListener ledft = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass4");
 				if(i%2 == 0) {
	 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":-0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
	 				roboConnection.instruct(command);
	 			}
	 			else {
 					roboConnection.instruct(stop);
 				}
 				
 				i++;
 			}
 		};

 		ActionListener backward = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass5");
 				command = command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":-0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
 				roboConnection.instruct(command);
 			}
 		};

 		ActionListener right = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass6");
 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
 				roboConnection.instruct(command);
 			}
 		};

 		ActionListener up = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass7");
 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0.25},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
 				roboConnection.instruct(command);
 			}
 		};

 		ActionListener down = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass8");
 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":-0.25},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
 				roboConnection.instruct(command);
 			}
 		};

 		ActionListener lTurn = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass9");
 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":1}}}";
 				roboConnection.instruct(command);
 			}
 		};

 		ActionListener rTurn = new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				System.out.println("pass9");
 				command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":5,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":-1}}}";
 				roboConnection.instruct(command);
 			}
 		};



 	/*	buttons[0][0].addActionListener(takeoff);
 		buttons[0][1].addActionListener(up);
 		buttons[0][2].addActionListener(land);
 		//buttons[1][0].addActionListener(left);
 		//buttons[1][1].addActionListener(forward);
 		//buttons[1][2].addActionListener(right);
 		//buttons[2][0].addActionListener(left);
 		buttons[2][1].addActionListener(takeoff);
 		buttons[2][2].addActionListener(right);
 		//buttons[3][0].addActionListener()
 		buttons[3][1].addActionListener(backward);
 		//buttons[3][1].addActionListener()
 		buttons[4][0].addActionListener(lTurn);
 		buttons[4][1].addActionListener(down);
 		buttons[4][2].addActionListener(rTurn); */



 		/*buttons[0][1].setText("Up");
 		buttons[0][2].setText("Velocity (+)");
 		buttons[1][0].setVisible(false);
 		buttons[1][1].setText("Forward");
 		buttons[1][2].setVisible(false);
 		buttons[2][0].setText("Left");
 		buttons[2][1].setText("Land/Takeoff");
 		buttons[2][2].setText("Right"); 
 		buttons[3][0].setVisible(false);
 		buttons[3][1].setText("Backward"); 
 		buttons[3][2].setVisible(false);
 		buttons[4][0].setText("Turn Left"); 
 		buttons[4][1].setText("Down"); 
 		buttons[4][2].setText("Turn Right"); */

//up
bModel[0][1].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[0][1].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0.25},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[0][1].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//forward
bModel[1][1].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[1][1].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[1][1].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//left
bModel[2][0].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[2][0].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":-0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[2][0].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//right 
bModel[2][2].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[2][2].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[2][2].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//backward
bModel[3][1].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[3][1].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":-0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[3][1].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//turn left
bModel[4][0].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[4][0].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":1}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[4][0].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//down
bModel[4][1].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[4][1].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":-0.25},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[4][1].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 

//turn right
bModel[4][2].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(bModel[4][2].isPressed() && !timer.isRunning()) {
					command = "{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":-1}}}";
					roboConnection.instruct(command);
					timer.start();
				}
				else if (!bModel[4][2].isPressed() && timer.isRunning()) {
					roboConnection.instruct(stop);
					timer.stop();
				}
			}
		}); 


	}

	private void fillGrid() {
 		for (int i = 0; i < w; i++) {
 			for (int j = 0; j < h; j++) {
 				buttons[i][j] = new JButton();
 				bModel[i][j] = buttons[i][j].getModel();
 				add(buttons[i][j]);
 			}
 		}
 		

 	}
}