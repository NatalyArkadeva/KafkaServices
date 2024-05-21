package com.nataly.metricsconsumer.service;

public interface MetricsListener {

    void listen(String metrics);

    void dltListen(byte[] in);
}
