package com.example.demoweb1.dao;

import com.example.demoweb1.data.Expression;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ExpressionDao {
    @PersistenceContext
    EntityManager em;

    public List<Expression> findAll() {
        return em.createNamedQuery("Expression.findAll", Expression.class).getResultList();
    }

    public void add(Expression expression) {
        em.persist(expression);
    }

    public void delete(Expression expression) {
        Expression e = em.find(Expression.class, expression.getId());
        em.remove(e);
    }

    public void update(Expression expression) {
        Expression e = em.find(Expression.class, expression.getId());
        e.setExpr(expression.getExpr());
        e.setResult(expression.getResult());
        em.merge(e);
    }
}
