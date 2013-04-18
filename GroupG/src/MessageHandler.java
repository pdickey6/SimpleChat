import java.util.Observable;
import java.util.Observer;


public class MessageHandler implements Observer {

	public MessageHandler(Observable client){
		client.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object msg) {

		if(msg instanceof String){
			//process message
			o.notifyObservers(msg);
		}
		
	}

}
