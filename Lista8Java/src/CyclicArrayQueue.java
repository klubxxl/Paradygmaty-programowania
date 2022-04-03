import java.util.ArrayList;

public class CyclicArrayQueue<E> implements MyQueue<E>{
    private int f=0,r=0;
    private final ArrayList<E> arrayList;

    public CyclicArrayQueue(int n){
        this.arrayList = new ArrayList<>(n+1);
        for(int i=0; i <= n ; i++){
            arrayList.add(null);
        }
    }

    @Override
    public void enqueue(E x) throws FullException {
        if(isFull()) throw new FullException("Full queue");
        else {
            arrayList.set(r, x);
            r = (r + 1) % arrayList.size();
        }
    }

    @Override
    public void dequeue() {
        if(!isEmpty()) f = (f + 1) % arrayList.size();
    }

    @Override
    public E first() throws EmptyException {
        if(isEmpty()) throw new EmptyException("Empty Queue");
        else return arrayList.get(f);
    }

    @Override
    public boolean isEmpty() {
        return  f == r;
    }

    @Override
    public boolean isFull() {
        return (r+1) % arrayList.size() == f;
    }
}
