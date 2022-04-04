// 以下のサンプルコード中の青い太字部分に、各自でさらにコードを追加せよ。
// 必要に応じて適切な場所に変数や処理を追加しても良い（その場合は必ず、追加したこと、及び追加の理由をコメントとして残すこと）。

package pr2calc;
//import ;

public class Matrix {
        // 必要なフィールド（インスタンス）変数を宣言せよ
        double[][] m;
        int numOfRow;
        int numOfColumn;
	
	public Matrix(){
		// 行列の行数,列数を格納するインスタンス変数の値を0に初期化
        this.numOfRow = 0;
        this.numOfColumn = 0;
	}

	public Matrix(double[][] input){
		// 二次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：配列 inputの0行0列目の値を、
		// 行列の0行0列目とする)
        int i,j;
        this.numOfRow = input.length;
        this.numOfColumn = input[0].length;
        this.m = new double[this.numOfRow][this.numOfColumn];
        for(i=0;i<this.numOfRow;i++){
            for(j=0;j<this.numOfColumn;j++){
                this.m[i][j] = input[i][j];
            }
        }
    }

	public Matrix(double[] input){
		// 一次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：行数は1、列数はinputの要素数とする)
        int i;
        this.numOfRow = 1;
        this.numOfColumn = input.length;
        this.m = new double[this.numOfRow][this.numOfColumn];
        for(i=0;i<this.numOfColumn;i++){
            this.m[0][i] = input[i];
        }
    }

	public int getNumOfRow(){
		return this.numOfRow;
    }   

	public int getNumOfColumn(){
		return this.numOfColumn;
    }

	public double getComponentOf(int rowIndex, int columnIndex){
		// 指定した範囲が存在しない場合
        if(this.m[columnIndex][rowIndex]=='\0'){
                System.out.println("指定する要素は存在しません.");
                System.exit(0);
        }
        // 指定された要素に対応する値を返す
        return this.m[rowIndex][columnIndex];
    }
        
	public void display(){
		// 行列内容の表示処理を実装せよ
        int i,j;
        for(i=0;i<this.numOfRow;i++){
            System.out.print("[");
            for(j=0;j<this.numOfColumn-1;j++){
                System.out.print(this.m[i][j]+",");
            }
            System.out.println(this.m[i][j]+"]");
        }     
	}

    // ベクトルAとBの内積 A・Bの結果を返す
    public double getInnerProduct(Matrix b){
        int inn=0;
        int i,j;
        // Aが列ベクトルである場合、エラーメッセージを表示させて System.exit(0)
        if(this.numOfColumn == 1){
            System.out.println("Aが列ベクトルなので計算できません。");
            System.exit(0);
        }
        // A, B 双方とも行ベクトル、かつ、要素数が等しければ内積を計算
        // Aが行ベクトル、Bが列ベクトルで、要素数が等しければ内積を計算
        // 内積計算が可能な条件を満たさない場合は、エラーメッセージを表示させてSystem.out.exit(0)
        if(this.numOfRow ==1 && b.numOfRow ==1 && this.numOfColumn == b.numOfColumn){
            for(i=0;i<this.numOfColumn;i++){
                inn += this.m[0][i]*b.m[0][i];
            }
        }else if(this.numOfRow ==1 && b.numOfColumn ==1 && this.numOfColumn == b.numOfRow){
            for(i=0;i<this.numOfColumn;i++){
                inn += this.m[0][i]*b.m[i][0];
            }
        }else{
            System.out.println("内積の計算ができない組み合わせです。");
            System.exit(0);
        }
        // 計算結果を返す
        return inn;
    }

    // 行列同士の乗算が可能であるかどうか判定する
	public boolean multipliable(Matrix y){
		// 判定処理を実装せよ
        if(this.numOfColumn != y.numOfRow){
            return false;
        }
        return true;
	}

    // 行列同士、もしくは行列とベクトルとの積を計算する
    public Matrix multiplyMatrix(Matrix target){
        // 行列演算が実施できるかどうかの判定は、メソッドに入る前に実行した方が難易度が低い
        //（Mainメソッドで、multiplyMatrixを実行する前段階で判定すると良い）
        // 積の計算処理を実装せよ
        // 積の結果をMatrix型で返す
        int i,j,k;
        double m=0;
        double[][] l = new double[this.numOfRow][target.numOfColumn];
        Matrix mult = new Matrix(l);
        for(i=0;i<this.numOfRow;i++){
            for(j=0;j<target.numOfColumn;j++){
                for(k=0;k<this.numOfColumn;k++){
                    mult.m[i][j] += this.m[i][k]*target.m[k][j];
                }
            }
        }
        return mult;
    }

	//転置行列の実装
	public Matrix transpose(){
		int i,j;
		double[][] l = new double[this.numOfColumn][this.numOfRow];
		Matrix tr=new Matrix(l);
		for(i=0;i<this.numOfRow;i++){
			for(j=0;j<this.numOfColumn;j++){
				tr.m[j][i]=this.m[i][j];
			}
		}
		return tr;
	}

	//回転処理(ラジアン変換)
	public static double convertIntoRadian(double theta){
		double radian;
		radian=(theta/180)*Math.PI;
		return radian;
	}

	//回転処理
	public Matrix rotate(double theta){
        if(this.numOfRow==1){
            System.out.println("error:行ベクトルが指定されています。");
            System.exit(0);
        }
        double radian = convertIntoRadian(theta);
        double[][] v={
                    {Math.cos(radian),-Math.sin(radian)},
                    {Math.sin(radian),Math.cos(radian)}};
        Matrix rot = new Matrix(v);
        double[][] l = new double[1][1];
        Matrix rotate = new Matrix(l);
        return rot.multiplyMatrix(this);
    }


    public static void main(String[] args) {
/*
 * main メソッド中で今回作成した内積計算メソッドや行列同士、ベクトルと行列、
 * 行列とベクトルの積を計算するメソッドが正常に動いているかを確認せよ。
 */

// 行列・ベクトル定義、および演算処理の一例 （あくまで一例です）　課題の要求を満たすよう、各自で加筆・修正してください

		Matrix mat0,mat1,mat2,tr,rot0,rot1;
		double theta;
		double[][] 
            m0 = {
                {5.0, 3.0, 1.0},
                {3.0, -3.0, 2.0}},
            m1 = {
                {-3.0},
                {3.0}},
            m2 = {
                {2.0},
                {-3.464}};

        mat0 = new Matrix(m0);
        mat1 = new Matrix(m1);
        mat2 = new Matrix(m2);

// 以下は、行列・ベクトル演算の実行＆結果表示の一例．不要であれば削除し，課題の条件を満たす記述を新たに追加すること

        System.out.println("m0 = ");
        mat0.display();
        System.out.println(" ");
		tr=mat0.transpose();
        System.out.println("t_m0 = ");
		tr.display();
		System.out.println("");
        System.out.println("m1(回転前) = ");
        mat1.display();
        rot0 = mat1.rotate(45);
        System.out.println("m1(45度回転) = ");
        rot0.display();
        System.out.println("");
        System.out.println("m2(回転前) = ");
        mat2.display();
        rot1 = mat2.rotate(60);
        System.out.println("m2(60度回転) = ");
        rot1.display();
        System.out.println(" ");
    }

}