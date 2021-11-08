package com.ruben.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ruben.market.persistence.entity.Producto;

/*Esta interface sirve para porder heredar de CrudReposirtory
 *y asi crear un objeto de esta clase y poder devolver cualquier
 *funcion de las que dispone y crear listas y optionals con los
 *objetos Producto
 */
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer >{

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    //
    //public Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
}
