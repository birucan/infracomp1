package pck;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public final class Buffer {
	  //Numero de Usuarios
		private int num;
		
		//tamaño de la cola
		private int tamañoLista;
		
		//tamaño del buffer
		private int tamañoBuffer;
	public Stack<Mensaje> listaMensajes = new Stack<Mensaje>();
	public Buffer(int pCapacidad, int pNumeroClientes) 
	{
		
		this.tamañoLista= 0;
		num=pNumeroClientes;
		this.tamañoBuffer=pCapacidad;
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
			tamañoLista--;
		}
		if(tamañoLista==0)
		{
			notificarTodos();
			return null;
		}
		tamañoLista--;
		return men;
			
	}
	
	/*
	 * cliente saca mensaje para ser procesado por servidor
	 */
	public synchronized void meterMensaje(Mensaje men) throws InterruptedException{
		if(tamañoLista>=tamañoBuffer)
		{
			wait();
		}
		 if(tamañoLista<tamañoBuffer)
		{
			if(tamañoLista==0){notifyAll();}
			listaMensajes.push(men);
			tamañoLista++;
		}
	}
		//Indica si aun hay algun cliente en el sistema
		public synchronized boolean hayMensajes()
		{
			return(tamañoLista>0);
		}
		public synchronized boolean sePuedeAgrugar()
		{
			return(tamañoLista<tamañoBuffer);
		}
		//Despierta a todos los objetos dormidos en el buffer
		public synchronized void notificarTodos() {
			notifyAll();
		}	
	 
	
	
	

}
