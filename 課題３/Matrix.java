package pr2calc;

import java.io.IOException;

public class Matrix {

// 必要なフィールド（インスタンス）変数を宣言せよ
private  double[][] m;
private int numOfRoW, numOfColumn;

public Matrix(){

// 行列の行数,列数を格納するインスタンス変数の値を0に初期化

    this.numOfColumn = 0;

    this.numOfRoW    = 0;

}

public Matrix(double[][] input){

// 二次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：配列 inputの0行0列目の値を、

// 行列の0行0列目とする)
if(input.length != 0){

  int i,h;
 
  this.m = new double[input.length][input[0].length];

  for(h=0;h<input.length;h++){ 

    for(i=0;i<input[0].length;i++) 

        this.m[h][i] = input[h][i]; 

  } 
} else { System.out.println("There are no elements in " + input + "array.");  }


}

public Matrix(double[] input){

// 一次元配列 input の内容で、行列（インスタンス変数）を初期化せよ(例：行数は1、列数はinputの要素数とする)

if(input.length != 0){

    this.m = new double[1][input.length];

    for( int i=0; i<input.length; i++){
        m[0][i] = input[i];
    }
} else { System.out.println("There are no elements in " + input + "array.");  }
//    System.out.println("input.length = " + input.length );

}

public void display( ){

    // 行列内容の表示処理を実装せよ
    int h,i;
    
    for(h=0; h < this.m.length; h++){

        for(i =0; i < this.m[0].length; i++){

            System.out.print(this.m[h][i] + "       ");
        }
        System.out.println();
    }
    
    }

    


public int getNumOfRow(){

    numOfRoW =  this.m.length;

 //   System.out.println(numOfRoW);
    
    return numOfRoW;

}

public int getNumOfColumn(){

    numOfColumn = this.m[0].length;

 //   System.out.println(numOfColumn);

    return numOfColumn;

}

public double getComponentOf(int rowIndex, int columnIndex){

// 指定した範囲が存在しない場合
try{

        if( this.m[rowIndex][columnIndex] == 0.0){

        System.out.println("指定する要素は存在しません.   Element not exit" );
        
        System.exit(0);

        return 0.0;

        }

        // 指定された要素に対応する値を返す
        else{ 

            return this.m[rowIndex][columnIndex];
        }
    }
catch (Exception e){
    
        System.out.println("we have a exception \n" + e); 

        return 0.0;   
    }  

}



// ベクトルAとBの内積 A・Bの結果を返す

public double getInnerProduct(  Matrix B ){

// Aが列ベクトルである場合、エラーメッセージを表示させて System.exit(0)
    int rowOfA, colOfA, rowOfB, colOfB;

    double doubleResult = 0.0;


    rowOfA = this.m.length;
    colOfA = this.m[0].length;

    rowOfB = B.m.length;
    colOfB = B.m[0].length;


// A, B 双方とも行ベクトル、かつ、要素数が等しければ内積を計算

if(     ((rowOfA == 1) && (rowOfB == 1)) && (colOfA == colOfB)      ){

    for(int i=0; i<colOfA; i++){

        doubleResult += this.m[0][i] * B.m[0][i];

    }
    System.out.println(doubleResult);
    return doubleResult;

    


}else {         

    // Aが行ベクトル、Bが列ベクトルで、要素数が等しければ内積を計算
    if( (rowOfA == colOfB) && (colOfA == rowOfB) ){
        for(int i=0; i<colOfA; i++){

            doubleResult += this.m[0][i] * B.m[i][0];
    
        }
        System.out.println(doubleResult);
        return doubleResult;
    }else {
        System.out.println("vectors can not get InnerProduct");
        return 0.0;
    }

}

// 内積計算が可能な条件を満たさない場合は、エラーメッセージを表示させてSystem.out.exit(0)

// 計算結果を返す

}


// 行列同士の乗算が可能であるかどうか判定する

public boolean multipliable(Matrix y){

// 判定処理を実装せよ
if(this.getNumOfColumn() == y.getNumOfRow()){
    System.out.println("Matrixs can be mutiplied");
    return true;
} else {
    System.out.println("Matrix can not be mutiplied");
    return false;
}

}




// 行列同士、もしくは行列とベクトルとの積を計算する

public Matrix multiplyMatrix(Matrix target){

    
// 行列演算が実施できるかどうかの判定は、メソッドに入る前に実行した方が難易度が低い
int rowOfA, colOfA, rowOfB, colOfB;

double[][] arrResult ;

rowOfA = this.m.length;
colOfA = this.m[0].length;

rowOfB = target.m.length;
colOfB = target.m[0].length;

Matrix resultMatrix = new Matrix();
    resultMatrix.m = new double[rowOfA][colOfB];

//arrResult = new double[rowOfA][colOfB];
if(this.multipliable(target) == true){
//（Mainメソッドで、multiplyMatrixを実行する前段階で判定すると良い）

// 積の計算処理を実装せよ

    for(int k=0; k<rowOfA; k++){

        for(int i=0; i<colOfB; i++){
          double ret = 0.0;
           
          for(int j=0; j<rowOfB; j++){
            ret += this.m[k][j] * target.m[j][i];
          }
          resultMatrix.m[k][i] = ret;
        }
      }

    return resultMatrix;
}else return resultMatrix;
// 積の結果をMatrix型で返す

}



public static void main(String[] args) {


double[] x = {2.0, -3.0, 7.0};

double[] y = {-1.0, -2.0, 2.0};

double[] v31 = {3.0, 7.0, -5.0, 2.0};

double[][]

m20 = {

{1.0, 2.0, 3.0},

{3.0, 2.0, -1.0},

{4.0, 2.0, 6.0}},

m21 = {

{ 5.0, 3.0, 1.0},

{3.0, -3.0, 2.0}
},

v30 = {

{ 3.0},

{-2.0}
},

m40 = {

{1.0, 2.0, 3.0},
    
{3.0, 2.0, -1.0},
    
{4.0, 2.0, 6.0}},
    
m41 = {
    
{ 8.0, 2.0},
    
{-3.0, 2.0},

{1.0, 6.0}
    },

m50 = {

{2.0, -3.0},

{4.0, 2.0}
},


m51 = {

{-5.0, -3.0, 1.0},

{-3.0, 3.0, 2.0}
};

// ----------------Q1
 
 Matrix mat0 = new  Matrix(x);
 System.out.println("the elements of vector x ");
 mat0.display();

 Matrix mat1 = new Matrix(y);
 System.out.println("the elements of vectory ");
 mat1.display();

 System.out.println("the result of vector X x vector Y ( Q.1 ) ");
 mat0.getInnerProduct(mat1);
 System.out.println("\n\n");
//(mat0.multiplyMatrix(mat1)).display();

//------------------Q2

 Matrix mat2 = new  Matrix(m20);
 System.out.println("the elements of matrix A ");
 mat2.display();

 Matrix mat3 = new Matrix(m21);
 System.out.println("the elements of matrix B ");
 mat3.display();

 System.out.println("the result of matrix A x matrix B ( Q.2 ) ");

 if(mat2.multipliable(mat3) == true) (mat2.multiplyMatrix(mat3)).display();
 System.out.println("\n\n");

//-------------------Q3

 Matrix mat4 = new  Matrix(v31);
 System.out.println("the elements of matrix A ");
 mat4.display();

 Matrix mat5 = new Matrix(v30);
 System.out.println("the elements of matrix B");
 mat5.display();

 System.out.println("the result of matrix A x matrix B ( Q.3 ) ");
 if(mat4.multipliable(mat5) == true) (mat4.multiplyMatrix(mat5)).display();
 System.out.println("\n\n");

 //----------------Q4

 Matrix mat6 = new  Matrix(m40);
 System.out.println("the elements of matrix A ");
 mat6.display();

 Matrix mat7 = new Matrix(m41);
 System.out.println("the elements of matrix B ");
 mat7.display();

 System.out.println("the result of matrix A x matrix B ( Q.4 ) ");
 (mat6.multiplyMatrix(mat7)).display();
 System.out.println("\n\n");

 //-----------------Q5

 Matrix mat8 = new  Matrix(m50);
 System.out.println("the elements of matrix A ");
 mat8.display();

 Matrix mat9 = new Matrix(m51);
 System.out.println("the elements of matrix B ");
 mat9.display();

 System.out.println("the result of matrix A x matrix B ( Q.5 ) ");
 (mat8.multiplyMatrix(mat9)).display();
 System.out.println("\n\n");


}



}