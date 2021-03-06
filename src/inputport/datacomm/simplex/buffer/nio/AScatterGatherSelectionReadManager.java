package inputport.datacomm.simplex.buffer.nio;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



public class AScatterGatherSelectionReadManager extends ASelectionReadManager   {
//	public static final int BYTES_IN_INT =  Integer.SIZE/Byte.SIZE;
	BlockingQueue<HeaderWriteCommand> headerBufferPool = new ArrayBlockingQueue<HeaderWriteCommand>(AScatterGatherSelectionManager.getMAX_OUTSTANDING_WRITES());


	int messageLength;
//	public static final int MAX_OUTSTANDING_WRITES = 256*4*4; // 4K buffers should be enough for even mouse movements

	public AScatterGatherSelectionReadManager(MessagingSelectingRunnable aSelectionManager) {
		super(aSelectionManager);		
	}

	@Override
	public ReadCommand createReadHandler(SocketChannel theSocketChannel) {
		return new AScatterGatherReadCommand(selectionManager, theSocketChannel);
	}
	
	






}
