/**
*Class:             SimpleUI.java
*Project:          	Trial Project
*Author:            Jason Van Kerkhoven
					Nathaniel Charlebois
*Date of Update:    02/02/2017
*Version:           1.0.0
*
*Purpose:           Basic interface for trial project.
*					Stripped down version of NodeUI.
*					Basic input getting and prints.
*
*
*
*Update Log
					v1.0.1
						-Added print()
					v1.0.0
*						- null
*/

package ui;


import javax.swing.JDialog;
//imports
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;


public class SimpleUI extends JFrame
{
	//declaring static class constants
	private static final Font DEFAULT_FONT = new Font("Monospaced", Font.PLAIN, 13);
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	private static final Color DEFAULT_TEXT_COLOR = Color.ORANGE;
	private static final int DEFAULT_WINDOW_X = 600;
	private static final int DEFAULT_WINDOW_Y = 600;

	//declaring local instance variables
	JTextArea output;


	//generic constructor
	public SimpleUI(String title)
	{
		//build frame
		super(title);
		this.setBounds(100, 100, DEFAULT_WINDOW_X, DEFAULT_WINDOW_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//add scroll pane for output
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		//add text area for output
		output = new JTextArea();
		//output.setToolTipText("Shows info on the \"Simon Says\"-like game");	//this got annoying fast
		output.setEditable(false);
		output.setWrapStyleWord(true);
		output.setLineWrap(true);
		output.setFont(DEFAULT_FONT);
		output.setCaretColor(DEFAULT_TEXT_COLOR);
		output.setForeground(DEFAULT_TEXT_COLOR);
		output.setBackground(DEFAULT_BACKGROUND_COLOR);
		scrollPane.setViewportView(output);

		//make visible
		this.setVisible(true);
		println("Simon-Says running...");
	}


	//close the UI
	//after call set SimpleUI to null
	public void close()
	{
		println("Closing UI...");
		this.dispose();
	}


	//generic format println
	public void println(String msg)
	{
		if (msg != null)
		{
			//format and print
			msg = msg.replace("\n", "\n  ");
			output.append(msg + "\n");
		}
	}

	public void print(String msg){
		if(msg != null){
			//format and print
			msg = msg.replace("\n", "\n ");
			output.append(msg);
		}

	}


	//error dialog
	public void printError(String msg)
	{
		println("ERROR: " + msg);
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}


	//get user input using dialog
	public int getPort(String msg)
	{
		String input;
		while(true)
		{
			//prompt user for input
			input = JOptionPane.showInputDialog(this, msg, "Enter Port", JOptionPane.QUESTION_MESSAGE);
			println("User entered Port: \"" + input + "\"");


			//check validity
			if (input != null)
			{
				//convert to integer
				try
				{
					int port = Integer.parseInt(input);
					return port;
				}
				//NaN entered
				catch (NumberFormatException e)
				{
					printError("Port number must be a valid 32bit integer");
				}
			}
		}
	}
}
