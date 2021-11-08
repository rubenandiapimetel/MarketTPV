package com.ruben.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


// aqui importa productoCrudRepository pero no extiende

import com.ruben.market.domain.Product;
import com.ruben.market.domain.repository.ProductRepository;
import com.ruben.market.persistence.crud.ProductoCrudRepository;
import com.ruben.market.persistence.entity.Producto;
import com.ruben.market.persistence.mapper.ProductMapper;


/*aqui lo que se pretende es crear un objeto mapper que herede las funciones
 * que hereda de entity.producto ya que las mapea, y devolver un objeto
 * mapper.lafunccionquelecorrespone, el objeto devuelto es de tipo product
 * y no producto ya que mapper mapea el producto y lo convierte en product
 */


//Repository sirve para decir donde se hace la peticion a la BDD.
@Repository
public class ProductoRepository implements ProductRepository{

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;
    //mapper tiene las funciones toproducto y toproduct

    //metodo para regresar todos los productos.
    @Override
    public List<Product> getAll(){

		/*tengo primero que castearlo a un objeto List<producto> para luego convertirlo
		en List<product> mediante toProduct */
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();

        return mapper.toProducts(productos);
        //la funcion toproduct convierte la lista de producto en product.

    }

	/*SIEMPRE TIENE POR PARAMETRO UN OBJETO DE LA CLASE PRODUCT, NO PRODUCTO.
	PERO COMO TIENE QUE USAR UNA FUNCION DE PRODUCTOCRUDREPOSITORY ENTONCES
	TIENE QUE CASTEARLO A PRODUCTO, SOLO POR ESO*/

    @Override
    public Product save(Product product) {

        Producto producto = mapper.toProducto(product);

        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    //en esta funcion no hace falta convertirlo en producto porque solo queriere un Id
    @Override
    public void delete(int product) {

        productoCrudRepository.deleteById(product);
    }


    //tambien le cambio el parametro al del
    //atributo de la clase Category
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);

        //el metodo optional.of es para poder devolver optional
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScaseProduct(int quantity) {

        Optional<List<Producto>> productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);

		/* mesto se hace porque findByCantidadStockLessThanAndEstado
		devuelve un Optional y no se puede cambiar de product a producto un Optional,
		entonces usamos la funcion .map y el lambda */
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {

        Optional<Producto> producto= (Optional<Producto>)productoCrudRepository.findById(productId);

        //Uso la funcion toProduct no toProducts xra que devuelva un solo producto
        //ya que finbyid me devuelve un producto.
        return producto.map(prod -> mapper.toProduct(prod));
    }

}
