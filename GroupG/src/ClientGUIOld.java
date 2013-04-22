import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.ChatClient;

import common.ChatIF;


public class ClientGUIOld implements Observer
{
	//Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	//Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	ChatClient client;
	JFrame chatFrame;
	private JLabel titleLabel;
	private javax.swing.JCheckBox avalibilityCheckBox;
	private javax.swing.JButton blockButton;
	private javax.swing.JTextField blockInput;
	private javax.swing.JPanel blockPanel;
	private javax.swing.JButton blocksMeButton;
	private javax.swing.JButton channelButton;
	private javax.swing.JTextField channelInput;
	private javax.swing.JTextArea chatDisplay;
	private javax.swing.JScrollPane chatDisplayScrollPane;
	private javax.swing.JScrollPane chatInputScrollPane;
	private javax.swing.JPanel chatPanel;
	private javax.swing.JTabbedPane chatTabbedPane;
	private javax.swing.JTextArea chatTextInput;
	private javax.swing.JButton forwardButton;
	private javax.swing.JTextField forwardInput;
	private javax.swing.JButton getCurrentHost;
	private javax.swing.JButton getCurrentPort;	
	private javax.swing.JPanel inputPanel;
	private javax.swing.JButton myBlocksButton;
	private javax.swing.JPanel networkPanel;
	private javax.swing.JButton sendButton;
	private javax.swing.JButton setHostButton;
	private javax.swing.JTextField setHostInput;
	private javax.swing.JButton setPortButton;
	private javax.swing.JTextField setPortInput;
	private javax.swing.JPanel settingsChatPanel;
	private javax.swing.JPanel settingsPanel;
	private javax.swing.JButton unblockButton;
	private javax.swing.JTextField unblockInput;	
	private javax.swing.JButton exitButton;

