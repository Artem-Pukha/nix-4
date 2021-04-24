package com.spnsolo.collection;

public class MathSat<T extends Number & Comparable<T>> {
    private final int DEFAULT_SIZE = 10;
    private int capacity;

    private T[]array;

    public MathSat(){
        array = (T[])new Number[DEFAULT_SIZE];
    }
    public MathSat(int size){
        array = (T[])new Number[size];
    }
    public int size(){
        return capacity;
    }
    public MathSat(T[] numbers){
        array = (T[])new Number[numbers.length];
        for (T number : numbers) {
            add(number);
        }
    }
    public MathSat(T[] ... numbers){
        int size = 0;
        for (T[] number : numbers) {
            size += number.length;
        }
        array = (T[])new Number[size];
        for(T[] number: numbers){
            for (T t : number) {
                add(t);
            }
        }

    }
    public MathSat(MathSat numbers){
        array=(T[])new Number[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            add((T) numbers.get(i));
        }
    }
    public MathSat(MathSat<T>... numbers){
        int size = 0;
        for(MathSat<T> n:numbers){
            size += n.size();
        }
        array = (T[])new Number[size];
        for(MathSat<T> n:numbers){
            for (int i = 0; i < n.size(); i++) {
                add((T) n.get(i));
            }
        }
    }
    public void add(T o){
        if(!contains(o)){
            if(capacity == array.length){
                array = copyOf(array,array.length+5);
            }
            array[capacity] = o;
            capacity++;
        }
    }
    public void add(T ... o){
        for(int i = 0; i < o.length;++i){
            add(o[i]);
        }
    }
    public T get(int index){
        if(index < 0||index > capacity)throw new IllegalArgumentException("Incorrect index" + index);
        return array[index];
    }
    public T getMin(){
        T min = array[0];
        for(int i = 1;i < capacity;++i){
            if(array[i].compareTo(min)<0)min = array[i];
        }
        return min;
    }
    public T getMax(){
        T max = array[0];
        for(int i = 1;i < capacity;++i){
            if(array[i].compareTo(max)>0)max = array[i];
        }
        return max;
    }
    public T[] toArray(){
        return array;
    }
    public T[] toArray(int firstIndex,int lastIndex){
        T[]returnedArray = (T[])new Number[firstIndex-lastIndex+1];
        if (returnedArray.length >= 0) System.arraycopy(array, firstIndex, returnedArray, 0, returnedArray.length);
        return returnedArray;
    }
    public boolean remove(T o) {
        int indexObject = 0;
        for (int i = 0; i < capacity; i++) {
            if(array[i].equals(o)) {
                indexObject = i;
                break;
            }
        }

        boolean switcher = false;
        T[]changed = (T[])(new Number[capacity-1]);
        for(int i = 0; i < capacity - 1; i++){
            if(i==indexObject){
                switcher = true;
            }
            if(switcher)changed[i]=array[i+1];
            else changed[i]=array[i];
        }
        array = changed;
        --capacity;
        return !contains(o);
    }
    public void clear(){
        for (int i = 0; i < capacity;i++) {
            array[i]= null;
        }
        capacity=0;
    }
    public void clear(T[] cleared){
        int counter = 0;
        for (int i = 0; i < cleared.length;i++) {
            if(contains(cleared[i]))remove(cleared[i]);
        }
        capacity=0;
    }
    public MathSat squash(int firstIndex, int lastIndex){
        MathSat<T> squashed = new MathSat<>();
        for (int i = 0; i < capacity; i++) {
            if(i >= firstIndex && i <= lastIndex)squashed.add(array[i]);
        }
        return squashed;
    }
    public Number getAverage(){
        Double sum = 0d;
        for (int i = 0; i < capacity; i++) {
            sum = array[i].doubleValue();
        }
        return sum/capacity;
    }
    public Number getMedian(){
        sortAsc();
        if(capacity % 2 != 0)return array[capacity/2];
        else {
            Double first = array[capacity/2].doubleValue();
            Double second = array[capacity/2 - 1].doubleValue();
            return (first+second)/2;
        }
    }
    public void sortDesc(){
        sortDesc(0,capacity);
    }
    public void sortDesc(int firstIndex, int lastIndex){
        sort(firstIndex,lastIndex,false);
    }
    public void sortDesc(T value){
        int index = value.intValue();
        sortDesc(0,index-1);
    }

    public void sortAsc(){
        sortAsc(0, array.length);
    }
    public void sortAsc(int firstIndex, int lastIndex){
        sort(firstIndex,lastIndex,true);
    }
    public void sortAsc(T value){
        int index = value.intValue();
        sortAsc(0,index-1);
    }

    public boolean isEmpty(){ return capacity == 0;}
    private void sort(int firstIndex, int lastIndex, boolean ascending){
        for(int i = capacity - 1; i > 0;--i){
            for (int j = 0; j < i;++j) {
                if(ascending && !isBigger(i,j))swap(i,j);
                else if(!ascending && isBigger(i,j))swap(i,j);
            }
        }
    }
    private boolean isBigger(int firstIndex,int secondIndex){
        return array[firstIndex].compareTo(array[secondIndex]) > 0;
    }
    private void swap(int firstIndex,int secondIndex){
        T b = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex]=b;
    }

    private boolean contains(T o){
        boolean contained = false;
        for(int i = 0; i < capacity; ++i){
            if (o.equals(array[i])) {
                contained = true;
                break;
            }
        }
        return contained;
    }

    private T[] copyOf(T[] array, int changedSize){
        int copiedSize = 0;
        if(changedSize < array.length)copiedSize = changedSize;
        else copiedSize = array.length;

        T[] copied = (T[])(new Number[changedSize]);
        for(int i = 0; i < copiedSize; i++){
            copied[i]=array[i];
        }
        return copied;
    }
}
