package com.cha0stig3r.recipe.server.utility;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

public class GCSUtil {

  final static String PROJECT_ID = "puffpastrycrack";

  final static String BUCKET = "puffpastrycrack_images";

  final static String PRE = "Recipe_Images/";

  public static String imgUploader(String imageName, byte[] content) throws IOException {

    String objectName = new Date().getTime() + "-" +  imageName;

    Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    BlobId blobId = BlobId.of(BUCKET, PRE+objectName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.createFrom(blobInfo, new ByteArrayInputStream(content));
    return objectName;
  }

  public static ByteArrayResource downloadObjectIntoMemory(String objectName) {

    Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    byte[] content = storage.readAllBytes(BUCKET, PRE+objectName);
    return new ByteArrayResource(content);
  }

}