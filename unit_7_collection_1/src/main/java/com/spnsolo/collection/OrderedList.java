package com.spnsolo.collection;

import java.util.*;
import java.util.function.UnaryOperator;

public class OrderedList <T extends Comparable<T>>  implements List<T> {

    private static final int DEFAULT_SIZE = 10;
    private int size;

    private T[] array;

    public OrderedList(){
        array = (T[])(new Comparable[DEFAULT_SIZE]);
    }
    public OrderedList(int initialSize){
        if(initialSize > 0) array = (T[])(new Comparable[initialSize]);
        else if(initialSize == 0) array = (T[])(new Comparable[DEFAULT_SIZE]);
        else throw new IllegalArgumentException("Illegal Capacity: "+
                    initialSize);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(T e: array){
            if(e.equals(o))return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return copyOf(array,array.length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if(size == array.length){
            array = copyOf(array,array.length+5);
        }
        array[size] = t;
        size++;
        return contains(t);
    }

    @Override
    public boolean remove(Object o) {
        int indexObject;
        for (int i = 0; i < size; i++) {

        }
        return !contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (T o: array) {
            o = null;
        }
        size=0;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index > size)throw new ArrayIndexOutOfBoundsException();
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 || index > size)throw new ArrayIndexOutOfBoundsException();
        T buffer = array[index];
        array[index] = element;
        return buffer;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if(!contains(o))return -1;
        for(int i = 1;i < size;++i){
            if(array[i].equals(o))return i;
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(!contains(o))return -1;
        for(int index = array.length-1 ;index > 0;--index){
            if(array[index].equals(o))return index;
        }
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private T[] copyOf(T[] array, int changedSize){
        if(changedSize == array.length)changedSize = array.length;
        else throw new IllegalArgumentException("Illegal Capacity: "+
                changedSize);

        T[] copied = (T[])(new Comparable[size]);
        for(int i = 0; i < array.length; i++){
            copied[i]=array[i];
        }
        return copied;
    }

    private class OrderedLstIterator implements Iterator<T>{
        private int current;

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public T next() {
            return null;
        }
    }
}
