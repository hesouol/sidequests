import java.util.*;
import java.io.*;
import java.awt.*;

//http://codeforces.com/problemset/problem/1328/F

public class EqualsK{
	
	public static long resposta = Long.MAX_VALUE;
	public static Map<Integer, Ficha> resultadoSoma = new HashMap<>();
	public static Ficha fichaBlank = new Ficha();
	
	public static void main (String[] args) {
		//Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		long[] v = {2l,1l,4l,3l,2l,2l};
		long k = 5;
		Arrays.sort(v);
		
		System.out.println(solve(v,k));
	}
	
	public static long[] criarVetorSomas(long[] v){
		long[] vetorSomas = new long[v.length];
		vetorSomas[0] = 0;
		for(int i=1;i<v.length;i++) vetorSomas[i] = vetorSomas[i-1] + v[i] - v[0];
		
		return vetorSomas;
	}
	
	public static long solve(long[] v, long k){
		long maxOps = (long) (k/2.0)*(1+k);

		long[] vetorSomas = criarVetorSomas(v);
		
		for(int i=0 ; i<=maxOps ; i++){
			for(int j=0; j<=maxOps; j++){
				//i = operacoes de soma
				//j = operacoes de subtracao
				if(i+j>maxOps) continue;
				
				//Pergunta, qual o numero de operacoes quando faco x somas + y subs?
				
				long parcial = 0;
				
				for(int l=0; l<i; l++){
					Ficha x = recursaoSoma(v,l, inicializarFichaMin(v), resultadoSoma);
					validaResultado(x,v);
				}

			}
		}
		return maxOps;		
		
	}
	
	public static boolean validaResultado(Ficha x, long[] v){
		if(x.posicaoAlterada+5 < 6) 
			return x.valorInseridoNaPosicao==v[x.posicaoAlterada+5] ;
		return false;
	}
	
	

	public static Ficha inicializarFichaMin(long[] v){
		Ficha f = fichaBlank.clone();
		f.posicaoAlterada=0;
		f.valorInseridoNaPosicao=v[0];
		f.extremoPosicao=0;
		f.soma=0;
		f.v0=v[0];
		f.encontrou=false;		
		return f;
	}


	public static Ficha recursaoSoma(long[] v, int qtd, Ficha fichaParametro, Map<Integer, Ficha> resultados){
		if(qtd==0) return fichaParametro;
		if(resultados.containsKey(qtd)) return resultados.get(qtd);
		
		Ficha f = recursaoSoma(v, qtd-1, fichaParametro, resultados);
		Ficha nova = f.clone();
			
		int posicao=0;
			
		if(f.posicaoAlterada==0){                //Chegou ao inicio do vetor na ultima iteracao.
			posicao = f.extremoPosicao+1;     //Devemos expandir a fronteira alem do que ja fomos.
			nova.soma+=v[posicao]-f.v0;        //Adicionamos o diff do inicio do vetor para o patamar que subimos.
			nova.valorInseridoNaPosicao=v[posicao]; //O valor que iremos setar eh o valor da expansao da fronteira .
			nova.extremoPosicao=posicao; //o extremo passa a ser o que atingimos agora.
		}
		else{
			posicao=f.posicaoAlterada-1;                 //Caso contrario vamos apenas replicar a soma para a posicao anterior.
			nova.soma+=f.valorInseridoNaPosicao-f.v0; //o diff eh colocado na soma total.
			if(posicao==0){
				nova.v0=f.valorInseridoNaPosicao;     //Caso tenhamos chegado ao zero, atualizamos o valor de V0.
			}
			//Aqui nao ha alteracao do valor a ser inserido.
		}
		
		nova.posicaoAlterada=posicao; //Setamos a posicao que alteramos.
		resultados.put(qtd,nova);
		
		return nova;
	}

		
	public static class Ficha{
		public int posicaoAlterada;
		public long valorInseridoNaPosicao;
		public int extremoPosicao;
		public long soma;
		public long v0;
		public boolean encontrou;
		
		public Ficha clone(){
			Ficha f = new Ficha();
			f.posicaoAlterada=posicaoAlterada;
			f.valorInseridoNaPosicao=valorInseridoNaPosicao;
			f.extremoPosicao=extremoPosicao;
			f.soma=soma;
			f.v0=v0;
			f.encontrou=encontrou;
			
			return f;
		}
		
		public Ficha(){
		}
		
	}
		

}


