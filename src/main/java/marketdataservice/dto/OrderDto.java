package marketdataservice.dto;

import lombok.*;
import marketdataservice.enums.Side;
import marketdataservice.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDto {
    private String id;

    private String product;

    private int quantity;

    private Double price;

    private Side side;

    @Enumerated(EnumType.STRING)
    private Status status;

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

}
