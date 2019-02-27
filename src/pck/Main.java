package pck;

public class Main {
	
	public static final short MAXSERVIDORES= 5;
	public static final short MAXCLIENTES = 100; 
	
	private static Buffer buffer = new Buffer(10, 10);
	static Cliente[] threadsC = new Cliente[MAXCLIENTES];
	static Servidor[] threadsS = new Servidor[MAXCLIENTES];
	
	public static void main(String[] args) {

		for (short b = 0; b < MAXSERVIDORES; b++) {
			Servidor s = new Servidor(b, buffer);
			threadsS[b] = s;
		}
		for (short a = 0; a < MAXCLIENTES; a++) {
			Cliente c = new Cliente(a, buffer);
			threadsC[a] = c;
			threadsC[a].start();
			responder();
		}
		responder();
		}
		public synchronized static void responder()
		{
			while(buffer.hayMensajes()){
				short b =  (short) ((short) (Math.random()*4)+1);
					threadsS[b].run();
		}
		//while(!buffer.listaMensajes.isEmpty()){
		//	Mensaje m=buffer.listaMensajes.pop();
		//	System.out.println(m.getMensaje());
		//}

	}

}
