package ru.geekbrains.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
@NamedQuery(name = "customerByName", query = "from Customer c where c.name = :name")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

   /* @OneToMany(mappedBy = "customer")
    private List<Order> orders;
*/
    public Customer() {
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }*/

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
             //   ", orders=" + orders +
                '}';
    }
}
