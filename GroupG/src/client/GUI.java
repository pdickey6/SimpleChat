/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


/**
 *
 * @author Sean
 */
public class GUI implements Observer{

	//Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	//Instance Variables***********************************************

	// Variables declaration - do not modify//GEN-BEGIN:variables

	ChatClient client;
	JFrame chatFrame;
	private JLabel titleLabel;
	private javax.swing.JPanel ChatWindow;
	private javax.swing.JPanel SettingsWindow;
	private javax.swing.JButton Send;
	private javax.swing.JButton ForwardButton;
	private JButton GetHost;
	private JButton GetPort;
	private javax.swing.JButton SetChannelButton;
	private javax.swing.JButton BlockButton;
	private javax.swing.JButton UnblockButton;
	private javax.swing.JButton WhoIBlockButton;
	private javax.swing.JButton WhoBlocksMeButton;
	private javax.swing.JButton SetHostButton;
	private javax.swing.JButton SetPortButton;
	private javax.swing.JPanel ChatSettingsTab;
	private javax.swing.JPanel BlockSettingsTab;
	private javax.swing.JPanel ServerSettingsTab;
	private javax.swing.JScrollPane ChatDisplay;
	private javax.swing.JTabbedPane MainWindowTabs;
	private javax.swing.JTabbedPane SettingsWindowTabs;
	private javax.swing.JTextArea MessageDisplay;
	private javax.swing.JTextField MessageInput;
	private javax.swing.JTextField ForwardTo;
	private javax.swing.JTextField ChannelToSetTo;
	private javax.swing.JTextField UserToChangeBlock;
	private javax.swing.JTextField HostInput;
	private javax.swing.JTextField PortInput;
	private javax.swing.JToggleButton Availability;

	//Constructors ****************************************************

