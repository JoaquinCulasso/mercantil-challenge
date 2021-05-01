package com.ma.pedidos.entity;

import com.ma.product.entity.ProductEntity;

import javax.persistence.*;

@Entity
@Table(name = "pedidos_detalle")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="id_pedido_cabecera", nullable = false)
    private OrderHeaderEntity order_header;

    @ManyToOne()
    @JoinColumn(name="id_producto", nullable = false)
    private ProductEntity productEntity;

    @Column(name = "cantidad", nullable = false)
    private int quantity;

    @Column(name = "precio_unitario", nullable = false)
    private float unit_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderHeaderEntity getOrder_header() {
        return order_header;
    }

    public void setOrder_header(OrderHeaderEntity order_header) {
        this.order_header = order_header;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }
}
