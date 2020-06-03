import java.util.*;
import java.io.*;
import java.awt.*;

//http://codeforces.com/problemset/problem/1328/F

public class Patamar{
	
	public static Par achado = new Par(999, 999);
	
	
	public static void main (String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
	}
	
	
	public Ficha recursao(int[] v, Par par, Ficha f, Map<Par, int> resultados){
		
		if(par.total()>=achado.total()) return f;
		if(par.total()==0) return inicializarFicha(v);
		if(resultados.containsKey(par)) return resultados.get(par);
		
		boolean valido = validaK(v, f, k);
		
		
		Ficha f2 = recursao(v,new Par(par.somas, par.subs), resultados);
		Ficha fMin = executeSum(v, f);
		
		f = recursao(v, new Par(par.somas, par.subs), resultados);
		Ficha fMax = executeSub(v, f);
		
	}
	
	
	public boolean validaK(int[] v, Ficha f, int k){
		//A ultima posicao mexida deve ser igual a ultima posicao mexida + k posicoes (isso significa que todas sao iguais
		
		
		
		if(f.posicaoAlteradaMin + k 
		
		
		
		
		
		
	}
	
	
	public Ficha inicializarFicha(int[] v){
		Ficha f = new Ficha();
		f.posicaoAlteradaMin=0;
		f.posicaoAlteradaMax=v.length;
		f.valorInseridoNaPosicaoMin=v[0];
		f.valorInseridoNaPosicaoMax=v[v.length-1];
		f.extremoPosicaoMin=0;
		f.extremoPosicaoMax=v.length;
		f.soma=0;
		f.v0=v[0];
		f.vMax=v[v.length-1];
		f.encontrou=false;		
	}
	
	
	public Ficha executeSum(int[] v, Ficha f){
			Ficha nova = f.clone();
			
			int posicao=0;
			
			if(f.posicaoAlteradaMin==0){ //Chegou ao inicio do vetor na ultima iteracao.
				posicao = f.extremoPosicaoMin+1; //Devemos expandir a fronteira alem do que ja fomos.
				nova.sum+=v[posicao]-f.v0; //Adicionamos o diff do inicio do vetor para o patamar que subimos.
				nova.valorInseridoNaPosicaoMin=v[posicao]; //O valor que iremos setar eh o valor da expansao da fronteira .
				nova.extremoPosicaoMin=posicao; //o extremo passa a ser o que atingimos agora.
			}
			else{
				posicao=f.posicaoAlteradaMin-1; //Caso contrario vamos apenas replicar a soma para a posicao anterior.
				nova.sum+=f.valorInseridoNaPosicaoMin-f.v0; //o diff eh colocado na soma total.
				if(posicao==0){
					nova.v0=f.valorInseridoNaPosicaoMin; //Caso tenhamos chegado ao zero, atualizamos o valor de V0.
				}
				//Aqui nao ha alteracao do valor a ser inserido.
			}
			
			nova.posicaoAlteradaMin=posicao; //Setamos a posicao que alteramos.
			
			return nova;
	}
	
	
	
	
	public class Par{
		public int somas;
		public int subs
		
		public Par(int somas, int subs){
			this.somas=somas;
			this.subs=subs;
		}
		
		public int total(){
			return somas+subs;
		}
	}
	
	
	public class Ficha{
			public int posicaoAlteradaMin;
			public int posicaoAlteradaMax;
			public int valorInseridoNaPosicaoMin;
			public int valorInseridoNaPosicaoMax;
			public int extremoPosicaoMin;
			public int extremoPosicaoMax;
			public int soma;
			public int v0;
			public int vMax;
			public boolean encontrou;
			
			
			public Ficha clone(){
				Ficha f = new Ficha();
				
				f.posicaoAlteradaMin=posicaoAlteradaMin;
				f.posicaoAlteradaMax=posicaoAlteradaMax;
				f.valorInseridoNaPosicaoMin=valorInseridoNaPosicaoMin;
				f.valorInseridoNaPosicaoMax=valorInseridoNaPosicaoMax;
				f.extremoPosicaoMin=extremoPosicaoMin;
				f.extremoPosicaoMax=extremoPosicaoMax;
				f.soma=soma;
				f.v0=v0;
				f.vMax=vMax;
				f.encontrou=encontrou;
			}
			
			public static Ficha blank(){
				return new Ficha();
			}
	}
}




