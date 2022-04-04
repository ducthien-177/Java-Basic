package pr2calc;
//----------------------------------
//		4J41 LE DUC THIEN 課題2
//----------------------------------
import java.io.*;

public class SourceExample{ // 同色の中括弧閉じ（この例の一番最後にある"}"）までが、SourceExampleオブジェクト

                            // の扱うデータと処理を定義する、SourceExampleクラス

    private int a; // 青字の部分がフィールド変数（メソッドの外にある）。

    private int[][] b; // クラス内の、main()メソッドを除く全メソッドで

    private String str; // 利用可能

    public SourceExample(){ // 各変数の初期化を実行（例なので、中身は適当です）

        int h,i; // メソッド内で宣言される変数は、メソッド内のみで利用可

        this.a = -1;    // フィールド変数として定義された変数は、クラス内どこでも利用可（this.a ・・・

                        // "この(= this)オブジェクト(インスタンス)自身の持つ変数a"）

        this.b = new int[2][2];

        for(h=0;h<this.b.length;h++){ // 二次元配列の値を -1 で初期化

            for(i=0;i<this.b[0].length;i++) // 配列に関しては、"new" を用いずに

                this.b[h][i] = -1; // 初期化することも可能（詳細はmain

        } // メソッド内を参照）

    // this.b.length : 配列の第一要素の要素数、this.b[0].length : 配列の第二要素の要素数

        this.str = "";  // 文字列型はオブジェクトとして用意されると記述したが、非常によく

                    // 用いられる型であるため、左のような記法も認められている

                    // （new String(""); という書き方でも当然OK）

        }

// フィールド変数 a, b, str の値を内部で初期化（設定）するコンストラクタをもう一つ記述する

    public SourceExample(int l,int m[][],String n){

        int i,j;

        this.a = l;

        this.b = new int[3][3];

        this.str = n;

        for(i=0;i<this.b.length;i++){

            for(j=0;j<this.b[0].length;j++){

                this.b[i][j] = m[i][j];

            }

        }

    }

	// ファイルからのデータ入力で変数を初期化するコンストラクタを作成

	public SourceExample(String fileName){

		try{

		// ファイル"fileName"から、データを読み込むメソッドを呼び出す

            String data;

            BufferedReader  fin = new BufferedReader(new FileReader(fileName));

            if((loadData(fileName)) == false){

                throw new java.io.IOException();

            }

		}catch(IOException e){ 
            
            System.out.println("ファイルからの入力に失敗しました。");

            System.exit(0);

        }

	}

	


    public void setA(int value){ // メソッドを通して、フィールド変数の値を設定する（setterと呼ばれる）

        this.a = value;

    }

    public int getA(){ // メソッドを通して、フィールド変数の値を返す（getterと呼ばれる）

        return this.a;

    }

    public void setB(int[][] value){

        this.b = value;

    }

    public int[][] getB(){

        return this.b;

    }

    public void setStr(String value){

        this.str = value;

    }

    public String getStr(){

        return this.str;

    }

    public void showAllContentsOfB(){

        // 各自で必要な内容を記述すること

        int i,j;

        for(i=0;i<this.b.length;i++){

            for(j=0;j<this.b[0].length;j++){

                if((j+1)%this.b[0].length==0){

                    System.out.println(this.b[i][j]);
                
                }else{

                    System.out.print(this.b[i][j]+",");

                }

            }

        }

    }

	private boolean loadData(String fileName) throws IOException{

		int h,i;

		int row, column;

		BufferedReader fin = new BufferedReader(new FileReader(fileName));

		String inputData;

		String[] inputValue;

		// inputData に、ファイルから文字列を一行分読み込む

        inputData = fin.readLine();

		// 得られた文字列データを、スペース(= "\\s") で区切り、配列 inputValue へ格納

        inputValue = inputData.split("\\s");

		if(inputValue.length != 1)

			return false;

		else{

			// フィールド（インスタンス）変数 a に、inputValueの最初（0番目）の要素を代入
			//aに代入する値がint型に変換できなかったときにfalseを返すようにした．

            try{
                
                this.a = Integer.parseInt(inputValue[0]);

            }catch(Exception e){

                return false;

            }

			// もう一行読み込み、スペース区切りで inputValue へデータ(次に読み込む行列の行数＆列数）を格納

            inputData = fin.readLine();

            inputValue = inputData.split("\\s");

			if(inputValue.length != 2) // 行数＆列数の双方が格納されていなければ

				return false;

			else{

				// 変数 row に行数のデータ、column に列数のデータを代入
				//代入するデータがint型に変換できなかった場合にfalseを返すようにした．

                try {

                    row  = Integer.parseInt(inputValue[0]);

                    column = Integer.parseInt(inputValue[1]);

                    
                } catch (Exception e) {
                    
                    return false;
    
                }

                row  = Integer.parseInt(inputValue[0]);

                column = Integer.parseInt(inputValue[1]);

				this.b = new int[row][column];

				// 配列 b のh行i列目の要素に、読みんだファイルのh行目、(左から）i番目のデータを格納
				//読み込んだ行数，列数と次の行の配列の行数と列数が違っているときにfalseを返すようにした．

				try {
				
					for(h=0;h<row;h++){

                    	inputData = fin.readLine();

                    	inputValue = inputData.split("\\s");
	
                    	for(i=0;i<column;i++){

                        	this.b[h][i] = Integer.parseInt(inputValue[i]);

                    	}
                	}
                }catch (Exception e){
                
                	return false;
                	
                }

				// 最後に一行読み込み、スペース区切りで inputValue へデータ(文字列）を格納

                inputData = fin.readLine();

                for(h=0;h<inputData.length();h++) 

                    inputValue = inputData.split("\\s");

				// フィールド（インスタンス）変数 str に、格納した文字列を代入

                this.str = String.join(" ",inputValue);

			}

		}

		fin.close();

		return true;

	}

/* このプログラムを実行すると、以下の main メソッドに記述された動作が実行される */

    public static void main(String[] args) throws IOException{

        SourceExample ex; // SourceExample クラスのオブジェクト ex を宣言

        if(args.length != 1){

            System.out.println("引数が不適当です。データ入力用のファイル名を再度指定して下さい。"); 

            String fn = null;

            // キーボードからファイル名を入力して 変数 fn に代入する

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            fn = in.readLine();

            ex = new SourceExample(fn);

        }

        else

            ex  = new SourceExample(args[0]);

        System.out.println("オブジェクトのaフィールドの値は"+ex.getA()+"です");

        System.out.println("");

        ex.showAllContentsOfB();

        System.out.println("");

        System.out.println(ex.getStr());

    }

} // ここまでがクラス SourceExample