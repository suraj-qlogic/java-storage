/*
 * Copyright 2015 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.storage;

import com.google.api.gax.paging.Page;
import com.google.api.gax.retrying.RetrySettings;
import com.google.cloud.BatchResult;
import com.google.cloud.ReadChannel;
import org.threeten.bp.Duration;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MyDemo {
/*  long nano = 1/100000;
  RetrySettings retrySettings =
      RetrySettings.newBuilder()
          .setInitialRpcTimeout(Duration.ofNanos(nano))
          .setMaxRpcTimeout(Duration.ofNanos(nano))
          .setMaxAttempts(2)
          .setTotalTimeout(Duration.ofSeconds(nano))
          .build();*/
  Storage storage =
      StorageOptions.newBuilder()
              //.setRetrySettings(retrySettings)
              .setProjectId("grass-clump-479").build().getService();

  public static void main(String[] args) throws IOException {
    MyDemo myDemo = new MyDemo();
    //  myDemo.createBlob("alex-test-bucket","butterfly.jpg");
    // myDemo.readerFromId("alex-test-bucket","butterfly_test.jpg");
    myDemo.batchGet("alex-test-bucket", "butterfly123.jpg", "butterfly.jpg");
 //   myDemo.getBlobData();

  }
  /** Example of getting information on several blobs using a single batch request. */
  public void batchGet(String bucketName, String blobName1, String blobName2) {
    StorageBatch batch = storage.batch();
    BlobId firstBlob = BlobId.of(bucketName, blobName1);
    BlobId secondBlob = BlobId.of(bucketName, blobName2);
    StorageBatchResult<Blob> result2 = batch.get(secondBlob);
    //batch.update(BlobInfo.newBuilder(secondBlob).setContentType("text/plain").build());
    StorageBatchResult<Blob> result = batch.get(firstBlob);
    batch.submit();
    List<Blob> blobList = new ArrayList<>();
    // if(result.completed()){
    // blobList.add(result.get());
    blobList.add(result2.get());
    // }

    // blobList.add(result2.get());
    System.out.println("blobList" + blobList);
  }

  public void getBlobData(){
    //myDemo.batchGet("alex-test-bucket", "butterfly123.jpg", "butterfly.jpg");
    String bucketName = "alex-test-bucket";
   String blobName = "butterfly.japg";
   BlobId blobId = BlobId.of(bucketName, blobName);
   Blob blob = storage.get(blobId);
  }

}
