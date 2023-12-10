package com.java.study.algorithm.init;

/**
 * @Author： yijun
 * @DATE: 2023/12/10 09:38
 * @Description
 */
public class Pair<K, V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", first, second);
    }
}
