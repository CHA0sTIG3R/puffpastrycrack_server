package com.cha0stig3r.recipe.server.utility;

import com.google.cloud.storage.*;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
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

  public static void updateImage(String imageName, byte[] content) throws IOException {
    Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    BlobId blobId = BlobId.of(BUCKET, PRE+imageName);
    Blob blob = storage.get(blobId);
    WritableByteChannel channel = blob.writer();
    channel.write(ByteBuffer.wrap(content));
    channel.close();
  }

  public static ByteArrayResource downloadImage(String imageName) {

    Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    byte[] content = storage.readAllBytes(BUCKET, PRE+imageName);
    return new ByteArrayResource(content);
  }

  public static void deleteImage(String imageName) {
    Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    Blob blob = storage.get(BUCKET, PRE+imageName);
    if (blob == null) {
      System.out.println("The image " + imageName + " wasn't found in " + BUCKET);
      return;
    }

    // Optional: set a generation-match precondition to avoid potential race
    // conditions and data corruptions. The request to upload returns a 412 error if
    // the object's generation number does not match your precondition.
    Storage.BlobSourceOption precondition =
            Storage.BlobSourceOption.generationMatch(blob.getGeneration());

    storage.delete(BUCKET, PRE+imageName, precondition);

    System.out.println("Image " + imageName + " was deleted from " + BUCKET);
  }

}