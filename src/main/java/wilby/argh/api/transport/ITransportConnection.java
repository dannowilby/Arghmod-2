package wilby.argh.api.transport;

import java.util.ArrayList;

public interface ITransportConnection extends ITransportHandler
{
	
	ArrayList<Object> getTransportObjects();
	boolean setTransportObjects(ArrayList<Object> toSet);
	
	Object addTransportObject(Object toTransfer);
	
}
