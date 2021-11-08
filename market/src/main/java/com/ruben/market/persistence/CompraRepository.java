package com.ruben.market.persistence;

import com.ruben.market.domain.Purchase;
import com.ruben.market.domain.repository.PurchaseRepository;
import com.ruben.market.persistence.crud.CompraCrudRepository;
import com.ruben.market.persistence.entity.Compra;
import com.ruben.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ESTA CLASE SIRVE PARA CONVERTIRLOS EN MAPPER TODOS LOS METODOS Y PUNTO
@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>)compraCrudRepository.findAll());

    }

    @Override
    public Optional<List<Purchase>> getByClient(String idCliente) {
        return compraCrudRepository.findByIdCliente(idCliente).map(compras -> mapper.toPurchases(compras));
    }
    /*
    AQUI LO QUE HAGO ES COGER PURCHASE MAPEARLO/CONVERTIRLO A COMPRA
    Y LUEGO EXTRAER CADA UNO DE SUS PRODUCTOS CON GET PRODUCTOS
    Y CON UN FOREACH HACER UN SET DE CADA UNO DE los PRODUCTOS QUE ME DEVUELVE
    QUE ESTAN DENTRO DE LA LIST<COMPRASPRODUCTOS> 
    */
    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
