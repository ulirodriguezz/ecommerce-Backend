package com.example.backenddesarrollodeapps2ecommerce.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.backenddesarrollodeapps2ecommerce.model.dao.ProductoDAO;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {
    @Value("${bucketName}")
    private String bucketName;

    @Autowired
    private ProductoDAO productoDAO;

/*    public void saveFile(MultipartFile file, Long idProducto){
        String originalFileName = file.getOriginalFilename();
        try {
            String name = "";
            ProductoEntity prod = productoDAO.getPorID(idProducto.longValue());
            System.out.println("Llego el prod: "+ prod.getIdProducto());
            name = idProducto+"-"+prod.getFotos().size();
            s3.putObject(bucketName,name,convertToFile(file));
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private final AmazonS3 s3;
    @Autowired
      public S3Service(AmazonS3 s3){
        this.s3 = s3;
    }
    private File convertToFile(MultipartFile mpFile) throws IOException {
        File file = new File(mpFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mpFile.getBytes());
        fos.close();
        return file;

    }*/
}
