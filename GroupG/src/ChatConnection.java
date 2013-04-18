import java.io.IOException;
import java.net.Socket;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;


public class ChatConnection extends ConnectionToClient {

	protected ChatConnection(ThreadGroup group, Socket socket, AbstractServer server) throws IOException {
		super(group, socket, server);
	}

	@Override
	protected boolean handleMessageFromClient(Object message)
	{
		//if true server's handle message from client also gets called...ie message passes through like in p2
		return true;
	}

}
