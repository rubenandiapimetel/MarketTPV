package com.ruben.market.persistence.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="productos")
public class Producto {

    @Id						        //Esto es porque es la primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")		//porque el original se llama id_producto
    private Integer idProducto;

    private String nombre;			//aqui no hace falta colum porque
    //se llama igual en la tabla

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Integer precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private boolean estado;




    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;


    //@OneToMany(mappedBy = "producto")
    //private List<ComprasProducto> comprasProducto;




    //getter & setters



    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
