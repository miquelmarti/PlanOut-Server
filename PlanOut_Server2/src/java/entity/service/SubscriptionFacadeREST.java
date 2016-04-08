/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Plan;
import entity.Subscription;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author mmartira
 */
@Stateless
@Path("entity.subscription")
public class SubscriptionFacadeREST extends AbstractFacade<Subscription> {
    @PersistenceContext(unitName = "PlanOut_Server2PU")
    private EntityManager em;

    public SubscriptionFacadeREST() {
        super(Subscription.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Subscription entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Subscription entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{user}/{plan}")
    public void remove(@PathParam("user") String userid, @PathParam("plan") Integer planid) {
        Query q = this.getEntityManager().createQuery("DELETE from Subscription s where s.user = :user and s.plan = :plan ");
        q.setParameter("user", new User(userid));
        q.setParameter("plan", new Plan(planid));
        q.executeUpdate();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Subscription find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Subscription> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Subscription> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("entity.user/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Subscription> findByUser(@PathParam("id") String id) {
        Query q = this.getEntityManager().createQuery("select s from Subscription s where s.user = :user");
        q.setParameter("user", new User(id));
        return q.getResultList();
    }
    
    @GET
    @Path("entity.plan/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Subscription> findByPlan(@PathParam("id") Integer id) {
        Query q = this.getEntityManager().createQuery("select s from Subscription s where s.plan = :plan");
        q.setParameter("plan", new Plan(id));
        return q.getResultList();
    }
    
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
