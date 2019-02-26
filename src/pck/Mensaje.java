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

	
	public void aumentarMensaje(){
		this.mensaje++;
	}

}
