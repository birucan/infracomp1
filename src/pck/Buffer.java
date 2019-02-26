package pck;

import java.util.Iterator;
import java.util.Stack;



public final class Buffer {
	

	
	
	
	public Stack<Mensaje> listaMensajes = new Stack<Mensaje>();
	

	/*
	 * servidor saca mensaje y despierta al cliente que fue procesado
	 */
	public synchronized  Mensaje sacarMesaje(short id){
			if(!listaMensajes.isEmpty()){
				Mensaje men;
				men = listaMensajes.pop();
				System.out.println("mensaje con valor "+men.getMensaje()+ " a procesar por servidor #"+id);
				men.aumentarMensaje();
				
				//TODO despertar thread cliente
				
				
				
				return men;	
			}
			Mensaje m = new Mensaje((short)0, (short)0);
			return m;
			
	}
	
	/*
	 * cliente saca mensaje para ser procesado por servidor
	 */
	public synchronized void meterMensaje(Mensaje men){
		listaMensajes.push(men);	
	}
	
	 
	
	
	

}
