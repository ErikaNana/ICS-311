package skip;
import java.util.*;


public class TestProg2
{

   public static void main(String[] args)
   {

      Random r = new Random();
      SkipList S = new SkipList();

      int i;


      for ( i = 0; i < 40; i++ )
      {
         S.insert("" + r.nextInt(100),  r.nextInt(100) );
      }

//    S.printHorizontal();
//    System.out.println("------");
      S.printVertical();

   }

}