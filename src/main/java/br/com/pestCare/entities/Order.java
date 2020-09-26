package br.com.pestCare.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.pestCare.entities.enumeration.OrderStatus;

@Entity
@Table(name = "tb_order")
@SequenceGenerator(name = "sq_order", sequenceName = "sq_order", initialValue = 1, allocationSize = 1)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sq_order", strategy = GenerationType.AUTO)
	@Column(name = "id_order", nullable = false)
	private Long id;

	@Column(name = "dt_moment")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	@ManyToOne
	@JoinColumn(name = "fk_user", referencedColumnName = "id_user")
	private User user;

	@Column(name = "ts_last_update", insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@Column(name = "en_order_status")
	private Integer orderStatus;

	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	public Order() {
		super();
	}

	public Order(Long id, Instant moment, User user, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.moment = moment;
		this.user = user;
		setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	@PreUpdate
	public void onUpdate() {
		this.lastUpdate = new Date();
	}

	@PrePersist
	public void onInsert() {
		this.lastUpdate = new Date();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderStatus getOrderStatus() {
		if (orderStatus != null) {
			return OrderStatus.valueOf(orderStatus);
		}
		return null;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		Double sum = items.stream().mapToDouble(item -> item.getSubTotal()).sum();
		return sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
