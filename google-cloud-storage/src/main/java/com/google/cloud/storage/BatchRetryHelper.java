
package com.google.cloud.storage;

import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.cloud.storage.spi.v1.BatchRetryException;

import java.util.concurrent.CancellationException;

public class BatchRetryHelper {

  public static class BatchRetryAlgorithm<T> implements ResultRetryAlgorithm<T> {

    @Override
    public TimedAttemptSettings createNextAttempt(
        Throwable prevThrowable, T prevResponse, TimedAttemptSettings prevSettings) {
      return null;
    }

    @Override
    public boolean shouldRetry(Throwable prevThrowable, T prevResponse)
        throws CancellationException {
      return prevThrowable != null && (prevThrowable instanceof BatchRetryException);
    }
  }
}