	//Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientGUI.
	 *
	 */
	public ClientGUIOld(String loginID, String password, String host, int port) 
	{
		try 
		{
			client= new ChatClient(loginID, password, host, port, this);
		} 
		catch(IOException exception) 
		{
			System.out.println("Cannot open connection. Awaiting command.");
			//System.exit(1);
		}
		client.addObserver(this);

		SetupUI(loginID);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof String){
			String message = arg1.toString();
			if(message.startsWith("Forwarding: ")){
				forwardButton.setText("Return");
				channelButton.setEnabled(false);
				blockButton.setEnabled(false);
				sendButton.setEnabled(false);
				chatTextInput.setEnabled(false);
				chatDisplay.setEnabled(false);
				avalibilityCheckBox.setEnabled(false);
			} else if (message.startsWith("End Forwarding:")){
				forwardButton.setText("Forward");
				channelButton.setEnabled(true);
				blockButton.setEnabled(true);
				sendButton.setEnabled(true);
				chatTextInput.setEnabled(true);
				chatDisplay.setEnabled(true);
				avalibilityCheckBox.setEnabled(true);
			}

			if(!message.substring( message.length()-1, message.length()).equals("\n"))
				message += "\n";

			//Update UI display safely
			final String msg = message;
			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run()
				{
					chatDisplay.append(msg) ;
				}
			});
		}
	}

	private void SetupUI(String loginID) {
		//Create and set up the window.
		chatFrame = new JFrame( "Chat " + loginID);

		chatFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//chatFrame.setSize(800, 800);

		titleLabel = new JLabel();
		chatTabbedPane = new javax.swing.JTabbedPane();
		chatPanel = new javax.swing.JPanel();
		chatDisplayScrollPane = new javax.swing.JScrollPane();
		chatDisplay = new javax.swing.JTextArea();
		inputPanel = new javax.swing.JPanel();
		chatInputScrollPane = new javax.swing.JScrollPane();
		chatTextInput = new javax.swing.JTextArea();
		sendButton = new javax.swing.JButton();
		avalibilityCheckBox = new javax.swing.JCheckBox();
		settingsPanel = new javax.swing.JPanel();
		settingsChatPanel = new javax.swing.JPanel();
		channelButton = new javax.swing.JButton();
		channelInput = new javax.swing.JTextField();
		forwardInput = new javax.swing.JTextField();
		forwardButton = new javax.swing.JButton();
		blockPanel = new javax.swing.JPanel();
		blockButton = new javax.swing.JButton();
		blockInput = new javax.swing.JTextField();
		unblockInput = new javax.swing.JTextField();
		unblockButton = new javax.swing.JButton();
		myBlocksButton = new javax.swing.JButton();
		blocksMeButton = new javax.swing.JButton();
		networkPanel = new javax.swing.JPanel();
		setPortInput = new javax.swing.JTextField();
		setPortButton = new javax.swing.JButton();
		setHostInput = new javax.swing.JTextField();
		setHostButton = new javax.swing.JButton();
		getCurrentHost = new javax.swing.JButton();
		getCurrentPort = new javax.swing.JButton();
		exitButton = new javax.swing.JButton();

		//Customize Form
		titleLabel = new JLabel("Chat " + loginID);

		//GENERATED FROM NETBEANS		
		chatFrame.setResizable(false);

		chatPanel.setPreferredSize(new java.awt.Dimension(430, 600));
		chatPanel.setVerifyInputWhenFocusTarget(false);

		chatDisplayScrollPane.setAutoscrolls(true);

		chatDisplay.setEditable(false);
		chatDisplay.setColumns(20);
		chatDisplay.setFont(new java.awt.Font("Arial", 0, 13)); 
		chatDisplay.setLineWrap(true);
		chatDisplay.setRows(5);
		chatDisplayScrollPane.setViewportView(chatDisplay);

		chatTextInput.setColumns(20);
		chatTextInput.setLineWrap(true);
		chatTextInput.setRows(5);
		chatInputScrollPane.setViewportView(chatTextInput);

		sendButton.setText("Send");

		avalibilityCheckBox.setFont(new java.awt.Font("Tahoma", 0, 10));
		avalibilityCheckBox.setText("Unavailable");        

		javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
		inputPanel.setLayout(inputPanelLayout);
		inputPanelLayout.setHorizontalGroup(
				inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(inputPanelLayout.createSequentialGroup()
						.addComponent(chatInputScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(avalibilityCheckBox)
								.addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(117, 117, 117))
				);
		inputPanelLayout.setVerticalGroup(
				inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(inputPanelLayout.createSequentialGroup()
						.addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(inputPanelLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(avalibilityCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(chatInputScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
		chatPanel.setLayout(chatPanelLayout);
		chatPanelLayout.setHorizontalGroup(
				chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(chatPanelLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
										.addComponent(chatDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
												.addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap())))
				);
		chatPanelLayout.setVerticalGroup(
				chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(chatPanelLayout.createSequentialGroup()
						.addGap(7, 7, 7)
						.addComponent(chatDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		chatTabbedPane.addTab("Chat", chatPanel);

		settingsChatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

		channelButton.setText("Channel");
		channelButton.setBorderPainted(false);

		forwardButton.setText("Forward");
		forwardButton.setBorderPainted(false);

		javax.swing.GroupLayout settingsChatPanelLayout = new javax.swing.GroupLayout(settingsChatPanel);
		settingsChatPanel.setLayout(settingsChatPanelLayout);
		settingsChatPanelLayout.setHorizontalGroup(
				settingsChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(settingsChatPanelLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(settingsChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, settingsChatPanelLayout.createSequentialGroup()
										.addComponent(channelInput, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(channelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, settingsChatPanelLayout.createSequentialGroup()
												.addComponent(forwardInput, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(forwardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		settingsChatPanelLayout.setVerticalGroup(
				settingsChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(settingsChatPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(settingsChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(channelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(channelButton))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(settingsChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(forwardButton)
										.addComponent(forwardInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		blockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Block"));

		blockButton.setBorderPainted(false);
		blockButton.setText("Block");


		unblockInput.setToolTipText("Leave empty to unblock all");
		unblockButton.setText("UnBlock");
		unblockButton.setBorderPainted(false);

		myBlocksButton.setText("My Blocks");
		myBlocksButton.setActionCommand("My Blocks");

		blocksMeButton.setText("Blocks Me");
		blocksMeButton.setActionCommand("Blocks Me");

		javax.swing.GroupLayout blockPanelLayout = new javax.swing.GroupLayout(blockPanel);
		blockPanel.setLayout(blockPanelLayout);
		blockPanelLayout.setHorizontalGroup(
				blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blockPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(unblockInput)
								.addComponent(blockInput, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(blockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(unblockButton, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(blocksMeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(myBlocksButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addContainerGap())
				);
		blockPanelLayout.setVerticalGroup(
				blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(blockPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(blockInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(blockButton)
								.addComponent(myBlocksButton))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(blockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(unblockInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(unblockButton)
										.addComponent(blocksMeButton))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		networkPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Network"));

		setPortButton.setText("Set Port");
		setHostButton.setText("Set Host");
		getCurrentHost.setText("Get Host");
		getCurrentPort.setText("Get Port");
		getCurrentPort.setActionCommand("Get Port");

		javax.swing.GroupLayout networkPanelLayout = new javax.swing.GroupLayout(networkPanel);
		networkPanel.setLayout(networkPanelLayout);
		networkPanelLayout.setHorizontalGroup(
				networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(networkPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(setPortInput, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(setHostInput))
								.addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(networkPanelLayout.createSequentialGroup()
												.addGap(6, 6, 6)
												.addComponent(setHostButton, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
												.addGroup(networkPanelLayout.createSequentialGroup()
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(setPortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(getCurrentHost, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
																.addComponent(getCurrentPort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
				);
		networkPanelLayout.setVerticalGroup(
				networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(networkPanelLayout.createSequentialGroup()
						.addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(setPortInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(setPortButton)
								.addComponent(getCurrentPort))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(setHostInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(setHostButton)
										.addComponent(getCurrentHost))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		exitButton.setText("Exit");
		exitButton.setMaximumSize(new java.awt.Dimension(95, 23));
		exitButton.setMinimumSize(new java.awt.Dimension(95, 23));

		javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
		settingsPanel.setLayout(settingsPanelLayout);
		settingsPanelLayout.setHorizontalGroup(
				settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(settingsPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(settingsPanelLayout.createSequentialGroup()
										.addComponent(networkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
												.addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(settingsChatPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(blockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
														.addContainerGap())
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
																.addGap(0, 0, Short.MAX_VALUE)
																.addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(26, 26, 26))))
				);
		settingsPanelLayout.setVerticalGroup(
				settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(settingsPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(networkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(blockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(settingsChatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
						.addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(19, 19, 19))
				);

		chatTabbedPane.addTab("Settings", settingsPanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(chatFrame.getContentPane());
		chatFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chatTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(chatTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
				);
		//END GENERATED CODE

		sendButton.setToolTipText("Send Message");

		//##################### Listeners #####################

		//display scrollbar vertical update
		chatDisplayScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent event) {  
				event.getAdjustable().setValue(event.getAdjustable().getMaximum());  
			}
		});

		chatTextInput.addKeyListener( new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int keyCode = arg0.getKeyCode();
				if(keyCode == 10){ //enter key
					String message = chatTextInput.getText();
					if(message != null && message.length() > 0){
						client.handleMessageFromClientUI(message);
						chatTextInput.setText("");
						arg0.consume();
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent arg0) {}
			@Override
			public void keyReleased(KeyEvent arg0) {}
		});

		channelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = channelButton.getActionCommand();
				if(command == "Channel"){
					//get new channel
					String channel = channelInput.getText();
					if(channel.length() > 0 ){
						client.handleMessageFromClientUI("#setchannel "+ channel);
						channelInput.setText("");
						chatTabbedPane.setSelectedIndex(0);
					}	
				}
			}
		});

		setPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = setPortButton.getActionCommand();
				if(command == "Set Port"){
					//get new port
					String port = setPortInput.getText();
					if(port.length() > 0 ){
						client.handleMessageFromClientUI("#setport " + port);
						chatTabbedPane.setSelectedIndex(0);
						setPortInput.setText("");
					}
				}
			}
		});

		getCurrentPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = getCurrentPort.getActionCommand();
				if(command == "Get Port"){
					client.handleMessageFromClientUI("#getport");
					chatTabbedPane.setSelectedIndex(0);
				}
			}
		});

		setHostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = setHostButton.getActionCommand();
				if(command == "Set Host"){
					//get new host
					String host = setHostInput.getText();
					if(host.length() > 0 ){
						client.handleMessageFromClientUI("#sethost " + host);
						chatTabbedPane.setSelectedIndex(0);
						setHostInput.setText("");
					}
				}
			}
		});

		getCurrentHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = getCurrentHost.getActionCommand();
				if(command == "Get Host"){
					client.handleMessageFromClientUI("#gethost");
					chatTabbedPane.setSelectedIndex(0);
				}
			}
		});


		myBlocksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = myBlocksButton.getActionCommand();
				if(command == "My Blocks"){
					client.handleMessageFromClientUI("#whoiblock");
					chatTabbedPane.setSelectedIndex(0);
				}
			}
		});

		blocksMeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = blocksMeButton.getActionCommand();
				if(command == "Blocks Me"){
					client.handleMessageFromClientUI("#whoblocksme");
					chatTabbedPane.setSelectedIndex(0);
				}
			}
		});

		blockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = blockButton.getActionCommand();
				if(command == "Block"){
					//get blockee
					String blockee = blockInput.getText();
					if(blockee.length() > 0 ){
						client.handleMessageFromClientUI("#block "+ blockee);
						blockInput.setText("");
						chatTabbedPane.setSelectedIndex(0);
					}
				}
			}
		});

		unblockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = unblockButton.getActionCommand();
				if(command == "UnBlock"){
					//get unblockee
					String unblockee = unblockInput.getText();
					client.handleMessageFromClientUI("#unblock "+ unblockee);
					unblockInput.setText("");
					chatTabbedPane.setSelectedIndex(0);
				}
			}
		});

		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = forwardButton.getActionCommand();
				if(command == "Forward"){
					//get new monitor
					String monitor = forwardInput.getText();
					if(monitor.length() > 0 ){				
						client.handleMessageFromClientUI("#forward "+ monitor);
						forwardInput.setText("");
						chatTabbedPane.setSelectedIndex(0);
					}
				} else if (command == "Return"){
					client.handleMessageFromClientUI("#endforward");
				}
			}
		});

		avalibilityCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(avalibilityCheckBox.isSelected()){
					client.handleMessageFromClientUI("#notavailable");
					forwardButton.setEnabled(false);
					sendButton.setEnabled(false);
				} else {
					client.handleMessageFromClientUI("#available");
					forwardButton.setEnabled(true);
					sendButton.setEnabled(true);
				}
			}
		});

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = sendButton.getActionCommand();
				if(command == "Send"){
					String message = chatTextInput.getText();
					if(message != null && message.length() > 0){
						client.handleMessageFromClientUI(message);
						chatTextInput.setText("");
					}
				}
			}
		});



		//Display the window.
		chatFrame.pack();
		chatFrame.setVisible(true);

	}

	private static void createAndShowGUI(String[] args) {

		//Get loginId
		String id = "";
		try {
			id = args[0];
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR - No login ID specified. Connection aborted.");
			System.exit(0);
		}

		//Get loginId
		String pw = "";
		try {
			pw = args[1];
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR - No password specified. Connection aborted.");
			System.exit(0);
		}

		//Get host
		String host = "";
		try {
			host = args[2];
		} catch(ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}

		//Get port
		int port = 0;
		try {
			port = Integer.parseInt(args[3]); 
		} catch(Throwable t) {
			port = DEFAULT_PORT; //Set port to 5555
		}

		ClientGUIOld chatWindow = new ClientGUIOld(id, pw, host, port);
	}

	public static void main(String[] args) {
		final String[] arguments = args;
		try {
			//Try to set look/feel to 'Nimbus' style.
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {} // Do nothing, use default look/feel

		//create/show application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(arguments);
			}
		});
	}
}
