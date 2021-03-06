package inputport.rpc.duplex;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;

public class ADuplexCallTrapperFactory implements TrapperFactory<Object, Object> {

	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		return new ADuplexCallSendTrapper(anInputPort, aDestination);
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) {
		return new ADuplexCallReceiveTrapper(anInputPort, aReceiveNotifier);
	}
	

}
