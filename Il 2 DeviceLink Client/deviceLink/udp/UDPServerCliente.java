package udp;

/**UDPserverClient:
 * Da soporte UDP al resto del programa. Su unica funcion es mandar y recibir paquetes UDP
 * 
 * Propiedades:
 * 		socket --> basica, datagramsocket, modificable y consultable
 * 		host --> basica, InetAddress, consultable
 * 		tiempoEspera --> basica, entero, modificable y consultable
 * 		tamañoPaquete --> basica, entero, modificable y consultable
 * 
 * Interfaz:
 * 	public DatagramSocket getSocket()
 * 	public InetAddress getHost()
 * 	public void setSocket(DatagramSocket socket) 
 * 
 * Metodos propios:
 * 	public String recibir()
 * 	public boolean mandar(String peticion)
 */

import java.io.*;
import java.net.*;
public class UDPServerCliente implements Closeable {
	//propiedades
	private DatagramSocket socket;
	private InetAddress host;
	private int tiempoEspera; 
	private int tamañoPaquete;
	private int puerto;
	
	//constructor
	public UDPServerCliente(int socket, int tiempoEspera, int tamañoPaquete) {
		try {
			this.host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("Ocurrio un error en la creacion del server");
		}
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println(e);
		}
		this.tiempoEspera = tiempoEspera;
		this.tamañoPaquete = tamañoPaquete;
		this.puerto = socket;
	}

	//getters
	
	public DatagramSocket getSocket() {
		return this.socket;
	}

	public InetAddress getHost() {
		return this.host;
	}
	
	public int getTiempoEspera() {
		return this.tiempoEspera;
	}
	
	public int getTamañoPaquete() {
		return this.tamañoPaquete;
	}
	
	public int getPuerto() {
		return this.puerto;
	}
	
	//set

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public void setTiempoEspera(int nTiempoEspera) {
		this.tiempoEspera = nTiempoEspera;
	}
	
	public void setTamañoPaquete(int nTamañoPaquete) {
		this.tamañoPaquete = nTamañoPaquete;
	}
	
	public void setPuerto(int nPuerto) {
		this.puerto = nPuerto;
	}
	
	//metodos propios
	/**Interfaz recibir
	 * Cabecera: public String recibir()
	 * Comentario: Recibe la respuesta del servidor UDP, si no se obtiene ningun dato devuelve null
	 * Precondiciones: ninguna
	 * Entradas: ninguna
	 * Salidas: un String
	 * Postcondiciones: Devuelve el String mandado por el server y en cualquier otro caso devuelve null
	 */
	public String recibir(){
		String recibido = null;
		DatagramPacket packet = new DatagramPacket(new byte[this.tamañoPaquete], this.tamañoPaquete);
		
		try {
			this.socket.setSoTimeout(tiempoEspera);
			this.socket.receive(packet);
			recibido = new String(packet.getData()).trim();
		} catch (SocketException e) {
			System.out.println("Fallo el acceso al puerto");
		} catch (IOException e) {
			System.out.println("No se recibio el paquete");
		}
		return recibido;
	}
	
	/**Interfaz mandar
	 * Cabecera: public boolean mandar(String peticion)
	 * Comentario: Manda un String al server, si se realiza correctamente devuelve true, en caso contrario devuelve false
	 * Precondiciones: ninguna
	 * Entradas: un String
	 * Salidas: un boolean
	 * Postcondiciones: Devuelve true en caso de mandar correctamente el mensaje y false en caso contrario
	 */
	public boolean mandar(String peticion){
		boolean correcto = false;
		byte [] data = peticion.getBytes(); 
		DatagramPacket packet = new DatagramPacket(data, data.length, this.host, this.puerto);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			System.out.println("Fallo al enviar el paquete");
		}
		return correcto;
	}

	@Override
	public void close(){
		this.socket.close();
	}
}
