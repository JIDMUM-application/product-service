package JIDMU.product.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private String productName;
    private String price;
    private String category;
    private int stock;
}