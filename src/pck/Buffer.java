package pck;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public final class Buffer {
	  //Numero de Usuarios
		private int num;
		
		//tama�o de la cola
		private int tama�oLista;
		
		//tama�o del buffer
		private int tama�oBuffer;
	public Stack<Mensaje> listaMensajes = new Stack<Mensaje>();
	public Buffer(int pCapacidad, int pNumeroClientes) 
	{
		
		this.tama�oLista= 0;
		num=pNumeroClientes;
		this.tama�oBuffer=pCapacidad;
	}

	/*
	 * servidor saca mensaje y despierta al cliente que fue procesado
	 */
	public synchronized  Mensaje sacarMesaje(short id) throws InterruptedException{
		Mensaje men = null;
		while(!listaMensajes.empty())
		{
			men = listaMensajes.pop();
			System.out.println("mensaje con valor "+men.getMensaje()+ " a procesar por servidor #"+id);
			men.aumentarMensaje();
			System.out.println("Respuesta:"+men.getMensaje());
			tama�oLista--;
		}
		if(tama�oLista==0)
		{
			notificarTodos();
			return null;
		}
		tama�oLista--;
		return men;
			
	}
	
	/*
	 * cliente saca mensaje para ser procesado por servidor
	 */
	public synchronized void meterMensaje(Mensaje men) throws InterruptedException{
		if(tama�oLista>=tama�oBuffer)
		{
			wait();
		}
		 if(tama�oLista<tama�oBuffer)
		{
			if(tama�oLista==0){notifyAll();}
			listaMensajes.push(men);
			tama�oLista++;
		}
	}
		//Indica si aun hay algun cliente en el sistema
		public synchronized boolean hayMensajes()
		{
			return(tama�oLista>0);
		}
		public synchronized boolean sePuedeAgrugar()
		{
			return(tama�oLista<tama�oBuffer);
		}
		//Despierta a todos los objetos dormidos en el buffer
		public synchronized void notificarTodos() {
			notifyAll();
		}	
	 
	
	
	

}
