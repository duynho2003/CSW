package vn.aptech.rs;

import java.util.List;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import vn.aptech.entity.Product;

/**
 * Session Bean implementation class ProductRs
 */
@Stateless
@LocalBean
@Path(value = "/product")
public class ProductRs {

	@PersistenceContext(unitName = "DemoRS")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ProductRs() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> findAll() {
		TypedQuery<Product> q = em.createQuery("SELECT o FROM Product o", Product.class);
		return q.getResultList();
	}
	
	@GET
	@Path(value = "/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Product findById (int id) {
		return em.find(Product.class, id);
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void create (Product p) {
		em.persist(p);
	}
}
