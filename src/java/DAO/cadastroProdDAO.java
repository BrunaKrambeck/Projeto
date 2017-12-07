package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import modelo.cadastroProd;

public class cadastroProdDAO {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaVendasPU");
    protected EntityManager em = emf.createEntityManager();
    
    public boolean salvar(cadastroProd cadProd) {
        em.getTransaction().begin();
        try {
            em.merge(cadProd);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException excecao) {
            /*Esta exceção ocorre quando há algum erro na execução do comando enviado para o banco.
             *Quando ocorrer, deve-se sempre verificar se a transação está ativa. 
             *Caso estiver, deve-se fazer rollback */
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
     public boolean remover(cadastroProd cadProd)  {
        em.getTransaction().begin();
        try {
            em.remove(cadastroProd cadProd);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException excecao) {
            /*Esta exceção ocorre quando há algum erro na execução do comando enviado para o banco.
             *Quando ocorrer, deve-se sempre verificar se a transação está ativa. 
             *Caso estiver, deve-se fazer rollback */

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
     
     public List<Categoria> buscarTodos(){
         Query cons = em.createQuery("select c from Categoria c");
         return cons.getResultList();
     }
    
}
