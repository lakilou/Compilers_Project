import java.io.*;
import minipython.lexer.Lexer;
import minipython.parser.Parser;
import minipython.node.*;
import java.util.*;

public class ParserTest
{
  public static void main(String[] args)
  {
    try
    {
      Parser parser =
        new Parser(
        new Lexer(
        new PushbackReader(
        new FileReader(args[0].toString()), 1024)));

      Hashtable<String, Node> symbolTable = new Hashtable();
      Hashtable<String, ArrayList<Integer>> functionArgs = new Hashtable<>();
      Hashtable<String, String> functionReturn = new Hashtable<>();

      Start ast = parser.parse();

      ast.apply(new Visitor_1(symbolTable, functionArgs));
      ast.apply(new Visitor_2(symbolTable, functionReturn));
      ast.apply(new Visitor_3(symbolTable, functionReturn));
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

