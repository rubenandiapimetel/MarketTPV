package com.ruben.market.domain.repository;

import com.ruben.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String idcliente);
    Purchase save(Purchase purchase);
}
