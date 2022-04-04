package pr2calc;

import java.io.IOException;

import java.lang.Math;




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
        System.out.println("vectors can not get InterProduct");
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
   // System.out.println("Matrixs can be mutiplied");
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


public Matrix transpose( ){

if(this.m.length >= 1){    

    double[][] newMatrix = new double[this.getNumOfColumn()][this.getNumOfRow()];

   

    for(int i = 0; i < this.m.length; i++){

        for(int j = 0; j < this.m[0].length; j++){

            newMatrix[j][i] = this.m[i][j];
        }
    }

    Matrix transposeMatrix = new Matrix(newMatrix);

    return transposeMatrix;

}else{

    System.out.println("No element in the matrix \n");

    Matrix transposeMatrix = new Matrix();

    return transposeMatrix;
}

}



public Matrix rotate(double theta){

    double[][] rotateCoefficient = {

        {Math.cos(Math.toRadians(theta)),  -(Math.sin(Math.toRadians(theta)))},   //   ( cos    -sin )
                                                                                //
        {Math.sin(Math.toRadians(theta)), Math.cos(Math.toRadians(theta))}      //   ( sin     cos )
    };

    Matrix rotateMatrix = new Matrix(rotateCoefficient);
  
    Matrix result = rotateMatrix.multiplyMatrix(this) ;

    return result; 
}




public static double convertIntoRadian(double theta){

    double radian;

    radian = theta*(Math.PI)/180;

    System.out.println("radian = " + radian);

    return radian;
}


public static void main(String[] args) {



double[][] v33 = {
    {-3.0},
    {3.0}
},

v34 = {
    {2.0},
    {-3.464}
},
m00 = {
    {2.0, 3.0, 5.0, 4.0},
    {-1.0, 9.0, 8.0, 7.0}
}
;

Matrix mat2 = new  Matrix(v33);
System.out.println("\nNOTE:   4.440892098500626E-16 = 0.00 \n");
System.out.println("the elements of vector A :");
mat2.display();
System.out.println("After rotating vector 45.0 degree :");
(mat2.rotate(45.0)).display();


Matrix mat1 = new  Matrix(v34);
System.out.println("\n\nNOTE:   4.440892098500626E-16 = 0.00");
System.out.println("the elements of vector A :");
mat1.display();
System.out.println("After rotating vector 60.0 degree :");
(mat1.rotate(60.0)).display();


Matrix mat0 = new  Matrix(m00);
System.out.println("\nthe elements of matrix :");
mat0.display();
System.out.println("After transpose :");
(mat0.transpose()).display();

} // end of Main()



} // end of class Matrix