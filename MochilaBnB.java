package br.com.cms.mochilatestesnet;

public class MochilaBnB
{

	static int max =100;
	static float[] unit = new float[max] ;
	static int y[] = new int[max];
	static int x[] = new int[max];
	static int fp=-1,fw;
	static int matrConf[][] = new int[max][max];
	static int n;
	
	
	static int trataConf(int c, int n)
	{
	    for(int p=0; p<n; p++)
	    {
	        if(matrConf[c][p] ==1 || matrConf[p][c]==1)
	        {


	            if(unit[c]<unit[p]  && y[p] ==1)
	            {
	                System.out.println("\n Um conflito foi localizado entre os itens: %d e %d. Item não adicionado"+ c+ p);
	                return 1;
	            }
	}

	}
	 return 0;}
	
	
	
	



	static void read_items()
	{

	   
	    System.out.println("\n Total number of items: ");
	    //System.out.println("\n" + n);
	    System.out.println("\n Maximum capacity of the knapsak: ");
	    //System.out.println("\n" + m);

	   // for(i=0;i<n;i++)
	   // {
	    	//System.out.println("\n Weight of the item : " + i+1);
	    	//System.out.println("\n" + w[i]);
	    	//System.out.println("\n Profit of the item : "+ i+1);
	    	//System.out.println("\n" +p[i]);
	    //}
	}




	static void print_items(int n, int w[], int p[])
	{
		int i;
	    float s=0.0f;
	    System.out.println("\n\tItem\tWeight\tCost\tUnit Profit\tSelected ");
	    for(i=0;i<n;i++)
	    	System.out.println("\n"+i+"\t" + w[i] +"\t"+ p[i]+"\t"+unit[i] +"\t"+x[i]);
	    System.out.println("\n\n The Rucksack now holds following items : ");
	    for(i=0;i<n;i++)
	    if(x[i]==1)
	    {
	    	System.out.println("%d\t"+i);
	        s += (float) p[i] * (float) x[i];
	    }

	    System.out.println("\n Maximum Profit: %f "+s);
	}




	/*Arrange the item based on high profit per Unit*/
	static void sort_items(int n, int p[], int w[])
	{
	    int i, t,t1, j;
	    float t2;
	    for(i=0;i<n;i++)
	    unit[i] = (float) p[i] / (float) w[i];
	    for(i=0;i<n-1;i++)
	    {
	        for(j=i+1;j<n;j++)
	        {
	            if(unit[i] < unit[j])
	            {
	                t2 = unit[i];
	                unit[i] = unit[j];
	                unit[j] = t2;
	                t = p[i];
	                p[i] = p[j];
	                p[j] = t;
	                t1 = w[i];
	                w[i] = w[j];
	                w[j] =t1;
	            }
	        }
	    }
	}




	static float bound(int n, int m,  float cp,float cw,int k, int p[], int w[])
	{
		int i;
	    float b = cp;
	    float c = cw;
	    for(i=k;i<n;i++)
	    {
	        c = c+w[i];
	        if( c < m)
	            b = b +p[i];
	        else return (b+(1-(c-m)/ (float)w[i])*p[i]);
	    }
	 return b;
	}






	static void knapsack(int k,int m, int n, float cp,float cw, int p[], int w[]) /*k - current depth in the search tree*/
	{
		if(k<n && trataConf(k, n) != 1)
		{
			
		int j;
	    if(cw+w[k] <= m)
	    {
	        y[k] = 1; /* n-tuples include item k in the optimal solution */
	        if(k <= n)
	        knapsack(k+1,m,n,cp+p[k],cw+w[k],p,w);
	        if(((cp+p[k]) > fp) && ( k == n-1))
	        {
	            fp = (int)cp+p[k];
	            fw = (int) cw+w[k];
	            for(j=0;j<=k;j++)
	            x[j] = y[j];
	        }
	    }
	    if(bound(n,m,cp,cw,k,p,w) >= fp)
	    {
	        y[k] = 0; /*n-tuples excluding item k in the optimal solution */
	        if( k <= n)
	            knapsack(k+1,m,n,cp,cw,p,w);
	        if((cp > fp) && (k == n-1))
	        {
	            fp = (int) cp;
	            fw = (int) cw;
	            for(j=0;j<=k;j++)
	                x[j] = y[j];
	        }
	    }
	}
	}



	public static void main(String[] args) 

	{
		
		
		int i;
		int n = 4;
		int m = 26;
	    int w[] = new int [n];
	    int p[] = new int [n];
	    
	    
	    matrConf[0][2] = 1;

	    
	    
	    w[0] = 10;
	    w[1] = 6;
	    w[2] = 15;
	    w[3] = 20;

	    p[0] = 100;
	    p[1] = 40;
	    p[2] = 90;
	    p[3] = 80;

	    System.out.println("\n Total number of items: ");
	    System.out.println(n);
	    System.out.println("\n Maximum capacity of the knapsak: ");
	    System.out.println(m);

	    for(i=0;i<n;i++)
	    {
	    	System.out.println("Weight of the item : " + (i+1));
	    	System.out.println(w[i]);
	    	System.out.println("Profit of the item : "+  (i+1));
	    	System.out.println(p[i]);
	    }
	    
		//void knapsack(int k,int m, int n, float cp,float cw, int p[], int w[]) /*k - current depth in the search tree*/

	    
	    
	    System.out.println("\n The Rucksack is arranged in the order\n");
	    sort_items(n, p,w);
	    knapsack(0,m,n,0.0f,0.0f,p,w);
	    print_items(n, w,p);
		
		
	}	

}
