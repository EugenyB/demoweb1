package com.example.demoweb1.beans;

import com.example.demoweb1.dao.ExpressionDao;
import com.example.demoweb1.data.Expression;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ExpressionBean implements Serializable {
    @EJB
    ExpressionDao expressionDao;

    private Expression expression = new Expression();

    public List<Expression> getExpressions() {
        return expressionDao.findAll();
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void add() {
        double v = new ExpressionBuilder(expression.getExpr()).build().evaluate();
        expression.setResult(v);
        expressionDao.add(expression);
        expression = new Expression();
    }

    public void delete(Expression expression) {
        expressionDao.delete(expression);
    }

    public String edit(Expression expression) {
        this.expression = expression;
        return "edit";
    }

    public String update() {
        double v = new ExpressionBuilder(expression.getExpr()).build().evaluate();
        expression.setResult(v);
        expressionDao.update(expression);
        return "index";
    }
}
