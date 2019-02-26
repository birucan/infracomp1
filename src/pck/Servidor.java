package pck;

public class Servidor extends Thread{
	private short id;
	private Buffer buffer;
	public Servidor(short nId, Buffer nBuffer){
		this.id = nId;
		this.buffer = nBuffer;
	}
	public void run(){
		
		while(!buffer.listaMensajes.isEmpty()) buffer.sacarMesaje(this.id);
	}
	
}
