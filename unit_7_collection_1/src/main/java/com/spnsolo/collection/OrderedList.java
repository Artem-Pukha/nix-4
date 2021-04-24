package com.spnsolo.collection;

import java.util.*;

public class OrderedList <T extends Comparable<T>>  implements List<T> {

    private final int DEFAULT_SIZE = 10;
    private int size;

    private T[] array;
    private Object[] elementData;

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
        for (int i = 0; i < size; i++) {
            if(array[i].equals(o))return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new OrderedIterator();
    }

    @Override
    public Object[] toArray() {
        return copyOf(array,array.length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        if(size == array.length){
            array = copyOf(array,array.length+5);
        }
        array[size] = t;
        size++;
        sort();
        return contains(t);
    }

    @Override
    public boolean remove(Object o) {
        int indexObject = 0;
        for (int i = 0; i < size; i++) {
            if(array[i].equals(o)) {
                indexObject = i;
                break;
            }
        }

        boolean switcher = false;
        T[]changed = (T[])(new Comparable[size-1]);
        for(int i = 0; i < size - 1; i++){
            if(i==indexObject){
                switcher = true;
            }
            if(switcher)changed[i]=array[i+1];
            else changed[i]=array[i];
        }
        array = changed;
        --size;
        return !contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(c.size() > size) return false;
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if(checkSize(c.size())) return false;
        for(Object o: c){
            add((T)o);
        }
        return true;
    }

    @Deprecated
    @Override
    public boolean addAll(int index, Collection<? extends T> c) { return false; }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(checkSize(c.size())) return false;
        for(Object o: c){
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < size; i++) {
            if(!c.contains(array[i])){
                remove(i);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        Arrays.fill(array,null);
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

    @Deprecated
    @Override
    public void add(int index, T element) {}

    @Override
    public T remove(int index) {
        remove(array[index]);
        return array[index];
    }

    @Override
    public int indexOf(Object o) {
        if(!contains(o))return -1;
        for(int i = 1;i < size;++i){
            if(array[i].equals(o))return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(!contains(o))return -1;
        for(int index = size-1 ;index > 0;--index){
            if(array[index].equals(o))return index;
        }
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new OrderedListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if(index < 0||index > size)throw new IllegalArgumentException("Illegal Capacity: "+ index);
        return new OrderedListIterator(index);
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        OrderedList<T>buffer = new OrderedList<>(toIndex-fromIndex+1);
        for(int i = fromIndex; i < toIndex+1;++i){
            buffer.add(array[i]);
        }
        return buffer;
    }

    private T[] copyOf(T[] array, int changedSize){
        int copiedSize = 0;
        if(changedSize < array.length)copiedSize = changedSize;
        else copiedSize = array.length;

        T[] copied = (T[])(new Comparable[changedSize]);
        for(int i = 0; i < copiedSize; i++){
            copied[i]=array[i];
        }
        return copied;
    }

    private class OrderedIterator implements Iterator<T>{
        private int current;
        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public T next() {
            current++;
            return array[current - 1];
        }
    }
    private class OrderedListIterator implements ListIterator<T>{
        private int current;

        public OrderedListIterator(){current=0;}
        public OrderedListIterator(int index){current=index;}
        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public T next() {
            current++;
            return array[current - 1];
        }

        @Override
        public boolean hasPrevious() {
            return current!=0;
        }

        @Override
        public T previous() {
            current--;
            return array[current + 1];
        }

        @Override
        public int nextIndex() {
            return current+1;
        }

        @Override
        public int previousIndex() {
            return current-1;
        }

        @Override
        public void remove() {
            OrderedList.this.remove(current);
        }

        @Override
        public void set(T t) {
            array[current] = t;
        }

        @Override
        public void add(T t) {
            if(size == array.length){
                array = copyOf(array,array.length+5);
            }
            array[size] = t;
            size++;
            sort();
        }
    }
    private void sort(){
        for(int i = size - 1; i > 0;--i){
            for (int j = 0; j < i;++j) {
                if(array[j].compareTo(array[i]) > 0){
                    T b = array[i];
                    array[i]= array[j];
                    array[j]=b;
                }
            }
        }
    }
    private boolean checkSize(int size){
        return size > this.size;
    }


}
