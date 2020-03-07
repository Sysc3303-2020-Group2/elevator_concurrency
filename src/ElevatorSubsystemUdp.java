import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ElevatorSubsystemUdp extends Thread {

	private DatagramSocket sendSocket, receiveSocket;
	private DatagramPacket sendPacket, receivePacket;

	public ElevatorSubsystemUdp(String name) {
		super(name);
	}

	/***
	 * Initialize socket and bind it to port 3001
	 */
	public void receiveSchedulerRequest() {

		try {

			// Initialize socket and listen to port 3001
			this.receiveSocket = new DatagramSocket(3001);

			// Construct a DatagramPacket for receiving packets up
			// to 100 bytes long
			byte data[] = new byte[100];
			receivePacket = new DatagramPacket(data, data.length);
			System.out.println("Elevator: Waiting for request.");

			receiveSocket.receive(receivePacket);
			// Process the received datagram.
			int len = receivePacket.getLength();

			// Form a String from the byte array.
			String received = new String(data, 0, len);
			System.out.print("Packet received contaning:\t");
			System.out.println(received);

			// We're finished, so close the sockets.
			receiveSocket.close();
		} catch (SocketException se) { // Can't create the socket.
			se.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.out.print("IO Exception: likely:");
			System.out.println("Receive Socket Timed Out.		" + e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void sendRequestToScheduler(byte[] data) {

		try {
			// initialize the socket and packet from the received data
			this.sendSocket = new DatagramSocket();
			sendPacket = new DatagramPacket(data, receivePacket.getLength(), receivePacket.getAddress(),
					receivePacket.getPort());
			System.out.print("Containing: ");
			System.out.println(new String(sendPacket.getData(), 0, sendPacket.getLength()));
			sendSocket.send(sendPacket);

			// Slow things down (wait 2 seconds)
			Thread.sleep(2000);

			System.out.println("Server: packet sent");

			// We're finished, so close the sockets.
			receiveSocket.close();
			sendSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void run() {
		this.receiveSchedulerRequest();
	}

	public static void main(String[] args) {

		ElevatorSubsystemUdp elev = new ElevatorSubsystemUdp("Elevator");
		elev.start();

	}

}
