package arvore;
import Queue.Queue;
 
public class TreeB<T  extends Comparable<T>> {
    private NodeB<T> raiz;
    private int ordem;
    private int maxChaves;
    private int minimo;
 
    public TreeB(int m){
        this.ordem = m;
        this.maxChaves = m-1;
        this.minimo = (m-1)/2;
        this.raiz = null;
    }
 
 
    private boolean isEmpty(){
        if(this.raiz == null){
            return true;
        }
        else{
            return false;
        }
    }
  
 
    private void cisao(NodeB<T> pai, int i ,NodeB<T> filho) {
        NodeB<T> novoFilho = new NodeB<>(ordem);
       
        for (int j = pai.getN(); j > i; j--) {
            pai.setFilho(pai.getFilho(j), j + 1);
        }
 
        pai.setFilho(novoFilho, i + 1);
        
        for (int j = pai.getN() - 1; j >= i; j--) {
            pai.setChv(pai.getChv(j), j + 1);
        }
 
        pai.setChv(filho.getChv(minimo), i);
        pai.setN(pai.getN() + 1);
        filho.setN(filho.getN() - 1);
        filho.setChv(null, minimo);
       
 
        
        for (int j = 0; j < minimo; j++) {
            novoFilho.setChv(filho.getChv(j + minimo + 1), j);
            filho.setChv(null, j + minimo + 1);
  
        }
 
        if (filho.getFilho(0) != null) {
            for (int j = 0; j < minimo + 1 ; j++) {
                novoFilho.setFilho(filho.getFilho( j+ minimo + 1), j);
                filho.setFilho(null, j + minimo + 1);
            }
        }
 
        filho.setN(minimo);
        novoFilho.setN(minimo);
        
        
        
        
    }
 
    public void inserir(T value) {
        Retorno<T> no = buscarNo(value);
    
        // Se o valor não estiver presente, podemos inseri-lo
        if (no.getNo() == null) {
            if (isEmpty() == true) {
                // Caso a árvore esteja vazia, inicializa a raiz
                this.raiz = new NodeB<T>(ordem);
                raiz.setChv(value, 0);
                raiz.setN(raiz.getN() + 1);
                porNivel();
               
            } else {
                // Inserção no nó não cheio
                inserirNoNaoCheio(this.raiz, value);
                porNivel();
            }
        } else {
            // Se o valor já existe na árvore
            System.out.println("Valor já presente na árvore");
        }
    }
    
    private void inserirNoNaoCheio(NodeB<T> no, T value) {
        int i = no.getN() - 1;

        if (no.getFilho(0) == null) {
            // Inserção no nó folha
            while (i >= 0 && no.getChv(i) != null && value.compareTo(no.getChv(i)) < 0) {
                no.setChv(no.getChv(i), i + 1);  
                i--;
            }
            no.setChv(value, i + 1);  
            no.setN(no.getN() + 1);    
        } else {
            // Inserção em nó não folha
            while (i >= 0 && no.getChv(i) != null && value.compareTo(no.getChv(i)) < 0) {
                i--;
            }
            i++;
    
            // Chama recursivamente para o filho apropriado
            inserirNoNaoCheio(no.getFilho(i), value);
    
            // Verifica se o nó filho ficou cheio após a inserção
            if (no.getFilho(i).getN() > maxChaves) {
                // Chama o método de cisão para dividir o nó filho
                cisao(no, i, no.getFilho(i));
    
                // Após a cisão, pode ser necessário avançar para o próximo nó (caso o valor seja maior que o valor no nó pai)
                if (value.compareTo(no.getChv(i)) > 0) {
                    i++;
                }
            }
        }
        // Verifica se a raiz precisa ser dividida
        if (no.getN() > maxChaves && no == this.raiz) {
            NodeB<T> novo = new NodeB<>(ordem);
            this.raiz = novo;
            novo.setFilho(no, 0);
            cisao(novo, 0, no);  
        }
    }
    private Retorno<T> acharMenor(){
        Retorno<T> no = new Retorno<>(null,-1);
        if(isEmpty() == true){
            return no;
        }
        else{
            NodeB<T> aux = this.raiz;
            while(aux.getFilho(0) != null){
                aux = aux.getFilho(0);
            }
            no.setNo(aux);
            no.setPos(0);
            return no;
        }
    }
    public void mostrarMenor(){
        Retorno<T> no = acharMenor();
        NodeB<T> aux;
        if(no.getNo() == null){
            System.out.println("Árvore Vazia");
        }
        else{
            aux = no.getNo();
            System.out.println(aux.getChv(no.getPos()));
        }
    }
    
