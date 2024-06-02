package com.mferpena.core;

import com.mferpena.core.domain.exceptions.ProductNotFoundInOrderException;
import com.mferpena.core.domain.models.Product;

public interface ProductFinderUseCase {
    Product findProductById(Long productId) throws ProductNotFoundInOrderException;
}
