package inputport.rpc.simplex;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.rpc.RPCRegistry;
import inputport.rpc.ReceivedCallInvoker;
import inputport.rpc.RemoteCall;
import port.trace.AConnectionEvent;
import port.trace.AReplaceConnectionEvent;
import port.trace.CallInitiated;
import port.trace.CallReceived;
import port.trace.ConnectiontEventBus;
import util.trace.Tracer;




public class ASimplexCallReceiveTrapper extends AnAbstractReceiveTrapper<Object, Object> {
	protected RPCRegistry rpcRegistry;
	protected ReceivedCallInvoker serializableCallHandler;
	protected ReceivedCallInvoker receivedCallInvoker;
	public ASimplexCallReceiveTrapper(InputPort anInputPort, ReceiveNotifier aReceiveNotifier) {
		super (anInputPort, aReceiveNotifier);
		rpcRegistry = (RPCRegistry) anInputPort;
//		createReceivedCallInvoker();
//		DistEventsBus.newEvent(new AConnectionEvent(rpcRegistry, this, false, false));

	}
	@Override
	public void notifyPortReceive(String remoteEnd, Object message) {
//		Tracer.info(this, " Processing serialized call:" + message + " from:" + remoteEnd);
		if (message instanceof RemoteCall) {
			Tracer.info(this, " Processing serialized call:" + message + " from:" + remoteEnd);

			RemoteCall aCall = (RemoteCall) message;
			CallReceived.newCase(this, destination, inputPort.getLocalName(), aCall); 

			receivedCallInvoker().messageReceived(remoteEnd,  aCall);
			CallInitiated.newCase(this, destination, inputPort.getLocalName(), aCall); 

		} 
		else
			destination.notifyPortReceive(remoteEnd, message);	 	
		
	}
	protected void sendDistEvent(ReceivedCallInvoker newReceivedCallInvoker) {
		if (receivedCallInvoker == null) {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newReceivedCallInvoker, false));
		} else {
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, receivedCallInvoker, newReceivedCallInvoker, false, true));
		}
	}
	protected  ReceivedCallInvoker createReceivedCallInvoker() {		
		ReceivedCallInvoker newReceivedCallInvoker = new ASimplexReceivedCallInvoker(rpcRegistry);
		sendDistEvent(newReceivedCallInvoker);
//		DistEventsBus.newEvent(new AConnectionEvent(this, newReceivedCallInvoker, false));
		receivedCallInvoker = newReceivedCallInvoker;
		return newReceivedCallInvoker;
	}
	protected  ReceivedCallInvoker receivedCallInvoker() {
		if (serializableCallHandler != null)
			return serializableCallHandler;		
		return createReceivedCallInvoker();
	}
	

}
