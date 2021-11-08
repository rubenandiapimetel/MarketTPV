package com.ruben.market.persistence.mapper;

import com.ruben.market.domain.Category;
import com.ruben.market.persistence.entity.Categoria;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//la anotacion mapper sirve para decirle a mapstruct que forma parte de spring

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	/*Esta interface sirve para declarar los metodos que usaremos
	para pasar de categoria a category para usarlos en el domain
	mediante mapping le pasamos de fuente-idCategoria a categoriId
	desde la clase Categoria de persistence a Category de domain */


    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })

        //El metodo to** sirve para esto mismo
    Category toCategory(Categoria categoria);

    //Este metodo hace lo mismo pero en sentido contrario

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}

