import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread{

	
	Socket socketClient;
	Wordguess clientinfo;
	int port = 0;
	ObjectOutputStream out;
	ObjectInputStream in;
	String ip1;
	int clientnum;
	boolean clientoneonserver;
	private Consumer<Serializable> callback;
	Client(int port2, String ip)
	{
		port = port2;
		ip1 = ip;
	}
	Client(Consumer<Serializable> call, int port2, String ip){
		callback = call;
		port = port2;
		ip1 = ip;
		clientnum = 0;
		clientinfo = new Wordguess();
		clientoneonserver = false;
	}
	public String getip()
	{
		return ip1;
	}
	public int getport()
	{
		return port;
	}
	public void run() {
		
		try {
		socketClient= new Socket(getip(), getport());
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
	    clientnum = (int)in.readObject();
		}
		catch(Exception e) {}
		while(true) {
			
			try {
					//recieve object from server
				    Object object = in.readObject();
					if(object instanceof Wordguess)
					{
						//convert object from server to MorraInfo clientinfo2
						//set clientinfo to clientinfo2
						Wordguess clientinfo2 = new Wordguess();
						clientinfo2 = (Wordguess)object; 
						//send clientinfo2 to clientgui
						callback.accept(clientinfo2);
					}
					else
					{
						//convert object from server to string
						String message = object.toString();
						//send message to clientgui
						callback.accept(message);
					}
			}
			catch(Exception e) {}
		}
	
    }
	//reset clientinfo
	public void reset()
	{
	}
	//send string data to server
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//send MorraInfo clientinfo1 to server
	public void send(Wordguess clientinfo1)
	{
		try {
			out.writeObject(clientinfo1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