    private Retorno<T> acharMaior(){
        Retorno<T> no = new Retorno<>(null,-1);
        if(isEmpty() == true){
            return no;
        }
        else{
            NodeB<T> aux = this.raiz;
            while(aux.getFilho(aux.getN()) != null){

                aux = aux.getFilho(aux.getN());

            }
            no.setNo(aux);
            no.setPos(aux.getN()-1);
            return no;

        }
        
    }
    public void mostrarMaior(){
        Retorno<T> no = acharMaior();
        NodeB<T> aux;
        if(no.getNo() == null){
            System.out.println("Árvore Vazia");
        }
        else{
            aux = no.getNo();
            System.out.println(aux.getChv(no.getPos()));
        }
    }

    public int altura(){
        if(isEmpty()== true){
            return 0;
        }
        else if(this.raiz.getFilho(0) == null){
            return 0;
        }
        else{
            int alt = -1;
            NodeB<T> aux = this.raiz;
            while(aux != null){
                aux = aux.getFilho(0);
                alt++;
            }
            return alt;
        }
    }

    int buscaBinaria(T value, NodeB<T> aux) {
        int meio, i, f, resultado;
        i = 0;
        f = aux.getN() - 1;  
        while (i <= f) {
            meio = (i + f) / 2;
            
            if (aux.getChv(meio) == null) {
                return meio;
            }
            
            resultado = value.compareTo(aux.getChv(meio));
            
            if (resultado == 0) {
                return meio; 
            } else if (resultado < 0) {
                f = meio - 1;  
            } else {
                i = meio + 1;  
            }
        }
        // Se não encontrou, retorna o índice da posição onde o valor pode ser inserido
        return i;
    }
    
    private Retorno<T> buscarNo(T value){
        Retorno<T> no = new Retorno<T>(null,-1);
        int pos;
        if(this.isEmpty() == true){
            return no;
        }
        else{
            NodeB<T> aux = this.raiz;
            while(aux != null){
                pos = buscaBinaria(value,aux);
                if(aux.getChv(pos) != null && aux.getChv(pos).compareTo(value) == 0){
                    no.setNo(aux);
                    no.setPos(pos);
                    return no;
                }
                else{
                    aux = aux.getFilho(pos);
                }
            }
     
            return no;
        }
    }

    public void busca(T value){
        Retorno<T> no = buscarNo(value);
        if(no.getNo() == null){
            System.out.println("Valor não encontrado");
        }
        else{
            System.out.println("Valor encontrado na posicao: " + no.getPos());
        }
    }
    public void emOrdem(){
        if(this.isEmpty() == true){
            System.out.println("arvore vazia");
        }
        else{
            percorrerEmOrdem(this.raiz);
        }
    }
    
 
    public void percorrerEmOrdem(NodeB<T> r) {
        int i;
        for (i = 0; i < r.getN(); i++) {
            if (r.getFilho(i) != null) {
                percorrerEmOrdem(r.getFilho(i));
            }
            System.out.print(r.getChv(i)+ " ");
        }
        
        if (r.getFilho(i) != null) {
            percorrerEmOrdem(r.getFilho(i));
        }
        System.out.println();
    }
 
