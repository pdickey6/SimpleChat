// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
//Edited by Patrick Dickey and Sean Jergensen

package client;

import com.lloseng.ocsf.client.*;
import common.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observer;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends ObservableClient
{
	//Instance variables **********************************************

	/**
	 * The interface type variable.  It allows the implementation of 
	 * the display method in the client.
	 */
	private Observer clientUI;
	private String loginId;
	private String password;
	private String monitor;
	private Boolean connected;  
	private Boolean isForwarding;	

	//Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host The server to connect to.
	 * @param port The port number to connect on.
	 * @param clientUI The interface type variable.
	 */  
	public ChatClient(Observer UI){
		super("localhost",5555);
		clientUI = UI;
		connected = false;
		isForwarding = false;
	}

	public ChatClient(String id, String pw, String host, int port, Observer UI) throws IOException {
		super(host, port); //Call the superclass constructor
		clientUI = UI;
		loginId = id;
		password = pw;
		isForwarding = false;
		
		openConnection();
		try {
			sendToServer("#login " + loginId + " " + password);
		} catch (IOException e) {
			notifyObservers("ERROR - No login ID specified. Connection aborted.");
			//clientUI.display("ERROR - No login ID specified. Connection aborted.");
		}
	}


	//Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) 
	{
		String message = msg.toString();
		if(!message.startsWith("#")){
			if(!isForwarding){
				setChanged();
				notifyObservers(message);
			}
			else {
				//In meeting so forward msg to monitor
				try {					
					sendToServer("#forward_message " + monitor + " " + message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Unable to forward message to server.");
				}
			}
		} else { //command
			int cmdEnd = message.indexOf(' ');
			if (cmdEnd < 1) 
				cmdEnd = message.length();
			String cmd = message.substring(1, cmdEnd);
			//clientUI.display(cmd);

			//Switch based on user command
			switch (cmd.toLowerCase()) {
			case "meeting":
				monitor = message.substring(cmdEnd+1);
				isForwarding = true;
				setChanged();
				
				notifyObservers("In meeting: " + monitor + " will now receive your messages. When you return type #endmeeting to cancel forwarding.");
				break;
			case "forward":
				monitor = message.substring(cmdEnd+1);
				isForwarding = true;
				setChanged();
				notifyObservers("Forwarding: " + monitor + " will now receive your messages. When you return type #endforward to cancel forwarding.");
				break;
			case "endforward":
				if (isForwarding) {
					isForwarding = false;
					setChanged();
					notifyObservers("End Forwarding: No longer forwarding messages to " + monitor + ".");
					monitor = "";
					break;
				} else {
					notifyObservers("Error: You were not forwarding messages.");
					break;
				}
			case "forwardblocked":
				isForwarding = false;
				setChanged();
				notifyObservers("Forwarding to " + monitor + " has been canceled because " + monitor + " is blocking messages from you.");
				monitor = "";
				break;
			case "endmeeting":
				if (isForwarding) {
					isForwarding = false;
					setChanged();
					notifyObservers("End Forwarding: No longer forwarding messages to " + monitor + ".");
					monitor = "";
					break;
				} else {
					setChanged();
					notifyObservers("Error: You were not forwarding messages.");
					break;
				}
			default:
				setChanged();
				notifyObservers("Command from server not recognized. " + cmd);
			}
		}
	}

	/**
	 * This method handles all data coming from the UI            
	 *
	 * @param message The message from the UI.    
	 */
	public void handleMessageFromClientUI(String message)
	{
		if(!message.startsWith("#"))//message
		{
			try {
				sendToServer(message);
			} catch(IOException e) {
				setChanged();
				notifyObservers("Could not send message to server. Terminating client.");
				quit();
			}
		} else { //command
			int cmdEnd = message.indexOf(' ');
			if (cmdEnd < 1) 
				cmdEnd = message.length();
			String cmd = message.substring(1, cmdEnd);

			//Switch based on user command
			switch (cmd.toLowerCase()) {
			case "quit" :
				quit();
				break;
			case "logoff" :
				if(!connected){
					setChanged();
					notifyObservers("You are already logged off.");
				}
				else {
					try {
						closeConnection();
						connected = false;
						setChanged();
						notifyObservers("Connection closed.");
					} catch (IOException e) {
						setChanged();
						notifyObservers("Unable to logoff.");
					}
				}
				break;
			case "login" :
				if(connected)
					notifyObservers("You are already logged in.");
				else {
					try {
						openConnection();
						sendToServer("#login " + loginId);
						connected = true;
					} catch (IOException e) {
						setChanged();
						notifyObservers("Unable to login.");
					}
				}
				break;
			case "sethost" :
				if(connected){
					setChanged();
					notifyObservers("Cannot set host while connected to server.");
					}
				else {
					String host = message.substring(cmdEnd +1, message.length());
					if(host.length() > 0 ) {
						setHost(host);
						setChanged();
						notifyObservers("Host set to: " + host);
					} else {
						setChanged();
						notifyObservers("Host could not be set");
					}
				}
				break;
			case "setport" :
				if(connected){
					setChanged();
					notifyObservers("Cannot set port while connected to server.");
				}
				else {
					try{
						int port = Integer.parseInt(message.substring(cmdEnd +1, message.length()));
						setPort(port);
						setChanged();
						notifyObservers("Port set to: " + port);
					}catch (NumberFormatException e){
						setChanged();
						notifyObservers("Port could not be set");
					}					
				}
				break;
			case "gethost" :
				setChanged();
				notifyObservers("Current Host: " + getHost());				
				break;
			case "getport" :
				setChanged();
				notifyObservers("Current Port: " + getPort());				
				break;
			case "block" :
				try{
					sendToServer(message);

				} catch (IOException e) {
					setChanged();
					notifyObservers("Messages could not be blocked.");
				}
				break;
			case "unblock" :
				try {
					sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Messages could not be unblocked.");
				}
				break;
			case "whoiblock" :
				try{
					sendToServer(message);
				} catch (IOException e){
					setChanged();
					notifyObservers("Could not get list of blocked users.");
				}
				break;
			case "whoblocksme" :
				try {
					sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Block list could not be retrived.");
				}
				break;
			case "setchannel":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Channel could not be set.");
				}
				break;
			case "private":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Could not send private message.");
				}
				break;
			case "meeting" :
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Could not initiate meeting.");
				}				
				break;
			case "endmeeting":
				isForwarding = false;
				break;
			case "status":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Status could not be set.");
				}
				break;
			case "available":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Could not change status.");
				}				
				break;
			case "notavailable":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Could not change status.");
				}	
				break;
			case "forward":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Could not forward messages.");
				}	
				break;
			case "endforward":
				try {sendToServer(message);
				} catch (IOException e) {
					setChanged();
					notifyObservers("Could not stop forwarding messages.");
				}
				break;
			default: 
				setChanged();
				notifyObservers("Command not recognized.");
			}
		}
	}

	/**
	 * Called when the connection to the server is closed.
	 */
	protected void connectionException(){
		connected = false;
		notifyObservers("Abnormal termination of connection");
	}

	/**
	 * Called when the connection to the server is closed.
	 */
	protected void connectionClosed(){
		try {
			closeConnection();
		} catch (IOException e) {

		}
		connected = false;
	}

	protected void connectionEstablished(){	  
		connected = true;
	}

	/**
	 * This method terminates the client.
	 */
	public void quit()
	{
		if(connected){
			try
			{
				closeConnection(); 
				connected = false;
			}
			catch(IOException e) {}
		}
		System.exit(0);
	}

	public Boolean isForwarding() {
		return isForwarding;
	}

	public String getMonitor() {
		return monitor;
	}

}
//End of ChatClient class