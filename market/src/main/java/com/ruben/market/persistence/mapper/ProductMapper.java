package com.ruben.market.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ruben.market.domain.Product;
import com.ruben.market.persistence.entity.Producto;

//esto para no tener que mapear product
//en categorymapper ya que seria este product
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")
            //de categoria si lo hago porque me interesa ver que categoria
            //tiene cada productos al contrario de productos en el que no
            //me interesa saber que productos hay en una categoria ya que sino
            //entro directamente a categoria
    })
    Product toProduct(Producto producto);

    //Esto es opcional, solo para que me devuelva una lista de products en plural
    //Y no hace falta poner de nuevo @Mappings
    List<Product> toProducts(List<Producto> productos);


    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
