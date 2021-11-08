package com.ruben.market.persistence.mapper;

import com.ruben.market.domain.Product;
import com.ruben.market.domain.PurchaseItem;
import com.ruben.market.persistence.entity.ComprasProducto;
import com.ruben.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "active")
            //total no hace falta porque esta se escribe igual

    })
    PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);

    List<PurchaseItem> toPurchaseItem(List<ComprasProducto> comprasProducto);

    @InheritInverseConfiguration
    @Mapping(target = "id.idCompra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "compra", ignore = true)
    ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
