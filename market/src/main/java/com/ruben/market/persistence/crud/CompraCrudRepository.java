package com.ruben.market.persistence.crud;

import com.ruben.market.domain.Purchase;
import com.ruben.market.persistence.entity.Compra;
import com.ruben.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//ES IMPORTANTE DECLARAS LOS QUERYMETHODS PARA PODER USARLOS EN LA COMPRAREPOSITORY

public interface CompraCrudRepository extends CrudRepository<Compra, Integer > {

    //le llamo idcliente porque es la manera en la que esta en el entity pero con la i en mayus.
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
