package Models;

import java.util.Date;

public class Order {
    private int Id;
    private Date OrderDT;
    private Double Subtotal;
    private int NumItems;
    private int EmployeeId;

    public Order(int id, Date orderDT, Double subtotal, int numItems, int employeeId) {
        Id = id;
        OrderDT = orderDT;
        Subtotal = subtotal;
        NumItems = numItems;
        EmployeeId = employeeId;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getOrderDT() {
        return OrderDT;
    }

    public void setOrderDT(Date orderDT) {
        OrderDT = orderDT;
    }

    public Double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Double subtotal) {
        Subtotal = subtotal;
    }

    public int getNumItems() {
        return NumItems;
    }

    public void setNumItems(int numItems) {
        NumItems = numItems;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }
}
