package com.ymmihw;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@Fork(2)
@Threads(16)
public class FalseSharing {

  private java.util.concurrent.atomic.LongAdder builtin =
      new java.util.concurrent.atomic.LongAdder();
  private LongAdder custom = new LongAdder();

  @Benchmark
  public void builtin() {
    builtin.increment();
  }

  @Benchmark
  public void custom() {
    custom.increment();
  }

  @Benchmark
  @Fork(jvmArgs = "-XX:-RestrictContended")
  public void customRestrictContended() {
    custom.increment();
  }
}
