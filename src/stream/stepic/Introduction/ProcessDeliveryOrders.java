package stream.stepic.Introduction;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Develop a delivery processing system.
In this system, an order is an instance of the DeliveryOrder class.
Sometimes, work with the system produces duplicate orders when orderId is different,
but address and deliveryDate are the same.
Such orders are considered as duplicates.
 */
class ProcessDeliveryOrders {

    /*
    Method takes a list of orders and must return the first / earliest order
    in the list according to the value of the deliveryDate field.
    If the list is empty, the method must return an order created by its default constructor
    ("the empty order").
     */
    public static DeliveryOrder findFirstOrder(List<DeliveryOrder> orders) {
        return orders.stream()
                .min(DeliveryOrder.getComparatorByDeliveryDate())
                .orElse(new DeliveryOrder());
    }

    /*
    Method also takes a list of orders and must print addresses sorted by deliveryDate
    (from the earliest to the latest one) without duplicates.
    Every address must be print on a new line.
    If the list contains a duplicate, only the first order should be printed
    to avoid visiting the same address twice.
     */
    public static void printAddressesToDeliver(List<DeliveryOrder> orders) {
        orders.stream()
                .distinct()
                .sorted(DeliveryOrder.getComparatorByDeliveryDate())
                .forEach(order -> System.out.println(order.getAddress()));
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        List<DeliveryOrder> orders = Stream.iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine().split("\\|"))
                .map(parts -> new DeliveryOrder(
                        Long.parseLong(parts[0]), parts[2], LocalDate.parse(parts[1])))
                .collect(Collectors.toList());

        System.out.println(findFirstOrder(orders));

        printAddressesToDeliver(orders);
    }
}

class DeliveryOrder {
    private final long orderId;
    private final String address;
    private final LocalDate deliveryDate;
    // there are even more fields: customer name, phone, products info, etc

    public DeliveryOrder() {
        this.orderId = -1;
        this.address = "No address";
        this.deliveryDate = LocalDate.MIN;
    }

    public DeliveryOrder(long orderId, String address, LocalDate deliveryDate) {
        this.orderId = orderId;
        this.address = address;
        this.deliveryDate = deliveryDate;
    }

    public static Comparator<DeliveryOrder> getComparatorByDeliveryDate() {
        return Comparator.comparing(DeliveryOrder::getDeliveryDate);
    }

    public long getOrderId() {
        return orderId;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeliveryOrder that = (DeliveryOrder) o;
        return address.equals(that.address) &&
                deliveryDate.equals(that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, deliveryDate);
    }

    @Override
    public String toString() {
        return orderId + "|" + deliveryDate + "|" + address;
    }
}
