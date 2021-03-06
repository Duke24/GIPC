package inputport.rpc;



import inputport.rpc.simplex.SimplexRPC;

import java.lang.reflect.Method;

import util.trace.Tracer;

public  class AnRPCProxyInvocationHandler extends ACachingAbstractRPCProxyInvocationHandler {	
	public AnRPCProxyInvocationHandler (SimplexRPC theRPCPort, String aDestination, Class aClass, String aName ) {
		super(theRPCPort, aDestination, aClass, aName);
	}	
	@Override
	protected Object call(String aDestination, String aName, Method aMethod, Object[] args) {
		Object retVal = rpcInputPort.call(aDestination, aName, aMethod, args);
		return retVal;
	}
	@Override
	protected Object call(String aDestination, Method aMethod, Object[] args) {
		Object retVal = rpcInputPort.call(aDestination, aMethod, args);
		return retVal;
	}	
	@Override
	protected Object call(String aDestination, Class aClass, Method method, Object[] args) {
//		Object retVal = rpcInputPort.call(destination, remoteType, method,  args);
		Tracer.info(this, "Invoking call method with destination:" + destination + " method:" + method + " args:" + args);
		Object retVal = rpcInputPort.call(aDestination, aClass, method,  args);
		return retVal;
	}
}
