package es.getafe.examen.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Comparable<Producto>, Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int idProducto;
	private String producto;
	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "fk_fabricante")
	private Fabricante fabricante;
	
	
	
	
	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public String getProducto() {
		return producto;
	}


	public void setProducto(String producto) {
		this.producto = producto;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Fabricante getFabricante() {
		return fabricante;
	}


	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	
	

	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return idProducto == other.idProducto;
	}


	@Override
	public int compareTo(Producto o) {
		
		return this.idProducto - o.idProducto;
	}

}
