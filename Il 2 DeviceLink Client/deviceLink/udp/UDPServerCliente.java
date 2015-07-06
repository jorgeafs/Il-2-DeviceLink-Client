package udp;

/**UDPserverClient:
 * Da soporte UDP al resto del programa. Su unica funcion es mandar y recibir paquetes UDP
 * 
 * Propiedades:
 * 		socket --> basica, datagramsocket, modificable y consultable
 * 		host --> basica, InetAddress, consultable
 * 
 * Interfaz:
 * 	public DatagramSocket getSocket()
 * 	public InetAddress getHost()
 * 	public void setSocket(DatagramSocket socket) 
 * 
 * Metodos propios:
 * 	public String recibir()
 */

import java.net.*;
public class UDPServerCliente {
	//propiedades
	private DatagramSocket socket;
	private InetAddress host;
	
	//constructor
	public UDPServerCliente(int socket) {
		try {
			this.host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("Ocurrio un error en la creacion del server");
		}
		try {
			this.socket = new DatagramSocket(socket, this.host );
		} catch (SocketException e) {
			System.out.println("Ocurrio un error en la creacion del server");
		}
	}

	//getters
	
	public DatagramSocket getSocket() {
		return socket;
	}

	public InetAddress getHost() {
		return host;
	}
	
	//set

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	//metodos propios
	/**Interfaz recibir
	 * Cabecera: public String recibir()
	 * Comentario: 
	 */
	public String recibir(){
		String recibido = null;
		return recibido;
	}
}