    public void porNivel() {
        // Passeio por nível incluindo nós nil e verificação de imprimir apenas ativos
        if (this.isEmpty()) {
            System.out.println("Árvore vazia");
            return;
        }
    
        Queue<NodeB<T>> fila = new Queue<>();  // Usando a fila personalizada
        fila.enQueue(this.raiz);  // Adiciona a raiz na fila
        
        while (!fila.isEmpty()) {
            NodeB<T> aux = fila.deQueue();  // Remove o nó da frente da fila
    
            // Imprime as chaves do nó atual
            for (int i = 0; i < aux.getN(); i++) {
                System.out.print(aux.getChv(i) + " ");
                
            }
            System.out.print("|");
            // Adiciona os filhos não nulos na fila
            for (int i = 0; i <= aux.getN(); i++) {
                NodeB<T> filho = aux.getFilho(i);
                if (filho != null) {
                    fila.enQueue(filho);  // Coloca o filho na fila para o próximo nível
                }
            }
        }
        System.out.println();
    }
    
   
    public void redistribuiChaves(NodeB<T> filho, NodeB<T> irmao, NodeB<T> pai, int indice) {
        if (irmao == pai.getFilho(indice + 1)) { // Irmão à direita
            // Caso o número de chaves do irmão seja par
            if (irmao.getN() % 2 == 0) {
                // Mover a chave do pai para o filho
                filho.setChv(pai.getChv(indice), filho.getN());
                filho.setN(filho.getN() + 1);
        
                // Mover a chave do irmão para o pai
                pai.setChv(irmao.getChv(0), indice);
        
                // Mover os filhos do irmão para o filho
                if (irmao.getFilho(0) != null) {
                    filho.setFilho(irmao.getFilho(0), filho.getN());
                    filho.setN(filho.getN() + 1);  // Incrementa o número de filhos
                }
        
                // Mover as chaves do irmão (exceto a primeira, que já foi movida para o pai)
                for (int i = 1; i < irmao.getN(); i++) {
                    irmao.setChv(irmao.getChv(i), i - 1);  // Desloca as chaves para a esquerda no irmão
                }
        
                // Mover os filhos do irmão (exceto o primeiro, que já foi movido)
                for (int i = 1; i <= irmao.getN(); i++) {
                    irmao.setFilho(irmao.getFilho(i), i - 1);
                }
        
                irmao.setN(irmao.getN() - 1);  // Atualiza o número de elementos no irmão
            } else {
                // Caso o número de chaves do irmão seja ímpar
                // Mover a chave do pai para o filho
                filho.setChv(pai.getChv(indice), filho.getN());
                filho.setN(filho.getN() + 1);
    
                // Mover a chave do irmão para o pai
                pai.setChv(irmao.getChv(0), indice);
    
                // Mover os filhos do irmão para o filho
                if (irmao.getFilho(0) != null) {
                    filho.setFilho(irmao.getFilho(0), filho.getN());
                    //filho.setN(filho.getN() + 1);
                }
    
                // Mover as chaves do irmão (exceto a primeira, que já foi movida para o pai)
                for (int i = 1; i < irmao.getN(); i++) {
                    irmao.setChv(irmao.getChv(i), i - 1);  // Desloca as chaves para a esquerda no irmão
                }
    
                // Mover os filhos do irmão (exceto o primeiro, que já foi movido)
                for (int i = 1; i <= irmao.getN(); i++) {
                    irmao.setFilho(irmao.getFilho(i), i - 1);
                }
    
                irmao.setN(irmao.getN() - 1);  // Atualiza o número de elementos no irmão
            }
        } else { // Irmão à esquerda
            // Caso o número de chaves do irmão seja par
            if (irmao.getN() % 2 == 0) {
                // Mover a chave do pai para o filho
                filho.setChv(filho.getChv(0), 2);
                filho.setChv(pai.getChv(indice - 1), 1);
                filho.setN(filho.getN() + 2);
            
                // Mover a chave do irmão para o pai
                pai.setChv(irmao.getChv(irmao.getN() - 2), indice - 1);
                filho.setChv(irmao.getChv(irmao.getN() -1), 0);
            
                // Mover os filhos do filho para a direita
                for (int i = filho.getN() - 1; i >= 0; i--) {
                    filho.setFilho(filho.getFilho(i), i + 1);
                }
    
                for (int i = 1; i >= 0; i--) {
                    filho.setFilho(irmao.getFilho(irmao.getN() - i - 1), i);
                }
    
                irmao.setN(irmao.getN() - 2);
            } else {
                // Caso o número de chaves do irmão seja ímpar
                filho.setChv(filho.getChv(0), 1);
                filho.setChv(pai.getChv(indice - 1), 0);
                filho.setN(filho.getN() + 1);
        
                // Mover a chave do irmão para o pai
                pai.setChv(irmao.getChv(irmao.getN() - 1), indice - 1);
        
                // Mover os filhos do filho para a direita
                for (int i = filho.getN() - 1; i >= 0; i--) {
                    filho.setFilho(filho.getFilho(i), i + 1);
                }
                filho.setFilho(irmao.getFilho(irmao.getN()), 0);
        
                irmao.setN(irmao.getN() - 1);
            }
        }
    }
    
    
    public void fusao(NodeB<T> filho, NodeB<T> irmao, NodeB<T> pai, int indice) {
        // Caso 1: Fusao com o irmao à direita
        if (irmao == pai.getFilho(indice + 1)) {
            // Mover a chave do pai para o filho
            filho.setChv(pai.getChv(indice), filho.getN());
            filho.setN(filho.getN() + 1);
    
            // Mover as chaves e filhos do irmão para o filho
            for (int i = 0; i < irmao.getN(); i++) {
                filho.setChv(irmao.getChv(i), filho.getN() + i);
                //filho.setFilho(irmao.getFilho(i), filho.getN() + i);
            }
            for (int i = 0; i <= irmao.getN(); i++) {
                filho.setFilho(irmao.getFilho(i), filho.getN() + i);
            }
    
            filho.setN(filho.getN() + irmao.getN());

            for (int i = indice; i < pai.getN(); i++) {
                pai.setChv(pai.getChv(i + 1), i);
                pai.setFilho(pai.getFilho(i + 1), i);
            }
        
            pai.setN(pai.getN() - 1);
        } else {  // Caso 2: Fusao com o irmao à esquerda
            
            // Desloca os filhos do filho para a direita
            for (int i = filho.getN(); i >= 0; i--) {
                filho.setFilho(filho.getFilho(i), i + irmao.getN() + 1);
            }
            // Desloca as chaves do filho para a direita
            for (int i = filho.getN(); i >= 0; i--) {
                filho.setChv(filho.getChv(i), i + irmao.getN() + 1);
            }
            
            // Mover as chaves do irmão para o filho
            for (int i = 0; i < irmao.getN(); i++) {
                filho.setChv(irmao.getChv(i), i);
            }

            for (int i = 0; i <= irmao.getN(); i++) {
                filho.setFilho(irmao.getFilho(i), i);
            }
            
            
            filho.setChv(pai.getChv(indice - 1), irmao.getN());
            filho.setN(filho.getN() + irmao.getN() + 1);

            // Remover a chave do pai após a fusão
            for (int i = indice - 1; i < pai.getN(); i++) {
                pai.setChv(pai.getChv(i + 1), i);
                pai.setFilho(pai.getFilho(i + 1), i);
            }
        
            pai.setN(pai.getN() - 1);
        }
    
        // Se a fusão ocorreu na raiz e ela está vazia, o único filho se torna a nova raiz
        if (pai == this.raiz && pai.getN() == 0) {
             this.raiz = filho;
        }
    }
  
   
    private Retorno<T> encontraValorRemover(NodeB<T> no, int indice) {
        NodeB<T> filho = no.getFilho(indice + 1);  // A subárvore à direita do nó
        while (filho.getFilho(0) != null) {
            filho = filho.getFilho(filho.getN());  // Vai até o filho mais à direita
        }
        Retorno<T> retorno = new Retorno<T>(filho, 0);
        return retorno;  // O maior valor na subárvore direita
    }

