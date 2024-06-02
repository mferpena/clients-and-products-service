package com.mferpena.core.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrder {
    private Order originalOrder;
    private Order updateOrder;
}
