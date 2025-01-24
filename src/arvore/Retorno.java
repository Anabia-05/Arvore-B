package arvore;

 class Retorno<T extends Comparable<T>>{
    private NodeB<T> no;
    private int pos;

    Retorno(NodeB<T> no, int pos){
        this.no = no;
        this.pos = pos;
    }

    void setNo(NodeB<T> no){
         this.no = no;
    }

    NodeB<T> getNo(){
        return this.no;
    }

    void setPos(int pos){
        this.pos = pos;
    }

    int getPos(){
        return this.pos;
    }
}