package utilidad;
/**UtilFilGen 
 * 	Propiedades:
 * 		aG --> básica, generica, modificable y consultable
 * 	Metodos:
 * 		public boolean crearFicheroBinario (String nombreFichero)
		public boolean borrarFicheroBinario (String nombreFichero)
		@Deprecated
		public boolean crearFicheroTexto (String nombreFichero)
		@Deprecated
		public boolean borrarFicheroTexto (String nombreFichero)
		public boolean copiarFicheroBinario(String ficheroOrigen, String ficheroCopia) 
		@Deprecated
		public boolean copiarFicheroTexto(String ficheroOrigen, String ficheroCopia)
		public boolean escribirRegistroBinario(T introducir, String ficheroIntroducir)
		public boolean escribirMultiplesRegistroBinario(ArrayList<T> introducir, String ficheroIntroducir)
		@Deprecated
		public boolean escribirRegistroTexto(String introducir, String ficheroIntroducir)
		public  ArrayList<T> leerFicheroBinario (String ruta)
		@Deprecated
		public  String leerFicheroTexto (String ruta)
		public boolean ordenacionHibrida(String desordenado, String ordenado)
		private List<T> ordenarArray(String desordenado)
		public ArrayList<T> busqueda (String buscar, String ruta) 
 * 
 * 	Cosas por hacer:
 * 		Lanzar excepcion cuando no se encuentre el archivo a utilizar
 */

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilFileGen<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	//parametro generico
	private T aG;//aG significa apoyoGenerico... pero es mas corto con dos letras
	
	//constructor por defecto
	public UtilFileGen(){
		this.aG = null;
	}
	
	//Gets y sets
	public T getAG(){
		return this.aG;
	}
	public void setAG(T nAG){
		this.aG = nAG;
	}
	
	//Metodos propios
	/**Interfaz crearFicheroBinario
	 * Cabecera: public boolean crearFicheroBinario (UtilidadesFichero<String> nombreFichero)
	 * Comentario: Dado un string crea un fichero con ese nombre en la carpeta activa, devuelve true si se creo correctamente y
	 * 				false si ya existia u ocurrio algun error
	 * Precondiciones: Ninguna
	 * Entrada: Un String
	 * Salida: un boolean
	 * Postcondiciones: Devuelve un true si se crea correctamente y false en caso de error o si ya existia
	*/
	public boolean crearFicheroBinario (String nombreFichero){
		boolean correcto = false;
		File nuevoFichero = new File(nombreFichero+".dat");
		ObjectOutputStream escribir = null;
		
		if(nuevoFichero.isFile() != true){
			try {
				correcto = nuevoFichero.createNewFile();
				FileOutputStream fos = new FileOutputStream (nuevoFichero);
				escribir = new ObjectOutputStream (fos);
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Fallo la creacion");
			} finally {
				try{
					if(escribir != null){ escribir.close();}
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("No se pudo cerrar correctamente");
				}
			}
		}		
		return correcto;
	}
	
	/**Interfaz borrarFicheroBinario
	 * Cabecera: public boolean borrarFicheroBinario (String nombreFichero)
	 * Comentario: Dado un string borra un fichero con ese nombre en la carpeta activa, devuelve true si se borro correctamente y
	 * 				false si no existia u ocurrio algun error
	 * Precondiciones: Ninguna
	 * Entrada: Un String
	 * Salida: un boolean
	 * Postcondiciones: Devuelve un true si se borra correctamente y false en caso de error o si no existia
	*/
	public boolean borrarFicheroBinario (String nombreFichero){
		boolean correcto = false;
		File aBorrar = new File (nombreFichero+".dat");
		if(aBorrar.isFile()){
			aBorrar.delete();
			correcto = true;
		}
		return correcto;
	} 
	/**Interfaz crearFicheroTexto
	 * Cabecera: public boolean crearFicheroTexto (UtilidadesFichero<String> nombreFichero)
	 * Comentario: Dado un string crea un fichero con ese nombre en la carpeta activa, devuelve true si se creo correctamente y
	 * 				false si ya existia u ocurrio algun error
	 * Precondiciones: Ninguna
	 * Entrada: Un String
	 * Salida: un boolean
	 * Postcondiciones: Devuelve un true si se crea correctamente y false en caso de error o si ya existia
	*/
	@Deprecated
	public boolean crearFicheroTexto (String nombreFichero){
		boolean correcto = false;
		/*File nuevoFichero = new File(nombreFichero+".txt");
		ObjectOutputStream escribir = null;
		
		if(nuevoFichero.isFile() != true){
			try {
				correcto = nuevoFichero.createNewFile();
				FileOutputStream fos = new FileOutputStream (nuevoFichero);
				escribir = new ObjectOutputStream (fos);
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Fallo la creacion");
			} finally {
				try{
					if(escribir != null){ escribir.close();}
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("No se pudo cerrar correctamente");
				}
			}
		}	*/	
		return correcto;
	}
	
	/**Interfaz borrarFicheroTexto
	 * Cabecera: public boolean borrarFicheroTexto (String nombreFichero)
	 * Comentario: Dado un string borra un fichero con ese nombre en la carpeta activa, devuelve true si se borro correctamente y
	 * 				false si no existia u ocurrio algun error
	 * Precondiciones: Ninguna
	 * Entrada: Un String
	 * Salida: un boolean
	 * Postcondiciones: Devuelve un true si se borra correctamente y false en caso de error o si no existia
	*/
	@Deprecated
	public boolean borrarFicheroTexto (String nombreFichero){
		boolean correcto = false;
	/*	File aBorrar = new File (nombreFichero+".txt");
		if(aBorrar.isFile()){
			aBorrar.delete();
			correcto = true;
		}*/
		return correcto;
	}

	/**Interfaz copiarFicheroBinario
	 *  Cabecera: public boolean copiarFicheroBinario (String ficheroOrigen, String ficheroCopia)
	 *  Comentario: Dado un string ficheroOrigen lo copia al fichero designado por ficheroCopia, devuelve true si se copio
	 * 				correctamente y false si no existia u ocurrio algun error
	 *  Precondiciones: Ninguna 
	 *  Entrada: dos String 
	 *  Salida: un boolean 
	 *  Postcondiciones: Devuelve un true si se copio correctamente y false en caso de error o si no existia
	 */
	@SuppressWarnings("unchecked")
	public boolean copiarFicheroBinario(String ficheroOrigen, String ficheroCopia) {
		boolean correcto = false;
		
		if (new File(ficheroOrigen+".dat").isFile()) {
			if (new File(ficheroCopia+".dat").isFile() != true) {
				this.crearFicheroBinario(ficheroCopia);
			}
			// para leer
			FileInputStream fisLectura = null;
			ObjectInputStream leer = null;
			// para copiar
			FileOutputStream fosCopia = null;
			ObjectOutputStream copia = null;
			try {
				fisLectura = new FileInputStream(ficheroOrigen+".dat");
				leer = new ObjectInputStream(fisLectura);
				fosCopia = new FileOutputStream(ficheroCopia+".dat");
				copia = new ObjectOutputStream(fosCopia);

				// auxiliar = oisLectura.readObject();
				try{
					//auxiliar.setAG(leer.readObject());
					this.setAG( (T) leer.readObject());
					while (this.getAG() != null) {
						copia.writeObject(this.getAG());
						copia.reset();
						// auxiliar = oisLectura.readObject();
						this.setAG( (T) leer.readObject());
					}
					} catch (EOFException e) {
						this.setAG(null);
					}

			} catch (FileNotFoundException e) {
				// e1.printStackTrace();
				System.out.println("Algun fichero no se pudo encontrar");
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("La copia no se pudo realizar");
			} catch (ClassNotFoundException e) {
				// e.printStackTrace();
				System.out.println("No se identifico ningun objeto");
			} finally {
				try {
					if (copia != null) { copia.close(); }
					if (leer != null) { leer.close(); }
					if (fosCopia != null) { fosCopia.close(); }
					if (fisLectura != null) { fisLectura.close(); }
				} catch (IOException e) {
					// e.printStackTrace();
					System.out.println("No se pudo cerrar correctamente");
				}
			}
			correcto = true;
		}
		return correcto;
	}
	
	/**Interfaz copiarFicheroTexto
	 *  Cabecera: public boolean copiarFicheroTexto (String ficheroOrigen, String ficheroCopia)
	 *  Comentario: Dado un string borra un fichero con ese nombre en la carpeta activa, devuelve true si se borro
	 * 				correctamente y false si no existia u ocurrio algun error
	 *  Precondiciones: Ninguna 
	 *  Entrada: Un String 
	 *  Salida: un boolean 
	 *  Postcondiciones: Devuelve un true si se borra correctamente y false en caso de error o si no existia
	 */
	@Deprecated
	public boolean copiarFicheroTexto(String ficheroOrigen, String ficheroCopia) {
		boolean correcto = false;
		/*String aux = null;
		
		if (new File(ficheroOrigen+".txt").isFile()) {
			if (new File(ficheroCopia+".txt").isFile() != true) {
				this.crearFicheroTexto(ficheroCopia);
			}
			// para leer
			FileReader flrLeer = null;
			BufferedReader leer = null;
			// para copiar
			FileWriter flwCopia = null;
			BufferedWriter copia = null;
			try {
				flrLeer = new FileReader(ficheroOrigen+".txt");
				leer = new BufferedReader(flrLeer);
				flwCopia = new FileWriter(ficheroCopia+".txt");
				copia = new BufferedWriter(flwCopia);

				// auxiliar = oisLectura.readObject();
				try{
					//auxiliar.setAG(leer.readObject());
					aux = leer.readLine();
					while (aux != null) {
						copia.write(aux);
						// auxiliar = oisLectura.readObject();
						aux = leer.readLine();
					}
					} catch (EOFException e) {
						aux = null;
					}

			} catch (FileNotFoundException e) {
				// e1.printStackTrace();
				System.out.println("Algun fichero no se pudo encontrar");
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("La copia no se pudo realizar");
			} finally {
				try {
					if (copia != null) { copia.close(); }
					if (leer != null) { leer.close(); }
					if (flwCopia != null) { flwCopia.close(); }
					if (flrLeer != null) { flrLeer.close(); }
				} catch (IOException e) {
					// e.printStackTrace();
					System.out.println("No se pudo cerrar correctamente");
				}
			}
			correcto = true;
		}*/
		return correcto;
	}
	
	/**Interfaz escribirRegistroBinario
	 * Cabecera: public boolean escribirRegistroBinario(UtilidadesFichero<Object> introducir, String ficheroIntroducir)
	 * Comentario: Dado un T introducir y un String que contienen la ruta del archivo en el que queremos escribir el objeto; true si se escribio correctamente
	 * y false en caso contrario o error
	 * error 
	 * Precondiciones: Ninguna
	 * Entrada: un String y un UtilidadesFichero<Object>
	 * Salida: un boolean
	 * Postcondiciones: devuelve un boolean como true si se escribio bien y false en caso contrario o error 
	*/
	public boolean escribirRegistroBinario(T introducir, String ficheroIntroducir){
		boolean devolver= false;
		FileOutputStream fosEscribir = null;
		ObjectOutputStream oosEscribir = null;
		
		if (new File(ficheroIntroducir+".dat").isFile()){
			try{
				fosEscribir = new FileOutputStream(ficheroIntroducir+".dat",true);
				oosEscribir = new MiObjectOutputStream(fosEscribir);
				oosEscribir.writeObject(introducir);
				devolver = true;
			}catch (IOException e) {
				//e.printStackTrace();
				System.out.println("No se pudo escribir");
			} finally{
				try {
					if(fosEscribir != null){ fosEscribir.close();}
					if(oosEscribir != null){ oosEscribir.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		return devolver;		
	}
	
	/**Interfaz escribirMultiplesRegistroBinario
	 * Cabecera: public boolean escribirMultiplesRegistroBinario(ArrayList<T> introducir, String ficheroIntroducir)
	 * Comentario: Dado un ArrayList<T> introducir y un String que contienen la ruta del archivo en el que queremos escribir los objetos; true si se escribio correctamente
	 * y false en caso contrario o error
	 * error 
	 * Precondiciones: Ninguna
	 * Entrada: un String y un UtilidadesFichero<Object>
	 * Salida: un boolean
	 * Postcondiciones: devuelve un boolean como true si se escribio bien y false en caso contrario o error 
	*/
	//probando a escribir objetos puros
	public boolean escribirMultiplesRegistroBinario(ArrayList<T> introducir, String ficheroIntroducir) {
		boolean devolver= false;
		FileOutputStream fosEscribir = null;
		ObjectOutputStream oosEscribir = null;
		
		if (new File(ficheroIntroducir+".dat").isFile()){
			try{
				fosEscribir = new FileOutputStream(ficheroIntroducir+".dat",true);
				oosEscribir = new MiObjectOutputStream(fosEscribir);
				for (int i =0 ; i <introducir.size();i++) {
					oosEscribir.writeObject(introducir.get(i));
					//System.out.println(introducir.get(i).toString());
				}
				devolver = true;
			}catch (IOException e) {
				//e.printStackTrace();
				System.out.println("No se pudo escribir");
			} finally{
				try {
					if(fosEscribir != null){ fosEscribir.close();}
					if(oosEscribir != null){ oosEscribir.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		return devolver;		
	}
	/**Interfaz escribirRegistroTexto
	 * Cabecera: public boolean escribirRegistroTexto(UtilidadesFichero<Object> introducir, String ficheroIntroducir)
	 * Comentario: Dado un T introducir y un String que contienen la ruta del archivo en el que queremos escribir el String; true si se escribio correctamente
	 * y false en caso contrario o error
	 * error 
	 * Precondiciones: Ninguna
	 * Entrada: un String y un UtilidadesFichero<Object>
	 * Salida: un boolean
	 * Postcondiciones: devuelve un boolean como true si se escribio bien y false en caso contrario o error 
	*/
	@Deprecated
	public boolean escribirRegistroTexto(String introducir, String ficheroIntroducir) {
		boolean devolver= false;
		/*FileWriter flwEscribir = null;
		BufferedWriter Escribir = null;
		
		if (new File(ficheroIntroducir+".txt").isFile()){
			try{
				flwEscribir = new FileWriter(ficheroIntroducir+".txt",true);
				Escribir = new BufferedWriter(flwEscribir);
				Escribir.write(introducir);
				devolver = true;
			}catch (IOException e) {
				//e.printStackTrace();
				System.out.println("No se pudo escribir");
			} finally{
				try {
					if(Escribir != null){ Escribir.close();}
					if(flwEscribir != null){ flwEscribir.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} */
		return devolver;
	}
	
	/**leerFicheroBinario
	 * Cabecera: public ArrayList<T> leerFicheroBinario (String ruta)
	 * Comentario: Dado un String con la ruta del fichero a leer, devuelve una LinkedList con todos los objetos de la lista ó null si 
	 * existio algun error.
	 * Precondiciones: ninguna
	 * Entradas: Un String
	 * Salidas: una LinkedList
	 * Postcondiciones: Se devuelve una LinkedList con los objetos del fichero ó null si existio algun error
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public  ArrayList<T> leerFicheroBinario (String ruta) {
		ArrayList<T> devolver = new ArrayList<T>();
		FileInputStream fisLeer = null;
		ObjectInputStream leer = null;
		
			if (new File(ruta+".dat").isFile()) {
				try {
					fisLeer = new FileInputStream(ruta+".dat");
					leer = new ObjectInputStream(fisLeer);
					
					try {
						setAG((T) leer.readObject());
						while (getAG() != null) {
							devolver.add(getAG());
							setAG((T) leer.readObject());
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (EOFException e) {
						setAG(null);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (leer != null) {
							leer.close();
						}
						if (fisLeer != null) {
							fisLeer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}		
		return devolver;
	}
	
	/**leerFicheroTexto
	 * Cabecera: public ArrayList<T> leerFicheroTexto (String ruta)
	 * Comentario: Dado un String con la ruta del fichero a leer, devuelve una LinkedList con todos los objetos de la lista ó null si 
	 * existio algun error.
	 * Precondiciones: ninguna
	 * Entradas: Un String
	 * Salidas: una LinkedList
	 * Postcondiciones: Se devuelve una LinkedList con los objetos del fichero ó null si existio algun error
	 * 
	 * Observaciones: necesita un cast al tipo de objeto que estemos leyendo.... no se si me gusta
	 */
	@Deprecated
	public  String leerFicheroTexto (String ruta) {
		String devolver = null;
		/*FileReader flrLeer = null;
		BufferedReader leer = null;
		
			if (new File(ruta+".dat").isFile()) {
				try {
					flrLeer = new FileReader(ruta+".dat");
					leer = new BufferedReader(flrLeer);
					
					try {
						setAG( (T) leer.readLine());
						while (getAG() != null) {
							devolver+=getAG()+"\n";
							setAG( (T) leer.readLine());
						}
					} catch (EOFException e) {
						setAG(null);
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (leer != null) {
							leer.close();
						}
						if (flrLeer != null) {
							flrLeer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}		*/
		return devolver;
	}
	
	/**Interfaz ordenacionHibrida
	 * Cabecera: public boolean ordenacionHibrida(String desordenado, String ordenado)
	 * Comentario: Dado dos String que contienen la ruta de dos archivos, uno desordenado y el otro donde se va a ordenar; realiza la ordenacion por el metodo hibrido
	 * Precondiciones: Ninguna
	 * Entrada: Dos String
	 * Salida: un boolean
	 * Postcondiciones: Devuelve un true si se ordena correctamente y false en caso de error o si alguno de los ficheros no existia
	*/
	public boolean ordenacionHibrida(String desordenado, String ordenado) {
		boolean correcto = false;
		List<T> objetos = new ArrayList<T>();
		
		if (new File(desordenado+".dat").isFile() & new File(ordenado+".dat").isFile()) {
			objetos = ordenarArray(desordenado);
			if (!objetos.isEmpty()) {
				FileOutputStream fosOrdenado = null;
				ObjectOutputStream oosOrdenado = null;
				try {
					fosOrdenado = new FileOutputStream(ordenado+".dat");
					oosOrdenado = new ObjectOutputStream(fosOrdenado);
					for (int i = 0; i < objetos.size(); i++) {
						oosOrdenado.writeObject(objetos.get(i));
						oosOrdenado.reset();
					}
				} catch (FileNotFoundException e) {
					// e1.printStackTrace();
					System.out.println("Algun fichero no se pudo encontrar");
				} catch (IOException e) {
					// e.printStackTrace();
					System.out.println("La copia no se pudo realizar");
				} finally {
					try {
						if (oosOrdenado != null) { oosOrdenado.close();}
						if (fosOrdenado != null) { fosOrdenado.close();;}
					} catch (IOException e) {
						// e.printStackTrace();
						System.out.println("No se pudo cerrar correctamente");
					}
				}
				correcto = true;
			}
		}
		return correcto;
	}
	/**Interfaz ordenarArray
	 * Cabecera: private List<T> ordenarArray(String desordenado)
	 * Comentario: Dado un String que contienen la ruta del archivo a ordenar; devuelve un UtilidadesFichero<List<Object>> con los objetos ordenados o null si ocurrio algun 
	 * error 
	 * Precondiciones: Ninguna
	 * Entrada: un String
	 * Salida: un UtilidadesFichero<List<Object>>
	 * Postcondiciones: devuelve un UtilidadesFichero<List<Object>> con los objetos ordenados o null si ocurrio algun error 
	*/
	private List<T> ordenarArray(String desordenado) {
		List<T> origen = leerFicheroBinario(desordenado);
		@SuppressWarnings("unchecked")
		T [] aux = (T[]) origen.toArray();
		if (aux != null) {
			Arrays.sort(aux);
			origen = Arrays.asList(aux);
		}
		return origen;
	}
	
	/**Interfaz: busqueda
	 * cabecera: public ArrayList<T> busqueda (String buscar, String ruta)
	 * comentario: Dado un String buscar y un String ruta con la direccion de un fichero de datos sin la extension .dat, busca y devuelve un ArrayList que cumpla la condicion de busqueda;
	 * 				sino devuelve null
	 * Precondicion: ninguna
	 * Entradas: dos String
	 * Salida: un tipo AlumnoImpl
	 * Postcondicion: Devuelve un ArrayList con los que cumplan la condicion de busqueda o null en caso de no encontrar ningun objeto que cumpla la condicion de busqueda 
	 */
	//Pattern.compile(Pattern.quote(s2)).matcher(s1).find();Pattern.compile(Pattern.quote(buscar)).matcher(auxiliar.get(i).toString()).find()
	//da errores cuando buscas por expresiones regulares.... y las necesito dammit--> ahora es "selectivo" regex
	public ArrayList<T> busqueda (String buscar, String ruta) {
		 ArrayList<T> devolver = null, auxiliar = null;
		 //boolean auxB = false;
		
		 
		 if(new File(ruta+".dat").isFile() && (auxiliar = leerFicheroBinario(ruta))!= null) {
			 for(int i = 0; i < auxiliar.size(); i++) {
				 /*String auxS = Pattern.quote(buscar);
				 String prueba = auxiliar.get(i).toString();
				 CharSequence apoyoC = prueba.;
				 Pattern auxP = Pattern.compile(auxS);
				 Matcher auxM = Pattern.matcher(apoyoC);*/
				 //auxB = Pattern.compile(Pattern.quote(buscar)).matcher(auxiliar.get(i).toString()).find();
				 Pattern p = Pattern.compile(Pattern.quote(buscar));
				 Matcher m = p.matcher(auxiliar.get(i).toString());
				 if(devolver == null && m.find()) {
					 devolver = new ArrayList<T>();
					 devolver.add(auxiliar.get(i));
				 } else if (m.find()) {
					 devolver.add(auxiliar.get(i));
				 }
				 /*
				 if(devolver == null && auxiliar.get(i).toString().contains(buscar)) {
					 devolver = new ArrayList<T>();
					 devolver.add(auxiliar.get(i));
				 } else if (auxiliar.get(i).toString().contains(buscar)) {
					 devolver.add(auxiliar.get(i));
				 }*/
			 }
		 }
		 
		 return devolver;
	}
}
