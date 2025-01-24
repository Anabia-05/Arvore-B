package Queue;

class QueueNode <T>{//define classe generica

    private T info;
    private QueueNode<T> prox;

    QueueNode(T info){
        this.info = info;
     }

    T getInfo() {
        return this.info;
    }
    void setInfo(T info) {
        this.info = info;
    }
    QueueNode<T> getProx() {
        return this.prox;
    }
    void setProx(QueueNode<T> prox) {
        this.prox = prox;
    }

    
}
