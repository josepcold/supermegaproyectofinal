import java.util.LinkedList;


public class ArbolBinario {
    
    private Nodo raiz;
    private int num_nodos;
    private int alt;

    public ArbolBinario() {
        
        raiz = null;
        num_nodos = 0;
        alt = 0;
        
    }
    
    //Metodo para insertar el dato:
    
    public void insertar(int dato){
        
       if(buscar(dato))return;
       Nodo nuevo = new Nodo(dato);
          if (raiz == null)
              raiz = nuevo;
          else
          {
              Nodo anterior = null;
              Nodo tmp = raiz;
              while (tmp != null)
              {
                  anterior = tmp;
                  if (dato < tmp.getDato())
                      tmp = tmp.getIzq();
                  else
                      tmp = tmp.getDer();
              }
              if (dato < anterior.getDato())
                  anterior.setIzq(nuevo);
              else
                  anterior.setDer(nuevo);
          }
          num_nodos++;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getNumNodos() {
        return num_nodos;
    }
    
    //Preorden:
    //LinkedList es para ir guardando el recorrido
    
    public void preorden(Nodo aux,LinkedList recorrido){
        
          if (aux != null)
          {
              recorrido.add(aux.getDato());
              preorden (aux.getIzq(),recorrido);
              preorden (aux.getDer(),recorrido);
          }
    }
    
    //InOrden
    
    public void inorden(Nodo aux,LinkedList recorrido){
        
          if (aux != null)
          {
              inorden (aux.getIzq(),recorrido);
              recorrido.add(aux.getDato());
              inorden (aux.getDer(),recorrido);
          }
    }
    
    //PostOrden
    
    public void postorden(Nodo aux,LinkedList recorrido){
          if (aux != null)
          {
              postorden (aux.getIzq(),recorrido);
              postorden (aux.getDer(),recorrido);
              recorrido.add(aux.getDato());
          }
    }
    
    //Recorrido por nivel: utilizamos una cola para ir guardando el nodo visto y verificar el siguiente
    // y asi realizar el recorrido 
    
    public void porNivel(Nodo aux,LinkedList recorrido){
        
        LinkedList<Nodo> nivel = new LinkedList<Nodo>();
        nivel.addLast(aux);
        
        while(nivel.size()> 0){
            Nodo tmp = nivel.pollFirst();
            recorrido.add(tmp.getDato());
            if(tmp.getIzq()!=null){
                nivel.addLast(tmp.getIzq());
            }
            if(tmp.getDer()!=null){
                nivel.addLast(tmp.getDer());
            } 
        }
    }
    
    private void altura (Nodo aux,int nivel)  {
        if (aux != null) {    
            altura(aux.getIzq(),nivel+1);
            alt = nivel;
            altura(aux.getDer(),nivel+1);
        }
    }
    
    //Devuleve la altura del arbol
    public int getAltura(){
        altura(raiz,1);
        return alt;
    }
   
    
    //Borrar el que sea
    
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        Nodo z = borrar(this.raiz, x);
        this.setRaiz(z);
        return x;

    }

    private Nodo borrar(Nodo r, int x) {
        if (r == null) {
            return null;		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzq(borrar(r.getIzq(), x));
        } else if (compara < 0) {
            r.setDer(borrar(r.getDer(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (r.getIzq() != null && r.getDer() != null) {
               
                Nodo cambiar = buscarMin(r.getDer());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDer(borrar(r.getDer(), x));
                System.out.println("2 ramas");
            } else {
                r = (r.getIzq() != null) ? r.getIzq() : r.getDer();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return r;
    }

    //buscar
    
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));

    }

    private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzq(), x));
        } else if (compara < 0) {
            return (buscar(r.getDer(), x));
        } else {
            return (true);
        }
    }

    //buscar min
    
    private Nodo buscarMin(Nodo r) {
        for (; r.getIzq() != null; r = r.getIzq());
        return (r);
    }
    
   
     
}
