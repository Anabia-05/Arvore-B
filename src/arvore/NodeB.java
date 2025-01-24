package arvore;
 
class NodeB<T extends Comparable<T>>{
    private int n;
    private T[] chv;
    private NodeB<T>[] filho;
 
    @SuppressWarnings("unchecked")
    NodeB(int m) {
        this.chv = (T[]) new Comparable[m];
        this.filho = (NodeB<T>[]) new NodeB[m+1];
        this.n = 0;
        for (int i = 0; i < m; i++) {
            filho[i] = null;
        }
    }
 
    int getN() {
        return n;
    }
 
    void setN(int n) {
        this.n = n;
    }
 
    T getChv(int n) {
        return this.chv[n];
 
    }
 
    void setChv(T chv, int n) {
        this.chv[n] = chv;
    }
 
    NodeB<T> getFilho(int n) {
        return filho[n];
    }
 
    void setFilho(NodeB<T> filho, int n) {
        this.filho[n] = filho;
    }
 
    
}