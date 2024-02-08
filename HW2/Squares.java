package HW2;
public class Squares
{
   public static void main(String[] args)
   {
      int [][] Square =
         {
         {1, 14, 14, 4},
         {11, 7, 6, 9},
         {8, 10, 10, 5},
         {13, 2, 3, 15}
         };  //true
         
      print2DArray(Square);
      System.out.println(isMagicSquare(Square));
      System.out.println("Number of 4-element combos with the sum of 33: " + countCombos(Square));
      System.out.println("Total combos with the sum of 15: " + countAllCombos(Square));
   }
   
   public static void print2DArray(int[][] square) {
      for (int i = 0; i < square.length; i++) {
         for (int x = 0; x < square[0].length; x++) {
            System.out.print(square[i][x] + " ");
         }
         System.out.println();
      }
   }
   public static int countCombos(int [][] square) {
      int count = 0;

      //Rows and Columns
      for (int i = 0; i < square.length; i++) {
         int rSum = 0;
         int cSum =0;
         for (int j = 0; j < square[0].length; j++) {
            rSum += square[i][j];
            cSum += square[i][j];
         }
         if (rSum == 33) count++;
         if (cSum == 33) count ++;
      }
      //diagonals
      if (square[0][0] + square[1][1] + square[2][2] == 33) count++;
      if (square[0][2] + square[1][1] + square[2][0] == 33) count++;

      //corners
      if (square [0][0] + square[0][2] + square[2][0]+ square[2][2] == 33) count++;

      return count;
   }

   public static int countAllCombos(int[][] square) {
      int count = 0;
      int desiredSum = 33;

      for (int i = 0; i < (1 << 9); i++) {
         int sum = 0;
         for (int j = 0; j < 9; j++) {
            if ((i & (1 << j)) > 0) {
               sum += square[j / 3][j % 3];
            }
         }
         if (sum == desiredSum) {
            count++;
         }
      }
      return count;
   }


   //no touchy anything below this line. 
   public static boolean isMagicSquare(int[][] square) {
      int sum =0;
     
      for (int r = 0; r < square.length; r++) {
         sum = 0;
      //test columns 
         for (int c = 0; c < square[0].length; c++)
         {    
            sum += square[r][c];
         }
                              
         if (sum != 33)
            return false;
      }
              
   //test diagonals
   //top left to bottom right
      sum = 0;
      for(int r = 0; r < square.length; r++) {
         for (int c = 0; c < square[0].length; c++) {
            if(r == c)
               sum += square[r][c];      
         }
      }
      if(sum != 33) {
         return false;
      }   
      //top right to bottom left
      sum = 0;
      for(int c = 0; c < square.length; c++) {
         sum += square[c][square.length - 1 - c];
      }
      if(sum != 33) {
         return false;
      }   
      return true; 
   }
}