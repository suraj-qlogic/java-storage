/*
 * Copyright 2020 Google LLC
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
