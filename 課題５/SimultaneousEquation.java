package pr2calc;

import java.io.IOException;

import java.lang.Math;


public class SimultaneousEquation extends Matrix{
    
    // instance variables decleration
    private double[] answers;

    private double[][] m;

    // Class constructor using super()
    public SimultaneousEquation(double[][] input){

        super(input);

        this.m = super.m;
        
    }


    // Methods in class

    public void normalize( int rowIndex){               // rowIndex         (Matrix element)
                                                        //  0         (1)   2   3   |   8
        double value = this.m[rowIndex][rowIndex];      //  1          4   (5)  6   |   4
                                                        //  2          7   8   (9)   |   2
        for(int i = 0; i < this.m[0].length; i++){      //  3    nomalize(0) will make value at [0][0] become 1 
                                                        //  .               (1) -->  1
            this.m[rowIndex][i] *= 1/ value;            //  .    nomalize(2) will make value at [2][2] become 1
                                                        //                  (9) -->  1 

        }

    }

    

    public void subtractRowFrom(int rowIndex){          //    if rowIndex = 0 this will make anothers rowIndex become 0,
                                                        //    BUT sure that nomalize(same rowIndex with this function) be  
                                                        //    called fisrt.
        int rowTotal = this.m.length;

        int collumTotal = this.m[0].length;

        for(int j = 0; j < rowTotal; j++){

            if( j != rowIndex){

                double value = this.m[j][rowIndex];

                for(int i = 0; i < collumTotal ; i++){

                    this.m[j][i] = this.m[j][i] - value*this.m[rowIndex][i];

                 }
            }else continue;
            

        }
       

    }



    public void solveByGaussJordan(){

        int rowTotal = this.m.length;

        for(int i=0; i < rowTotal; i++){

            System.out.println("\nMake row " + i + " collum " + i + " to 1, the others will be made to 0 ");

            this.normalize(i);

            this.subtractRowFrom(i);

            this.display();

        }

        // Pass the result to Instance Variable   double answer[]
        System.out.println("\nThe Answers:");

        for(int j = 0; j < rowTotal; j++){

            int lastCollumIndex = this.m[0].length - 1;

            this.answers = new double[rowTotal];

            this.answers[j] = this.m[j][lastCollumIndex];

            System.out.println("X" + j + " = " + this.answers[j] );
        }

    }
    

    public static void main(String[] args){

        double[][] m00 = {
            {2.0, 1.0, 3.0, 4.0, 2.0},
            {3.0, 2.0, 5.0, 2.0, 12.0},
            {3.0, 4.0, 1.0, -1.0, 4.0},
            {-1.0, -3.0, 1.0, 3.0, -1.0}
        };

        SimultaneousEquation mat0 = new SimultaneousEquation(m00);

        System.out.println("\nThe elements of Matrix :");

        mat0.display();

        mat0.solveByGaussJordan();
        
}// end of main()


}// end of class
