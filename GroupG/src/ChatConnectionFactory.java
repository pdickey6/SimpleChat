import java.io.IOException;
import java.net.Socket;

import com.lloseng.ocsf.server.AbstractConnectionFactory;
import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;


public class ChatConnectionFactory extends AbstractConnectionFactory {

	@Override
	protected ConnectionToClient createConnection(ThreadGroup group, Socket socket, AbstractServer server) throws IOException {
		return new ChatConnection(group,socket,server);
	}
}
