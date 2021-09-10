package com.project.models.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.project.appenum.DiscountType;
import com.project.appenum.PaymentMethod;
import com.project.appenum.StatusOrder;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(name="date_create")
	private Date dateCreate;
	
//	@Column(name="total_price")
//	private Double totalPrice;
	
	@Column(name="address_delivery")
	private String addressDelivery;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String email;
	
	private String note;
	
	@Column(name="ship_price")
	private Double shipPrice;
	
	private Double vat;
	
	private Double payment;
	
	private Double discount;
	
	@Column(name="discount_type")
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	
	@Column(name="payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private StatusOrder status;
	
	@OneToMany(mappedBy = "order")
	private Set<OrderDetails> oderDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

//	public Double getTotalPrice() {
//		return totalPrice;
//	}
//
//	public void setTotalPrice(Double totalPrice) {
//		this.totalPrice = totalPrice;
//	}

	public String getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(String addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(Double shipPrice) {
		this.shipPrice = shipPrice;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public Set<OrderDetails> getOderDetails() {
		return oderDetails;
	}

	public void setOderDetails(Set<OrderDetails> oderDetails) {
		this.oderDetails = oderDetails;
	}
	
	@Transactional
	public Double getTotalPrice() {
		return payment + shipPrice + vat - discount;
	}
}
