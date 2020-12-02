package ru.geekbrains;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @CreationTimestamp
    private Timestamp orderItemTime;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal count;

    public OrderItem() {
    }

    public OrderItem(Long id, Timestamp orderItemTime, Product product, Order order, BigDecimal count) {
        Id = id;
        this.orderItemTime = orderItemTime;
        this.product = product;
        this.order = order;
        this.price = product.getPrice();
        this.count = count;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Timestamp getOrderItemTime() {
        return orderItemTime;
    }

    public void setOrderItemTime(Timestamp orderItemTime) {
        this.orderItemTime = orderItemTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