    private Retorno<T> buscarPai(T value){
        // Se a árvore estiver vazia, retorna null
        if (this.isEmpty()) {
            return null;
        }
    
        // Inicia a busca na raiz
        NodeB<T> no = this.raiz;
        Retorno<T> pai = new Retorno<>(null, -1); // O pai será atualizado conforme a busca
    
        // Enquanto houver um nó para explorar
        while (no != null) {
            int pos = buscaBinaria(value, no); // Busca binária no nó atual
    
            // Se encontramos o valor no nó, retornamos o pai atual
            if (no.getChv(pos) != null && no.getChv(pos).compareTo(value) == 0) {
                return pai;  // Retorna o pai com a posição da chave
            }
            else{
                pai.setNo(no);
                pai.setPos(pos);
                no = no.getFilho(pos);
            }
            
        }
    
        // Se não encontrar o valor, retorna null
        return null;
    }
    

    // Função de remoção
    public void remocao(T value) {
        if(isEmpty() == true){
            System.out.println("Árvore vazia");

        }
        else{
            remocaoEmArvoreB(value, this.raiz);
        }
       
    }

    
    private void remocaoEmArvoreB(T value, NodeB<T> no) {
        Retorno<T> nó = buscarNo(value);
        Retorno<T> buscaP = buscarPai(value);
    
        if (nó.getNo() != null) {
            NodeB<T> noAtual = nó.getNo();
            int posicao = nó.getPos();
    
            if (noAtual.getFilho(0) == null) {
                // Caso 1: O nó é uma folha
                for (int i = 0; i < noAtual.getN(); i++) {
                    if (noAtual.getChv(i).equals(value)) {
                        for (int j = i; j < noAtual.getN() - 1; j++) {
                            noAtual.setChv(noAtual.getChv(j + 1), j);
                        }
                        noAtual.setN(noAtual.getN() - 1);
                        verificarEPropagar(noAtual, buscaP); // Verifica após remoção em folha
                        break;
                    }
                }
            } else {
                // Caso 2: O nó não é uma folha
                
                Retorno<T> sucessor = encontraValorRemover(noAtual, posicao);
        
                // Guarda o valor do sucessor
                T valorSucessor = sucessor.getNo().getChv(sucessor.getPos());
                buscaP = buscarPai(valorSucessor);
                // Substitui o valor a ser removido pelo sucessor
                noAtual.setChv(valorSucessor, posicao);
                
                // Remove o valor sucessor (que está em uma folha)
                NodeB<T> noSucessor = sucessor.getNo();
                for (int i = sucessor.getPos(); i < noSucessor.getN() - 1; i++) {
                    noSucessor.setChv(noSucessor.getChv(i + 1), i);
                }
                noSucessor.setN(noSucessor.getN() - 1);
                
                // Verifica e propaga as alterações a partir do nó do sucessor
                verificarEPropagar(noSucessor, buscaP);
            }
        }
        else{
            System.out.println("Valor não esta presente na árvore");
        }
    }
    
    private void verificarEPropagar(NodeB<T> no, Retorno<T> buscaP) {
        if (no.getN() < minimo) {
            if (buscaP != null && buscaP.getNo() != null) {
                NodeB<T> irmao, pai;
                pai = buscaP.getNo();
                int posF = buscaP.getPos();
    
                if (posF > 0) {
                    irmao = pai.getFilho(posF - 1);
                } else {
                    irmao = pai.getFilho(posF + 1);
                }
    
                if (irmao.getN() > minimo) {
                    redistribuiChaves(no, irmao, pai, posF);
                } else {
                    fusao(no, irmao, pai, posF);
                    
                    // Após fusão, verifica se o pai precisa de reorganização
                    if (pai.getN() < minimo && pai.getChv(0) != null) {
                        Retorno<T> paiDoPai = buscarPai(pai.getChv(0));
                        verificarEPropagar(pai, paiDoPai);
                    }
                }
            }
        }
    }

}
