package pr2calc;

public class SimultaneousEquation extends Matrix{
	double[] answers;
	public SimultaneousEquation(double[][] a){
		super(a);
	}
	public void normalize(int targetRow){
		int i;
		double diagonal=m[targetRow][targetRow];
		for(i=0;i<numOfColumn;i++){
			m[targetRow][i]=m[targetRow][i]/diagonal;
		}
	}

	public void substrackRowForm(int a,int b){
		int i;
		double l = m[b][a]/m[a][a];
		for(i=0;i<numOfColumn;i++){
			m[b][i]=m[b][i]-m[a][i]*l;
		}
	}

	public void solveByGaussJordan(){
		int i,j;
		for(i=0;i<numOfRow;i++){
			//System.out.println((i+1)+"行"+(i+1)+"列目が1となるように割り,他の行の"+(i+1)+"列目が0となるように引く.");
			normalize(i);
			for(j=0;j<numOfRow;j++){
				if(i!=j) substrackRowForm(i,j);
			}
			//display();
			//System.out.println(" ");
		}
		this.answers=new double[numOfRow];
		char ch='A';
		System.out.println("Answer:");
		for(i=0;i<numOfRow-1;i++){
			answers[i]=m[i][numOfColumn-1];
			System.out.print(ch+"="+answers[i]+", ");
			ch++;
		}
		answers[i]=m[i][numOfColumn-1];
		System.out.println(ch+"="+answers[i]);
	}

	public void solveByGauss(){
		int i,j;
		double k=0;
		double l=0;
		this.answers=new double[numOfColumn];
		for(i=0;i<numOfRow-1;i++){
			for(j=i;j<numOfRow;j++){
				if(i!=j) substrackRowForm(i,j);
			}
			display();
			System.out.println(" ");
		}
		
		for(i=numOfRow-1;i>=0;i--){
			l=m[i][numOfColumn-1];
			for(j=numOfColumn-2;j>i;j--){
				l-=this.answers[j]*m[i][j];
			}
			this.answers[i]=l/m[i][i];
		}

		char ch='A';
		System.out.println("Answers:");
		for(i=0;i<numOfRow;i++){
			System.out.println(ch+"="+this.answers[i]);
			ch++;
		}
		System.out.println(" ");
		
	}

	public static void main(String[] args){
		double[][]
			mat0 = {
				{2.0,1.0,3.0,4.0,2.0},
				{3.0,2.0,5.0,2.0,12.0},
				{3.0,4.0,1.0,-1.0,4.0},
				{-1.0,-3.0,1.0,3.0,-1.0}
			};
		SimultaneousEquation s = new SimultaneousEquation(mat0);
		s.display();
		System.out.println(" ");
		s.solveByGauss();
	}
}