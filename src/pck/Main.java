package pck;

public class Main {
	
	public static final short MAXSERVIDORES= 5;
	public static final short MAXCLIENTES = 100; 
	
	private static Buffer buffer = new Buffer();

	public static void main(String[] args) {
		Cliente[] threadsC = new Cliente[MAXCLIENTES];
		Servidor[] threadsS = new Servidor[MAXCLIENTES];
		
		for (short a = 0; a < MAXCLIENTES; a++) {
			Cliente c = new Cliente(a, buffer);
			threadsC[a] = c;
			threadsC[a].start();
		}
		
		for (short b = 0; b < MAXSERVIDORES; b++) {
			Servidor s = new Servidor(b, buffer);
			threadsS[b] = s;
		}
		for (short b = 0; b < MAXSERVIDORES; b++) {
			threadsS[b].start();
		}
		
		//while(!buffer.listaMensajes.isEmpty()){
		//	Mensaje m=buffer.listaMensajes.pop();
		//	System.out.println(m.getMensaje());
		//}

	}

}