	/**
	 * Creates new form GUI
	 */
	public GUI(String loginID, String password, String host, int port) {
		try 
		{
			client= new ChatClient(loginID, password, host, port, this);
		} catch(IOException exception) 
		{
			System.out.println("Cannot open connection. Awaiting command.");
			//System.exit(1);
		}
		client.addObserver(this);
		SetupUI(loginID);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	private void SetupUI(String loginID) {

		chatFrame = new JFrame("Chat " + loginID);
		MainWindowTabs = new javax.swing.JTabbedPane();
		ChatWindow = new javax.swing.JPanel();
		ChatDisplay = new javax.swing.JScrollPane();
		MessageDisplay = new javax.swing.JTextArea();
		MessageDisplay.setEditable(false);
		MessageInput = new javax.swing.JTextField();
		Send = new javax.swing.JButton();
		Send.setBackground(Color.decode("#16A085"));
		Availability = new javax.swing.JToggleButton();
		Availability.setBackground(Color.decode("#2ECC71"));
		SettingsWindow = new javax.swing.JPanel();
		SettingsWindowTabs = new javax.swing.JTabbedPane();
		BlockSettingsTab = new javax.swing.JPanel();
		UserToChangeBlock = new javax.swing.JTextField();
		BlockButton = new javax.swing.JButton();
		UnblockButton = new javax.swing.JButton();
		WhoIBlockButton = new javax.swing.JButton();
		WhoBlocksMeButton = new javax.swing.JButton();
		ServerSettingsTab = new javax.swing.JPanel();
		HostInput = new javax.swing.JTextField();
		SetHostButton = new javax.swing.JButton();
		PortInput = new javax.swing.JTextField();
		SetPortButton = new javax.swing.JButton();

		//Customize Form
		titleLabel = new JLabel("Chat " + loginID);

		chatFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		MainWindowTabs.setBackground(new java.awt.Color(44, 62, 80));
		MainWindowTabs.setToolTipText("");
		MainWindowTabs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		MainWindowTabs.setMinimumSize(new java.awt.Dimension(80, 44));
		MainWindowTabs.setOpaque(true);

		ChatWindow.setBackground(new java.awt.Color(52, 73, 94));
		ChatWindow.setMinimumSize(new java.awt.Dimension(70, 70));
		ChatWindow.setName(""); // NOI18N

		MessageDisplay.setBackground(new java.awt.Color(127, 140, 141));
		MessageDisplay.setColumns(20);
		MessageDisplay.setRows(5);
		ChatDisplay.setViewportView(MessageDisplay);
		MessageInput.setBackground(new java.awt.Color(127, 140, 141));
		MessageInput.setText("Message");
		MessageInput.setToolTipText("");

		Send.setText("Send");

		Availability.setText("Available");

		javax.swing.GroupLayout ChatWindowLayout = new javax.swing.GroupLayout(ChatWindow);
		ChatWindow.setLayout(ChatWindowLayout);
		ChatWindowLayout.setHorizontalGroup(
				ChatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(ChatWindowLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(ChatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(ChatWindowLayout.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(Availability, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChatWindowLayout.createSequentialGroup()
												.addComponent(MessageInput, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(ChatDisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addContainerGap(9, Short.MAX_VALUE))
				);
		ChatWindowLayout.setVerticalGroup(
				ChatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(ChatWindowLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(ChatDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(ChatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(MessageInput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(Availability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(64, 64, 64))
				);

		MainWindowTabs.addTab("Chat", ChatWindow);

		SettingsWindow.setBackground(new java.awt.Color(44, 62, 80));

		SettingsWindowTabs.setBackground(new java.awt.Color(44, 62, 80));
		ChatSettingsTab = new javax.swing.JPanel();
		ForwardTo = new javax.swing.JTextField();
		ChannelToSetTo = new javax.swing.JTextField();
		ForwardButton = new javax.swing.JButton();
		SetChannelButton = new javax.swing.JButton();

		ChatSettingsTab.setBackground(new java.awt.Color(52, 73, 94));

		ForwardTo.setBackground(new java.awt.Color(127, 140, 141));
		ForwardTo.setText("Name of User to Forward to");

		ChannelToSetTo.setBackground(new java.awt.Color(127, 140, 141));
		ChannelToSetTo.setText("Channel to Connect");

		ForwardButton.setText("Forward");

		SetChannelButton.setText("Set Channel");

		javax.swing.GroupLayout gl_ChatSettingsTab = new javax.swing.GroupLayout(ChatSettingsTab);
		gl_ChatSettingsTab.setHorizontalGroup(
			gl_ChatSettingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChatSettingsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ChatSettingsTab.createParallelGroup(Alignment.LEADING)
						.addComponent(ChannelToSetTo, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(ForwardTo, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_ChatSettingsTab.createParallelGroup(Alignment.LEADING, false)
						.addComponent(SetChannelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ForwardButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(139))
		);
		gl_ChatSettingsTab.setVerticalGroup(
			gl_ChatSettingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChatSettingsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ChatSettingsTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(ForwardTo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(ForwardButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ChatSettingsTab.createParallelGroup(Alignment.LEADING)
						.addComponent(SetChannelButton)
						.addComponent(ChannelToSetTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(406, Short.MAX_VALUE))
		);
		ChatSettingsTab.setLayout(gl_ChatSettingsTab);

		SettingsWindowTabs.addTab("Chat", ChatSettingsTab);

		BlockSettingsTab.setBackground(new java.awt.Color(52, 73, 94));

		UserToChangeBlock.setBackground(new java.awt.Color(127, 140, 141));
		UserToChangeBlock.setText("Username");

		BlockButton.setText("Block");

		UnblockButton.setText("Unblock");

		WhoIBlockButton.setText("Blocked Users");

		WhoBlocksMeButton.setText("Who Blocks Me?");

		javax.swing.GroupLayout gl_BlockSettingsTab = new javax.swing.GroupLayout(BlockSettingsTab);
		gl_BlockSettingsTab.setHorizontalGroup(
			gl_BlockSettingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BlockSettingsTab.createSequentialGroup()
					.addContainerGap()
					.addComponent(UserToChangeBlock, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_BlockSettingsTab.createParallelGroup(Alignment.LEADING, false)
						.addComponent(WhoIBlockButton, 0, 0, Short.MAX_VALUE)
						.addComponent(BlockButton, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_BlockSettingsTab.createParallelGroup(Alignment.LEADING, false)
						.addComponent(UnblockButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(WhoBlocksMeButton, GroupLayout.PREFERRED_SIZE, 133, Short.MAX_VALUE))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		gl_BlockSettingsTab.setVerticalGroup(
			gl_BlockSettingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BlockSettingsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_BlockSettingsTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(UserToChangeBlock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BlockButton)
						.addComponent(UnblockButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_BlockSettingsTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(WhoIBlockButton)
						.addComponent(WhoBlocksMeButton))
					.addContainerGap(367, Short.MAX_VALUE))
		);
		BlockSettingsTab.setLayout(gl_BlockSettingsTab);

		SettingsWindowTabs.addTab("Block", BlockSettingsTab);

		ServerSettingsTab.setBackground(new java.awt.Color(52, 73, 94));

		HostInput.setBackground(new java.awt.Color(127, 140, 141));
		HostInput.setText("Host");

		SetHostButton.setText("Set Host");

		PortInput.setBackground(new java.awt.Color(127, 140, 141));
		PortInput.setText("Port");

		SetPortButton.setText("Set Port");
		GetHost = new JButton("Get Host");
		GetPort = new JButton("Get Port");

		javax.swing.GroupLayout gl_ServerSettingsTab = new javax.swing.GroupLayout(ServerSettingsTab);
		gl_ServerSettingsTab.setHorizontalGroup(
				gl_ServerSettingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ServerSettingsTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_ServerSettingsTab.createParallelGroup(Alignment.LEADING, false)
								.addComponent(PortInput)
								.addComponent(HostInput, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_ServerSettingsTab.createParallelGroup(Alignment.LEADING, false)
										.addComponent(SetPortButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(SetHostButton, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_ServerSettingsTab.createParallelGroup(Alignment.LEADING)
												.addComponent(GetPort, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
												.addComponent(GetHost, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
												.addContainerGap())
				);
		gl_ServerSettingsTab.setVerticalGroup(
				gl_ServerSettingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ServerSettingsTab.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_ServerSettingsTab.createParallelGroup(Alignment.BASELINE)
								.addComponent(HostInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(SetHostButton)
								.addComponent(GetHost))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_ServerSettingsTab.createParallelGroup(Alignment.BASELINE)
										.addComponent(PortInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(SetPortButton)
										.addComponent(GetPort))
										.addContainerGap(406, Short.MAX_VALUE))
				);
		ServerSettingsTab.setLayout(gl_ServerSettingsTab);

		SettingsWindowTabs.addTab("Server", ServerSettingsTab);

		javax.swing.GroupLayout SettingsWindowLayout = new javax.swing.GroupLayout(SettingsWindow);
		SettingsWindow.setLayout(SettingsWindowLayout);
		SettingsWindowLayout.setHorizontalGroup(
				SettingsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(SettingsWindowLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(SettingsWindowTabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(11, Short.MAX_VALUE))
				);
		SettingsWindowLayout.setVerticalGroup(
				SettingsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(SettingsWindowLayout.createSequentialGroup()
						.addComponent(SettingsWindowTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
						.addContainerGap())
				);

		MainWindowTabs.addTab("Settings", SettingsWindow);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(chatFrame.getContentPane());
		chatFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(MainWindowTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(MainWindowTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
				);

		//##################### Listeners #####################

		//display scrollbar vertical update
		ChatDisplay.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent event) {  
				event.getAdjustable().setValue(event.getAdjustable().getMaximum());  
			}
		});

		MessageInput.addKeyListener( new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int keyCode = arg0.getKeyCode();
				if(keyCode == 10){ //enter key
					String message = MessageInput.getText();
					if(message != null && message.length() > 0){
						client.handleMessageFromClientUI(message);
						MessageInput.setText("");
						arg0.consume();
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent arg0) {}
			@Override
			public void keyReleased(KeyEvent arg0) {}
		});

		SetChannelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = SetChannelButton.getActionCommand();
				if(command == "Set Channel"){
					//get new channel
					String channel = ChannelToSetTo.getText();
					if(channel.length() > 0 ){
						client.handleMessageFromClientUI("#setchannel "+ channel);
						ChannelToSetTo.setText("");
						MainWindowTabs.setSelectedIndex(0);
					}	
				}
			}
		});

		SetPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = SetPortButton.getActionCommand();
				if(command == "Set Port"){
					//get new port
					String port = PortInput.getText();
					if(port.length() > 0 ){
						client.handleMessageFromClientUI("#setport " + port);
						MainWindowTabs.setSelectedIndex(0);
						PortInput.setText("");
					}
				}
			}
		});

		GetPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = GetPort.getActionCommand();
				if(command == "Get Port"){
					client.handleMessageFromClientUI("#getport");
					MainWindowTabs.setSelectedIndex(0);
				}
			}
		});

		SetHostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = SetHostButton.getActionCommand();
				if(command == "Set Host"){
					//get new host
					String host = HostInput.getText();
					if(host.length() > 0 ){
						client.handleMessageFromClientUI("#sethost " + host);
						MainWindowTabs.setSelectedIndex(0);
						HostInput.setText("");
					}
				}
			}
		});

		GetHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = GetHost.getActionCommand();
				if(command == "Get Host"){
					client.handleMessageFromClientUI("#gethost");
					MainWindowTabs.setSelectedIndex(0);
				}
			}
		});


		WhoIBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = WhoIBlockButton.getActionCommand();
				if(command == "Blocked Users"){
					client.handleMessageFromClientUI("#whoiblock");
					MainWindowTabs.setSelectedIndex(0);
				}
			}
		});

		WhoBlocksMeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = WhoBlocksMeButton.getActionCommand();
				if(command == "Who Blocks Me?"){
					client.handleMessageFromClientUI("#whoblocksme");
					MainWindowTabs.setSelectedIndex(0);
				}
			}
		});

		BlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = BlockButton.getActionCommand();
				if(command == "Block"){
					//get blockee
					String blockee = UserToChangeBlock.getText();
					if(blockee.length() > 0 ){
						client.handleMessageFromClientUI("#block "+ blockee);
						UserToChangeBlock.setText("");
						MainWindowTabs.setSelectedIndex(0);
					}
				}
			}
		});

		UnblockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = UnblockButton.getActionCommand();
				if(command == "UnBlock"){
					//get unblockee
					String unblockee = UserToChangeBlock.getText();
					client.handleMessageFromClientUI("#unblock "+ unblockee);
					UserToChangeBlock.setText("");
					MainWindowTabs.setSelectedIndex(0);
				}
			}
		});

		ForwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = ForwardButton.getActionCommand();
				if(command == "Forward"){
					//get new monitor
					String monitor = ForwardTo.getText();
					if(monitor.length() > 0 ){				
						client.handleMessageFromClientUI("#forward "+ monitor);
						ForwardTo.setText("");
						MainWindowTabs.setSelectedIndex(0);
					}
				} else if (command == "Stop Forwarding"){
					client.handleMessageFromClientUI("#endforward");
				}
			}
		});

		Availability.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(Availability.isSelected()){
					client.handleMessageFromClientUI("#notavailable");
					Availability.setText("Unavailable");
					Availability.setBackground(Color.decode("#E74C3C"));
					ForwardButton.setEnabled(false);
					Send.setEnabled(false);
				} else {
					client.handleMessageFromClientUI("#available");
					Availability.setText("Available");
					Availability.setBackground(Color.decode("#2ECC71"));
					ForwardButton.setEnabled(true);
					Send.setEnabled(true);
				}
			}
		});

		Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = Send.getActionCommand();
				if(command == "Send"){
					String message = MessageInput.getText();
					if(message != null && message.length() > 0){
						client.handleMessageFromClientUI(message);
						MessageInput.setText("");
					}
				}
			}
		});
		




		//Display the window.
		chatFrame.pack();
		chatFrame.setVisible(true);
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		final String[] arguments = args;
		/* Set the Nimbus look and feel */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {} // use Default loof/feel
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(arguments);
			}
		});
	}

	protected static void createAndShowGUI(String[] args) {
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

		@SuppressWarnings("unused")
		GUI chatWindow = new GUI(id, pw, host, port);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof String){
			String message = arg1.toString();
			if(message.startsWith("Forwarding: ")){
				ForwardButton.setText("Stop Forwarding");
				SetChannelButton.setEnabled(false);
				BlockButton.setEnabled(false);
				Send.setEnabled(false);
				MessageInput.setEnabled(false);
				MessageDisplay.setEnabled(false);
				Availability.setEnabled(false);
			} else if (message.startsWith("End Forwarding:")){
				ForwardButton.setText("Forward");
				SetChannelButton.setEnabled(true);
				BlockButton.setEnabled(true);
				Send.setEnabled(true);
				MessageInput.setEnabled(true);
				MessageDisplay.setEnabled(true);
				Availability.setEnabled(true);
			}

			if(!message.substring( message.length()-1, message.length()).equals("\n"))
				message += "\n";

			//Update UI display safely
			final String msg = message;
			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run()
				{
					MessageDisplay.append(msg) ;
				}
			});
		}
	}
}
