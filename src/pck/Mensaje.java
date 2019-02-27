package pck;

public class Mensaje {
	private short mensaje;
	private short idEnviador;

	
	public Mensaje(short nMensaje, short nIdE){
		this.mensaje = nMensaje;
		this.idEnviador = nIdE;
	}
	
	public short getMensaje(){
		return this.mensaje;
	}
	public short getIdEnviador(){
		return this.idEnviador;
	}
	//Notifica a un hilo de ejecucion que este esprando sobre un objeto mensaje
		public synchronized void notificar(){
			notify();
		}
		
		//pone en espera a un hilo de ejecuión sobre mensaje
		public synchronized void esperar(){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	public void aumentarMensaje(){
		this.mensaje++;
	}

}
