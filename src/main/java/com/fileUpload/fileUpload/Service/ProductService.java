package com.fileUpload.fileUpload.Service;

import com.fileUpload.fileUpload.Models.Products;
import com.fileUpload.fileUpload.Respository.ProductRepository;
import com.fileUpload.fileUpload.Utility.ProductUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    public Products uploadImage(MultipartFile file) throws IOException {
        Products pImage = new Products();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(ProductUtility.compressImage(file.getBytes()));
        return productRepo.save(pImage);
    }
    public byte[] downloadImage(String fileName){
        Optional<Products> imageData = productRepo.findByName(fileName);
        return ProductUtility.decompressImage(imageData.get().getImageData());
    }
}
