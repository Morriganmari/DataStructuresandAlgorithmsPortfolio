package dSandAPortfolio;

import java.util.LinkedList;
import java.util.Queue;
import java.text.DecimalFormat;

class Order {
    private Queue<OrderDetails> orderQueue;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.00"); 

    public Order() {
        orderQueue = new LinkedList<>();
    }

    public void addOrder(String customerLastName, int orderNumber, double orderTotalCost) {
        OrderDetails order = new OrderDetails(customerLastName, orderNumber, orderTotalCost);
        orderQueue.offer(order);
    }

    public OrderDetails removeOrder() {
        return orderQueue.poll();
    }

    public boolean isEmpty() {
        return orderQueue.isEmpty();
    }

    public Queue<OrderDetails> getOrderQueue() {
        return orderQueue;
    }

    public String getCustomerLastName() {
        OrderDetails order = orderQueue.peek();
        if (order != null) {
            return order.getCustomerLastName();
        }
        return "";
    }

    public int getOrderNumber() {
        OrderDetails order = orderQueue.peek();
        if (order != null) {
            return order.getOrderNumber();
        }
        return 0;
    }

    public String getOrderTotalCost() {
        OrderDetails order = orderQueue.peek();
        if (order != null) {
            return decimalFormat.format(order.getOrderTotalCost());
        }
        return "0.00";
    }

    public static class OrderDetails {
        private String customerLastName;
        private int orderNumber;
        private double orderTotalCost;

        public OrderDetails(String customerLastName, int orderNumber, double orderTotalCost) {
            this.customerLastName = customerLastName;
            this.orderNumber = orderNumber;
            this.orderTotalCost = orderTotalCost;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public double getOrderTotalCost() {
            return orderTotalCost;
        }
    }
}