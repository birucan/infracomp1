package pck;

import java.util.Stack;

public class Cliente extends Thread{
	private short id;
	private Stack<Mensaje> mensajes = new Stack<Mensaje>();
	private Buffer buffer;
	
	
	
	public Cliente(short nId, Buffer nBuffer) {
		super();
		this.id = nId;
		this.buffer= nBuffer;
	}

	/*
	 * genera un numero aleatorio de mensajes y los mete a la lista en el buffer
	 */
	public void run(){
		generarMensajes();
		while(!mensajes.isEmpty()){
			Mensaje m = mensajes.pop();
			try {
				buffer.meterMensaje(m);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("El Cliente con id "+id+" envia el mensaje: "+m.getMensaje());
		}
		
			
	}
	
	private void generarMensajes (){

		short numeroMensajes =  (short) ((short) (Math.random()*5)+1);
		numeroMensajes ++;
		for (int i = 0; i < numeroMensajes; i++) {
			Mensaje nMen = new Mensaje((short) (Math.random()*1000), id);
			mensajes.push(nMen);
		}
	}
}
